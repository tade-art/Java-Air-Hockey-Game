import java.util.GregorianCalendar;

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
        double friction = 0.98;
        double constantSpeed = 12.0;

        /**
         * Objects which are used to display game info
         */
        int p1score = 0;
        int p2score = 0;
        Text player1Text = new Text(String.valueOf(p1score), 50, 50, 350, "RED");
        Text player2Text = new Text(String.valueOf(p2score), 50, 950, 350, "RED");
        Text titleText = new Text("Welcome to Airhockey", 35, 125, 75, "RED");
        Text p1WinText = new Text("Player 1 Wins!",35,175,650,"RED");
        Text p2WinText = new Text("Player 2 Wins!", 35, 575, 650, "RED");
        Text mute = new Text("Press M to mute", 25, 400, 700, "RED");
        Text unmute = new Text("Press M to unmute", 25, 400, 700, "RED");
        
        /**
         * Flags used throughout the code
         */
        boolean muteFlag = false;
        boolean puckSize = false;
        boolean malletSize = false;


        /**
         * Adds the Text objects to the arena
         */
        arena.addText(player1Text); 
        arena.addText(player2Text);
        arena.addText(titleText);
        arena.addText(mute);

        /**
         * Creating the sound objects to be played and the audio player
         */
         SoundPlayer sound = new SoundPlayer(null);

         String drumroll = "drumroll.wav";
         String bounce = "bounce.wav";
         String applause = "applause.wav";
         String hit = "hit.wav";

         sound.changePath("fanfare.wav");
         Thread greetSoundThread = new Thread( () -> sound.play());
         greetSoundThread.start();

        /**
        * While loop to run until the game ends 
        */
        while(gameRunning){
            /**
             * Check statements to see if the end of the game has been reached 
             */    
            if(p1score ==6) {
                    arena.addText(p1WinText);     
                    sound.changePath(drumroll);
                    sound.play();
                    gameRunning=false;
                }
                else if(p2score == 6){
                    arena.addText(p2WinText); 
                    sound.changePath(drumroll);
                    sound.play();
                    gameRunning = false;
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
            if(arena.letterPressed('a')){
                player1.moveMallet(-constantSpeed, 0);
            }
            if(arena.letterPressed('s')){
                player1.moveMallet(0, constantSpeed);
            }
            if(arena.letterPressed('d')){
                player1.moveMallet(constantSpeed, 0);
            }
            
            /**
            * Movement controls for Player 2
            */ 
            if(arena.upPressed()){
                player2.moveMallet(0, -constantSpeed);
            }
            if(arena.leftPressed()){
                player2.moveMallet(-constantSpeed, 0);
            }
            if(arena.downPressed()){
                player2.moveMallet(0, constantSpeed);
            }
            if(arena.rightPressed()){
                player2.moveMallet(constantSpeed, 0);
            }

            /**
             * Code for mute button
             */
            if(arena.letterPressed('m')){
                if(muteFlag){
                    arena.removeText(unmute);
                    arena.addText(mute);
                    
                    muteFlag=false;
                    sound.toggleMute();
                    arena.pause();
                }
                else{                    
                    arena.removeText(mute);
                    arena.addText(unmute);
                    
                    muteFlag=true;
                    sound.toggleMute();
                    arena.pause();
                }
                
                arena.pause();
            }

            /**
             * Code for cheats
             */
            if(arena.letterPressed('u')){
                if(puckSize){
                    puck.setSize(85);
                    puckSize = false;
                    arena.pause();
                }
                
                else{
                    puck.setSize(30);
                    puckSize = true;
                    arena.pause();
                }

                arena.pause();
            }

            if(arena.letterPressed('i')){
                if(malletSize){
                    player1.setSize(20);
                    player2.setSize(20);
                    malletSize = false;
                    arena.pause();
                }

                else{
                    player1.setSize(80);
                    player2.setSize(80);
                    malletSize=true;
                    arena.pause();
                }

                arena.pause();
            }

            /**
            * Branch statements checking for collisions between player 1 and the borders
            */          
            if(player1.returnYPos() - (player1.returnSize() / 2) <= 124){
                player1.moveMallet(0, constantSpeed);
            }
            if(player1.returnYPos() + (player1.returnSize() / 2) >= 532){
                player1.moveMallet(0, -constantSpeed);
            }
            if(player1.returnXPos() - (player1.returnSize() / 2) <=124){
                player1.moveMallet(constantSpeed, 0);
            }
            if(player1.returnXPos() + (player1.returnSize() / 2) >=512){
                player1.moveMallet(-constantSpeed, 0);
            }

            /**
            * Branch statements checking for collisions between player 2 and the borders
            */          
            if(player2.returnYPos() - (player2.returnSize() / 2) <= 124){
                player2.moveMallet(0, constantSpeed);
            }
            if(player2.returnYPos() + (player2.returnSize() / 2) >= 532){
                player2.moveMallet(0, -constantSpeed);
            }
            if(player2.returnXPos() - (player2.returnSize() / 2) <=512){
                player2.moveMallet(constantSpeed, 0);
            }
            if(player2.returnXPos() + (player2.returnSize() / 2) >=900){
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
                
                sound.changePath(hit);
                Thread soundThread = new Thread( () -> sound.play());
                soundThread.start();
                
                puck.setXSpeed(velocity[0]);
                puck.setYSpeed(velocity[1]);
            }

            if(player2.collides(puck)){
                velocity = puck.deflect(player2, puck.getXSpeed(), player2.returnXVelo(), puck.getYSpeed(), player2.returnYVelo());
                
                sound.changePath(hit);
                Thread soundThread = new Thread( () -> sound.play());
                soundThread.start();
                
                puck.setXSpeed(velocity[0]);
                puck.setYSpeed(velocity[1]);
            }

            /**
            * Branch statements checking for collisions between puck and walls
            */
            if(puck.getYPosition() - (puck.getSize()/2) <= 130 || puck.getYPosition() + (puck.getSize()/2) >= 535){
                
                sound.changePath(bounce);
                Thread soundThread = new Thread( () -> sound.play());
                soundThread.start();

                velocity[1] = velocity[1] * -1;
                puck.setYSpeed(velocity[1]);
            }

            if(puck.getXPosition() -(puck.getSize()/2) <= 130 || puck.getXPosition() + (puck.getSize()/2) >= 906){
                
                sound.changePath(bounce);
                Thread soundThread = new Thread( () -> sound.play());
                soundThread.start();
                
                velocity[0] = velocity[0] * -1;
                puck.setXSpeed(velocity[0]);
            }

            /**
            * Branch statements to check for collisions between the Puck and the Goal, then do subsequent methods
            */ 
            if((puck.getYPosition() - ((puck.getSize()/2) + 15) >= 280 && puck.getYPosition() - ((puck.getSize()/2) + 15) <= 370) || (puck.getYPosition() + ((puck.getSize()/2) + 15) >= 280 && puck.getYPosition() + ((puck.getSize()/2) + 15) <=370)){                
                if(puck.getXPosition() - ((puck.getSize()/2) + 15) <= 115){
                    velocity[0] = 0;
                    velocity[1] = 0;
                    
                    reset(AHT, puck, player1, player2, -1);
                    
                    p2score++;
                    player2Text.setText(String.valueOf(p2score));

                    sound.changePath(applause);
                    Thread goalThread = new Thread( () -> sound.play());
                    goalThread.start();
                }

                if(puck.getXPosition() + ((puck.getSize()/2) + 15) >= 900){
                    velocity[0] = 0;
                    velocity[1] = 0;
                    
                    reset(AHT, puck, player1, player2, 1);
                    
                    p1score++;
                    player1Text.setText(String.valueOf(p1score));

                    sound.changePath(applause);
                    Thread goalThread = new Thread( () -> sound.play());
                    goalThread.start();
                }
            }
            
            /**
            * Branch statements checking for collisions between player 1 and the borders
            */          
            if(player1.returnYPos() - (player1.returnSize()/2) <= 124){
                player1.moveMallet(0, constantSpeed);
            }
            if(player1.returnYPos() + (player1.returnSize()/2) >= 532){
                player1.moveMallet(0, -constantSpeed);
            }
            if(player1.returnXPos() - (player1.returnSize()/2) <=124){
                player1.moveMallet(constantSpeed, 0);
            }
            if(player1.returnXPos() + (player1.returnSize()/2) >=512){
                player1.moveMallet(-constantSpeed, 0);
            }

            /**
            * Branch statements checking for collisions between player 2 and the borders
            */          
            if(player2.returnYPos() - (player1.returnSize()/2) <= 124){
                player2.moveMallet(0, constantSpeed);
            }
            if(player2.returnYPos() + (player1.returnSize()/2) >= 532){
                player2.moveMallet(0, -constantSpeed);
            }
            if(player2.returnXPos() - (player1.returnSize()/2) <=512){
                player2.moveMallet(constantSpeed, 0);
            }
            if(player2.returnXPos() + (player1.returnSize()/2) >=900){
                player2.moveMallet(-constantSpeed, 0);
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