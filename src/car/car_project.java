package car;

import java.sql.*;
import java.util.Scanner;

public class car_project {
public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in);
	String url = "jdbc:mysql://localhost:3306?user=root&password=Prem@1234";
	Connection con = null;
	PreparedStatement pstmt =null;
	ResultSet rs = null;
	char ans = 'y';
	while(ans =='y') {
	System.out.println("Welcome to Car Showroom !!!");
	System.out.println("press 1 to visit the showroom");
	System.out.println("press 2 to sell cars");
	System.out.println("press 3 to buy the cars");
	System.out.println("press 4 to see upcoming cars");
	
	System.out.println("Enter your choice->");
	int n = sc.nextInt();
	
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url);
		switch (n) {
		case 1:
			
			pstmt=con.prepareStatement("select * from car_wale.carinfo");
			rs = pstmt.executeQuery();
			System.out.println("Cars in Our Showroom are -> ");
			while(rs.next()) {
				int a = rs.getInt("car_id");
				String b =rs.getString("car_name");
				int c =rs.getInt("car_model");
				double d =rs.getDouble("car_price");
				System.out.println(a+" "+b+" "+c+" "+d);
			}
			
			System.out.println("Enter the following Details->");
			System.out.println("Enter user_name");
			String str=sc.next();
			System.out.println("Enter password");
			String str2=sc.next();
			System.out.println("Enter Your Name");
			sc.nextLine();
			String str3=sc.nextLine();
			System.out.println("Enter Location");
			String str4=sc.next();
			pstmt=con.prepareStatement("Insert into car_wale.user_info(user_name,password,name,location) values(?,?,?,?)");
			pstmt.setString(1, str);
			pstmt.setString(2, str2);
			pstmt.setString(3, str3);
			pstmt.setString(4, str4);
			pstmt.executeUpdate();
			break;
		
		case 2:
			System.out.println("Enter the Name of car you want to sell->");
			String p = sc.next();
			System.out.println("Enter car Model year->");
			int q =sc.nextInt();
			System.out.println("Enter car Price->");
			int r =sc.nextInt();
			pstmt = con.prepareStatement("Insert into car_wale.carinfo(car_name,car_model,car_price) values(?,?,?)");
			pstmt.setString(1, p);
			pstmt.setInt(2, q);
			pstmt.setDouble(3, r);
			pstmt.executeUpdate();
			break;
		case 3:
			System.out.println("Enter the car id of car which you want to buy ->");
			int x = sc.nextInt();
			pstmt=con.prepareStatement("select * from car_wale.carinfo where car_id=?");
			pstmt.setInt(1,x);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int a = rs.getInt("car_id");
				String b =rs.getString("car_name");
				int c =rs.getInt("car_model");
				double d =rs.getDouble("car_price");
				System.out.println("Congratulations..!! you owned a "+b+" of model "+c+" at price "+d);}
			pstmt=con.prepareStatement("Delete from car_wale.carinfo where car_id=?");
			pstmt.setInt(1,x);
			pstmt.executeUpdate();
			break;
		case 4:
			pstmt=con.prepareStatement("select * from car_wale.newcarinfo");
			rs = pstmt.executeQuery();
			System.out.println("Upcoming cars in Showroom are -> ");
			while(rs.next()) {
				int a = rs.getInt("car_id");
				String b =rs.getString("car_name");
				System.out.println(a+" "+b);
			}
			break;

		default:
			break;
		}
		
	} catch (Exception e) {
		System.out.println(e);
	}
	finally {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
}
		System.out.print("Want to continue : press Y");
		sc.next().charAt(0);
	}
	}}}
