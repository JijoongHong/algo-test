//후보 추천하기
package day1.P1713;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class P1713 {

    static int N, K;
    static Nominee[] nominees;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/day1/P1713/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        nominees = new Nominee[101]; // 순차탐색 방지

        List<Nominee> list = new ArrayList<>();
        for (int k = 0; k < K; k++) {
            int num = sc.nextInt();
            // 해당 후보가 최초 호출 시
            if (nominees[num] == null) { // 넣은 게 아니고 이런 후보가 있다
                nominees[num] = new Nominee(num, 0, 0, false);
            }
            // 해당 후보가 사진들에 있을 경우
            if (nominees[num].isIn == true) {
                nominees[num].count++;
            } else {
                // 해당 후보가 사진 틀에 없음
                // 사진틀이 가득 찬 경우
                if (list.size() == N) {
                    // 정렬 후 하나 지움
                    Collections.sort(list);
                    Nominee removed = list.remove(0); // 지우면서 반환
                    removed.isIn = false;
                }
                // 사진틀에 여유가 있는 경우
                nominees[num].count = 1;
                nominees[num].isIn = true;
                nominees[num].timeStamp = k;
                list.add(nominees[num]);
            }
        }

        Collections.sort(list, new Comparator<Nominee>() {
            @Override
            public int compare(Nominee o1, Nominee o2) {
                return Integer.compare(o1.num, o2.num);
            }
        });

    }
}

class Nominee implements Comparable<Nominee>{
    int num;
    int count;
    int timeStamp;
    boolean isIn;

    public Nominee(int num, int count, int timeStamp, boolean isIn) {
        this.num = num;
        this.count = count;
        this.timeStamp = timeStamp;
        this.isIn = isIn;
    }

    // 1. 추천 수(오름차순)   2. 시간(오름차순)
    @Override
    public int compareTo(Nominee o) {
        int comp = Integer.compare(count, o.count);
        if (comp == 0){
            return Integer.compare(timeStamp, o.timeStamp);
        }else{
            return comp;
        }
    }
}


