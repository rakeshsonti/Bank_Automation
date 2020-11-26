import com.thinking.machines.banking.dl.*;
import com.thinking.machines.banking.dl.exceptions.*;
import com.thinking.machines.banking.dl.interfaces.*;
public class AccountAddTestCase 
{
public static void main(String gg[])
{
String firstName=gg[0];
String lastName=gg[1];
String gender=gg[2];
String panNumber=gg[3];
System.out.println( firstName +lastName+gender+panNumber);
try
{
AccountDTOInterface accountDTOInterface =new AccountDTO();
System.out.println("Ram");
accountDTOInterface.setFirstName(firstName);
accountDTOInterface.setLastName(lastName);
accountDTOInterface.setGender(gender);
accountDTOInterface.setPANNumber(panNumber);
AccountDAOInterface accountDAOInterface=new AccountDAO();
System.out.println("Ram1");
accountDAOInterface.add(accountDTOInterface);
System.out.println("Ram2");
System.out.println("Account added"+accountDTOInterface.getAccountNumber());
}
catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}
