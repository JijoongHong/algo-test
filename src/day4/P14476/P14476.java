package day4.P14476;
// 최대공약수 하나 빼기
// 유클리드 호제법 활용
// 누적합 응용한 누적 gcb
// LR (0~i) : A | A,B | A,B,C | A~D | A~E | A~F -> T(N)
// RL(n-1~i) : A~F | B~F | C~f | D-F | E,F | F -> T(N)
// gcd(abdef) -> gcd(LR[1], RL[3]) -> N번 수행 T(3N) / O(N)

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14476 {
    static int N;
    static int[] nums;
    static int[] gcdLtoR;
    static int[] gcdRtoL;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day4/P14476/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        gcdLtoR = new int[N];
        gcdRtoL = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // LtoR
        gcdLtoR[0] = nums[0];
        for (int i = 1; i < N; i++) {
            gcdLtoR[i] = gcd(gcdLtoR[i - 1], nums[i]);
        }
        // RtoL
        gcdRtoL[N - 1] = nums[N - 1];
        for (int i = N-2; i >= 0; i--) {
            gcdRtoL[i] = gcd(gcdRtoL[i + 1], nums[i]);
        }
        // K 선정하기
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < N; i++) {
            int gcd = 0;
            // 0
            if (i == 0) gcd = gcdRtoL[1];

            // N-1
            else if (i == N-1) gcd = gcdLtoR[N-2];
            // 중간
            else gcd = gcd(gcdLtoR[i - 1], gcdRtoL[i + 1]);

            if (nums[i] % gcd != 0 && gcd > max) {
                max = gcd;
                maxIndex = i;
            }
        }


    }

    // gcd(a, b) == gcd(b , r) == gcd(b, a % b), stop when a % b == 0 암기하기!
    static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

}
