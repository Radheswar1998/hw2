package hw2;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class hw2 {
   static final String DB_URL = "jdbc:mysql://localhost:3306/hw2";
   static final String USER = "root";
   static final String PASS = "Radheswar1998";
   

   public static void main(String[] args) {
      // Open a connection
	  
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
      ) {
    	 Scanner sc= new Scanner(System.in);
    	 int a =1;
    	 while(a==1) {
    	 System.out.printf("Select Option :1-view tables, 2-insert data , 3-modify budget, 4-delete department%n");
    	 int op = sc.nextInt();
    	 if(op == 1) {
		 System.out.printf("enter the table name:%n");
    	 String str = sc.next();
    	 if(str.equals("prereq")) {
    		 String sql = "SELECT * FROM prereq";
             ResultSet rs = stmt.executeQuery(sql);
             while(rs.next()){
                //Display values
                System.out.print("ID: " + rs.getString("course_id"));
                System.out.printf(", prereq: " + rs.getString("prereq_id%n"));
         
             }
             System.out.printf("WIll you try again after this? If yes select 1 or else 0%n");
        	 a = sc.nextInt();
             rs.close();
             
    	 }
    	 else if(str.equals("instructor")) {
    		 String sql = "SELECT * FROM instructor";
             ResultSet rs = stmt.executeQuery(sql);
             while(rs.next()){
                //Display values
                System.out.print("ID: " + rs.getString("ID"));
                System.out.print("Name: " + rs.getString("name"));
                System.out.print("dept_name: " + rs.getString("dept_name"));
                System.out.printf("ID: " + rs.getInt("salary")+"%n");
             }
             System.out.printf("WIll you try again after this? If yes select 1 or else 0%n");
        	 a = sc.nextInt();
             rs.close();
    		 
    	 }
    	 else if(str.equals("course")){
    		 String sql = "SELECT * FROM course";
             ResultSet rs = stmt.executeQuery(sql);
             while(rs.next()){
                //Display values
                System.out.print("ID: " + rs.getString("course_id"));
                System.out.print("Title: " + rs.getString("title"));
                System.out.print("Dept_Name: " + rs.getString("dept_name"));
                System.out.printf("Credits: " + rs.getInt("credits")+"%n");
             }
             System.out.printf("WIll you try again after this? If yes select 1 or else 0%n");
        	 a = sc.nextInt();
             rs.close();
    	 }
    	 else {
    		 System.out.printf("Wrong input%n");
    		 System.out.printf("WIll you try again after this? If yes select 1 or else 0%n");
        	 a = sc.nextInt();
    	 }
    	 }
    	 else if(op==2) {
    		 System.out.printf("option 2 selected%n");
    	 
    	 PreparedStatement pStmt = conn.prepareStatement( 
                 "insert into department values(?,?,?)");
    
    	 System.out.printf("Enter Dept_name:%n");
    	 String b = sc.next();
    	 System.out.printf("Dept building: %n");
    	 String c = sc.next();
    	 System.out.printf("Enter budget: %n");
    	 int d = sc.nextInt();
    	 pStmt.setString(1, b);
    	 pStmt.setString(2, c);
    	 pStmt.setInt(3, d);
    	 pStmt.executeUpdate();
    	 System.out.printf("Updated in the department table %n");
    	 System.out.printf("WIll you try again after this? If yes select 1 or else 0%n");
    	 a = sc.nextInt();
    	 
        
    	 }
    	 else if(op==3) {
    		 System.out.printf("option 3 selected%n");
    		 System.out.printf("Enter the department name:%n");
    		 String dept = sc.next();
    		 String sql2 = "select budget from department where dept_name = '"+dept+"'";
    		 ResultSet rs2 = stmt.executeQuery(sql2);
    		 rs2.next();
    		 System.out.printf("Budget:%n " + rs2.getInt("budget")+"%n");
    		 rs2.close();
    		 System.out.printf("Enter the new budget :%n");
    		 int bud = sc.nextInt();
    		 PreparedStatement pstmt =  conn.prepareStatement("UPDATE department SET budget ='"+bud+"'where dept_name = '"+dept+"'");
    		 pstmt.execute();
    		 
    		 System.out.printf("Budget updated%n");
    		 System.out.printf("WIll you try again after this? If yes select 1 or else 0%n");
        	 a = sc.nextInt();
    	
    	 }
    	 else if(op==4) {
    		 System.out.printf("option 4 selected%n");
    		 System.out.printf("Enter the department name:%n");
    		 String dept2 = sc.next();
    		 System.out.printf("Are you sure? y or n%n");
    		 String ans = sc.next();
    		 if(ans.equals("y")) {
    			 PreparedStatement pstmt =  conn.prepareStatement("DELETE from department where dept_name = '"+dept2+"'");
        		 pstmt.execute();
        		 System.out.printf("Deleted%n");
    		 }
    		 else {
    			 System.out.printf("Not deleted%n");
    		 }
    		 System.out.printf("WIll you try again after this? If yes select 1 or else 0%n");
        	 a = sc.nextInt();
    	 }
    	 else {
    		 System.out.printf("Wrong option%n");
    		 System.out.printf("WIll you try again after this? If yes select 1 or else 0%n");
        	 a = sc.nextInt();
    	 }
    	 }
      } 
      catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}