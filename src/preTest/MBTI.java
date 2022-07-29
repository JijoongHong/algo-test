package preTest;

import java.util.*;
import java.io.*;
import java.io.IOException;


public class MBTI {
    public static BufferedReader br;
    public static int t, n;
    public static int[] pay;

    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        t =  Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++){
            logic(i+1);
        }
    }

    public static void logic(int t) throws IOException {
        n = Integer.valueOf(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        pay = new int[16];
        for (int i=0; i < 16; i++){
            pay[i] = Integer.valueOf(st.nextToken());
        }

    }
}
