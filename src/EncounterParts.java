import java.util.*;
import java.util.concurrent.TimeUnit;

interface EncounterPart {
    boolean run();
}

class EncounterParts {

    /* This is where the scripts for all the encounter parts will be located
    Every encounter part will:
     - be a runnable function
     - return true if completed and false if not

       !! IMPORTANT !!
       The boss fight functions will be in a DIFFERENT package
     */

    /* For first encounter => "Spire"
    */
    public static final EncounterPart spire = () -> {
        /* Ideas:
            - a maze to get to the different sync plates
            - a mini-boss fight to fend off the sync plates
            - a game where you have to use 'A' and 'D' to get under the correct sync plate and then type 'W'
         */

        // get scanner for input
        Scanner input = new Scanner(System.in);

        // initialize variables
        String userInput;
        int userLocation = 1;
        int enemyLocation = 2;
        boolean enemyDead = false;
        boolean missed = false;

        // play-through 5 times
        for (int i = 0; i < 5; i++) {
            do {
                // display and get input
                clearConsole();
                printPlates(enemyLocation, userLocation);
                userInput = input.next();

                // process input
                switch (userInput.toUpperCase()) {
                    case "W" -> {
                        if (userLocation == enemyLocation) {
                            enemyDead = true;
                        } else {
                            missed = true;
                        }
                    }
                    case "A" -> {
                        userLocation--;
                        userLocation = clamp(userLocation, 1, 3);
                    }
                    case "D" -> {
                        userLocation++;
                        userLocation = clamp(userLocation, 1, 3);
                    }
                }

            } while (!enemyDead && !missed);

            // if missed then return false (because player failed to complete encounter)
            if (missed) {
                return false;
            }

            // change up enemy location
            enemyLocation = (int) Math.floor(Math.random() * 3) + 1;
            System.out.println(enemyLocation);

            // reset enemy dead
            enemyDead = false;

        }

        // return true because player never missed
        return true;
    };

    private static void printPlates(int plateWithEnemy, int userCurrentPlate) {
        // print tops
        System.out.println("/---\\   /---\\   /---\\");

        // print centers
        for (int i = 1; i <= 3; i++) {
            if (i == plateWithEnemy) {
                System.out.print("| 0 |");
            } else {
                System.out.print("|   |");
            }
            System.out.print("   ");
        }

        // print bottom
        System.out.println("\n\\---/   \\---/   \\---/");

        // print where the user is
        for (int i = 1; i <= 3; i++) {
            if (i == userCurrentPlate) {
                System.out.print("  *  ");
            } else {
                System.out.print("     ");
            }
            System.out.print("   ");
        }

        // print a new line to prompt the user for a new input
        System.out.print("\nHit 'D' / 'W' / 'A', then <enter>: ");
    }

    /* For second encounter => "Confluxes"
     */

    public static final EncounterPart confluxes = () -> {
        /* IDEAS:
            - it is essentially the same thing as spire
         */

        return true;

    };

    /* For third encounter => "Oracles"
     */

    public static final EncounterPart oracles = () -> {

        // initialize variables
        Scanner input = new Scanner(System.in);
        int playerInput;
        int numberOfOracles;
        List<Integer> oracles = new ArrayList<>();
        List<Integer> randomOraclesOrder = new ArrayList<>();
        List<Integer> oraclesDisplay = new ArrayList<>();


        // 3 rounds of oracles
        for (int round = 0; round < 3; round++) {

            // define the number of oracles
            numberOfOracles = 3 + round * 2;

            // reset / initialize the many oracles lists
            randomOraclesOrder.clear();
            oraclesDisplay.clear();
            oracles.clear();
            for (int i = 1; i <= 7; i++) {
                oracles.add(i);
            }

            // pick 3, 5, 7 random numbers from as the oracles and put them in 'randomOraclesOrder'
            // then put those in oracles display and sort then display them
            for (int i = 0; i < numberOfOracles; i++) {
                // get random oracle
                int randNum = (int) Math.floor(Math.random() * oracles.size());
                int randOracle = oracles.get(randNum);
                randomOraclesOrder.add(randOracle);
                oraclesDisplay.add(randOracle);
                // update the display
                Collections.sort(oraclesDisplay);
                clearConsole();
                System.out.println("Oracles -> " + oraclesDisplay);
                oracles.remove(randNum);
                if (i == numberOfOracles - 1) {
                    // don't wait after the last oracle has been displayed
                    continue;
                }
                // wait around 2 seconds between oracles
                wait(1600);
            }

            // now ask for user input as to what order the oracles were in
            // also check if they were correct
            for (int i = 0; i < numberOfOracles; i++) {
                System.out.print("Oracle " + (i + 1) + ": ");
                playerInput = input.nextInt();
                if (playerInput != randomOraclesOrder.get(i)) {
                    // player was incorrect so encounter was failed
                    return false;
                } else {
                    // tell them they were right
                    System.out.println("Correct!");
                }
            }
        }

        // player made it through 3 rounds
        return true;

    };


    /*
    --------------------
    Some useful functions for all the encounters
    --------------------
     */

    private static void wait(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    private static void clearConsole() {
        System.out.println("CLEAR");
        System.out.println("\n\n\n");
    }

}
