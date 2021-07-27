package Combat;

public class Message {

    private int intData;
    private String stringData;
    private Fighter fighterData;
    private Attack attackData;
    private final Messages message;

    // constructors
    public Message(Messages message, int data) {
        this.intData = data;
        this.message = message;
    }
    public Message(Messages message, String data) {
        this.stringData = data;
        this.message = message;
    }
    public Message(Messages message, Fighter data) {
        this.fighterData = data;
        this.message = message;
    }
    public Message(Messages message, Attack data) {
        this.attackData = data;
        this.message = message;
    }

    public Message(Messages message) {
        this.message = message;
    }

    // getters
    public int getIntData() { return this.intData; }
    public String getStringData() { return this.stringData; }
    public Fighter getFighterData() { return this.fighterData; }
    public Attack getAttackData() { return this.attackData; }
    public Messages getMessage() { return this.message;}
}
