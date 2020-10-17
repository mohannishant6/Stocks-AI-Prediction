# Investor Progression

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;![Stock Progresssion Visualization](https://github.com/amittal-tcd/TCD-work/blob/master/AI%20Project/Stocks%20AI%20project/Animated%20Visualization/Viz/Investment-Portfolio-Manager.gif)

This is an exploratory visualization which can be used to identify patterns of stock selection by different agents. Also, the individual portfolios show the selection order for different agents as calculated by the ranking logics. Finally, the balance amounts answer the question of which algorithm performed the best. The visualization is broken down into 3 parts which form 3 levels of detail for a hierarchical drill down of investment strategy and its effectiveness.

#### 1. Stock State:
The left half of the screen presents the set of 400 stocks where each stock is presented using a square stock banner with its name inside. Each investor selects at most 5 stocks denoted by coloured square pointers in the same colour as the investor’s ledger state. The 4 pointers are spaced in 4 corners of the banners to avoid confusion due to overlapping of pointers when 2 investors select the same stock/stocks. The position of the pointer is also same as the position of ledger states of the investors in ledger state space. The space can be used to identify patterns of similarity in stock selection by different agents.

#### 2. Ledger State:
The selected stocks’ current unit price and the amount decided by the investor to invest in each stock is shown in the top right quadrant of the visualization. It denotes the move each investor makes in response to new market data. The order of stocks in each ledger shows the priority of each stock as per ranking logic of the investor.

#### 3. Balance State:
The investors are given 1000 dollars each to invest at Day-0. As they move ahead in time buying and selling stocks the initial amount changes as per the opening price of stocks on the next day. The balance states explain the effectiveness of each algorithm in terms of final balance amounts and resilience to market fluctuations which shows accuracy in predicting market risk.

> Therefore, the strategy can be explained starting from the selection of stocks, amount decided to be invested and the final gain from the investments.