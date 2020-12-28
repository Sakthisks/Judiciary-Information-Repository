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
import java.util.Scanner;

public class Delete {
  
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Scanner sc = new Scanner(System.in);
      
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rajagiri");
      System.out.println("Connected database successfully...");
      stmt = conn.createStatement();
      String sql;
       sql = "DELETE FROM judge " +
               "WHERE jname='sakthi'";
      stmt.executeUpdate(sql);
      System.out.println("table after deletion:");
      sql = "SELECT jid,jname,j_phoneno from judge";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
         int jid  = rs.getInt("jid");
         String jname = rs.getString("jname");
         String phoneno = rs.getString("j_phoneno");
         //String eid = rs.getString("emailid");
         System.out.print("  JID:" + jid);
         System.out.print("   judgename:" + jname);
         System.out.println("   phoneno:" + phoneno);
         //System.out.println("   emailid" + eid);
      }
      rs.close();
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
   System.out.println("Goodbye!");
}
}

