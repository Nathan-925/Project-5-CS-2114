// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Zachary Milne (906363804)
package prj5;

import student.TestCase;

/**
 * Represents a social media user with posts.
 * 
 * @author Zachary Milne (zmilne)
 * @version 2022.11.11
 */
public class UserTest extends TestCase {

    private User test;

    /**
     * Sets up a standard User object before every test method.
     */
    public void setUp() {
        test = new User("test", "theTestClass", "java", "testing");
    }


    /**
     * Tests the following method to ensure it meets expectations.
     */
    public void testGetUsername() {
        assertEquals(test.getUsername(), "test");
    }


    /**
     * Tests the following method to ensure it meets expectations.
     */
    public void testGetChannelName() {
        assertEquals(test.getChannelName(), "theTestClass");
    }


    /**
     * Tests the following method to ensure it meets expectations.
     */
    public void testGetCountry() {
        assertEquals(test.getCountry(), "java");
    }


    /**
     * Tests the following method to ensure it meets expectations.
     */
    public void testGetMainTopic() {
        assertEquals(test.getMainTopic(), "testing");
    }


    /**
     * Tests the following method to ensure it meets expectations.
     */
    public void testAdd() {
        assertEquals(test.getMonthData().size(), 0);
        MonthData jan = new MonthData("January", 13, 500, 2, 0, 1000);
        test.addData(jan);
        assertEquals(test.getMonthData().size(), 1);
    }


    /**
     * Tests getFirstQuarterTraditionalEngagement
     */
    public void testGetFirstQuarterTraditionalEngagement() {
        test.addData(new MonthData("January", 1000, 100, 100, 100, 100));
        test.addData(new MonthData("February", 1000, 100, 100, 100, 100));
        test.addData(new MonthData("March", 1000, 100, 100, 100, 100));
        test.addData(new MonthData("May", 1000, 100, 100, 100, 100));
        assertEquals(60.0, test.getTraditionalEngagement(new String[] {
            "January", "February", "March" }), 1);
    }


    /**
     * Tests getFirstQuarterReachEngagement
     */
    public void testGetFirstQuarterReachEngagement() {
        test.addData(new MonthData("January", 1000, 100, 100, 100, 100));
        test.addData(new MonthData("February", 1000, 100, 100, 100, 100));
        test.addData(new MonthData("March", 1000, 100, 100, 100, 1000));
        test.addData(new MonthData("May", 1000, 100, 100, 100, 1000));
        assertEquals(50.0, test.getReachEngagement(new String[] { "January",
            "February", "March" }), 1);
    }
}
