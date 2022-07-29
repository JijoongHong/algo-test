package day8.P5179;
// 2G.거의 최단경로
// 다익스트라
// 시간복잡도
// v = 500 e = 10000 => v^2 가능, e^2 불가능
// 최단거리(다익스트라) : ElogE * 2
// 최단거리 지우기 : dfs + 백트래킹 : V+E 
// 최단거리 구하기
// 최단거리 간선 지우기
// 다시 최단거리 구하기
// 인접리스트 사용 시 : 지워야할 애들 미리 배열에 저장하고, 정렬하면 구현하기 어려울듯
// 인접행렬 사용 시 : 도착지에서 출발지로 가는 간선이 존재하는지 확인할 때 역방향 전환이 매우 간편함 O(1)

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class P5179 {

    static int N, M, S, D;
    static int INF = Integer.MAX_VALUE;
    static int dist[], adj[][]; // 인접 행렬 -> 정점을 하나 뺄 때 마다 V개만큼의 시간복잡도가 추가됨
    static int maxN = 500;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day8/P5179/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            adj = new int[maxN][maxN];
            dist = new int[maxN];

            // 인접행렬
            int u, v, p;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                p = Integer.parseInt(st.nextToken());
                adj[u][v] = p;
            }

            dijkstra(S);
            removeShortest();
            dijkstra(S);

            if (dist[D] == INF) System.out.println(-1);
            else System.out.println(dist[D]);
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Route> pq = new PriorityQueue<Route>();
        pq.add(new Route(start, 0));
        Arrays.fill(dist, INF);
        dist[start] = 0;
        while (!pq.isEmpty()) {
            Route cur = pq.poll();
            if (dist[cur.vertex] < cur.weight) continue;
            for (int i = 0; i < N; i++) {
                if (adj[cur.vertex][i] == 0) continue; // 간선의 비용이 0이라면 지워진 간선으로 침
                int next = i; // 이하는 기존 다익스트라
                int weight = cur.weight + adj[cur.vertex][i];
                if (dist[next] > weight){
                    dist[next] = weight;
                    pq.add(new Route(next, weight));
                }
            }
        }

    }

    static void removeShortest() {
        // BFS 통한 간선 제거
        Queue<Integer> q = new LinkedList<>();
        q.add(D); // 도착지에서 출발
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < N; i++) { // 인접행렬이므로 정점개수만큼 포문
                // 도착지로 도달하는 간선이 있고 && 도착지까지 거리 == 시작~i까지의 거리 + i~도착지까지의 거리
                if (adj[i][cur] != 0 && dist[cur] == dist[i] + adj[i][cur]) { // cur는 간선의 역순
                    adj[i][cur] = 0; // 해당 간선을 지운다
                    q.add(i);
                }
            }
        }
    }
}

class Route implements Comparable<Route> {
    int vertex;
    int weight;

    public Route(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Route o) {
        return this.weight - o.weight;
    }
}