// import java.util.*;

// public class tmp3 {
//     public static String remove(String str, int r) {
//         StringBuilder tmp = new StringBuilder();
//         for (int i = 0; i < r; i++) {
//             tmp.append(str.charAt(i));
//         }
//         for (int i = r; i < str.length(); i++) {
//             tmp.append(str.charAt(i));
//         }
//         return tmp.toString();
//     }

//     public static boolean[] isInString(String json) {
//         boolean[] ans = new boolean[json.length()];
//         boolean insideString = false;
//         // شروع از ایندکس 0
//         for (int i = 0; i < json.length(); i++) {
//             if (json.charAt(i) == '"' && (i == 0 || json.charAt(i - 1) != '\\')) {
//                 insideString = !insideString;
//             }
//             ans[i] = insideString;
//         }
//         return ans;
//     }

//     public static ArrayList<String> arraySplitter(String array) { 
//         ArrayList<String> ans = new ArrayList<>(); 
//         boolean[] in_string = isInString(array); 
        
//         // حذف براکت‌های بیرونی آرایه
//         if (array.length() >= 2 && array.charAt(0) == '[' && array.charAt(array.length() - 1) == ']') {
//             array = array.substring(1, array.length() - 1).trim(); 
//         }
        
//         int i = 0;
//         while (i < array.length()) {
//             if (array.charAt(i) == ' ') { 
//                 i++; 
//                 continue; 
//             }
//             if (in_string[i]) {
//                 int startString = i; 
//                 while (i < array.length() && in_string[i]) i++; 
//                 ans.add(array.substring(startString, i));
//             }
//             else if (array.charAt(i) == '{' || array.charAt(i) == '[') {
//                 int start = i;
//                 char open = array.charAt(i);
//                 char close = (open == '{') ? '}' : ']';
//                 int balance = 1;
//                 i++;
//                 while (i < array.length() && balance > 0) {
//                     if (!in_string[i]) {
//                         if (array.charAt(i) == open) balance++;
//                         if (array.charAt(i) == close) balance--;
//                     }
//                     i++;
//                 }
//                 ans.add(array.substring(start, i)); 
//             }
//             else {
//                 int start = i;
//                 while (i < array.length() && array.charAt(i) != ',' && array.charAt(i) != ']') i++; 
//                 ans.add(array.substring(start, i).trim()); 
//             }
//             if (i < array.length() && array.charAt(i) == ',') i++;
//         }
//         return ans; 
//     }

//     public static HashMap<String, String> objectSplitter(String object) { 
//         HashMap<String, String> ans = new HashMap<>();
//         HashMap<String, String> nullHash = new HashMap<>();

//         if (object.length() < 2 || object.charAt(0) != '{' || object.charAt(object.length() - 1) != '}') {
//             return nullHash;
//         }
        
//         boolean[] in_string = isInString(object);
//         int i = 1; 
//         while (i < object.length() - 1) { 
//             // حذف فضاهای خالی قبل از کلید
//             while (i < object.length() - 1 && Character.isWhitespace(object.charAt(i))) i++;
//             if (i >= object.length() - 1) break;
//             if (object.charAt(i) != '"') return nullHash;
            
//             int startKey = ++i;
//             while (i < object.length() - 1 && in_string[i]) i++;
//             if (i >= object.length() - 1 || object.charAt(i) != '"') return nullHash;
//             int endKey = i; // index of closing quote of key
//             String tmpKey = object.substring(startKey, endKey);
//             i++; // skip closing quote
            
//             while (i < object.length() - 1 && Character.isWhitespace(object.charAt(i))) i++;
//             if (i >= object.length() - 1 || object.charAt(i) != ':') return nullHash;
//             i++; // skip colon
            
//             while (i < object.length() - 1 && Character.isWhitespace(object.charAt(i))) i++;
//             int startValue = i;
//             if (i >= object.length() - 1) return nullHash;
            
