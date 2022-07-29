import java.util.*;
import java.io.*;

public class Main {

    public static int t, m, n, d;
    public static Integer[] map;
    public static int[] fuel;
    public static int[] cut;
    public static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in)); //선언
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i<t; i++){
            logic(i);
        }
    }

    public static void logic(int t) throws IOException{
        long result = 0;
        long temp = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new Integer[n*m];
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                map[i*n + j] = Integer.parseInt(st.nextToken());
            }
        }

        fuel = new int[d];
        cut = new int[n*m]; // 가장 최근에 잘린 날 저장

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < d; i++){
            fuel[i] = Integer.parseInt(st.nextToken());
        }

        // 내림차순 정렬
        Arrays.sort(map, Collections.reverseOrder());

        int idx = 0;
        int day = 1; // 자란 양 저장
        for (int i = 0; i<d; i++){
            // 오늘의 연료 량 만큼 대상의 값을 저장 시킨 후 1로 변경
            for (int j = 0; j<fuel[i]; j++){
                temp += map[idx] -1 + day - cut[idx];
                map[idx] =  1;
                cut[idx] = day;
                idx = (idx + 1) % (n*m);
            }
            day++;
            // result에 날짜 곱한 값 저장
            result += temp*(i+1);
            temp = 0;
        }

        System.out.println("#"+(t+1)+" "+result);

    }
}






//import java.util.*;
//import java.io.*;
//
//public class Main {
//
//    public static int TC;
//    public static int N, M, D;
//    public static Integer[][] grasses;
//    public static int[] fuel;
//    public static long total;
//
//    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/grass/input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        TC = Integer.parseInt(br.readLine());
//        StringTokenizer st;
//
//        for (int tc = 0; tc < TC; tc++) {
//
//            st = new StringTokenizer(br.readLine());
//            N = Integer.parseInt(st.nextToken());
//            M = Integer.parseInt(st.nextToken());
//            D = Integer.parseInt(st.nextToken());
//
//            total = 0;
//            fuel = new int[D];
//            grasses = new Integer[M * N][2];
//
//            for (int i = 0; i < N; i++) {
//                st = new StringTokenizer(br.readLine());
//                for (int j = 0; j < M; j++) {
//                    Integer[] temp = {Integer.parseInt(st.nextToken()) + 1, 0};
//                    grasses[i * M + j] = temp;
//                }
//            }
//
//            Arrays.sort(grasses, new Comparator<Integer[]>() {
//                @Override
//                public int compare(Integer[] o1, Integer[] o2) {
//                    return Integer.compare(o2[0], o1[0]);
//                }
//            });
//
//            st = new StringTokenizer(br.readLine());
//            for (int i = 0; i < D; i++) {
//                fuel[i] = Integer.parseInt(st.nextToken());
//            }
//
//            int round = 0;
//            int idx = 0;
//            while (round < D) {
//                long thisRound = 0;
//                for (int i = 0; i < fuel[round]; i++) {
//                    Integer[] current = grasses[idx];
//                    thisRound += (round + 1) * (current[0] - 1 + round - current[1]);
//                    current[0] = 1;
//                    current[1] = round;
//                    idx = (idx + 1) % (M * N);
//                }
//                total += thisRound;
//                round++;
//            }
//
//            System.out.format("#%d %d\n", tc + 1, total);
//        }
//    }
//}