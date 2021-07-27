package Combat;

import java.util.ArrayList;

public class ComponentContainer {

    public void send(Message message) {
        for (Component component : components) {
            component.receive(message);
        }
    }

    protected ArrayList<Component> components = new ArrayList<>();

}
