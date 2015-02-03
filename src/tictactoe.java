
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

/*
 * Copyright 2015 tbking.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 *
 * @author tbking
 */
public class tictactoe {
    int player,computer,status=-1,rr=0,rc=0,n=3,turn=0,row,column,check=0;
    String[][] matrix = new String[n][n];
    Random randomGenerator = new Random();
    Scanner scan = new Scanner(System.in);
    int[][] backend = new int [n][n];
    tictactoe()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                matrix[i][j]="_";
                backend[i][j]=0;
            }
        }
        
    }
    public void player(String choice)
    {
        switch (choice) {
            case "X":
                player=1;
                computer=0;
                break;
            case "O":
                player=0;
                computer=1;
                break;
            default:
                player=computer=-1;
                System.out.println("Invalid choice. Try again.");
                
        }
    }
    public void input()
    {
        System.out.println("Enter the box number.");
            int box=scan.nextInt();
            switch(box)
            {
                case 1:
                    row=0;column=0;
                    break;
                case 2:
                    row=0;column=1;
                    break;
                case 3:
                    row=0;column=2;
                    break;
                case 4:
                    row=1;column=0;
                    break;
                case 5:
                    row=1;column=1;
                    break;
                case 6:
                    row=1;column=2;
                    break;
                case 7:
                    row=2;column=0;
                    break;
                case 8:
                    row=2;column=1;
                    break;
                case 9:
                    row=2;column=2;
                    break;
                default:
                    System.out.println("Invalid selection!");
                    input();
            }
            while(backend[row][column]!=0)
            {
                System.out.println(backend[row][column]);
                System.out.println("Invalid selection! Try again.");
                input();
            }
    }
    public void human() throws IOException
    {
       if(turn<n*n)
       {
           input();
            
            if(player==1)
            {
                matrix[row][column]="X";
                backend[row][column]=1;
            }
            else if(player==0)
            {
                matrix[row][column]="O";
                backend[row][column]=-1;
            }
            turn++;
       }
    }
    public void print()
    {
        if(turn ==0)
            System.out.println("-------------\n| 1 | 2 | 3 |\n-------------\n| 4 | 5 | 6 |\n-------------\n| 7 | 8 | 9 |\n-------------");  
        else if(turn<=n*n)
        {
            System.out.println("-------------");
            System.out.printf("| %s | %s | %s |\n",matrix[0][0],matrix[0][1],matrix[0][2]);
            System.out.println("-------------");
            System.out.printf("| %s | %s | %s |\n",matrix[1][0],matrix[1][1],matrix[1][2]);
            System.out.println("-------------");
            System.out.printf("| %s | %s | %s |\n",matrix[2][0],matrix[2][1],matrix[2][2]);
            System.out.println("-------------");
        }
        else
        {
            status=3;
        }
    }
    public int check()
    {
        int sum;
            //row
            for(int i=0;i<n;i++)
            {
                sum=backend[i][0]+backend[i][1]+backend[i][2];
                if(sum==-n)
                {
                    status=0;
                    return status;
                }
                else if(sum==n)
                {
                    status=1;
                    return status;
                }
            }
            //column
            for(int i=0;i<n;i++)
            {
                sum=backend[0][i]+backend[1][i]+backend[2][i];
                if(sum==-n)
                {
                    status=0;
                    return status;
                }
                else if(sum==n)
                {
                    status=1;
                    return status;
                }
            }
            //diagonally\
                sum=backend[0][0]+backend[1][1]+backend[2][2];
                if(sum==-n)
                {
                    status=0;
                    return status;
                }
                else if(sum==n)
                {
                    status=1;
                    return status;
                }
            //diagonally/
                sum=backend[0][2]+backend[1][1]+backend[2][0];
                if(sum==-n)
                {
                    status=0;
                    return status;
                }
                else if(sum==n)
                {
                    status=1;
                    return status;
                }
                if(turn ==n*n)
                {
                    status=3;
                }
                else{
                    status=-1;
                }
            return status;
    }
    
    public void com()
    {
        if(turn<n*n)
        {
            System.out.println("My turn. Let me think...");
            //random();
            intel();
            if(computer==0)
            {
                matrix[rr][rc]="O";
                backend[rr][rc]=-1;
            }
            else if(computer==1)
            {
                matrix[rr][rc]="X";
                backend[rr][rc]=1;
            }
            turn++;
        }
    }
    void random()
    {
        if(backend[1][1]==0)
        {
            rr=1;
            rc=1;
        }
        else
        {
            rr= randomGenerator.nextInt(n);
            rc = randomGenerator.nextInt(n);
            if(backend[rr][rc]!=0)
            {
                random();
            }
            //System.out.println("row"+Math.abs(rr-row));
            //1System.out.println("col"+Math.abs(rc-column));
            if(!(Math.abs(rr-row)<2) && (Math.abs(rc-column)<2 ) && check<10)
            {
                random();
                check++;
            }
        }
    }
    void intel()
    {
        int mult;
        if(computer==0)
        {
            mult=-1;
        }
        else
        {
            mult=1;
        }
        if(look(mult,n-1)!=1)
        {
            if(look(mult,1-n)!=1)
            {
                random();
            }
        }
    }
    int look(int mult, int hs)
    {
        int halfsum,op;
        //diagonally\
        op=2;
        halfsum=mult*(backend[0][0]+backend[1][1]+backend[2][2]);
        if(halfsum==hs)
        {
            //System.out.println("halfsum="+halfsum+"\twin called with op="+op);
            move(op,0);
            return 1;
        }
        //diagonally/
        op=3;
        halfsum=mult*(backend[0][2]+backend[1][1]+backend[2][0]);
        if(halfsum==hs)
        {
            //System.out.println("halfsum="+halfsum+"\twin called with op="+op);
            move(op,0);
            return 1;
        }
        for(int i=0;i<n;i++)
        {
            //row
            op=0;
            halfsum=mult*(backend[i][0]+backend[i][1]+backend[i][2]);
            if(halfsum==hs)
            {
                //System.out.println("halfsum="+halfsum+"\twin called with op="+op);
                move(op,i);
                return 1;
            }
            
            //column
            op=1;
            halfsum=mult*(backend[0][i]+backend[1][i]+backend[2][i]);
            if(halfsum==hs)
            {
                //System.out.println("halfsum="+halfsum+"\twin called with op="+op);
                move(op,i);
                return 1;
            }
        }
        return 0;
    }
    
    void move(int op, int i)
    {
        switch(op)
        {
            case 0:
                //row
                for(int j=0;j<n;j++)
                {
                    if(backend[i][j]==0)
                    {
                        rr=i;
                        rc=j;
                        return;
                    }
                }
                break;
            case 1:
                //column
                for(int j=0;j<n;j++)
                {
                    if(backend[j][i]==0)
                    {
                        rr=j;
                        rc=i;
                        return;
                    }
                }
                break;
            case 2:
                //diagonally\
                for(int j=0;j<n;j++)
                {
                    if(backend[j][j]==0)
                    {
                        rr=j;
                        rc=j;
                        return;
                    }
                }
                break;
            case 3:
                //diagonally\
                for(int j=0;j<n;j++)
                {
                    if(backend[j][n-1-j]==0)
                    {
                        rr=j;
                        rc=n-1-j;
                        return;
                    }
                }
                break;
        }
    }
}
