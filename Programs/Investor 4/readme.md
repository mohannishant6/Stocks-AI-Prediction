# Hold-out with Risk Minimization (HRM)

The simple yet effective extension of the “Buy low – sell high” methodology is the hold out method where we risk more conservatively and expand the stock balance more steadily by holding out any earnings in the game and investing only the initial amount the agent gets right at the beginning of the progression. For example, if the agent is provided a 1000 dollars at the beginning of the 1st iteration or day and it earns 5 dollars by the end of that day, it will hold the extra 5 dollars in the Wallet and invest only the 1000 dollars it had at the beginning. In case the agent incurs a loss of 5 dol-lars and has only 995 dollars to invest the next day it will invest all the 995 dollars. This implements the risk minimization paradigm considering both the decision of investing as well as the amount of investing at any point of time. By investing less, the risk of losing reduces along with the obvious reduction in probability of higher reward.

***for(i in date_range){
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;for(i in stocks_list){    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;predict next day stock price;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;calculate AIC;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;calculate price percentage difference;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}
if(percentage difference > 0){    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;select top 5 stocks by AIC;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;calculate investment as min(balance,initial_balance);    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;invest amount = investment/portfolio_size in each stock;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}    
calculate extra balance as balance,initial_balance;    
if(extra balance > 0){    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PocketMoney = PocketMoney + extra balance;    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}    
}***    
