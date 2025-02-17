import java.io.*;
import java.nio.file.*;
import java.util.*;

public class judge2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Process compileProcess = Runtime.getRuntime().exec("javac main2.java");
        compileProcess.waitFor();

        // getting the list of input's name
        File inputDir = new File("in");
        File[] inputFiles = inputDir.listFiles((dir, name) -> name.startsWith("input") && name.endsWith(".txt"));
        if (inputFiles == null || inputFiles.length == 0) {
            System.out.println("input was not found");
            return;
        }

        // sorting inputs
        Arrays.sort(inputFiles, Comparator.comparingInt(file -> extractNumber(file.getName())));

        for (File inputFile : inputFiles) {
            int testCaseNumber = extractNumber(inputFile.getName());
            File expectedOutputFile = new File("out/output" + testCaseNumber + ".txt");

            if (!expectedOutputFile.exists()) {
                System.out.println("ouput for testcase" + testCaseNumber + " was not found");
                continue;
            }

            String input = Files.readString(inputFile.toPath()).trim();
            String expectedOutput = Files.readString(expectedOutputFile.toPath()).trim();

            // run java
            Process process = Runtime.getRuntime().exec("java main2");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(input + "\n"); //input
            writer.flush();
            writer.close();

            // resdingoutput
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputBuilder.append(line).append("\n");
            }
            reader.close();

            String output = outputBuilder.toString().trim();

            // comparing
            System.out.print("test " + testCaseNumber);
            if (output.equals(expectedOutput)) {
            }
            else {
                System.out.print(" failed ");
            }
            System.out.println();
            System.out.println("   input: " + input);
            System.out.println("   output: " + output);
            System.out.println("   expected output: " + expectedOutput);
            System.out.println();
        }
    }

    private static int extractNumber(String filename) {
        return Integer.parseInt(filename.replaceAll("[^0-9]", ""));
    }
}



// import java.util.*;

// public class main2 {

//     public static boolean isSameKey(String first, String second) {
//         if (first.length()<2 || second.length()<2) return false; 
//         StringBuilder two = new StringBuilder(); 
//         two.append("</"); 
//         for (int i = 1; i<first.length(); i++) {
//             two.append(first.charAt(i)); 
//         }
//         String finalTwo = two.toString(); 
//         return finalTwo.compareTo(second)==0 ; 
//     }

//     public static String endKeyOf(String first) {
//         StringBuilder two = new StringBuilder(); 
//         two.append("</"); 
//         for (int i = 0; i<first.length(); i++) {
//             two.append(first.charAt(i)); 
//         }
//         two.append(">"); 
//         return two.toString();         
//     }
    
//     public static boolean[] isInString(String str) {
//         boolean[] ans = new boolean[str.length()];
//         boolean insideString = false;
//         for (int i = 0; i < str.length(); i++) {
//             char ch = str.charAt(i);
//             if (ch == '"') {
//                 int backslashCount = 0;
//                 int j = i - 1;
//                 while (j >= 0 && str.charAt(j) == '\\') {
//                     backslashCount++;
//                     j--;
//                 }
//                 if (backslashCount % 2 == 0) {
//                     insideString = !insideString;
//                 }
//             }
//             ans[i] = insideString;
//         }
//         return ans;
//     }


//     public static boolean is_valid_string(String str) { 
//         if (str.charAt(0) != '"' || str.charAt(str.length()-1) != '"') return false; 
//         for (int i = 1; i < str.length()-1; i++) {
//             char c = str.charAt(i); 
//             if ((c == '"' || c=='<' || c=='>') && str.charAt(i-1) != '\\') return false; 
//             else if (c == '\\') {
//                 if (i < str.length()-1 && (str.charAt(i+1) == '\\' || str.charAt(i+1) == '"' || str.charAt(i+1)=='<' || str.charAt(i+1)=='>')) {
//                     i++; 
//                     continue; 
//                 }
//                 else return false; 
//             }
//         }
//         return true; 
//     }

    
//     public static boolean isNumber(String str) {
//         if (str.isEmpty()) return false;
//         int dotCount = 0;
//         int digitCount = 0;
//         int i = 0;
//         if (str.charAt(0) == '-') {
//             i++;
//         }
//         for (; i < str.length(); i++){
//             char c = str.charAt(i);
//             if (c == '.') {
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


//     public static boolean is_valid_obj(String str) { 
//         String suffix = "<"; String prefix = ">";
//         int i = 1; 
//         while (!isSameKey(suffix, prefix) && i<str.length()) {
//             suffix = suffix + str.charAt(i);
//             prefix = str.charAt(str.length()-1-i) + prefix;
//             i++; 
//         }
//         String value = str.substring(suffix.length(), str.length()-prefix.length()); 
//         if (is_valid_string(value)) return true; 
//         if (isNumber(value)) return true; 
//         if (value.equals("true") || value.equals("false") || value.isEmpty()) return true; 

