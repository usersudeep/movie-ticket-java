import javax.swing.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class bookticket extends JFrame implements ActionListener {
    String movieName, name, theatreN, showD, showT;
    int amount;
    JFrame frame;
    JComboBox<String> movieNames;
    JComboBox<String> seatTypes;
    JComboBox<String> theatreName;
    JComboBox<String> showDate;
    JComboBox<String> showTime;
    JTextField seatQty;
    JLabel selectMovie;
    JLabel seatPrice;
    JLabel selectSeatType;
    JLabel enterQty;
    JLabel viewAmountLabel;
    JLabel viewAmountNumber;
    JLabel messageLabel;
    JLabel showDateLabel;
    JLabel showTimingLabel;
    JLabel theatreNameLabel;
    JButton confirm;
    JButton bookTicket;
    JButton backButton;

    String[] seats = { "Gold", "Silver", "Regular" };

    ArrayList<String> theatres = new ArrayList<String>();

    bookticket(String moviename, String username) {
        name = username;
        movieName = moviename;

        frame = new JFrame("Book tickets");
        frame.setSize(1000, 650);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new JLabel(new ImageIcon(
                new ImageIcon("D:\\JAVA_PROJECT\\Movie-Ticket-Booking-System-main\\model\\Images\\moviereel.jpg")
                        .getImage().getScaledInstance(1000, 650, Image.SCALE_DEFAULT))));
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backButton = new JButton("Back");
        backButton.setBounds(850, 25, 100, 30);
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        frame.add(backButton);

        selectSeatType = new JLabel("Select seat type:");
        selectSeatType.setBounds(500, 100, 100, 30);
        seatTypes = new JComboBox<String>(seats);
        seatTypes.setBounds(650, 100, 100, 30);
        seatTypes.addActionListener(this);
        seatPrice = new JLabel("");
        seatPrice.setBounds(575, 150, 200, 30);
        frame.add(selectSeatType);
        frame.add(seatTypes);
        frame.add(seatPrice);

        enterQty = new JLabel("Enter number of seats:");
        enterQty.setBounds(500, 200, 150, 30);
        seatQty = new JTextField();
        seatQty.setBounds(650, 200, 100, 30);
        frame.add(enterQty);
        frame.add(seatQty);

        SingleDatabase obj = SingleDatabase.getInstance();
        List<String> namesTheatres = obj.getTheatres(movieName);
        System.out.println(namesTheatres);
        theatreNameLabel = new JLabel("Select theatre:");
        theatreNameLabel.setBounds(500, 250, 100, 30);
        theatreName = new JComboBox<String>();
        for (String j : namesTheatres) {
            theatreName.addItem(j);
        }
        theatreName.setBounds(650, 250, 100, 30);
        theatreName.addActionListener(this);
        frame.add(theatreNameLabel);
        frame.add(theatreName);

        showDateLabel = new JLabel("Select date:");
        showDateLabel.setBounds(500, 300, 150, 30);
        frame.add(showDateLabel);

        showTimingLabel = new JLabel("Select timing:");
        showTimingLabel.setBounds(500, 350, 150, 30);
        frame.add(showTimingLabel);

        viewAmountLabel = new JLabel("Amount:");
        viewAmountLabel.setBounds(500, 400, 100, 30);
        viewAmountNumber = new JLabel("0");
        viewAmountNumber.setBounds(650, 400, 100, 30);
        viewAmountNumber.setForeground(Color.WHITE);
        messageLabel = new JLabel("");
        messageLabel.setBounds(550, 500, 300, 30);
        messageLabel.setForeground(Color.WHITE);
        frame.add(viewAmountLabel);
        frame.add(viewAmountNumber);
        frame.add(messageLabel);

        confirm = new JButton("Confirm");
        bookTicket = new JButton("Book");
        confirm.setBounds(500, 450, 100, 30);
        confirm.setFocusable(false);
        bookTicket.setBounds(645, 450, 100, 30);
        bookTicket.setFocusable(false);
        frame.add(confirm);
        frame.add(bookTicket);
        confirm.addActionListener(this);
        bookTicket.addActionListener(this);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new userView(name);
        }
        if (e.getSource() == seatTypes) {
            String typeOfSeat = (String) seatTypes.getSelectedItem();
            if (typeOfSeat == "Gold") {
                seatPrice.setText("Gold Price: " + 500);
            } else if (typeOfSeat == "Silver") {
                seatPrice.setText("Silver Price: " + 300);
            } else {
                seatPrice.setText("Regular Price: " + 150);
            }
        }
        if (e.getSource() == theatreName) {
            theatreN = (String) theatreName.getSelectedItem();
            SingleDatabase obj1 = SingleDatabase.getInstance();
            List<String> showdates = obj1.getDate(movieName, theatreN);
            System.out.println(showdates);
            showDate = new JComboBox<String>();
            for (String k : showdates) {
                showDate.addItem(k);
            }
            showDate.setBounds(650, 300, 100, 30);
            showDate.addActionListener(this);

            frame.add(showDate);
        }
        if (e.getSource() == showDate) {
            showD = (String) showDate.getSelectedItem();
            SingleDatabase obj2 = SingleDatabase.getInstance();
            List<String> showTimes = obj2.getTime(movieName, theatreN, showD);
            showTime = new JComboBox<String>();
            for (String k : showTimes) {
                showTime.addItem(k);
            }
            showTime.setBounds(650, 350, 100, 30);
            showTime.addActionListener(this);
            frame.add(showTime);
        }
        if (e.getSource() == showTime) {
            showT = (String) showTime.getSelectedItem();
        }
        if (e.getSource() == confirm) {
            String typeOfSeat = (String) seatTypes.getSelectedItem();
            SingleDatabase obj = new SingleDatabase();
            int seats = obj.getDetails(movieName, showD, showT, theatreN);
            if (seatQty.getText().equals("")) {
                messageLabel.setText("Select number of seats.");
            } else {
                if (seats >= Integer.parseInt(seatQty.getText())) {
                    if (typeOfSeat == "Gold") {
                        viewAmountNumber
                                .setText("Rs. " + String.valueOf(500 * Integer.parseInt(seatQty.getText())));
                        amount = 500 * Integer.parseInt(seatQty.getText());
                    } else if (typeOfSeat == "Silver") {
                        viewAmountNumber
                                .setText("Rs. " + String.valueOf(300 * Integer.parseInt(seatQty.getText())));
                        amount = 300 * Integer.parseInt(seatQty.getText());
                    } else {
                        viewAmountNumber
                                .setText("Rs. " + String.valueOf(150 * Integer.parseInt(seatQty.getText())));
                        amount = 150 * Integer.parseInt(seatQty.getText());
                    }
                    messageLabel.setText("");
                } else {
                    messageLabel.setText("Not enough seats");
                }
            }

        }
        if (e.getSource() == bookTicket) {
            String typeSeat = (String) seatTypes.getSelectedItem();
            int qtySeat = Integer.parseInt(seatQty.getText());

            if (amount == 0) {
                messageLabel.setText("Enter details and press confirm.");
            } else {
                frame.dispose();
                new PaymentPage(name, movieName, typeSeat, qtySeat, theatreN, showD, showT, amount);
                // uncomment the above line when you integrate payment part
            }
        }
    }
}
