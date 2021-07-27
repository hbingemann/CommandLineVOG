package Combat;

import Debug.Debug;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Attack {

    // get the json file object that stores all the attacks defined in json file
    private static final String JSONFileLocation = "src/Data/attacks.json";
    private static final String contents;
    static {
        String tempContents;
        try {
            tempContents = new String(Files.readAllBytes(Paths.get(JSONFileLocation)));
        } catch (IOException e) {
            e.printStackTrace();
            tempContents = "";
        }
        contents = tempContents;
    }
    private static final JSONObject attacks = new JSONObject(contents);

    // non-static things
    private final JSONObject attackData;
    private final ArrayList<JSONObject> attackOutcomes;
    private Fighter target;

    public Attack(String attackId) {
        this.attackData = attacks.getJSONObject(attackId);
        this.attackOutcomes = new ArrayList<>();
    }

    // evaluate outcomes and saves to a mutable state
    public void evaluateOutcome() {
        this.attackOutcomes.clear();
        JSONArray outcomes = attackData.getJSONArray("outcomes");
        // normally do this in a loop
        for (int i = 0; i < outcomes.length(); i++) {
            JSONObject outcome = outcomes.getJSONObject(i);
            // make this a call to evaluateConditions later
            if (Math.random() * 100 < outcome.getInt("chance")) {
                attackOutcomes.add(outcome);
                if (attackData.getBoolean("singleOutcome")) {
                    break;
                }
            }
        }
    }

    // make a function here called evaluate conditions to evaluate things like "is rainy" or "chance 100"
    // conditions themselves will be evaluated by another class (?)

    // getters
    public String getName() {
        return attackData.getString("name");
    }
    public String getDescription() {
        return attackData.getString("description");
    }

    // getters for outcome of attack
    public int getDeltaTargetHealth() {
        Debug.log("target health accessed");
        // loop through attack outcomes and search for damage
        for (JSONObject outcome : attackOutcomes) {
            if (outcome.has("damage")) {
                Debug.log("damage is " + outcome.getInt("damage"));
                return outcome.getInt("damage") * -1;  // make negative since positive damage subtracts health
            }
        }
        // return 0 since none of the outcomes had a damage value
        return 0;
    }

    public int getDeltaAttackerHealth() {
        // TODO: implement delta attacker health
        return 0;
    }

}
