import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.thinking.machines.banking.bl.exceptions.*;
import com.thinking.machines.banking.pl.model.exceptions.*;
import com.thinking.machines.banking.pl.model.*;
import com.thinking.machines.banking.bl.*;
import com.thinking.machines.banking.bl.interfaces.*;
import com.thinking.machines.banking.bl.exceptions.*;

public class eg1 extends JFrame implements DocumentListener,ActionListener
{
private JTable table;
private JScrollPane jsp;
private Object[][] data;  
private JLabel accountLabel,searchLabel,errorLabel,label1,label2,label3,label4;
private JTextField searchTextField;
private JButton clearButton;
JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9,panel10,panel11,panel12,panel13,panel14,panel15,panel16;
private JTextField accountNumberTextField,firstNameTextField,lastNameTextField,genderTextField,panNumberTextField;
private JLabel accountNumberLabel,firstNameLabel,lastNameLabel,genderLabel,panNumberLabel,emptyLabel;
private JLabel  emptyLabel1;
private JButton saveButton,editButton,deleteButton,cancelButton,pdfButton;
private AccountModel accountModel;

eg1()
{
setTitle("Bank Automation");
try
{
accountModel=new AccountModel();
}
catch(ProcessException processException)
{
System.out.println(processException);
}
emptyLabel=new JLabel(" ");
accountLabel=new JLabel("Account");
accountLabel.setForeground(Color.GRAY);
accountLabel.setFont(new Font("Verdana",Font.BOLD,26));

table=new JTable(accountModel);

table.setFont(new Font("Verdana",Font.BOLD,22));
table.setForeground(Color.BLUE);
table.setRowHeight(40);
jsp=new  JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
table.getTableHeader().setFont(new Font("Verdana",Font.BOLD,22));
table.setPreferredScrollableViewportSize(
    new Dimension(table.getPreferredSize().width*4,table.getRowHeight()*4));

Font font1= new Font("Verdana",Font.BOLD,30);
Font font2=new Font("Verdana",Font.PLAIN,23);
panel1=new JPanel();
panel1.add(jsp);
panel2=new JPanel();
panel2.setLayout(new GridLayout(1,5));
accountLabel=new JLabel("Account");
accountLabel.setForeground(Color.GRAY);
accountLabel.setFont(font1);
label1=new JLabel("   ");
label2=new JLabel("           ");
label3=new JLabel("          ");
panel2.add(accountLabel);
panel2.add(label1);
panel2.add(label2);
panel2.add(label3);
panel2.add(label3);
panel4=new JPanel(new FlowLayout());
searchLabel=new JLabel("search");
searchLabel.setForeground(Color.GRAY);
searchLabel.setFont(font1);
searchLabel.setLocation(10,10);
searchTextField=new JTextField(42);
searchTextField.setFont(font2);
clearButton=new JButton("clear");
clearButton.setForeground(Color.RED);
clearButton.setFont(font2);
clearButton.setSize(30,30);
errorLabel=new JLabel(" ");
errorLabel.setFont(font2);
errorLabel.setForeground(Color.RED);
errorLabel.setSize(100,30);
panel15=new JPanel();
panel15.add(errorLabel);
emptyLabel1=new JLabel("       ");
panel4.add(searchLabel);
panel4.add(searchTextField);
panel4.add(clearButton);

panel4.add(emptyLabel1);
panel5=new JPanel(new GridLayout(3,1));
panel5.add(panel2);
panel5.add(panel4);
panel5.add(panel15);
panel6=new JPanel();
Font font6=new Font("Verdana",Font.PLAIN,20);
Font font7=new Font("Verdana",Font.BOLD,25);
accountNumberLabel=new JLabel(" Account No");
accountNumberLabel.setForeground(Color.GRAY);
accountNumberLabel.setFont(font7);
firstNameLabel=new JLabel(" First Name");
firstNameLabel.setForeground(Color.GRAY);
firstNameLabel.setFont(font7);

lastNameLabel=new JLabel(" Last Name ");
lastNameLabel.setForeground(Color.GRAY);
lastNameLabel.setFont(font7);
genderLabel=new JLabel("   Gender   ");
genderLabel.setForeground(Color.GRAY);
genderLabel.setFont(font7);
panNumberLabel=new JLabel("PAN Number");
panNumberLabel.setForeground(Color.GRAY);
panNumberLabel.setFont(font7);
accountNumberTextField=new JTextField(25);
accountNumberTextField.setFont(font6);
firstNameTextField=new JTextField(25);
firstNameTextField.setFont(font6);
lastNameTextField=new JTextField(25);
lastNameTextField.setFont(font6);
genderTextField=new JTextField(25);
genderTextField.setFont(font6);
panNumberTextField=new JTextField(25);
panNumberTextField.setFont(font6);
panel10=new JPanel();
panel10.setLayout(new FlowLayout());
panel10.add(accountNumberLabel);
panel10.add(accountNumberTextField);
panel11=new JPanel();
panel11.setLayout(new FlowLayout());
panel11.add(firstNameLabel);
panel11.add(firstNameTextField);
panel12=new JPanel();
panel12.setLayout(new FlowLayout());
panel12.add(lastNameLabel);
panel12.add(lastNameTextField);
panel13=new JPanel();
panel13.setLayout(new FlowLayout());
panel13.add(genderLabel);
panel13.add(genderTextField);
panel14=new JPanel();
panel14.setLayout(new FlowLayout());
panel14.add(panNumberLabel);
panel14.add(panNumberTextField);
panel6.setLayout(new GridLayout(5,1));
panel6.add(panel10);
panel6.add(panel11);
panel6.add(panel12);
panel6.add(panel13);
panel6.add(panel14);
panel7=new JPanel();
Font font12=new Font("Verdana",Font.BOLD,20);
saveButton=new JButton("save");
saveButton.setFont(font12);
saveButton.setBackground(Color.GREEN);
editButton=new JButton("edit");
editButton.setFont(font12);
deleteButton=new JButton("delete");
deleteButton.setFont(font12);
cancelButton=new JButton("cancel");
cancelButton.setFont(font12);
pdfButton=new JButton("PDF");
pdfButton.setFont(font12);
panel7.setLayout(new FlowLayout());
panel7.add(saveButton);
panel7.add(editButton);
panel7.add(deleteButton);
panel7.add(cancelButton);
panel7.add(pdfButton);
panel9=new JPanel();
panel9.setLayout(new  BorderLayout());
panel9.add(panel5,BorderLayout.NORTH);
panel9.add(panel1,BorderLayout.CENTER);
panel3=new JPanel();
panel3.setLayout(new BorderLayout());
panel3.add(panel9,BorderLayout.NORTH);
panel3.add(panel6,BorderLayout.CENTER);
panel3.add(panel7,BorderLayout.SOUTH);
add(panel3);

searchTextField.getDocument().addDocumentListener(this);

setDefaultCloseOperation(EXIT_ON_CLOSE);
setSize(600,600);
setLocation(100,100);
setVisible(true);
}

public void insertUpdate(DocumentEvent ev)
{
System.out.println("Sonti ji ");
}
public void removeUpdate(DocumentEvent ev)
{
System.out.println("Sonti ji ");
}
public void changedUpdate(DocumentEvent ev)
{
System.out.println("Sonti ji ");
}
public void actionPerformed(ActionEvent ev)
{
System.out.println("Sonti ji ");
}


public static void main (String args[])
{
new eg1();
}
}

///------------------------------------------------------------------------------------------------------------
/*
C:\java715amtts\bankapp\pl\src\com\thinking\machines\banking\pl>javac -classpath c:\java715amtts\seq\classes;c:\java715amtts\bankapp\dl\classes;c:\java715amtts\bankapp\bl\classes;c:\java715amtts\bankapp\pl\classes;c:\java715amtts\bankapp\dl\testcases;c:\java715amtts\bankapp\pl\src\com\thinking\machines\banking\pl;c:\java715amtts\bankapp\bl\testcases eg1.java

C:\java715amtts\bankapp\pl\src\com\thinking\machines\banking\pl>java -classpath c:\java715amtts\seq\classes;c:\java715amtts\bankapp\dl\classes;c:\java715amtts\bankapp\bl\classes;c:\java715amtts\bankapp\pl\classes;c:\java715amtts\bankapp\dl\testcases;c:\java715amtts\bankapp\pl\src\com\thinking\machines\banking\pl;c:\java715amtts\bankapp\bl\testcases eg1
*/