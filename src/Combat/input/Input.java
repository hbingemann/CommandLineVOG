package Combat.input;

import Combat.Attack;
import Combat.battle.Battle;
import Combat.Component;
import Combat.Fighter;

import java.util.Scanner;

public class Input extends Component {

    protected static final Scanner input = new Scanner(System.in);

    public Attack getAttackChoice(Fighter fighter) { assert false : "Input function not overridden in one of it's subcomponents."; return null; }
    public Fighter getOpponentChoice(Fighter fighter) { assert false : "Input function not overridden in one of it's subcomponents."; return null; }

}
