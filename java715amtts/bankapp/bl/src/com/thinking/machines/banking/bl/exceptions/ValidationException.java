package com.thinking.machines.banking.bl.exceptions;
import java.util.*;
public class ValidationException extends Exception implements java.io.Serializable
{



private HashMap<String,String>Exceptions=new HashMap<String,String>();
//private ValidationException exception=new ValidationException();
private HashMap<String,String>exceptions=new HashMap<String,String>();




public void addException(String key,String value)
{
exceptions.put(key,value);
}


public List<String>getException()
{
return new LinkedList(exceptions.keySet());
}
public boolean hasException()
{
return exceptions.size()>0;
}
public String getByValue(String value)
{
return exceptions.get(value);
}
public String getByKey(String key)
{
return exceptions.get(key);
}
public int getExceptionsCount()
{
return exceptions.size();
}

}
