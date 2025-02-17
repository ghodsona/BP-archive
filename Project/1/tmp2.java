import java.util.*;

public class tmp2 {
    public static String remove (String str, int r) {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i<r; i++) {
            tmp.append(str.charAt(i));
        }
        for (int i = r; i<str.length(); i++) {
            tmp.append(str.charAt(i));
        }
        return tmp.toString();
    }

    public static boolean[] isInString(String json) {
        boolean[] ans = new boolean[json.length()]; 
        int i = 1;
        while (i<=json.length()-1) {
            if (json.charAt(i) == '"') { // start of a string
                ans[i] = true; 
                while (i<=json.length()-1) {
                    i++;
                    ans[i] = true; 
                    if (json.charAt(i) == '"' && json.charAt(i-1) != '\\') {
                        ans[i] = true; 
                        break; // string ended
                    }
                }
            }
            i++;
        }
        return ans; 
    }

    public static ArrayList<String> arraySplitter(String array) { 
        ArrayList<String> ans = new ArrayList<>(); 
        boolean[] in_string = isInString(array); 

        array = array.substring(1, array.length() - 1).trim(); 

        int i = 0;
        while (i < array.length()) {
            if (array.charAt(i) == ' ') { 
                i++; 
                continue; 
            }

            if (in_string[i]) {
                int startString = i; 
                i++;
                while (i < array.length() && in_string[i]) i++; 
                ans.add(array.substring(startString, i + 1)); 
                i++; 
            }
            else if (array.charAt(i) == '{' || array.charAt(i) == '[') {
                int start = i;
                char open = array.charAt(i);
                char close = (open == '{') ? '}' : ']';
                int balance = 1;
                i++;
                while (i < array.length() && balance > 0) {
                    if (!in_string[i]) {
                        if (array.charAt(i) == open) balance++;
                        if (array.charAt(i) == close) balance--;
                    }
                    i++;
                }
                ans.add(array.substring(start, i)); 
            }
            else {
                int start = i;
                while (i < array.length() && array.charAt(i) != ',' && array.charAt(i) != ']') i++; 
                ans.add(array.substring(start, i).trim()); 
            }

            if (i < array.length() && array.charAt(i) == ',') i++;
        }
        
        return ans; 
    }

    public static HashMap<String, String> objectSplitter(String object) { 
        HashMap<String, String> ans = new HashMap<>();
        HashMap<String, String> nullHash = new HashMap<>(); 

        boolean[] in_string = isInString(object);
        int i = 1; 
        int startKey = -1; 
        int endKey = -1; 
        String tmpKey = ""; 
        String tmpValue = "";
        while(i<object.length()-1) { 
            while (i<object.length()-1) {
                if (in_string[i]) {
                    startKey = i+1; 
                    while (in_string[i]) i++;
                    endKey = i-2; 
                    break;
                }
                else i++; 
            }
            if (object.charAt(i) == ':') tmpKey = object.substring(startKey, endKey); 
            else {
                return nullHash;
            }
            i++;

            int startValue = endKey+3; 
            int endValue; 
            if (object.charAt(i) == '"') {
                i++;
                while (type(object.substring(endKey+2, i)) != 's') i++; 
                endValue = i-1; 
                tmpValue = object.substring(startValue, endValue); 
                i++;
            }
            else if (object.charAt(i) == '{' || object.charAt(i) == '[') {
                int startCount = 1; int endCount = 0; 
                while (startCount != endCount) {
                    i++; 
                    if (object.charAt(i) == '[' || object.charAt(i) == '{') startCount++; 
                    else if (object.charAt(i) == ']' || object.charAt(i) == '}') endCount++;
                }
                endValue = i; 
                tmpValue = object.substring(startValue, endValue); 
            }
            else {
                while (object.charAt(i) != ',') i++; 
                endValue = i-1; 
                tmpValue = object.substring(startValue, endValue); 
                if (!(type(tmpValue) == 'b' || type(tmpValue) == 'n' || type(tmpValue) == 'l')) {
                    return nullHash; 
                }
            }
            if (ans.containsKey(tmpKey)) {
                ans.remove(tmpKey); 
            }
            ans.put(tmpKey, tmpValue); 
            if (object.charAt(i) == '}') return ans; 
            else if (object.charAt(i) == ',') i++; 
            else return nullHash;

        }
        return ans;
    }
    
    public static boolean isNumber(String str) {
        if (str.isEmpty()) return false; 
        int dotCount = 0; 
        int digitCount = 0;

        if (str.charAt(0) == '.') dotCount++;
        else if (Character.isDigit(str.charAt(0))) digitCount++; 
        else if (!(str.charAt(0) == '-')) return false; 

        for (int i = 0; i<str.length()-1; i++) { 
            if (str.charAt(i) == '.')  {
                dotCount++; 
                if (dotCount>1) return false; 
                continue;
            }
            if (!Character.isDigit(str.charAt(i))) return false; 
            if (Character.isDigit(str.charAt(i))) digitCount++; 
        }

        if (digitCount == 0) return false; 
        return true; 
    }

    public static char type(String str) {
        if (str.startsWith("{") && str.endsWith("}")) return 'O'; // object
        if (str.startsWith("[") && str.endsWith("]")) return 'A'; // Array
        if (str.startsWith("\"") && str.endsWith("\"")) return 's'; // string
        if (str.compareTo("true")==0 || str.compareTo("false")==0) return 'b'; // boolean
        if (str.compareTo("null")==0) return 'l'; 
        if (isNumber(str)) return 'n'; 
        return 'e';
    }

    public static int valueCounter(String str) {    
        int ans = 0; 
        if (type(str) == 'O') {
            HashMap<String, String> object = new HashMap<>(); 
            if (object.isEmpty()) {
                System.out.println(0);
                return 0; 
            }
            object = objectSplitter(str); 
            for (String value : object.values()) {
                System.out.println(value);
                ans++; 
                ans += valueCounter(value); 
            }
        }
        else if (type(str) == 'e') return 0; 
        else if (type(str) == 'A') {
            ans++;
            ArrayList<String> array = arraySplitter(str);
            for (String value : array) { 
                System.out.println(value);
                ans++;
                ans+=valueCounter(value); 
            }
        }
        else {
            return 1; 
        }
        return ans; 
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String json = scanner.nextLine();
        if (!(json.startsWith("{") && json.endsWith("}"))) {
            System.out.println(0);
            return; 
        }

        boolean[] is_in_string = isInString(json);
        for (int i = 0; i<json.length(); i++) {
            if (json.charAt(i)==' '  && !is_in_string[i]) json  = remove(json, i); 
        }

        System.out.println(valueCounter(json));
        scanner.close();
    }
}