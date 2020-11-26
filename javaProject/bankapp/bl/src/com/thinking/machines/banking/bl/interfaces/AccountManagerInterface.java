package com.thinking.machines.banking.bl.interfaces;
import com.thinking.machines.banking.bl.exceptions.*;
import java.util.*;
public interface AccountManagerInterface
{
public void addAccount(AccountInterface accountInterface)throws ProcessException,ValidationException;
public void updateAccount(AccountInterface accountInterface)throws ProcessException,ValidationException;
public void deleteAccount(int accountNumber)throws ProcessException,ValidationException;
public AccountInterface getByAccountNumber(int accountNumber)throws ProcessException,ValidationException;
public AccountInterface getByPANNumber(String panNumber)throws ProcessException,ValidationException;
public List<AccountInterface>getAll()throws ProcessException,ValidationException;
public int getCount()throws ProcessException,ValidationException;
public void addEvent(AccountInterface account)throws ProcessException,ValidationException;
}

