package Combat.distributor;

import Combat.Component;
import Combat.Fighter;
import Combat.Message;
import Debug.Debug;

public class DamageDistributor extends Component {

    private Fighter target;
    private Integer deltaTargetHealth;

    @Override
    public void receive(Message message) {
        switch (message.getMessage()) {
            case TARGET -> {
                target = message.getFighterData();
                if (deltaTargetHealth != null) {
                    target.healthComponent.changeHealth(deltaTargetHealth);
                }
            }
            case DELTA_TARGET_HEALTH -> {
                deltaTargetHealth = message.getIntData();
                if (target != null) {
                    target.healthComponent.changeHealth(deltaTargetHealth);
                }
            }
            case END_TURN -> {
                deltaTargetHealth = null;
                target = null;
            }
        }
    }

}
