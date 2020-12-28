/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Judicial_Repository;

/**
 *
 * @author sakth
 */

import java.sql.*;
public class Retrieve{
    
    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rajagiri"); Statement st = con.createStatement()) {
            ResultSet rs;
            System.out.println("JID JNAME J_PHONENO");
            rs = st.executeQuery("select * from judge");
            while (rs.next())
            {
                System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3));
                
            }
            rs.close();
        }
    }
}
