/**
 * This is the class which is used to assemble the arena in the air hockey game
 * @author Tadas Ivanauskas
 */
public class Table{
    
    /**
     * Creating the layout (Separated via classes referenced)
    */
    private Rectangle leftSide = new Rectangle(0,0,384,400,"WHITE");          
    private Rectangle rightSide = new Rectangle(0,0,384,400,"WHITE");    
    private Rectangle leftGoal =  new Rectangle(0, 0, 15, 150, "GREY");    
    private Rectangle rightGoal = new Rectangle(0, 0, 15, 150, "GREY");;   

    /**
     * Creating the outlines of the arena and the centre line
     */
    private Line centreLine = new Line(0, 0, 0, 0, 4, "RED");    
    private Line topWall = new Line(0, 0, 0, 0, 20, "RED");    
    private Line bottomWall = new Line(0, 0, 0, 0, 20, "RED");
    private Line leftWall = new Line(0, 0, 0, 0, 20, "RED"); 
    private Line rightWall = new Line(0, 0, 0, 0, 20, "RED");

    /**
     * Ball classes which are used to create the "illusion" of the centre
     */
    private Ball bottomLayer = new Ball(0, 0, 75, "RED", 0);   
    private Ball topLayer = new Ball(0, 0, 70, "WHITE", 1); 

    /**
     * Constructor Method
     * @param xPos the X Position of the top left hand corner of the arena
     * @param yPos the Y Position of the top left hand corner of the arena
     */
    public Table(double xPos, double yPos){
        /**
         * Setting the positions of the left and right sides for the players
         */
        leftSide.setXPosition(xPos);
        leftSide.setYPosition(yPos);
        rightSide.setXPosition(xPos + 384);
        rightSide.setYPosition(yPos);

        /**
         * Setting the positions of the left and right goals
         */
        leftGoal.setXPosition(xPos);
        leftGoal.setYPosition(yPos+125);
        rightGoal.setXPosition(xPos+755);
        rightGoal.setYPosition(yPos+125);

        /**
         * Settings the lines - creating the outlines of the arena
         */
        centreLine.setLinePosition(xPos+384, yPos+400, xPos+384, yPos);
        topWall.setLinePosition(xPos, yPos - 10, xPos + 768, yPos - 10);
        bottomWall.setLinePosition(xPos, yPos + 410, xPos + 778, yPos + 410);
        leftWall.setLinePosition(xPos-10, yPos - 10, xPos - 10, yPos + 410);
        rightWall.setLinePosition(xPos+778, yPos - 10, xPos + 778, yPos + 410);

        /**
         * Setting the centre piece - the "illusion" of the middle
         */
        bottomLayer.setXPosition(xPos+384);
        bottomLayer.setYPosition(xPos+200);
        topLayer.setXPosition(xPos+383);
        topLayer.setYPosition(xPos+200);
    }

    /**
     * Method to add the arena to the table
     * @param a the arena which table is being added to 
     */
    public void addToArena(GameArena a){
        a.addRectangle(leftSide);
        a.addRectangle(rightSide);
        a.addRectangle(leftGoal);
        a.addRectangle(rightGoal);
        a.addLine(bottomWall);
        a.addLine(topWall);
        a.addLine(leftWall);
        a.addLine(rightWall);
        a.addLine(centreLine);
        a.addBall(bottomLayer);
        a.addBall(topLayer);
    }

    /**
     * Method to return the X Position of the left side
     * @return Returns the X Position
     */
    public double returnLeftSideXPos(){
        return leftSide.getXPosition();
    }

    /**
     * Method to return the Y Position of the left side
     * @return Returns the Y Position
     */
    public double returnLeftSideYPos(){
        return leftSide.getXPosition();
    }

    /**
     * Method to return the X Position of the right side
     * @return Returns the X Position
     */
    public double returnRightSideXPos(){
        return rightSide.getXPosition();
    }

    /**
     * Method to return the Y Position of the right side
     * @return Returns the Y Position
     */
    public double returnRightSideYPos(){
        return rightSide.getXPosition();
    }

}