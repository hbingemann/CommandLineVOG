package Debug;

import jdk.swing.interop.SwingInterOpUtils;

public class Debug {

    private static final boolean DEBUG = false;

    // for color
    private static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void log(String string) {
        if (DEBUG) {
            // adding a little yellow color so I know it's a debug message
            System.out.println(ANSI_YELLOW + string + ANSI_RESET);
        }
    }

    public static void clearScreen() {
        if (!DEBUG) {
            System.out.println("CLEAR");
            System.out.print("\n\n\n");
        }
    }

}
