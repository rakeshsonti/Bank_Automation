import com.thinking.machines.banking.bl.*;
import com.thinking.machines.banking.bl.pojo.*;
import com.thinking.machines.banking.bl.interfaces.*;
import com.thinking.machines.banking.bl.exceptions.*;
class getCountTestCase
{
public static void main(String gg[])throws ProcessException,ValidationException
{
try
{
int totalRec=0;
AccountManagerInterface accountManagerInterface=new AccountManager();
totalRec=accountManagerInterface.getCount();

System.out.println("Total Record :"+totalRec);
System.out.println(" get Count Invoked\n");
}
catch(ValidationException validationException)
{
throw new ProcessException(validationException.getMessage());
}
}
}