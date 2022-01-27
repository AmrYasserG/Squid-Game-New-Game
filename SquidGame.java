
public class SquidGame {

    static int sum(int arr[], int from, int to) {
        int total = 0;
        for (int i = from; i <= to; i++)
            total += arr[i];
        return total;
    }

    static String printArr(int arr[], int best, int k) {
        String result = "";
        int temp = 0;
        int remaining = arr.length;
        for (int i = 0; i < arr.length; i++) {
            if (k == remaining) {
                if (i == arr.length - 1) {
                    result += arr[i];
                } else {
                    result += arr[i] + ";";
                    k--;
                }
                remaining--;
            } else {
                if (i == arr.length - 1)
                    result += arr[i];
                else {
                    if (temp + arr[i] + arr[i + 1] > best) {
                        temp = 0;
                        result += arr[i] + ";";
                        k--;
                    } else {
                        if (i == arr.length - 1 || temp + arr[i] + arr[i + 1] > best)
                            result += arr[i];
                        else
                            result += arr[i] + ",";
                        temp += arr[i];
                    }
                }
                remaining--;
            }
        }
        return result;
    }

    public static String naive(int k, int[] l) {
        int maxNum = naive(l, l.length, k);
        String arr = printArr(l, maxNum, k);
        return maxNum + ";" + arr;
    }

    static int naive(int arr[], int n, int k) {
        if (k == 1)
            return sum(arr, 0, n - 1);
        if (n == 1)
            return arr[0];
        int best = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++)
            best = Math.min(best, Math.max(naive(arr, i, k - 1),
                    sum(arr, i, n - 1)));
        return best;
    }

    public static String efficient(int k, int[] l) {
        int maxNum = efficient(l, l.length, k);
        String arr = printArr(l, maxNum, k);
        return maxNum + ";" + arr;
    }

    static int efficient(int arr[], int n, int k) {
        int dp[][] = new int[k + 1][n + 1];
        for (int i = 1; i <= n; i++)
            dp[1][i] = sum(arr, 0, i - 1);
        for (int i = 1; i <= k; i++)
            dp[i][1] = arr[0];
        for (int i = 2; i <= k; i++) {
            for (int j = 2; j <= n; j++) {
                int best = Integer.MAX_VALUE;
                for (int p = 1; p <= j; p++)
                    best = Math.min(best, Math.max(dp[i - 1][p],
                            sum(arr, p, j - 1)));
                dp[i][j] = best;
            }
        }
        return dp[k][n];
    }

    public static void main(String[] args) {
        int[] arr = { 38, 171, 191, 197, 160, 177, 143, 30, 25, 11, 12, 5, 78, 180, 53, 17, 109, 134, 122, 183 };
        int k = 20;
        int n = arr.length;
        System.out.println(efficient(k, arr));
    }
}