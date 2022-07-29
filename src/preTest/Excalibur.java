package preTest;

import java.util.*;
import java.io.*;
import java.io.IOException;

public class Excalibur {

    public static int t, n, m, k, startX, startY, SX, SY, AX, AY, BX, BY, CX, CY;
    public static String map[][];
    public static boolean visited[][];
    public static Queue<Node> q;
    public static BufferedReader br;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/ex_input.txt"));

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
        startX = Integer.valueOf(st.nextToken()) - 1;
        startY = Integer.valueOf(st.nextToken()) - 1;
        map = new String[n][m];

        for (int i = 0; i < n; i++){
            String[] str = br.readLine().split("");
            for (int j = 0; j < m; j++){
                map[i][j] = str[j];
                if (map[i][j].equals("S")){
                    SX = i;
                    SY = j;
                }else if (map[i][j].equals("A")){
                    AX = i;
                    AY = j;
                }else if (map[i][j].equals("B")){
                    BX = i;
                    BY = j;
                }else if (map[i][j].equals("C")){
                    CX = i;
                    CY = j;
                }
            }
        }

        int sToA = bfs(startX, startY, AX, AY); // 시작점-> A
        int sToB = bfs(startX, startY, BX, BY);
        int sToC = bfs(startX, startY, CX, CY);

        int aToB = bfs(AX, AY, BX, BY); // A -> B
        int aToC = bfs(AX, AY, CX, CY); // A -> C

        int bToA = bfs(BX, BY, AX, AY);
        int bToC = bfs(BX, BY, CX, CY);

        int cToA = bfs(CX, CY, AX, AY);
        int cToB = bfs(CX, CY, BX, BY);

        int aToS = bfs2(AX, AY, SX, SY);
        int bToS = bfs2(BX, CY, SX, SY);
        int cToS = bfs2(CX, CY, SX, SY);

        List<Integer> resultSet = new ArrayList<>();

        // A->B->C->S
        resultSet.add(sToA + aToB + bToC + cToS);
        // A->C->B->S
        resultSet.add(sToA + aToC + cToB + bToS);
        // B->A->C->S
        resultSet.add(sToB + bToA + aToC + cToS);
        // B->C->A->S
        resultSet.add(sToB + bToC + cToA + aToS);
        // C->A->B->S
        resultSet.add(sToC + cToA + aToB + bToS);
        // C->B->A->S
        resultSet.add(sToC + cToB + bToA + aToS);

        System.out.println("#"+(t)+" "+Collections.min(resultSet));

    }

    public static int bfs(int x, int y, int targetX, int targetY){
        visited = new boolean[n][m];
        q = new LinkedList<>();

        q.add(new Node(x, y, 0));

        while (!q.isEmpty()){
            Node node = q.poll();
            visited[node.x][node.y] = true;
            // 상
            if(node.x-1 >= 0 && node.x-1 < n && !visited[node.x-1][node.y] && !map[node.x-1][node.y].equals("X")){
                q.add(new Node(node.x-1,node.y,node.depth+1));
            }

            // 하
            if(node.x+1 >= 0 && node.x+1 < n && !visited[node.x+1][node.y] && !map[node.x+1][node.y].equals("X")){
                q.add(new Node(node.x+1, node.y,node.depth+1));
            }

            // 좌
            if(node.y-1 >= 0 && node.y-1 < m && !visited[node.x][node.y-1] && !map[node.x][node.y-1].equals("X")){
                q.add(new Node(node.x,node.y-1,node.depth+1));
            }

            // 우
            if(node.y+1 >= 0 && node.y+1 < m && !visited[node.x][node.y+1] && !map[node.x][node.y+1].equals("X")){
                q.add(new Node(node.x,node.y+1,node.depth+1));
            }

            if (visited[targetX][targetY]){
                return node.depth;

            }
        }

        return 9999;
    }

    public static int bfs2(int x, int y, int targetX, int targetY){
        visited = new boolean[n][m];
        q = new LinkedList<>();

        q.add(new Node(x, y, 0));

        while (!q.isEmpty()){
            Node node = q.poll();
            visited[node.x][node.y] = true;
            // 상
            if(node.x-1 >= 0 && node.x-1 < n && !visited[node.x-1][node.y]){
                q.add(new Node(node.x-1,node.y,node.depth+1));
            }

            // 하
            if(node.x+1 >= 0 && node.x+1 < n && !visited[node.x+1][node.y]){
                q.add(new Node(node.x+1, node.y,node.depth+1));
            }

            // 좌
            if(node.y-1 >= 0 && node.y-1 < m && !visited[node.x][node.y-1]){
                q.add(new Node(node.x,node.y-1,node.depth+1));
            }

            // 우
            if(node.y+1 >= 0 && node.y+1 < m && !visited[node.x][node.y+1]){
                q.add(new Node(node.x,node.y+1,node.depth+1));
            }

            if (visited[targetX][targetY]){
                return node.depth;
            }
        }
        return 9999;
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
