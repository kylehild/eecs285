//Purpose: Implement Car initialization and usage
//Original Programmer: Kyle Hildebrandt
public class CarClass{

    final double MAXSPEED = 100;

    //declare variables
    String brand, plate;
    LocationClass location = new LocationClass();
    double speed = 0.0;
    char direction = 'N';

    //constructor
    CarClass(String inBrand, String inPlate){
        brand = inBrand;
        plate = inPlate;
    }

    boolean adjustSpeed(double adjustment){

        double newSpeed = speed + adjustment;

        //negative or over MAX
        if(newSpeed < 0 || newSpeed > MAXSPEED)
            return false;
        else //change speed
            speed = newSpeed;

        return true;
    }

    void turnRight(){
        switch(direction){//change dir based on old dir
            case 'N':   direction = 'E';
                        break;

            case 'E':   direction = 'S';
                        break;

            case 'S':   direction = 'W';
                        break;

            case 'W':   direction = 'N';
                        break;

            default:    break;
        }
    }

    void turnLeft(){
        switch(direction){//change dir based on old dir
            case 'N':   direction = 'W';
                        break;

            case 'E':   direction = 'N';
                        break;

            case 'S':   direction = 'E';
                        break;

            case 'W':   direction = 'S';
                        break;

            default:    break;
        }
    }

    public String toString(){
        String dir;

        switch(direction){//get String for current dir
            case 'N':   dir = "NORTH";
                        break;

            case 'E':   dir = "EAST";
                        break;

            case 'S':   dir = "SOUTH";
                        break;

            case 'W':   dir = "WEST";
                        break;

            default:    dir = "FAIL";
                        break;
        }
        //concatenate string
    	String s = (brand + " plate: " + plate + " loc: " + location.toString()
                    + " dir: " + dir + " speed: " + speed);
        return s;
    }

    void advance(double time){
        if(time < 0)//negative do nothing
            return;
        else{
            switch(direction){//adjust pos based on dir and speed
                case 'N':   location.adjustY(speed*time);
                            break;

                case 'E':   location.adjustX(speed*time);
                            break;
                //negative for coord plane
                case 'S':   location.adjustY(-speed*time);
                            break;

                case 'W':   location.adjustX(-speed*time);
                            break;
                    
                default:    break;
            }
        }
    }
}