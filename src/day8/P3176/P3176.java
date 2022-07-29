package day8.P3176;

import java.io.*;
import java.util.*;

// 1H. 도로 네트워크
// N개의 V와 N-1개의 E, 도시를 연결하는 유일한 경로 => 사이클이 없는 연결그래프 즉 트리임
// 시간제한 1초 : N이 10만 K개 만큼 반복, DFS, BFS로는 못 풀음. 쿼리하는 갯수(k)를 못줄임. N을 최소한 로그스케일로 줄여야함
// 그래프가 트리고 경로가 유일하면, 최소공통조상을 거쳐서 가게 되어있음
// sparse 테이블을 구축하고, A와 B의 최소공통조상을 찾으며 길이를 구함
// 스택 제한이 1mb 이므로 DFS 지양
public class P3176 {

    static int N, K, D, depth[], dp[][], min[][], max[][]; // D는 2^k 의 의미임
    static List<int[]> adj[];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day8/P3176/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // N의 개수에 맞추어 최대 깊이 생성 (10만개이므로 18로 두고 해도 되지만 최적화 한 경우)
        // N이 5인경우 D = 2 => 1 10 100
        for(; (1<<D) < N; D++);

        // 배열 초기화
        depth = new int[N + 1];
        dp = new int[D][N + 1];
        // 2^k 승만큼 뛰어갔을 때 최솟값, 최댓값
        // 인접한 것 끼리(2^0)는 MIN max 동일
        // 그 다음 정점에서 2^0 이동한 것과 min, max 연산
        // 2^2 거리는 2^1 끼리 min, max 연산
        min = new int[D][N + 1];
        max = new int[D][N + 1];
        adj = new ArrayList[N + 1];

        // 인접리스트
        for (int i = 1; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        // 양방향 간선
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }

        // 트리 생성
        bfs(1);

        // sparse table 생성
        for (int j = 1; j < D; j++) {
            for (int i = 1; i < N + 1; i++) {
                dp[j][i] = dp[j - 1][dp[j - 1][i]]; //2^k승번째 부모
                min[j][i] = Math.min(min[j - 1][i], min[j - 1][dp[j - 1][i]]); // min max도 같이 계산
                max[j][i] = Math.max(max[j - 1][i], max[j - 1][dp[j - 1][i]]);
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            // 최소 공통조상을 찾아가는 과정에서 경로상의 min max 값 갱신
            int res[] = lca(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            bw.write(res[0] + " " + res[1] + "\n");
        }
        bw.close();
    }

    static void bfs(int root) {
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        depth[root] = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < adj[cur].size(); i++) {
                int[] next = adj[cur].get(i);
                int nv = next[0]; // 다음 정점
                int w = next[1]; // 다음 정점까지의 거리
                if (depth[nv] > 0) continue; // visited의 역할
                dp[0][nv] = cur; // sparse table 2^0 바로 위 부모
                max[0][nv] = min[0][nv] = w; // 부모까지의 거리로 초기값 지정
                depth[nv] = depth[cur] + 1; // 현재 노드 아래에 추가(depth++)
                q.add(nv);
            }
        }
    }

    static int[] lca(int u, int v) {
        int min_ = Integer.MAX_VALUE;
        int max_ = Integer.MIN_VALUE;

        if (depth[u] > depth[v]){
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[v] - depth[u];
        // 깊이 맞추기
        for (int i = 0; i <= D; i++) {
            if ((diff & (1 << i)) > 0) { // 이진화 하여 1 이상인 경우
                min_ = Math.min(min_, min[i][v]); // 정점의 높이를 맞출 때 min max 갱신
                max_ = Math.max(max_, max[i][v]);
                v = dp[i][v];
            }
        }

        // 최소공통조상까지 건너뜀
        if (u != v) {
            for (int i = D - 1; i >= 0; i--) {
                if (dp[i][u] != dp[i][v]) {
                    min_ = Math.min(min_, Math.min(min[i][u], min[i][v]));
                    max_ = Math.max(max_, Math.max(max[i][u], max[i][v]));
                    u = dp[i][u];
                    v = dp[i][v];
                }
            }
            // 바로 위쪽 부모값까지 고려하며 min max 갱신
            min_ = Math.min(min_, Math.min(min[0][u], min[0][v]));
            max_ = Math.max(max_, Math.max(max[0][u], max[0][v]));
        }
        return new int[]{min_, max_};
    }


}
