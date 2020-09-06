package challenge.breakingbestandworstrecords;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    // Complete the breakingRecords function below.
    static int[] breakingRecords(int[] scores) {
        return new int[]{getCountBreaks(scores, RecordBreakType.BEST), getCountBreaks(scores, RecordBreakType.WORST)};
    }

    enum RecordBreakType {
        BEST {
            @Override
            public boolean compare(int current, int old) {
                return current > old;
            }
        }, WORST {
            @Override
            public boolean compare(int current, int old) {
                return current < old;
            }
        };

        public abstract boolean compare(int current, int old);
    }

    private static int getCountBreaks(int[] scores, RecordBreakType recordBreakType) {
        int count = 0;
        int compare = scores[0];
        for (int s : scores) {
            if (recordBreakType.compare(s, compare)) {
                compare = s;
                count++;
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[n];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int[] result = breakingRecords(scores);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
