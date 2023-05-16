/**
 * This is the file which contains the pucks used in the Driver class
 * @author Tadas Ivanauskas
 */
public class Puck{

    private Ball puck = new Ball(0, 0, 50, "GREY",2);     //Create the Puck object
    private double xPos = 0;
    private double yPos = 0;
    private double xSpeed = 0;
    private double ySpeed = 0;
    private double size = 30;

    /**
     * Constructor Method
     * @param xPos the X position of the Puck
     * @param yPos the Y position of the Puck
     */
    public Puck(double xPos, double yPos){
        this.puck.setXPosition(xPos);
        this.puck.setYPosition(yPos);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Method to add a puck to the GameArena
     * @param a The GameArena which is being added too
     */
    public void addToArena(GameArena a){
        a.addBall(puck);
    }

    /**
     * Method to return the X position of the puck
     * @return Returns the x Position
     */
    public double getXPosition(){
        return xPos;
    }  

    /**
     * Method to return the Y position of the puck
     * @return Returns the y Position
     */
    public double getYPosition(){
        return yPos;
    }

    /**
     * Method to return the size of the puck
     * @return Returns the size
     */
    public double getSize(){
        return size;
    }

    /**
     * Method to return the X Speed of the puck
     * @return Returns the X Speed
     */
    public double getXSpeed(){
        return this.xSpeed;
    }

    /**
     * Method to return the Y Speed of the puck
     * @return Returns the Y Speed
     */
    public double getYSpeed(){
        return this.ySpeed;
    }

    /**
     * Method to set the X Position of the puck
     * @param dx the new X Position of the puck
     */
    public void setXPosition(double dx){
        this.puck.setXPosition(dx); 
        this.xPos = dx;
    }

    /**
     * Method to set the new Y Position of the puck
     * @param dy the new Y Position of the puck
     */
    public void setYPosition(double dy){
        this.puck.setYPosition(dy);
        this.yPos = dy;
    }

    /**
     * Method to set the new size of the puck
     * @param ds the new size of the puck
     */
    public void setSize(double ds){
        this.puck.setSize(ds);
    }

    /**
     * Method to set the new X Speed of the puck
     * @param dxs the new X Speed of the puck
     */
    public void setXSpeed(double dxs){
        this.xSpeed = dxs;
    }

    /**
     * Method to set the new Y Speed of the puck
     * @param dys the new Y Speed of the puck
     */
    public void setYSpeed(double dys){
        this.ySpeed = dys;
    }

    /**
     * Method to move the puck
     * @param xVelo the X Velocity of the puck 
     * @param yVelo the Y Velocity of the puck
     */
    public void movePuck(double xVelo , double yVelo){
        xPos += xVelo;
        yPos += yVelo;
        puck.move(xVelo, yVelo);
    }

    /**
     * Method to calculate the physics of the deflection between the Mallet in contact and the Puck
     * @param m1 The mallet which is making contact with the puck
     * @param xs1 The X speed of the puck
     * @param xs2 The X speed of the Mallet
     * @param ys1 The y speed of the puck
     * @param ys2 The y speed of the Mallet
     * @return Returns the final trajectory of the puck (in X and Y direction)
     */
    public double[] deflect(Mallet m1, double xs1 , double xs2, double ys1, double ys2){
        double xPos1 = this.getXPosition();
        double yPos1 = this.getYPosition();

        double xPos2 = m1.returnXPos();
        double yPos2 = m1.returnYPos();

        double[] p1Trajectory = {xs1,ys1};
        double[] p2Trajectory = {xs2,ys2};

        double[] impactVector = {xPos2 - xPos1, yPos2 - yPos1};
        double[] impactVectorNorm = normalizeVector(impactVector);

        double p1dotImpact = Math.abs(p1Trajectory[0] * impactVectorNorm[0] + p1Trajectory[1] * impactVectorNorm[1]);
        double p2dotImpact = Math.abs(p2Trajectory[0] * impactVectorNorm[0] + p2Trajectory[1] * impactVectorNorm[1]);

        double[] p1Deflect = {-impactVectorNorm[0] * p2dotImpact, -impactVectorNorm[1] * p2dotImpact};
        double[] p2Deflect = {impactVectorNorm[0] * p1dotImpact, impactVectorNorm[1] * p1dotImpact};

        double[] p1FinalTrajectory = {p1Trajectory[0] + p1Deflect[0] - p2Deflect[0],p1Trajectory[1] + p1Deflect[1]- p2Deflect[1]};

        return p1FinalTrajectory;
    }

    /**
     * Internal method used within the physics engine
     * @param vec the array which needs to be normalized
     * @return  Returns the array once it's been normalized
     */
    private double[] normalizeVector(double[] vec){
        double mag = 0.0;
        int dimensions = vec.length;
        double[] result = new double[dimensions];

        for(int i=0; i< dimensions; i++)
            mag += vec[i] * vec[i];
        
        mag = Math.sqrt(mag);
        
        if (mag==0.0){
            result[0] = 1.0;
            for(int i = 1; i < dimensions; i++)
                result[i] = 0.0;
            
        }

        else{
            for (int i =0; i < dimensions;i++)
                result[i] = vec[i] / mag;
        }

        return result;
        
    }

}