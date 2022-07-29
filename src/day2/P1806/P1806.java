package day2.P1806;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 두 배열의 합
public class P1806 {

    static int N, S;
    static int[] data;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day2/P1806/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        data = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        int sum = data[0], lo = 0, hi = 0, result = Integer.MAX_VALUE;

        while (true) {
            // sum == S
            if (sum >= S){
                result = Math.min(result, hi - lo + 1);
                sum -= data[lo++];
            }
            // sum < S
            else if (sum < S){
                sum += data[++hi];
            }
            if (hi == N)
                break;
        }
        if (result == Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(result);
    }

}
