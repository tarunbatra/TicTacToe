
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
    String[][] matrix = new String[3][3];
    Random randomGenerator = new Random();
    int[][] backend = new int [3][3];
    int player,computer,status=-1;
    tictactoe()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
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
        }
    }
    public void human() throws IOException
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the row");
        int row=scan.nextInt();
        System.out.println("Enter the column");
        int column=scan.nextInt();
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
    }
    public void print()
    {
        System.out.println("-------------");
        System.out.printf("| %s | %s | %s |\n",matrix[0][0],matrix[0][1],matrix[0][2]);
        System.out.println("-------------");
        System.out.printf("| %s | %s | %s |\n",matrix[1][0],matrix[1][1],matrix[1][2]);
        System.out.println("-------------");
        System.out.printf("| %s | %s | %s |\n",matrix[2][0],matrix[2][1],matrix[2][2]);
        System.out.println("-------------");
        
    }
    public int check()
    {
        int sum=0;
            //row
            for(int i=0;i<3;i++)
            {
                sum=backend[i][0]+backend[i][1]+backend[i][2];
                if(sum==-3)
                {
                    status=0;
                    return status;
                }
                else if(sum==3)
                {
                    status=1;
                    return status;
                }
            }
            //column
            for(int i=0;i<3;i++)
            {
                sum=backend[0][i]+backend[1][i]+backend[2][i];
                if(sum==-3)
                {
                    status=0;
                    return status;
                }
                else if(sum==3)
                {
                    status=1;
                    return status;
                }
            }
            //diagonally\
                sum=backend[0][0]+backend[1][1]+backend[2][2];
                if(sum==-3)
                {
                    status=0;
                    return status;
                }
                else if(sum==3)
                {
                    status=1;
                    return status;
                }
            //diagonally/
                sum=backend[0][2]+backend[1][1]+backend[2][0];
                if(sum==-3)
                {
                    status=0;
                    return status;
                }
                else if(sum==3)
                {
                    status=1;
                    return status;
                }
            return status;
    }
    
    public void com()
    {
        System.out.println("My turn. Let me think...");
        int rr= randomGenerator.nextInt(3);
        int rc = randomGenerator.nextInt(3);
        while(backend[rr][rc]!=0)
        {
            rr= randomGenerator.nextInt(3);
            rc = randomGenerator.nextInt(3); 
        }
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
    }
        
}
