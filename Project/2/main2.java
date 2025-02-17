import java.util.*;
public class main2 {
    public static boolean isSameKey(String first, String second) {
        if (first.length() < 2 || second.length() < 2) return false;
        StringBuilder two = new StringBuilder();
        two.append("</");
        for (int i = 1; i < first.length(); i++) {
            two.append(first.charAt(i));
        }
        return two.toString().compareTo(second) == 0;
    }
    public static int counter(String str, String tmp) {
        int c = 0;
        for (int i = 0; i < str.length() - tmp.length(); i++) {
            if (str.substring(i).startsWith(tmp)) c++;
        }
        return c;
    }
    public static HashMap<String, Integer> keyCount(String str) {
        HashMap<String, Integer> ans = new HashMap<>();
        boolean[] in_string = isInString(str);
        int i = 0;
        while (i < str.length()) {
            while (i < str.length() && !(str.charAt(i) == '<' && !in_string[i])) i++;
            if (i >= str.length()) break;
            int startTag = i;
            while (i < str.length() && !(str.charAt(i) == '>' && !in_string[i])) i++;
            int endTag = i;
            String tmpTag = str.substring(startTag, endTag + 1);
            if (ans.containsKey(tmpTag)) ans.remove(tmpTag);
            ans.put(tmpTag, counter(str, tmpTag));
        }
        return ans;
    }
    public static String endKeyOf(String first) {
        StringBuilder two = new StringBuilder();
        two.append("</");
        for (int i = 0; i < first.length(); i++) {
            two.append(first.charAt(i));
        }
        two.append(">");
        return two.toString();
    }
    public static boolean[] isInString(String str) {
        boolean[] ans = new boolean[str.length()];
        boolean insideString = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '"') {
                int backslashCount = 0;
                int j = i - 1;
                while (j >= 0 && str.charAt(j) == '\\') {
                    backslashCount++;
                    j--;
                }
                if (backslashCount % 2 == 0) {
                    insideString = !insideString;
                }
            }
            ans[i] = insideString;
        }
        return ans;
    }
    public static boolean is_valid_string(String str) {
        if (str.length() < 2) return false;
        if (str.charAt(0) != '"' || str.charAt(str.length() - 1) != '"') return false;
        for (int i = 1; i < str.length() - 1; i++) {
            char c = str.charAt(i);
            if ((c == '"' || c == '<' || c == '>') && str.charAt(i - 1) != '\\') return false;
            else if (c == '\\') {
                if (i < str.length() - 1 && (str.charAt(i + 1) == '\\' || str.charAt(i + 1) == '"' || str.charAt(i + 1) == '<' || str.charAt(i + 1) == '>')) {
                    i++;
                    continue;
                }
                else return false;
            }
        }
        return true;
    }
    public static boolean isNumber(String str) {
        if (str.isEmpty()) return false;
        int dotCount = 0, digitCount = 0, i = 0;
        if (str.charAt(0) == '-') i++;
        for (; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '.') {
                dotCount++;
                if (dotCount > 1) return false;
            } else if (Character.isDigit(c)) {
                digitCount++;
            } else {
                return false;
            }
        }
        return digitCount > 0;
    }
    public static boolean is_valid_obj(String str) {
        String suffix = "<", prefix = ">";
        int i = 1;
        while (!isSameKey(suffix, prefix) && i < str.length()) {
            suffix = suffix + str.charAt(i);
            prefix = str.charAt(str.length() - 1 - i) + prefix;
            i++;
        }
        String value = str.substring(suffix.length(), str.length() - prefix.length());
        if (is_valid_string(value)) return true;
        if (isNumber(value)) return true;
        if (value.equals("true") || value.equals("false") || value.isEmpty()) return true;
        else return false;
    }
    public static String keyOf(String object) {
        int i = 1;
        while (i < object.length() && object.charAt(i) != '>') i++;
        return object.substring(1, i);
    }
    public static String valueOf(String object) {
        int start = object.indexOf('>');
        int end = object.lastIndexOf('<');
        if (start == -1 || end == -1 || end <= start) return "";
        return object.substring(start + 1, end);
    }
    public static HashMap<String, String> arraySplitter(String str) {
        HashMap<String, String> array = new HashMap<>();
        HashMap<String, String> nullHash = new HashMap<>();
        int i = 0, counter = 0;
        while (i < str.length()) {
            if (str.charAt(i) != '<') return nullHash;
            int tagEnd = str.indexOf('>', i);
            if (tagEnd == -1) return nullHash;
            String openTag = str.substring(i, tagEnd + 1);
            String key = keyOf(openTag);
            String closeTag = endKeyOf(key);
            int closeIndex = str.indexOf(closeTag, tagEnd + 1);
            if (closeIndex == -1) return nullHash;
            int endOfObj = closeIndex + closeTag.length();
            String fullObj = str.substring(i, endOfObj);
            array.put(openTag + counter, fullObj);
            counter++;
            i = endOfObj;
        }
        return array;
    }
    public static boolean containsKey(String str) {
        boolean[] in_string = new boolean[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '<' && !in_string[i]) return true;
        }
        return false;
    }
    public static char type(String str) {
        if (is_valid_string(str)) return 's';
        if (str.startsWith("\"") && str.endsWith("\"")) return 'e';
        if (str.startsWith("<") && str.endsWith(">")) {
            String key = keyOf(str);
            String closingTag = endKeyOf(key);
            if (!str.endsWith(closingTag)) return 'e';
            return 'O';
        }
        if (isNumber(str)) return 'n';
        if (str.equals("true") || str.equals("false")) return 'b';
        if (str.isEmpty()) return 'l';
        return 'e';
    }
    public static int valueCounter(String str, boolean isRoot) {
        char t = type(str);
        if (t == 'e') return 0;
        if (t == 'O') {
            String inner = valueOf(str).trim();
            boolean hasTag = false;
            boolean[] inStr = isInString(inner);
            for (int i = 0; i < inner.length(); i++) {
                if (inner.charAt(i) == '<' && !inStr[i]) {
                    hasTag = true;
                    break;
                }
            }
            if (!hasTag) {
                if (inner.equals("") || isNumber(inner) || inner.equals("true") || inner.equals("false") || is_valid_string(inner))
                    return 2;
                else return 0;
            }
            HashMap<String, String> children = arraySplitter(inner);
            HashMap<String, List<String>> groups = new HashMap<>();
            for (String child : children.values()) {
                String k = keyOf(child);
                groups.putIfAbsent(k, new ArrayList<>());
                groups.get(k).add(child);
            }
            int sumGroups = 0;
            for (List<String> group : groups.values()) {
                int groupSum = 0;
                for (String child : group) {
                    int vc = valueCounter(child, false);
                    if (vc == 0) return 0;
                    groupSum += vc;
                }
                sumGroups += (groupSum - (group.size() - 1));
            }
            int container = 1;
            return container + sumGroups;
        }
        if (t == 's' || t == 'b' || t == 'n' || t == 'l') return 1;
        return 0;
    }
    public static int valueCounter(String str) {
        return valueCounter(str, true);
    }
    public static boolean checkTagBalance(String xml) {
        boolean[] inStr = isInString(xml);
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < xml.length(); i++) {
            if (xml.charAt(i) == '<' && !inStr[i]) {
                int j = xml.indexOf('>', i);
                if (j == -1) return false;
                String tag = xml.substring(i, j + 1);
                if (tag.startsWith("</")) {
                    String tagName = tag.substring(2, tag.length() - 1);
                    if (stack.isEmpty() || !stack.pop().equals(tagName)) return false;
                } else {
                    String tagName = tag.substring(1, tag.length() - 1);
                    stack.push(tagName);
                }
                i = j;
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder inputData = new StringBuilder();
        while (scanner.hasNextLine()) {
            inputData.append(scanner.nextLine().trim());
        }
        scanner.close();
        String xml = inputData.toString();
        if (!(xml.startsWith("<") && xml.endsWith(">"))) {
            System.out.println(0);
            return;
        }
        String rootKey = keyOf(xml);
        String rootClosing = endKeyOf(rootKey);
        if (!xml.endsWith(rootClosing)) {
            System.out.println(0);
            return;
        }
        String innerRoot = valueOf(xml);
        if (innerRoot.contains("><") && !innerRoot.trim().startsWith("<")) {
            System.out.println(0);
            return;
        }
        if (!checkTagBalance(xml)) {
            System.out.println(0);
            return;
        }
        System.out.println(valueCounter(xml));
    }
}
