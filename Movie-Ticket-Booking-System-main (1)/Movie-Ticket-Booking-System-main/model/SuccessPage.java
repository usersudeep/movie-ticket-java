import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.*;

public class SuccessPage implements ActionListener {
    JFrame frame = new JFrame();
    JButton backButton = new JButton("Back");
    JLabel success = new JLabel("Payment Successful");
    JLabel ticket = new JLabel("Ticket");
    JLabel userNameLabel;
    JLabel movieNameLabel;
    JLabel seatTypeLabel;
    JPanel successPanel = new JPanel();
    JPanel ticketPanel = new JPanel();
    String username, moviename, typeseat, theatrename, showdate, showtime;
    int qtyseat, cost;
    int count = 225, top = 125;

    JLabel movieLabel = new JLabel("");
    JLabel theatreLabel = new JLabel("");
    JLabel dateLabel = new JLabel("");
    JLabel timeLabel = new JLabel("");
    JLabel qtyLabel = new JLabel("");
    JLabel typeLabel = new JLabel("");
    JLabel costLabel = new JLabel("");

    SuccessPage(String Username, String Moviename, String Typeseat, int Qtyseat, String Theatrename, String Showdate,
            String Showtime, int Cost) {
        // System.out.println("LoginPage");
        username = Username;
        moviename = Moviename;
        typeseat = Typeseat;
        qtyseat = Qtyseat;
        theatrename = Theatrename;
        showdate = Showdate;
        showtime = Showtime;
        cost = Cost;
        userNameLabel = new JLabel("Name: " + username);
        movieNameLabel = new JLabel("Movie: " + moviename);
        seatTypeLabel = new JLabel("Seat Type: " + typeseat);

        SingleDatabase singledatabase = SingleDatabase.getInstance();
        singledatabase.updateTicket(username, moviename, typeseat, qtyseat, theatrename, showdate, showtime, cost);

        Font f = new Font("Times New Roman", Font.BOLD, 20);
        successPanel.setBackground(new Color(0, 0, 0, 80));
        successPanel.setBounds(0, 0, 1000, 100);

        success.setBounds(400, 50, 400, 50);
        success.setForeground(Color.WHITE);
        success.setFont(new Font("Serif", Font.BOLD, 30));

        backButton.setBounds(850, 550, 100, 30);
        backButton.addActionListener(this);

        ticket.setBounds(450, -100, 400, 500);
        ticket.setForeground(Color.BLACK);
        ticket.setFont(new Font("Serif", Font.BOLD, 25));

        movieLabel.setText("Movie: " + moviename);
        movieLabel.setBounds(400, -50, 400, 500);
        movieLabel.setBackground(new Color(0, 0, 0, 0));
        movieLabel.setForeground(Color.BLACK);
        movieLabel.setFont(f);

        theatreLabel.setText("Theatre: " + theatrename);
        theatreLabel.setBounds(400, 0, 400, 500);
        theatreLabel.setBackground(new Color(0, 0, 0, 0));
        theatreLabel.setForeground(Color.BLACK);
        theatreLabel.setFont(f);

        dateLabel.setText("Date and time: " + showdate + "  " + showtime);
        dateLabel.setBounds(400, 50, 400, 500);
        dateLabel.setBackground(new Color(0, 0, 0, 0));
        dateLabel.setForeground(Color.BLACK);
        dateLabel.setFont(f);

        qtyLabel.setText("Number of Seats: " + Integer.toString(qtyseat));
        qtyLabel.setBounds(400, 100, 400, 500);
        qtyLabel.setBackground(new Color(0, 0, 0, 0));
        qtyLabel.setForeground(Color.BLACK);
        qtyLabel.setFont(f);

        typeLabel.setText("Type of Seat: " + typeseat);
        typeLabel.setBounds(400, 150, 400, 500);
        typeLabel.setBackground(new Color(0, 0, 0, 0));
        typeLabel.setForeground(Color.BLACK);
        typeLabel.setFont(f);

        costLabel.setText("Amount to be paid: " + Integer.toString(cost));
        costLabel.setBounds(400, 200, 400, 500);
        costLabel.setBackground(new Color(0, 0, 0, 0));
        costLabel.setForeground(Color.BLACK);
        costLabel.setFont(f);

        successPanel.add(success);

        ImageIcon background_image = new ImageIcon("index.jfif");
        Image img = background_image.getImage();
        Image temp_img = img.getScaledInstance(1000, 101, Image.SCALE_SMOOTH);
        background_image = new ImageIcon(temp_img);
        JLabel background = new JLabel("", background_image, JLabel.CENTER);
        background.setBounds(0, 0, 1000, 101);
        
        frame.setTitle("Success");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        frame.setLayout(null);
        
        background.add(successPanel);
       
        frame.add(background);
       
        frame.add(ticket);
        frame.add(movieLabel);
        frame.add(theatreLabel);
        frame.add(dateLabel);
        frame.add(qtyLabel);
        frame.add(typeLabel);
        frame.add(costLabel);
        frame.add(backButton);
       

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        new userView(username);
    }
}
