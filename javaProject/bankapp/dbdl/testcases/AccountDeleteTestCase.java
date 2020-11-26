import com.thinking.machines.banking.dl.*;
import com.thinking.machines.banking.dl.exceptions.*;
import com.thinking.machines.banking.dl.interfaces.*;

public class AccountDeleteTestCase 
{
public static void main(String gg[])
{
int accountNumber=Integer.parseInt(gg[0]);
try
{
AccountDTOInterface accountDTOInterface =new AccountDTO();
AccountDAOInterface accountDAOInterface=new AccountDAO();
accountDTOInterface.setAccountNumber(accountNumber);
accountDAOInterface.delete(accountNumber);
System.out.println("Account deleted : "+accountDTOInterface.getAccountNumber());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
