import com.thinking.machines.banking.dl.*;
import com.thinking.machines.banking.dl.interfaces.*;
import com.thinking.machines.banking.dl.exceptions.*;
public class AccountGetByAccountNumberTestCase
{
public static void main(String data[])
{
int vAccountNumber;
vAccountNumber=Integer.parseInt(data[0]);
AccountDTOInterface accountDTOInterface;
try
{
AccountDAOInterface accountDAOInterface=new AccountDAO();
accountDTOInterface=accountDAOInterface.getByAccountNumber(vAccountNumber);
System.out.println(accountDTOInterface.getFirstName()+accountDTOInterface.getLastName()+accountDTOInterface.getGender()+accountDTOInterface.getPANNumber()+accountDTOInterface.getAccountNumber());
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
