import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.*;

public class leaveReview implements ActionListener {
    JFrame frame = new JFrame();
    JButton submitButton = new JButton("Submit");
    JButton backButton = new JButton("Back");
    final ThreadLocal<JComboBox> reviewRating = new ThreadLocal<JComboBox>();
    JTextArea reviewField = new JTextArea();
    JLabel ratingLabel = new JLabel("Rating: ");
    JLabel reviewLabel = new JLabel("Review: ");
    JLabel messageLabel = new JLabel("Please Give Review!");
    JLabel review;
    String Username, Moviename;

    leaveReview(String username, String moviename) {
        Username = username;
        Moviename = moviename;
        frame.setContentPane(new JLabel(new ImageIcon(
                new ImageIcon("D:\\JAVA_PROJECT\\Movie-Ticket-Booking-System-main\\model\\Images\\moviereel.jpg")
                        .getImage().getScaledInstance(1000, 650, Image.SCALE_DEFAULT))));
        review = new JLabel("Leave Review for the Movie " + Moviename);
        frame.setTitle("Leave a Review");

        backButton = new JButton("Back");
        backButton.setBounds(850, 50, 100, 30);
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        frame.add(backButton);

        ratingLabel.setBounds(500, 210, 100, 30);
        ratingLabel.setForeground(Color.WHITE);
        reviewLabel.setBounds(500, 260, 100, 30);
        reviewLabel.setForeground(Color.WHITE);

        messageLabel.setBounds(625, 500, 500, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        messageLabel.setForeground(Color.WHITE);

        String[] ratings = { "1", "2", "3", "4", "5" };
        reviewRating.set(new JComboBox(ratings));
        reviewRating.get().setBounds(650, 210, 100, 30);
        reviewField.setBounds(650, 260, 200, 200);

        submitButton.setBounds(675, 550, 100, 30);
        submitButton.setFocusable(false);
        submitButton.addActionListener(this);

        frame.add(ratingLabel);
        frame.add(reviewRating.get());
        frame.add(reviewLabel);
        frame.add(reviewField);
        frame.add(messageLabel);
        frame.add(submitButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String rating = (String) reviewRating.get().getSelectedItem();
            String review = reviewField.getText();
            SingleDatabase obj = SingleDatabase.getInstance();
            obj.changeReview(Username, Moviename, rating, review);
            messageLabel.setText("Review Updated");
        }
        if (e.getSource() == backButton) {
            frame.dispose();
            new userReview(Username);
        }
    }
}
