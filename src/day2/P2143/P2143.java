package day2.P2143;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// 투포인터 두 배열의 합
public class P2143 {

    static long T;
    static  int N, M;
    static long[] A, B;
    public static void main(String[] args) throws Exception{

        System.setIn(new FileInputStream("src/day2/P2143/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Long.parseLong(br.readLine());

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new long[N];
        for (int i = 0; i < N; i++) {
            A[i] =  Long.parseLong(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        B = new long[M];
        for (int i = 0; i < M; i++) {
            B[i] =  Long.parseLong(st.nextToken());
        }

        // Sub 배열 구하기 및 정렬
        List<Long> subA = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            long sum = A[i];
            subA.add(sum);
            for (int j = i + 1; j < N; j++) {
                sum += A[j];
                subA.add(sum);
            }
        }
        List<Long> subB = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            long sum = B[i];
            subB.add(sum);
            for (int j = i + 1; j < M; j++) {
                sum += B[j];
                subB.add(sum);
            }
        }

        // 정렬
        Collections.sort(subA); // 1 1 2 3 3 4 4 5 6 7
        Collections.sort(subB, Comparator.reverseOrder()); // 6 5 4 3 2 1

        // 2 pointer
        long result = 0;
        int ptA = 0;
        int ptB = 0;
        while (true) {
            long currentA = subA.get(ptA);
            long target = T - currentA;
            // currentB == target -> subA, subB 같은 수 개수 체크 -> 답구하기, ptA++, ptB++
            if (subB.get(ptB) == target) {
                long countA = 0;
                long countB = 0;

                while (ptA < subA.size() && subA.get(ptA) == currentA) { //if같은 while
                    countA++;
                    ptA++;
                }

                while (ptB < subB.size() && subB.get(ptB) == target) { //if같은 while
                    countB++;
                    ptB++;
                }

                result += countA * countB;

            }
            // currentB > target -> ptB++
            else if (subB.get(ptB) > target) {
                ptB++;
            }
            // currentB < target -> ptA++
            else {
                ptA++;
            }

            // 탈출조건(pt가 범위를 넘어감)
            if (ptA == subA.size() || ptB == subB.size()) {
                break;
            }
        }

        System.out.println(result);
    }



}
