public class Puck{

    private Ball puck = new Ball(0, 0, 30, "GREY",2);     //Create the Puck object

    //Constructor for Puck
    public Puck(double xPos, double yPos){
        this.puck.setXPosition(xPos);
        this.puck.setYPosition(yPos);
    }

    //Function to add puck to table
    public void addToArena(GameArena a){
        a.addBall(puck);
    }

}