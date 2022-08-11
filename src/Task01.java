import java.util.Arrays;
import java.util.Scanner;

public class Task01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] valuesX = new int[4];
        int[] valuesY = new int[4];

        for (int i = 0; i < 4; i++) {
            valuesX[i] = scanner.nextInt();
            valuesY[i] = scanner.nextInt();
        }

        Arrays.sort(valuesX);
        Arrays.sort(valuesY);
        int maxSide = Math.max(valuesX[3] - valuesX[0], valuesY[3] - valuesY[0]);

        System.out.println(maxSide * maxSide);
        scanner.close();
    }
}
