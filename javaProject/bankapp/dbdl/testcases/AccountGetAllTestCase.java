import com.thinking.machines.banking.dl.*;
import com.thinking.machines.banking.dl.interfaces.*;
import com.thinking.machines.banking.dl.exceptions.*;
import java.util.*;
public class AccountGetAllTestCase
{
public static void main(String gg[])
{
try
{
AccountDAOInterface accountDAOInterface=new AccountDAO();
List<AccountDTOInterface> accounts=accountDAOInterface.getAll();
for(AccountDTOInterface accountDTOInterface:accounts)
{
System.out.println(accountDTOInterface.getAccountNumber());
System.out.println(accountDTOInterface.getFirstName());
System.out.println(accountDTOInterface.getLastName());
System.out.println(accountDTOInterface.getGender());
System.out.println(accountDTOInterface.getPANNumber());
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}