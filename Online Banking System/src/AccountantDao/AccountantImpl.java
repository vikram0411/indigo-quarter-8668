package AccountantDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import exeptions.AccountantException;
import exeptions.CustomerException;
import Utility.Db;

public class AccountantImpl implements Accountant {

	@Override
	public boolean login(String name, String password) throws AccountantException {
		// TODO Auto-generated method stub
		
//		String str=null;
		
		try (Connection conn=Db.connect()){
			
			PreparedStatement pst =conn.prepareStatement("select * from Accountant where username=? and password=?" );
			pst.setString(1, name);
			pst.setString(2, password);
			
			ResultSet rs=pst.executeQuery();
//			System.out.println(rs);
			if(rs.next()) {
//				System.out.println("hii");
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			throw new AccountantException(e.getMessage());
			// TODO: handle exception
		}
	}
	
//	2........................................................................................................
	
	@Override
	public String updateCustomer(String cname) throws AccountantException {
		// TODO Auto-generated method stub
		
		try (Connection conn=Db.connect()){
			PreparedStatement cps=conn.prepareStatement("select * from customer where username =? ");
			cps.setString(1, cname);
			ResultSet nrs=cps.executeQuery();
			
			if(nrs.next()) {
				Scanner sc=new Scanner(System.in);
				System.out.println("you can now make changes in "+cname+"'s account");
				System.out.println("please select which value to change");
				System.out.println("1 : username");
				System.out.println("2 : password");
				System.out.println("3 : gender");
				System.out.println("4 : state");
				System.out.println("5 : exit");
				String cc=sc.next();
				
				while (!cc.equals("5")) {
					
				
				if(cc.equals("1")) {
					try {
						
						System.out.println("please enter the new value");
						String nname=sc.next();
						
						PreparedStatement ccps=conn.prepareStatement("update customer set username =? where username=? ");
						ccps.setString(1, nname);
						ccps.setString(2, cname);
						int y=ccps.executeUpdate();
						if(y>0) {
							return "update successfull";
						}else {
							throw new AccountantException("some error accured"); 
						}
					} catch (Exception e) {
						throw new AccountantException("username already taken by another user ");
					}
				}
				else if(cc.equals("2")) {
					System.out.println("please enter the new value");
					String nname=sc.next();
					
					PreparedStatement ccps=conn.prepareStatement("update customer set password =? where username=? ");
					ccps.setString(1, nname);
					ccps.setString(2, cname);
					int y=ccps.executeUpdate();
					if(y>0) {
						return "update successfull";
					}else {
						throw new AccountantException("some error accured"); 
					}
				}
				else if(cc.equals("3")) {
					System.out.println("please enter the new value");
					String nname=sc.next();
					
					PreparedStatement ccps=conn.prepareStatement("update customer set gender =? where username=? ");
					ccps.setString(1, nname);
					ccps.setString(2, cname);
					int y=ccps.executeUpdate();
					if(y>0) {
						return "update successfull";
					}else {
						throw new AccountantException("some error accured"); 
					}
				}else if(cc.equals("4")) {
					System.out.println("please enter the new value");
					String nname=sc.next();
					
					PreparedStatement ccps=conn.prepareStatement("update customer set state =? where username=? ");
					ccps.setString(1, nname);
					ccps.setString(2, cname);
					int y=ccps.executeUpdate();
					if(y>0) {
						return "update successfull";
					}else {
						throw new AccountantException("some error accured"); 
					}
				}else {
					System.out.println("please select a valid option");
					System.out.println();
					System.out.println("please select which value to change");
					System.out.println("1 : username");
					System.out.println("2 : password");
					System.out.println("3 : gender");
					System.out.println("4 : state");
					System.out.println("5 : exit");
					cc=sc.next();
				}
				
				}
				
			}else {
				throw new AccountantException("no Customer present for this name");
			}
		} catch (SQLException e){
//			System.out.println(e.getMessage());
			throw new AccountantException(e.getMessage());
			// TODO: handle exception
		}
		
		
		return "";
	}

	@Override
	public String getCustomer(Long num) throws AccountantException {
		// TODO Auto-generated method stub
		
		try (Connection conn=Db.connect()){
			
			PreparedStatement cps=conn.prepareStatement("select * from customer where accountNo =? ");
			cps.setLong(1, num);
			ResultSet rs=cps.executeQuery();
			if(rs.next()) {
				return"Account no : " +rs.getInt("accountNo")+" | "+" username : "+rs.getString("username")+" | "+" password : "+rs.getString("password")+" | "+" Gender : "+rs.getString("gender")+" | "+" State : "+rs.getString("state")+" | "+" Account Balance : "+rs.getInt("Account_Balance")+" | "+" Transaction History : "+rs.getString("Transaction_history");
			}else {
				throw new AccountantException("NO  Customer for this user name");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new AccountantException(e.getMessage());
			// TODO: handle exception
		}
		
	}

	@Override
	public ArrayList<String> getCustomers() throws AccountantException {
		// TODO Auto-generated method stub
		
         try (Connection conn=Db.connect()){
			
			PreparedStatement cps=conn.prepareStatement("select * from customer ");
//			cps.setString(1, name);
			ArrayList<String> l1=new ArrayList<>();
			
			ResultSet rs=cps.executeQuery();
			
			boolean flag=false;
			
			while(rs.next()) {
				flag=true;
				l1.add("Account no : " +rs.getInt("accountNo")+" | "+" username : "+rs.getString("username")+" | "+" password : "+rs.getString("password")+" | "+" Gender : "+rs.getString("gender")+" | "+" State : "+rs.getString("state")+" | "+" Account Balance : "+rs.getInt("Account_Balance")+" | "+" Transaction History : "+rs.getString("Transaction_history"));
//				return
				}
			if(flag){
				return l1;
			}
			else {
				throw new AccountantException("NO user found for this user name");
			}
			
		} catch (SQLException e) {
//			System.out.println(e.getMessage());
			throw new AccountantException(e.getMessage());
			// TODO: handle exception
		}
	}

	@Override
	public String WD(String name) throws AccountantException {
		// TODO Auto-generated method stub
		try(Connection conn=Db.connect()){
			PreparedStatement cps=conn.prepareStatement("select * from customer where username =? ");
			cps.setString(1, name);
			ResultSet nrs=cps.executeQuery();
			
			if(nrs.next()) {
				Scanner sc=new Scanner(System.in);
				System.out.println("you can now perform  deposite and withdrawal operations in "+name+"'s account");
			System.out.println("please chose a operation to perform");
			System.out.println("1 : Withdraw");
			System.out.println("2 : Deposit");
			System.out.println("3 : Exit");
			String num=sc.next();
			while (!num.equals("3")) {
			if(num.equals("1")) {
				System.out.println("please enter amount");
				Long amt=sc.nextLong();
				if(amt<=nrs.getInt( "Account_Balance")) {
					String tt=nrs.getString("Transaction_history")+"  ,-"+amt;
					PreparedStatement aas=conn.prepareStatement("update customer set Account_Balance = Account_Balance -? ,Transaction_history=?  where username = ?");
					aas.setLong(1, amt);
					aas.setString(2, tt);
					aas.setString(3, name);
					
					int z=aas.executeUpdate();
					if(z>0) {
					Long bala=nrs.getLong("Account_Balance")-amt;
		 			return"withdrawal successfull \n "+"Account no : " +nrs.getInt("accountNo")+" | "+" username : "+nrs.getString("username")+" | "+" password : "+nrs.getString("password")+" | "+" Gender : "+nrs.getString("gender")+" | "+" State : "+nrs.getString("state")+" | "+" Account Balance : "+bala+" | "+" Last Transaction : -"+amt;
					}else {
						throw new AccountantException("some error accured");
					}
				    
				}else{
					throw new AccountantException("not enough money to withdraw");
				}
			}else if(num.equals("2")) {
				System.out.println("please enter amount");
				Long amt=sc.nextLong();
				
					String tt=nrs.getString("Transaction_history")+"  ,+"+amt;
					PreparedStatement aas=conn.prepareStatement("update customer set Account_Balance = Account_Balance +? ,Transaction_history=?  where username = ?");
					aas.setLong(1, amt);
					aas.setString(2, tt);
					aas.setString(3, name);
					
					int z=aas.executeUpdate();
					if(z>0) {
						Long bala=nrs.getLong("Account_Balance")+amt;
			 			return"Deposit successfull \n "+"Account no : " +nrs.getInt("accountNo")+" | "+" username : "+nrs.getString("username")+" | "+" password : "+nrs.getString("password")+" | "+" Gender : "+nrs.getString("gender")+" | "+" State : "+nrs.getString("state")+" | "+" Account Balance : "+bala+" | "+" Last Transaction : +"+amt;
						}
					else {
						throw new AccountantException("some error accured");
					}
				    
			}
			else {
				System.out.println();
				System.out.println("please select a valid option");
				System.out.println("please chose a operation to perform");
				System.out.println("1 : Withdraw");
				System.out.println("2 : Deposit");
				System.out.println("3 : Exit");
				num=sc.next();
			}
			}
			}
			else {
				throw new AccountantException("NO Customer found for  user name : "+name);
			}
			
			
		} catch (SQLException e) {
//			System.out.println(e.getMessage());
			throw new AccountantException(e.getMessage());
			// TODO: handle exception
		}
		return "";
		
	}

	@Override
	public String del(Long AcNo) throws AccountantException {
		// TODO Auto-generated method stub
		try (Connection conn=Db.connect()){
			
			PreparedStatement cps=conn.prepareStatement("select * from customer where accountNo =? ");
			cps.setLong(1, AcNo);
			ResultSet nrs=cps.executeQuery();
			
			if(nrs.next()) {
				PreparedStatement del=conn.prepareStatement("delete from customer where accountNo =? ");
				del.setLong(1, AcNo);
				int c=del.executeUpdate();
				if(c>0) {
					return"Account no : " +nrs.getInt("accountNo")+" | "+" username : "+nrs.getString("username")+" | "+" password : "+nrs.getString("password")+" | "+" Gender : "+nrs.getString("gender")+" | "+" State : "+nrs.getString("state")+" | "+" Account Balance : "+nrs.getInt("Account_Balance")+" | "+" Transaction History : "+nrs.getString("Transaction_history")+"\n Account deleted successfully";
					
				}
				else {
					return "Account deleteing unsuccessfully";
				}
			}
			else {
				throw new AccountantException("NO user found for this Account Number");	
			}

			
		} catch (SQLException e) {
			throw new AccountantException(e.getMessage());
			// TODO: handle exception
		}

	}


}
