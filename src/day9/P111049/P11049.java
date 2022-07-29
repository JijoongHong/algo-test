package day9.P111049;
// DP1F. 행렬곱순서
// 사선 DP, 구간DP

// 시간복잡도 500^3 => 1억2천500 , 사선DP이므로 상단부 삼각형만 사용 => 6250만
// 비슷한 유형 많이 풀어보기!
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11049 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] rows = new int[N+1];
        st = new StringTokenizer(br.readLine());
        rows[0] = Integer.parseInt(st.nextToken());
        rows[1] = Integer.parseInt(st.nextToken());
        for(int i = 2 ; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            // c값만 가져옴
            rows[i-1] = Integer.parseInt(st.nextToken()); // 5 3
            rows[i] = Integer.parseInt(st.nextToken());   // 3 5
        }
        int[][] dp = new int[N+1][N+1];

        //j = 구간 시작점
        //i = 구간 종료점
        //k = j <= k < i
        for(int i = 1; i <= N; i++){
            for(int j = i-1; j > 0; j--){
                int min = Integer.MAX_VALUE;
                for(int k = j; k < i; k++){
                    min = Math.min(min, dp[j][k]+dp[k+1][i]+rows[i]*rows[k]*rows[j-1]);
                }
                dp[j][i] = min;
            }
        }
        System.out.print(dp[1][N]);
    }
}