//         else return false; 
//     }

//     public static String keyOf(String object) {
//         int i = 1; 
//         while (object.charAt(i) != '>') i++; 
//         return object.substring(1, i); 
//     }

//     public static String valueOf(String object) {
//         return object.substring(keyOf(object).length()+2, object.length()-keyOf(object).length()-2); 
//     }

//     public static HashMap<String, String> arraySplitter(String str) {
//         HashMap<String, String> array = new HashMap<>(); 
//         HashMap<String, String> nullHash = new HashMap<>(); 

//         int i = 0; 
//         int end_of_last_obj = -1; 
//         while (i<str.length()) {
//             if (str.charAt(i) != '<') return nullHash; 
//             while (str.charAt(i) != '>' && i<str.length()) i++;
//             if (i>=str.length()) return nullHash; 

//             String tmpKey = str.substring(end_of_last_obj+1, i+1); 
//             String tmpValue = str.substring(i+1 , str.substring(i).indexOf(endKeyOf(tmpKey)));
//             end_of_last_obj += 2*tmpKey.length() + tmpValue.length() + 1; 
//             i = end_of_last_obj; 

//             array.put(tmpKey, tmpValue); 
//         }

//         return array; 
//     }

//     public static boolean containsKey(String str) {
//         boolean[] in_string = new boolean[str.length()]; 
//         for (int i = 0; i<str.length(); i++) {
//             if (str.charAt(i) == '<' && !in_string[i]) return true; 
//         }
//         return false; 
//     }

//     public static char type(String str) {
//         char ans = ' '; 
//         if (is_valid_string(str)) return 's'; //string
//         else if (str.startsWith("\"") && str.endsWith("\"")) return 'e'; //invalid string
//         else if (str.startsWith("<") && str.endsWith(">")) {
//             String suffix = "<"; String prefix = ">";
//             int i = 1; 
//             while (!isSameKey(suffix, prefix) && i<str.length()) {
//                 suffix = suffix + str.charAt(i);
//                 prefix = str.charAt(str.length()-1-i) + prefix;
//                 i++; 
//             }
//             if (isSameKey(suffix, prefix)) { // obj or array
//                 if (containsKey(str.substring(suffix.length(), str.length()-prefix.length()))) return 'A'; //array
//                 else return 'O'; // object
//             }
//             if (isNumber(str)) return 'n'; // number
//             if(str.equals("true") || str.equals("false")) return 'b'; //boolean
//             if (str.isEmpty()) return 'l'; //null
//             else return 'e'; // invalid obj
//         }
//         return ans; 
//     }

//     public static int valueCounter(String str) {
//         char t = type(str); 
//         if (t == 'e') return 0; 
//         if (t == 'O') return 1; 
//         if (t == 's' || t == 'b' || t == 'n' || t == 'l') return 1; 
//         if (t == 'A') {
//             int count = 1; 
//             HashMap<String, String> array = arraySplitter(str);  
//             for (String value : array.values()) {
//                 count += valueCounter(value);
//             }
//             return count; 
//         }
//         else return 0; 
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         StringBuilder inputData = new StringBuilder();
//         while (scanner.hasNextLine()) {
//             inputData.append(scanner.nextLine().trim()).append("");
//         }
//         scanner.close();
//         String xml = inputData.toString();

//         System.out.println(valueCounter(xml));
//     }
// }





















// import java.util.*;
// public class main2 {
//     public static boolean is_valid_string(String str) {
//         if(str.length() < 2) return false;
//         if(str.charAt(0) != '"' || str.charAt(str.length()-1) != '"') return false;
//         for(int i = 1; i < str.length()-1; i++){
//             char c = str.charAt(i);
//             if((c == '"' || c == '<' || c == '>') && str.charAt(i-1) != '\\')
//                 return false;
//             else if(c == '\\'){
//                 if(i < str.length()-1 && (str.charAt(i+1)=='\\' || str.charAt(i+1)=='"' || str.charAt(i+1)=='<' || str.charAt(i+1)=='>')){
//                     i++;
//                     continue;
//                 } else return false;
//             }
//         }
//         return true;
//     }
    
//     public static boolean isNumber(String str) {
//         if(str.isEmpty()) return false;
//         int dotCount = 0, digitCount = 0, i = 0;
//         if(str.charAt(0) == '-') i++;
//         for(; i < str.length(); i++){
//             char c = str.charAt(i);
//             if(c == '.'){
//                 dotCount++;
//                 if(dotCount > 1) return false;
//             } else if(Character.isDigit(c)){
//                 digitCount++;
//             } else return false;
//         }
//         return digitCount > 0;
//     }
    
