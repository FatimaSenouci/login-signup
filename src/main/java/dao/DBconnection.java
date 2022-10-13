package dao;
import models.usersModel;

import java.sql.*;

public class DBconnection {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final  String URL = "127.0.0.1";
    private static final int PORT = 3306;
    private static final String DB_NAME = "login";
    public static Connection con ; // n oublie pas requires

    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + URL + ":" + PORT + "/" + DB_NAME, USERNAME, PASSWORD);
        } catch (SQLException se) {
            se.printStackTrace();
        }}
        public static Boolean checkLogin(String username, String password) throws SQLException {
            Connection con = DBconnection.con;
            if (con == null) {
                return false;
            }
            String tableName="users";

            String sql = "SELECT * FROM " + tableName + " WHERE username=? AND password=? ";
            try {
                PreparedStatement prest = con.prepareCall(sql);
                prest.setString(1, username);
                prest.setString(2, password);
                ResultSet rs = prest.executeQuery();
                while (rs.next()) {
                    return true;
                }

            } catch (SQLException se) {
                se.printStackTrace();
            }
            return false;
        }
    public static void signUp(usersModel users) {

        String sql = "INSERT INTO `users`(`username`, `password`) VALUES (?,?);";
        Connection con = DBconnection.con;
        try {
            PreparedStatement prest = con.prepareStatement(sql);
            
            prest.setString(1, users.getUsernameid());
            prest.setString(2, users.getPasswordid());
            
            prest.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}



