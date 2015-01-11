
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
    int player,computer,status=-1,rr=0,rc=0,n=3,turn=0;
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
    public void human() throws IOException
    {
       if(turn<n*n)
       {
            System.out.println("Enter the row");
            int row=scan.nextInt();
            System.out.println("Enter the column");
            int column=scan.nextInt();
            while(backend[row][column]!=0 || row>n || column>n)
            {
                System.out.println("Invalid selection! Try again.");
                System.out.println("Enter the row");
                row=scan.nextInt();
                System.out.println("Enter the column");
                column=scan.nextInt();
            }
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
        if(turn<=n*n)
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
        int sum=0;
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
                status=-1;
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
        rr= randomGenerator.nextInt(n);
        rc = randomGenerator.nextInt(n);
        while(backend[rr][rc]!=0)
        {
            rr= randomGenerator.nextInt(n);
            rc = randomGenerator.nextInt(n); 
        }
        
    }
    void intel()
    {
        int mult,halfsum,op;
        if(computer==0)
        {
            mult=1;
        }
        else
        {
            mult=-1;
        }
        System.out.println("mult="+mult);
        //row
        op=0;
        for(int i=0;i<n;i++)
        {
            halfsum=mult*(backend[i][0]+backend[i][1]+backend[i][2]);
            if(halfsum==n-1)
            {
                System.out.println("halfsum="+halfsum+"\twin called with op="+op);
                move(op,i);
                return;
            }
            else if(halfsum==1-n)
            {
                System.out.println("halfsum="+halfsum+"\tblock called with op="+op);
                move(op,i);
                return;
            }
        }
        //column
        op=1;
        for(int i=0;i<n;i++)
        {
            halfsum=mult*(backend[0][i]+backend[1][i]+backend[2][i]);
            if(halfsum==n-1)
            {
                System.out.println("halfsum="+halfsum+"\twin called with op="+op);
                move(op,i);
                return;
            }
            else if(halfsum==1-n)
            {
                System.out.println("halfsum="+halfsum+"\tblock called with op="+op);
                move(op,i);
                return;
            }
        }
        //diagonally\
        op=2;
        halfsum=mult*(backend[0][0]+backend[1][1]+backend[2][2]);
        if(halfsum==n-1)
        {
            System.out.println("halfsum="+halfsum+"\twin called with op="+op);
            move(op,0);
            return;
        }
        else if(halfsum==1-n)
        {
            System.out.println("halfsum="+halfsum+"\tblock called with op="+op);
            move(op,0);
            return;
        }
        //diagonally/
        op=3;
        halfsum=mult*(backend[0][2]+backend[1][1]+backend[2][0]);
        if(halfsum==n-1)
        {
            System.out.println("halfsum="+halfsum+"\twin called with op="+op);
            move(op,0);
            return;
        }
        else if(halfsum==1-n)
        {
            System.out.println("halfsum="+halfsum+"\tblock called with op="+op);
            move(op,0);
            return;
        }
        random();
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
