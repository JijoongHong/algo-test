package day2.P2805;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//parametric search skanwkfmrl

public class P2805 {

    static int N, M;
    static int[] tree;
    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/day2/P2805/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        tree = new int[N];
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }

        long sum = 0, start = 0, end = 0, mid = 0, result = 0;

        Arrays.sort(tree);
        end = tree[N-1];
        start = 0;


        while (true) {
            mid = (end+start) / 2;
            sum = calc(mid);
            // sum > M
            if (sum > M){
                result = mid;
                start = mid + 1;
            }
            // sum == M
            else if ( sum == M ){
                result = mid;
                break;
            }
            else if ( sum < M){
                end = mid - 1;
            }
            // sum < M

            if (start > end) // 돌고나서 검증
                break;
        }

        System.out.println(result);
    }

    static long calc(long mid){

        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += Math.max(0, tree[i]-mid);
        }
        return sum;
    }
}
