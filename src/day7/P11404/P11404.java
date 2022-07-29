package day7.P11404;
// 2D. 플로이드

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11404 {

    static int N, M;
    static int[][] dp;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/day7/P11404/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i==j) dp[i][j] = 0;
                else dp[i][j] = INF;
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            dp[start][end] = Math.min(dp[start][end], Integer.parseInt(st.nextToken()));
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dp[i][j] > dp[i][k] + dp[k][j]) dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
    }
}
