
String src = "C:\\Users\\adhis\\Desktop\\DV Assignment 3\\Final\\Processing Program\\";   /**************Needs to be set by the user to folder containing data directories*************/

Table table;

float sf = 2;
float min_balance;
float max_balance;
float balance_start;

/* Table 1 */
String[] unique_stocks = new String[400]; /* rows in unique stocks file */
int[] states = new int[342]; /* rows in historical ledger file */
String[] stocks = new String[342];
float[] bought_at = new float[342];
float[] investments = new float[342];
float[] balances = new float[59]; /* Rows in balances data */

/* Table 2 */
int[] states_2 = new int[342]; /* rows in historical ledger file */
String[] stocks_2 = new String[342];
float[] bought_at_2 = new float[342];
float[] investments_2 = new float[342];
float[] balances_2 = new float[59]; /* Rows in balances data */

/* Table 3 */
int[] states_3 = new int[342]; /* rows in historical ledger file */
String[] stocks_3 = new String[342];
float[] bought_at_3 = new float[342];
float[] investments_3 = new float[342];
float[] balances_3 = new float[59]; /* Rows in balances data */

/* Table 4 */
int[] states_4 = new int[342]; /* rows in historical ledger file */
String[] stocks_4 = new String[342];
float[] bought_at_4 = new float[342];
float[] investments_4 = new float[342];
float[] balances_4 = new float[59]; /* Rows in balances data */

int cnt = 1;
int frame = 1;
int SavedFrame = 1;
int square_side = 50;
int spacing = 7;

int x;
int y;

String path1 = src + "Investor 2\\Unique Stocks.csv";  /* Only need to be read once */

String path2 = src + "Investor 2\\Historical Ledger.csv";
String path3 = src + "Investor 2\\balances.csv";
        
String path4 = src + "Investor 3\\Historical Ledger.csv";
String path5 = src + "Investor 3\\balances.csv";
      
String path6 = src + "Investor 4\\Historical Ledger.csv";
String path7 = src + "Investor 4\\balances.csv";

String path8 = src + "Investor 1\\Historical Ledger.csv";
String path9 = src + "Investor 1\\balances.csv";

