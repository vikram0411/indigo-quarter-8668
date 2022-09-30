package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import AccountantDao.*;
import Bean.CustomerBean;
import CustomerDao.*;
import Utility.Db;
import exeptions.AccountantException;
import exeptions.CustomerException;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("welcome to Online Banking System");
		System.out.println("please select an option to  continue");
		System.out.println("1 : login as a Accountant");
		System.out.println("2 : login as a Customer");
		System.out.println("3 : Exit");
		Scanner sc = new Scanner(System.in);
		AccountantImpl ai = new AccountantImpl();
		CustomerImpl ci =new CustomerImpl();
		
		String choice= sc.next();
		while(!choice.equals("3")){
		if(choice.equals("1")) {
			System.out.println("enter username");
			String n=sc.next();
			System.out.println("enter password");
			String p=sc.next();
			try {
				boolean log=ai.login(n, p);
				if(log) {
					
					System.out.println();
					System.out.println("you are now logged in Accountant DataBase");
					System.out.println("please select an option according to the operation you want to perform");
					System.out.println("1 : add a new cutomer account");
					System.out.println("2 : edit a existing cutomer account");
					System.out.println("3 : view a customer account details");
					System.out.println("4 : view all account details");
					System.out.println("5 : do deposite or withdrawl in customer account");
					System.out.println("6 : delete customer account");
					System.out.println("7 : Exit");

					String AccountantChoice=sc.next();
					while(!AccountantChoice.equals("7"))  {
						
					if(AccountantChoice.equals("1")) {
						
						
						try {
							System.out.println("enter customer name");
							String cn=sc.next();
							System.out.println("create a password for customer");
							String cp=sc.next();
							System.out.println("enter customer gneder M/F");
							String cg=sc.next();
							while(!cg.equals("M") && !cg.equals("F")) {
								System.out.println("Please enter gender in M/F format");
								 cg=sc.next();
//								 cg=sc.next();
							}
							System.out.println("enter customer state location");
							String cs=sc.next();
							
							boolean v=true;
							while(v) {
								
								try {
									System.out.println("enter deposite amount ");
									
									int camt=sc.nextInt();
									String th="+"+camt;
									CustomerBean cb=new CustomerBean(cn, cp, cg, cs, camt,th );
									String cans=ci.AddCustomer(cb);
									System.out.println(cans);
									v=false;
								} 	catch (InputMismatchException e) {
									System.out.println("please enter valid input");
									AccountantChoice= sc.next();
									System.out.println();
									continue;
								}
							}
							
							
							
							
						} catch (CustomerException e) {
							System.out.println(e.getMessage());
						}
//					
						
					}else if(AccountantChoice.equals("2")) {
						
						
						try {
							System.out.println("enter customer name ");
							String cna=sc.next();
							
							String A2=ai.updateCustomer(cna);
							System.out.println(A2);
							
						} catch (AccountantException e) {
							System.out.println(e.getMessage());
						}
						
					}else if(AccountantChoice.equals("3")) {
							
						try {
							
							System.out.println("enter Account number ");
							Long cna=sc.nextLong();
							
							String A3=ai.getCustomer( cna);
							System.out.println(A3);
							
							
						} catch (InputMismatchException e){
							System.out.println("not a valid input: no account found");
							sc.next();

						}catch (AccountantException e) {
							// TODO: handle exception
							
							System.out.println(e.getMessage());
							
						}
					
						
					}
					else if(AccountantChoice.equals("4")) {
						ArrayList<String> A4=ai.getCustomers();
						for(String i:A4) {
							System.out.println(i);
						}
					}
					else if(AccountantChoice.equals("5")){
						
						try {
							System.out.println("enter customer name ");
							String cna=sc.next();
							
							String A5=ai.WD(cna);
							
							System.out.println(A5);
							
						} catch (InputMismatchException e) {
						System.out.println("Error accured due to invalid input");
						}catch (AccountantException e) {
							System.out.println(e.getMessage());
							
						}
					}
					else if(AccountantChoice.equals("6")) {
						
						try {
							System.out.println("enter customer account number ");
							Long cna=sc.nextLong();
							
							String A6=ai.del(cna);
						
                            System.out.println(A6);
							
						} catch (InputMismatchException e) {
							sc.next();
						}catch (AccountantException e) {
							System.out.println(e.getMessage());
						}
					
					}
					else {
						System.out.println();
						System.out.println("please enter a valid option");
						System.out.println();
					}
						 System.out.println();
							System.out.println("please select an option according to the operation you want to perform");
							System.out.println("1 : add a new cutomer account");
							System.out.println("2 : edit a existing cutomer account");
							System.out.println("3 : view a customer account details");
							System.out.println("4 : view all account details");
							System.out.println("5 : do deposite or withdrawl in customer account");
							System.out.println("6 : delete customer account");
							System.out.println("7 : Exit");
							AccountantChoice= sc.next();
					
				
					}
					System.out.println("logging out of Accountant Database... ");
					System.out.println();
					
				}else {
					System.out.println("incorrect username or password");
				}

			} catch (AccountantException e) {
			
				System.out.println(e.getMessage());;
			}
			
			
		}
		
		
		
		
//		//////////////////////////////////////////////////////////////////// customer queries
		else if(choice.equals("2")) {
			System.out.println("enter username");
			String n=sc.next();
			System.out.println("enter password");
			String p=sc.next();
			try {
				
				boolean log=ci.login(n, p);
				
				if(log) {
					System.out.println();
					System.out.println("login successfull uou are in "+n+"'s user account");
					System.out.println("please select an option according to the operation you want to perform");
					System.out.println("1 : transfer money to another account");
					System.out.println("2 : see transaction History");
					System.out.println("3 : Exit");
					String slc=sc.next();
					
					while(!slc.equals("3")) {
					if(slc.equals("1")) {
						try {
							
							System.out.println();
							System.out.println("enter the customer name you want to transfer money to");
							String cna=sc.next();
							if(n.equals(cna)) {
								System.out.println("cant send money to yourself");
							}else {							
								String trs=ci.transfee(cna, n);
								System.out.println(trs);
							}
						} catch (CustomerException e) {
							// TODO: handle exception
							System.out.println(e.getMessage());
						}
					}else if(slc.equals("2")) {
						
						String his=ci.transactions( n);
						System.out.println(his);
					}else {
						System.out.println("please select a valid option");
					}
					System.out.println();
					System.out.println("please select an option according to the operation you want to perform");
					System.out.println("1 : transfer money to another account");
					System.out.println("2 : see transaction History");
					System.out.println("3 : Exit");
					 slc=sc.next();
					}
					System.out.println("Logging out of "+n+"'s account...");
					
				}else {
					System.out.println("incorrect username or password");
				}
				
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		else{
			System.out.println("please select a valid option");
		}
		System.out.println();
		System.out.println("please select an option to  continue");
		System.out.println("1 : login as a Accountant");
		System.out.println("2 : login as a Customer");
		System.out.println("3 : Exit");
		choice=sc.next();
		
		}	
		System.out.println("Thanks for visiting");
	}
	
}
