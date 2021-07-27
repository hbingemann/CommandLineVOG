package Combat.battle;

import Combat.Component;
import Combat.ComponentContainer;
import Combat.Fighter;
import Combat.display.Display;
import Combat.health.Health;
import Combat.input.Input;

import java.util.ArrayList;

// battle will be a component of the fighters
// the difference between this component and the others:
// - every fighter has the same battle component instance (assuming that they are in the same battle)
public class Battle extends Component {

    public void run() {}

    // these should probably be changed in some way
    public void registerFighter(Fighter fighter) {}
    public ArrayList<Fighter> getOpponents(Fighter fighter) { return null; }
    public ArrayList<Fighter> getWinners() { return null; }

}
