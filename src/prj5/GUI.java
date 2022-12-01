// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Nathan Dunlap (nathan925)
package prj5;

import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import cs2.Button;
import cs2.Shape;
import cs2.TextShape;
import cs2.Window;
import cs2.WindowSide;

/**
 * Handles the window of the project
 * 
 * @author Nathaniel Dunlap (nathan925)
 * @version 12/01/2022
 *
 */
public class GUI {

    private DoublyLinkedList<User> users;
    private Button sortByChannelNameButton;
    private Button sortByEngagementRateButton;
    private Button traditionalEngagementRateButton;
    private Button reachEngagementRateButton;
    private Button januaryButton;
    private Button februaryButton;
    private Button marchButton;
    private Button firstQuarterButton;
    private Button quitButton;
    private Window window;
    private boolean reachOrTraditional;
    private String[] months;

    private Comparator<User> channelNameComparator;
    private Comparator<User> engagementRateComparator;

    private Comparator<User> activeComparator;

    /**
     * Creates a gui with input users
     * 
     * @param users
     *            the users graphed
     */
    public GUI(DoublyLinkedList<User> users) {
        this.users = users;

        reachOrTraditional = false;

        window = new Window("Social Media Visualization");
        window.setSize(800, 600);

        sortByChannelNameButton = new Button("Sort By Channel Name");
        sortByChannelNameButton.onClick(this, "channelNameButtonPressed");
        window.addButton(sortByChannelNameButton, WindowSide.NORTH);

        sortByEngagementRateButton = new Button("Sort By EngagementRate");
        sortByEngagementRateButton.onClick(this, "engagementRateButtonPressed");
        window.addButton(sortByEngagementRateButton, WindowSide.NORTH);

        traditionalEngagementRateButton = new Button(
            "Traditional Engagement Rate");
        traditionalEngagementRateButton.onClick(this,
            "traditionalEngagementRateButtonPressed");
        window.addButton(traditionalEngagementRateButton, WindowSide.WEST);

        reachEngagementRateButton = new Button("Reach Engagement Rate");
        reachEngagementRateButton.onClick(this,
            "reachEngagementRateButtonPressed");
        window.addButton(reachEngagementRateButton, WindowSide.WEST);

        januaryButton = new Button("January");
        januaryButton.onClick(this, "januaryButtonPressed");
        window.addButton(januaryButton, WindowSide.SOUTH);

        februaryButton = new Button("February");
        februaryButton.onClick(this, "februaryButtonPressed");
        window.addButton(februaryButton, WindowSide.SOUTH);

        marchButton = new Button("March");
        marchButton.onClick(this, "marchButtonPressed");
        window.addButton(marchButton, WindowSide.SOUTH);

        firstQuarterButton = new Button("First Quarter");
        firstQuarterButton.onClick(this, "firstQuarterButtonPressed");
        window.addButton(firstQuarterButton, WindowSide.SOUTH);

        quitButton = new Button("Quit");
        quitButton.onClick(this, "quitButtonPressed");
        window.addButton(quitButton, WindowSide.NORTH);

        channelNameComparator = (n, m) -> n.getChannelName().compareTo(m
            .getChannelName());

        engagementRateComparator = (n, m) -> {
            double rate1 = reachOrTraditional
                ? n.getReachEngagement(months)
                : n.getTraditionalEngagement(months);
            if (!Double.isFinite(rate1)) {
                rate1 = 0;
            }
            double rate2 = reachOrTraditional
                ? m.getReachEngagement(months)
                : m.getTraditionalEngagement(months);
            if (!Double.isFinite(rate2)) {
                rate2 = 0;
            }
            return Double.compare(rate1, rate2);
        };

        activeComparator = channelNameComparator;
        firstQuarterButtonPressed(firstQuarterButton);
    }


    /**
     * Sorts by channel name
     * 
     * @param button
     *            the button pressed
     */
    public void channelNameButtonPressed(Button button) {
        activeComparator = channelNameComparator;
        sort();
    }


