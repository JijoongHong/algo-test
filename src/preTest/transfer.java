package preTest;

import java.io.*;
import java.util.*;

public class transfer {

    public static int t, n, m, s, e;
    public static List<Integer>[] routes, edges;
    public static BufferedReader br;

    public static void main(String[] args)  throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        t =  Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++){
            logic(i+1);
        }
    }


    public static void logic(int t)  throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 지하철 역의 수
        m = Integer.parseInt(st.nextToken()); // 지하철 노선의 수
        s = Integer.parseInt(st.nextToken()); // 시작 정거장
        e = Integer.parseInt(st.nextToken()); // 도착 정거장

        edges = new ArrayList[n+1]; // 노선에 속한 역
        routes = new ArrayList[m+1]; // 특정 역에서 갈 수 있는 노선

        int[] stationNum = new int[m]; // 호선 별 역의 수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++){
            stationNum[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < m+1; i++)
            routes[i] = new ArrayList<>();
        for (int i = 0; i < n+1; i++)
            edges[i] = new ArrayList<>();

        for (int i = 1; i < m+1; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < stationNum[i-1]; j++){
                int station = Integer.parseInt(st.nextToken());
                routes[i].add(station);
                edges[station].add(i);
            }
        }

        bfs(t);
    }

    public static void bfs(int t){

        boolean[] visitRoute = new boolean[m + 1];
        boolean[] visitStation = new boolean[n + 1];

        Queue<Node> q = new LinkedList<>();

        visitStation[s] = true; // 시작점 방문한 것으로 표시
        for (int v : edges[s]) {
            visitRoute[v] = true; // 해당 노선 방문한 것으로 표시
            q.offer(new Node(v, 0)); // 해당 노선 큐에 삽입
        }

        while (!q.isEmpty()) {
            Node n = q.poll();
            for (int v : routes[n.line]) { // 해당 호선에서 갈 수 있는 역들 순회
                if (v == e) { // 목표 역에 도착한 경우
                    System.out.println("#"+t+" "+n.count);
                    return;
                }
                if (visitStation[v]) continue; // 이미 방문한 역인 경우
                visitStation[v] = true; // 방문한 것으로 표시
                for (int nv : edges[v]) { // 해당 역에서 갈 수 있는 노선 순회
                    if (visitRoute[nv]) continue; // 이미 방문한 노선인 경우
                    visitRoute[nv] = true; // 노선 방문한 것으로 표시
                    q.offer(new Node(nv, n.count + 1)); // 해당 노선 큐에 삽입
                }
            }
        }
        System.out.println("#"+t+" -1");
    }

    static class Node{
        int line;
        int count;

        public Node(int line, int count){
            this.line = line;
            this.count = count;
        }
    }
}
