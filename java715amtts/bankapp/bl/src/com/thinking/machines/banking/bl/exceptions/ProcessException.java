package com.thinking.machines.banking.bl.exceptions;
import java.io.*;
public  class ProcessException  extends Exception implements Serializable
{
public  ProcessException(String message)
{
super(message);
}
}
