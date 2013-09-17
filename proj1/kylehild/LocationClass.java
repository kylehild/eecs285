//Purpose: Implement Location initialization and usage
//Original Programmer: Kyle Hildebrandt
public class LocationClass{

    //declare variables
    double xVal, yVal;

    //default ctor at (0, 0)
    LocationClass(){
        xVal = 0;
        yVal = 0;
    }

    //ctor with input at (inX, inY)
    LocationClass(double inX, double inY){
        xVal = inX;
        yVal = inY;
    }

    public String toString(){//concatenate string
        String s = ("(" + xVal + ", " + yVal + ")");
        return s;
    }

    void adjustX(double amount){
        xVal += amount;//change xPos
    }

    void adjustY(double amount){
        yVal += amount;//change yPos
    }
}