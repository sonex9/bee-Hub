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

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Ứng Dụng Học Lập Trình");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTabbedPane tabbedPane = new JTabbedPane();

        // Tạo các panel cho từng trang
        JPanel homePanel = new JPanel();
        homePanel.add(new JLabel("Trang Chủ"));
        
        JPanel personalPanel = new JPanel();
        personalPanel.add(new JLabel("Cá Nhân"));
        
        JPanel lessonPanel = new JPanel();
        lessonPanel.add(new JLabel("Bài Học"));
        
        JPanel interactionPanel = new JPanel();
        interactionPanel.add(new JLabel("Tương Tác"));
        
        JPanel planPanel = new JPanel();
        planPanel.add(new JLabel("Kế Hoạch"));
        
        JPanel settingsPanel = new JPanel();
        settingsPanel.add(new JLabel("Cài Đặt"));
        
        JPanel helpPanel = new JPanel();
        helpPanel.add(new JLabel("Trợ Giúp"));
        
        JPanel loginPanel = new JPanel();
        loginPanel.add(new JLabel("Đăng Nhập"));

        // Thêm các panel vào tabbedPane
        tabbedPane.addTab("Trang Chủ", homePanel);
        tabbedPane.addTab("Cá Nhân", personalPanel);
        tabbedPane.addTab("Bài Học", lessonPanel);
        tabbedPane.addTab("Tương Tác", interactionPanel);
        tabbedPane.addTab("Kế Hoạch", planPanel);
        tabbedPane.addTab("Cài Đặt", settingsPanel);
        tabbedPane.addTab("Trợ Giúp", helpPanel);
        tabbedPane.addTab("Đăng Nhập", loginPanel);

        // Thêm tabbedPane vào JFrame
        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}