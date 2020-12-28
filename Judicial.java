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

import java.util.Scanner;
import java.sql.*;
public class Judicial
{
	public static void main(String args[])
	{
		Scanner scanInt=new Scanner(System.in);
		Scanner scanStr=new Scanner(System.in);
		int t_cin,t_crid=0,t_cid=0,t_pid=0,t_jid=0,flag=0,t_rid=0;
		int i_cin,i_pid,i_jid,i_crid,i_cid,choice;
		String i_sdate,i_edate,i_loc,i_dname,i_dphone,i_type,i_aname,i_adate,i_cdate,i_cname;
		String t_rname=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","dbmspro","pass");
			System.out.println("*****JUDICIARY INFORMATION SYSTEM*****");
			System.out.println("CHOOSE YOUR CHOICE \n1.FILE A CASE \n2.GET DETAILS ABOUT A CASE");
			choice=scanInt.nextInt();
			
			
			//connecting to user
			/*
			 * rs=case
			 * rs1=commited
			 * rs2=crime
			 * rs3=criminal
			 * rs4=judge
			 * rs5=prosecutor
			 * rs6=dependent
			 * rs7=arrest
			 * rs8=reshedule
			 * rs9=register
			 * rs10=insert
			 * */
			//inserting
			switch(choice)
			{
			case 1:
			{
			System.out.println("CASE FILING BY DEPENDENT");
			System.out.println("Enter the DEPENDENT name :");
			i_dname=scanStr.nextLine();
			System.out.println("Enter the DEPENDENT contact number");
			i_dphone=scanStr.nextLine();
			System.out.println("Enter the case number");
			i_cin=scanInt.nextInt();
			System.out.println("Enter the name of criminal");
			i_cname=scanStr.nextLine();
			System.out.println("Enter the location of crime");
			i_loc=scanStr.nextLine();
			System.out.println("Enter the date of crime");
			i_cdate=scanStr.nextLine();
			System.out.println("Enter the starting date of case");
			i_sdate=scanStr.nextLine();
			System.out.println("Enter the ending date of case");
			i_edate=scanStr.nextLine();
			System.out.println("Enter the ID of PROSECUTOR handling this case");
			i_pid=scanInt.nextInt();
			System.out.println("Enter the ID of JUDGE handling this case");
			i_jid=scanInt.nextInt();
			
			
			
			//case
			String sql10="insert into case values (?,?,?,?,?)";
			PreparedStatement stmt10=con.prepareStatement(sql10);
			stmt10.setInt(1,i_cin);
			stmt10.setString(2,i_sdate);
			stmt10.setString(3,i_edate);
			stmt10.setInt(4,i_pid);
			stmt10.setInt(5,i_jid);
			ResultSet rs10=stmt10.executeQuery();
			//System.out.println("Case inserted");
			
			//dependent
			sql10="insert into dependent values (?,?,?)";
			stmt10=con.prepareStatement(sql10);
			stmt10.setString(1,i_dname);
			stmt10.setString(2,i_dphone);
			stmt10.setInt(3,i_cin);
			rs10=stmt10.executeQuery();
			//System.out.println("dependent inserted");
			
			System.out.println("*******CASE FILED *********");
			}
			break;
			case 2:
			{
			System.out.println("Enter the CIN of the case whose details are needed");
			t_cin=scanInt.nextInt();
			if(t_cin>=6001 && t_cin<=6005)
		
			{String sql="select * from case where cin=?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt(1,t_cin);
			ResultSet rs=stmt.executeQuery();
			//case details
			System.out.println("\n*******  CASE DETAILS  **********");
			while(rs.next())
			{
				System.out.println("CIN\t\t:"+rs.getInt(1)+"\nStarting Date\t:"+rs.getDate(2)+"\nEnding Date\t:"+rs.getDate(3));
				t_pid=rs.getInt(4);
				t_jid=rs.getInt(5);
			}
			
			
			//dependent
			System.out.println("\n******* COMPLAINED BY (DEPENDENT DETAILS)**********");
			String sql6="select * from DEPENDENT where cin=?";
			PreparedStatement stmt6=con.prepareStatement(sql6);
			stmt6.setInt(1,t_cin);
			ResultSet rs6=stmt6.executeQuery();
			while(rs6.next())
			{
				System.out.println("Name\t\t:"+rs6.getString(1)+"\nContact no\t:"+rs6.getString(2));
	
			}
			
			//fetching details from commited
			String sql1="select * from commited where cin=?";
			PreparedStatement stmt1=con.prepareStatement(sql1);
			stmt1.setInt(1,t_cin);
			ResultSet rs1=stmt1.executeQuery();
			while(rs1.next())
					{
						t_cid=rs1.getInt(3);
						t_crid=rs1.getInt(2);
					}
			//crime details
			System.out.println("\n*******CRIME DETAILS**********");
			String sql2="select * from crime where c_id=?";
			PreparedStatement stmt2=con.prepareStatement(sql2);
			stmt2.setInt(1,t_cid);
			ResultSet rs2=stmt2.executeQuery();
			while(rs2.next())
				   System.out.println("C_ID\t\t:"+rs2.getInt(1)+"\nType\t\t:"+rs2.getString(2)+"\nLocation\t:"+rs2.getString(3)+"\nDate\t\t:"+rs2.getDate(4));
			
			//criminal details
			System.out.println("\n*******CRIMINAL DETAILS**********");
			String sql3="select * from criminal where cr_id=?";
			PreparedStatement stmt3=con.prepareStatement(sql3);
			stmt3.setInt(1,t_crid);
			ResultSet rs3=stmt3.executeQuery();
			while(rs3.next())
				   {
						System.out.println("CR_ID\t\t:"+rs3.getInt(1)+"\nName\t\t:"+rs3.getString(2));
				   }
			
			//judge details
			System.out.println("\n*******JUDGE DETAILS**********");
			String sql4="select * from judge where j_id=?";
			PreparedStatement stmt4=con.prepareStatement(sql4);
			stmt4.setInt(1,t_jid);
			ResultSet rs4=stmt4.executeQuery();
			while(rs4.next())
				   {
						System.out.println("J_ID\t\t:"+rs4.getInt(1)+"\nName\t\t:"+rs4.getString(2)+"\nContact No\t:"+rs4.getInt(3));
				   }
			//prosecutor details
			System.out.println("\n*******PROSECUTOR DETAILS**********");
			String sql5="select * from prosecutor where p_id=?";
			PreparedStatement stmt5=con.prepareStatement(sql5);
			stmt5.setInt(1,t_pid);
			ResultSet rs5=stmt5.executeQuery();
			while(rs5.next())
				   {
						System.out.println("P_ID\t\t:"+rs5.getInt(1)+"\nName\t\t:"+rs5.getString(2)+"\nContact No\t:"+rs5.getInt(3)+"\nEmail id\t:"+rs5.getString(4));
				   }
			
			//arrest details
			System.out.println("\n*******ARREST DETAILS**********");
			String sql7="select * from arresting_officer where cr_id=?";
			PreparedStatement stmt7=con.prepareStatement(sql7);
			stmt7.setInt(1,t_crid);
			ResultSet rs7=stmt7.executeQuery();
			while(rs7.next())
				   {
						System.out.println("ARRESTED BY\t:"+rs7.getString(1)+"\nDate\t\t:"+rs7.getDate(3));
				   }
			
			//reshedule and register
			System.out.println("\n*******RESHEDULE DETAILS**********");
			String sql8="select * from reshedule";
			PreparedStatement stmt8=con.prepareStatement(sql8);
			ResultSet rs8=stmt8.executeQuery();
			while(rs8.next())
				   {
						int i=rs8.getInt(1);
						if(i==t_cin)
						{
						  System.out.println("CASE RESHEDULED TO "+rs8.getDate(4));	
						  flag=1;
						  t_rid=rs8.getInt(2);
						}
				   }
			
			if(flag==1)
			{
				String sql9="select * from register where r_id=?";
				PreparedStatement stmt9=con.prepareStatement(sql9);
				stmt9.setInt(1,t_rid);
				ResultSet rs9=stmt9.executeQuery();
				while(rs9.next())
					   {
						t_rname=rs9.getString(2);
					   }
				System.out.println("CASE RESHEDULED BY REGISTER	"+t_rname);
				
			}else		
				System.out.println("NO FURTHER RESHEDULEMENT FOR THIS CASE NO "+t_cin);
			}
			else
				System.out.println(t_cin+" : NO SUCH CASE FILED ");
				}
			}
			con.close();
			
			System.out.println("\n ***** THANK YOU *****");
			}catch(Exception e){ System.out.println(e);}
		
	}
}
