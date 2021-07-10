public class Encounter {


    // might not need this class
    // leave for now

    /* Encounter can:
    - begin/end itself
    - begin/end different parts of the encounter

    Notes:
    - boss fights are part of the encounter
    - a function that will loop through all the
        parts of the encounter and run them
        using 'Runnable' functions
     */

    private final Runnable[] encounterParts;
    private String name;

    public Encounter(String name, Runnable[] encounterParts) {
        this.encounterParts = encounterParts;
    }

    public void RunEncounter() {
        for (Runnable encounterPart : this.encounterParts) {
            encounterPart.run();
        }
    }

}
