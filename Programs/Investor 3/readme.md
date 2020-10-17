# Dual-objective optimization (PMRM)

The problem of finding the best stock to maximize the earnings are a function of both risk and reward. So here we scalarize the problem into a single objective function such that we get multiple pareto optimal points which negotiate the possibility of rewards. A scalarizing function for the 2-dimensional features vector in the decision data is calculated as the combined ranks in risk and reward priority tables. The algo-rithm can be summarized as shown below.

***for(i in date_range){    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;for(i in stocks_list){    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;predict next day stock price;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;calculate AIC;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;calculate price percentage difference;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}
calculate combined ranks;    
if(percentage difference > 0){    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;select top 5 stocks by combined ranks;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;invest amount = balance/portfolio_size in each stock;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}    
}***    

The program does not hold out any balance in the Wallet, as long as the criteria are fulfilled by any 5 stocks.
