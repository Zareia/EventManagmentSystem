package data;

import java.util.HashMap;

import managers.Identifiable;

public class IDGenerator {
    private static final HashMap<String, Integer> ID_HASH_MAP = new HashMap<>();

    // gets a unique prefix for each class ex: 
    // Event class: EVT
    // Admin class: ADM
    // then searches ID_HASH_MAP using the prefix to see how many IDs of that class are created then creates a new id
    // with the prefix + count + 1 so that every object gets a unique ID
    public static String generate(String prefix) {
        int count = ID_HASH_MAP.getOrDefault(prefix, 0)+1;
        ID_HASH_MAP.put(prefix, count);
        return String.format("%s%04d", prefix, count);
    }

    public static String generate(Identifiable obj){
        return generate(obj.getIdPrefix());
    }
}
