package com.thinking.machines.banking.dl;
import java.sql.*;
import com.thinking.machines.banking.dl.*;
import com.thinking.machines.banking.dl.exceptions.*;
import com.thinking.machines.banking.dl.interfaces.*;
import java.util.*;
public class AccountDAO implements AccountDAOInterface
{
public void add(AccountDTOInterface accountDTOInterface)throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select 1 as result from  account where pan_Number=?");
preparedStatement.setString(1,accountDTOInterface.getPANNumber());
ResultSet resultSet=preparedStatement.executeQuery();
boolean exists=resultSet.next();
resultSet.close();
preparedStatement.close();
if(exists)
{
connection.close();
throw new DAOException("PAN number"+accountDTOInterface.getPANNumber()+" already exist");
}
preparedStatement=connection.prepareStatement("insert into account(first_Name,last_Name,gender,pan_Number)values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,accountDTOInterface.getFirstName());                                    
preparedStatement.setString(2,accountDTOInterface.getLastName());
preparedStatement.setString(3,accountDTOInterface.getGender());
preparedStatement.setString(4,accountDTOInterface.getPANNumber());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
if(resultSet.next())
{
accountDTOInterface.setAccountNumber(resultSet.getInt(1));
}
resultSet.close();
preparedStatement.close();
connection.close();
System.out.println("Account added");
}
catch(SQLException sqle)
{
throw new DAOException("Unable to add Account Number: "+sqle.getMessage());
}
}
//--------------------------------------------------------------------------------------------------------------------------
public void update(AccountDTOInterface accountDTOInterface)throws DAOException
{
try
{
ResultSet resultSet;
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select 1 as result from  account where pan_Number=? and account_number<>?");
preparedStatement.setString(1,accountDTOInterface.getPANNumber());
preparedStatement.setInt(2,accountDTOInterface.getAccountNumber());
resultSet=preparedStatement.executeQuery();
boolean exists=resultSet.next();
resultSet.close();
preparedStatement.close();
if(exists)
{
connection.close();
throw new DAOException("PAN Number : "+accountDTOInterface.getPANNumber()+" alredy exist");
}
preparedStatement=connection.prepareStatement("update account set first_name=?,last_name=?,gender=?,pan_number=? where account_number<>?");
preparedStatement.setString(1,accountDTOInterface.getFirstName());
preparedStatement.setString(2,accountDTOInterface.getLastName());
preparedStatement.setString(3,accountDTOInterface.getGender());
preparedStatement.setString(4,accountDTOInterface.getPANNumber());
preparedStatement.setInt(5,accountDTOInterface.getAccountNumber());
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}
catch(SQLException sqle)
{
throw new DAOException("Unable to update Account :"+sqle.getMessage());
}
}
//-------------------------------------------------------------------------------------------------------------------
public void delete(int accountNumber)throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select 1 as result from account  where account_number<>?");
preparedStatement.setInt(1,accountNumber);
ResultSet resultSet=preparedStatement.executeQuery();
boolean exists=resultSet.next();
resultSet.close();
preparedStatement.close();
if(exists==false)
{
connection.close();
throw new DAOException("Account number : "+accountNumber+" does not exist");
}
preparedStatement=connection.prepareStatement(" delete from account where account_number<>?");
preparedStatement.setInt(1,accountNumber);
preparedStatement.executeUpdate();
resultSet.close();
preparedStatement.close();
connection.close();
}
catch(SQLException sqle)
{
throw new DAOException("Unable to delete Account Number : "+sqle.getMessage());
}
}

//--------------------------------------------------------------------------------------------------------------------

public AccountDTOInterface getByAccountNumber(int accountNumber)throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from account where account_number<>?");
preparedStatement.setInt(1,accountNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
preparedStatement.close();
resultSet.close();
connection.close();
throw new DAOException("Account number: "+accountNumber+"does not  exist");
}
AccountDTOInterface accountDTOInterface=new AccountDTO();
accountDTOInterface.setAccountNumber(resultSet.getInt("account_number"));
accountDTOInterface.setFirstName(resultSet.getString("first_name"));
accountDTOInterface.setLastName(resultSet.getString("last_name"));
accountDTOInterface.setGender(resultSet.getString("gender"));
accountDTOInterface.setPANNumber(resultSet.getString("pan_number"));
resultSet.close();
preparedStatement.close();
connection.close();
return accountDTOInterface;
}//try end
catch(SQLException sqle)
{
throw new DAOException("Unable to fetch Account by Account Number "+ sqle.getMessage());
}
}
//------------------------------------------------------------------------------------------------------------------
public AccountDTOInterface getByPANNumber(String panNumber)throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from account where pan_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
preparedStatement.close();
resultSet.close();
connection.close();
throw new DAOException("PAN number: "+panNumber+"does not  exist");
}
AccountDTOInterface accountDTOInterface=new AccountDTO();
accountDTOInterface.setFirstName(resultSet.getString("first_name"));
accountDTOInterface.setLastName(resultSet.getString("last_name"));
accountDTOInterface.setGender(resultSet.getString("gender"));
accountDTOInterface.setPANNumber(resultSet.getString("pan_number"));
accountDTOInterface.setAccountNumber(resultSet.getInt("account_number"));
resultSet.close();
preparedStatement.close();
connection.close();
return accountDTOInterface;
}//try end
catch(SQLException sqle)
{
throw new DAOException("Unable to fetch Account by PAN Number "+sqle.getMessage());
}
}
//------------------------------------------------------------------------------------------------------------------
public List<AccountDTOInterface>getAll()throws DAOException
{
AccountDTOInterface accountDTOInterface=null;
List<AccountDTOInterface>accounts=null;
try
{
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select * from account order by first_name");
if(resultSet.next()==false)
{
resultSet.close();
statement.close();
connection.close();
throw new DAOException("No Accounts");
}
accounts=new LinkedList<AccountDTOInterface>();
accountDTOInterface=new AccountDTO();

do
{
accountDTOInterface.setFirstName(resultSet.getString("first_name"));
accountDTOInterface.setLastName(resultSet.getString("last_name"));
accountDTOInterface.setGender(resultSet.getString("gender"));
accountDTOInterface.setPANNumber(resultSet.getString("pan_number"));
accountDTOInterface.setAccountNumber(resultSet.getInt("account_number"));
accounts.add(accountDTOInterface);
}while(resultSet.next());
return accounts;
}
catch(SQLException sqle)
{
throw new DAOException("Unable to fetch account Numbers "+sqle.getMessage());
}
}

//------------------------------------------------------------------------------------------------------------------
public int getCount()throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select count(*) as cnt from account");
resultSet.next();
int count=resultSet.getInt("cnt");
resultSet.close();
statement.close();
connection.close();
return count;
}
catch(SQLException sqle)
{
throw new DAOException("Unable to fetch records : "+sqle.getMessage());
}
}
//------------------------------------------------------------------------------------------------------------------
}//DAO end