//     public static int findClosingTag(String s, int start) {
//         if(s.charAt(start) != '<') return -1;
//         int startTagEnd = s.indexOf('>', start);
//         if(startTagEnd == -1) return -1;
//         String tagName = s.substring(start+1, startTagEnd);
//         String endTag = "</" + tagName + ">";
//         int index = startTagEnd + 1, count = 1;
//         while(index < s.length()){
//             int nextOpen = s.indexOf('<', index);
//             if(nextOpen == -1) break;
//             if(s.startsWith(endTag, nextOpen)){
//                 count--;
//                 if(count == 0) return nextOpen + endTag.length() - 1;
//                 index = nextOpen + endTag.length();
//             } else {
//                 int nextClose = s.indexOf('>', nextOpen);
//                 if(nextClose == -1) break;
//                 String innerTag = s.substring(nextOpen+1, nextClose);
//                 if(innerTag.equals(tagName)){
//                     count++;
//                 }
//                 index = nextClose + 1;
//             }
//         }
//         return -1;
//     }
    
//     public static int parseXML(String xml) {
//         xml = xml.trim();
//         if(xml.startsWith("<") && xml.endsWith(">")){
//             int startTagEnd = xml.indexOf('>');
//             if(startTagEnd == -1) return -1;
//             String tagName = xml.substring(1, startTagEnd);
//             String endTag = "</" + tagName + ">";
//             if(!xml.endsWith(endTag)) return -1;
//             String inner = xml.substring(startTagEnd+1, xml.length()-endTag.length()).trim();
//             int childCount = 0, i = 0;
//             while(i < inner.length()){
//                 if(inner.charAt(i) == '<'){
//                     int j = findClosingTag(inner, i);
//                     if(j == -1) return -1;
//                     String child = inner.substring(i, j+1);
//                     int cnt = parseXML(child);
//                     if(cnt == -1) return -1;
//                     childCount += cnt;
//                     i = j+1;
//                 } else {
//                     int j = i;
//                     while(j < inner.length() && inner.charAt(j) != '<') j++;
//                     String text = inner.substring(i, j).trim();
//                     if(!text.isEmpty()){
//                         if(isNumber(text) || text.equals("true") || text.equals("false") || is_valid_string(text))
//                             childCount += 1;
//                         else return -1;
//                     }
//                     i = j;
//                 }
//             }
//             return childCount == 0 ? 2 : 1 + childCount;
//         } else {
//             if(is_valid_string(xml) || isNumber(xml) || xml.equals("true") || xml.equals("false") || xml.isEmpty())
//                 return 1;
//             return -1;
//         }
//     }
    
//     public static void main(String[] args){
//         Scanner scanner = new Scanner(System.in);
//         StringBuilder inputData = new StringBuilder();
//         while(scanner.hasNextLine()){
//             inputData.append(scanner.nextLine().trim());
//         }
//         scanner.close();
//         String xml = inputData.toString();
//         if(!(xml.startsWith("<") && xml.endsWith(">"))) {
//             System.out.println(0);
//             return;
//         }
//         int total = parseXML(xml);
//         if(total == -1) {
//             System.out.println(0);
//         }
//         else {
//             int startTagEnd = xml.indexOf('>');
//             String tagName = xml.substring(1, startTagEnd);
//             String endTag = "</" + tagName + ">";
//             String inner = xml.substring(startTagEnd+1, xml.length()-endTag.length()).trim();
//             if (inner.startsWith("<")) {
//                 System.out.println(total - 1);
//             }
//             else {
//                 System.out.println(total);
//             }
//         }
//     }
// }












// import java.util.*;

// public class main2 {

//     public static boolean isSameKey(String first, String second) {
//         if (first.length() < 2 || second.length() < 2) return false;
//         StringBuilder two = new StringBuilder();
//         two.append("</");
//         for (int i = 1; i < first.length(); i++) {
//             two.append(first.charAt(i));
//         }
//         String finalTwo = two.toString();
//         return finalTwo.compareTo(second) == 0;
//     }

//     public static String endKeyOf(String first) {
//         StringBuilder two = new StringBuilder();
//         two.append("</");
//         for (int i = 0; i < first.length(); i++) {
//             two.append(first.charAt(i));
//         }
//         two.append(">");
//         return two.toString();
//     }

//     public static boolean[] isInString(String str) {
//         boolean[] ans = new boolean[str.length()];
//         boolean insideString = false;
//         for (int i = 0; i < str.length(); i++) {
//             char ch = str.charAt(i);
//             if (ch == '"') {
//                 int backslashCount = 0;
//                 int j = i - 1;
//                 while (j >= 0 && str.charAt(j) == '\\') {
//                     backslashCount++;
//                     j--;
//                 }
//                 if (backslashCount % 2 == 0) {
//                     insideString = !insideString;
//                 }
//             }
//             ans[i] = insideString;
//         }
//         return ans;
//     }

