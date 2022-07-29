package day3.P2042;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//인덱스트리
public class P2042 {

    static int N, M, K;
    static long[] nums;
    static long[] tree;
    static int S;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY03/P2042/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new long[N];
        for (int i = 0; i < N ; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        S = 1;
        while (S < N) {
            S *= 2; // 커버 못해서 두배 늘림
        } // 12345 -> S = 8

        tree = new long[S * 2]; // 0더미노드 + 15트리

    }

    static void initBU() {
        // leaf에 값 반영
        for (int i = 0; i < N; i++) {
            tree[S+i] = nums[i];
        }

        // 내부 노드 채움
        for (int i = S-1; i > 0 ; i--) {
            tree[i] = tree[i*2] + tree[i * 2 + 1];
        }
    }

    static long query(int left, int right, int node, int queryLeft, int quertRight){
        // 연관 없음 -> 결과에 영향 업는 값 리턴
        if (quertRight < left || right < quertRight){
            return 0;
        }
        // 판단 가능 -> 현재 노드 값 리턴
        else if (queryLeft <= left && right <= quertRight) {
            return tree[node];
        }
        // 판단 불가, 자식 위임, 자식에서 올라온 합을 리턴
        else {
            int mid = (left + right) / 2;
            long resultLeft = query(left, mid, node * 2, queryLeft, quertRight);
            long resultRight = query(mid + 1, right, node * 2, queryLeft, quertRight);
            return resultLeft + resultRight;
        }
    }

    static void update(int left, int right, int node, int target, long diff) {
        // 연관없음
        if (target < left || right < target) {
            return;
        } else {
          tree[node] += diff;
          if (left != right) {
              // 리프인지 아닌지 확인
              int mid = (left + right) / 2;
              update(left, mid, node * 2, target, diff);
              update(mid + 1, right, node * 2, target, diff);
          }
        }
        // 연관있음 -> 현재 노드에 diff 반영 -> 자식에게 diff 전달
    }

    static long queryBU(int queryLeft, int queryRight) {
        // Leaf에서 left, right  설정
        int left = S + queryLeft - 1;
        int right = S + queryRight - 1;
        long sum = 0;
        while (left <= right) {// left, right 뒤틀리기 전 까지(=는 구간이 하나일 때)
            // 좌측 노드가 홀수이면 현재 노드 값 사용하고 한칸 우로 옆으로
            if (left % 2 == 1) { // 오른쪽 자식 이므로 현재 값
                sum += tree[left++];
            }
            // 우측 노드가 짝수이면 현재 노드 값 사용학 한칸 좌로 옆으로
            if (right % 2 == 0){ // 왼쪽 자식 이므로 현재 값
                sum += tree[right--];
            }
            // 좌측, 우측 모두 부모 이동   (level 낮추기)
            left /= 2;
            right /= 2;
        }
        return sum;
    }

    static void updateBU(int target, long value){
        // 리프에서 타겟 찾기
        int node = S + target - 1;
        // value 변경
        tree[node] = value;
        //루트에 도달할 때 까지 부모에 값 반영
        node /= 2; // 부모 이동
        while (node > 0) {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
            node /= 2;
        }


    }

}
