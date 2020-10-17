# Profit Maximization (PM)
This algorithm is essentially a greedy approach, keeping only the reward metric as the decision data. The algorithm can be summarized as shown below.

***for(i in date_range){    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;for(i in stocks_list){    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;predict next day stock price;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;calculate AIC;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;calculate price percentage difference;    
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;select top 5 stocks by maximum percentage difference;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if(percentage difference > 0){    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;invest amount = balance/portfolio_size in each stock;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}    
}***    

Any extra balance if not invested is stored in the reserve i.e. Wallet, which is inde-pendent of any changes in the stock price values.