package com.thinking.machines.banking.pl.model;
import com.thinking.machines.banking.pl.model.exceptions.*;
import javax.swing.table.*; 
import java.util.*; 
import com.thinking.machines.banking.bl.*; 
import com.thinking.machines.banking.bl.events.*; 
import com.thinking.machines.banking.bl.exceptions.*; 
import com.thinking.machines.banking.bl.pojo.*; 
import com.thinking.machines.banking.bl.interfaces.*; 
public class AccountModel extends AbstractTableModel implements AccountListener
{
private AccountManager accountManager;
private List<AccountInterface> accounts;
private String title[]={"S.No.","Account No.","Name    "};
public AccountModel() throws ProcessException
{
this.accountManager=new AccountManager();
this.populateDataStructure();
this.addListeners();
}
private void populateDataStructure()
{
try
{
accounts=accountManager.getAll();
}
catch(ProcessException processException)
{
accounts=new LinkedList<>();
}
}
public int getColumnCount()
{
return this.title.length;
}
public int getRowCount()
{
return this.accounts.size();
}
public String getColumnName(int columnIndex)
{
return title[columnIndex];
}
public Class getColumnClass(int columnIndex)
{
if(columnIndex==0||columnIndex==1)
{
return Integer.class;
}
return String.class;
}

public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0)return rowIndex+1;
if(columnIndex==1)return accounts.get(rowIndex).getAccountNumber();
AccountInterface account=accounts.get(rowIndex);
String name=account.getFirstName()+" "+account.getLastName();
return name;
}
public boolean isCellEditable(int rowIndex,int CoumnIndex)
{
return false;
}
//next methode is not required if none of the cell is editable
/*
public void setValueAt(Object data,int rowIndex,int columnIndex)
{
}
*/

private void addListeners()
{
this.accountManager.addAccountListener(this);
}



public void addAccount(AccountInterface account)throws ModelException
{
try
{
accountManager.addAccount(account);

AccountEvent ev=new AccountEvent();
ev.setNewAccount(account);
this.accountAdded(ev);
}
catch(ValidationException validationException)
{
throw new ModelException(validationException.getMessage());
}
catch(ProcessException processException)
{
throw new ModelException(processException.getMessage());
}
}
public void updateAccount(AccountInterface account)throws ModelException
{
try
{
accountManager.updateAccount(account);
AccountEvent ev=new AccountEvent();
ev.setNewAccount(account);
this.accountUpdated(ev);
}
catch(ValidationException validationException)
{
throw new ModelException(validationException.getMessage());
}
catch(ProcessException processException)
{
throw new ModelException(processException.getMessage());
}
}
public void deleteAccount(int account)throws ModelException
{
try
{
accountManager.deleteAccount(account);

AccountInterface acInterface=null;
int found=0;
for(AccountInterface accountInterface:accounts)
{
if(account==accountInterface.getAccountNumber())
{
found=1;
acInterface=accountInterface;
break;
}
}

AccountEvent ev=new AccountEvent();
ev.setNewAccount(acInterface);
this.accountDeleted(ev);

}
catch(ValidationException validationException)
{
throw new ModelException(validationException.getMessage());
}
catch(ProcessException processException)
{
throw new ModelException(processException.getMessage());
}
}
public void accountAdded(AccountEvent ev)
{
 this.fireTableDataChanged();

}
public void accountUpdated(AccountEvent ev)
{
 this.fireTableDataChanged();
this.fireTableCellUpdated(1, 0);
}
public void accountDeleted(AccountEvent ev)
{
 this.fireTableDataChanged();
}
}
