import java.util.Scanner;

// Enum for N;E;S;W 
enum Direction {  
    N, E, S, W;  
    // Calculate the cardinal direction to the left. 
    // Uses the 4 points on a compass (North having an ordinal value of 0 in this case)
    // to calculate the rotation. (0 + 3) mod 4 giving us the value of 3
    // which then points towards the west point. 
    public Direction rotateLeft() {  
        return values()[(ordinal() + 3) % 4];  
    }  
    // Calc to turn right - same principle  
    public Direction rotateRight() {  
        return values()[(ordinal() + 1) % 4]; 
    }  
} 

public class MarsRover {  
    private int x;  
    private int y;  
    private Direction dir; 
    // Constructor
    public MarsRover(int x, int y, Direction direction) {  
        this.x = x;  
        this.y = y;  
        this.dir = direction;  
    }  
    // Rotate the rover depending on the command received 
    public void rotate(char rotation) {   
        dir = (rotation == 'L') ? dir.rotateLeft() : dir.rotateRight(); 
    }  
    // Move the rover if the move has been validated
    public void move() {  
        int nextX = x;  
        int nextY = y;  
        switch (dir) { 
            case N:  
                nextY++;  
                break;  
            case E:  
                nextX++;  
                break;  
            case S:  
                nextY--;  
                break;  
            case W:  
                nextX--;  
                break;  
        }  
        // Check for upcoming border tiles  
        String nextPos = nextX + " " + nextY;  
        if (validPosition(nextX, nextY)) {  
            x = nextX;  
            y = nextY; 
        } else {
            System.out.println("Position: " + nextPos + " is not a reachable point on the plateau."); 
        } 
    }  
    // Method for the scanner that receives user input in the console
    public static String inputScanner() {
        Scanner uInput = new Scanner(System.in);
        String input = uInput.nextLine();
        return input;
    }
    // Method that validates the user input for the x y coordinates
    public static String uValidateCoord(String input) {
        if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) <= 5) {  
                
        } else {  
            System.out.println("This is not a valid position on the plateau. The plateau ranges from 0:0 - 5:5."); 
            System.exit(0);
        }
        return input;
    }
    // Method that validates the rover command string
    public static String uValidateCommand(String input) {
        String temp = input;
        for (char order : temp.toCharArray()) {  
            if (order == 'L' || order == 'R' || order == 'M') {  
                
            } else {  
                System.out.println("This is not a valid command string for the rover. A valid command string consists only of 'L', 'R' and 'M'"); 
                System.exit(0);
            }
        }         
        return input;
    }
    // Get the current position of the rover  
    public String getPos() {  
        return x + " " + y + " " + dir;  
    }  
    // Check if a position is valid in the grid  
    private boolean validPosition(int x, int y) {  
        int maxX = 5; // Border X-coord of the plateau
        int maxY = 5; // Border Y-coord of the plateau  
        return x >= 0 && x <= maxX && y >= 0 && y <= maxY;  
    }  
    // Method for the control centre of the rover
    // The method splits the received command string into an array of char and loops
    // Through it to execute specific orders
    public static void commandCentre(MarsRover rover, String commandString) {
        String controlSequence = commandString;  
        for (char order : controlSequence.toCharArray()) {  
            if (order == 'L' || order == 'R') {  
                rover.rotate(order);  
            } else if (order == 'M') {  
                rover.move();  
            }  
        } 
    }; 
    public static void main(String[] args) { 
        // Create a rover object 
        MarsRover rover1 = new MarsRover(Integer.parseInt(uValidateCoord(inputScanner())), Integer.parseInt(uValidateCoord(inputScanner())), Direction.N);  
        commandCentre(rover1, uValidateCommand(inputScanner()));
        // Print rover1 final position 
        System.out.println("------------------" + '\n' + "Rover 1 Final Position: " + rover1.getPos() + "\n" + "------------------"); 
        // Second rover object
        MarsRover rover2 = new MarsRover(Integer.parseInt(uValidateCoord(inputScanner())), Integer.parseInt(uValidateCoord(inputScanner())), Direction.E); 
        commandCentre(rover2, uValidateCommand(inputScanner()));
        // Print rover2 final position 
        System.out.println("------------------" + '\n' + "Rover 2 Final Position: " + rover2.getPos() + "\n" + "------------------"); 
    }  
}  