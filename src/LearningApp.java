import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;

public class LearningApp extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    public LearningApp() {
        setTitle("Ứng dụng học lập trình");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo layout chính
        JPanel container = new JPanel(new BorderLayout());
        
        // Thêm menu bên trái
        container.add(createSideMenu(), BorderLayout.WEST);
        
        // Thêm các trang content
        mainPanel.add(createDashboard(), "dashboard");
        mainPanel.add(createJavaCoursePage(), "java_course");
        container.add(mainPanel, BorderLayout.CENTER);

        add(container);
    }

    private JPanel createSideMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(245, 245, 245));
        menuPanel.setPreferredSize(new Dimension(250, 0));

        String[] menuItems = {"Trang chủ", "Cá nhân", "Bài học", "Tương tác", "Kế hoạch", "Cài đặt", "Trợ giúp"};
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            styleMenuButton(btn);
            btn.addActionListener(this::handleMenuNavigation);
            menuPanel.add(btn);
            menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        return menuPanel;
    }

    private void styleMenuButton(JButton btn) {
        btn.setPreferredSize(new Dimension(220, 45));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBackground(new Color(245, 245, 245));
        btn.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
    }

    private JPanel createDashboard() {
        JPanel dashboard = new JPanel(new BorderLayout());
        
        // Header
        JPanel header = new JPanel();
        header.add(new JLabel("Chào mừng!"));
        dashboard.add(header, BorderLayout.NORTH);

        // Nội dung chính
        JTextPane content = new JTextPane();
        content.setContentType("text/html");
        content.setText("<html><h2>NGÔN NGỮ JAVA</h2>"
                + "<p>Java là ngôn ngữ lập trình hướng đối tượng...</p>");
        dashboard.add(new JScrollPane(content), BorderLayout.CENTER);

        return dashboard;
    }

    private JPanel createJavaCoursePage() {
        JPanel coursePanel = new JPanel(new BorderLayout());

        // Phần mô tả khóa học
        JTextPane courseInfo = new JTextPane();
        courseInfo.setContentType("text/html");
        String htmlContent = """
            <html>
                <h1>Khóa học Java từ cơ bản đến nâng cao</h1>
                <p><b>Thời lượng:</b> 60 giờ</p>
                <p><b>Số bài học:</b> 50 bài</p>
                <hr>
                <h2>Nội dung khóa học</h2>
            </html>""";
        courseInfo.setText(htmlContent);
        coursePanel.add(courseInfo, BorderLayout.NORTH);

        // Cây bài học với checkbox
        JTree lessonTree = new JTree(createLessonTreeModel());
        lessonTree.setCellRenderer(new CheckboxTreeCellRenderer());
        lessonTree.setCellEditor(new CheckboxTreeCellEditor(lessonTree, new CheckboxTreeCellRenderer()));
        lessonTree.setEditable(true);
        
        coursePanel.add(new JScrollPane(lessonTree), BorderLayout.CENTER);

        // Nút đăng ký
        JButton enrollButton = new JButton("Đăng ký ngay");
        enrollButton.setBackground(new Color(0, 150, 136));
        enrollButton.setForeground(Color.WHITE);
        enrollButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        coursePanel.add(enrollButton, BorderLayout.SOUTH);

        return coursePanel;
    }

    private DefaultTreeModel createLessonTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Danh sách bài học");
        
        for (int i = 1; i <= 35; i++) {
            DefaultMutableTreeNode chapter = new DefaultMutableTreeNode("Bài " + i);
            for (int j = 1; j <= 3; j++) {
                chapter.add(new DefaultMutableTreeNode(new CheckboxNode("Bài " + i + "." + j, false)));
            }
            root.add(chapter);
        }
        
        return new DefaultTreeModel(root);
    }

    private void handleMenuNavigation(ActionEvent e) {
        String command = ((JButton) e.getSource()).getText();
        switch (command) {
            case "Bài học" -> cardLayout.show(mainPanel, "java_course");
            default -> cardLayout.show(mainPanel, "dashboard");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LearningApp().setVisible(true));
    }
}

// Lớp hỗ trợ cho node checkbox
class CheckboxNode {
    String text;
    boolean selected;
    
    public CheckboxNode(String text, boolean selected) {
        this.text = text;
        this.selected = selected;
    }
}

// Lớp renderer cho tree node
class CheckboxTreeCellRenderer extends JPanel implements TreeCellRenderer {
    private JCheckBox checkBox = new JCheckBox();
    private DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();

    public CheckboxTreeCellRenderer() {
        setLayout(new BorderLayout());
        add(checkBox, BorderLayout.CENTER);
        setOpaque(false);
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        
        Component defaultComponent = defaultRenderer.getTreeCellRendererComponent(
                tree, value, selected, expanded, leaf, row, hasFocus
        );

        if (value instanceof DefaultMutableTreeNode) {
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
            if (userObject instanceof CheckboxNode) {
                CheckboxNode cn = (CheckboxNode) userObject;
                checkBox.setText(cn.text);
                checkBox.setSelected(cn.selected);
                checkBox.setOpaque(false);
                return this;
            }
        }
        
        return defaultComponent;
    }
}

// Lớp editor cho tree node
class CheckboxTreeCellEditor extends AbstractCellEditor implements TreeCellEditor {
    private CheckboxTreeCellRenderer renderer;
    private JTree tree;
    private DefaultMutableTreeNode editingNode;

    public CheckboxTreeCellEditor(JTree tree, CheckboxTreeCellRenderer renderer) {
        this.tree = tree;
        this.renderer = renderer;
        
        tree.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int row = tree.getRowForLocation(e.getX(), e.getY());
                if (row < 0) return;
                
                TreePath path = tree.getPathForRow(row);
                if (path == null) return;
                
                Object node = path.getLastPathComponent();
                if (node instanceof DefaultMutableTreeNode) {
                    DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
                    if (treeNode.getUserObject() instanceof CheckboxNode) {
                        CheckboxNode cn = (CheckboxNode) treeNode.getUserObject();
                        cn.selected = !cn.selected;
                        tree.treeDidChange();
                        stopCellEditing();
                    }
                }
            }
        });
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value,
            boolean isSelected, boolean expanded, boolean leaf, int row) {
        renderer.getTreeCellRendererComponent(tree, value, true, expanded, leaf, row, true);
        editingNode = (DefaultMutableTreeNode) value;
        return renderer;
    }

    @Override
    public Object getCellEditorValue() {
        if (editingNode != null && editingNode.getUserObject() instanceof CheckboxNode) {
            return editingNode.getUserObject();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if (!(event instanceof MouseEvent)) return false;
        MouseEvent me = (MouseEvent) event;
        TreePath path = tree.getPathForLocation(me.getX(), me.getY());
        return path != null && ((DefaultMutableTreeNode) path.getLastPathComponent()).getUserObject() instanceof CheckboxNode;
    }
}