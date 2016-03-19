/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author it3680110
 */
public class UserDAOImpl implements UserDAO {

    private static int isAdmin = -1000;

    @Override
    public int authenticate(User user) {

        Connection DBConn = null;

        String ulid;
        String password;
        String userId;
        String createdTS;
        String lastUpdtTS;

        String query1 = "SELECT COUNT(1) FROM USERS WHERE ULID='" + user.getUlid().toLowerCase().toString().trim() + "'";
        String query = "SELECT * FROM USERS WHERE ULID = '" + user.getUlid().toLowerCase().toString().trim() + "' AND PASSWORD = '" + user.getPassword().toString().trim() + "'";
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/sfsystem";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");

            Statement stmt1 = DBConn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(query1);
            int exists =0; //0 = false, 1= true
            while (rs1.next()) {

                exists = Integer.parseInt(rs1.getString(1));
                System.out.println("CCHECK DB" + exists);
            }

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // if username exists only then check for login
            if (exists != 0) {
                while (rs.next()) {

                    userId = rs.getString(1);
                    ulid = rs.getString(2);
                    password = rs.getString("PASSWORD");
                    isAdmin = Integer.parseInt(rs.getString(4));
                    createdTS = rs.getString(5);
                    lastUpdtTS = rs.getString(6);

                    System.out.println("C>" + createdTS + " | U>" + ulid + " | P>" + password);
                }
            }
            // else set isAdmin to unregistered user i.e = -2
            else{
            isAdmin = -2;
            }

            rs.close();
            rs1.close();
            stmt.close();
            stmt1.close();
        } catch (Exception e) {
            isAdmin = -1;
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return isAdmin;
    }
}
