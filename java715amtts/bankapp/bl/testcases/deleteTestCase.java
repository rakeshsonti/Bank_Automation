import com.thinking.machines.banking.bl.*;
import com.thinking.machines.banking.bl.pojo.*;
import com.thinking.machines.banking.bl.interfaces.*;
import com.thinking.machines.banking.bl.exceptions.*;
public class deleteTestCase
{
public static void main(String gg[])throws ProcessException,ValidationException
{
try
{
int vAccountNumber;
vAccountNumber=Integer.parseInt(gg[0]);
AccountManagerInterface accountManagerInterface=new AccountManager();
accountManagerInterface.deleteAccount(vAccountNumber);
System.out.println("Account delete:"+vAccountNumber);
}
catch(ValidationException validationException)
{
throw new ProcessException(validationException.getMessage());
}

}
}