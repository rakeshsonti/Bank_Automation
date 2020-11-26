import com.thinking.machines.banking.dl.*;
import com.thinking.machines.banking.dl.interfaces.*;
import com.thinking.machines.banking.dl.exceptions.*;
import com.thinking.machines.seq.*;
import com.thinking.machines.seq.exceptions.*;
import com.thinking.machines.seq.interfaces.*;
public class AccountGetCountTestCase
{
public static void main(String gg[])
{
int countValue;
try
{
AccountDAOInterface accountDAOInterface=new AccountDAO();
countValue=accountDAOInterface.getCount();
System.out.println("Total records in file are="+countValue);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}