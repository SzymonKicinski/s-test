package sawczuk.AutoCenter.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class InvalidRequestParameterException extends Exception {

    public InvalidRequestParameterException(Object... searchParamsMap) {
        super(InvalidRequestParameterException.generateMessage(toMap(String.class, Object.class, searchParamsMap)));
    }

    private static String generateMessage(Map<String, Object> searchParams) {
        return "Parameter is null or empty: " + searchParams;
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