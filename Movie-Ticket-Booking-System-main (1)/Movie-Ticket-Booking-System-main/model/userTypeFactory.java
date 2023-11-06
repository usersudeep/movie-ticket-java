import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

interface User {
}

class ManagerView implements ActionListener, User {
    JFrame frame = new JFrame();
    JButton deleteButton = new JButton("Delete");
    JButton addButton = new JButton("ADD");
    JButton logoutButton = new JButton("Logout");
    JTable tblData;
    JScrollPane jScrollPane2 = new javax.swing.JScrollPane();

    ManagerView() {

        frame.setTitle("Manager View");
        tblData = new javax.swing.JTable();
        tblData.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] {
                }));
        tblData.setDefaultEditor(Object.class, null);
        jScrollPane2.setBounds(100, 100, 600, 200);
        jScrollPane2.setViewportView(tblData);

        DefaultTableModel model = (DefaultTableModel) tblData.getModel();
        SingleDatabase singledatabase = SingleDatabase.getInstance();
        singledatabase.setManagerView(model);

        logoutButton.setBounds(850, 25, 100, 30);
        logoutButton.addActionListener(this);
        logoutButton.setFocusable(false);

        deleteButton.setBounds(600, 450, 100, 35);
        deleteButton.addActionListener(this);

        addButton.setBounds(800, 450, 100, 35);
        addButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        frame.setLayout(null);

        frame.setContentPane(new JLabel(new ImageIcon(
                new ImageIcon("D:\\JAVA_PROJECT\\Movie-Ticket-Booking-System-main\\model\\Images\\moviereel.jpg")
                        .getImage().getScaledInstance(1000, 650, Image.SCALE_DEFAULT))));
        frame.add(jScrollPane2);
        frame.add(deleteButton);
        frame.add(addButton);
        frame.add(logoutButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) tblData.getModel();
        if (e.getSource() == logoutButton) {
            frame.dispose();
            new Login();
        }
        if (e.getSource() == deleteButton) {
            if (tblData.getSelectedRowCount() == 1) {
                int row = tblData.getSelectedRow();
                String name = tblData.getModel().getValueAt(row, 0).toString();
                String theatre = tblData.getModel().getValueAt(row, 1).toString();
                String showdate = tblData.getModel().getValueAt(row, 3).toString();
                String showtime = tblData.getModel().getValueAt(row, 4).toString();
                model.removeRow(row);
                SingleDatabase singledatabase = SingleDatabase.getInstance();
                singledatabase.deleteManagerView(name, theatre, showdate, showtime);

            } else {
                if (tblData.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this.frame, "Table is empty");
                } else {
                    JOptionPane.showMessageDialog(this.frame, "Please Select single row to delete");
                }
            }
        }

        if (e.getSource() == addButton) {
            frame.dispose();
            new Addmovie();
            // WelcomePage welcomePage = new WelcomePage();
            // View view = new View();
        }
    }
}

class userView implements ActionListener, User {
    String userName;
    JFrame frame = new JFrame();
    JButton logoutButton = new JButton("Logout");
    JButton bookButton = new JButton("Book");
    JButton reviewButton = new JButton("Add Review");;
    JTable tblData;
    JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    String path = "D:\\CSE_6th_Sem\\UE19CS353_OOAD\\Project\\Code\\original\\twoTier-Factory\\Images\\";

    userView(String username) {
        userName = username;

        frame.setContentPane(new JLabel(new ImageIcon(
                new ImageIcon("D:\\JAVA_PROJECT\\Movie-Ticket-Booking-System-main\\model\\Images\\moviereel.jpg")
                        .getImage().getScaledInstance(1000, 650, Image.SCALE_DEFAULT))));
        DefaultTableModel model = new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] {
                });
        tblData = new JTable(model) {
            public Class getColumnClass(int column) {
                return (column == 0) ? Icon.class : Object.class;
            }
        };

        tblData.setDefaultEditor(Object.class, null);
        tblData.setRowHeight(150);
        jScrollPane2.setBounds(20, 100, 900, 400);
        jScrollPane2.setViewportView(tblData);

        SingleDatabase singledatabase = SingleDatabase.getInstance();
        singledatabase.updateUserView(model, path);

        reviewButton.setBounds(100, 550, 200, 30);
        reviewButton.addActionListener(this);
        reviewButton.setFocusable(false);
        frame.add(reviewButton);

        bookButton.setBounds(600, 550, 100, 35);
        bookButton.setFocusable(false);
        bookButton.addActionListener(this);

        logoutButton.setBounds(800, 550, 100, 35);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        frame.setLayout(null);

        frame.getContentPane().add(jScrollPane2);
        frame.add(bookButton);
        frame.add(logoutButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {
            if (tblData.getSelectedRowCount() == 1) {
                int row = tblData.getSelectedRow();
                String movieName = tblData.getModel().getValueAt(row, 1).toString();
                frame.dispose();
                new bookticket(movieName, userName);
            } else {
                if (tblData.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this.frame, "Table is empty");
                } else {
                    JOptionPane.showMessageDialog(this.frame, "Please Select single row to book ticket");
                }
            }
        }

        if (e.getSource() == logoutButton) {
            frame.dispose();
            new Login();
        }
        if (e.getSource() == reviewButton) {
            frame.dispose();
            new userReview(userName);
        }
    }
}

// Factory Pattern

public class userTypeFactory {
    public User getUser(String name, String password, JFrame frame) {
        SingleDatabase obj = SingleDatabase.getInstance();
        int auth = obj.getManagerIDPasswords(name, password);
        if (auth == 1) {
            frame.dispose();
            return new ManagerView();
        } else {
            auth = obj.getUserIDPassword(name, password);
            System.out.println("Auth: " + auth);
            if (auth == 1) {
                frame.dispose();
                return new userView(name);
            } else {
                return null;
            }
        }

    }
}
