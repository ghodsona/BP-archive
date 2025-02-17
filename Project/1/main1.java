import java.util.*;

public class main1 {
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
    
    public static HashMap<String, String> objectSplitter(String object) {
        HashMap<String, String> ans = new HashMap<>();
        HashMap<String, String> nullHash = new HashMap<>();
        
        if (object.length() < 2 || object.charAt(0) != '{' || object.charAt(object.length() - 1) != '}') {
            return nullHash;
        }
        
        boolean[] inString = isInString(object);
        int i = 1;
        while (i < object.length() - 1) {
            while (i < object.length() - 1 && Character.isWhitespace(object.charAt(i))) i++;
            if (i >= object.length() - 1) break;
            if (object.charAt(i) != '"') return nullHash;
            
            int startKey = ++i;
            while (i < object.length() - 1 && inString[i]) i++;
            if (i >= object.length() - 1 || object.charAt(i) != '"') return nullHash;
            int endKey = i;
            // کلیدها را به صورت رشته (شامل نقل قول) نگهداری می‌کنیم
            String tmpKey = "\"" + object.substring(startKey, endKey) + "\"";
            i++; // رد کردن نقل قول پایانی
            
            while (i < object.length() - 1 && Character.isWhitespace(object.charAt(i))) i++;
            if (i >= object.length() - 1 || object.charAt(i) != ':') return nullHash;
            i++; // رد کردن :
            
            while (i < object.length() - 1 && Character.isWhitespace(object.charAt(i))) i++;
            int startValue = i;
            if (i >= object.length() - 1) return nullHash;
            
            if (object.charAt(i) == '"') {
                i++; // رد کردن نقل قول ابتدایی
                while (i < object.length() - 1 && (inString[i] || (object.charAt(i) == '\\' && i+1 < object.length()))) i++;
                if (i >= object.length() - 1 || object.charAt(i) != '"') return nullHash;
                i++; // رد کردن نقل قول پایانی
            } else if (object.charAt(i) == '{' || object.charAt(i) == '[') {
                char open = object.charAt(i);
                char close = (open == '{') ? '}' : ']';
                int balance = 1;
                i++;
                while (i < object.length() - 1 && balance > 0) {
                    if (!inString[i]) {
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
            String tmpValue = object.substring(startValue, endValue).trim();
            ans.put(tmpKey, tmpValue);
            
            while (i < object.length() - 1 && Character.isWhitespace(object.charAt(i))) i++;
            if (i < object.length() - 1 && object.charAt(i) == ',') i++;
        }
        return ans;
    }
    
    public static ArrayList<String> arraySplitter(String array) { 
        ArrayList<String> ans = new ArrayList<>(); 
        boolean[] in_string = isInString(array);
        
        // حذف براکت‌های بیرونی آرایه
        if (array.length() >= 2 && array.charAt(0) == '[' && array.charAt(array.length() - 1) == ']') {
            array = array.substring(1, array.length() - 1).trim();
            in_string = isInString(array);
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
                ans.add(array.substring(startString, i));
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
            if (objectSplitter(str).isEmpty() && !str.equals("{}")) return 'e'; 
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
        ArrayList<String> array = arraySplitter(arr); 
        boolean tmp = true; 
        for (String value : array) {
            tmp = tmp && is_valid(value); 
        }
        return tmp; 
    }
    
    public static boolean is_valid_obj(String obj) {
        if (type(obj) == 'e') return false; 
        HashMap<String, String> object = objectSplitter(obj); 
        boolean tmp = true; 
        for (String key : object.keySet()) {
            tmp = tmp && is_valid_string(key) && is_valid(object.get(key)); 
        }
        return tmp; 
    }
    
    public static boolean is_valid_string(String str) { 
        if (str.charAt(0) != '"' || str.charAt(str.length()-1) != '"') return false; 
        for (int i = 1; i < str.length()-1; i++) {
            if (str.charAt(i) == '"' && str.charAt(i-1) != '\\') return false; 
            else if (str.charAt(i) == '\\') {
                if (i+1 < str.length() && (str.charAt(i+1) == '\\' || str.charAt(i+1) == '"')) {
                    i++; 
                    continue; 
                }
                else return false; 
            }
        }
        return true; 
    }
    
    // اگر {}، خالی باشد، به عنوان 1 در نظر گرفته می‌شود
    public static int valueCounter(String str) {
        char t = type(str);
        if (t != 'O') return 0;
        if (str.equals("{}")) return 1;
        int total = 0;
        HashMap<String, String> obj = objectSplitter(str);
        for (String value : obj.values()) {
            char vt = type(value);
            if (vt == 'O') {
                total += 1 + valueCounter(value);
            } else if (vt == 'A') {
                total += 1 + arrayValueCounter(value);
            } else if (vt == 'l') {
                total += 2;
            } else if (vt != 'e') {
                total += 1;
            }
        }
        return total;
    }
    
    public static int arrayValueCounter(String str) {
        if (str.equals("[]")) return 1;
        int total = 0;
        ArrayList<String> arr = arraySplitter(str);
        for (String elem : arr) {
            char et = type(elem);
            if (et == 'O') {
                total += 1 + valueCounter(elem);
            } else if (et == 'A') {
                total += 1 + arrayValueCounter(elem);
            } else if (et == 'l') {
                total += 2;
            } else if (et != 'e') {
                total += 1;
            }
        }
        return total;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String json = scanner.nextLine().trim();
        scanner.close();
        
        // اگر ورودی اصلی یک JSONObject نباشد، خروجی 0
        if (!(json.startsWith("{") && json.endsWith("}"))) {
            System.out.println(0);
            return;
        }
        
        if (!is_valid(json)) System.out.println(0);
        else System.out.println(valueCounter(json));
    }
}


// import java.util.*;

// public class main1 {
//     public static boolean[] isInString(String json) {
//         boolean[] ans = new boolean[json.length()];
//         boolean insideString = false;
//         for (int i = 0; i < json.length(); i++) {
//             if (json.charAt(i) == '"' && (i == 0 || json.charAt(i - 1) != '\\')) {
//                 insideString = !insideString;
//             }
//             ans[i] = insideString;
//         }
//         return ans;
//     }

//     public static HashMap<String, List<String>> objectSplitter(String object) {
//         HashMap<String, List<String>> ans = new HashMap<>();
//         HashMap<String, List<String>> nullHash = new HashMap<>();

//         if (object.length() < 2 || object.charAt(0) != '{' || object.charAt(object.length() - 1) != '}') {
//             return nullHash;
//         }

//         boolean[] inString = isInString(object);
//         int i = 1;
//         while (i < object.length() - 1) {
//             while (i < object.length() - 1 && object.charAt(i) == ' ') i++;

//             if (i >= object.length() - 1) break;
//             if (object.charAt(i) != '"') return nullHash;

//             int startKey = ++i;
//             while (i < object.length() - 1 && inString[i]) i++;
//             if (i >= object.length() - 1 || object.charAt(i) != '"') return nullHash;
//             String tmpKey = object.substring(startKey, i++);

//             while (i < object.length() - 1 && object.charAt(i) == ' ') i++;
//             if (i >= object.length() - 1 || object.charAt(i) != ':') return nullHash;
//             i++;

//             while (i < object.length() - 1 && object.charAt(i) == ' ') i++;
//             int startValue = i;

//             if (object.charAt(i) == '"') {
//                 i++;
//                 while (i < object.length() - 1 && inString[i]) i++;
//                 if (i >= object.length() - 1 || object.charAt(i) != '"') return nullHash;
//                 i++;
//             } else if (object.charAt(i) == '{' || object.charAt(i) == '[') {
//                 char open = object.charAt(i);
//                 char close = (open == '{') ? '}' : ']';
//                 int balance = 1;
//                 i++;
//                 while (i < object.length() - 1 && balance > 0) {
//                     if (!inString[i]) {
//                         if (object.charAt(i) == open) balance++;
//                         if (object.charAt(i) == close) balance--;
//                     }
//                     i++;
//                 }
//                 if (balance != 0) return nullHash;
//             } else {
//                 while (i < object.length() - 1 && object.charAt(i) != ',' && object.charAt(i) != '}') i++;
//             }

//             String tmpValue = object.substring(startValue, i);
//             ans.putIfAbsent(tmpKey, new ArrayList<>());
//             ans.get(tmpKey).add(tmpValue);

//             while (i < object.length() - 1 && object.charAt(i) == ' ') i++;
//             if (i < object.length() - 1 && object.charAt(i) == ',') i++;
//         }
//         return ans;
//     }

//     public static boolean isNumber(String str) {
//         return str.matches("-?\\d+(\\.\\d+)?");
//     }

//     public static char type(String str) {
//         if (str.startsWith("{") && str.endsWith("}")) return 'O';
//         if (str.startsWith("[") && str.endsWith("]")) return 'A';
//         if (str.startsWith("\"") && str.endsWith("\"")) return 's';
//         if (str.equals("true") || str.equals("false")) return 'b';
//         if (str.equals("null")) return 'l';
//         if (isNumber(str)) return 'n';
//         return 'e';
//     }

//     public static int valueCounter(String str) {
//         int count = 0;
//         if (type(str) == 'O') {
//             HashMap<String, List<String>> object = objectSplitter(str);
//             if (object.isEmpty()) return 0;
//             for (List<String> values : object.values()) {
//                 for (String value : values) {
//                     count += (type(value) == 'l') ? 2 : 1;
//                     count += valueCounter(value);
//                 }
//             }
//         } else if (type(str) != 'e') {
//             count = (type(str) == 'l') ? 2 : 1;
//         }
//         return count;
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         String json = scanner.nextLine().trim();
//         scanner.close();

//         if (!(json.startsWith("{") && json.endsWith("}"))) {
//             System.out.println(0);
//             return;
//         }

//         System.out.println(objectSplitter(json));
//         System.out.println(valueCounter(json));
//     }
// }

// import java.util.*;

// public class main1 {
//     public static boolean[] isInString(String json) {
//         boolean[] ans = new boolean[json.length()];
//         boolean insideString = false;
//         for (int i = 0; i < json.length(); i++) {
//             if (json.charAt(i) == '"' && (i == 0 || json.charAt(i - 1) != '\\')) {
//                 insideString = !insideString;
//             }
//             ans[i] = insideString;
//         }
//         return ans;
//     }

//     public static HashMap<String, List<String>> objectSplitter(String object) {
//         HashMap<String, List<String>> ans = new HashMap<>();
//         HashMap<String, List<String>> nullHash = new HashMap<>();

//         if (object.length() < 2 || object.charAt(0) != '{' || object.charAt(object.length() - 1) != '}') {
//             return nullHash;
//         }

//         boolean[] inString = isInString(object);
//         int i = 1;
//         while (i < object.length() - 1) {
//             while (i < object.length() - 1 && object.charAt(i) == ' ') i++;
//             if (i >= object.length() - 1) break;
//             if (object.charAt(i) != '"') return nullHash;
            
//             int startKey = ++i;
//             while (i < object.length() - 1 && inString[i]) i++;
//             if (i >= object.length() - 1 || object.charAt(i) != '"') return nullHash;
//             String tmpKey = object.substring(startKey, i++);
            
//             while (i < object.length() - 1 && object.charAt(i) == ' ') i++;
//             if (i >= object.length() - 1 || object.charAt(i) != ':') return nullHash;
//             i++;
            
//             while (i < object.length() - 1 && object.charAt(i) == ' ') i++;
//             int startValue = i;
            
//             if (object.charAt(i) == '"') {
//                 i++;
//                 while (i < object.length() - 1 && inString[i]) i++;
//                 if (i >= object.length() - 1 || object.charAt(i) != '"') return nullHash;
//                 i++;
//             } else if (object.charAt(i) == '{' || object.charAt(i) == '[') {
//                 char open = object.charAt(i);
//                 char close = (open == '{') ? '}' : ']';
//                 int balance = 1;
//                 i++;
//                 while (i < object.length() - 1 && balance > 0) {
//                     if (!inString[i]) {
//                         if (object.charAt(i) == open) balance++;
//                         if (object.charAt(i) == close) balance--;
//                     }
//                     i++;
//                 }
//                 if (balance != 0) return nullHash;
//             } else {
//                 while (i < object.length() - 1 && object.charAt(i) != ',' && object.charAt(i) != '}') i++;
//             }
            
//             String tmpValue = object.substring(startValue, i).trim();
//             ans.putIfAbsent(tmpKey, new ArrayList<>());
//             ans.get(tmpKey).add(tmpValue);
            
//             while (i < object.length() - 1 && object.charAt(i) == ' ') i++;
//             if (i < object.length() - 1 && object.charAt(i) == ',') i++;
//         }
//         return ans;
//     }

//     public static boolean isNumber(String str) {
//         return str.matches("-?\\d+(\\.\\d+)?");
//     }

//     public static char type(String str) {
//         if (str.startsWith("{") && str.endsWith("}")) return 'O';
//         if (str.startsWith("[") && str.endsWith("]")) return 'A';
//         if (str.startsWith("\"") && str.endsWith("\"")) return 's';
//         if (str.equals("true") || str.equals("false")) return 'b';
//         if (str.equals("null")) return 'l';
//         if (isNumber(str)) return 'n';
//         return 'e';
//     }

//     public static int valueCounter(String str) {
//         int count = 0;
//         if (type(str) == 'O') {
//             HashMap<String, List<String>> object = objectSplitter(str);
//             if (object.isEmpty()) return 0;
//             for (List<String> values : object.values()) {
//                 for (String value : values) {
//                     count += (type(value) == 'l') ? 2 : 1;
//                     count += valueCounter(value);
//                 }
//             }
//         } else if (type(str) == 'A') {
//             str = str.substring(1, str.length() - 1).trim();
//             if (!str.isEmpty()) {
//                 String[] elements = str.split("(?<!\\\\),");
//                 for (String element : elements) {
//                     count += (type(element.trim()) == 'l') ? 2 : 1;
//                     count += valueCounter(element.trim());
//                 }
//             }
//         } else if (type(str) != 'e') {
//             count = (type(str) == 'l') ? 2 : 1;
//         }
//         return count;
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         String json = scanner.nextLine().trim();
//         scanner.close();

//         if (!(json.startsWith("{") && json.endsWith("}"))) {
//             System.out.println(0);
//             return;
//         }

//         System.out.println(valueCounter(json));
//     }
// }

// import java.util.*;

// public class main1 {
//     public static boolean[] isInString(String json) {
//         boolean[] ans = new boolean[json.length()];
//         boolean insideString = false;
//         for (int i = 0; i < json.length(); i++) {
//             if (json.charAt(i) == '"' && (i == 0 || json.charAt(i - 1) != '\\')) {
//                 insideString = !insideString;
//             }
//             ans[i] = insideString;
//         }
//         return ans;
//     }

//     public static HashMap<String, List<String>> objectSplitter(String object) {
//         HashMap<String, List<String>> ans = new HashMap<>();
//         if (object.length() < 2 || object.charAt(0) != '{' || object.charAt(object.length() - 1) != '}') {
//             return ans;
//         }

//         boolean[] inString = isInString(object);
//         int i = 1;
//         while (i < object.length() - 1) {
//             while (i < object.length() - 1 && object.charAt(i) == ' ') i++;
//             if (i >= object.length() - 1 || object.charAt(i) != '"') return ans;
            
//             int startKey = ++i;
//             while (i < object.length() - 1 && inString[i]) i++;
//             if (i >= object.length() - 1 || object.charAt(i) != '"') return ans;
//             String tmpKey = object.substring(startKey, i++);
            
//             while (i < object.length() - 1 && object.charAt(i) == ' ') i++;
//             if (i >= object.length() - 1 || object.charAt(i) != ':') return ans;
//             i++;
            
//             while (i < object.length() - 1 && object.charAt(i) == ' ') i++;
//             int startValue = i;
            
//             if (object.charAt(i) == '"') {
//                 i++;
//                 while (i < object.length() - 1 && inString[i]) i++;
//                 if (i >= object.length() - 1 || object.charAt(i) != '"') return ans;
//                 i++;
//             } else if (object.charAt(i) == '{' || object.charAt(i) == '[') {
//                 char open = object.charAt(i);
//                 char close = (open == '{') ? '}' : ']';
//                 int balance = 1;
//                 i++;
//                 while (i < object.length() - 1 && balance > 0) {
//                     if (!inString[i]) {
//                         if (object.charAt(i) == open) balance++;
//                         if (object.charAt(i) == close) balance--;
//                     }
//                     i++;
//                 }
//                 if (balance != 0) return ans;
//             } else {
//                 while (i < object.length() - 1 && object.charAt(i) != ',' && object.charAt(i) != '}') i++;
//             }
            
//             String tmpValue = object.substring(startValue, i).trim();
//             ans.putIfAbsent(tmpKey, new ArrayList<>());
//             ans.get(tmpKey).add(tmpValue);
            
//             while (i < object.length() - 1 && object.charAt(i) == ' ') i++;
//             if (i < object.length() - 1 && object.charAt(i) == ',') i++;
//         }
//         return ans;
//     }

//     public static boolean isNumber(String str) {
//         return str.matches("-?\\d+(\\.\\d+)?");
//     }

//     public static char type(String str) {
//         if (str.startsWith("{") && str.endsWith("}")) return 'O';
//         if (str.startsWith("[") && str.endsWith("]")) return 'A';
//         if (str.startsWith("\"") && str.endsWith("\"")) return 's';
//         if (str.equals("true") || str.equals("false")) return 'b';
//         if (str.equals("null")) return 'l';
//         if (isNumber(str)) return 'n';
//         return 'e';
//     }

//     public static int valueCounter(String str) {
//         int count = 0;
//         if (type(str) == 'O') {
//             HashMap<String, List<String>> object = objectSplitter(str);
//             if (object.isEmpty()) return 0;
//             for (List<String> values : object.values()) {
//                 for (String value : values) {
//                     count += valueCounter(value);
//                 }
//             }
//         } else if (type(str) == 'A') {
//             str = str.substring(1, str.length() - 1).trim();
//             if (!str.isEmpty()) {
//                 List<String> elements = new ArrayList<>();
//                 int balance = 0;
//                 boolean insideString = false;
//                 StringBuilder element = new StringBuilder();
//                 for (char c : str.toCharArray()) {
//                     if (c == '"' && (element.length() == 0 || element.charAt(element.length() - 1) != '\\')) {
//                         insideString = !insideString;
//                     }
//                     if (!insideString && (c == ',' && balance == 0)) {
//                         elements.add(element.toString().trim());
//                         element.setLength(0);
//                         continue;
//                     }
//                     if (!insideString && (c == '[' || c == '{')) balance++;
//                     if (!insideString && (c == ']' || c == '}')) balance--;
//                     element.append(c);
//                 }
//                 if (element.length() > 0) elements.add(element.toString().trim());
//                 for (String elementStr : elements) {
//                     count += valueCounter(elementStr);
//                 }
//             }
//         } else if (type(str) != 'e') {
//             count = 1;
//         }
//         return count;
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         String json = scanner.nextLine().trim();
//         scanner.close();

//         System.out.println(valueCounter(json));
//     }
// }
