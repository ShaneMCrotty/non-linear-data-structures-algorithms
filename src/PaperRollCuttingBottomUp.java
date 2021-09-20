// Student Name: Shane Crotty
// Student Number: R00066752

import java.util.Scanner;
import java.lang.Math;

public class PaperRollCuttingBottomUp {

    public static void main(String[] args) {

        System.out.print("Please enter the length of the paper roll to be cut: ");

        // Allow for Multiple Possibilities for the Length of the Rod
        Scanner input = new Scanner(System.in);

        // n is the Initial Length of the Rod to be Cut
        int n = input.nextInt();
        int sizeOfArray = 6;

        // As We Will Be Dealing with an Array of Length 6 for Prices At the Very Least
        if (n >= 6){
            sizeOfArray = (n+1);
        }
        // Given the User's Input (Length of the Rod), an Array is Created, and Populated with the only Cuts with Prices
        double[] price = new double[sizeOfArray];
        price[1] = 1.20;
        price[2] = 3;
        price[3] = 5.8;
        price[5] = 10.1;

        System.out.println();
        System.out.println("The initial length of the paper roll is " + n + ".\n");

        // The Maximum Number of Possible Ways a Rod Can be Cut
        int number_of_ways_to_cut = (int) Math.pow(2, (n - 1));
        System.out.println("There are " + number_of_ways_to_cut + " ways of cutting a rod of length " + n + "\n");

        // An Array to Store the Best Cuts Made
        double[] optimumCuts = new double[number_of_ways_to_cut + 1];

        // An Array to Track the Maximum Revenue
        double[] r = new double[n+1];
        r[0] = 0;
        int i,j;

        // For the Length of the Rod
        for(j=1; j<=n; j++) {
            double maximumRevenue = Double.NEGATIVE_INFINITY;
            // For each Cut
            for(i=1; i<=j; i++) {
                if(maximumRevenue < price[i] + r[j-i]){
                    maximumRevenue = price[i] + r[j-i];
                    optimumCuts[j] = i;
                }
            }
            // The Final Max Revenue
            r[j] = maximumRevenue;
        }
        System.out.print("The best possible revenue obtainable for a rod of length: "+ n + " is: ");
        System.out.printf("€%.2f", r[n]);
        System.out.print("\n\n");
        System.out.println("The Cuts are as follows:");
        System.out.print("\n");

        // Printing the Optimum Cuts Made in a Loop
        while (n > 0){
            System.out.printf("Rod of Length: %.0f", (optimumCuts[n]));
            System.out.print("\n");
            n = (int) (n - optimumCuts[n]);
        }
    }
}

