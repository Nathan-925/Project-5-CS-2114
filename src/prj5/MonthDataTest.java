package prj5;

import student.TestCase;

/**
 * Test class for MonthData
 * 
 * @author Nathaniel Dunlap (nathan925)
 * @version 11/18/2022
 *
 */
public class MonthDataTest extends TestCase {

    private MonthData month;

    /**
     * Sets up the test methods
     */
    public void setUp() {
        month = new MonthData("March", 1000, 100, 10, 100, 100);
    }


    /**
     * Tests all getters
     */
    public void testGetters() {
        assertEquals("March", month.getMonth());
        assertEquals(1000, month.getFollowerCount());
        assertEquals(100, month.getLikes());
        assertEquals(10, month.getPosts());
        assertEquals(100, month.getComments());
        assertEquals(100, month.getViews());
    }


    /**
     * Tests traditionalEngagement
     */
    public void testTraditionalEngagement() {
        assertEquals(20.0, month.traditionalEngagement(), 1);
    }


    /**
     * Tests engagementByReach
     */
    public void testEngagementByReach() {
        assertEquals(200.0, month.engagementByReach(), 1);
    }


    /**
     * Tests compareTo
     */
    public void testCompareTo() {
        assertTrue(month.compareTo(new MonthData("April", 100, 100, 100, 100,
            100)) > 0);
        assertTrue(month.compareTo(new MonthData("March", 100, 100, 100, 100,
            100)) > 0);
        assertTrue(month.compareTo(new MonthData("March", 1000, 100, 100, 100,
            100)) < 0);
        assertTrue(month.compareTo(new MonthData("March", 1000, 100, 10, 100,
            100)) == 0);
    }

}
