//Purpose: This class will act as a driver program to test functionality
//         implemented for EECS285 project 1, a small project to 
//         create car objects and "drive" them around via an API.
//Original Programmer: Andrew M. Morgan
public class CarController
{
  public static void main(String[] args)
  {
    CarClass chevy;
    CarClass ford;

    // Create two new car objects
    chevy = new CarClass("Chevrolet", "412 IQS");
    ford = new CarClass("Ford", "191 MMZ");

    // Print out car's initial state
    System.out.println(chevy.toString());
    System.out.println(ford.toString());

    // Adjust some of the chevy's attributes, and print
    chevy.adjustSpeed(10);
    chevy.turnRight();
    System.out.println(chevy.toString());

    // Move the chevy now using its direction and speed
    chevy.advance(10);
    System.out.println(chevy.toString());

    // Perform some operations on the ford as well
    ford.turnLeft();
    ford.advance(10);
    ford.adjustSpeed(20);
    ford.advance(10);

    ford.turnLeft();
    ford.advance(20);

    // Some more chevy movement
    chevy.adjustSpeed(-5);
    chevy.advance(10);

    // Finally print out the final states of the cars
    System.out.println(chevy.toString());
    System.out.println(ford.toString());
  }
}
