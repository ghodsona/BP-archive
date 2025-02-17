import java.util.Scanner;

public class midterm5 {
    static int[][] graph; // اگه بین دو دانشجوی آی و جی دوستی باشه، اون خونه از گراف 1 میشه وگرنه 0 
    static int[] isInGroup; // برای هر دانشجو نشون میده به گروه روابط عمومی وصله یا نه. اگه وصل بود 1، در غیر این صورت 0
    static int[] isSenfi; // اگه دانشجویی عضو صنفی باشه میشه 1 در غیر این صورت 0
    static boolean[] jaigasht; // در واقع هر خونه این ارایه میگه جایگشت آی ام از حضور یا عدم حضور یه خونه توی مجموعه احاطه گر، آیا مورد قبوله و مجموعه احاطه گر هست یا نه
    static int[] tedadeSenfi; // به ازای هر جایگشت میگه توش چند تا عضو صنفی داریم
    static int n; // تعداد رئوس گراف
    static int m; // تعداد یال های گراف

    // تولید زیر مجموعه آی ام از مجموعه رئوس گراف
    public static void chooseSenfi(int i, int n) {
        int nn = i; 
        for (int j = 0; j<n; j++) {
            isSenfi[j] = 0; 
        }
        for (int j = 0; j<n; j++) {
            isSenfi[j] = nn%2; 
            nn /= 2; 
        }
    }

    // اول ریست و بعد باز تولید آرایه ای که میگه یه نفر ایا توی گروه هست یا نه (یعنی احاطه شده یا نه) 
    public static void makeGroup() {
        for (int i = 0; i < n; i++) {
            isInGroup[i] = 0;
        }
        for (int i = 0; i<n; i++) {
            if (isSenfi[i]==1) {
                isInGroup[i] = 1;
                continue;
            }
            for (int j = 0; j<n; j++) {
                if (i==j) continue; 
                if (graph[i][j]==1 && isSenfi[j]==1) {
                    isInGroup[i] = 1; 
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        n = scanner.nextInt(); 
        m = scanner.nextInt(); 
        graph = new int[n][n];
        isInGroup = new int[n]; 
        isSenfi = new int[n];
        

        // مشخص کردن یال ها و روابط دوستی بین ادم ها
        for (int i = 0; i<m; i++) {
            int a = scanner.nextInt(); 
            int b = scanner.nextInt(); 
            graph[a-1][b-1] = 1; 
            graph[b-1][a-1] = 1; 
        }
        

        // حداکثر تعداد زیر مجموعه های رئوس گراف
        int xxx = (int)Math.pow(2, n);
        jaigasht = new boolean[xxx];
        tedadeSenfi = new int[xxx];

        for (int i = 0; i<xxx; i++) {
            // تولید زیر مجموعه آی ام از آدمایی که قراره صنفی باشن
            chooseSenfi(i, n);

            // مشخص کردن اینکه ایا هر ادم احاطه شده یا نه
            makeGroup();

            // به صورت پیشفرض هر زیرمجموعه از رئوس رو احاطه گر میگیریم مگه اینکه راسی پیدا کنیم که احاطه نشه
            jaigasht[i] = true; 
            for (int j = 0; j<n; j++) {
                if (isInGroup[j] == 0) {
                    jaigasht[i] = false; 
                    break; 
                }
            }

            // حالا میخوایم بشمریم ببنیم این زیرمجموعه از رئوس چند عضویه
            tedadeSenfi[i] = 0;
            for (int j = 0; j<n; j++) {
                tedadeSenfi[i] += isSenfi[j]; 
            }
        }

        // حداقل تعداد رئوس مجموعه احاطه گر رو الکی میگیریم 20 
        int minTedadSenfi = 20;
        for (int i =0; i<xxx; i++) {

            // اگه جایگشتی احاطه گر بود و تعداد اعضاش کمتر از مینیممی بود که تا الان پیدا کردیم، مینیمم آپدیت میشه
            if (jaigasht[i]) {
                if (tedadeSenfi[i] < minTedadSenfi) {
                    minTedadSenfi = tedadeSenfi[i];
                }
            }
        }

        System.out.println(minTedadSenfi);
        scanner.close();
    }
}