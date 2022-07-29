package day1.P3055;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class P3055 {
    //                       좌, 우, 상, 하
    static final int[] mx = {-1, 1, 0, 0};
    static final int[] my = {0, 0, -1, 1};
    static int R, C;
    static char[][] map;
    static int[][] visit;
    static Node D, S;
    static Queue<Node> q;
    static boolean foundAnswer;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/day1/P3055/input.txt"));
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();

        map = new char[R][C];
        visit = new int[R][C];
        q = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String temp = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);
                if(map[i][j] == 'S')
                    S = new Node(i, j, map[i][j]);
                else if (map[i][j] == 'D')
                    D = new Node(i, j, map[i][j]);
                else if (map[i][j] == '*')
                    q.add(new Node(i, j, '*'));
            }
        }

        q.add(S);

        while(!q.isEmpty()){

            // 1. 큐에서 가져옴 -> *, S, ., D
            Node cur = q.poll();

            // 2. 목적지인가? -> D
            if(cur.type == 'D'){
                foundAnswer = true;
                break;
            }
            // 3. 연결된 곳을 순회 -> 좌 우 상 하
            for (int dir = 0; dir < 4; dir++) {
                int ny = cur.y + my[dir];
                int nx = cur.x + mx[dir];
                // 4. 갈 수 있는가? -> (공통) : 맵안에 들어오는가
                if(0 <= ny && ny < R && 0 <= nx && nx < C){
                    if(cur.type == 'S' || cur.type == '.'){
                        // 4. 갈 수 있는가? -> (고슴도치) : . / D / 방문하지 않은곳
                        if ((map[ny][nx] == '.' || map[ny][nx] == 'D') && visit[ny][nx] == 0){
                            // 5. 체크인 (고슴도치) -> visit[][] = 이동거리
                            visit[ny][nx] = visit[cur.y][cur.x] + 1;
                            // 6. 큐에 넣음
                            q.add(new Node(ny, nx, map[ny][nx]));
                        }
                    } else if (cur.type == '*') {
                        // 4. 갈 수 있는가? -> (물) : .
                        if (map[ny][nx] == '.' || map[ny][nx] == 'S'){
                            // 5. 체크인 (물) : map[][] = '*'
                            map[ny][nx] = '*';
                            // 6. 큐에 넣음
                            q.add(new Node(ny, nx, '*'));
                        }
                    }
                }
            }
        }

        if (foundAnswer)
            System.out.println(visit[D.y][D.x]);
        else
            System.out.println("KAKTUS");

    }

    static class Node{
        int y;
        int x;
        char type;

        public Node(int y, int x, char type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }
    }

}





//package day1.P3055;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.util.*;
//
//public class P3055 {
//    //                       좌, 우, 상, 하
//    static final int[] mx = {-1, 1, 0, 0};
//    static final int[] my = {0, 0, -1, 1};
//    static int R, C;
//    static char[][] map;
//    static int[][] visit;
//    static Node D, S;
//    static List<Node> water;
//    static Queue<Node> q = new LinkedList<>();
//    public static void main(String[] args) throws FileNotFoundException {
//        System.setIn(new FileInputStream("src/day1/P3055/input.txt"));
//        Scanner sc = new Scanner(System.in);
//
//        R = sc.nextInt();
//        C = sc.nextInt();
//
//        map = new char[R][C];
//        visit = new int[R][C];
//        water = new ArrayList<>();
//
//        for (int i = 0; i < R; i++) {
//            String temp = sc.next();
//            for (int j = 0; j < C; j++) {
//                map[i][j] = temp.charAt(j);
//                if(map[i][j] == 'S')
//                    S = new Node(i, j, map[i][j]);
//                else if (map[i][j] == 'D')
//                    D = new Node(i, j, map[i][j]);
//                else if (map[i][j] == '*')
//                    water.add(new Node(i, j, map[i][j]));
//            }
//        }
//
//        for (int i = 0; i < water.size(); i++) {
//            q.add(water.get(i));
//        }
//        q.add(S);
//        int step = 0;
//        while(!q.isEmpty()){
//            Node cur = q.poll();
//            visit[cur.x][cur.y]++;
//            System.out.println(cur.type);
//            if (cur.type == '*'){
//                for (int dir = 0; dir < 4; dir++){
//                    int ny = cur.y + my[dir];
//                    int nx = cur.x + mx[dir];
//                    if(ny < 0 || ny >= C || nx < 0 || nx >= R) continue;
//                    if(visit[nx][ny] > 0) continue;
//                    map[nx][ny] = '*';
//                    q.add(new Node(nx, ny, map[nx][ny]));
//                }
//            }else if (cur.type == 'S' || cur.type == '.'){
//                for (int dir = 0; dir < 4; dir++){
//                    int ny = cur.y + my[dir];
//                    int nx = cur.x + mx[dir];
//                    if(ny < 0 || ny >= C || nx < 0 || nx >= R) continue;
//                    if(map[nx][ny] == '*' || map[nx][ny] == 'X') continue;
//                    if(visit[nx][ny]>0) continue;
//
//                    //visit[nx][ny] = visit[cur.x][cur.y]+1;
//                    visit[nx][ny]++;
//
//                    if (map[nx][ny] == '.' || map[nx][ny] == 'D')
//                        q.add(new Node(nx, ny, map[nx][ny]));
//                }
//            }
//        }
//        System.out.println(visit[D.y][D.x]);
//
//        for (int i = 0; i < R; i++) {
//            System.out.println("\n");
//
//            for (int j = 0; j < C; j++) {
//                System.out.print(visit[i][j]+ " ");
//            }
//        }
//
//    }
//
//    static class Node{
//        int x;
//        int y;
//        char type;
//
//        public Node(int x, int y, char type) {
//            this.x = x;
//            this.y = y;
//            this.type = type;
//        }
//    }
//
//}
