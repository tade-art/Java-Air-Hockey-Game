public class Driver{
    public static void main(String[] args) {
        GameArena arena = new GameArena(1024, 768);         //Creating Arena
        Table AHT = new Table(128, 128);                       //Creating Table  
        Mallet player1 = new Mallet(AHT.returnLeftSideXPos() + 192, AHT.returnLeftSideYPos()+200);          //Creating left Mallet + Positioning them
        Mallet player2 = new Mallet(AHT.returnRightSideXPos() + 192, AHT.returnRightSideYPos()-180);        //Creating Right Mallet + Positioning them
        Puck puck = new Puck(AHT.returnRightSideXPos(), AHT.returnLeftSideYPos()+200);                      //Creating + Positioning Puck

        //Added the objects for manipulating the pucks and mallets
        double[] velocity = {0,0};
        double friction = 0.01;
        double constantSpeed = 12.0;

        //Added the text objects
        int p1score = 0;
        int p2score = 0;
        Text player1Text = new Text(String.valueOf(p1score), 50, 275, 650, "RED");
        Text player2Text = new Text(String.valueOf(p2score), 50, 700, 650, "RED");
        Text titleText = new Text("Welcome to Airhockey", 50, 200, 75, "RED");


        //Adding everything to the Arena
        AHT.addToArena(arena);                  //Added Table
        player1.addToArena(arena);              //Added Player 1 (left side)
        player2.addToArena(arena);              //Added Player 2 (right side)
        puck.addToArena(arena);                 //Added puck to table
        arena.addText(player1Text);             //Added text to represent player 1 score
        arena.addText(player2Text);             //Added text to represent player 2 score
        arena.addText(titleText);               //Added the title text

        boolean gameRunning = true;
        
        //While loop to run until the game ends
        while(gameRunning){     
            
            //Checking if the score limit has been reached
            // if(p1score ==6 || p2score ==6){
            //     gameRunning=false;
            // }
            
            arena.pause();      //Game is paused for animation purposes

            if(arena.letterPressed('w')){
                player1.moveMallet(0, -constantSpeed);
            }
            if(arena.letterPressed('a') == true){
                player1.moveMallet(-constantSpeed, 0);
            }
            if(arena.letterPressed('s') == true){
                player1.moveMallet(0, constantSpeed);
            }
            if(arena.letterPressed('d') == true){
                player1.moveMallet(constantSpeed, 0);
            }
            
            if(arena.letterPressed('i') == true){
                player2.moveMallet(0, -constantSpeed);
            }
            if(arena.letterPressed('j') == true){
                player2.moveMallet(-constantSpeed, 0);
            }
            if(arena.letterPressed('k') == true){
                player2.moveMallet(0, constantSpeed);
            }
            if(arena.letterPressed('l') == true){
                player2.moveMallet(constantSpeed, 0);
            }

                      //Checking for collisions for PLAYER 1          
                    if(player1.returnYPos() - 40 <= 124){
                        player1.moveMallet(0, constantSpeed);
                    }
                    if(player1.returnYPos() + 40 >= 532){
                        player1.moveMallet(0, -constantSpeed);
                    }
                    if(player1.returnXPos() -40 <=124){
                        player1.moveMallet(constantSpeed, 0);
                    }
                    if(player1.returnXPos() +40 >=512){
                        player1.moveMallet(-constantSpeed, 0);
                    }
        
                    //Checking for collisions for PLAYER 2          
                    if(player2.returnYPos() - 40 <= 124){
                        player2.moveMallet(0, constantSpeed);
                    }
                    if(player2.returnYPos() + 40 >= 532){
                        player2.moveMallet(0, -constantSpeed);
                    }
                    if(player2.returnXPos() -40 <=512){
                        player2.moveMallet(constantSpeed, 0);
                    }
                    if(player2.returnXPos() +40 >=900){
                        player2.moveMallet(-constantSpeed, 0);
                    }
                    
            //Moving Puck (Setting velocity for the puck) 
            //FIX FRICTRION IMMEDITAELY
            /* |
             * |
             * |
             * \/
             */
             if( (puck.getXSpeed() > 0 && puck.getYSpeed() > 0) || (puck.getXSpeed() < 0 && puck.getYSpeed() < 0) ){            
                puck.movePuck(velocity[0] * (1-friction), velocity[1] * (1-friction));
            }    
            else{
                puck.movePuck(velocity[0], velocity[1]);
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

            //Checking for collisions between puck and walls
            if(puck.getYPosition() - 25 <= 130 || puck.getYPosition() + 25 >= 535){
                velocity[1] = velocity[1] * -1;
                puck.setYSpeed(velocity[1]);
            }

            if(puck.getXPosition() - 25 <= 130 || puck.getXPosition() + 25 >= 906){
                velocity[0] = velocity[0] * -1;
                puck.setXSpeed(velocity[0]);
            }

            //Checking for collisions between puck and goals
            if((puck.getYPosition()-40 >= 253 && puck.getYPosition()-40 <= 403) || (puck.getYPosition()+40 >= 253 && puck.getYPosition()-40 <=403)){                
                if(puck.getXPosition() - 40 <= 128){
                    velocity[0] = 0;
                    velocity[1] = 0;
                    resetBall(AHT, puck, player1, player2,true);
                    
                    p1score++;
                    player1Text.setText(String.valueOf(p1score));
                }

                if(puck.getXPosition() + 40 >= 883){
                    velocity[0] = 0;
                    velocity[1] = 0;
                    resetBall(AHT, puck, player1, player2,false);
                    
                    p2score++;
                    player2Text.setText(String.valueOf(p2score));
                }
            }
        }
    }
        //Function to reset the puck once goal scored
        public static void resetBall(Table AHT,Puck puck, Mallet p1, Mallet p2, boolean flag){
            //If player 1 scores
            if(flag){
                puck.setXPosition(AHT.returnRightSideXPos() - 50);
                puck.setYPosition(AHT.returnLeftSideXPos() + 200);
                p1.setPos(AHT.returnLeftSideXPos() + 192, AHT.returnLeftSideYPos()+200);  
                p2.setPos(AHT.returnRightSideXPos() + 192, AHT.returnRightSideYPos()-180);
                puck.setXSpeed(0);
                puck.setYPosition(0);    
            }
            
            //If player 2 scores
            else{
                puck.setXPosition(AHT.returnRightSideXPos() - 50);
                puck.setYPosition(AHT.returnLeftSideXPos() + 200);
                p1.setPos(AHT.returnLeftSideXPos() + 192, AHT.returnLeftSideYPos()+200);  
                p2.setPos(AHT.returnRightSideXPos() + 192, AHT.returnRightSideYPos()-180);
                puck.setXSpeed(0);
                puck.setYPosition(0);    
            }                         
                                     
            
        }
}