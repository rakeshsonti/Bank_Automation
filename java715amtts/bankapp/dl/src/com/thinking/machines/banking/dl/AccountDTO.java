package com.thinking.machines.banking.dl;
import com.thinking.machines.banking.dl.interfaces.*;
public class AccountDTO implements AccountDTOInterface
{
private int accountNumber;
private String firstName;
private String lastName;
private String gender;
private String panNumber;
public AccountDTO()
{
this.accountNumber=0;
this.firstName="";
this.lastName="";
this.gender="";
this.panNumber="";
}
public void setAccountNumber(int accountNumber)
{
this.accountNumber=accountNumber;
}
public int getAccountNumber()
{
return accountNumber;
}
public void setFirstName(String firstName)
{
this.firstName=firstName;
}
public String getFirstName()
{
return firstName;
}
public void setLastName(String lastName)
{
this.lastName=lastName;
}
public String gatLastName()
{
return lastName;
}
public String getLastName()
{
return lastName;
}
public void setGender(String gender)
{
this.gender=gender;
}
public String getGender()
{
return gender;
}
public void setPANNumber(String panNumber)
{
this.panNumber=panNumber;
}
public String getPANNumber()
{
return panNumber;
}
public boolean equals(Object object)
{
if(object==null)return false;
if(!(object instanceof AccountDTOInterface))return false;
AccountDTOInterface other=(AccountDTOInterface)object;
return this.accountNumber==other.getAccountNumber();
}
public int compareTo(AccountDTOInterface other)
{
return this.accountNumber-other.getAccountNumber();
}
public int hashCode()
{
return this.accountNumber;
}
}