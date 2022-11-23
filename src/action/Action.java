package action;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class Action {
    /**
     * Performs the action specific to this object
     */
    public ObjectNode perform() {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("command", getCommand());
        return objectNode;
    }

    /**
     * @return This action's command
     */
    public abstract String getCommand();
}
