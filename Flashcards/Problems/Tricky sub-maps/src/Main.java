import java.util.*;

class MapUtils {
    public static Map<Integer, String> getSubMap(TreeMap<Integer, String> map) {
        Map<Integer, String> result = new TreeMap<>();
        if(map.firstKey() % 2 != 0) {
            for(int i = 0, key = map.firstKey(); i <= 4; i++, key++) {
                String value = map.get(key);
                if(value != null){
                    result.put(new Integer(key), value);
                }
            }
        } else {
            for(int i = 0, key = map.lastKey(); i <= 4; i++, key--) {
                String value = map.get(key);
                if(value != null){
                    result.put(new Integer(key), value);
                }
            }
        }
        Map<Integer, String> reversedResult = new TreeMap<>(Collections.reverseOrder());
        reversedResult.putAll(result);
        return reversedResult;
    }
}

/* Do not modify code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeMap<Integer, String> map = new TreeMap<>();
        Arrays.stream(scanner.nextLine().split("\\s")).forEach(s -> {
            String[] pair = s.split(":");
            map.put(Integer.parseInt(pair[0]), pair[1]);
        });

        Map<Integer, String> res = MapUtils.getSubMap(map);
        res.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}