    /**
     * Sorts by engagement rate
     * 
     * @param button
     *            the button pressed
     */
    public void engagementRateButtonPressed(Button button) {
        activeComparator = engagementRateComparator;
        sort();
    }


    private void sort() {
        users.sort(activeComparator);
        repaint();
    }


    /**
     * Exits the program
     * 
     * @param button
     *            the button pressed
     */
    public void quitButtonPressed(Button button) {
        System.exit(0);
    }


    /**
     * Sets the window to show traditional engagement
     * 
     * @param button
     *            the button pressed
     */
    public void traditionalEngagementRateButtonPressed(Button button) {
        reachOrTraditional = false;
        sort();
    }


    /**
     * Sets the window to show reach engagement
     * 
     * @param button
     *            the button pressed
     */
    public void reachEngagementRateButtonPressed(Button button) {
        reachOrTraditional = true;
        sort();
    }


    /**
     * Shows January data
     * 
     * @param button
     *            the button pressed
     */
    public void januaryButtonPressed(Button button) {
        months = new String[] { "January" };
        sort();
    }


    /**
     * Shows February data
     * 
     * @param button
     *            the button pressed
     */
    public void februaryButtonPressed(Button button) {
        months = new String[] { "February" };
        sort();
    }


    /**
     * Shows March data
     * 
     * @param button
     *            the button pressed
     */
    public void marchButtonPressed(Button button) {
        months = new String[] { "March" };
        sort();
    }


    /**
     * Shows first quarter data
     * 
     * @param button
     *            the button pressed
     */
    public void firstQuarterButtonPressed(Button button) {
        months = new String[] { "January", "February", "March" };
        sort();
    }


    private void repaint() {
        window.removeAllShapes();

        window.addShape(new TextShape(10, 10, Arrays.toString(months).substring(
            1, Arrays.toString(months).length() - 1)));
        window.addShape(new TextShape(10, 30, reachOrTraditional
            ? "Reach Engagement Rate"
            : "Traditional Engagement Rate"));
        window.addShape(new TextShape(10, 50, activeComparator.equals(
            channelNameComparator)
                ? "Sorting By Channel Name"
                : "Sorting By Engagement Rate"));

        Iterator<User> it = users.iterator();
        int barWidth = (window.getGraphPanelWidth()) / users.size();
        for (int i = 0; i < users.size(); i++) {
            User user = it.next();
            double engagementRate = reachOrTraditional
                ? user.getReachEngagement(months)
                : user.getTraditionalEngagement(months);
            if (!Double.isFinite(engagementRate)) {
                engagementRate = -1;
            }
            int height = 2 * Math.max(0, (int)Math.ceil(engagementRate));
            window.addShape(new Shape(10 + i * barWidth, 420 - height, barWidth
                / 2, height, new Color((int)(255 * (1 - ((double)i / (users
                    .size() - 1)))), 0, (int)(255 * (i / (users.size()
                        - 1))))));
            window.addShape(new TextShape(10 + i * barWidth, 440, user
                .getChannelName()));
            window.addShape(new TextShape(10 + i * barWidth, 460,
                engagementRate >= 0
                    ? String.format("%.1f", engagementRate)
                    : "N/A"));
        }

        window.addShape(new Shape(0, 420, window.getGraphPanelWidth(), 1,
            new Color(200, 200, 200)));
        window.addShape(new Shape(0, 320, window.getGraphPanelWidth(), 1,
            new Color(200, 200, 200)));
        window.addShape(new Shape(0, 220, window.getGraphPanelWidth(), 1,
            new Color(200, 200, 200)));
        window.addShape(new Shape(0, 120, window.getGraphPanelWidth(), 1,
            new Color(200, 200, 200)));

        window.addShape(new TextShape(550, 400, "0"));
        window.addShape(new TextShape(550, 300, "50"));
        window.addShape(new TextShape(550, 200, "100"));
        window.addShape(new TextShape(550, 100, "150"));
    }

}
