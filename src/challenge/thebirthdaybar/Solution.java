package challenge.thebirthdaybar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the birthday function below.
    static int birthday(List<Integer> s, int d, int m) {
        int cs = s.size();
        if(cs == 1){
            return s.get(0) == d ? 1 : 0;
        }
        List<List<Integer>> matches = new ArrayList<>();
        for (int i = 0; i < cs - m; i++) {
            int ide = m + i - 2;
            for (int j = 1; j < cs - m - i; j++) {
                int idl = ide + j;
                List<Integer> sl = new ArrayList<>(s.subList(i, ide + 1));
                Integer last = s.get(idl);
                sl.add(last);
                if (d == sl.stream().mapToInt(Integer::intValue).sum()) {
                    matches.add(sl);
                }
            }
        }
        return (int) matches.stream().distinct().count();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        String[] dm = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int d = Integer.parseInt(dm[0]);

        int m = Integer.parseInt(dm[1]);

        int result = birthday(s, d, m);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
