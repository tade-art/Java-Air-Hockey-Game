public class Mallet{
    private double xPos = 0 ;
    private double yPos= 0;
    private double xVelo = 0;
    private double yVelo = 0;

    private Ball mallet = new Ball(0, 0, 70, "GREY", 1);         //Creating the Mallet
    
    //Constructor for the Mallet
    public Mallet (double x, double y){
        mallet.setXPosition(x);
        mallet.setYPosition(y);
        xPos = x;
        yPos = y;
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

    public void setPos(double x , double y){
        xPos = x;
        yPos = y;
    }
    
    /* METHOD TO MOVE THE MALLET */
    public void moveMallet(double xVelo , double yVelo){
        xPos += xVelo;
        yPos += yVelo;
        mallet.move(xVelo, yVelo);
    }

}
 
