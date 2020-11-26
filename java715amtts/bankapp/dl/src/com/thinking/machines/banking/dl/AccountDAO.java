package com.thinking.machines.banking.dl;
import com.thinking.machines.seq.*; 
import com.thinking.machines.seq.interfaces.*; 
import com.thinking.machines.seq.exceptions.*; 
import com.thinking.machines.banking.dl.exceptions.*; 
import com.thinking.machines.banking.dl.interfaces.*; 
import java.util.*; 
import java.io.*; 
public class AccountDAO implements AccountDAOInterface 
{ 
private static final String dataFile="accounts.data"; 
//-------------------------------------------------------------------------------------------
public void add(AccountDTOInterface accountDTOInterface)throws DAOException 
{ 
try 
{ 
int vAccountNumber; 
String vFirstName; 
String vLastName; 
String vGender; 
String vPANNumber; 
File file=new File(dataFile); 
if(file.exists()) 
{ 
RandomAccessFile randomAccessFile; 
randomAccessFile=new RandomAccessFile(file,"rw"); 
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{ 
vAccountNumber=Integer.parseInt(randomAccessFile.readLine()); 
vFirstName=randomAccessFile.readLine(); 
vLastName=randomAccessFile.readLine(); 
vGender=randomAccessFile.readLine(); 
vPANNumber=randomAccessFile.readLine(); 
if(vPANNumber.equalsIgnoreCase(accountDTOInterface.getPANNumber())) 
{ 
randomAccessFile.close(); 
throw new DAOException("PAN Number : "+vPANNumber+" exists."); 
} 
} 
randomAccessFile.close(); 
} 
SequenceGenerator sequenceGenerator=SequenceGenerator.getInstance(); 
try 
{ 
vAccountNumber=sequenceGenerator.getNext("account"); 
}
catch(SequenceException sequenceException) 
{
 sequenceException.printStackTrace(); 
// remove after testing 
throw new DAOException("Unable to generate account number, contact administrator"); 
} 
RandomAccessFile randomAccessFile; 
randomAccessFile=new RandomAccessFile(file,"rw"); 
randomAccessFile.seek(randomAccessFile.length()); 
randomAccessFile.writeBytes(String.valueOf(vAccountNumber)); 
randomAccessFile.writeBytes("\n"); 
randomAccessFile.writeBytes(accountDTOInterface.getFirstName()); 
randomAccessFile.writeBytes("\n"); 
randomAccessFile.writeBytes(accountDTOInterface.getLastName()); 
randomAccessFile.writeBytes("\n"); 
randomAccessFile.writeBytes(accountDTOInterface.getGender()); 
randomAccessFile.writeBytes("\n"); 
randomAccessFile.writeBytes(accountDTOInterface.getPANNumber()); 
randomAccessFile.writeBytes("\n"); 
randomAccessFile.close(); 
accountDTOInterface.setAccountNumber(vAccountNumber); 
}
catch(IOException ioException) 
{ 
//ioException.printStackTrace(); 
throw new DAOException(ioException.getMessage()); 
// remove after testing 
} 
}
//------------------------------------------------------------------------------------------- 
public void update(AccountDTOInterface accountDTOInterface)throws DAOException
{
try
{
File file=new File(dataFile);
int iAccountNumber;
int vAccountNumber;
String vPANNumber;
String vFirstName;
String vLastName;
String vGender;
iAccountNumber=accountDTOInterface.getAccountNumber();
if(file.exists()==false)
{
throw new DAOException("Account number not exists");
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Account Number :"+iAccountNumber);
}
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine());
vFirstName=randomAccessFile.readLine();
vLastName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
if(iAccountNumber==vAccountNumber)
{
found=true;
break;
}
}
if(!found)
{
randomAccessFile.close();
throw new DAOException("Invalid Account Number: "+iAccountNumber);
}
String iPANNumber=accountDTOInterface.getPANNumber();
randomAccessFile.seek(0);
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine());
vFirstName=randomAccessFile.readLine();
vLastName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
if(vPANNumber.equalsIgnoreCase(iPANNumber)&&vAccountNumber!=iAccountNumber)
{
randomAccessFile.close();
throw new DAOException("PAN number exist");
}
}
randomAccessFile.seek(0);
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists())tmpFile.delete();
RandomAccessFile tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine());
vFirstName=randomAccessFile.readLine();
vLastName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
if(vAccountNumber!=iAccountNumber)
{
tmpRandomAccessFile.writeBytes(vAccountNumber+"\n");
tmpRandomAccessFile.writeBytes(vFirstName+"\n");
tmpRandomAccessFile.writeBytes(vLastName+"\n");
tmpRandomAccessFile.writeBytes(vGender+"\n");
tmpRandomAccessFile.writeBytes(vPANNumber+"\n");
}
else
{
tmpRandomAccessFile.writeBytes(accountDTOInterface.getAccountNumber()+"\n");
tmpRandomAccessFile.writeBytes(accountDTOInterface.getFirstName()+"\n");
tmpRandomAccessFile.writeBytes(accountDTOInterface.getLastName()+"\n");
tmpRandomAccessFile.writeBytes(accountDTOInterface.getGender()+"\n");
tmpRandomAccessFile.writeBytes(accountDTOInterface.getPANNumber()+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(randomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
randomAccessFile.close();
}catch(IOException ioException)
{
ioException.printStackTrace();
throw new DAOException(ioException.getMessage());
}
}
//---------------------------------------------------------------------------------------------------------------
public void delete(int accountNumber)throws DAOException 
{ 
try
{
File file=new File(dataFile);
int vAccountNumber;
String vPANNumber;
String vFirstName;
String vLastName;
String vGender;
if(file.exists()==false)
{
throw new DAOException("Account number not exists"+accountNumber);
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Account Number"+accountNumber);
}
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine());
vFirstName=randomAccessFile.readLine();
vLastName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
if(accountNumber==vAccountNumber)
{
found=true;
break;
}
}
if(!found)
{
randomAccessFile.close();
throw new DAOException("Invalid Account Number"+accountNumber);
}
randomAccessFile.seek(0); 
File tmpFile=new File("tmp.tmp"); 
if(tmpFile.exists()) tmpFile.delete();
 RandomAccessFile tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw"); 
while(randomAccessFile.getFilePointer()<randomAccessFile.length()) 
{ 
vAccountNumber=Integer.parseInt(randomAccessFile.readLine());
vFirstName=randomAccessFile.readLine(); 
vLastName=randomAccessFile.readLine(); 
vGender=randomAccessFile.readLine(); 
vPANNumber=randomAccessFile.readLine(); 
if(vAccountNumber!=accountNumber) 
{ 
tmpRandomAccessFile.writeBytes(vAccountNumber+"\n"); 
tmpRandomAccessFile.writeBytes(vFirstName+"\n"); 
tmpRandomAccessFile.writeBytes(vLastName+"\n"); 
tmpRandomAccessFile.writeBytes(vGender+"\n"); 
tmpRandomAccessFile.writeBytes(vPANNumber+"\n"); 
} 
} 
randomAccessFile.seek(0); 
tmpRandomAccessFile.seek(0); 
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length()) 
{ 
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n"); 
} 
randomAccessFile.setLength(tmpRandomAccessFile.length()); 
tmpRandomAccessFile.setLength(0); 
randomAccessFile.close(); 
tmpRandomAccessFile.close(); 
}
catch(IOException ioException) 
{ 
ioException.printStackTrace(); 
throw new DAOException(ioException.getMessage()); 
} 
}
//--------------------------------------------------------------------------------------------------------------
public AccountDTOInterface getByAccountNumber(int accountNumber)throws DAOException 
{ 
try
{
 File file=new File(dataFile);
if(file.exists()==false)
{
throw new DAOException("Invalid account number"+accountNumber);
}
int vAccountNumber;
String vPANNumber;
String vGender;
String vFirstName;
String vLastName;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid account number"+accountNumber);
}
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine());
vFirstName=randomAccessFile.readLine();
vLastName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
if(accountNumber==vAccountNumber)
{
AccountDTOInterface accountDTOInterface=new AccountDTO();
accountDTOInterface.setAccountNumber(vAccountNumber);
accountDTOInterface.setFirstName(vFirstName);
accountDTOInterface.setLastName(vLastName);
accountDTOInterface.setGender(vGender);
accountDTOInterface.setPANNumber(vPANNumber);
randomAccessFile.close();
return accountDTOInterface;
}
}
randomAccessFile.close();
throw new DAOException("Invalid account Number"+accountNumber);
}catch(IOException ioException)
{
ioException.printStackTrace();
throw new DAOException(ioException.getMessage());
}
}
//----------------------------------------------------------------------------------------------------
public AccountDTOInterface getByPANNumber(String panNumber)throws DAOException 
{ 
try
{
 File file=new File(dataFile);
if(file.exists()==false)
{
throw new DAOException("Invalid PAN number (File not exist)"+panNumber);
}
int vAccountNumber;
String vPANNumber;
String vGender;
String vFirstName;
String vLastName;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid PAN number (length 0)"+panNumber);
}
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine());
vFirstName=randomAccessFile.readLine();
vLastName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
if(vPANNumber.equalsIgnoreCase(panNumber))
{
AccountDTOInterface accountDTOInterface=new AccountDTO();
accountDTOInterface.setAccountNumber(vAccountNumber);
accountDTOInterface.setFirstName(vFirstName);
accountDTOInterface.setLastName(vLastName);
accountDTOInterface.setGender(vGender);
accountDTOInterface.setPANNumber(vPANNumber);
randomAccessFile.close();
return accountDTOInterface;
}
}
randomAccessFile.close();
throw new DAOException("Invalid PAN Number"+panNumber);
}catch(IOException ioException)
{
ioException.printStackTrace();
throw new DAOException(ioException.getMessage());
}
}
//----------------------------------------------------------------------
public List<AccountDTOInterface> getAll() throws DAOException
{
try
{
File file=new File(dataFile);

if(file.exists()==false)
{
//throw new DAOException("No Accounts ");

}

List<AccountDTOInterface>accounts=null;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");

if(randomAccessFile.length()==0)
{
//randomAccessFile.close();
//throw new DAOException("No Accounts ");
}

accounts=new LinkedList<AccountDTOInterface>();
AccountDTOInterface accountDTOInterface;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
accountDTOInterface =new AccountDTO();
accountDTOInterface.setAccountNumber(Integer.parseInt(randomAccessFile.readLine()));
accountDTOInterface.setFirstName(randomAccessFile.readLine());
accountDTOInterface.setLastName(randomAccessFile.readLine());
accountDTOInterface.setGender(randomAccessFile.readLine());
accountDTOInterface.setPANNumber(randomAccessFile.readLine());
accounts.add(accountDTOInterface);
}
randomAccessFile.close();
return accounts;
}catch(IOException ioException)
{
ioException.printStackTrace();
throw new DAOException(ioException.getMessage());
//Remove after testing
}
}
//-------------------------------------------------------------------------------------------------------------
public int getCount()throws DAOException 
{ 
try
{
File file=new File(dataFile);
if(file.exists()==false)
{
return 0;
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
int count=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
count++;
}
randomAccessFile.close();
return count=count/5;
}catch(IOException ioException)
{
ioException.printStackTrace();
throw new DAOException(ioException.getMessage());
}
}
}
//-----------------------------Account DAO finish------------------  