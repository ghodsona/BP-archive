import java.util.*;

public class final2 {
    public static HashMap<String, Integer> counter (String s) {
        HashMap<String, Integer> answer = new HashMap<>(); 

        String tmp = ""; 
        for (int i = 0; i<s.length(); i++) {
            if (s.charAt(i) != ' ') tmp = tmp + s.charAt(i); 
            else if (!tmp.isEmpty()){
                if (answer.containsKey(tmp)) { 
                    int a = answer.get(tmp); 
                    answer.remove(tmp); 
                    answer.put(tmp, a+1); 
                    tmp = ""; 
                }
                else {
                    answer.put(tmp, 1); 
                    tmp = ""; 
                }
            }
        }
        return answer; 
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        String s = scanner.nextLine(); 
        HashMap<String, Integer> count = new HashMap<>(); 
        count = counter(s); 
        int max = 0; 
        for (int i : count.values()) {
            if (i>max) max = i; 
        }

        System.out.println(max);
        
    }
}
