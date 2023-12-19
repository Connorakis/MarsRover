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
    // Rotate the rover  
    public void rotate(char rotation) {   
        dir = (rotation == 'L') ? dir.rotateLeft() : dir.rotateRight(); 
    }  
    // Move the rover  
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
        // Check for previously visited tiles and upcoming border tiles  
        String nextPos = nextX + " " + nextY;  
        if (validPosition(nextX, nextY)) {  
            x = nextX;  
            y = nextY; 
        } else {
            System.out.println("Position: " + nextPos + " is invalid. ");
        } 
    }  
    // Get the current position of the rover  
    public String getPos() {  
        return x + " " + y + " " + dir;  
    }  
    // Check if a position is valid in the grid  
    private boolean validPosition(int x, int y) {  
        int maxX = 5; // Border X-coordinate of the grid  
        int maxY = 5; // Border Y-coordinate of the grid  
        return x >= 0 && x <= maxX && y >= 0 && y <= maxY;  
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
        Scanner uIn = new Scanner(System.in);
        Scanner uIn2 = new Scanner(System.in);
        // Create a rover object 
        MarsRover rover1 = new MarsRover(uIn.nextInt(), uIn.nextInt(), Direction.N);  
        commandCentre(rover1, uIn2.nextLine());
        // Print rover1 final position 
        System.out.println("------------------" + '\n' + "Rover 1 Final Position: " + rover1.getPos() + "\n" + "------------------"); 
        // Second rover object
        MarsRover rover2 = new MarsRover(uIn.nextInt(), uIn.nextInt(), Direction.E); 
        commandCentre(rover2, uIn2.nextLine());
        // Print rover2 final position 
        System.out.println("------------------" + '\n' + "Rover 2 Final Position: " + rover2.getPos() + "\n" + "------------------"); 
        // Close the scannners
        uIn.close();
        uIn2.close(); 
    }  
}  