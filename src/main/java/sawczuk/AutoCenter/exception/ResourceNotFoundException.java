package sawczuk.AutoCenter.exception;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String entity, Object... searchParamsMap) {
        super(ResourceNotFoundException.generateMessage(entity, toMap(String.class, Object.class, searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, Object> searchParams) {
        String message = StringUtils.capitalize(entity) + " was not found";
        if (searchParams != null && !searchParams.isEmpty())
            message += " for parameters " + searchParams;
        return message;
    }

    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}