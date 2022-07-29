package day8.P3830;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1H. 교수님은 기다리지 않는다
// 그래프, 서로소조합
// 시간제한 2초
// 입력 10만, 10만 -> 둘 중 하나를 로그 스케일로 떨어뜨려야함
// 문제를 본다. 질의는 M, N * M이 되면 시간 초과
// lca는 트리의 형태가 동적으로 변화되어 쉽지 않음
// 경로압축저장 => union find disjoint set, 경로까지의 누적합을 저장해둠, 트리가 동적으로 변해도 가능
// 무게차이 계산 :
public class P3830 {
    static int N, M;
    static int[] parent;
    static long[] weight;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/day8/P3830/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;

            parent = new int[N + 1];
            weight = new long[N + 1];

            for (int i = 1; i <= N; i++) {
                parent[i] = i; // 자기 자신을 가리킴
            }
            int a, b, w;
            char cmd;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                cmd = st.nextToken().charAt(0);
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                if (cmd == '!') {
                    w = Integer.parseInt(st.nextToken());
                    union(a, b, w);
                }else{
                    if (find(a) != find(b)) {
                        sb.append("UNKNOWN\n");
                    }else{
                        long diff = weight[a] - weight[b];
                        sb.append(diff+"\n");
                    }
                }
            }
        }
        System.out.println(sb);
    }

    // 경로압축 -> 루트까지의 웨이트 합을 넣고 바로 루트와 연결
    // 바로 앞 부모와 자신의 웨이트를 합치고, 부모를 루트로 설정, 재귀적으로 퍼져나감
    static int find(int a) {
        if (parent[a] == a) return a;
        else {
            int p = find(parent[a]);
            weight[a] += weight[parent[a]]; // 바로앞 부모까지의 웨이트를 더함
            parent[a] = p; // 부모를 루트로 설정
            return p;
        }

    }

    static void union(int a, int b, int w) {
        // a속한 집합이 b속한 집합을 가리키도록 함
        int pa = find(a); // 루트 찾아가기 -> find 하면서 가중치 배열이 경로압축화 (a가 루트를 가리키는 가중치)
        int pb = find(b);
        if (pa == pb) return;
        weight[pa] = weight[b] - weight[a] + w; // b에서 루트 가중치 - a에서 루트 가중치 + 차이나는 가중치
        parent[pa] = pb;

    }




}
