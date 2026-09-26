import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        StringBuilder currentKey = new StringBuilder();
        boolean inBracket = false;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                inBracket = true;
            } else if (c == ')') {
                result.append(map.getOrDefault(currentKey.toString(), "?"));
                currentKey.setLength(0);
                inBracket = false;
            } else {
                if (inBracket) {
                    currentKey.append(c);
                } else {
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }
}