//     public static boolean is_valid_string(String str) {
//         if (str.length() < 2) return false;
//         if (str.charAt(0) != '"' || str.charAt(str.length() - 1) != '"') return false;
//         for (int i = 1; i < str.length() - 1; i++) {
//             char c = str.charAt(i);
//             if ((c == '"' || c == '<' || c == '>') && str.charAt(i - 1) != '\\') return false;
//             else if (c == '\\') {
//                 if (i < str.length() - 1 && (str.charAt(i + 1) == '\\' || str.charAt(i + 1) == '"' || str.charAt(i + 1) == '<' || str.charAt(i + 1) == '>')) {
//                     i++;
//                     continue;
//                 } else return false;
//             }
//         }
//         return true;
//     }

//     public static boolean isNumber(String str) {
//         if (str.isEmpty()) return false;
//         int dotCount = 0;
//         int digitCount = 0;
//         int i = 0;
//         if (str.charAt(0) == '-') {
//             i++;
//         }
//         for (; i < str.length(); i++){
//             char c = str.charAt(i);
//             if (c == '.') {
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

//     public static boolean is_valid_obj(String str) {
//         String suffix = "<"; 
//         String prefix = ">";
//         int i = 1;
//         while (!isSameKey(suffix, prefix) && i < str.length()) {
//             suffix = suffix + str.charAt(i);
//             prefix = str.charAt(str.length() - 1 - i) + prefix;
//             i++;
//         }
//         String value = str.substring(suffix.length(), str.length() - prefix.length());
//         if (is_valid_string(value)) return true;
//         if (isNumber(value)) return true;
//         if (value.equals("true") || value.equals("false") || value.isEmpty()) return true;
//         else return false;
//     }

//     public static String keyOf(String object) {
//         int i = 1;
//         while (i < object.length() && object.charAt(i) != '>') i++;
//         return object.substring(1, i);
//     }

//     public static String valueOf(String object) {
//         int start = object.indexOf('>');
//         int end = object.lastIndexOf('<');
//         if (start == -1 || end == -1 || end <= start) return "";
//         return object.substring(start + 1, end);
//     }

//     public static HashMap<String, String> arraySplitter(String str) {
//         HashMap<String, String> array = new HashMap<>();
//         int i = 0, counter = 0;
//         while (i < str.length()) {
//             if (str.charAt(i) != '<') return new HashMap<>();
//             int tagEnd = str.indexOf('>', i);
//             if (tagEnd == -1) return new HashMap<>();
//             String openTag = str.substring(i, tagEnd + 1);
//             String key = keyOf(openTag);
//             String closeTag = endKeyOf(key);
//             int closeIndex = str.indexOf(closeTag, tagEnd + 1);
//             if (closeIndex == -1) return new HashMap<>();
//             int fullEnd = closeIndex + closeTag.length();
//             String fullTag = str.substring(i, fullEnd);
//             array.put(openTag + counter, fullTag);
//             counter++;
//             i = fullEnd;
//         }
//         return array;
//     }

//     public static boolean containsKey(String str) {
//         boolean[] in_string = new boolean[str.length()];
//         for (int i = 0; i < str.length(); i++) {
//             if (str.charAt(i) == '<' && !in_string[i]) return true;
//         }
//         return false;
//     }

//     public static char type(String str) {
//         if (is_valid_string(str)) return 's';
//         if (str.startsWith("\"") && str.endsWith("\"")) return 'e';
//         if (str.startsWith("<") && str.endsWith(">")) {
//             String key = keyOf(str);
//             String closingTag = endKeyOf(key);
//             if (!str.endsWith(closingTag)) return 'e';
//             return 'O';
//         }
//         if (isNumber(str)) return 'n';
//         if (str.equals("true") || str.equals("false")) return 'b';
//         if (str.isEmpty()) return 'l';
//         return 'e';
//     }

//     public static int valueCounter(String str) {
//         char t = type(str);
//         if (t == 'e') return 0;
//         if (t == 'O') {
//             String inner = valueOf(str);
//             if (!inner.contains("<")) return 2;
//             HashMap<String, String> children = arraySplitter(inner);
//             int sum = 0;
//             for (String child : children.values()) {
//                 sum += valueCounter(child);
//             }
//             if (children.size() == 1) return sum;
//             else return 1 + sum;
//         }
//         if (t == 's' || t == 'b' || t == 'n' || t == 'l') return 1;
//         return 0;
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         StringBuilder inputData = new StringBuilder();
//         while (scanner.hasNextLine()) {
//             inputData.append(scanner.nextLine().trim());
//         }
//         scanner.close();
//         String xml = inputData.toString();

