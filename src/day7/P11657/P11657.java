package day7.P11657;
// 2 . 타임머신


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11657 {

    static int N, M;
    static int[][] edgeList;
    static long[] distance; // n * m * 최대 최소 코스트
    static long INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/day7/P11657/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edgeList = new int[M][3];
        distance = new long[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList[i][0] = Integer.parseInt(br.readLine());
            edgeList[i][1] = Integer.parseInt(br.readLine());
            edgeList[i][2] = Integer.parseInt(br.readLine());
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