void setup() 
  {
    /***************Data read******************/
    
  cnt = 1;
    table = loadTable(path1, "header");
    for (TableRow row : table.rows()) 
      {
        String stock = row.getString("Stock Symbol");
        unique_stocks[cnt-1] = stock;
        cnt += 1;
      }
    
  cnt = 1;
    table = loadTable(path2, "header");
    for (TableRow row : table.rows()) 
      {
        int state = row.getInt("State");
        states[cnt-1] = state;
        String stock = row.getString("Stock Symbol");
        stocks[cnt-1] = stock;
        float ba = row.getFloat("Bought at");
        bought_at[cnt-1] = ba;
        float investment = row.getFloat("Investment");
        investments[cnt-1] = investment;
        cnt += 1;
      }
  
    cnt = 1;
    table = loadTable(path3, "header");
    for (TableRow row : table.rows()) 
      {
        float balance = row.getFloat("Balance");
        balances[cnt-1] = balance;
        cnt += 1;
      }
    
  cnt = 1;
    table = loadTable(path4, "header");
    for (TableRow row : table.rows()) 
      {
        int state = row.getInt("State");
        states_2[cnt-1] = state;
        String stock = row.getString("Stock Symbol");
        stocks_2[cnt-1] = stock;
        float ba = row.getFloat("Bought at");
        bought_at_2[cnt-1] = ba;
        float investment = row.getFloat("Investment");
        investments_2[cnt-1] = investment;
        cnt += 1;
      }
  
    cnt = 1;
    table = loadTable(path5, "header");
    for (TableRow row : table.rows()) 
      {
        //float balance = row.getFloat("Balance");
        balances_2[cnt-1] = row.getFloat("Balance");
        cnt += 1;
      }
     
  cnt = 1;
    table = loadTable(path6, "header");
    for (TableRow row : table.rows()) 
      {
        int state = row.getInt("State");
        states_3[cnt-1] = state;
        String stock = row.getString("Stock Symbol");
        stocks_3[cnt-1] = stock;
        float ba = row.getFloat("Bought at");
        bought_at_3[cnt-1] = ba;
        float investment = row.getFloat("Investment");
        investments_3[cnt-1] = investment;
        cnt += 1;
      }
  
    cnt = 1;
    table = loadTable(path7, "header");
    for (TableRow row : table.rows()) 
      {
        float balance = row.getFloat("Balance");
        balances_3[cnt-1] = balance;
        cnt += 1;
      }
    
  cnt = 1;
    table = loadTable(path8, "header");
    for (TableRow row : table.rows()) 
      {
        int state = row.getInt("State");
        states_4[cnt-1] = state;
        String stock = row.getString("Stock Symbol");
        stocks_4[cnt-1] = stock;
        float ba = row.getFloat("Bought at");
        bought_at_4[cnt-1] = ba;
        float investment = row.getFloat("Investment");
        investments_4[cnt-1] = investment;
        cnt += 1;
      }
  
    cnt = 1;
    table = loadTable(path9, "header");
    for (TableRow row : table.rows()) 
      {
        float balance = row.getFloat("Balance");
        balances_4[cnt-1] = balance;
        cnt += 1;
      }
    
    /**************Setting up display***********/
    size(2500,1400);
    strokeJoin(ROUND);
    background(255);
    //noStroke();
    noLoop();  // Run once and stop
    //frameRate(1);
    
    min_balance = min(min(min(balances),min(balances_2)),min(min(balances_3),min(balances_4)));
    max_balance = max(max(max(balances),max(balances_2)),max(max(balances_3),max(balances_4)));
    balance_start = ((1000-min_balance)/(max_balance + 0.00001 - min_balance));
    
    for(int i = 0; i < 58; i ++)
    {
        balances[i] = ((balances[i]-min_balance)/(max_balance + 0.00001 - min_balance));
        balances_2[i] = ((balances_2[i]-min_balance)/(max_balance + 0.00001 - min_balance));
        balances_3[i] = ((balances_3[i]-min_balance)/(max_balance + 0.00001 - min_balance));
        balances_4[i] = ((balances_4[i]-min_balance)/(max_balance + 0.00001 - min_balance));
        //print(balances[i],balances_2[i],balances_3[i],balances_4[i],"\n");
    }
  }

//float min_cal(float vals[])
//{
//    float min_val = 100000;
//    for(int i = 0; i < vals.length; i++)
//    {
//        if(min_val > vals[i])
//        {
//            min_val = vals[i];
//        }
//        print(min_val,"\n");
//    }
//    return min_val;
//}

void stock_select(String stocks2[], float bought_at2[], float investments2[], int ind)
{
  stroke(0);
  for(int i = 0; i < 400; i++)
  {
    fill(0,0);
    x = (i%20)*(square_side + spacing) + 80;
    y = (i/20)*(square_side + spacing) + 180;
    //print(x,y,"\n");
    strokeWeight(2.5);
    square(x,y,square_side);
    
    int flag = 0;
  
    for(int j = 0; j < 6; j++)
    {
        //print(stocks2[j],"\n");
        
        if(unique_stocks[i].equals(stocks2[j]))
        {
            String s = unique_stocks[i];
            
            int m = 0;
            int n = 0;
            
            if(ind == 1)
            {
                m = 540;
                n = 0;
            }
            else if(ind == 2)
            {
                m = 540;
                n = 270;
            }
            else if(ind == 3)
            {
                m = 0;
                n = 270;
            }
            
            textSize(30);
            fill(0);
            text(s,(square_side + spacing)*23 + 45 + m, 200 + n + j*50 + 120);
            text(bought_at2[j],(square_side + spacing)*23 + 45 + 150 + m, 200 + n + j*50 + 120);
            text(investments2[j],(square_side + spacing)*23 + 45 + 350 + m, 200 + n + j*50 + 120);
            
            flag = 2;
            if(investments2[j] > 0)
            {
                flag = 1;
            }
        }
    }
    
    if(flag == 1)
    {
        if(ind == 1)
        {
            fill(150,150,50,100);
            stroke(150,150,50,100);
        }
        else if(ind == 2)
        {
            fill(50,150,150,100);
            stroke(50,150,150,100);
        }
        else if(ind == 3)
        {
            fill(150,50,150,100);
            stroke(150,50,150,100);
        }
        else if(ind == 4)
        {
            fill(150,150,150,100);
            stroke(150,150,150,100);
        }
    }
    else if(flag == 2)
    {
      fill(50,150,50,100);
      stroke(50,150,50,100);
    }
    else
    {
      fill(0,0);
    }
    
    if(flag == 1 | flag == 2)
    {
        int a = 0;
        int b = 0;
        
        if(ind == 1)
        {
            a = square_side/2;
            b = 0;
        }
        else if(ind == 2)
        {
            a = square_side/2;
            b = square_side/2;
        }
        else if(ind == 3)
        {
            a = 0;
            b = square_side/2;
        }
        
        x = (i%20)*(square_side + spacing) + 80;
        y = (i/20)*(square_side + spacing) + 180;
        strokeWeight(0);
        square(x + a,y + b,square_side/2);
        stroke(0);
    }
    
    fill(0);
    textSize(15);
    text(unique_stocks[i],x+5,y+30);
  }
  
}

