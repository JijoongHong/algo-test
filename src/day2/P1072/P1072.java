package day2.P1072;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 게임 이분탐색 (logN)
public class P1072 {

    static int X, Y;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/day2/P1072/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        int Z = Y/X*100;


        if (Z >= 99){
            System.out.println(-1);
        }else {
            long start = 0;
            long end = X;
            while (start < end){
                long mid = (start + end) / 2;
                long newRate = (100 * (Y + mid) / (X + mid)); // 소숫점 없애기 위해 100부터 곱함

                // 승률 그대로
                if (newRate == Z){
                    start = mid + 1;
                }
                // 승률 변함 0
                else {
                    end = mid; // mid 도 후보값이므로 살림, parametric search에서 가장 중요한 것!
                    // 중간중간에 타임아웃 나면 이거 활용해서 로그로 변경
                }

            }
        }


    }
}
