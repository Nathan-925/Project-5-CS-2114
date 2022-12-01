// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Nathan Dunlap (nathan925)
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

        GUI gui = new GUI(users);
    }
}
