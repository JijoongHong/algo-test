package day6.P1717;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1A. 집합의 표현
// Union Find
public class  P1717 {

    static int M, N;
    static int parent[], depth[]; // union 최적화를 위한 depth 배열
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day6/P1717/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        depth = new int[N + 1];

        // initialize
        for (int i = 1; i < N+1; i++) {
            parent[i] = i;
            depth[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command == 0) {
                union(a, b);
            } else if (command == 1) {
                sb.append((find(a) == find(b) ? "YES" : "NO") + "\n"); // 따로 해야함!
            }
        }

        System.out.println(sb);
    }


    static int find(int x) {
        if (parent[x] == x) return parent[x] = x; // 루트면 리턴
        else return parent[x] = find(parent[x]); // 아니면 타고 들어감, 모든 노드의 부모를 루트로 만들어줌

        // return parent[x] = (parent[x] == x) ? x : find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x); // 각자의 루트 찾기
        y = find(y);
        if (x != y) {
            if (depth[x] < depth[y]) { // 무조건 x가 더 크게 만들고
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x; // y를 x에 붙임
            if (depth[x] == depth[y]) depth[x]++; // x와 y의 높이가 같다면 하나 더 늘려줌
        }
    }
}
