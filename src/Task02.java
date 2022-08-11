import java.util.*;

public class Task02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int qty = scanner.nextInt();
        scanner.nextLine();

        Map<String, Integer> teamsMap = new HashMap<>();
        int maxWin = 0;
        for (int i = 0; i < qty; i++) {
            String[] teamRaw = scanner.nextLine().split(" ");
            Arrays.sort(teamRaw);
            String team = Arrays.toString(teamRaw);

            teamsMap.merge(team, 1, Integer::sum);
            if (teamsMap.get(team) > maxWin) {
                maxWin = teamsMap.get(team);
            }
        }

        System.out.println(maxWin);
        scanner.close();
    }
}
