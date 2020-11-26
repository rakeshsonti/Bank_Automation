import com.thinking.machines.banking.bl.*;
import com.thinking.machines.banking.bl.pojo.*;
import com.thinking.machines.banking.bl.interfaces.*;
import com.thinking.machines.banking.bl.exceptions.*;
public class addTestCase
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
System.out.println( vFirstName+" "+vLastName+" "+vGender+" "+vPANNumber);

AccountInterface accountInterface=new Account();

accountInterface.setFirstName(vFirstName);
accountInterface.setLastName(vLastName);
accountInterface.setGender(vGender);
accountInterface.setPANNumber(vPANNumber);

AccountManagerInterface accountManagerInterface=new AccountManager();

accountManagerInterface.addAccount(accountInterface);
}

catch(ValidationException validationException)
{

throw new ProcessException(validationException.getMessage());

}
System.out.println("Account add");
}
}