//         if (!(xml.startsWith("<") && xml.endsWith(">"))) {
//             System.out.println(0);
//             return;
//         }

//         String rootKey = keyOf(xml);
//         String rootClosing = endKeyOf(rootKey);
//         if (!xml.endsWith(rootClosing)) {
//             System.out.println(0);
//             return;
//         }

//         String innerRoot = valueOf(xml);
//         if (innerRoot.contains("><") && !innerRoot.trim().startsWith("<")) {
//             System.out.println(0);
//             return;
//         }
//         System.out.println(valueCounter(xml));
//     }
// }














// import java.util.*;

// public class main2 {

//     public static boolean isSameKey(String first, String second) {
//         if (first.length() < 2 || second.length() < 2) return false;
//         StringBuilder two = new StringBuilder();
//         two.append("</");
//         for (int i = 1; i < first.length(); i++) {
//             two.append(first.charAt(i));
//         }
//         String finalTwo = two.toString();
//         return finalTwo.compareTo(second) == 0;
//     }

//     public static int counter(String str, String tmp) {
//         int c = 0; 
//         for (int i = 0; i<str.length()-tmp.length(); i++) {
//             if (str.substring(i).startsWith(tmp)) c++; 
//         }
//         return c; 
//     }

//     public static HashMap<String, Integer> keyCount(String str) {
//         HashMap<String, Integer> ans = new HashMap<>();
//         HashMap<String, Integer> nullHash = new HashMap<>();
//         boolean[] in_string = isInString(str); 
//         int i = 0; 
//         while (i<str.length()) {
//             while (!(str.charAt(i) == '<' && !in_string[i]) && i<str.length()) i++; 
//             if (i>=str.length()) return nullHash;

//             int startTag = i; 
//             while (!(str.charAt(i) == '>' && !in_string[i]) && i<str.length()) i++;
//             int endTag = i; 
//             String tmpTag = str.substring(startTag, endTag+1); 

//             if (ans.containsKey(tmpTag)) ans.remove(tmpTag); 
//             ans.put(tmpTag, counter(str, tmpTag)); 
//         }
//         return ans; 
//     }

//     public static String endKeyOf(String first) {
//         StringBuilder two = new StringBuilder();
//         two.append("</");
//         for (int i = 0; i < first.length(); i++) {
//             two.append(first.charAt(i));
//         }
//         two.append(">");
//         return two.toString();
//     }

//     public static boolean[] isInString(String str) {
//         boolean[] ans = new boolean[str.length()];
//         boolean insideString = false;
//         for (int i = 0; i < str.length(); i++) {
//             char ch = str.charAt(i);
//             if (ch == '"') {
//                 int backslashCount = 0;
//                 int j = i - 1;
//                 while (j >= 0 && str.charAt(j) == '\\') {
//                     backslashCount++;
//                     j--;
//                 }
//                 if (backslashCount % 2 == 0) {
//                     insideString = !insideString;
//                 }
//             }
//             ans[i] = insideString;
//         }
//         return ans;
//     }

//     public static boolean is_valid_string(String str) {
//         if (str.length() < 2) return false;
//         if (str.charAt(0) != '"' || str.charAt(str.length() - 1) != '"') return false;
//         for (int i = 1; i < str.length() - 1; i++) {
//             char c = str.charAt(i);
//             if ((c == '"' || c == '<' || c == '>') && str.charAt(i - 1) != '\\') return false;
//             else if (c == '\\') {
//                 if (i < str.length() - 1 && (str.charAt(i + 1) == '\\' || str.charAt(i + 1) == '"' || str.charAt(i + 1) == '<' || str.charAt(i + 1) == '>')) {
//                     i++;
//                     continue;
//                 }
//                 else return false;
//             }
//         }
//         return true;
//     }

//     public static boolean isNumber(String str) {
//         if (str.isEmpty()) return false;
//         int dotCount = 0;
//         int digitCount = 0;
//         int i = 0;
//         if (str.charAt(0) == '-') {
//             i++;
//         }
//         for (; i < str.length(); i++){
//             char c = str.charAt(i);
//             if (c == '.') {
//                 dotCount++;
//                 if (dotCount > 1) return false;
//             }
//             else if (Character.isDigit(c)) {
//                 digitCount++;
//             }
//             else {
//                 return false;
//             }
//         }
//         return digitCount > 0;
//     }

