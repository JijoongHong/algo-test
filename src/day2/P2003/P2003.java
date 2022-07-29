package day2.P2003;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

// 시간복잡도 수들의 합 2 투포인터
public class P2003 {
    static int N, M;
    static int[] data;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/day2/P2003/input.txt"));

        BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //data = new int[N];
        data = new int[N+1];

        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

//        int lo = 0, hi = 0, sum = 0, result = 0;
//
//        while(hi != N){
//
//            for (int i = lo; i <= hi ; i++) {
//                sum += data[i];
//            }
//            // sum < M
//            if (sum < M){
//                hi++;
//            } // sum > M
//            else if (sum > M){
//                lo++;
//            } // sum == M
//            else if (sum == M){
//                result++;
//                lo++; hi++;
//            }
//            sum = 0;
//        }

        int lo = 0, hi = 0, sum = data[0], result = 0;

        while(true){

            if (sum == M){
                result++;
                sum -= data[lo++];
            }else if (sum > M)
                sum -= data[lo++];
            else if (sum < M)
                sum += data[++hi];

            if (hi == N)
                break;
        }

        System.out.println(result);
    }
}
