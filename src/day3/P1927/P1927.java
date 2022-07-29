package day3.P1927;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
// 최소힙
public class P1927 {

    static int N;

    public static void main(String[] args) {
        MinHeap mh = new MinHeap();

    }
}

class MinHeap {
    List<Integer> list;

    public MinHeap(){
        list = new ArrayList<>();
        list.add(0);
    }

    public void insert(int val) {
        // 1. leaf 마지막에 삽입
        list.add(val);
        // 2. 부모와 비교 후 조건에 맞지 않으면 스왑
        int current = list.size() - 1;
        int parent = current / 2;
        while (true) {
            if (parent == 0 || list.get(parent) <= list.get(current)) {
                break;
            }

            int temp = list.get(parent);
            list.set(parent, list.get(current));
            list.set(current, temp);

            current = parent;
            parent = current / 2;
        }
    }

    public int delete() {
        // 비어있는 경우
        if (list.size() == 1) {
            return 0;
        }

        // 1, 루트에 리프 마지막 데이터 가져옴
        int top = list.get(1);
        list.set(1, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        // 2. 자식과 비교후 조건이 맞지 않으면 스왑

        // 3. 조건이 만족되거나 리프까지 반복
        int curPos = 1;
        while (true) {
            int leftPos = curPos * 2;
            int rightPos = curPos * 2 + 1;
            // 왼쪽 자식 먼저 확인
            if (leftPos >= list.size()) {
                break;
            }
            int minValue = list.get(leftPos);
            int minPos = leftPos;
            // 오른쪽 자식 확인
            if (rightPos < list.size() && minValue > list.get(rightPos)) {
                minValue = list.get(rightPos);
                minPos = rightPos;

                // 스왑
                if (list.get(curPos) > minValue) {
                    int temp = list.get(curPos);
                    list.set(curPos, minValue);
                    list.set(minPos, temp);
                    curPos = minPos;
                }
            }

        }
        return top;
    }
}
