package day3.P9202;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//boggle
// trie
// DFS
// 시작점 결정 : 트라이의 루트가 단어의 첫문자를 가지고 있는가
// A를 결정-> C갈 수 있는가? -> 가능 -> 아래로 갈 수 있는가? -> 자식이 없으므로 불가능
// 단어를 중복해서 찾을 경우 방지 -> isHit 플래그 추가, 해시맵 없어도 됨(메모리 절감), 한 턴 끝나고 clear hit 필요(나 하고 자식에게 클리어 요청 재귀로 or isWord 마킹된애들 리스트에 모아놓음)
public class P9202 {
    static int[] mx = {-1, 1, 0, 0, -2, 1, -2, 1};
    static int[] my = {0, 0, -1, 1, -1, -1, 1, 1};
    static int[] score = {0, 0, 0, 1, 2, 3, 4, 5, 11};

    static int w, b;
    static char[][] map;
    static boolean[][] visited;
    static String answer;
    static int sum, count;
    static StringBuilder sb = new StringBuilder(); // 답 작업용 저장, 스트링 넣고 뺴고 빠르게 함
    static TrieNode root = new TrieNode();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/day3/P9202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        w = Integer.parseInt(br.readLine());

        for (int i = 0; i < w; i++) {
            insertTrieNdde(br.readLine());
        }

        br.readLine();
        b = Integer.parseInt(br.readLine());
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < b; i++) {
            map = new char[4][4];
            visited = new boolean[4][4];
            answer = "";
            sum = 0;
            count = 0;
            sb = new StringBuilder();

            for (int j = 0; j < 4; j++) {
                String in = br.readLine();
                for (int k = 0; k < 4; k++) {
                    map[i][k] = in.charAt(k);
                }
            }
            br.readLine();
            // 각 맵을 순회하면서 출발
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    // 출발가능조건 -> root가 해당 child를 가지면
                    if (root.hasChild(map[y][x])) {
                        search(y, x, root.getChild(map[y][x]));
                    }
                }
            }
            // 결과 출력
            root.clearHit();
        }

        System.out.println(resultSb.toString());
    }

    static void search(int y, int x, TrieNode node) { // current 유지용
        // 1. 체크인 -> boolean[4][4]
        visited[y][x] = true;
        sb.append(map[y][x]);
        // 2. 목적지인가? -> isWord && !isHit
        if (node.isWord && !node.isHIt) {
            node.isHIt = true;
            // 답 작업 -> 길이, 단어
            String foundWord = sb.toString();
            int length = foundWord.length();
        }
        // 3. 연결된 곳 -> 8방향
        for (int i = 0; i < 8; i++) {
            int ty = y + my[i];
            int tx = x + mx[i];
            // 4. 갈 수 있는가? -> visited && 맵 내부 && trie에 존재(현재의 자식)
            if (0 <= ty && ty < 4 && 0 <= tx && tx < 4) {
                if (!visited[ty][tx] && node.hasChild(map[ty][tx])) {
                    // 5. 간다
                    search(ty, tx, node.getChild(map[ty][tx]));
                }
            }
        }

        // 6. 체크아웃 -> boolean[4][4]
        visited[y][x] = false;
        sb.deleteCharAt(sb.length() - 1); // 맨 뒤에거 삭제
    }



    static void insertTrieNdde(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char a = word.charAt(i);
            int index = a = 'A';
            if (current.child[index] == null) {
                current.child[index] = new TrieNode();
            }
            current = current.child[index];
        }
        current.isWord = true;
    }
}

class TrieNode {
    boolean isWord = false;
    boolean isHIt = false;
    TrieNode[] child = new TrieNode[26];

    void clearHit(){
        isHIt = false;
        for (int i = 0; i < child.length; i++) {
            if (child[i] != null) {
                child[i].clearHit();
            }
        }
    }

    boolean hasChild(char c) {
        return child[c-'A'] != null;
    }

    TrieNode getChild(char c){
        return child[c - 'A'];
    }
}