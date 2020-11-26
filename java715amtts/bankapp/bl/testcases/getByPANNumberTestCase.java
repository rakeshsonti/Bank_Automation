import com.thinking.machines.banking.bl.*;
import com.thinking.machines.banking.bl.pojo.*;
import com.thinking.machines.banking.bl.interfaces.*;
import com.thinking.machines.banking.bl.exceptions.*;
class getByPANNumberTestCase
{
public static void main(String gg[])throws ProcessException,ValidationException
{
try
{
String vPANNumber;
AccountInterface accountInterface;
vPANNumber=gg[0];
AccountManagerInterface accountManagerInterface=new AccountManager();
accountInterface=accountManagerInterface.getByPANNumber(vPANNumber);

System.out.println("First Name :"+accountInterface.getFirstName()+"\n");
System.out.println("Last Name: "+accountInterface.getLastName()+"\n");
System.out.println("Gender : "+accountInterface.getGender()+"\n");
System.out.println("PAN Number : "+accountInterface.getPANNumber()+"\n");
System.out.println("Account  Number : "+accountInterface.getAccountNumber()+"\n");
System.out.println(" get By PAN Number Invoked\n");
}
catch(ValidationException validationException)
{
throw new ProcessException(validationException.getMessage());
}
}
}