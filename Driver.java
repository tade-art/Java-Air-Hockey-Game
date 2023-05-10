import java.awt.event.*;

public class Driver{
    public static void main(String[] args) {
        GameArena arena = new GameArena(1024, 768);         //Creating Arena
        Table airHockeyTable = new Table(128, 128);            //Creating Table
        Mallet player1 = new Mallet(airHockeyTable.returnLeftSideXPos() + 192, airHockeyTable.returnLeftSideYPos()+200);      //Creating left Mallet + Positioning them
        Mallet player2 = new Mallet(airHockeyTable.returnRightSideXPos() + 192, airHockeyTable.returnRightSideYPos()-180);      //Creating Right Mallet + Positioning them
        Puck puck = new Puck(airHockeyTable.returnRightSideXPos(), airHockeyTable.returnLeftSideYPos()+200);        //Creating + Positioning Puck

        //Adding everything to the Arena
        airHockeyTable.addToArena(arena);       //Added Table
        player1.addToArena(arena);              //Added Player 1 (left side)
        player2.addToArena(arena);              //Added Player 2 (right side)
        puck.addToArena(arena);                 //Added puck to table

        boolean gameRunning = true;
        
        while(gameRunning){
            
            arena.pause();
            
            if(arena.letterPressed('w') == true){
                System.out.println("Pressed w");
            }
            if(arena.letterPressed('a') == true){
                System.out.println("Pressed a");
            }
            if(arena.letterPressed('s') == true){
                System.out.println("Pressed s");
            }
            if(arena.letterPressed('d') == true){
                System.out.println("Pressed d");
            }
            
            if(arena.letterPressed('i') == true){
                System.out.println("Pressed i");
            }
            if(arena.letterPressed('j') == true){
                System.out.println("Pressed j");
            }
            if(arena.letterPressed('k') == true){
                System.out.println("Pressed k");
            }
            if(arena.letterPressed('l') == true){
                System.out.println("Pressed l");
            }
        }
            
    }

}