package Combat;

public class Message {

    private int intData;
    private double doubleData;
    private String stringData;
    private Fighter fighterData;

    // constructors
    public Message(Messages message, int data) {
        this.intData = data;
    }

    public Message(Messages message, double data) {
        this.doubleData = data;
    }

    public Message(Messages message, String data) {
        this.stringData = data;
    }

    public Message(Messages message, Fighter data) {
        this.fighterData = data;
    }

    // getters
    public int getIntData() {
        return this.intData;
    }

    public String getStringData() {
        return this.stringData;
    }

    public double getDoubleData() {
        return this.doubleData;
    }

    public Fighter getFighterData() {
        return this.fighterData;
    }
}
