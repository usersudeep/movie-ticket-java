import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class userReview implements ActionListener {
    JFrame frame = new JFrame();
    JButton reviewButton = new JButton("Review");
    JButton backButton = new JButton("Back");
    JTable tblData;
    JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    String Username;

    userReview(String username) {
        Username = username;
        frame.setTitle("Review Page");
        tblData = new javax.swing.JTable();
        tblData.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] {
                }));
        tblData.setDefaultEditor(Object.class, null);
        jScrollPane2.setBounds(200, 100, 600, 200);
        jScrollPane2.setViewportView(tblData);

        DefaultTableModel model = (DefaultTableModel) tblData.getModel();
        SingleDatabase singledatabase = SingleDatabase.getInstance();
        singledatabase.setReviewView(model, username);

        backButton.setBounds(850, 25, 100, 30);
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        reviewButton.setBounds(450, 500, 100, 35);
        reviewButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        frame.setLayout(null);

        frame.setContentPane(new JLabel(new ImageIcon(
                new ImageIcon("D:\\JAVA_PROJECT\\Movie-Ticket-Booking-System-main\\model\\Images\\moviereel.jpg")
                        .getImage().getScaledInstance(1000, 650, Image.SCALE_DEFAULT))));
        frame.add(jScrollPane2);
        frame.add(reviewButton);
        frame.add(backButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new userView(Username);
        }
        if (e.getSource() == reviewButton) {
            if (tblData.getSelectedRowCount() == 1) {
                int row = tblData.getSelectedRow();
                String moviename = tblData.getModel().getValueAt(row, 0).toString();
                frame.dispose();
                new leaveReview(Username, moviename);
            } else {
                if (tblData.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this.frame, "Table is empty");
                } else {
                    JOptionPane.showMessageDialog(this.frame, "Please Select single row to delete");
                }
            }
        }
    }

    public static void main(String[] args) {
        new userReview("archit");
    }
}
