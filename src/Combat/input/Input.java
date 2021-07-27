package Combat.input;

import Combat.*;

import java.util.Scanner;

public class Input extends Component {

    protected static final Scanner input = new Scanner(System.in);

    public void handle(Fighter fighter) {
        // get choices
        Attack attackChosen = getAttackChoice(fighter);
        Fighter targetChosen = getTargetChoice(fighter);

        // evaluate attack outcome
        attackChosen.evaluateOutcome();

        // send messages
        fighter.send(new Message(Messages.ATTACK, attackChosen));
        fighter.send(new Message(Messages.TARGET, targetChosen));
        fighter.send(new Message(Messages.ATTACKER, fighter));
        fighter.send(new Message(Messages.DELTA_ATTACKER_HEALTH, attackChosen.getDeltaAttackerHealth()));
        fighter.send(new Message(Messages.DELTA_TARGET_HEALTH, attackChosen.getDeltaTargetHealth()));
    };

    public Attack getAttackChoice(Fighter fighter) { return null; }
    public Fighter getTargetChoice(Fighter fighter) { return null; }

}
