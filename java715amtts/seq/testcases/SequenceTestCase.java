import com.thinking.machines.seq.*; 
import com.thinking.machines.seq.interfaces.*;
import com.thinking.machines.seq.exceptions.*; 
  public class SequenceTestCase 
{ 
public static void main(String data[]) 
{ 
String entity=data[0]; 
SequenceGenerator sequenceGenerator=SequenceGenerator.getInstance(); 
try 
{ 
int code=sequenceGenerator.getNext(entity);
System.out.printf("Next sequence for %s is %d",entity,code); 
}
catch(SequenceException sequenceException) 
{ 
System.out.println(sequenceException); 
} 
} 
}