package com.thinking.machines.seq;
import com.thinking.machines.seq.exceptions.*;
import com.thinking.machines.seq.interfaces.*;
import java.util.*;
import java.io.*;
public class SequenceGenerator implements SequenceGeneratorInterface
{
private static SequenceGenerator sequenceGenerator=null;
private SequenceGenerator()
{
}
public static SequenceGenerator getInstance()
{
if(sequenceGenerator==null)sequenceGenerator=new SequenceGenerator();

return sequenceGenerator;

}
public int getNext(String entity)throws SequenceException
{
try
{
int newNumber=1;
entity=entity.toUpperCase();
String fileName="sequence.seq";
File file=new File(fileName);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
boolean found=false;
LinkedList<String>list=new LinkedList<String>();

String line;
String pc1,pc2;
int commaPosition;
int lastNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
line=randomAccessFile.readLine();
commaPosition=line.indexOf(",");
pc1=line.substring(0,commaPosition);
pc2=line.substring(commaPosition+1);
if(pc1.equals(entity))
{
lastNumber=Integer.parseInt(pc2);
newNumber=lastNumber+1;
list.add(pc1+","+newNumber);
found=true;
}
else
{
list.add(line);
}
}
if(found==false)
{
list.add(entity+",1");
}
randomAccessFile.seek(0);
for(String g:list)
{
randomAccessFile.writeBytes(g+"\n");
}
randomAccessFile.close();
return newNumber;
}
catch(IOException ioException)
{
throw new SequenceException("Unable to generate sequence for:"+entity+",reason:"+ioException.getMessage());
}
}
}

