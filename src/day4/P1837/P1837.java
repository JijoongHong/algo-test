package day4.P1837;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 암호제작
public class P1837 {
    static int MAX = 1000000;
    static char[] P;
    static int K, temp;
    static boolean[] isNotPrime;
    static List<Integer> primes = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/day4/P1837/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        P = st.nextToken().toCharArray();
        K = Integer.parseInt(st.nextToken());

        // 소수 구하기
        isNotPrime = new boolean[MAX + 1];
        for (int i = 2; i < MAX; i++) {
            if (isNotPrime[i] == false) {
                primes.add(i);
                // 소수를 찾으면 배수를 TRUE
                for (int j = i * 2; j < MAX + 1; j++) {
                    isNotPrime[j] = true;
                }
            }
        }

        // 차례로 나누기
        for (int prime : primes) {
            if (prime >= K){
                break;
            }

            if (checkIsBad(prime)){
                System.out.println("BAD");
                System.out.println(prime);
                return;
            }

            System.out.println("GOOD");

        }


    }
    static boolean checkIsBad(int x){
        int ret = 0;
        for (int i = 0; i < P.length; i++) {
            ret = (ret * 10 + (P[i] - '0')) % x;
        }
        if (ret == 0)
            return true;
        else
            return false;

    }

}
