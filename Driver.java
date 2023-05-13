import java.awt.event.*;

public class Driver{
    public static void main(String[] args) {
        GameArena arena = new GameArena(1024, 768);         //Creating Arena   (OLD W=  1024, OLD H = 768)
        Table AHT = new Table(128, 128);            //Creating Table       (old x = 128)
        Mallet player1 = new Mallet(AHT.returnLeftSideXPos() + 192, AHT.returnLeftSideYPos()+200);      //Creating left Mallet + Positioning them
        Mallet player2 = new Mallet(AHT.returnRightSideXPos() + 192, AHT.returnRightSideYPos()-180);      //Creating Right Mallet + Positioning them
        Puck puck = new Puck(AHT.returnRightSideXPos(), AHT.returnLeftSideYPos()+200);        //Creating + Positioning Puck

        double[] velocity = {0,0};
        double friction = 0.75;

        //Adding everything to the Arena
        AHT.addToArena(arena);       //Added Table
        player1.addToArena(arena);              //Added Player 1 (left side)
        player2.addToArena(arena);              //Added Player 2 (right side)
        puck.addToArena(arena);                 //Added puck to table

        boolean gameRunning = true;
        
        //While loop to run until the game ends
        while(gameRunning){     
            arena.pause();      //Game is paused for animation purposes

            if(arena.letterPressed('w')){
                player1.moveMallet(0, -15);
            }
            if(arena.letterPressed('a') == true){
                player1.moveMallet(-15, 0);
            }
            if(arena.letterPressed('s') == true){
                player1.moveMallet(0, 15);
            }
            if(arena.letterPressed('d') == true){
                player1.moveMallet(15, 0);
            }
            
            if(arena.letterPressed('i') == true){
                player2.moveMallet(0, -15);
            }
            if(arena.letterPressed('j') == true){
                player2.moveMallet(-15, 0);
            }
            if(arena.letterPressed('k') == true){
                player2.moveMallet(0, 15);
            }
            if(arena.letterPressed('l') == true){
                player2.moveMallet(15, 0);
            }

            //Checking for collisions for PLAYER 1          
            if(player1.returnYPos() - 30 <= 124){
                player1.moveMallet(0, 15);
            }
            if(player1.returnYPos() + 30 >= 532){
                player1.moveMallet(0, -15);
            }
            if(player1.returnXPos() -30 <=124){
                player1.moveMallet(15, 0);
            }
            if(player1.returnXPos() +30 >=512){
                player1.moveMallet(-15, 0);
            }


            //Checking for collisions for PLAYER 2          
            if(player2.returnYPos() - 30 <= 124){
                player2.moveMallet(0, 15);
            }
            if(player2.returnYPos() + 30 >= 532){
                player2.moveMallet(0, -15);
            }
            if(player2.returnXPos() -30 <=512){
                player2.moveMallet(15, 0);
            }
            if(player2.returnXPos() +30 >=883){
                player2.moveMallet(-15, 0);
            }

            //Moving Puck (Setting velocity for the puck)
            if(puck.getXSpeed() > 0 && puck.getYSpeed() > 0){
                puck.movePuck(puck.getXSpeed() - (velocity[0]- (1 *friction)) , puck.getYSpeed() - (velocity[1]- (1 *friction))); //Equation is based off SUVAT
            }
            else{
                puck.movePuck(puck.getXSpeed(), puck.getYSpeed());
            }
            
            //Checking for collisions between mallet and puck
            if(player1.collides(puck)){
                velocity = puck.deflect(player1, puck.getXSpeed(), player1.returnXVelo(), puck.getYSpeed(), player1.returnYVelo());
                puck.setXSpeed(velocity[0]);
                puck.setYSpeed(velocity[1]);
            }

            if(player2.collides(puck)){
                velocity = puck.deflect(player2, puck.getXSpeed(), player2.returnXVelo(), puck.getYSpeed(), player2.returnYVelo());
                puck.setXSpeed(velocity[0]);
                puck.setYSpeed(velocity[1]);
            }  
            
        }
            
    }
}