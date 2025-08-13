import javax.swing.*;
import java.awt.*;

public class ToDoApp extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;

    public ToDoApp() {
        setTitle("ToDo List App");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        taskField = new JTextField();
        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Task");
        JButton viewAllButton = new JButton("View All Tasks"); // NEW BUTTON

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3)); // Now 3 buttons
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(viewAllButton);

        add(taskField, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Add Task Action
        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Task cannot be empty.");
            }
        });

        // Delete Task Action
        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Select a task to delete.");
            }
        });

        // View All Tasks Action
        viewAllButton.addActionListener(e -> {
            if (taskListModel.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No tasks to display.");
            } else {
                StringBuilder tasks = new StringBuilder();
                for (int i = 0; i < taskListModel.size(); i++) {
                    tasks.append((i + 1)).append(". ").append(taskListModel.getElementAt(i)).append("\n");
                }
                JOptionPane.showMessageDialog(this, tasks.toString(), "All Tasks", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ToDoApp().setVisible(true);
        });
    }
}
