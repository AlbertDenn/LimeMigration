package Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Response {

    private HashMap<String, Object> response = new HashMap<>();

    public Response() {
        reset();
    }

    public HashMap<String, Object> getResponse() {
        return response;
    }

    public Response addObject(String key, Object object) {
        response.put(key, object);
        return this;
    }

    public Response reset() {
        response.clear();
        response.put("timestamp", new Date());
        return this;
    }

    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();

        for (Map.Entry<String, Object> entry: response.entrySet()) {
            string.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
        }

        return string.reverse().deleteCharAt(0).reverse().toString();

    }

}