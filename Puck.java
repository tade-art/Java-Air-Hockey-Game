public class Puck{

    private Ball puck = new Ball(0, 0, 30, "GREY",2);     //Create the Puck object
    private double xPos = 0;
    private double yPos = 0;
    private double xSpeed = 0;
    private double ySpeed = 0;
    private double size = 30;

    //Constructor for Puck
    public Puck(double xPos, double yPos){
        this.puck.setXPosition(xPos);
        this.puck.setYPosition(yPos);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    //Function to add puck to table
    public void addToArena(GameArena a){
        a.addBall(puck);
    }

    /* GETTER METHODS */
    public double getXPosition(){
        return xPos;
    }  

    public double getYPosition(){
        return yPos;
    }

    public double getSize(){
        return size;
    }

    public double getXSpeed(){
        return this.xSpeed;
    }

    public double getYSpeed(){
        return this.ySpeed;
    }

    /* SETTER METHODS */

    public void setXPosition(double dx){
        this.xPos = dx;
    }

    public void setYPosition(double dy){
        this.yPos = dy;
    }

    public void setSize(double ds){
        this.puck.setSize(ds);
    }

    public void setXSpeed(double dxs){
        this.xSpeed = dxs;
    }

    public void setYSpeed(double dys){
        this.ySpeed = dys;
    }

    public void movePuck(double xVelo , double yVelo){
        xPos += xVelo;
        yPos += yVelo;
        puck.move(xVelo, yVelo);
    }

    /* PHYSICS ENGINE */
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