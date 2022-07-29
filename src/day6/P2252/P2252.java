package day6.P2252;
// 1B. 줄세우기
// 위상정렬

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2252 {

    static int N, M, indegree[];
    static ArrayList<Integer>[] adj;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/day6/P2252/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new int[N + 1]; // 인디그리
        adj = new ArrayList[N + 1]; // 인젋리스트 생성
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int succ = Integer.parseInt(st.nextToken());
            adj[pre].add(succ); // 선행자에 후행자 연결
            indegree[succ]++; // 후행자의 인디그리 증가
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) { // O(V)
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        sb = new StringBuilder();
        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" "); // 답 추가
            for (int succ : adj[now]) { // O(E)
                indegree[succ]--; // 연결된 정점들의 인디그리 감소
                if (indegree[succ] == 0) { // 앞에 연결된 선행자가 없으면 큐에 삽입
                    q.add(succ);
                }
            }
        }
        System.out.println(sb);
    }
}
