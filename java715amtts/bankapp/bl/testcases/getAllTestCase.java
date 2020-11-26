import com.thinking.machines.banking.bl.*;
import com.thinking.machines.banking.bl.pojo.*;
import com.thinking.machines.banking.bl.interfaces.*;
import com.thinking.machines.banking.bl.exceptions.*;
import java.util.*;
public class getAllTestCase
{
public static void main(String gg[])throws ProcessException,ValidationException
{
try
{
AccountManagerInterface accountManagerInterface=new AccountManager();
List<AccountInterface> all=new LinkedList<AccountInterface>();

all=accountManagerInterface.getAll();

for(AccountInterface account:all)
{
System.out.println("First Name: "+account.getFirstName()+"\n");
System.out.println("Last Name: "+account.getLastName()+"\n");
System.out.println(" Gender : "+account.getGender()+"\n");
System.out.println("PAN number : "+account.getPANNumber()+"\n");
System.out.println(" Account Number : "+account.getAccountNumber()+"\n");
System.out.println("--------------------------------------------------------------------------------------------\n");
}
}
catch(ValidationException validationException)
{
throw new ProcessException(validationException.getMessage());
}
System.out.println("Get all invoked");
}
}