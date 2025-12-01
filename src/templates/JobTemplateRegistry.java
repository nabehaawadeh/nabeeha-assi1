package templates;

import templates.JobPrototype;

import java.util.HashMap;
import java.util.Map;

public class JobTemplateRegistry {

    private static final Map<String, JobPrototype> registry = new HashMap<>();

    public static void register(String key, JobPrototype prototype) {
        registry.put(key, prototype);
    }

    public static JobPrototype get(String key) {
        return registry.get(key);
    }
}