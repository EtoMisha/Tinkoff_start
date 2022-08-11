import java.util.Scanner;

public class Task03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int qty = scanner.nextInt();

        int minPositive = Integer.MAX_VALUE;
        int mostNegative = 0;
        int totalIncome = 0;

        for (int i = 0; i < qty; i++) {
            int num = scanner.nextInt();

            if (i % 2 == 0 ) {
                totalIncome += num;
                if (num < minPositive) {
                    minPositive = num;
                }
            } else {
                totalIncome -= num;
                if (num > mostNegative) {
                    mostNegative = num;
                }
            }
        }

        System.out.println(totalIncome + (mostNegative - minPositive) * 2);
        scanner.close();
    }
}
