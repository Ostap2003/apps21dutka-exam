package json;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private List<JsonPair> pairs;

    public JsonObject(JsonPair... jsonPairs) {
        pairs = new ArrayList<JsonPair>();
        Collections.addAll(pairs, jsonPairs);
    }

    @Override
    public String toJson() {
        StringBuilder jsonOut = new StringBuilder();
        Iterator<JsonPair> jsonPairIterator = pairs.iterator();
        while (jsonPairIterator.hasNext()) {
            JsonPair pair = jsonPairIterator.next();
            jsonOut.append(pair.key + ":" + pair.value.toJson());
            if (jsonPairIterator.hasNext()) {
                jsonOut.append(", ");
            }
        }
        return "{" + jsonOut.toString() + "}";
    }

    private JsonPair findPair(String key) {
        for (JsonPair p : pairs) {
            if (p.key.equals(key)) {
                return p;
            }
        }
        return null;
    }

    public void add(JsonPair jsonPair) {
        pairs.add(jsonPair);
    }

    public Json find(String name) {
        JsonPair foundPair = findPair(name);
        if (foundPair != null) {
            return foundPair.value;
        }
        return null;
    }

    public JsonObject projection(String... names) {

        JsonObject jsonObjectOut = new JsonObject();
        for (String name : names) {
            JsonPair pair = findPair(name);
            if (pair != null) {
                jsonObjectOut.add(pair);
            }
        }
        return jsonObjectOut;
    }
}
