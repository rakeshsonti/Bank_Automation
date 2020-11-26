package com.thinking.machines.banking.bl;
import com.thinking.machines.banking.bl.interfaces.*;
import com.thinking.machines.banking.bl.exceptions.*;
import com.thinking.machines.banking.bl.pojo.*;
import com.thinking.machines.banking.bl.events.*;
import com.thinking.machines.banking.dl.*;
import com.thinking.machines.banking.dl.interfaces.*;
import com.thinking.machines.banking.dl.exceptions.*;
import java.util.*;
public class AccountManager implements AccountManagerInterface
{
//Data Structure--------------------------------------------------------------------
private Map<String,AccountInterface>panNumberDictionary;
private Map<Integer,AccountInterface>accountNumberDictionary;
private List<AccountInterface>accountsList;
private List<AccountListener>accountListeners;
public AccountManager()throws ProcessException
{
populateDataStructure();
}
private void populateDataStructure()throws ProcessException
{
accountListeners= new LinkedList<AccountListener>();
panNumberDictionary =new HashMap<String,AccountInterface>();
accountNumberDictionary=new HashMap<Integer,AccountInterface>();
accountsList=new LinkedList<AccountInterface>();
AccountDAOInterface accountDAO = new AccountDAO();
List<AccountDTOInterface> daoAccountsList;
AccountInterface account;
int vAccountNumber;
String vFirstName;
String vLastName;
String vGender;
String vPANNumber;
try
{
daoAccountsList=accountDAO.getAll();
for(AccountDTOInterface dlAccount:daoAccountsList)
{
vAccountNumber=dlAccount.getAccountNumber();
vFirstName=dlAccount.getFirstName();
vLastName=dlAccount.getLastName();
vGender=dlAccount.getGender();
vPANNumber=dlAccount.getPANNumber();
account=new Account();
account.setAccountNumber(vAccountNumber);
account.setFirstName(vFirstName);
account.setLastName(vLastName);
account.setGender(vGender);
account.setPANNumber(vPANNumber);
accountsList.add(account);
panNumberDictionary.put(vPANNumber.toUpperCase(),account);
accountNumberDictionary.put(vAccountNumber,account);
}
Collections.sort(accountsList,new Comparator<AccountInterface>(){
public int compare(AccountInterface left,AccountInterface right)
{
String leftName=left.getFirstName()+" "+left.getLastName();
leftName=leftName.toUpperCase();
String rightName=right.getFirstName()+" "+right.getLastName();
rightName=rightName.toUpperCase();
return leftName.compareTo(rightName);
}
});
}catch(DAOException daoException)
{
throw new ProcessException(daoException.getMessage());
}
}
//------------------------------------------------------------------------------------------------------------------------------
public void addAccount(AccountInterface account) throws ProcessException,ValidationException
{
int vAccountNumber;
String vFirstName;
String vLastName;
String vGender;
String vPANNumber;
vFirstName=account.getFirstName();
vLastName=account.getLastName();
vGender=account.getGender().toUpperCase();
vPANNumber=account.getPANNumber();
ValidationException validationException=new ValidationException();

if(vFirstName==null||vFirstName.trim().length()==0)
{
validationException.addException("First Name"," require");
}
else if(vFirstName.trim().length()>20)
{
validationException.addException("FirstName",  "Can not exceed 20 characters");
}
if(vLastName==null|| vLastName.trim().length()==0)
{
validationException.addException("Last Name"," require");
}
else if(vLastName.trim().length()>20)
{
validationException.addException("LastName",  "Can not exceed 20 characters");
}
if(vGender==null||(vGender.trim().equals("M")==false&&vGender.trim().equals("F")==false))
{
if(vGender==null)validationException.addException("Gender ","Required");
else validationException.addException("Gender","Required (M/F)");
}
if(vPANNumber==null|| vPANNumber.trim().length()==0)
{
validationException.addException("PAN Number", "Required");
}
else if(vPANNumber.trim().length()>10)
{
validationException.addException("PAN Number ","Can not exceed 10 Characters");
}
if(validationException.hasException())throw validationException;
if(panNumberDictionary.containsKey(vPANNumber.toUpperCase().trim()))
{
validationException.addException("PAN Number","Exist");
throw validationException;
}
try
{
AccountDTOInterface accountDTO=new  AccountDTO();
accountDTO.setFirstName(vFirstName.trim());
accountDTO.setLastName(vLastName.trim());
accountDTO.setGender(vGender.trim());
accountDTO.setPANNumber(vPANNumber.trim());
//new AccountDAO().add(accountDTO);
AccountDAOInterface accountDAO=new AccountDAO();
accountDAO.add(accountDTO);
vAccountNumber=accountDTO.getAccountNumber();
account.setAccountNumber(vAccountNumber);
AccountInterface dsAccount=getCopy(account);
panNumberDictionary.put(vPANNumber.toUpperCase(),dsAccount);
accountNumberDictionary.put(new Integer(vAccountNumber),dsAccount);


if(accountsList.size()==0)
{
accountsList.add(dsAccount);
}
else
{
Comparator<AccountInterface>comparator=new Comparator<AccountInterface>()
{
public int compare(AccountInterface left,AccountInterface right)
{
String leftName=left.getFirstName().toUpperCase()+" "+left.getLastName().toUpperCase();
String rightName=right.getFirstName().toUpperCase()+" "+right.getLastName().toUpperCase();
return leftName.compareTo(rightName);
}
};

AccountInterface tmp=accountsList.get(0);
if(comparator.compare(dsAccount,tmp)<=0)
{
accountsList.add(0,dsAccount);
}
else
{
tmp=accountsList.get(accountsList.size()-1);



if(comparator.compare(dsAccount,tmp)>=0)
{
accountsList.add(dsAccount);
}
else
{

int lb,ub,mid;
lb=0;
ub=accountsList.size()-1;
int x;
while(lb<=ub)
{
mid=(int)((lb+ub)/2);
x=comparator.compare(accountsList.get(mid),dsAccount);
if(x==0)
{
accountsList.add(mid,dsAccount);
break;
}
else
if(x<0)
{
lb=mid+1;
}else if(x>0)
{
if(comparator.compare(accountsList.get(mid-1),dsAccount)<=0)
{
accountsList.add(mid,dsAccount);
}
else
{
ub=mid-1;
}
}
}
}
}
}

AccountEvent ev=new AccountEvent();
ev.setNewAccount(getCopy(dsAccount));
for(AccountListener accountListener:accountListeners)
{
accountListener.accountAdded(ev);
}
}
catch(DAOException daoException)
{
throw new ProcessException(daoException.getMessage());
}
}
//--------------------------------------------------------------------------------------------------------------------

public void updateAccount(AccountInterface account) throws ProcessException,ValidationException 
{ 
int vAccountNumber;
String vFirstName;
String vLastName;
String vGender;
String vPANNumber;
vFirstName=account.getFirstName();
vLastName=account.getLastName();
vGender=account.getGender();
vPANNumber=account.getPANNumber();
vAccountNumber=account.getAccountNumber();
ValidationException validationException=new ValidationException();
if(vFirstName==null||vFirstName.trim().length()==0)
{
validationException.addException("First Name"," require");
}
else if(vFirstName.trim().length()>20)
{
validationException.addException("FirstName",  "Can not exceed 20 characters");
}
if(vLastName==null|| vLastName.trim().length()==0)
{
validationException.addException("Last Name"," require");
}
else if(vLastName.trim().length()>20)
{
validationException.addException("LastName",  "Can not exceed 20 characters");
}
if(vGender==null||(vGender.trim().equals("M")==false&&vGender.trim().equals("F")==false))
{
if(vGender==null)validationException.addException("Gender ","Required");
else validationException.addException("Gender","Required (M/F)");
}
if(vPANNumber==null|| vPANNumber.trim().length()==0)
{
validationException.addException("PAN Number", "Required");
}
else if(vPANNumber.trim().length()>10)
{
validationException.addException("PAN Number ","Can not exceed 10 Characters");
}
if(validationException.hasException())throw validationException;
for(AccountInterface accountInterface:accountsList)
{
if(accountInterface.getPANNumber().compareTo(account.getPANNumber())==0&&accountInterface.getAccountNumber()!=account.getAccountNumber())
{
throw  new ProcessException("PAN Number already Alloted to another number "+accountInterface.getPANNumber());
}
}
try
{
if(accountNumberDictionary.containsKey(vAccountNumber))
{
AccountDTOInterface accountDTO=new  AccountDTO();
accountDTO.setFirstName(vFirstName.trim());
accountDTO.setLastName(vLastName.trim());
accountDTO.setGender(vGender.trim());
accountDTO.setPANNumber(vPANNumber.trim());
accountDTO.setAccountNumber(vAccountNumber);
AccountDAOInterface accountDAO=new AccountDAO();
accountDAO.update(accountDTO);
account.setAccountNumber(vAccountNumber);
AccountInterface dsAccount=getCopy(account);
panNumberDictionary.put(vPANNumber.toUpperCase(),dsAccount);
accountNumberDictionary.put(new Integer(vAccountNumber),dsAccount);
if(accountsList.size()==0)
{
accountsList.add(dsAccount);
}
else
{
Comparator<AccountInterface>comparator=new Comparator<AccountInterface>()
{
public int compare(AccountInterface left,AccountInterface right)
{
String leftName=left.getFirstName().toUpperCase()+" "+left.getLastName().toUpperCase();
String rightName=right.getFirstName().toUpperCase()+" "+right.getLastName().toUpperCase();
return leftName.compareTo(rightName);
}
};

AccountInterface tmp=accountsList.get(0);
if(comparator.compare(dsAccount,tmp)<=0)
{
accountsList.add(0,dsAccount);
}
else
{
tmp=accountsList.get(accountsList.size()-1);



if(comparator.compare(dsAccount,tmp)>=0)
{
accountsList.add(dsAccount);
}
else
{

int lb,ub,mid;
lb=0;
ub=accountsList.size()-1;
int x;
while(lb<=ub)
{
mid=(int)((lb+ub)/2);
x=comparator.compare(accountsList.get(mid),dsAccount);
if(x==0)
{
accountsList.add(mid,dsAccount);
break;
}
else
if(x<0)
{
lb=mid+1;
}else if(x>0)
{
if(comparator.compare(accountsList.get(mid-1),dsAccount)<=0)
{
accountsList.add(mid,dsAccount);
}
else
{
ub=mid-1;
}
}
}
}
}
}
AccountEvent ev=new AccountEvent();
ev.setNewAccount(getCopy(dsAccount));
for(AccountListener accountListener:accountListeners)
{
accountListener.accountUpdated(ev);
}
}//if end........
}
catch(DAOException daoException)
{
throw new ProcessException(daoException.getMessage());
}
} 

//--------------------------------------------------------------------------------------------------------------------
public void deleteAccount(int accountNumber) throws ProcessException,ValidationException 
{
AccountInterface acInterface=null;
int found=0;
for(AccountInterface accountInterface:accountsList)
{
if(accountNumber==accountInterface.getAccountNumber())
{
found=1;
acInterface=accountInterface;
break;
}
}
if(found==0)
{
throw new ProcessException("Account Number does not Exist");
}
try
{
AccountDAOInterface accountDAO=new AccountDAO();
accountDAO.delete(accountNumber);
AccountEvent ev=new AccountEvent();
ev.setOldAccount(getCopy(acInterface));
for(AccountListener accountListener:accountListeners)
{
accountListener.accountDeleted(ev);
}
}
catch(DAOException daoException)
{
throw new ProcessException(daoException.getMessage());
}
}
//---------------------------------------------------------------------------------------------------------------

public AccountInterface getByAccountNumber(int vAccountNumber) throws ProcessException,ValidationException 
{
int found=0;
for(AccountInterface account:accountsList)
{
if(vAccountNumber==account.getAccountNumber())
{
found=1;
break;
}
}
if(found==0)
{
throw new ProcessException("Account Number does not exist");
}
try
{
AccountDAOInterface accountDAO=new AccountDAO();
AccountInterface account=new Account();
AccountDTOInterface accountDTO=new AccountDTO();
accountDTO=accountDAO.getByAccountNumber(vAccountNumber);
account.setAccountNumber(accountDTO.getAccountNumber());
account.setPANNumber(accountDTO.getPANNumber().trim());
account.setFirstName(accountDTO.getFirstName().trim());
account.setLastName(accountDTO.getLastName().trim());
account.setGender(accountDTO.getGender().trim());
return account;
}
catch(DAOException daoException)
{
throw new ProcessException(daoException.getMessage());
}
}
//.................................................another approch.....................................
/*
AccountInterface account=null;
try
{
if(accountNumberDictionary.containsKey(vAccountNumber))
{
account=accountNumberDictionary.get(vAccountNumber);
}
}catch(Exception exception)
{
throw new ProcessException(exception.getMessage());
}
return account;
}
*/
//--------------------------------------------------------------------------------------------------------------------
public AccountInterface getByPANNumber(String vPANNumber) throws ProcessException,ValidationException 
{
int found=0;
for(AccountInterface account:accountsList)
{
if(vPANNumber==account.getPANNumber())
{
found=1;
break;
}
}
try
{
AccountInterface account=new Account();
AccountDTOInterface accountDTO=new AccountDTO();
AccountDAOInterface accountDAO=new AccountDAO();
accountDTO=accountDAO.getByPANNumber(vPANNumber);
account.setAccountNumber(accountDTO.getAccountNumber());
account.setPANNumber(accountDTO.getPANNumber().trim());
account.setFirstName(accountDTO.getFirstName().trim());
account.setLastName(accountDTO.getLastName().trim());
account.setGender(accountDTO.getGender().trim());
return account;
}
catch(DAOException daoException)
{
throw new ProcessException(daoException.getMessage());
}
}
//........................................................Another Approch.............................................................
/*
AccountInterface account=null;
try
{
if(panNumberDictionary.containsKey(vPANNumber))
{
account=panNumberDictionary.get(vPANNumber);
}
}catch(Exception exception)
{
throw new ProcessException(exception.getMessage());
}
return account;
}
*/
//--------------------------------------------------------------------------------------------------------------------

public List<AccountInterface> getAll() throws ProcessException 
{ 
List<AccountInterface>all=new LinkedList<AccountInterface>();
AccountInterface allAccount;
for(AccountInterface account:accountsList)
{
allAccount=getCopy(account);
all.add(allAccount);
}
return all;
}
//--------------------------------------------------------------------------------------------------------------------
public int getCount() throws ProcessException 
{ 
return accountsList.size();
} 
//--------------------------------------------------------------------------------------------------------------------
public void addAccountListener(AccountListener accountListener) 
{ 
accountListeners.add(accountListener); 
} 
//--------------------------------------------------------------------------------------------------------------------

public void addEvent(AccountInterface account)throws ProcessException,ValidationException
{ 
throw new ProcessException("Not yet implemented"); 
} 

//--------------------------------------------------------------------------------------------------------------------
private AccountInterface getCopy(AccountInterface account) 
{ 
AccountInterface copyOfAccount=new Account(); 
copyOfAccount.setAccountNumber(account.getAccountNumber()); 
copyOfAccount.setFirstName(account.getFirstName()); 
copyOfAccount.setLastName(account.getLastName()); 
copyOfAccount.setGender(account.getGender()); 
copyOfAccount.setPANNumber(account.getPANNumber()); 
return copyOfAccount; 
} 
}
//-----------------------------------------------Account Manager END!---------------------------------------


