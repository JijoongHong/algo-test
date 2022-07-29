//package day7.P11266;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//// 2. 단절점
//// dfs 가능?
//public class P11266 {
//
//    static int V, E;
//    static ArrayList<Integer> adj[];
//    static boolean visited[];
//
//    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("/src/day7/P11266/input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        V = Integer.parseInt(st.nextToken());
//        E = Integer.parseInt(st.nextToken());
//
//        adj = new ArrayList[V+1];
//        for (int i = 1; i < V; i++) {
//            adj[i] = new ArrayList<>();
//        }
//
//        for (int i = 0; i < E; i++) {
//            st = new StringTokenizer(br.readLine());
//            int u = Integer.parseInt(st.nextToken());
//            int v = Integer.parseInt(st.nextToken());
//            adj[u].add(v);
//            adj[v].add(u);
//        }
//    }
//
//    static int dfs(int cur, boolean isRoot) {
//        order[cur] = ++cnt;
//        int ret = cnt;
//        int child = 0;
//        for (int next : adj[cur]) {
//            if(order[next] == 0){
//                child++;
//            }
//        }
//
//
//    }
//}
