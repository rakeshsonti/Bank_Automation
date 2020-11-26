package com.thinking.machines.banking.dl;
import java.sql.*;
import com.thinking.machines.banking.dl.exceptions.*;
class DAOConnection 
{
DAOConnection()
{
}
public  static Connection getConnection() throws DAOException
{
Connection c=null;
try
{
Class.forName("org.apache.derby.jdbc.ClientDriver");
c=DriverManager.getConnection("jdbc:derby://localhost:1527/bankingdb");
}
catch(SQLException sqle)
{
throw new DAOException(sqle.getMessage());
}
catch(ClassNotFoundException cnfe)
{
throw new DAOException (cnfe.getMessage());
}
return c;
}
}