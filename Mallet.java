public class Mallet{
    private double xPos;
    private double yPos;
    private double xVelo = 0;
    private double yVelo = 0;

    private Ball mallet = new Ball(0, 0, 70, "GREY", 1);         //Creating the Mallet
    
    //Constructor for the Mallet
    public Mallet (double x, double y){
        mallet.setXPosition(x);
        mallet.setYPosition(y);
    }

    //Function to add it to the Arena
    public void addToArena(GameArena a){
        a.addBall(mallet);
    }

    /* GETTER METHODS */
    public double returnXVelo(){
        return xVelo;
    }
    
    public double returnYVelo(){
        return yVelo;
    }

    public double returnXPos(){
        return xPos;
    }
    
    public double returnYPos(){
        return yPos;
    }

    /* SETTER METHODS */
    public void setXVelo(double x){
        xVelo = x;
    }

    public void setYVelo(double y){
        yVelo = y;
    }

    public void setXPos(double x){
        xPos = x;
    }

    public void setYPos(double y){
        yPos = y;
    }
    
    /* METHODS TO MOVE THE MALLET */
    public void moveYPos(double y){
        yPos+= y;
    }
   
    public void moveXPos(double x){
        xPos+= x;
    }

    public void moveMallet(double xVelo , double yVelo){
        mallet.move(xVelo, yVelo);
    }

    /* METHOD TO SET BOUNDARY */
    public void setBoundary(Line top, Line left, Line right , Line bottom){
        boolean flag = false;
        if((mallet.getYPosition()- 35) <= 118){
            flag = true;
            System.out.println("in 1");
            while(flag){
                mallet.move(0, -10);
                System.out.println("in 2");
                if(mallet.getYPosition() - 35 <=top.getYStart()){flag=false;}
                }
            }
        
        else{System.out.println("Nah");}
    }
}
 
