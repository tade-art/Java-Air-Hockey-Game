import javax.management.monitor.GaugeMonitor;

public class Table{
    
    //Creating the layout (Separated via classes referenced)
    private Rectangle leftSide = new Rectangle(0,0,384,400,"WHITE");            //Left player side
    private Rectangle rightSide = new Rectangle(0,0,384,400,"WHITE");           //Right Player side
    private Rectangle leftGoal =  new Rectangle(0, 0, 15, 150, "GREY");        //Goal on the right side
    private Rectangle rightGoal = new Rectangle(0, 0, 15, 150, "GREY");;       //Goal on the left side

    //Line classes
    private Line centreLine = new Line(0, 0, 0, 0, 4, "RED");     //Centre line between the player sides
    private Line topWall = new Line(0, 0, 0, 0, 20, "RED");       //Line for the top wall
    private Line bottomWall = new Line(0, 0, 0, 0, 20, "RED");    //Line for the top wall
    private Line leftWall = new Line(0, 0, 0, 0, 20, "RED");      //Line for the top wall
    private Line rightWall = new Line(0, 0, 0, 0, 20, "RED");     //Line for the top wall

    //Ball classes (Used to create circular outline in the middle of arena)
    private Ball bottomLayer = new Ball(0, 0, 75, "RED", 0);         //Bottom layer of the cirlce in middle (used to colour outline)
    private Ball topLayer = new Ball(0, 0, 70, "WHITE", 1);          //Top layer of the cirlce in middle (used to hollow out circle and create the look)

    //Constructor for the class (initialises the arena)
    public Table(double xPos, double yPos){
        //Set both player sides
        leftSide.setXPosition(xPos);
        leftSide.setYPosition(yPos);
        rightSide.setXPosition(xPos + 384);
        rightSide.setYPosition(yPos);

        //Setting Goal positions
        leftGoal.setXPosition(xPos);
        leftGoal.setYPosition(yPos+125);
        rightGoal.setXPosition(xPos+755);
        rightGoal.setYPosition(yPos+125);

        //Set the lines
        centreLine.setLinePosition(xPos+384, yPos+400, xPos+384, yPos);
        topWall.setLinePosition(xPos, yPos - 10, xPos + 768, yPos - 10);
        bottomWall.setLinePosition(xPos, yPos + 410, xPos + 778, yPos + 410);
        leftWall.setLinePosition(xPos-10, yPos - 10, xPos - 10, yPos + 410);
        rightWall.setLinePosition(xPos+778, yPos - 10, xPos + 778, yPos + 410);

        //Create the balls in the centre
        bottomLayer.setXPosition(xPos+384);
        bottomLayer.setYPosition(xPos+200);
        topLayer.setXPosition(xPos+383);
        topLayer.setYPosition(xPos+200);
    }

    //Method to add the table to the GameArena
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

    //Getter Method to return XPos of left player's side
    public double returnLeftSideXPos(){
        return leftSide.getXPosition();
    }

    //Getter Method to return YPos of left player's side
    public double returnLeftSideYPos(){
        return leftSide.getXPosition();
    }

    //Getter Method to return XPos of right player's side
    public double returnRightSideXPos(){
        return rightSide.getXPosition();
    }

    //Getter Method to return YPos of right player's side
    public double returnRightSideYPos(){
        return rightSide.getXPosition();
    }

    public Line returnLeftWall(){
        return leftWall;
    }
    public Line returnRightWall(){
        return rightWall;
    }    
    public Line returnTopWall(){
        return topWall;
    }    
    public Line returnBottomWall(){
        return bottomWall;
    }

}