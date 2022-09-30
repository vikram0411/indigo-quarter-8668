package AccountantDao;

import exeptions.AccountantException;

public class AccCheck {

	public static void main(String[] args) {
		
		AccountantImpl aci = new  AccountantImpl();
		try {
		String str=	aci.updateCustomer("poo");
		
		
		} catch (AccountantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
	}
	
}
