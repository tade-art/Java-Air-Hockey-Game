
/**
 * This class manipulates all the other java files in the folder to create an Air Hockey game.
 * @author Tadas Ivanauskas
 */

public class Driver{
    public static void main(String[] args) {
        
        /**
         * Initialized all the objects used in the runGame method
         */
        GameArena arena = new GameArena(1024, 768); 
        Table AHT = new Table(128, 128);     
        Mallet player1 = new Mallet(AHT.returnLeftSideXPos() + 192, AHT.returnLeftSideYPos()+200);          
        Mallet player2 = new Mallet(AHT.returnRightSideXPos() + 192, AHT.returnRightSideYPos()-180);
        Puck puck = new Puck(AHT.returnRightSideXPos(), AHT.returnLeftSideYPos()+200);

        /**
         * Internal flag used for checking if game is running
         */
        boolean gameRunning = true;

        /**
         * Internal construction method
         */
        AHT.addToArena(arena);                 
        player1.addToArena(arena);              
        player2.addToArena(arena); 
        puck.addToArena(arena); 
        
        /**
         * While loop to run the game forever
         */
        while(true){
            runGame(arena, AHT, player1, player2, puck, gameRunning);
        }

    }

    /**
     * Method used to run the game
     * @param arena The arena which is being added too
     * @param AHT   The Table which the game takes place on
     * @param player1   The Mallet belonging to player 1
     * @param player2   The Mallet belonging to player 2
     * @param puck  The puck which is being used 
     * @param gameRunning   The flag which is being used to check if the game is running or not
     */
    public static void runGame(GameArena arena, Table AHT, Mallet player1, Mallet player2, Puck puck,boolean gameRunning){
        /**
         * Variables which are used within the phyics engine
         */
        double[] velocity = {0,0};
        double friction = 0.99;
        double constantSpeed = 12.0;

        /**
         * Text Objects which are used to display game info
         */
        int p1score = 5;
        int p2score = 5;
        Text player1Text = new Text(String.valueOf(p1score), 50, 275, 650, "RED");
        Text player2Text = new Text(String.valueOf(p2score), 50, 700, 650, "RED");
        Text titleText = new Text("Welcome to Airhockey", 50, 200, 75, "RED");
        Text p1WinText = new Text("Player 1 Wins!",35,175,700,"RED");
        Text p2WinText = new Text("Player 2 Wins!", 35, 575, 700, "RED");

        /**
         * Adds the Text objects to the arena
         */
        arena.addText(player1Text); 
        arena.addText(player2Text);
        arena.addText(titleText);

        /**
        * While loop to run until the game ends 
        */
        while(gameRunning){
                        
                if(p1score ==6) {
                    gameRunning=false;
                    arena.addText(p1WinText);
                }
                else if(p2score == 6){
                    gameRunning = false;
                    arena.addText(p2WinText); 
                }
            
            
            /**
             * Internal method used for animation purposes - called with GameArena
             */
            arena.pause();

            /**
            * Movement controls for Player 1
            */   
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
            
            /**
            * Movement controls for Player 1
            */ 
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

                    /**
                    * Branch statements checking for collisions between player 1 and the borders
                    */          
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
        
                    /**
                    * Branch statements checking for collisions between player 2 and the borders
                    */          
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
                    
            /**
            * Internal method used to move the puck via velocity with regards to friction
            */ 
            velocity[0] *= friction;
            velocity[1] *= friction;
            puck.movePuck(velocity[0], velocity[1]);
            
            /**
            * Branch statements checking for collisions between Mallet and Puck 
            */
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

            /**
            * Branch statements checking for collisions between puck and walls
            */
            if(puck.getYPosition() - 25 <= 130 || puck.getYPosition() + 25 >= 535){
                velocity[1] = velocity[1] * -1;
                puck.setYSpeed(velocity[1]);
            }

            if(puck.getXPosition() - 25 <= 130 || puck.getXPosition() + 25 >= 906){
                velocity[0] = velocity[0] * -1;
                puck.setXSpeed(velocity[0]);
            }

            /**
            * Branch statements to check for collisions between the Puck and the Goal, then do subsequent methods
            */ 
            if((puck.getYPosition()-40 >= 280 && puck.getYPosition()-40 <= 370) || (puck.getYPosition()+40 >= 280 && puck.getYPosition()+40 <=370)){                
                if(puck.getXPosition() - 40 <= 115){
                    velocity[0] = 0;
                    velocity[1] = 0;
                    
                    reset(AHT, puck, player1, player2, -1);
                    
                    p2score++;
                    player2Text.setText(String.valueOf(p2score));
                }

                if(puck.getXPosition() + 40 >= 900){
                    velocity[0] = 0;
                    velocity[1] = 0;
                    
                    reset(AHT, puck, player1, player2, 1);
                    
                    p1score++;
                    player1Text.setText(String.valueOf(p1score));
                }
            }

        /**
        * Internal method used to reset the game once score limit has been reached  
        */
        while(gameRunning == false){
            arena.pause();
            if(arena.letterPressed('r')){
                p1score = 0;
                p2score = 0;
                reset(AHT, puck, player1, player2, 0);
                arena.removeText(p2WinText);
                arena.removeText(p1WinText);
                player1Text.setText(String.valueOf(p1score));
                player2Text.setText(String.valueOf(p2score));
                gameRunning = true;
            }
        }
        }
    }
 
    /**
	 * Resets the GameArena and Table.
	 *
	 * @param Table the table which is being edited.
	 * @param Puck the puck which is being reset.
     * @param Mallet the first mallet which is being reset (Player 1)
     * @param Mallet the second mallet which is being reset (Player 2)
     * @param Check the flag used to check which condition used to execute (0 is used to reset game, 1 / -1 used to reset each side of the board)
	 */
    
     public static void reset (Table AHT, Puck puck, Mallet p1 , Mallet p2 , int check){
        /**
        * Reset the player's positions, Puck position and Puck speed 
        */
        p1.setPos(AHT.returnLeftSideXPos() + 192, AHT.returnLeftSideYPos()+200);  
        p2.setPos(AHT.returnRightSideXPos() + 192, AHT.returnRightSideYPos()-180);
        puck.setYPosition(AHT.returnLeftSideXPos() + 200);
        puck.setXSpeed(0);
        puck.setYSpeed(0);
        
        /**
        * If player 1 scored, then reset and spawn ball in player's 2 favour 
        */
        if(check > 0){
            puck.setXPosition(AHT.returnRightSideXPos() + 35);
        }
        
        /**
        * If player 2 scored, then reset and spawn ball in player 1's favour
        */
        else if(check < 0){
            puck.setXPosition(AHT.returnRightSideXPos() - 35);
        }

        /**
        *If flag equals 0, then position the puck in the middle (used for resetting game)
        */
        else if (check ==0){
            puck.setXPosition(AHT.returnRightSideXPos());
        }

    }
}