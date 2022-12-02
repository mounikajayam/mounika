package com.example.com.example.software;


import java.sql.*;
import java.util.Scanner;

public class JdbcConnection  {
	static final Scanner obj=new Scanner(System.in);
	public static void update(Connection connection,int software_id) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement("update  softwaredetails set  softwarename=? where software_id=?");
		String value=obj.next();
		pstmt.setString(1,value);
		pstmt.setInt(2,software_id);

		int record=pstmt.executeUpdate();
		System.out.println(record);

	}

	public static void addSoftwareDetails(Connection connection,SoftwareDetails  obj) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement("insert into softwaredetails values(?,?,?,?,?,?,?) ");
		pstmt.setString(1,obj.getsoftwarename());
		pstmt.setInt(2,obj.getsoftware_id());
		pstmt.setString(3,obj.getsoftwaredescription());
		pstmt.setInt(4,obj.getprice());
		pstmt.setString(5, obj.getcompanyname());
		pstmt.setInt(6,obj.getrating());
		pstmt.setInt(7, obj.getstock());

		int record=pstmt.executeUpdate();
		System.out.println(record);

	}
	public static void delete(Connection connection,int software_id) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement("delete from softwaredetails where price=?");
		int val=obj.nextInt();
		pstmt.setInt(1, val);
		int record=pstmt.executeUpdate();
		System.out.println(record);

	}
	public static void view(Connection connection,int software_id) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement("select * from softwaredetails where software_id=?");
		int id=obj.nextInt();
		pstmt.setInt(1,id);


		ResultSet record=pstmt.executeQuery();
while(record.next()) {
			System.out.println(record.getString(1)+" "+record.getInt(2)+" "+record.getString(3)+" "+record.getInt(4)+" "+record.getString(5)+" "+record.getInt(6)+" "+record.getInt(7));

		}

}
	



	public static void main(String[] args)throws SQLException, Exception{
		Connection connection=null;
		final  SoftwareDetails addobj =new SoftwareDetails();
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedetails", "root" ,"mouni@99989M");
		System.out.println("enter 1 for addsoftwaredetails");
		System.out.println("enter 2 for updatesoftware");
		System.out.println("enter 3 for deletesoftware");
		System.out.println("enter 4 for viewsoftware");
		System.out.println("enter your choice");
		int choice = obj.nextByte();
		switch(choice) {
			case 1:
				SoftwareDetails obj =  getSoftwareDetails ();
				if(connection!=null) {
					addSoftwareDetails(connection,obj);//method call
				}
				else {
					System.out.println("check your connection");
				}


				break;
			case 2:

				System.out.println("enter which record do you want to update");
				update(connection,125);

				break;
			case 3:
				System.out.println("enter which record do you want to delete");
				delete(connection,125);

				break;
			case 4:
				System.out.println("enter id to read the record");
				view(connection,145);

				break;

			default:
				System.out.println("enter proper choice");
				connection.close();


		}

	}
	private static SoftwareDetails getSoftwareDetails() {	
		SoftwareDetails user = new SoftwareDetails ();
		System.out.println("enter softwarename");
		user.setsoftwarename(obj.next());
		System.out.println("enter software_id");
		user.setsoftware_id(obj.nextInt());
		System.out.println("enter softwaredescription ");
		user.setsoftwaredescription(obj.next());
		System.out.println("enter price");
		user.setprice(obj.nextInt());
		System.out.println("enter companyname");
		user.setcompanyname(obj.next());
		System.out.println("enter rating");
		user.setrating(obj.nextInt());
		System.out.println("enter stock");
		user.setstock(obj.nextInt());
		return user;

	}



}









