package CustomerDao;
import Bean.CustomerBean;
import exeptions.AccountantException;
import exeptions.CustomerException;
public interface Customer {

	public String AddCustomer(CustomerBean customer)throws CustomerException;
	public boolean login(String name,String password)throws CustomerException ;
	public String transfee(String nn,String n)throws CustomerException;
	public String transactions(String name)throws CustomerException;
}
