import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class Addmovie implements ActionListener {
    JFrame frame = new JFrame();

    JLabel movie_name = new JLabel("Movie Name:");
    JTextField movie_name_text = new JTextField();
    JLabel theatre = new JLabel("Theatre:");
    JTextField theatre_text = new JTextField();
    JLabel seats = new JLabel("Seats:");
    JTextField seats_text = new JTextField();
    JLabel date = new JLabel("Date:");
    JTextField date_text = new JTextField();
    JLabel time = new JLabel("Time:");
    JTextField time_text = new JTextField();
    // JLabel price = new JLabel("Price:");
    // JTextField price_text = new JTextField();
    JLabel movie_des = new JLabel("About:");
    // JLabel id = new JLabel("ID:");
    // JTextField id_text = new JTextField();
    JTextArea movie_description = new JTextArea();
    JButton add_movie = new JButton("Add Movie");
    JButton back = new JButton("Back");

    public Addmovie() {

        frame.setContentPane(new JLabel(new ImageIcon(
                new ImageIcon("D:\\JAVA_PROJECT\\Movie-Ticket-Booking-System-main\\model\\Images\\moviereel.jpg")
                        .getImage().getScaledInstance(1000, 650, Image.SCALE_DEFAULT))));

        frame.setTitle("Operations Page");

        back.setBounds(850, 50, 75, 30);
        back.addActionListener(this);

        movie_name.setBounds(450, 100, 100, 30);
        movie_name.setForeground(Color.BLACK);

        movie_name_text.setBounds(550, 100, 200, 30);
        movie_name_text.setForeground(Color.BLACK);

        theatre.setBounds(450, 150, 100, 30);
        theatre.setForeground(Color.BLACK);

        theatre_text.setBounds(550, 150, 200, 30);
        theatre_text.setForeground(Color.BLACK);

        seats.setBounds(450, 200, 100, 30);
        seats.setForeground(Color.BLACK);

        seats_text.setBounds(550, 200, 200, 30);
        seats_text.setForeground(Color.BLACK);

        movie_des.setBounds(450, 250, 100, 30);
        movie_des.setForeground(Color.BLACK);

        movie_description.setBounds(550, 250, 200, 200);
        movie_description.setForeground(Color.BLACK);

        date.setBounds(450, 500, 100, 30);
        date.setForeground(Color.BLACK);

        date_text.setBounds(550, 500, 200, 30);
        date_text.setForeground(Color.BLACK);

        time.setBounds(450, 550, 100, 30);
        time.setForeground(Color.BLACK);

        time_text.setBounds(550, 550, 100, 30);
        time_text.setForeground(Color.BLACK);

        add_movie.setBounds(825, 550, 100, 30);
        add_movie.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBackground(new Color(135, 206, 235));
        frame.setSize(1000, 650);

        frame.add(movie_name);
        frame.add(movie_name_text);
        frame.add(theatre);
        frame.add(theatre_text);
        frame.add(seats);
        frame.add(seats_text);
        frame.add(movie_des);
        frame.add(movie_description);
        // frame.add(price);
        // frame.add(price_text);
        frame.add(add_movie);
        // frame.add(id);
        // frame.add(id_text);
        frame.add(date);
        frame.add(date_text);
        frame.add(time);
        frame.add(time_text);
        frame.add(back);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("Addmovie");
        if (e.getSource() == add_movie) {
            System.out.println("Addmovie");
            SingleDatabase obj = SingleDatabase.getInstance();
            int success = obj.addMovie(movie_name_text.getText(), movie_description.getText(), theatre_text.getText(),
                    seats_text.getText(), date_text.getText(), time_text.getText());
            if (success == 1)
                JOptionPane.showMessageDialog(null, "Movie Added");
            else
                JOptionPane.showMessageDialog(null, "Another Movie already scheduled for that time");
        }
        if (e.getSource() == back) {
            frame.dispose();
            new ManagerView();
        }
    }
}
