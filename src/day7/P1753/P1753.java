package day7.P1753;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 2B. 최단경로
// V*E 하면 시간안에 불가능. 둘 중하나 로그 스케일로.
// ElogV = 450만
public class P1753 {

    static int V, E, start;
    static int[] distance;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Node> adj[];

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/day7/P1753/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
        Arrays.fill(distance, INF);
        distance[start] = 0;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, w));
        }

        dijkstra();

        for (int i = 0; i < V; i++) {
            if (distance[i] == INF) System.out.println("INF");
            else System.out.println(distance[i]);
        }
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
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
