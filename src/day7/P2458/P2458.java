package day7.P2458;
// 키 순서
// N(N-1) / 2 싸이클 없앤 완전 그래프
// 판단하려면 나에게 들어오거나 나가야함(N-1)개
// in + out + 자신 = N
// DFS(O(V+E) * N => O(NE))
// 플로이드 워셜(N이 작음, O(V^3+V^2))로도 풀 수 있음
// DFS 로 풀 수 있는가? --> 일렬로 구성되면 스택오버플로우가 나는가
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2458 {
    static int N, M, inCnt[], outCnt[];
    static ArrayList<Integer> adj[];
    static boolean visited[];
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("/src/day7/P2458/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N+1];

        for (int i = 1; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
        }

        outCnt = new int[N + 1];
        for (int i = 1; i < N; i++) {
            visited = new boolean[N + 1];
            outCnt[i] = dfs(i) - 1;
        }

        int answer = 0;
        for (int i = 1; i < N; i++) {
            if((inCnt[i] + outCnt[i] == N -1)){
                answer++;
            }
        }
        System.out.println(answer);

    }

    static int dfs(int cur) {
        int outCnt = 0;
        for (int next : adj[cur]) {
            if (visited[next]) continue;
            inCnt[next]++;
            visited[next] = true;
            outCnt += dfs(next);
        }
        return outCnt + 1;
    }


}
