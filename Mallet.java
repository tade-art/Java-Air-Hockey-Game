/**
 * This class is used to create the mallets for the air hockey game
 * @author Tadas Ivanauskas
 */
public class Mallet{
    private double xPos = 0 ;
    private double yPos= 0;
    private double xVelo = 0;
    private double yVelo = 0;
    private double size = 0;

    private Ball mallet = new Ball(0, 0, 80, "GREY", 1);         //Creating the Mallet
    
    /**
     * Constructor Method
     * @param x the X Position of the Mallet
     * @param y the Y Position of the Mallet
     */
    public Mallet (double x, double y){
        mallet.setXPosition(x);
        mallet.setYPosition(y);
        xPos = x;
        yPos = y;
    }

    /**
     * Method to add the mallet to the GameArena
     */
    public void addToArena(GameArena a){
        a.addBall(mallet);
    }

    /** 
     * Method to return the X Velocity of the Mallet
    */
    public double returnXVelo(){
        return xVelo;
    
    }
    
    /** 
     * Method to return the Y Velocity of the Mallet
    */
    public double returnYVelo(){
        return yVelo;
    }

    /** 
     * Method to return the X Position of the Mallet
    */
    public double returnXPos(){
        return xPos;
    }
    
    /** 
     * Method to return the Y Position of the Mallet
    */
    public double returnYPos(){
        return yPos;
    }

    /** 
     * Method to set the X Velocity of the Mallet
    */
    public void setXVelo(double x){
        xVelo = x;
    }

    /** 
     * Method to set the Y Velocity of the Mallet
    */
    public void setYVelo(double y){
        yVelo = y;
    }

    /**
     * Method to set the size of the mallet
     */
    public void setSize (double ns){
        this.mallet.setSize(ns);
    }

    /** 
     * Method to set the position of the Mallet
     * @param x the new X Position of the mallet
     * @param y the new Y Position of the mallet
    */
    public void setPos(double x , double y){
        this.mallet.setXPosition(x);
        this.mallet.setYPosition(y);

        xPos = x;
        yPos = y;
    }
    
    /**
     * Method to move the mallet
     * @param xVelo the amount of pixels needed to move the mallet in the X plane
     * @param yVelo the amount of pixels needed to move the mallet in the Y plane
     */
    public void moveMallet(double xVelo , double yVelo){
        this.xPos += xVelo;
        this.yPos += yVelo;
        this.xVelo = xVelo;
        this.yVelo = yVelo;
        mallet.move(xVelo, yVelo);
    }
    
    /**
     * Boolean method to check and see if a mallet collides with a Puck
     * @param p the puck which is being checked to see if it collided with a mallet
     * @return Returns true if the mallet is overlapping the puck
     */
    public boolean collides(Puck p)
	{
		double dx = p.getXPosition() - xPos;
		double dy = p.getYPosition() - yPos;
		double distance = Math.sqrt(dx*dx+dy*dy);

		return distance < mallet.getSize()/2 + p.getSize()/2;
	}

}
 