//     public static boolean is_valid_obj(String str) {
//         String suffix = "<";
//         String prefix = ">";
//         int i = 1;
//         while (!isSameKey(suffix, prefix) && i < str.length()) {
//             suffix = suffix + str.charAt(i);
//             prefix = str.charAt(str.length() - 1 - i) + prefix;
//             i++;
//         }
//         String value = str.substring(suffix.length(), str.length() - prefix.length());
//         if (is_valid_string(value)) return true;
//         if (isNumber(value)) return true;
//         if (value.equals("true") || value.equals("false") || value.isEmpty()) return true;
//         else return false;
//     }

//     public static String keyOf(String object) {
//         int i = 1;
//         while (i < object.length() && object.charAt(i) != '>') i++;
//         return object.substring(1, i);
//     }

//     public static String valueOf(String object) {
//         int start = object.indexOf('>');
//         int end = object.lastIndexOf('<');
//         if (start == -1 || end == -1 || end <= start) return "";
//         return object.substring(start + 1, end);
//     }

//     public static HashMap<String, String> arraySplitter(String str) {
//         HashMap<String, String> array = new HashMap<>();
//         HashMap<String, String> nullHash = new HashMap<>();

//         int i = 0, counter = 0;
//         while (i < str.length()) {
//             if (str.charAt(i) != '<') return nullHash;

//             int tagEnd = str.indexOf('>', i);
//             if (tagEnd == -1) return nullHash;

//             String openTag = str.substring(i, tagEnd + 1);
//             String key = keyOf(openTag);
//             String closeTag = endKeyOf(key);

//             int closeIndex = str.indexOf(closeTag, tagEnd + 1);
//             if (closeIndex == -1) return nullHash;

//             int endOfObj = closeIndex + closeTag.length();
//             String fullObj = str.substring(i, endOfObj);
//             array.put(openTag + counter, fullObj);
//             counter++;
//             i = endOfObj;
//         }
//         return array;
//     }

//     public static boolean containsKey(String str) {
//         boolean[] in_string = new boolean[str.length()];
//         for (int i = 0; i < str.length(); i++) {
//             if (str.charAt(i) == '<' && !in_string[i]) return true;
//         }
//         return false;
//     }

//     public static char type(String str) {
//         if (is_valid_string(str)) return 's';
//         if (str.startsWith("\"") && str.endsWith("\"")) return 'e';
//         if (str.startsWith("<") && str.endsWith(">")) {
//             String key = keyOf(str);
//             String closingTag = endKeyOf(key);
//             if (!str.endsWith(closingTag)) return 'e';
//             return 'O';
//         }
//         if (isNumber(str)) return 'n';
//         if (str.equals("true") || str.equals("false")) return 'b';
//         if (str.isEmpty()) return 'l';
//         return 'e';
//     }

//     public static int valueCounter(String str, boolean isRoot) {
//         char t = type(str);
//         if (t == 'e') return 0;
//         if (t == 'O') {
//             String inner = valueOf(str).trim();
//             boolean hasTag = false;
//             boolean[] inStr = isInString(inner);
//             for (int i = 0; i < inner.length(); i++){
//                 if(inner.charAt(i) == '<' && !inStr[i]){
//                     hasTag = true;
//                     break;
//                 }
//             }
//             if (!hasTag) {
//                 if(inner.equals("") || isNumber(inner) || inner.equals("true") || inner.equals("false") || is_valid_string(inner))
//                     return 2;
//                 else return 0;
//             }
//             HashMap<String, String> children = arraySplitter(inner);
//             HashMap<String, List<String>> groups = new HashMap<>();
//             for(String child : children.values()){
//                 String k = keyOf(child);
//                 groups.putIfAbsent(k, new ArrayList<>());
//                 groups.get(k).add(child);
//             }
//             int sumGroups = 0;
//             for(List<String> group : groups.values()){
//                 int groupSum = 0;
//                 for(String child : group){
//                     int vc = valueCounter(child, false);
//                     if(vc == 0) return 0;
//                     groupSum += vc;
//                 }
//                 sumGroups += (groupSum - (group.size() - 1));
//             }
//             int container = 1;
//             return container + sumGroups;
//         }
//         if (t == 's' || t == 'b' || t == 'n' || t == 'l') return 1;
//         return 0;
//     }

//     public static int valueCounter(String str) {
//         return valueCounter(str, true);
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         StringBuilder inputData = new StringBuilder();
//         while (scanner.hasNextLine()) {
//             inputData.append(scanner.nextLine().trim());
//         }
//         scanner.close();

//         String xml = inputData.toString();
//         if (!(xml.startsWith("<") && xml.endsWith(">"))) {
//             System.out.println(0);
//             return;
//         }

//         String rootKey = keyOf(xml);
//         String rootClosing = endKeyOf(rootKey);
//         if (!xml.endsWith(rootClosing)) {
//             System.out.println(0);
//             return;
//         }
//         String innerRoot = valueOf(xml);

