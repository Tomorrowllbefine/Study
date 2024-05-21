import java.util.*;

public class Main{

    static final int N = (int)(2e6 + 10);
    static final int BASE = 131;
    static char[] str = new char[N]; // 存储字符串
    static long[] hl = new long[N]; // 正向哈希数组
    static long[] hr = new long[N]; // 逆向哈希数组
    static long[] p = new long[N]; // 基值数组

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        int round = 1;
        while(in.hasNext())
        {
            String input = in.next();
            System.out.println(input);
            if(input.equals("END")) break;
            str[0] = ' ';
            for(int i = 1; i <= input.length(); i ++) str[i] = input.charAt(i-1);
            //System.out.println(Arrays.toString(str));
            int n = (input.length()) * 2;

            for(int i = n; i > 0; i -= 2){
                str[i] = str[i/2];
                str[i-1] = 'z' + 1;
            }
            p[0] =  1;

            // 填充哈希数组
            for(int i = 1, j = n; i <= n; i ++, j --)
            {
                hl[i] = hl[i-1] * BASE + str[i] - 'a' + 1; // 正向
                hr[i] = hr[i-1] * BASE + str[j] - 'a' + 1; // 逆向
                p[i] = p[i-1] * BASE;
            }

            int res = 0;
            // 遍历中心点，二分回文子串的半径
            for(int i = 1; i <= n; i ++)
            {
                int l = 0; // 最小半径
                int r = Math.min(i - 1, n - i);
                int mid = l + r + 1 >> 1;
                while( l < r){
                    if( get(hl, i - mid, i - 1) != get(hr, n - i - mid + 1, n - i) )
                        r = mid - 1; // 半径大了
                    else l = mid;
                }

                if(str[i - l] <= 'z'){
                    res = Math.max(res, l + 1);
                }else{
                    res = Math.max(res, l);
                }
            } // for
            System.out.println("Case " + (round ++) + ": " + res);
        } // while

    }

    private static long get(long[] h, int l, int r){
        return (h[r] - h[l-1] * p[r - l + 1]);
    }
}