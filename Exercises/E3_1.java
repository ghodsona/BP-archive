import java.util.Scanner;

public class E3_1 {

    public static void bubbleSort(int[] nums) {
        boolean doWeSwap = true; 
        while(doWeSwap) {
            doWeSwap = false; 
            for (int i=0; i<nums.length-1; i++) {
                if (nums[i+1] < nums[i]) {
                    int c = nums[i]; 
                    nums[i] = nums[i+1]; 
                    nums[i+1] = c;
                    doWeSwap = true; 
                }
            }
        }
    }
   
    public static void swap(int[] nums_, int i, int j) {
        int googooli = nums_[i];
        nums_[i] = nums_[j];
        nums_[j] = googooli;
    }

    public static void inverse(int[] nums_, int start, int end) {
        int left = start;
        int right = end;
        while (left < right) {
            swap(nums_, left, right);
            left++;
            right--;
        }
    }


    public static int sum(int[] nums) {
        int sumArray = 0;
        for(int i=1; i<nums.length; i++) {
            sumArray += Math.abs(nums[i] - nums[i-1]); 
        } 
        return sumArray; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n]; //doesn't change
        int[] nums_ = new int[n];
        
        for (int i=0; i<n; i++) {
            nums[i] = scanner.nextInt();
            nums_[i] = nums[i];
        }
        int maxSum = sum(nums); 

        for (int i=2; i<n+1; i++) {
            for (int j=0; j<n-i+1; j++) {
                inverse(nums_, j, j+i-1); 
                int s = sum(nums_);
                if (s > maxSum) maxSum = s;
                inverse(nums_, j, j+i-1);
            }
        }

        System.out.println(maxSum);
        scanner.close();
    }
}
