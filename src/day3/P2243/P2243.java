package day3.P2243;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사탕상자
// 인덱스트리 사용 이유 : 사탕의 개수 변화가 지속적으로 이루어짐
// PQ 조건 : 두개이상의 정렬, 가장 ~~한
// 인덱스트리 조건 : 두개이상의 정렬, 개수 구하기
public class P2243 {

    static int N, M;
    static int A, B, C;
    static int MAX = 1000000;
    static int[] tree;
    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = 1;
        while (S < MAX) {
            S *= 2;
        }
        tree = new int[2 * S];
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            if (A == 2) {
                B = Integer.parseInt(st.nextToken());
                C = Integer.parseInt(st.nextToken());
                update(1, S, 1, B, C);
            } else if (A == 1) {
                B = Integer.parseInt(st.nextToken());
                int index = query(1, S, 1, B);
                update(1, S, 1, index, -1);
                System.out.println(index);
            }
        }
    }

    static int query(int left, int right, int node, int count) {
        // 1. 리프에 도착 -> 사탕 번호 변환
        if (left == right) {
            return left;
        }

        else {
            int mid = (left + right) / 2;
            // 2. 왼족 >= count -> 왼쪽으로 이동
            if (tree[node * 2] >= count) {
                return query(left, mid, node*2, count);
            }
            // 3, 왼족 < count -> 오른쪽으로 이동
            else {
                count =- tree[node * 2];
                return query(mid + 1, right, node * 2 + 1, count);
            }
        }
    }

    static void update(int left, int right, int node, int index, long diff) {
        if (left <= index && index <= right) {
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, index, diff);
                update(mid + 1, right, node * 2 + 1, index, diff);
            }
        }
    }
}
