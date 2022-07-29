package day7.P1854;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 2H. K번째 최단경로 찾기
// ElogV -> 2백만 * 10 -> 곱하기 K
// 오로지 다익스트라로는 불가능, 응용 필요
// pq에서 K번째로 빠지는 것, 전체 다 구하면 더이상 삽입하지 않음
// Elog(v)*k -> Elog(v*k)

public class P1854 {

    static int N, M, K;
    static int[] distance;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node> adj[];

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/day7/P1854/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        Arrays.fill(distance, INF);
        distance[0] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, w));
        }

        dijkstra();

        for (int i = 0; i < M; i++) {
            if (distance[i] == INF) System.out.println("INF");
            else System.out.println(distance[i]);
        }
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int vertex = node.vertex;
            int weight = node.weight;
            if (distance[vertex] < weight) {
                continue;
            }
            for (int i = 0; i < adj[vertex].size(); i++) {
                int vertex2 = adj[vertex].get(i).vertex;
                int weight2 = adj[vertex].get(i).weight + weight;
                if (distance[vertex2] > weight2) {
                    distance[vertex2] = weight2;
                    pq.add(new Node(vertex2, weight2));
                }
            }
        }
    }
}

class Node implements Comparable<Node>{
    int vertex;
    int weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}
