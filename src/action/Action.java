package action;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class Action {
    /**
     * Performs the action specific to the class
     *
     * @return Json Node containing command output
     */
    public ObjectNode perform() {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("command", getCommand());
        return objectNode;
    }

    /**
     * @return This action's command as a string
     */
    public abstract String getCommand();
}
