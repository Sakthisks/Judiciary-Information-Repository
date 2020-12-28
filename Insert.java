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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Insert
{
    public static void main(String args[])
    {
    try (Scanner sc = new Scanner(System.in)){
        System.out.println("Enter the judge ID");
        int jid=sc.nextInt();
        System.out.println("Enter the judge Name");
        String jname;
        jname = sc.next();
        System.out.println("Enter the judge phonenumber");
        String pn;
        pn = sc.next();
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rajagiri");
        PreparedStatement ps;
        ps = con.prepareStatement("insert into judge values(?,?,?)");
        try {
            ps.setInt(1,jid);
        } catch (SQLException ex) {
            Logger.getLogger(Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps.setString(2,jname);
        } catch (SQLException ex) {
            Logger.getLogger(Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps.setString(3,pn);
        } catch (SQLException ex) {
            Logger.getLogger(Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
        int res=ps.executeUpdate();
        if(res==0)
            System.out.println("Record is not entered");
        else
            System.out.println("Record is entered");
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
catch(Exception e)
{
    e.printStackTrace();
}
            
}
}


