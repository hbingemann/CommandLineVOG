package Combat;

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

    public Attack(String attackId) {
        this.attackData = attacks.getJSONObject(attackId);
    }

    public int getDamage() {
        if (Math.random() * 100 < attackData.getInt("chance")) {
            return attackData.getInt("damage");
        }
        return 0;
    }

    // make a function here called evaluate conditions to evaluate things like "is rainy" or "chance 100"

    // also make a function to get other damage that may occur if attack misses

    public String getName() {
        return attackData.getString("name");
    }

    public String getDescription() {
        return attackData.getString("description");
    }

}
