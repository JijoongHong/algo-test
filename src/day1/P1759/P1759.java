package day1.P1759;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class P1759 {
    static int L, C;
    static char[] data;
    static boolean[] visited;
    static int num, vowels, consonants;
    static List<String> result;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/day1/P1759/input.txt"));
        Scanner sc = new Scanner(System.in);

        L = sc.nextInt();
        C = sc.nextInt();

        visited = new boolean[C];
        result = new LinkedList<>();
        data = new char[C];

        for (int i = 0; i < C; i++) {
            data[i] = sc.next().charAt(0);
        }

        Arrays.sort(data);

        for (int i = 0; i < data.length; i++) {
            if (!visited[i])
                dfs(0, 0, 0, -1, ""); // 더미에서 시작
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    static void dfs(int lentgh, int ja, int mo, int current, String pwd){
//      1. 체크인(visited) 생략가능
        if(current >= 0)
            visited[current]=true;

//      2. 목적지인가(length==4, 자음모음개수)
        if (lentgh == L){ // 목적지인가
            if (ja >= 2 && mo >= 1){ // 정답인가
                // 정답처리
                result.add(pwd);
            }
        }else{
//        3. 연결된 곳(current + 1 ~ C)
            for (int i = current+1; i < C; i++) {
//          4. 갈 수 있는가(정렬되었다면 다 갈 수 있음 )
//              5. 간다(모음)
                if(data[i] == 'a' || data[i] == 'e' || data[i] == 'i' || data[i] == 'o' || data[i] == 'u'){
                    dfs(lentgh+1, ja, mo+1, i, pwd + data[i]);
                }else{
                    //5. 간다(자음)
                    dfs(lentgh+1, ja+1, mo, i, pwd + data[i]);
                }
            }
        }
//      6. 체크아웃 생략가능
    }
}

// 정렬되지않았다면 앞뒤로 가보기, 갈 수 있는 곳의 체크 필요