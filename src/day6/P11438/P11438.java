package day6.P11438;
// 1D. LCA2
// stack size 1MB -> 함수개당 12바이트~20바이트 -> call depth 최대 10만개이므로 200만바이트, 1.9메가 소요 -> DFS로 하면 안됨(5만개 이하 가능)
//



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P11438 {
    static int N, M;
    static int[][] dp;
    static int[] depth;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day6/P11438/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        dp = new int[18][N+1];
        depth = new int[N + 1];
        boolean[] vst = new boolean[N + 1]; // visited, 트리 생성 시 플래그 담을 배열

        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 1; i < N+1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        // BFS
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        vst[1] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < adj[cur].size(); i++) {
                int next = adj[cur].get(i);
                if (vst[next]) {
                    continue;
                }

                dp[0][next] = cur; // BFS 하면서 채우기
                vst[next] = true;
                depth[next] = depth[cur] + 1; // BFS 하면서 채우기
                q.add(next);
            }
        }
        // 트리구성 완료

        // 점화식
        for (int i = 1; i < 18; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j] = dp[i-1][dp[i-1][j]];
            }
        }

        // 쿼리
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(lca(a, b) + "\n");
        }
        System.out.println(sb);
    }

    static int lca(int a, int b) {
        // depth 맞추기
        if (depth[a] > depth[b]) { // 치환
            int temp = b;
            b = a;
            a = temp;
        }

        int gap = depth[b] - depth[a]; // 0보다 크거나 같은 양수
        for (int i = 0; i < 18; i++) { // 이진수 변환
            if ((gap & (1 << i)) > 0) { // 11 -> 1011 -> 1<<i는 2^i의 값이 나오므로 and 연산하면 1인지 아닌지 알 수 있음
                                        // 1011 & 0001 / 1011 & 0010 / 1011 & 0100 / 1011 & 1000
                b = dp[i][b]; // 졈프
            }
        }

        // 공통조상인 경우
        if (a == b) {
            return a; // a, b 둘다 가능
        }

        // 아닌 경우 이분탐색
        for (int i = 17; i >= 0 ; i--) {
            if (dp[i][a] != dp[i][b]) { // 같지 않으면 올려버림, 쓸모없는 아래부분 버림
                a = dp[i][a];
                b = dp[i][b];
            }
        }
        return dp[0][a]; // a, b 둘다 가능, 바로 위의 부모 리턴
    }

}
