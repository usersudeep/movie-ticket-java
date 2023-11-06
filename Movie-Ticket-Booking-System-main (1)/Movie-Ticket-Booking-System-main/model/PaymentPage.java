import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.*;

public class PaymentPage implements ActionListener {
    JFrame frame = new JFrame();
    JButton payButton = new JButton("Pay");
    JButton cancelButton = new JButton("Cancel");
    JTextField cardNumField = new JTextField(20);
    JPasswordField cardPasswordField = new JPasswordField(20);
    JLabel cardNumLabel = new JLabel("Enter Card Number:");

    JLabel movieLabel = new JLabel("");
    JLabel theatreLabel = new JLabel("");
    JLabel dateLabel = new JLabel("");
    JLabel timeLabel = new JLabel("");
    JLabel qtyLabel = new JLabel("");
    JLabel typeLabel = new JLabel("");
    JLabel costLabel = new JLabel("");

    JLabel cardPasswordLabel = new JLabel("Enter Password:");
    JLabel messageLabel = new JLabel("");
    String username, moviename, typeseat, theatrename, showdate, showtime;
    int qtyseat, cost;

    PaymentPage(String Username, String Moviename, String Typeseat, int Qtyseat, String Theatrename, String Showdate,
            String Showtime, int Cost) {
        username = Username;
        moviename = Moviename;
        typeseat = Typeseat;
        qtyseat = Qtyseat;
        theatrename = Theatrename;
        showdate = Showdate;
        showtime = Showtime;
        cost = Cost;

        frame.setContentPane(new JLabel(new ImageIcon(
                new ImageIcon("D:\\JAVA_PROJECT\\Movie-Ticket-Booking-System-main\\model\\Images\\moviereel.jpg")
                        .getImage().getScaledInstance(1000, 650, Image.SCALE_DEFAULT))));

        movieLabel.setText("Movie: " + moviename);
        movieLabel.setBounds(600, 50, 200, 25);
        movieLabel.setBackground(new Color(0, 0, 0, 0));
        movieLabel.setForeground(Color.WHITE);

        theatreLabel.setText("Theatre: " + theatrename);
        theatreLabel.setBounds(600, 100, 200, 25);
        theatreLabel.setBackground(new Color(0, 0, 0, 0));
        theatreLabel.setForeground(Color.WHITE);

        dateLabel.setText("Date and time: " + showdate + "  " + showtime);
        dateLabel.setBounds(600, 150, 400, 25);
        dateLabel.setBackground(new Color(0, 0, 0, 0));
        dateLabel.setForeground(Color.WHITE);

        qtyLabel.setText("Number of Seats: " + Integer.toString(qtyseat));
        qtyLabel.setBounds(600, 200, 200, 25);
        qtyLabel.setBackground(new Color(0, 0, 0, 0));
        qtyLabel.setForeground(Color.WHITE);
        typeLabel.setText("Type of Seat: " + typeseat);
        typeLabel.setBounds(600, 250, 200, 25);
        typeLabel.setBackground(new Color(0, 0, 0, 0));
        typeLabel.setForeground(Color.WHITE);

        costLabel.setText("Amount to be paid: " + Integer.toString(cost));
        costLabel.setBounds(600, 300, 200, 25);
        costLabel.setBackground(new Color(0, 0, 0, 0));
        costLabel.setForeground(Color.WHITE);

        cardNumLabel.setBounds(450, 400, 150, 25);
        cardNumLabel.setBackground(new Color(0, 0, 0, 0));
        cardNumLabel.setForeground(Color.WHITE);

        cardPasswordLabel.setBounds(450, 450, 150, 25);
        cardPasswordLabel.setBackground(new Color(0, 0, 0, 0));
        cardPasswordLabel.setForeground(Color.WHITE);

        messageLabel.setBounds(600, 550, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        messageLabel.setForeground(Color.WHITE);

        cardNumField.setBounds(600, 400, 250, 40);
        cardNumField.setBackground(new Color(210, 180, 50));
        cardNumField.setForeground(Color.WHITE);
        cardNumField.setFont(new Font("Serif", Font.BOLD, 20));
        cardNumField.setOpaque(false);

        cardPasswordField.setBounds(600, 450, 250, 40);
        cardPasswordField.setBackground(new Color(210, 180, 50));
        cardPasswordField.setForeground(Color.WHITE);
        cardPasswordField.setFont(new Font("Serif", Font.BOLD, 20));
        cardPasswordField.setOpaque(false);

        payButton.setBounds(675, 500, 100, 35);
        payButton.setBackground(Color.GREEN);
        payButton.setForeground(Color.WHITE);
        payButton.addActionListener(this);

        cancelButton.setBounds(525, 500, 100, 35);
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        frame.setLayout(null);

        frame.add(movieLabel);
        frame.add(theatreLabel);
        frame.add(dateLabel);
        frame.add(qtyLabel);
        frame.add(typeLabel);
        frame.add(costLabel);
        frame.add(cardNumLabel);
        frame.add(cardPasswordLabel);
        frame.add(messageLabel);
        frame.add(cardNumField);
        frame.add(cardPasswordField);
        frame.add(payButton);
        frame.add(cancelButton);

        frame.setTitle("Payment Details");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            frame.dispose();
            new bookticket(moviename, username);
        }

        if (e.getSource() == payButton) {
            String cardNum = cardNumField.getText();
            String cardPassword = String.valueOf(cardPasswordField.getPassword());
            if (cardNum.equals("1000") && cardPassword.equals("1000")) {
                frame.dispose();
                new SuccessPage(username, moviename, typeseat, qtyseat, theatrename, showdate, showtime, cost);
            } else {
                messageLabel.setText("Payment Failed");
            }

        }
    }
}
