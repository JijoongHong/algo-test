package day9.P11660;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DP 기초
// 구간합구하기 5
// M -> 쿼리의 갯수가 10만번, 이중포문 돌리면 불가능
// O(N^2)로 풀어야함
public class P11660 {
    static int N, M, dp[][];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day9/P11660/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][N + 1];

        for (int y = 1; y < N + 1; y++) {
            st = new StringTokenizer(br.readLine());
            int x_sum = 0;
            //dp[y][x] = (1,1) ~ (y, x) 영역의 합
            for (int x = 1; x < N + 1; x++) {
                x_sum += Integer.parseInt(st.nextToken());
                dp[y][x] = dp[y - 1][x] + x_sum;
            }
        }
        StringBuilder sb = new StringBuilder();
        int x1, x2, y1, y2;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            y1 = Integer.parseInt(st.nextToken());
            x1 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            int result = dp[y2][x2] - dp[y1 - 1][x2] - dp[y2][x1 - 1] + dp[y1 - 1][x1 - 1];
            // 1,1에서 끝까지 - 윗부분 - 왼쪽부분 + 겹치는 부분
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

}
