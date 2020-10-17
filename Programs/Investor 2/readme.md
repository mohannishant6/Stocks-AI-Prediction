# Risk Minimization (RM)

This algorithm picks the stocks with the minimum risk or AIC values for the forecast and invests in the stock if the percentage difference in unit price values are more than 0.

***for(i in date_range){    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;for(i in stocks_list){    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;predict next day stock price;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;calculate AIC;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;calculate price percentage difference;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;select top 5 stocks by minimum AIC if percentage dif-ference > 0;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;invest amount = balance/portfolio_size in each stock;    
}***    

The algorithm takes any stocks out of the 400 which meet the above criteria and since there are no other limitations, no amount will be held outside that iteration of trade in the Wallet.
