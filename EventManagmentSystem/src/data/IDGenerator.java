package data;

import java.util.HashMap;

import managers.Identifiable;

public class IDGenerator {
    private static final HashMap<String, Integer> ID_HASH_MAP = new HashMap<>();

    public static String generate(String prefix) {
        int count = ID_HASH_MAP.getOrDefault(prefix, 0)+1;
        ID_HASH_MAP.put(prefix, count);
        return String.format("%s%04d", prefix, count);
    }

    public static String generate(Identifiable obj){
        return generate(obj.getIdPrefix());
    }
}
