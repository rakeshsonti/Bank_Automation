package com.thinking.machines.banking.bl.pojo;
import  com.thinking.machines.banking.bl.interfaces.*;
public class Account implements AccountInterface
{
private int accountNumber;
private String firstName;
private String lastName;
private String gender;
private String panNumber;
public Account()
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
return this.accountNumber; 
} 
public void setFirstName(String firstName) 
{ 
this.firstName=firstName; 
} 
public String getFirstName() 
{ 
return this.firstName; 
} 
public void setLastName(String lastName) 
{ 
this.lastName=lastName; 
} 
public String getLastName() 
{ 
return this.lastName; 
} 
public void setGender(String gender) 
{ 
this.gender=gender; 
} 
public String getGender() 
{ 
return this.gender; 
} 

public void setPANNumber(String PANNumber) 
{ 
this.panNumber=PANNumber; 
} 
public String getPANNumber() 
{ 
return this.panNumber; 
} 
public boolean equals(Object object) 
{ 
if(object==null)return false; 
if(!(object instanceof AccountInterface))return false; 
AccountInterface other=(AccountInterface)object; 
return this.accountNumber==other.getAccountNumber(); 
} 
public int compareTo(AccountInterface other) 
{ 
return this.accountNumber-other.getAccountNumber(); 
} 
public int hashCode() 
{ 
return this.accountNumber; 
}  
} 