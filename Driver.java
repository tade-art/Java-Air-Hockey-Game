import java.awt.event.*;

public class Driver{
    public static void main(String[] args) {
        GameArena arena = new GameArena(1024, 768);         //Creating Arena
        Table AHT = new Table(128, 128);            //Creating Table
        Mallet player1 = new Mallet(AHT.returnLeftSideXPos() + 192, AHT.returnLeftSideYPos()+200);      //Creating left Mallet + Positioning them
        Mallet player2 = new Mallet(AHT.returnRightSideXPos() + 192, AHT.returnRightSideYPos()-180);      //Creating Right Mallet + Positioning them
        Puck puck = new Puck(AHT.returnRightSideXPos(), AHT.returnLeftSideYPos()+200);        //Creating + Positioning Puck

        //Adding everything to the Arena
        AHT.addToArena(arena);       //Added Table
        player1.addToArena(arena);              //Added Player 1 (left side)
        player2.addToArena(arena);              //Added Player 2 (right side)
        puck.addToArena(arena);                 //Added puck to table

        boolean gameRunning = true;
        
        //While loop to run until the game ends
        while(gameRunning){     
            arena.pause();      //Game is paused for animation purposes
            //Movement controls for PLAYER 1
            if(arena.letterPressed('w') == true){
                player1.moveMallet(0, -10);
            }
            if(arena.letterPressed('a') == true){
                player1.moveMallet(-10, 0);
            }
            if(arena.letterPressed('s') == true){
                player1.moveMallet(0, 10);
            }
            if(arena.letterPressed('d') == true){
                player1.moveMallet(10, 0);
            }
            
            //Movement controls for PLAYER 2
            if(arena.letterPressed('i') == true){
                player2.moveMallet(0, -10);
            }
            if(arena.letterPressed('j') == true){
                player2.moveMallet(-10, 0);
            }
            if(arena.letterPressed('k') == true){
                player2.moveMallet(0, 10);
            }
            if(arena.letterPressed('l') == true){
                player2.moveMallet(10, 0);
            }

            //Checking for collisions for PLAYER 1          
            if(player1.returnYPos() - 30 <= 124){
                player1.moveMallet(0, 10);
            }
            if(player1.returnYPos() + 30 >= 532){
                player1.moveMallet(0, -10);
            }
            if(player1.returnXPos() -30 <=124){
                player1.moveMallet(10, 0);
            }
            if(player1.returnXPos() +30 >=512){
                player1.moveMallet(-10, 0);
            }


            //Checking for collisions for PLAYER 2          
            if(player2.returnYPos() - 30 <= 124){
                player2.moveMallet(0, 10);
            }
            if(player2.returnYPos() + 30 >= 532){
                player2.moveMallet(0, -10);
            }
            if(player2.returnXPos() -30 <=512){
                player2.moveMallet(10, 0);
            }
            if(player2.returnXPos() +30 >=883){
                player2.moveMallet(-10, 0);
            }
            
        }
            
    }

}