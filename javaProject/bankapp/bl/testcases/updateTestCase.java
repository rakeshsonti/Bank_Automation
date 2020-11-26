import com.thinking.machines.banking.bl.*;
import com.thinking.machines.banking.bl.pojo.*;
import com.thinking.machines.banking.bl.interfaces.*;
import com.thinking.machines.banking.bl.exceptions.*;
public class updateTestCase
{
public static void main(String gg[])throws ProcessException,ValidationException
{
try
{
int vAccountNumber;
String vFirstName;
String vLastName;
String vGender;
String vPANNumber;
vFirstName=gg[0];
vLastName=gg[1];
vGender=gg[2];
vPANNumber=gg[3];
vAccountNumber=Integer.parseInt(gg[4]);
//System.out.println( vFirstName+" "+vLastName+" "+vGender+" "+vPANNumber+" "+vAccountNumber );
AccountInterface accountInterface=new Account();
accountInterface.setFirstName(vFirstName);
accountInterface.setLastName(vLastName);
accountInterface.setGender(vGender);
accountInterface.setPANNumber(vPANNumber);
accountInterface.setAccountNumber(vAccountNumber);
AccountManagerInterface accountManagerInterface=new AccountManager();
accountManagerInterface.updateAccount(accountInterface);
System.out.println("Account update"+vAccountNumber);
}
catch(ValidationException validationException)
{
throw new ProcessException(validationException.getMessage());
}
}
}