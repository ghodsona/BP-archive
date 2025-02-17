import java.util.Scanner;

public class E3_4 {

    // تابعی برای محاسبه بیشترین ارزشی که میتونیم بدزدیم، وقتی ارزش هر شی تو ارایه ولیو باشه
    public static long maxPrice(long[] value) {

        // تعداد اشیا
        int n = value.length;

        // اگه هیچ شی ای نباشه، هیچی نمیشه دزدید
        if (n == 0) return 0;

        // اگه فقط یه شی باشه تنها راه اینه که همون رو بدزدیم
        if (n == 1) return value[0];

        /* یه آرایه تعریف میکنیم که توی عنصر آی ام اون، بیشترین ارزشی که میشه به دست آورد، اگه فقط بتونیم شی 1 تا آی به علاوه 1 ام رو بدزدیم
        ذخیره شده
        مثلا 
        dp[i]
        i+1 میشه بیشترین ارزشی که میشه دزدید ، اگه فقط بشه شی اول تا  
        رو دزدید
        */
        long[] dp = new long[n];
        dp[0] = value[0];
        dp[1] = Math.max(value[0], value[1]);

        // مقدار این ارایه، از روی عناصر قبلیش حساب میشه
        /*
        دو حالت وجود داره:

        یا شی آی ام دزدیده میشه که در اون صورت ارزشی که به دست میاریم میشه ارزش شی آی ام،
        به علاوه حداکثر ارزشی که میتونیم از خونه 1 تا 
        i-2 ام 
        بدزدیم

        یا شی آی ام دزدیده نمیشه 
        که در اون صورت حداکثر ارزشی که به دست میاد میشه
        dp[i-1]

        باید ببینیم بین این دو تا حالت، کدوم بزرگ تر میشه
        */
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], value[i] + dp[i-2]);
        }

        // اگه قرار باشه تا شی آخر یعنی
        // n 
        // رو بدزدیم، جواب میشه
        // dp[n-1]
        return dp[n-1];
    }

    public static void main(String[] args) {
        //ورودی میگیریم
        Scanner scanner = new Scanner(System.in); 
        int n = scanner.nextInt();
        long[] value = new long[n]; 
        for (int i = 0; i < n; i++) {
            value[i] = scanner.nextLong();
        }

        // حداکثر ارزشی که میشه به دست آورد رو چاپ میکنیم
        System.out.println(maxPrice(value));
        scanner.close();
    }
}