//         if (innerRoot.contains("><") && !innerRoot.trim().startsWith("<")) {
//             System.out.println(0);
//             return;
//         }

//         HashMap<String, Integer> key_count = new HashMap<>(); 
//         key_count = keyCount(xml);
//         for (String key : key_count.keySet()) {
//             if (key_count.get(key)%2 != 0) {
//                 System.out.println(0);
//                 return; 
//             }
//         }

//         System.out.println(valueCounter(xml));
//     }
// }










// import java.util.*;

// public class main2 {

//     public static boolean isSameKey(String first, String second) {
//         if (first.length() < 2 || second.length() < 2) return false;
//         StringBuilder two = new StringBuilder();
//         two.append("</");
//         for (int i = 1; i < first.length(); i++) {
//             two.append(first.charAt(i));
//         }
//         String finalTwo = two.toString();
//         return finalTwo.compareTo(second) == 0;
//     }

//     public static int counter(String str, String tmp) {
//         int c = 0;
//         for (int i = 0; i < str.length() - tmp.length(); i++) {
//             if (str.substring(i).startsWith(tmp)) c++;
//         }
//         return c;
//     }

//     public static HashMap<String, Integer> keyCount(String str) {
//         HashMap<String, Integer> ans = new HashMap<>();
//         boolean[] in_string = isInString(str);
//         int i = 0;
//         while (i < str.length()) {
//             while (i < str.length() && !(str.charAt(i) == '<' && !in_string[i])) i++;
//             if (i >= str.length()) break;
//             int startTag = i;
//             while (i < str.length() && !(str.charAt(i) == '>' && !in_string[i])) i++;
//             int endTag = i;
//             String tmpTag = str.substring(startTag, endTag + 1);
//             if (ans.containsKey(tmpTag)) ans.remove(tmpTag);
//             ans.put(tmpTag, counter(str, tmpTag));
//         }
//         return ans;
//     }

//     public static String endKeyOf(String first) {
//         StringBuilder two = new StringBuilder();
//         two.append("</");
//         for (int i = 0; i < first.length(); i++) {
//             two.append(first.charAt(i));
//         }
//         two.append(">");
//         return two.toString();
//     }

//     public static boolean[] isInString(String str) {
//         boolean[] ans = new boolean[str.length()];
//         boolean insideString = false;
//         for (int i = 0; i < str.length(); i++) {
//             char ch = str.charAt(i);
//             if (ch == '"') {
//                 int backslashCount = 0;
//                 int j = i - 1;
//                 while (j >= 0 && str.charAt(j) == '\\') {
//                     backslashCount++;
//                     j--;
//                 }
//                 if (backslashCount % 2 == 0) {
//                     insideString = !insideString;
//                 }
//             }
//             ans[i] = insideString;
//         }
//         return ans;
//     }

//     public static boolean is_valid_string(String str) {
//         if (str.length() < 2) return false;
//         if (str.charAt(0) != '"' || str.charAt(str.length() - 1) != '"') return false;
//         for (int i = 1; i < str.length() - 1; i++) {
//             char c = str.charAt(i);
//             if ((c == '"' || c == '<' || c == '>') && str.charAt(i - 1) != '\\') return false;
//             else if (c == '\\') {
//                 if (i < str.length() - 1 && (str.charAt(i + 1) == '\\' || str.charAt(i + 1) == '"' || str.charAt(i + 1) == '<' || str.charAt(i + 1) == '>')) {
//                     i++;
//                     continue;
//                 }
//                 else return false;
//             }
//         }
//         return true;
//     }

//     public static boolean isNumber(String str) {
//         if (str.isEmpty()) return false;
//         int dotCount = 0;
//         int digitCount = 0;
//         int i = 0;
//         if (str.charAt(0) == '-') {
//             i++;
//         }
//         for (; i < str.length(); i++){
//             char c = str.charAt(i);
//             if (c == '.') {
//                 dotCount++;
//                 if (dotCount > 1) return false;
//             }
//             else if (Character.isDigit(c)) {
//                 digitCount++;
//             }
//             else {
//                 return false;
//             }
//         }
//         return digitCount > 0;
//     }

//     public static boolean is_valid_obj(String str) {
//         String suffix = "<";
//         String prefix = ">";
//         int i = 1;
//         while (!isSameKey(suffix, prefix) && i < str.length()) {
//             suffix = suffix + str.charAt(i);
//             prefix = str.charAt(str.length() - 1 - i) + prefix;
//             i++;
//         }
//         String value = str.substring(suffix.length(), str.length() - prefix.length());
//         if (is_valid_string(value)) return true;
//         if (isNumber(value)) return true;
//         if (value.equals("true") || value.equals("false") || value.isEmpty()) return true;
//         else return false;
//     }

