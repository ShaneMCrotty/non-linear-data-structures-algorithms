// Student Name: Shane Crotty
// Student Number: R00066752

import java.util.Scanner;

public class RobotMoving {

    // Unicode for Arrows
    private static String Right = "\uD83E\uDC6A";
    private static String Down = "\uD83E\uDC6B";
    private static String Diagonal = "\uD83E\uDC6E";

    // Create a Matrix using the Length Parameter (n)
    private static double[][] createMatrix(int n) {

        double[][] matrix = new double[n][n];

        return matrix;
    }
    // Create a Matrix for Directions using the Length Parameter (n)
    private static String[][] createDirectionMatrix(int n) {

        String[][] matrix = new String[n][n];

        return matrix;
    }
    private static double minimumOfThree(double x, double y, double z){

        // Returns the Minimum of First Two Numbers, Then the Minimum of The New Number and the Remaining Number
        return Math.min(Math.min(x,y), z);
    }
    private static void printPossibleMovements(double matrix[][]){
        // For Each Row
        for (int row = 0; row < matrix.length; row++) {
            // For Each Column
            for (int col = 0; col < matrix[row].length; col++) {
                // Print Bars to Separate Values
                System.out.print("|");
                // 1 Decimal Point
                System.out.printf("%.1f", matrix[row][col]);
                System.out.print("|");
            }
            // Skip to Next Row
            System.out.println();
        }
    }
    private static void printDirections(String matrix[][]){
        // For Each Row
        for (int row = 0; row < matrix.length; row++) {
            // For Each Column
            for (int col = 0; col < matrix[row].length; col++) {
                // Print Bars to Separate Values
                System.out.print("|");
                System.out.print(matrix[row][col]);
                System.out.print("|");
            }
            // Skip to Next Row
            System.out.println();
        }
    }

    // Function to Print Best Path, The Cost of the Cell and Direction
    private static void printPath(double[][]costMatrix, String[][]directMatrix, int i, int j){

        // The Beginning, where case "  " means the Start of the Matrix
        if (directMatrix[i][j].equals("S")){
            System.out.printf("Start" + " %.1f,", costMatrix[i][j]);
        }
        // If the Position is Right
        if (directMatrix[i][j].equals(Right)) {
            // Recursively Call (We Wish to get to the Base Case where directMatrix[i][j] is "S"
            printPath(costMatrix, directMatrix, i, j - 1);
            System.out.printf(Right + "%.1f,", costMatrix[i][j]);
        }
        // If the Position is Down
        if (directMatrix[i][j].equals(Down)) {
            printPath(costMatrix, directMatrix, i - 1, j);
            System.out.printf(Down + "%.1f,", costMatrix[i][j]);
        }
        // If the Position is Diagonal (Right-Down)
        if (directMatrix[i][j].equals(Diagonal)) {
            printPath(costMatrix, directMatrix, i - 1, j - 1);
            System.out.printf(Diagonal + "%.1f,", costMatrix[i][j]);
        }
    }


    public static void main(String[] args) {

        // The Costs for Each Movement, in the following Order (Right, Down, Diagonal)
        double[] cost1 = {1.1, 1.3, 2.5};
        double[] cost2 = {1.5, 1.2, 2.3};

        // Allow for Multiple Possibilities for the Size of the Matrix
        System.out.print("Please enter the size of the matrix: ");
        Scanner input = new Scanner(System.in);

        // n is the size of the Matrix to be Passed to the createMatrix Method
        int n = input.nextInt();
        double[][] costMatrix = createMatrix(n);
        String[][] directMatrix = createDirectionMatrix(n);
        double[] cost = new double[3];

        System.out.print("Would you like to use the energy costings for Cost1 or Cost2? (1 or 2) ");

        // Allow for Either of Two Costings
        int selection = input.nextInt();
        if (selection == 1){
            cost = cost1;
        }
        if (selection ==2){
            cost = cost2;
        }

        // The Robot's Starting Position in the Top-Left
        costMatrix[0][0] = 0.0;
        directMatrix[0][0] = "S";

        // Populate Both Matrices
        for (int j = 1; j < costMatrix.length; j++) {
            costMatrix[0][j] = costMatrix[0][j - 1] + cost[0];
            directMatrix[0][j] = Right;
        }
        for (int i = 1; i < costMatrix.length; i++){
            costMatrix[i][0] = costMatrix[i-1][0] + cost[1];
            directMatrix[i][0] = Down;

            for(int j= 1; j < costMatrix.length; j++){
                double down = (costMatrix[i-1][j] + cost[1]);
                double right = (costMatrix[i][j-1] + cost[0]);
                double diagonal = (costMatrix[i-1][j-1] + cost[2]);

                // Using a Pre-made Function for Minimum of Three Values
                costMatrix[i][j] = minimumOfThree(right, down, diagonal);

                // Using if Statements to Populate with Strings (Arrows)
                if (right < down && right < diagonal){
                    directMatrix[i][j] = Right;
                }
                else if (down < diagonal){
                    directMatrix[i][j] = Down;
                }
                else{
                    directMatrix[i][j] = Diagonal;
                }
            }
        }
        System.out.println("");
        System.out.println("Possible Movements:");
        printPossibleMovements(costMatrix);

        // Minimum Cost
        System.out.println("");
        System.out.printf("The minimum cost to complete this path is: %.1f ", costMatrix[n-1][n-1]);
        System.out.println("\n");

        // Directions
        System.out.println("The Directions Available are as follow: ");
        printDirections(directMatrix);
        System.out.print("\n");

        // Directions Taken to Reach the Path of Minimum Cost (Recursion)
        printPath(costMatrix, directMatrix, n-1, n-1);




    }
}



