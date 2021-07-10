package Combat;

import java.util.ArrayList;

public class Subject {

    /*
    ########################################

        THE MODERN OBSERVER PATTERN (I THINK)

    ########################################
     */

    // keep track of other classes that are observing
    protected ArrayList<Observer> observingFunctions;

    // allow other classes to observe by giving this class their observing function
    public void addObserver(Observer observingFunction) {
        observingFunctions.add(observingFunction);
    }

    // function for notifying all observing classes
    protected void sendNotification(Notifications notification) {
        for (Observer observingFunction: observingFunctions) {
            observingFunction.onNotify(notification);
        }
    }

}
