package day3.P1202;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 보석도둑
// 가방, 보석 오름차순 정렬
// 보석 힙에 넣어서 넣을 수 있는 것 중 가장 비싼것 추출
public class P1202 {
    static int N, K;
    static Jewel[] jewels;
    static int[] bags;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day3/P1202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewels = new Jewel[N];
        bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 가방 오름차순 정렬
        Arrays.sort(bags);

        // 보석 무게 오름차순 정렬
        Arrays.sort(jewels, Comparator.comparingInt(Jewel::getWeight)); // 기준이 달라지는 경우

        // 보석 가격이 높은 값 기준 힙
        PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparingInt(Jewel::getPrice).reversed());

        // two pointer 개념
        int jIdx = 0;
        int result = 0;

        // 1. 남은 가방 중 제일 작은 가방을 선택 <- 정렬
        for (int i = 0; i < bags.length; i++) {
            // 2. 선택된 가방에 넣을 수 있는 남은 보석 중 가장 비싼 보석을 선택 <- 힙을 사용
            while (jIdx < N && jewels[jIdx].weight <= bags[i]){ // jidx가 보석 범위에 있고, 보석이 현재 가방보다 가벼움
                pq.add(jewels[jIdx++]);
            }
            if (!pq.isEmpty()) { // 가방에 넣을 수 있는 보석이 아직 존재
                result += pq.poll().price; // 제일 비싼 보석
            }
        }

        System.out.println(result);

    }

    static class Jewel {
        int weight;
        int price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        public int getWeight() {
            return weight;
        }

        public int getPrice() {
            return price;
        }

//        @Override // 디폴트로 사용 시 효율적
//        public int compareTo(Object o) {
//            Jewel other = (Jewel) o;
//            if (this.weight < other.price) return -1;
//            else if (this.price > other.price) return 1;
//            else return 0;
//        }
    }
}
