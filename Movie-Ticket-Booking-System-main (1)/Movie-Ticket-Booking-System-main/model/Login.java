import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.*;
import java.awt.*;

public class Login implements ActionListener {

    JFrame frame = new JFrame("Login Page");
    JPanel panel;
    JButton loginButton;
    JButton registerButton;
    JLabel NameLabel;
    JLabel PasswordLabel;
    JLabel ImageLabel;
    JTextField NameField;
    JPasswordField PasswordField;
    JLabel messageLabel;
    public String name;
    public String password;

    Login() {
        frame.setContentPane(new JLabel(new ImageIcon(
                new ImageIcon("D:\\JAVA_PROJECT\\Movie-Ticket-Booking-System-main\\model\\Images\\moviereel.jpg").getImage().getScaledInstance(1000, 650,
                        Image.SCALE_DEFAULT))));
        frame.setSize(1000, 650);
        frame.setLayout(null);

        ImageLabel = new JLabel();
        ImageLabel.setIcon(new ImageIcon(
                new ImageIcon("D:\\JAVA_PROJECT\\Movie-Ticket-Booking-System-main\\model\\Images\\manager.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        ImageLabel.setBounds(200, 200, 200, 200);
        frame.add(ImageLabel);

        messageLabel = new JLabel("");
        messageLabel.setBounds(650, 305, 200, 30);
        messageLabel.setForeground(Color.WHITE);
        frame.add(messageLabel);

        panel = new JPanel();
        panel.setBounds(475, 175, 2, 250);
        panel.setBackground(Color.BLACK);
        frame.add(panel);

        NameLabel = new JLabel("Enter Name: ");
        NameLabel.setBounds(600, 210, 150, 30);
        NameLabel.setForeground(Color.WHITE);
        NameField = new JTextField();
        NameField.setBounds(700, 210, 150, 30);
        frame.add(NameLabel);
        frame.add(NameField);

        PasswordLabel = new JLabel("Enter Password:");
        PasswordLabel.setBounds(575, 260, 150, 30);
        PasswordLabel.setForeground(Color.WHITE);
        PasswordField = new JPasswordField();
        PasswordField.setBounds(700, 260, 150, 30);
        frame.add(PasswordLabel);
        frame.add(PasswordField);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(575, 350, 100, 30);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);
        registerButton = new JButton("REGISTER");
        registerButton.setBounds(750, 350, 100, 30);
        registerButton.addActionListener(this);
        registerButton.setFocusable(false);
        frame.add(loginButton);
        frame.add(registerButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            name = NameField.getText();
            password = String.valueOf(PasswordField.getPassword());
            if (name.equals("")) {
                messageLabel.setText("Enter correct details...");
            } else {
                if (password.equals("")) {
                    messageLabel.setText("Enter correct details...");
                } else {
                    userTypeFactory obj = new userTypeFactory();
                    if (obj.getUser(name, password, frame) == null) {
                        System.out.println("Login Fail...");
                        messageLabel.setText("Login Failed");
                    }
                }
            }
        }
        if (e.getSource() == registerButton) {
            name = NameField.getText();
            password = String.valueOf(PasswordField.getPassword());
            if (name.equals("")) {
                messageLabel.setText("Enter correct details...");
            } else {
                if (password.equals("")) {
                    messageLabel.setText("Enter correct details...");
                } else {
                    SingleDatabase obj = SingleDatabase.getInstance();

                    int auth = obj.getManagerIDPasswords(name, password);
                    if (auth == 0) {
                        auth = obj.getUserIDPassword(name, password);
                        if (auth == 1) {
                            messageLabel.setText("User exists, try other name.");
                        } else {
                            auth = obj.setUserIDPassword(name, password);
                            if (auth == 1) {
                                messageLabel.setText("Register Successful");
                            } else {
                                messageLabel.setText("register Failed");
                            }
                        }
                    } else {
                        messageLabel.setText("Try different Username");
                    }
                }
            }
        }
    }
}