/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hello soncute nka
 */
import javax.swing.*;
import java.awt.*;

public class EduhouseApp {
    public static void main(String[] args) {
        // Create main frame
        JFrame frame = new JFrame("Eduhouse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.LIGHT_GRAY);
        JLabel titleLabel = new JLabel("Eduhouse");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        // Create main menu (left sidebar)
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.add(new JButton("Dashboard"));
        menuPanel.add(new JButton("E-book"));
        menuPanel.add(new JButton("My Courses"));
        menuPanel.add(new JButton("Purchase Course"));
        menuPanel.add(new JButton("Completed Courses"));
        menuPanel.add(new JButton("Community"));
        menuPanel.add(new JButton("Profile"));
        menuPanel.add(new JButton("Settings"));
        menuPanel.add(new JButton("Logout"));

        frame.add(menuPanel, BorderLayout.WEST);

        // Create content panel for courses and categories
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 1));

        // Courses in Progress panel
        JPanel coursesPanel = new JPanel();
        coursesPanel.setLayout(new GridLayout(3, 1));
        coursesPanel.setBorder(BorderFactory.createTitledBorder("Courses in Progress"));
        
        JPanel appDesign = createCoursePanel("App Design", "Dec 15, 2020", "20% Progress");
        JPanel webDesign = createCoursePanel("Web Design", "Dec 15, 2020", "80% Progress");
        JPanel dashboardDesign = createCoursePanel("Dashboard", "Dec 15, 2020", "50% Progress");

        coursesPanel.add(appDesign);
        coursesPanel.add(webDesign);
        coursesPanel.add(dashboardDesign);

        contentPanel.add(coursesPanel);

        // Popular Categories panel
        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new GridLayout(2, 2));
        categoriesPanel.setBorder(BorderFactory.createTitledBorder("Popular Categories"));
        
        categoriesPanel.add(new JLabel("UX/UI Design (15 Courses)"));
        categoriesPanel.add(new JLabel("Marketing (34 Courses)"));
        categoriesPanel.add(new JLabel("Development (21 Courses)"));
        categoriesPanel.add(new JLabel("Business (31 Courses)"));

        contentPanel.add(categoriesPanel);

        // Add content panel to main frame
        frame.add(contentPanel, BorderLayout.CENTER);

        // Create footer for subscription
        JPanel footerPanel = new JPanel();
        footerPanel.add(new JLabel("You have 5 days left on your subscription."));
        JButton upgradeButton = new JButton("Upgrade to Pro");
        footerPanel.add(upgradeButton);
        frame.add(footerPanel, BorderLayout.SOUTH);

        // Show frame
        frame.setVisible(true);
    }

    private static JPanel createCoursePanel(String courseName, String date, String progress) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(courseName));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(date));
        panel.add(new JLabel(progress));
        return panel;
    }
    ///sss///
}