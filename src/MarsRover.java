import java.util.HashSet;
import java.util.Set;  
import java.util.Scanner;
// Enum for N;E;S;W 
enum Direction {  
    N, E, S, W;  
    // Calculate the cardinal direction to the left. 
    // Definitely a better way to do this as the values are hard coded. 
    // But it's fairly simple and works.
    // Uses the 4 points on a compass (North having an ordinal value of 0 in this case)
    // to calculate the rotation. (0 + 3) mod 4 giving us the value of 3
    // which then points towards the west point. 
    public Direction rotateLeft() {  
        return values()[(ordinal() + 3) % 4];  
    }  
    // Calc to turn right  
    public Direction rotateRight() {  
        return values()[(ordinal() + 1) % 4]; 

    }  
}  

public class MarsRover {  
    private int x;  
    private int y;  
    private Direction direction; 
    // Set to store the positions of visited tiles
    private Set<String> positionsVisited; 
    // Constructor
    public MarsRover(int x, int y, Direction direction) {  
        this.x = x;  
        this.y = y;  
        this.direction = direction; 
        this.positionsVisited = new HashSet<>();  
    }  
    // Rotate the rover  
    public void rotate(char rotation) {   
        direction = (rotation == 'L') ? direction.rotateLeft() : direction.rotateRight(); 
    }  
    // Move the rover  
    public void move() {  
        int nextX = x;  
        int nextY = y;  
        switch (direction) { 
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
        // Check for previously visited tiles and upcoming border tiles  
        String nextPosition = nextX + " " + nextY;  
        if (!positionsVisited.contains(nextPosition) && isValidPosition(nextX, nextY)) {  
            x = nextX;  
            y = nextY;  
            tileSurveyed(nextX, nextY);
        } else {
            System.out.println(nextPosition + " is invalid.");
        } 
    }  
    // Check if a position is valid in the grid  
    private boolean isValidPosition(int x, int y) {  
        int maxX = 5; // Border X-coordinate of the grid  
        int maxY = 5; // Border Y-coordinate of the grid  
        return x >= 0 && x <= maxX && y >= 0 && y <= maxY;  
    }  
    // Add a tile to the list of surveyed area on the grid  
    public void tileSurveyed(int x, int y) {  
        positionsVisited.add(x + " " + y);  
    }  
    // Get the current position of the rover  
    public String getPosition() {  
        return x + " " + y + " " + direction;  
    }  
    // Control centre for the rover  
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
        // Create a Scanner object to receive input
        Scanner uInput = new Scanner(System.in);
        Scanner uInput2 = new Scanner(System.in);
        // Create a rover object 
        MarsRover rover1 = new MarsRover(uInput.nextInt(), uInput.nextInt(), Direction.N);   
        commandCentre(rover1, uInput2.nextLine());
        // Close the scannner
        uInput.close();
        uInput2.close();
        // Print rover final position  
        System.out.println(rover1.getPosition()); 
        System.out.println(rover1.positionsVisited.toArray());
    }  
}  