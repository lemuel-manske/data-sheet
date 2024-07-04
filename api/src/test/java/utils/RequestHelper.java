package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public final class RequestHelper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readAs(Class<T> objectToRead, String jsonContent) throws JsonProcessingException {
        return mapper.reader().forType(objectToRead).readValue(jsonContent);
    }

    public static <T> String writeAsString(T objectToRead) throws JsonProcessingException {
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        return ow.writeValueAsString(objectToRead);
    }
}