void draw_balance(int frame, float balances[], int ind)
{
    if(ind == 1)
    {
        stroke(150,150,50,200);
    }
    else if(ind == 2)
    {
        stroke(50,150,150,200);
    }
    else if(ind == 3)
    {
        stroke(150,50,150,200);
    }
    else if(ind == 4)
    {
        stroke(150,150,150,200);
    }
    
    strokeWeight(5);
    for(int i = 0; i < frame-1; i++)
    {
        float x1 = (square_side + spacing)*22 + 100 + i*20;
        float y1 = height + 200 - balances[i]*height/sf;
        float x2 = (square_side + spacing)*22 + 100 + (i+1)*20;
        float y2 = height + 200 - balances[i + 1]*height/sf;
        line(x1, y1, x2, y2);   
    }
}

void draw()
{    
    background(255,255);
    SavedFrame += 1;
    
    fill(0,0);
    strokeWeight(2);
    rect(width/2 + 150,160,width/2 - 300,60);
    
    fill(0);
    textSize(30);
    text("Press |>| Key to move forward or |<| Key to move backwards", width/2 + 180, 200);
    
    String[] stocks2 = new String[342];
    float[] bought_at2 = new float[342];
    float[] investments2 = new float[342];
  
    String[] stocks2_2 = new String[342];
    float[] bought_at2_2 = new float[342];
    float[] investments2_2 = new float[342];
  
    String[] stocks2_3 = new String[342];
    float[] bought_at2_3 = new float[342];
    float[] investments2_3 = new float[342];
  
    String[] stocks2_4 = new String[342];
    float[] bought_at2_4 = new float[342];
    float[] investments2_4 = new float[342];
    
    for(int i = 0; i < 6; i++)
    {
        int index = (frame-1)*6 + i;
    
        stocks2[i] = stocks[index];
        bought_at2[i] = bought_at[index];
        investments2[i] = investments[index];
    
        stocks2_2[i] = stocks_2[index];
        bought_at2_2[i] = bought_at_2[index];
        investments2_2[i] = investments_2[index];
    
        stocks2_3[i] = stocks_3[index];
        bought_at2_3[i] = bought_at_3[index];
        investments2_3[i] = investments_3[index];
    
        stocks2_4[i] = stocks_4[index];
        bought_at2_4[i] = bought_at_4[index];
        investments2_4[i] = investments_4[index];
    }
    
    stock_select(stocks2,bought_at2,investments2,1);
    stock_select(stocks2_2,bought_at2_2,investments2_2,2);
    stock_select(stocks2_3,bought_at2_3,investments2_3,3);
    stock_select(stocks2_4,bought_at2_4,investments2_4,4);
    
    //float[] balances2 = new float[59];  
    
    fill(50,0,50,10);
    rect((square_side + spacing)*22 + 90,height - 100 - balance_start*height/sf,1050,450);
    stroke(0);
    line((square_side + spacing)*22 + 100,height - 100 - balance_start*height/sf,(square_side + spacing)*22 + 100,height + 350 - balance_start*height/sf);
    line((square_side + spacing)*22 + 100,height + 200 - balance_start*height/sf,width - 100,height + 200 - balance_start*height/sf);
    
    fill(0);
    textSize(60);
    translate((square_side + spacing)*22 + 30, height - 200);
    rotate(3*HALF_PI);
    text("Balance",0,0);
    rotate(-3*HALF_PI);
    translate(-((square_side + spacing)*22 + 30), -(height - 200));
    
    textSize(40);
    translate((square_side + spacing)*22 + 70, height - 880);
    rotate(3*HALF_PI);
    text("Investor-1",0,0);
    rotate(-3*HALF_PI);
    translate(-((square_side + spacing)*22 + 70), -(height - 880));
    
    textSize(40);
    translate((square_side + spacing)*22 + 1160, height - 830 - 270);
    rotate(HALF_PI);
    text("Investor-3",0,0);
    rotate(-HALF_PI);
    translate(-((square_side + spacing)*22 + 1160), -(height - 830 - 270));
    
    textSize(40);
    translate((square_side + spacing)*22 + 1160, height - 830);
    rotate(HALF_PI);
    text("Investor-4",0,0);
    rotate(-HALF_PI);
    translate(-((square_side + spacing)*22 + 1160), -(height - 830));
    
    textSize(40);
    translate((square_side + spacing)*22 + 70, height - 880 + 270);
    rotate(3*HALF_PI);
    text("Investor-2",0,0);
    rotate(-3*HALF_PI);
    translate(-((square_side + spacing)*22 + 70), -(height - 880 + 270));
    
    textSize(30);
    translate((square_side + spacing)*22 + 70, height - 130);
    rotate(3*HALF_PI);
    text("Starting with 1000 dollars",0,0);
    rotate(-3*HALF_PI);
    translate(-((square_side + spacing)*22 + 70), -(height - 130));
    
    textSize(25);
    text("Stock",(square_side + spacing)*22 + 45 + 50, 270);
    text("Unit Price",(square_side + spacing)*22 + 45 + 210, 270);
    text("Investment",(square_side + spacing)*22 + 45 + 400, 270);
    
    text("Stock",(square_side + spacing)*22 + 45 + 50 + 540, 270);
    text("Unit Price",(square_side + spacing)*22 + 45 + 210 + 540, 270);
    text("Investment",(square_side + spacing)*22 + 45 + 400 + 540, 270);
    
    fill(150,150,150,30);
    rect((square_side + spacing)*22 + 45 + 40, 160 + 120, 520, 260);
    rect((square_side + spacing)*22 + 45 + 200, 160 + 120, 360, 260);
    rect((square_side + spacing)*22 + 45 + 390, 160 + 120, 170, 260);
    
    fill(150,150,50,30);
    rect((square_side + spacing)*22 + 45 + 40 + 540, 160 + 120, 520, 260);
    rect((square_side + spacing)*22 + 45 + 200 + 540, 160 + 120, 360, 260);
    rect((square_side + spacing)*22 + 45 + 390 + 540, 160 + 120, 170, 260);
    
    fill(150,50,150,30);
    rect((square_side + spacing)*22 + 45 + 40, 430 + 120, 520, 260);
    rect((square_side + spacing)*22 + 45 + 200, 430 + 120, 360, 260);
    rect((square_side + spacing)*22 + 45 + 390, 430 + 120, 170, 260);
    
    fill(50,150,150,30);
    rect((square_side + spacing)*22 + 45 + 40 + 540, 430 + 120, 520, 260);
    rect((square_side + spacing)*22 + 45 + 200 + 540, 430 + 120, 360, 260);
    rect((square_side + spacing)*22 + 45 + 390 + 540, 430 + 120, 170, 260);
    
    draw_balance(frame, balances, 1);
    draw_balance(frame, balances_2, 2);
    draw_balance(frame, balances_3, 3);
    draw_balance(frame, balances_4, 4);
    
    textSize(70);
    fill(0);
    String t = "Progression for the investers : Day - "+frame;
    text(t,width/2 - textWidth(t)/2,100);
    
    fill(255,0);
    rect(10,10,width-20,height - 20);
    line(10,130,width-10,130);
    
    //line(20,20,width-20,height-20);
    
    saveFrame(src + "Images\\" + SavedFrame + ".jpg");
}

void keyPressed() {
  if(keyCode == 37)
    {
      frame = max(frame - 1,1);
      //print(keyCode,key);
      redraw();
    }
 
  if(keyCode == 39)
    {
        frame = min(frame + 1,53);
        //print(keyCode,key);
        redraw();
    }
}