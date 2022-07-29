package day1.P1062;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class P1062 {

    static int N, K;
    static String[] words;
    static boolean[] visited;
    static int selecetedCount = 0; //선택갯수, 탈출조건
    static int max = 0;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/day1/P1062/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        words = new String[N];
        for (int i = 0; i < N; i++){
            words[i] = sc.next().replaceAll("[antic]", "");
        }


        if (K < 5){
            System.out.println("0");
            return;
        }else if (K == 26){
            System.out.println(N);
            return;
        }else{
            visited = new boolean[26];
            visited['a'-97] = true;
            visited['c'-97] = true;
            visited['n'-97] = true;
            visited['i'-97] = true;
            visited['t'-97] = true;
            selecetedCount = 5;
            max = countWords();

            for (int i = 0; i < 26; i++) {
                if(!visited[i])
                    dfs(i);
            }

            System.out.println(max);
        }
    }

    static void dfs(int index){
//      1. 체크인
        visited[index] = true;
        selecetedCount++;

//      2. 목적지인가? => 읽을 수 있는 단어 갯수 계산
        if (selecetedCount == K){
            max = Math.max(max, countWords());
        }else{
//          3. 연결된 곳 순회 => 현재 알파벳보다 큰 알파벳 (index+1~25)
            for (int i = index+1; i < 26; i++) {
//              4. 갈 수 있는가?(방문여부)
                if(!visited[i])
//                  5. 간다(호출)
                    dfs(i);
            }
        }
//      6. 체크아웃
        visited[index] = false;
        selecetedCount--;
    }

    static int countWords(){ // 읽을 수 있는지 여부 확인
        int count = 0;
        //단어를 돌면서 알파벳이 방문되었는지 확인
        for (int n = 0; n < N; n++) {
            boolean isPossible = true;
            String word = words[n];
            for (int i = 0; i < word.length(); i++) {
                if(!visited[word.charAt(i) - 'a']) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) count++;
        }
        return count;
    }
}
