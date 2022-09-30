package CustomerDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Bean.CustomerBean;
import Utility.Db;
import exeptions.AccountantException;
import exeptions.CustomerException;

public class CustomerImpl implements Customer
{

	@Override
	public String AddCustomer(CustomerBean c) throws CustomerException {
		// TODO Auto-generated method stub
		String name=c.getName();
		String password=c.getPassword();
		String gender=c.getGender();
		String state=c.getState();
		int balance=c.getAccountBalance();
		String transactions=c.getTransactionDetails();
		
		try (Connection conn=Db.connect()){

			PreparedStatement cps=conn.prepareStatement("select * from customer where username =? ");
			cps.setString(1, name);
			ResultSet nrs=cps.executeQuery();
			
			if(nrs.next()) {
				throw new CustomerException("username already taken please select another name ");
			}else {
				
				PreparedStatement ps=conn.prepareStatement("insert into customer(username,password,gender,state,Account_Balance,transaction_history) values(?,?,?,?,?,?)");
				
				ps.setString(1, name);
				ps.setString(2, password);
				ps.setString(3, gender);
				ps.setString(4, state);
				ps.setInt(5,balance);
				ps.setString(6, transactions);
//			System.out.println("4567");
				
				int x=ps.executeUpdate();
				System.out.println(x);
				if(x>0) {
					PreparedStatement nn=conn.prepareStatement("select * from customer where username =? ");
					nn.setString(1, name);
					ResultSet nri=nn.executeQuery();
					
					nri.next();
					return   "Account created Successfully \n Account no : " +nri.getInt("accountNo")+" | "+" username : "+nri.getString("username")+" | "+" password : "+nri.getString("password")+" | "+" Gender : "+nri.getString("gender")+" | "+" State : "+nri.getString("state")+" | "+" Account Balance : "+nri.getInt("Account_Balance")+" | "+" Transaction History : "+nri.getString("Transaction_history");
											
				}else {
					throw new CustomerException("Account creation unsuccessfull: TEchnical Error");
				
			   }	
		}
			} catch (SQLException e){
//			System.out.println(e.getMessage());
			throw new CustomerException(e.getMessage());
			// TODO: handle exception
		}
	}

	
	
//	2...................................................................
	@Override
	public boolean login(String name, String password) throws CustomerException {
	try (Connection conn=Db.connect()){
			
			PreparedStatement pst =conn.prepareStatement("select * from customer where username=? and password=?" );
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
			
		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
			// TODO: handle exception
		
	}

	

}
	@Override
	public String transfee(String nn,String n) throws CustomerException {
		// TODO Auto-generated method stub
		
		try (Connection conn=Db.connect()){
			
			PreparedStatement pst =conn.prepareStatement("select * from customer where username=? " );
			pst.setString(1, nn);
			
			ResultSet rs=pst.executeQuery();
//			System.out.println(rs);
			if(rs.next()) {
				PreparedStatement qwe =conn.prepareStatement("select * from customer where username=? " );
				qwe.setString(1, n);
				ResultSet rsa=qwe.executeQuery();
				rsa.next();
				int bal=rsa.getInt("Account_Balance");
//				System.out.println(bal);
				
				Scanner sc=new Scanner(System.in);
				System.out.println("enter the amount you want to transfer");
			    int amt =sc.nextInt();
			    if(bal>=amt) {
			    	String tt=rsa.getString("transaction_history")+",-"+amt;
					PreparedStatement aas=conn.prepareStatement("update customer set Account_Balance = Account_Balance -? ,transaction_history=?  where username = ?");
					aas.setInt(1, amt);
					aas.setString(2, tt);
					aas.setString(3, n);
					int z=aas.executeUpdate();	
					
					String tt2=rs.getString("transaction_history")+",+"+amt;
//					System.out.println(tt2);
					PreparedStatement aas2=conn.prepareStatement("update customer set Account_Balance = Account_Balance +? ,transaction_history=?  where username = ?");
					aas2.setInt(1, amt);
					aas2.setString(2, tt2);
					aas2.setString(3, nn);
					int z2=aas2.executeUpdate();	
					
					if(z>0 && z2>0) {
						
						int bag1=rsa.getInt("Account_Balance")-amt;
						int bag2=rs.getInt("Account_Balance")+amt;
				 return"Transfer successfull \n "+"Sender"+"Account no : " +rsa.getInt("accountNo")+" | "+" username : "+rsa.getString("username")+" | "+" password : "+rsa.getString("password")+" | "+" Gender : "+rsa.getString("gender")+" | "+" State : "+rsa.getString("state")+" | "+" Account Balance : "+bag1+" | "+"\n"+"Receiver"+"Account no : " +rs.getInt("accountNo")+" | "+" username : "+rs.getString("username")+" | "+" password : "+rs.getString("password")+" | "+" Gender : "+rs.getString("gender")+" | "+" State : "+rs.getString("state")+" | "+" Account Balance : "+bag2+" | "  ;
						
					}else {
						throw new CustomerException("some error occured");	
					}
			    }else {
			    	throw new CustomerException("not enough amount to transfer");
			    }
//				System.out.println("hii");
//				return true'
			}else {
				throw new CustomerException("no Account found fot this user name");
			}
			
		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
			// TODO: handle exception
		}catch (InputMismatchException e) {
			// TODO: handle exception
			throw new CustomerException(" Transaction failed : invalid input");
		}
		
	
	}



	@Override
	public String transactions(String name) throws CustomerException {
		// TODO Auto-generated method stub
		try (Connection conn=Db.connect()){
			PreparedStatement cps=conn.prepareStatement("select * from customer where username =? ");
			cps.setString(1, name);
			ResultSet nrs=cps.executeQuery();
			if(nrs.next()) {
				return "Transcaction History : "+nrs.getString("transaction_history");
			}else {
				throw new CustomerException("No user found with this name");
			}
			
		} catch (SQLException e){
			System.out.println(e.getMessage());
			throw new CustomerException(e.getMessage());
			// TODO: handle exception
		}
		
		
	}
	}
