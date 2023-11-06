import java.awt.Image;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

//  Singleton Pattern

public class SingleDatabase {
    private static SingleDatabase single_instance = null;
    public Connection c;
    public Statement stm;

    SingleDatabase() {
        try {

            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/moviedb","postgres","PRaju@089");
            System.out.println("Opened database successfully");
            c.setAutoCommit(true);
            stm = c.createStatement();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static SingleDatabase getInstance() {
        if (single_instance == null)
            single_instance = new SingleDatabase();

        return single_instance;
    }

    void updateTicket(String username, String moviename, String typeseat, int qtyseat, String theatrename,
            String showdate, String showtime, int cost) {
        try 
        {
            String query = "insert into ticket values(" + "'" + username + "'" + ",'" + theatrename + "'" + ",'"
                    + showdate + "'" + ",'" + showtime + "'" + "," + Integer.toString(qtyseat) + ","
                    + Integer.toString(cost) + ")";
            stm.executeUpdate(query);
            System.out.println(query);
            ResultSet res = stm.executeQuery("SELECT * FROM moviedetails" + " WHERE name=" + "'" + moviename
                    + "' and theatre=" + "'" + theatrename + "' and showdate=" + "'" + showdate + "' and showtime="
                    + "'" + showtime + "'" + ";");
            System.out.println("SELECT * FROM moviedetails" + " WHERE name=" + "'" + moviename + "' and theatre=" + "'"
                    + theatrename + "' and showdate=" + "'" + showdate + "' and showtime=" + "'" + showtime + "'"
                    + ";");

            res.next();
            int seatval = res.getInt("seats");
            System.out.println(res);
            query = "UPDATE moviedetails SET seats=" + (seatval - qtyseat) + " WHERE name=" + "'" + moviename
                    + "' and theatre=" + "'" + theatrename + "' and showdate=" + "'" + showdate + "' and showtime="
                    + "'" + showtime + "'" + ";";
            System.out.println(query);
            stm.executeUpdate(query);
            query="insert into ratedmovies values(" + "'" + username + "'" + ",'" + moviename + "'" + ","+(-1)+",'')";
            System.out.println(query);
            stm.executeUpdate(query);
        } 
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    void deleteManagerView(String name, String theatre, String showdate, String showtime) {
        try {
            String query = "delete from moviedetails where name=" + "'" + name + "'" + " and " + "theatre="
                    + "'" + theatre + "'" + " and " + "showdate=" + "'" + showdate + "'" + " and " + "showtime="
                    + "'" + showtime + "'";
            System.out.println(query);
            stm.executeUpdate(query);
        } catch (Exception err) {
            System.err.println(err.getClass().getName() + ": " + err.getMessage());
            System.exit(0);
        }
    }

    void setManagerView(DefaultTableModel model) {
        try {
            String query = "select * from moviedetails";
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colname = new String[cols];
            System.out.println(cols);
            for (int i = 0; i < cols; i++)
                colname[i] = rsmd.getColumnName(i + 1);
            model.setColumnIdentifiers(colname);
            String name, theatre, showdate, showtime;
            int seats;
            while (rs.next()) {
                name = rs.getString(1);
                theatre = rs.getString(2);
                seats = rs.getInt(3);
                showdate = rs.getString(4);
                showtime = rs.getString(5);
                String[] row = { name, theatre, Integer.toString(seats), showdate, showtime };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    void changeReview(String username, String moviename, String rating, String review)
    {
        try 
        {
            String query = "UPDATE ratedmovies SET rating=" + rating + ", review='"+review+"' WHERE username=" + "'" + username
                    + "' and moviename=" + "'" + moviename + "';";
            System.out.println(query);
            stm.executeUpdate(query);
        } 
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    void setReviewView(DefaultTableModel model, String username) {
        try {
            String query = "select * from ratedmovies where username='"+username+"';";
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colname = {"Movie","Rating", "Review"};
            System.out.println(cols);
            model.setColumnIdentifiers(colname);
            String Moviename, review;
            int rating;
            while (rs.next()) {
                //Username = rs.getString(1);
                Moviename = rs.getString(2);
                rating = rs.getInt(3);
                review = rs.getString(4);
                String rate;
                if(rating==-1)
                    rate="Not Rated";
                else
                    rate=Integer.toString(rating);
                String[] row = {Moviename, rate, review };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    int addMovie(String movie_name_text, String movie_description, String theatre_text, String seats_text,
            String date_text, String time_text) {
        try {
            int flag = 0;
            String fetch = "Select * from movie where name='" + movie_name_text + "'";
            String query = "insert";
            ResultSet rs = stm.executeQuery(fetch);

            while (rs.next()) {
                flag = 1;
            }
            if (flag == 0) {
                query = "insert into movie (name,description) values ('" + movie_name_text + "','"
                        + movie_description + "')";
                System.out.println("new movie");
                stm.executeUpdate(query);
            } else {
                System.out.println("movie already exists");
            }
            query = "insert into moviedetails(name,theatre,seats,showdate,showtime) values ('"
                    + movie_name_text + "','" + theatre_text + "',"
                    + Integer.parseInt(seats_text) + ",'" + date_text + "','"
                    + time_text + "')";
            stm.executeUpdate(query);
            System.out.println("Movie Added");
            return 1;
        } catch (Exception ex) {
            System.out.println("error");
            System.out.println(ex);
            return 0;
        }
    }

    void updateUserView(DefaultTableModel model, String path) {
        try {
            String query = "select * from movie";
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colname = { "Poster", "Movie Name", "Description" };
            System.out.println(cols);
            model.setColumnIdentifiers(colname);
            String name, description;
            while (rs.next()) {
                name = rs.getString(1);
                description = rs.getString(2);
                Object[] row = { new ImageIcon(new ImageIcon(path + name + ".jfif").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)), name, description };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    int getDetails(String movieName, String showdate, String showtime, String theatreN) {
        try {
            System.out.println("in get details function...");
            String sql = "select * from moviedetails where name='" + movieName + "' and showdate='" + showdate
                    + "' and showtime='" + showtime +"' and theatre='"+theatreN+ "';";
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                int seats = res.getInt("seats");
                return seats;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            return -1;
        }
        return -1;
    }

    List<String> getTheatres(String moviename) {
        System.out.println("in getTheatres function...");
        List<String> theatres = new ArrayList<String>();
        try {
            String sql = "select * from moviedetails where name='" + moviename + "';";
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                String t = res.getString("theatre");
                System.out.println(t);
                theatres.add(t);
            }
            return (theatres);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            return theatres;
        }
    }

    List<String> getDate(String moviename, String theatre) {
        System.out.println("in getDate function...");
        List<String> dates = new ArrayList<String>();
        try {
            String sql = "select * from moviedetails where name='" + moviename + "' and theatre ='" + theatre
                    + "';";
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                String t = res.getString("showdate");
                System.out.println(t);
                dates.add(t);
            }
            return (dates);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            return dates;
        }
    }

    List<String> getTime(String moviename, String theatre, String showdate) {
        System.out.println("in getTime function...");
        List<String> times = new ArrayList<String>();
        try {
            String sql = "select * from moviedetails where name='" + moviename + "' and theatre ='" + theatre
                    + "' and showdate ='" + showdate + "';";
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                String t = res.getString("showtime");
                System.out.println(t);
                times.add(t);
            }
            return (times);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            return times;
        }
    }

    int getManagerIDPasswords(String name, String password) {
        System.out.println("in getManagerIDPWD function...");
        try {
            ResultSet res = stm.executeQuery("select name, password from manager where name='" + name
                    + "' and password='" + password + "';");
            if (res.next()) {
                return 1;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            return 0;
        }
        return 0;
    }

    int setManagerIDPasswords(String name, String password) {
        System.out.println("in setmanagerIDPWD function...");
        try {
            String sql = "INSERT INTO manager(name, password) Values('" + name + "'," + "'" + password
                    + "');";
            stm.executeUpdate(sql);
            return 1;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            return 0;
        }

    }

    int getUserIDPassword(String username, String password) {
        System.out.println("in getUserIDPWD function...");
        try {
            stm = c.createStatement();
            ResultSet res = stm.executeQuery("select * from users where name='" + username
                    + "' and password='" + password + "';");
            if (res.next()) {
                return 1;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            return 0;
        }

        return 0;
    }

    int setUserIDPassword(String username, String password) {
        System.out.println("in setUserIDPWD function...");
        try {
            String sql = "INSERT INTO users(name, password) Values('" + username + "'," + "'" + password
                    + "');";
            stm.executeUpdate(sql);

            return 1;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            return 0;
        }
    }

}
