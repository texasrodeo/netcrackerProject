package com.netcrackerTask.backend.business;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    private static final String url = "jdbc:mysql://localhost:3306/accountstore?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String user = "root";
    private static final String password = "";

    //   private  static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
//
//    private  void getConnection(){
//
//        try {
//            // opening database connection to MySQL server
//            con = DriverManager.getConnection(url, user, password);
//
//        } catch (SQLException sqlEx) {
//            sqlEx.printStackTrace();
//        }
//
//
//    }

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }

    public List<String> executeSelect(String query) throws SQLException {
        List<String> result = new ArrayList<String>();
        try{

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()){
                result.add(rs.getString(1));
            }
        }
        catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        finally {

            stmt.close();
            rs.close();

        }
        return result;
    }

}
