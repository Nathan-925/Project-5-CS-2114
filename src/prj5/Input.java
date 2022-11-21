package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Runs the finished project
 * 
 * @author Nathaniel Dunlap (nathan925)
 * @version 11/18/2022
 *
 */
public class Input {

    public static final String[] VALID_MONTHS = { "January", "February",
        "March", "April", "May", "June", "July", "August", "September",
        "October", "November", "December" };

    /**
     * Runs the program
     * 
     * @param args
     *            optional space for input file path
     * @throws FileNotFoundException
     *             if the specified file was not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(args.length > 0
            ? args[0]
            : "SampleInput1_2022.csv"));
        scan.nextLine();
        DoublyLinkedList<User> users = new DoublyLinkedList<>();
        while (scan.hasNextLine()) {
            String[] data = scan.nextLine().split("\\s*,\\s*");
            if (Arrays.asList(VALID_MONTHS).contains(data[0])) {
                User user = null;
                for (User u : users) {
                    if (u.getUsername().equals(data[1])) {
                        user = u;
                        break;
                    }
                }
                if (user == null) {
                    user = new User(data[1], data[2], data[3], data[4]);
                    users.add(user);
                }
                user.addData(new MonthData(data[0], Integer.parseInt(data[7]),
                    Integer.parseInt(data[5]), Integer.parseInt(data[6]),
                    Integer.parseInt(data[8]), Integer.parseInt(data[9])));
            }
        }

        users.sort((n, m) -> String.CASE_INSENSITIVE_ORDER.compare(n
            .getChannelName(), m.getChannelName()));
        for (User u : users) {
            System.out.println(u.getChannelName());
            if (Double.isFinite(u.getFirstQuarterTraditionalEngagement())) {
                System.out.printf("traditional: %.1f%n", u
                    .getFirstQuarterTraditionalEngagement());
            }
            else {
                System.out.println("traditional: N/A");
            }
            System.out.println("==========");
        }
        System.out.println("**********");
        System.out.println("**********");
        users.sort((n, m) -> Double.compare(m.getFirstQuarterReachEngagement(),
            n.getFirstQuarterReachEngagement()));
        for (User u : users) {
            System.out.println(u.getChannelName());
            if (Double.isFinite(u.getFirstQuarterReachEngagement())) {
                System.out.printf("reach: %.1f%n", u
                    .getFirstQuarterReachEngagement());
            }
            else {
                System.out.println("reach: N/A");
            }
            System.out.println("==========");
        }
    }
}
