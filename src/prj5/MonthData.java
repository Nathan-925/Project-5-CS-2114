// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I 
// accept the actions of those who do.
// -- Elishah Steele (esteele21)
package prj5;

/**
 * @author esteele21
 * @version 17.11.2022
 */
public class MonthData implements Comparable<MonthData> {
    
    private String month;
    private int followerCount;
    private int likes;
    private int posts;
    private int comments;
    private int views;
    
    /**
     * Constructor for a monthData object
     * @param month the month
     * @param followerCount amount of followers in this month
     * @param likes amount of likes
     * @param posts amount of posts
     * @param comments amount of comments
     * @param views amount of views
     */
    public MonthData(String month, int followerCount, int likes, int posts, int comments, int views) {
        this.month = month;
        this.followerCount = followerCount;
        this.likes = likes;
        this.posts = posts;
        this.comments = comments;
        this.views = views;
    }
    
    /**
     * Gets the name of the month
     * @return a string
     */
    public String getMonth() {
        return month;
    }
    
    /**
     * Gets the amount of followers this month
     * @return an int indicating followers
     */
    public int getFollowerCount() {
        return followerCount;
    }
    
    /**
     * Gets the amount of likes received
     * @return an int
     */
    public int getLikes() {
        return likes;
    }
    
    /**
     * Gets the amount of posts
     * @return an int
     */
    public int getPosts() {
        return posts;
    }
    
    /**
     * Gets the amount of comments
     * @return an int
     */
    public int getComments() {
        return comments;
    }
    
    /**
     * Gets the amount of views
     * @return an int
     */
    public int getViews() {
        return views;
    }
    
    /**
     * Calculates the total engagement
     * @return an int
     */
    private int totalEngagement() {
        return this.comments + this.likes;
    }

    /**
     * Returns the percentage of engagement using the
     * traditional calculation
     * @return an int
     */
    public double traditionalEngagement() {
        return (totalEngagement() / this.followerCount) * 100;
    }
    
    /**
     * Returns the percentage of engagement by calculating
     * using the reach of the posts
     * @return an int
     */
    public double engagementByReach() {
        return (totalEngagement() / this.views) * 100;
    }
    
    /**
     *
     * @param obj another month
     * @param 
     */
    @Override
    public int compareTo(MonthData obj) {
        
        return 0;
    }

}
