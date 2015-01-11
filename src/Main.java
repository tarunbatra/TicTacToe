import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
public class Main {
    public static void main(String args[]) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("*** Tic Tac Toe ***\n\nChoose X or O\n");
        String choice =br.readLine();
        tictactoe game=new tictactoe();
        game.player(choice);
        game.print();
        System.out.println("Your turn first.");
        int status=-1;
        while(status==-1)
        {
            game.human();
            game.print();
            game.com();
            game.print();
            status=game.check();
        }
        if(status==0)
        {
            if(game.player==0)
            {
                System.out.println("Congratulations! You won.");
            }
            else
            {
                System.out.println("Pity you! I won.");
            }
        }
        else if(status==1)
        {
            if(game.player==1)
            {
                System.out.println("Congratulations! You won.");
            }
            else
            {
                System.out.println("Pity you! I won.");
            }
        }
    }
}