//     public static String keyOf(String object) {
//         int i = 1;
//         while (i < object.length() && object.charAt(i) != '>') i++;
//         return object.substring(1, i);
//     }

//     public static String valueOf(String object) {
//         int start = object.indexOf('>');
//         int end = object.lastIndexOf('<');
//         if (start == -1 || end == -1 || end <= start) return "";
//         return object.substring(start + 1, end);
//     }

//     public static HashMap<String, String> arraySplitter(String str) {
//         HashMap<String, String> array = new HashMap<>();
//         HashMap<String, String> nullHash = new HashMap<>();
//         int i = 0, counter = 0;
//         while (i < str.length()) {
//             if (str.charAt(i) != '<') return nullHash;
//             int tagEnd = str.indexOf('>', i);
//             if (tagEnd == -1) return nullHash;
//             String openTag = str.substring(i, tagEnd + 1);
//             String key = keyOf(openTag);
//             String closeTag = endKeyOf(key);
//             int closeIndex = str.indexOf(closeTag, tagEnd + 1);
//             if (closeIndex == -1) return nullHash;
//             int endOfObj = closeIndex + closeTag.length();
//             String fullObj = str.substring(i, endOfObj);
//             array.put(openTag + counter, fullObj);
//             counter++;
//             i = endOfObj;
//         }
//         return array;
//     }

//     public static boolean containsKey(String str) {
//         boolean[] in_string = new boolean[str.length()];
//         for (int i = 0; i < str.length(); i++) {
//             if (str.charAt(i) == '<' && !in_string[i]) return true;
//         }
//         return false;
//     }

//     public static char type(String str) {
//         if (is_valid_string(str)) return 's';
//         if (str.startsWith("\"") && str.endsWith("\"")) return 'e';
//         if (str.startsWith("<") && str.endsWith(">")) {
//             String key = keyOf(str);
//             String closingTag = endKeyOf(key);
//             if (!str.endsWith(closingTag)) return 'e';
//             return 'O';
//         }
//         if (isNumber(str)) return 'n';
//         if (str.equals("true") || str.equals("false")) return 'b';
//         if (str.isEmpty()) return 'l';
//         return 'e';
//     }

//     public static int valueCounter(String str, boolean isRoot) {
//         char t = type(str);
//         if (t == 'e') return 0;
//         if (t == 'O') {
//             String inner = valueOf(str).trim();
//             boolean hasTag = false;
//             boolean[] inStr = isInString(inner);
//             for (int i = 0; i < inner.length(); i++){
//                 if(inner.charAt(i) == '<' && !inStr[i]){
//                     hasTag = true;
//                     break;
//                 }
//             }
//             if (!hasTag) {
//                 if(inner.equals("") || isNumber(inner) || inner.equals("true") || inner.equals("false") || is_valid_string(inner))
//                     return 2;
//                 else return 0;
//             }
//             HashMap<String, String> children = arraySplitter(inner);
//             HashMap<String, List<String>> groups = new HashMap<>();
//             for(String child : children.values()){
//                 String k = keyOf(child);
//                 groups.putIfAbsent(k, new ArrayList<>());
//                 groups.get(k).add(child);
//             }
//             int sumGroups = 0;
//             for(List<String> group : groups.values()){
//                 int groupSum = 0;
//                 for(String child : group){
//                     int vc = valueCounter(child, false);
//                     if(vc == 0) return 0;
//                     groupSum += vc;
//                 }
//                 sumGroups += (groupSum - (group.size() - 1));
//             }
//             int container = 1;
//             return container + sumGroups;
//         }
//         if (t == 's' || t == 'b' || t == 'n' || t == 'l') return 1;
//         return 0;
//     }

//     public static int valueCounter(String str) {
//         return valueCounter(str, true);
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         StringBuilder inputData = new StringBuilder();
//         while (scanner.hasNextLine()) {
//             inputData.append(scanner.nextLine().trim());
//         }
//         scanner.close();
//         String xml = inputData.toString();
//         if (!(xml.startsWith("<") && xml.endsWith(">"))) {
//             System.out.println(0);
//             return;
//         }
//         String rootKey = keyOf(xml);
//         String rootClosing = endKeyOf(rootKey);
//         if (!xml.endsWith(rootClosing)) {
//             System.out.println(0);
//             return;
//         }
//         String innerRoot = valueOf(xml);
//         if (innerRoot.contains("><") && !innerRoot.trim().startsWith("<")) {
//             System.out.println(0);
//             return;
//         }

//         // HashMap<String, Integer> key_count = keyCount(xml);
//         // for (String key : key_count.keySet()) {
//         //     if (key_count.get(key) % 2 != 0) {
//         //         System.out.println(0);
//         //         return;
//         //     }
//         // }
//         System.out.println(valueCounter(xml));
//     }
// }







