import com.thinking.machines.banking.dl.*;
import com.thinking.machines.banking.dl.exceptions.*;
import com.thinking.machines.banking.dl.interfaces.*;
public class AccountGetByPANNumberTestCase
{
public static void main(String data[])
{
String vPANNumber=data[0];
AccountDTOInterface accountDTOInterface;
try
{
AccountDAOInterface accountDAOInterface =new AccountDAO();
accountDTOInterface=accountDAOInterface.getByPANNumber(vPANNumber);
}
catch( DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}