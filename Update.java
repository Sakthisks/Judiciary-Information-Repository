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

public class Update {
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{    
      Class.forName("oracle.jdbc.driver.OracleDriver");
      conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rajagiri");    
      stmt = conn.createStatement();
      String sql = "UPDATE judge " +
                   "SET j_phoneno=9857568465 where jname='sam'";
      stmt.executeUpdate(sql);
      sql = "SELECT jid, jname, j_phoneno FROM judge";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
         int id  = rs.getInt("jid");        
         String first = rs.getString("jname");
         String last = rs.getString("j_phoneno");
        
         System.out.print("JID: " + id);
         System.out.print("  jname: " + first);
         System.out.println("  j_phoneno: " + last);
      }
      rs.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
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