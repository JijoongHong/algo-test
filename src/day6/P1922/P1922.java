package day6.P1922;
// 1C. 네트워크 연결
// 최소신장트리(크루스칼, 프림)
// V = 1000, E = 100000
// VE -> 1억 -> 간당간당, 하나는 log로 내려야함 -> E log E -> 170만

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1922 {

    static int V, E, parent[];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day6/P1922/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() { // 정렬하던 pq로 하던
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        // union find 초기화
        parent = new int[V + 1];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }


        // pq 초기화
        int a, b, c;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            pq.add(new int[]{a, b, c});
        }

        // 크루스칼 알고리즘
        int cnt = 0, total = 0;
        while (cnt < V - 1 && !pq.isEmpty()) { // 전체 정점을 돌면 종료
            int[] temp = pq.poll();
            if (find(temp[0]) != find(temp[1])){ // 각 루트가 다르다면, 즉 사이클이 없다면
                cnt++; // 사이클 증가
                union(temp[0], temp[1]); // 둘 연결
                total += temp[2]; // 총 가중치 더함
            }
        }
        System.out.println(total);
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        parent[x] = y;
    }
}

