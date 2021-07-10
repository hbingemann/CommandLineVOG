package Combat;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

// ALL ATTACKS ARE STATIC JSON OBJECTS
public class Attacks {

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

    // for storing activeAttacks
    private static final JSONObject activeAttackObjects = new JSONObject();

    // to get an attack and not create a new one all the time
    public static JSONObject getAttack(String attackName) {
        // if attack exists already return it (from 'activeAttackObjects' list)
        if (activeAttackObjects.has(attackName)) {
            return activeAttackObjects.getJSONObject(attackName);
        }
        // else get the attack from 'attacks, add it to list 'activeAttackObjects' then return it
        JSONObject newAttackObject = attacks.getJSONObject(attackName);
        activeAttackObjects.put(attackName, newAttackObject);
        return newAttackObject;
    }

}
