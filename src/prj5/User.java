// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Zachary Milne (906363804)
package prj5;

/**
 * Represents a social media user with posts.
 * 
 * @author Zachary Milne (zmilne), Nathaniel Dunlap (nathan925)
 * @version 2022.11.11
 */
public class User {

    private DoublyLinkedList<MonthData> data;
    private String username;
    private String channelName;
    private String country;
    private String mainTopic;

    /**
     * Creates a new user object with specified username, channelName, country,
     * and mainTopic.
     * 
     * @param username
     *            The username of the User.
     * @param channelName
     *            The channelName of the User.
     * @param country
     *            The country of the User.
     * @param mainTopic
     *            The mainTopic of the User.
     */
    public User(
        String username,
        String channelName,
        String country,
        String mainTopic) {

        this.username = username;
        this.channelName = channelName;
        this.country = country;
        this.mainTopic = mainTopic;
        data = new DoublyLinkedList<MonthData>();
    }


    /**
     * Adds a new month of data.
     * 
     * @param newMonth
     *            A new month of data to be added.
     */
    public void addData(MonthData newMonth) {
        data.add(newMonth);
    }


    /**
     * Returns the username of this User.
     * 
     * @return The username of this User object.
     */
    public String getUsername() {
        return username;
    }


    /**
     * Returns the channel name of this User.
     * 
     * @return The channelName of this User object.
     */
    public String getChannelName() {
        return channelName;
    }


    /**
     * Returns the country of this User.
     * 
     * @return The country of this User object.
     */
    public String getCountry() {
        return country;
    }


    /**
     * Returns the main topic of this User.
     * 
     * @return The mainTopic of this User object.
     */
    public String getMainTopic() {
        return mainTopic;
    }


    /**
     * Returns the linked list of month data
     * 
     * @return the linked list of month data
     */
    public DoublyLinkedList<MonthData> getMonthData() {
        return data;
    }


    /**
     * Returns the traditional engagement for the first quarter
     * 
     * @return the traditional engagement for the first quarter
     */
    public double getFirstQuarterTraditionalEngagement() {
        double engagement = 0;
        double followers = 0;
        for (MonthData month : data) {
            if (month.getMonth().equals("January") || month.getMonth().equals(
                "February") || month.getMonth().equals("March")) {
                engagement += month.getComments() + month.getLikes();
                if (month.getMonth().equals("March")) {
                    followers = month.getFollowerCount();
                }
            }
        }
        return (engagement / followers) * 100;
    }


    /**
     * Returns the reach engagement for the first quarter
     * 
     * @return the reach engagement for the first quarter
     */
    public double getFirstQuarterReachEngagement() {
        double engagement = 0;
        double reach = 0;
        for (MonthData month : data) {
            if (month.getMonth().equals("January") || month.getMonth().equals(
                "February") || month.getMonth().equals("March")) {
                engagement += month.getComments() + month.getLikes();
                reach += month.getViews();
            }
        }
        return (engagement / reach) * 100;
    }

}
