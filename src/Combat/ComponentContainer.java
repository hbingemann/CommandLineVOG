package Combat;

import java.util.ArrayList;

public class ComponentContainer {

    public void send(String message) {
        for (Component component : components) {
            component.receive(message);
        }
    }

    protected ArrayList<Component> components = new ArrayList<>();

}
