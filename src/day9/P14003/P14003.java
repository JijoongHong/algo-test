package day9.P14003;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// dp1G. 가장 긴 증가하는 부분 수열 5(LIS)
// 4번은 DP로 풀 수 있음
// 이분탐색 활용

public class P14003 {
    static int N, LIS[];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day9/P14003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        LIS = new int[N + 1]; // 길이가 I인 LIS의 마지막 원소
        LIS[0] = Integer.MIN_VALUE;
        int length = 0;
        ArrayList<Integer> result = new ArrayList<>(); // LIS 하나씩 찾으며 추가
        int[] LISIndex = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            // LIS 배열의 가장 마지막 원소보다 Ai가 크다면 LIS 마지막 공간에 삽입
            if (LIS[length] < arr[i - 1]) {
                LIS[++length] = arr[i - 1];
                LISIndex[i] = length; // 원소 각각마다 어느 위치에 저장되었는지 기록
            }
            // 그렇지 않으면 이분 탐색해서 LIS 중간에 삽입
            else {
                int index = binarySearch(1, length, arr[i - 1]); // a[i]가 삽입될 위치 찾음
                LIS[index] = arr[i - 1];
                LISIndex[i] = index;
            }
        }

        for (int i = N; i > 0 && length > 0; i--) { // 왼쪽으로 진행하면서 LIS의 길이로 저장된 것이 있는지
            if (length == LISIndex[i]) { // 있다면
                result.add(arr[i - 1]); // 원소를 더해줌
                length--; // LIS의 길이를 하나씩 줄임
            }
        }
        bw.write(result.size() + "\n");
        for (int i = result.size() - 1; i >= 0; i--) { // 역순 출력
            bw.write(result.get(i) + " ");
        }
        bw.flush();
        bw.close();
        bw.close();

    }

    static int binarySearch(int left, int right, int x) {
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (x > LIS[mid]) left = mid + 1;
            else right = mid;
        }
        return right;
    }

}
