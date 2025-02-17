import java.util.Arrays;
import java.util.Scanner;

public class E3_555 {
    static int[][] wall; // وضعیت دیوار و آجرایی که موجودن
    static int h; // تعداد حملات
    static boolean[][] status; // آیا آجر پایداره؟
    static boolean[][] isChecked; // آیا این آجر رو بازدید کردم؟
    static int row; // تعداد ردیف
    static int clm; // تعداد ستون
    static int[] answer; // تغییرات تعداد پایدار ها بعد از هر حمله

    // دو تا آرایه ای که مجموع عنصر مثلا آی ام شون، میشه اون حرکتی که واسه بازدید میخوایم بزنیم
    static int[] mx = {-1, 0, 0, 1};
    static int[] my = {0, -1, 1, 0}; 

    // تابعی برای شمارش تعداد خونه های پایدار، وقتی وضعیت پایداری مشخص شده باشه
    public static int aajorCount() {
        int count = 0; 
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < clm; j++) {
                if (status[i][j]) count++; 
            }
        }
        return count; 
    }

    //  تابعی برای علامت گذاری خونه های پایدار مجاور خونه[i][j]
    public static void mark(int i, int j) {
        // علامت گذاری خونه به عنوان پایدار و بازدید شده
        status[i][j] = true; 
        isChecked[i][j] = true;

        // حلقه ای برای هر کدوم از 4 خونه مجاور
        for (int d = 0; d < 4; d++) {
            // تعیین مختصاتی که قراره بازدیدو علامت گذاری شه 
            int iCheck = i + mx[d]; 
            int jCheck = j + my[d]; 

            // اول شرط میزاریم که اون خونه اولا موجود باشه و بیرون از دیوار نباشه
            // ثانیا اینکه اون خونه توش آجری باشه. چک کردن خونه هایی که توشون آجر نیست
            // بی فایده ست و لزومی نداره.
            if (iCheck < row && jCheck < clm && iCheck > -1 && jCheck > -1 && wall[iCheck][jCheck] == 1) {

                // حالا اگه اون خونه بازدید نشده بود، بیا و تابع علامت گذاری خونه های پایدار مجاورش رو اجرا کن
                if (!isChecked[iCheck][jCheck]) {
                    mark(iCheck, jCheck);
                }
            }
        }
    }

    public static void main(String[] args) {
        // داریم طبق توضیح ورودی میگیریم
        Scanner scanner = new Scanner(System.in); 
        
        row = scanner.nextInt(); 
        clm = scanner.nextInt(); 
        wall = new int[row][clm];       
        status = new boolean[row][clm];
        isChecked = new boolean[row][clm];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < clm; j++) {
                wall[i][j] = scanner.nextInt(); 
            }
        }
        
        h = scanner.nextInt();
        answer = new int[h];         


        // حالا میخوایم علامت گذاری رو شروع کنیم
        for (int i = 0; i < h; i++) {

            // دریافت مخصات حمله
            int hx = scanner.nextInt();
            int hy = scanner.nextInt();

            // اگه اون خونه ای که بهش حمله شده توش آجری نبود، هیچ تغییری ایجاد نمیشه و جواب میشه 0
            // پس اصلا علامت گذاری و فلان نمیخواد و مستقیم میریم سراغ حمله بعد
            if (wall[hx][hy] == 0) {
                answer[i]=0; 
                continue; 
            }
            
            // اینجا اول کل خونه هارو ناپایدار و بازدید نشده در نظر میگیریم
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < clm; c++) {
                    status[r][c] = false;
                    isChecked[r][c] = false;
                }
            }

            // تابع علامت گذاری رو برای تمام خونه هایی که سطر اولن و توشون آجری هست اجرا میکنیم
            // تا خود اون خونه ها و تمام اجرای مجاور و مجاورمجاور و مجاورمجاورمجاور و ... شون پایدار علامت بخوره
            for (int k = 0; k < clm; k++) {
                if (wall[0][k] == 1) {
                    mark(0, k);
                }
            }

            // حالا اون خونه هایی که ناپایدارن قراره فرو بریزن و 0 شن
            for (int rr = 0; rr < row; rr++) {
                for (int cc = 0; cc < clm; cc++) {
                    if (!status[rr][cc]) wall[rr][cc] = 0;
                }
            }

            // حالا تعداد آجرای پایدار رو میشمریم، میشه تعداد اولیه پایدار ها
            int oldCount = aajorCount();

            //---- واردکردن ضربه ----
            wall[hx][hy] = 0;

            // اینجا دوباره کل خونه هارو ناپایدار و بازدید نشده در نظر میگیریم
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < clm; c++) {
                    status[r][c] = false;
                    isChecked[r][c] = false;
                }
            }

            // تابع علامت گذاری رو برای تمام خونه هایی که سطر اولن و توشون آجری هست اجرا میکنیم
            // تا خود اون خونه ها و تمام اجرای مجاور و مجاورمجاور و مجاورمجاورمجاور و ... شون پایدار علامت بخوره
            for (int k = 0; k < clm; k++) {
                if (wall[0][k] == 1) {
                    mark(0, k);
                }
            }

            // حالا تعداد آجرای پایدار رو میشمریم، میشه تعداد اولیه پایدار ها
            int newCount = aajorCount();
            
            // حالا اون خونه هایی که ناپایدارن قراره فرو بریزن و 0 شن
            for (int rr = 0; rr < row; rr++) {
                for (int cc = 0; cc < clm; cc++) {
                    if (!status[rr][cc]) wall[rr][cc] = 0;
                }
            }
            
            //---- محاسبهٔ تعداد خانه‌های ناپایدارشده ----
            // یک واحد کم میکنیم چون یکی از اونایی که ناپایدارن و موجود نیستن توسط نازنین ناموجود شده که واسمون مهم نیست
            answer[i] = (oldCount - newCount) - 1;
        }

        // آرایه جواب رو به رشته تبدیل میکنیم و خروجی میدیم
        System.out.println(Arrays.toString(answer));
        scanner.close(); 
    }
}
