package Combat;

import Combat.health.Health;
import Debug.Debug;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// creates Effects like poisoned or confused
public class StatusEffect {

    // get the json file object
    private static final String JSONFileLocation = "src/Data/statusEffects.json";
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
    private static final JSONObject statusEffects = new JSONObject(contents);
    // all valid effect names
    private static final String validEffectNames = statusEffects.getJSONArray("validEffectNames").toString();

    // non-static variables
    private JSONObject statusEffectData;
    private JSONObject effects;

    /* #######  HOW IT WORKS ###### */

    // json file stores data for status effects
    // attack stores name of status effect(s) it has
    // when attack is used on fighter, status effect is created using name
    // status effect also store weaknesses on the player

    // CONSTRUCTOR (this will be type object pattern using json file)
    public StatusEffect(String statusEffectName, Fighter fighterWithStatusEffect) {
        // search json file using name and get object representing status effect
        this.statusEffectData = statusEffects.getJSONObject(statusEffectName);
        // create a new object so that it can be altered, the Effect does not need to be altered
        this.effects = new JSONObject(statusEffectData.getJSONObject("effects").toString());
        // "subscribe" to the fighters events
        //fighterWithStatusEffect.addObserver(mainObserver);
    }


    /*
    ###################################

     FUNCTIONS CALLED BY THE OBSERVER PATTERN

    ###################################
     */

    public void fighterEndTurn() {

        // loop through all the effects and subtract one turn from them
        JSONArray effectsArray = effects.names();
        for (int i = 0; i < effectsArray.length(); i++) {
            JSONObject effect = effectsArray.getJSONObject(i);
            int updatedTurns = effect.getInt("turns") - 1;
            // remove this effect if it no longer has any turns (it wore off)
            if (updatedTurns == 0) {
                effectsArray.remove(i);
            } else {
                // otherwise replace the turns with updated value
                effect.put("turns", updatedTurns);
            }
        }
        // check if there are any more effects


        // whatever else should happen when fighter turn ends

    }


    /* #######################################

        FUNCTIONS TO BE CALLED BY THE FIGHTER CLASS

       #######################################
     */


    // function to alter damage output of fighter
    public int affectDamage(int currentFighterDamage) {
        return (int) affectValue("damage", currentFighterDamage);
    }

    // function to alter health of fighter (taking damage)
    public void affectHealth(Health fighterHealth) {
        //fighterHealth.changeHealth( (int) affectValue("health", fighterHealth.getHealth()));
    }

    // function to alter the chance of missing attack (chance = x out of 100)
    public int affectChance(int currentFighterChance) {
        return (int) affectValue("chance", currentFighterChance);
    }

    // function to check whether fighter misses a turn
    public boolean shouldMissTurn() { return effectOccurs("missTurn"); }

    // function for checking for weakness / resistance to certain elements
    public int getElementalAffect(String elementName) {
        return (int) affectValue("elemental", 1, elementName);
    }


    /*
    * ###################################
    *
    * FUNCTIONS THAT SIMPLIFY ABOVE METHODS
    *
    * #####################################
    * */




    // allow just an effectName as a parameter, calls onto function below it
    private boolean effectOccurs(String effectName) { return effectOccurs(effectName, null); }

    // checks whether effect takes place (it exists and doesn't miss **Rhyme! )
    // allows element name as a parameter
    private boolean effectOccurs(String effectName, String elementName) {
        // check to make sure we have a valid effectName
        assert(validEffectNames.contains(effectName));
        Debug.log(effectName + " exists");
        if (effects.has(effectName)) {
            JSONObject effect = effects.getJSONObject(effectName);
            // if it is an elemental effect then make sure it is the right element
            if (effectName.equals("elemental") && effect.has("element") && !effect.getString("element").equals(elementName)) {
                // the elemental name does not match
                return false;
            }
            // check for chance of effect hitting
            if (effect.has("chance")) {
                return Math.random() < effect.getInt("chance") / 100.0;
            }
            return true;
        }
        // effect doesn't exist
        return false;
    }

    // function for checking without an element name, calls on function below
    private double affectValue(String effectName, double value) { return affectValue(effectName, value, null); }

    // for when an element wants to affect a value
    private double affectValue(String effectName, double value, String elementName) {
        if (effectOccurs(effectName, elementName)) {
            JSONObject effect = effects.getJSONObject(effectName);
            if (effect.has("amount")) {
                return value + effect.getInt("amount");
            }
            if (effect.has("influence")) {
                return value * effect.getDouble("influence");
            }
            Debug.log(effectName + "'s value was not properly affected");
        }
        return value;
    }



}
