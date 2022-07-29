package day9.P1932;
// 기초 DP
// 정수삼각형
// 그리디로는 불가능, 완전탐색을 해야지 알 수 있음 -> 2^n -> DP로 메모이제이션 -> N

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1932 {
    static int N, triangle[][], dp[][];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day9/P1932/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        triangle = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i+1; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        // 점화식 : dp[i][j] = max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j]
        // 왼쪽 또는 오른쪽 위 에서 큰 것 + 자기 자신
        dp[0][0] = triangle[0][0]; // 초기값
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i + 1; j++) {
                int lt = j - 1 < 0 ? 0 : dp[i - 1][j - 1];
                int rt = dp[i - 1][j];
                dp[i][j] = Math.max(lt, rt) + triangle[i][j];
            }
        }
        for (int i = 0; i < N; i++) {
            result = Math.max(result, dp[N - 1][i]); // 가장 마지막 행을 스캔하며 그 중 최댓값
        }
        System.out.println(result);
    }

}
