package preTest;

import java.util.*;
import java.io.*;
import java.io.IOException;


public class antEscape{

    public static int t, n, m, k, startX, startY;
    public static String map[][];
    public static Queue<Node> q;
    public static BufferedReader br;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("src/ant_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.valueOf(br.readLine());

        for (int i = 0; i < t; i++){
            logic(i+1);
        }
    }

    public static void logic(int t) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.valueOf(st.nextToken());
        m = Integer.valueOf(st.nextToken());
        k = Integer.valueOf(st.nextToken());
        map = new String[n][m];

        for (int i = 0; i < n; i++){
            String[] str = br.readLine().split("");
            for (int j = 0; j < m; j++){
                map[i][j] = str[j];
                if (map[i][j].equals("S")){
                    startX = i;
                    startY = j;
                }
            }
        }

        bfs(startX, startY, t);

    }

    public static void bfs(int s, int e, int t){
        q = new LinkedList<>();
        long count = 0l;

        q.add(new Node(s, e, 0));

        while (!q.isEmpty()){
            Node node = q.poll();

            // 상
            if (node.x-1 < 0) count++;
            else if(node.x-1 >= 0 && node.x-1 < n && (map[node.x-1][node.y].equals(".") || map[node.x-1][node.y].equals("S"))){
                if (node.depth + 1 < k) q.add(new Node(node.x-1,node.y,node.depth+1));
            }

            // 하
            if (node.x+1 >= n) count++;
            else if(node.x+1 >= 0 && node.x+1 < n && (map[node.x+1][node.y].equals(".")||map[node.x+1][node.y].equals("S"))){
                if (node.depth + 1 < k) q.add(new Node(node.x+1, node.y,node.depth+1));
            }

            // 좌
            if (node.y-1 < 0) count++;
            else if(node.y-1 >= 0 && node.y-1 < m && (map[node.x][node.y-1].equals(".") || map[node.x][node.y-1].equals("S"))){
                if (node.depth + 1 < k) q.add(new Node(node.x,node.y-1,node.depth+1));
            }

            // 우
            if(node.y+1 >= m) count++;
            else if(node.y+1 >= 0 && node.y+1 < m && (map[node.x][node.y+1].equals(".") || map[node.x][node.y+1].equals("S"))){
                if (node.depth + 1 < k) q.add(new Node(node.x,node.y+1,node.depth+1));
            }
        }

        System.out.println("#"+(t)+" "+(count%1000000007));

    }

    static class Node{
        int x;
        int y;
        int depth;

        Node(int x, int y, int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

}
