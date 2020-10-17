#!/usr/bin/env python
# coding: utf-8

# In[1]:


import warnings
import numpy as np
import matplotlib.pyplot as plt
warnings.filterwarnings("ignore")
plt.style.use('fivethirtyeight')
import pandas as pd
import matplotlib
from pyramid.arima import auto_arima
import yfinance as yf
import random
from multiprocessing import Pool
from functools import partial


# In[2]:


def data_prep(path, dest, distinct_stocks = 100):
    try:
        pd.read_csv(dest)
    except:
        dfc = pd.read_csv(path,sep=",")
        l = dfc.set_index('Symbol').to_dict()['Description']
        df = pd.DataFrame()
        lr = random.sample(list(l),round(distinct_stocks*3))

        for i in lr:
            dftemp = yf.download(i, 
                              start='2010-01-01', 
                              end='2020-02-29', 
                              progress=False,
                             interval= '1d')
            dftemp['Stock Symbol'] = i
            dftemp['Stock name'] = l[i]
            df = df.append(dftemp)

        df2 = df[['Stock Symbol','Close']]
        
        stocks_list = list(np.unique(df2['Stock Symbol']))
        stocks_list = random.sample(list(stocks_list),distinct_stocks)
        df2 = df2[df2['Stock Symbol'].isin(stocks_list)]
        df2 = df2.sort_values('Date')
        df2.to_csv(dest)


# In[3]:


def read_stock_data(path):
    df = pd.read_csv(path)
    df = df.sort_values('Date')
    return df


def multi_process(i, df, date):
        try:
            df2 = df.loc[df['Stock Symbol'] == i].drop(columns = 'Stock Symbol')
            df2.isnull().sum()
            df2['Date'] = pd.to_datetime(df2['Date'], format='%Y-%m-%d', errors='coerce')
            df2t = df2[df2['Date'] <= date].iloc[-713:,]
            df2v = df2[df2['Date'] > date].iloc[:1,]
            df2t = df2t.set_index('Date')
            df2t_2 = df2t.diff().dropna()
            df2v = df2v.set_index('Date')

            model = auto_arima(df2t_2, trace=False, error_action='ignore', suppress_warnings=True)
            model.fit(df2t_2)

            next_day_forecast = model.predict(n_periods=1)[0] + df2t.iloc[-1:,].values[0][0]
            AIC = model.aic()
            next_day_value = df2v.iloc[:1,].values[0][0]
            last_day_value = df2t.iloc[-1:,].values[0][0]
            l = [i, last_day_value, next_day_forecast, AIC, next_day_value]
            return l
        except:
            print('Error with ',i)
        
            
def create_decision_data(df, date, ls):

    # dl = [['Stock Symbol','last_day_value','next_day_forecast','AIC','next_day_value']]
    
    func = partial(multi_process, df = df, date = date)
    
    pool = Pool()
    dl = pool.map(func, ls)
    pool.close()
    pool.join()
    
    dl = [i for i in dl if i != None]
    
    print(dl)
    print(type(dl))
    
    df3 = pd.DataFrame(dl)
    # df3.columns = df3.iloc[0]
    # df3 = df3.drop(df3.index[0])
    
    df3.columns = ['Stock Symbol','last_day_value','next_day_forecast','AIC','next_day_value']
    
    df3['diff'] = (df3['next_day_forecast'] - df3['last_day_value'])/df3['last_day_value']
    df3 = df3.sort_values('diff', ascending = False)
    return df3

# In[4]:


def update_ledger(df3, path):
    try:
        dfl = pd.read_csv(path)
        dfl2 = dfl.merge(df3, how = 'left', on = 'Stock Symbol')
        dfl2['Investment'] = np.where(dfl2['Stock Symbol'] != 'pocketmoney',dfl2['Investment']*(dfl2['last_day_value']/dfl2['Bought at']),dfl2['Investment'])
        dfl2['Bought at'] = np.where(dfl2['Stock Symbol'] != 'pocketmoney',dfl2['last_day_value'],1)
        dfl2 = dfl2[['Stock Symbol','Bought at','Investment']]
    except:
        dfl2 = pd.DataFrame(columns = ['Stock Symbol','Bought at','Investment'])
    return dfl2

def make_a_move(df3,dfl,path,num_investments):
    if dfl.shape[0] == 0:
        balance = 1000
    else:
        balance = dfl['Investment'].sum()
    df3 = df3.iloc[:num_investments,]
    df3['Investment'] = np.where(df3['diff'] > 0.0, balance/num_investments, 0)
    
    dflf = df3[['Stock Symbol','last_day_value','Investment']]
    dflf.columns = ['Stock Symbol','Bought at','Investment']
    dflf = dflf.append(pd.DataFrame(data = {'Stock Symbol':['pocketmoney'],'Bought at':[1],'Investment':[(balance - df3['Investment'].sum())]})).reset_index().drop(columns = 'index')
    
    dflf.to_csv(path)
    return balance


# In[ ]:


src = "AI Stock Project/Programs/" ## Change this to the source of the folder containing the program file

data_prep(path = src + "Source Data/Stock codes.csv", dest = src + "Source Data/main_data.csv", distinct_stocks = 400)

df = read_stock_data(path = src + "Source Data/main_data.csv")
l = [['Date','Balance']]
date = '2020-01-01'
num_investments = 5

dfl_csv = pd.DataFrame(columns = ['Stock Symbol','Bought at','Investment'])

ls = np.unique(df['Stock Symbol'])

while date < df['Date'].max():
    try:
        dfb = pd.read_csv(src + 'Investor 1/Results/balances.csv')
        date = (pd.to_datetime(dfb['Date'].max(),format='%Y-%m-%d') + pd.DateOffset(1)).strftime('%Y-%m-%d')
    except:
        dfb = pd.DataFrame(columns = ['Date','Balance'])
        date = '2020-01-01'

    df3 = create_decision_data(df, date, ls)
    dfl2 = update_ledger(df3, path = src + 'Investor 1/Results/ledger.csv')
    balance = make_a_move(df3,dfl2,num_investments = num_investments, path = src + 'Investor 1/Results/ledger.csv')
    l.append([date,balance])
    
    df4 = pd.DataFrame(l)
    df4.columns = df4.iloc[0]
    df4 = df4.drop(df4.index[0])
    
    dfl_csv = dfl_csv.append(dfl2).reset_index().drop(columns = 'index')
    dfl_csv.to_csv(src + 'Investor 1/Results/Historical Ledger.csv')
    
    df4.to_csv(src + 'Investor 1/Results/balances.csv')
    print('Next iteration :',date < df['Date'].max())

