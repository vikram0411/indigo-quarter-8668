package AccountantDao;
import java.util.ArrayList;
import java.util.List;

import exeptions.*;
public interface Accountant {

	public boolean login(String name,String password)throws AccountantException ;
	public String updateCustomer(String cname)throws AccountantException;	
	public String getCustomer(Long num)throws AccountantException;
	public ArrayList<String> getCustomers()throws AccountantException;
	public String WD(String name)throws AccountantException;
	public String del(Long name)throws AccountantException;
	
}