//             if (object.charAt(i) == '"') {
//                 i++; // skip opening quote of value
//                 while (i < object.length() - 1 && (in_string[i] || (object.charAt(i) == '\\' && i+1 < object.length()))) i++;
//                 if (i >= object.length() - 1 || object.charAt(i) != '"') return nullHash;
//                 i++; // skip closing quote
//             } else if (object.charAt(i) == '{' || object.charAt(i) == '[') {
//                 char open = object.charAt(i);
//                 char close = (open == '{') ? '}' : ']';
//                 int balance = 1;
//                 i++;
//                 while (i < object.length() - 1 && balance > 0) {
//                     if (!in_string[i]) {
//                         if (object.charAt(i) == open) balance++;
//                         if (object.charAt(i) == close) balance--;
//                     }
//                     i++;
//                 }
//                 if (balance != 0) return nullHash;
//             } else {
//                 while (i < object.length() - 1 && object.charAt(i) != ',' && object.charAt(i) != '}') i++;
//             }
//             int endValue = i;
//             String tmpValue = object.substring(startValue, endValue).trim();
//             ans.put(tmpKey, tmpValue);
            
//             while (i < object.length() - 1 && Character.isWhitespace(object.charAt(i))) i++;
//             if (i < object.length() - 1 && object.charAt(i) == ',') i++;
//         }
//         return ans;
//     }

//     public static boolean isNumber(String str) {
//         if (str.isEmpty()) return false; 
//         int dotCount = 0; 
//         int digitCount = 0;

//         if (str.charAt(0) == '.') dotCount++;
//         else if (Character.isDigit(str.charAt(0))) digitCount++; 
//         else if (str.charAt(0) != '-') return false; 

//         for (int i = 0; i < str.length(); i++) { 
//             char c = str.charAt(i);
//             if (c == '.')  {
//                 dotCount++; 
//                 if (dotCount > 1) return false; 
//             } else if (Character.isDigit(c)) {
//                 digitCount++;
//             } else {
//                 return false;
//             }
//         }
//         return digitCount > 0;
//     }

//     public static char type(String str) {
//         if (str.startsWith("{") && str.endsWith("}")) return 'O'; // object
//         if (str.startsWith("[") && str.endsWith("]")) return 'A'; // Array
//         if (str.startsWith("\"") && str.endsWith("\"")) return 's'; // string
//         if (str.compareTo("true") == 0 || str.compareTo("false") == 0) return 'b'; // boolean
//         if (str.compareTo("null") == 0) return 'l'; 
//         if (isNumber(str)) return 'n'; 
//         return 'e';
//     }

//     public static int valueCounter(String str) {
//         int ans = 0; 
//         char t = type(str);
//         if (t == 'O') {
//             HashMap<String, String> object = objectSplitter(str);
//             for (String value : object.values()) {
//                 ans++;
//                 ans += valueCounter(value);
//             }
//         }
//         else if (t == 'A') {
//             ans++;
//             ArrayList<String> array = arraySplitter(str);
//             for (String value : array) { 
//                 ans++;
//                 ans += valueCounter(value);
//             }
//         }
//         else if (t != 'e') {
//             ans = 1; 
//         }
//         return ans; 
//     }



import java.util.*;

