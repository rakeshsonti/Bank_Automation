import com.thinking.machines.seq.*; 
import com.thinking.machines.seq.interfaces.*; 
import com.thinking.machines.seq.exceptions.*; 
import com.thinking.machines.banking.dl.*;
import com.thinking.machines.banking.dl.exceptions.*;
import com.thinking.machines.banking.dl.interfaces.*;

public class AccountUpdateTestCase 
{
public static void main(String gg[])
{
int accountNumber=Integer.parseInt(gg[0]);
String firstName=gg[1];
String lastName=gg[2];
String gender=gg[3];
String panNumber=gg[4];
try
{
AccountDTOInterface accountDTOInterface =new AccountDTO();
accountDTOInterface.setAccountNumber(accountNumber);
accountDTOInterface.setFirstName(firstName);
accountDTOInterface.setLastName(lastName);
accountDTOInterface.setGender(gender);
accountDTOInterface.setPANNumber(panNumber);
AccountDAOInterface accountDAOInterface=new AccountDAO();
accountDAOInterface.update(accountDTOInterface);
System.out.println("Account update"+accountDTOInterface.getAccountNumber());
System.out.println(accountNumber+"    "+ firstName +"   "+lastName+"    "+gender+"       "+panNumber);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