public class tmp {
    public static String remove(String str, int r) {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < r; i++) {
            tmp.append(str.charAt(i));
        }
        for (int i = r; i < str.length(); i++) {
            tmp.append(str.charAt(i));
        }
        return tmp.toString();
    }

    public static boolean[] isInString(String json) {
        boolean[] ans = new boolean[json.length()];
        boolean insideString = false;
        for (int i = 0; i < json.length(); i++) {
            if (json.charAt(i) == '"' && (i == 0 || json.charAt(i - 1) != '\\')) {
                insideString = !insideString;
            }
            ans[i] = insideString;
        }
        return ans;
    }

    public static ArrayList<String> arraySplitter(String array) { 
        ArrayList<String> ans = new ArrayList<>(); 
        boolean[] in_string = isInString(array); 

        // حذف براکت‌های بیرونی آرایه
        if (array.length() >= 2 && array.charAt(0) == '[' && array.charAt(array.length() - 1) == ']') {
            array = array.substring(1, array.length() - 1); 
        }
        
        int i = 0;
        while (i < array.length()) {
            if (array.charAt(i) == ' ') { 
                i++; 
                continue; 
            }
            if (in_string[i]) {
                int startString = i; 
                while (i < array.length() && in_string[i]) i++; 
                ans.add(array.substring(startString, i+1));
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
                ans.add(array.substring(start, i+1)); 
            }
            else {
                int start = i;
                while (i < array.length() && array.charAt(i) != ',' && array.charAt(i) != ']') i++; 
                ans.add(array.substring(start, i+1)); 
            }
            if (i < array.length() && array.charAt(i) == ',') i++;
        }
        
        return ans; 
    }

    public static HashMap<String, String> objectSplitter(String object) { 
        HashMap<String, String> ans = new HashMap<>();
        HashMap<String, String> nullHash = new HashMap<>();

        if (object.compareTo("{}") == 0 || object.length() < 2 || object.charAt(0) != '{' || object.charAt(object.length() - 1) != '}') {
            return nullHash;
        }
        
        boolean[] in_string = isInString(object);
        int i = 1; 
        while (i < object.length() - 1) { 
            while (i < object.length() - 1 && object.charAt(i)==' ') i++;
            if (i >= object.length() - 1) break;
            if (object.charAt(i) != '"') return nullHash;
            
            int startKey = ++i;
            while (i < object.length() - 1 && in_string[i]) i++;
            if (i >= object.length() - 1 || object.charAt(i) != '"') return nullHash;
            int endKey = i;
            StringBuilder keyBuilder = new StringBuilder(); 
            keyBuilder.append('"'); keyBuilder.append(object.substring(startKey, endKey+1)); keyBuilder.append('"');
            String tmpKey = keyBuilder.toString(); 
            i++; 
            
            while (i < object.length() - 1 && Character.isWhitespace(object.charAt(i))) i++;
            if (i >= object.length() - 1 || object.charAt(i) != ':') return nullHash;
            i++; // رد کردن :
            
            while (i < object.length() - 1 && Character.isWhitespace(object.charAt(i))) i++;
            int startValue = i;
            if (i >= object.length() - 1) return nullHash;
            
            if (object.charAt(i) == '"') {
                i++; // رد کردن نقل قول ابتدایی\n                while (i < object.length() - 1 && (in_string[i] || (object.charAt(i) == '\\' && i+1 < object.length()))) i++;
                if (i >= object.length() - 1 || object.charAt(i) != '"') return nullHash;
                i++; // رد کردن نقل قول پایانی
            } else if (object.charAt(i) == '{' || object.charAt(i) == '[') {
                char open = object.charAt(i);
                char close = (open == '{') ? '}' : ']';
                int balance = 1;
                i++;
                while (i < object.length() - 1 && balance > 0) {
                    if (!in_string[i]) {
                        if (object.charAt(i) == open) balance++;
                        if (object.charAt(i) == close) balance--;
                    }
                    i++;
                }
                if (balance != 0) return nullHash;
            } else {
                while (i < object.length() - 1 && object.charAt(i) != ',' && object.charAt(i) != '}') i++;
            }
            int endValue = i;
            String tmpValue = object.substring(startValue, endValue+1);
            ans.put(tmpKey, tmpValue);
            
            while (i < object.length() - 1 && Character.isWhitespace(object.charAt(i))) i++;
            if (i < object.length() - 1 && object.charAt(i) == ',') i++;
        }
        return ans;
    }
    
    public static boolean isNumber(String str) {
        if (str.isEmpty()) return false; 
        int dotCount = 0; 
        int digitCount = 0;

        if (str.charAt(0) == '.') dotCount++;
        else if (Character.isDigit(str.charAt(0))) digitCount++; 
        else if (str.charAt(0) != '-') return false; 

        for (int i = 0; i < str.length(); i++) { 
            char c = str.charAt(i);
            if (c == '.')  {
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

    public static char type(String str) {
        if (str.startsWith("{") && str.endsWith("}")) {
            if (objectSplitter(str).isEmpty() && str != "{}") return 'e'; 
            return 'O'; // object
        }
        if (str.startsWith("[") && str.endsWith("]")) return 'A'; // Array
        if (str.startsWith("\"") && str.endsWith("\"") && is_valid_string(str)) return 's'; // string
        if (str.compareTo("true") == 0 || str.compareTo("false") == 0) return 'b'; // boolean
        if (str.compareTo("null") == 0) return 'l'; 
        if (isNumber(str)) return 'n'; 
        return 'e';
    }

    public static boolean is_valid(String str) {
        if (type(str) == 'O') return is_valid_obj(str); 
        if (type(str) == 's') return is_valid_string(str); 
        if (type(str) == 'A') return is_valid_array(str); 
        if (type(str) == 'e') return false; 
        if (type(str) == 'n' || type(str) == 'l' || type(str) == 'b') return true; 
        else return false; 
    }

    public static boolean is_valid_array(String arr) {
        ArrayList<String> array = new ArrayList<>();
        array = arraySplitter(arr); 
        boolean tmp = true; 
        for (String value : array) {
            tmp = tmp && is_valid(value); 
        }
        return tmp; 
    }

    public static boolean is_valid_obj(String obj) {
        if (type(obj) == 'e') return false; 
        HashMap<String, String> object = new HashMap<>();
        object = objectSplitter(obj); 
        boolean tmp = true; 
        for (String key : object.keySet()) {
            tmp = tmp && is_valid_string(key) && is_valid(object.get(key)); 
        }
        return tmp; 
    }
    public static boolean is_valid_string(String str) { 
        if (str.charAt(0) != '"' || str.charAt(str.length()-1)!='"') return false; 
        for (int i = 0; i<str.length()-1; i++) {
            if ((str.charAt(i) == '"') && str.charAt(i-1) != '\\') return false; 
            else if (str.charAt(i) == '\\') {
                if (str.charAt(i+1) == '\\' || str.charAt(i+1) == '"') {
                    i++; 
                    continue; 
                }
                else return false; 
            }
        }
        return true; 
    }

    // تابع شمارش JSONValueها طبق منطق: 
    // برای JSONObject: count = 1 + sum(count(value) for each member)
    // برای JSONArray: count = 1 + sum(count(element) for each element)
    // برای مقادیر ساده: count = 1
    // اما تنها اگر ورودی اصلی یک JSONObject باشد؛ در غیر اینصورت 0.

    public static int valueCounter(String str) {
        char t = type(str);
        if (t == 'O') {
            int c = 1;
            HashMap<String, String> obj = objectSplitter(str);
            for (String value : obj.values()) {
                char vt = type(value);
                if (vt == 'O' || vt == 'A') {
                    c += valueCounter(value);
                } else if (vt != 'e') {
                    c += 1;
                }
            }
            return c;
        }
        else if (t == 'A') {
            int c = 1;
            ArrayList<String> arr = arraySplitter(str);
            for (String elem : arr) {
                char et = type(elem);
                if (et == 'O' || et == 'A') {
                    c += valueCounter(elem);
                } else if (et != 'e') {
                    c += 1;
                }
            }
            return c;
        }
        if (t=='e') {
            System.out.println(0);
            System.exit(0);
            return 0; 
        }
        else {
            return 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String json = scanner.nextLine();
        boolean[] is_in_string = isInString(json);
        StringBuilder new_str = new StringBuilder();
        for (int i = 0; i < json.length(); i++) {
            if (json.charAt(i) != ' ')
                new_str.append(json.charAt(i));
            else if (is_in_string[i])
                new_str.append(json.charAt(i));
        }
        json = new_str.toString();
        if (!(json.startsWith("{") && json.endsWith("}"))) {
            System.out.println(0);
            return;
        }

        if (!is_valid(json)) System.out.println("00");
        else { System.out.println(valueCounter(json));
        System.out.println(objectSplitter(json));}
        scanner.close();
    }
}
