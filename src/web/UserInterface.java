package web;
import java.awt.event.*; 
import javax.swing.*; 
import java.awt.*;
import javax.swing.UIManager.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class UserInterface implements ActionListener,ItemListener{  
	JFrame f,f1,statusFrame,f2,f4,f5,f6;
	JList<String> p1list;
	JTextArea name,emailID=new JTextArea(),phoneno,dateOfBirth,occupation,hobbys,p2t,appname,details,appointments,emailIDlog=new JTextArea(1,40);
	JPasswordField password,passwordlog;
	JButton signUP,login,login2,addapp,remind,manage,back=new JButton("back to options"),gMainPage=new JButton("Go to Main Page"),p1b,p2b,bb=new JButton("Show current Files in Directory"),addappb,backappointment=new JButton("back to options"),backReminder=new JButton("back to options"),boption=new JButton("back to signup");
	JLabel namel,emailIDl,passwordl,phonenol,dateOfBirthl,genderl,occupationl,hobbysl,p1l,p2l,appnamel,datel,detailsl,sortbyl,timel,t1l,t2l;
	JComboBox<String> d1,d2,d3,d1f,d2f,d3f,t1,t2;
	JComboBox<String> sortc;
	String sortbyo[]={"Name","Date","Time"};  
	JRadioButton rb1,rb2;
	JPanel p1f,p2f,pmainf,p3f,p4f,p1f1,p2f1,pmainf1,p3f1,p4f1,p1f2,p2f2,pmainf2,p3f2,p4f2,p1f4,p2f4,pmainf4,p3f4,p4f4,p1f5,p2f5,pmainf5,p3f5,p4f5,p1f6,p2f6,pmainf6,p3f6,p4f6;
	JFileChooser fc;
	public void initialise()
	{
		this.signup();
		this.login();
		this.options();
		this.addAppointment();
		this.reminder();
		this.fileChooser();
		this.f.setVisible(true);
	}
	
public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==this.signUP)
	{
		//System.out.println("aduidh");
		//this.f.setVisible(false);
		boolean isValid = this.isInteger(this.phoneno.getText());
		
		EmailValidator em=new EmailValidator();
		if(em.validate(this.emailID.getText()))
		{
			if(isValid)
			{
		if(this.signupAction())
		{
			this.f4.setVisible(true);this.f.setVisible(false);this.f1.setVisible(false);this.f2.setVisible(false);this.f5.setVisible(false);this.f6.setVisible(false);
			this.statusFrame=new JFrame("status");
			JOptionPane.showMessageDialog(this.statusFrame,"signup succesful");
		}
		else
		{
			this.statusFrame=new JFrame("status");
			JOptionPane.showMessageDialog(this.statusFrame,"this email ID is empty or has already been registered");
		}
		}
		else
		{
			this.statusFrame=new JFrame("status");
			JOptionPane.showMessageDialog(this.statusFrame,"Invalid Phone number ,it can only be numerical");
			
		}
		}
		else
		{
			this.statusFrame=new JFrame("status");
			JOptionPane.showMessageDialog(this.statusFrame,"this email ID is invalid,Type valid email ID");
		}
		
	}
	
	else if(e.getSource()==this.login)
	{
		emailID.setText("");
		this.f4.setVisible(false);this.f.setVisible(false);this.f1.setVisible(true);this.f2.setVisible(false);this.f5.setVisible(false);this.f6.setVisible(false);
	}
	
	else if(e.getSource()==this.gMainPage)
	{
		this.f4.setVisible(false);this.f.setVisible(true);this.f1.setVisible(false);this.f2.setVisible(false);this.f5.setVisible(false);this.f6.setVisible(false);
	}
	else if(e.getSource()==this.login2)
	{
		if(this.loginAction())
		{
			this.statusFrame=new JFrame("status");
			JOptionPane.showMessageDialog(this.statusFrame,"You have succesfully logged in");
			this.f4.setVisible(true);this.f.setVisible(false);this.f1.setVisible(false);this.f2.setVisible(false);this.f5.setVisible(false);this.f6.setVisible(false);
		}
		else
		{
			this.statusFrame=new JFrame("status");
			JOptionPane.showMessageDialog(this.statusFrame,"incorrect login credentials");
		}
		this.emailID.setText("");
	}
	else if(e.getSource()==this.back)
	{
		this.f4.setVisible(true);this.f.setVisible(false);this.f1.setVisible(false);this.f2.setVisible(false);this.f5.setVisible(false);this.f6.setVisible(false);
	}
	else if(e.getSource()==this.backappointment)
	{
		this.f4.setVisible(true);this.f.setVisible(false);this.f1.setVisible(false);this.f2.setVisible(false);this.f5.setVisible(false);this.f6.setVisible(false);
	}
	else if(e.getSource()==this.backReminder)
	{
		this.appointments.setText("");
		this.f4.setVisible(true);this.f.setVisible(false);this.f1.setVisible(false);this.f2.setVisible(false);this.f5.setVisible(false);this.f6.setVisible(false);
	}
	else if(e.getSource()==this.addapp)
	{
		this.appname.setText("");
		this.details.setText("");
		this.f4.setVisible(false);this.f.setVisible(false);this.f1.setVisible(false);this.f2.setVisible(false);this.f5.setVisible(true);this.f6.setVisible(false);
	}
	else if(e.getSource()==this.remind)
	{
		
		this.f4.setVisible(false);this.f.setVisible(false);this.f1.setVisible(false);this.f2.setVisible(false);this.f5.setVisible(false);this.f6.setVisible(true);
	}
	else if(e.getSource()==this.manage)
	{
		this.f4.setVisible(false);this.f.setVisible(false);this.f1.setVisible(false);this.f2.setVisible(true);this.f5.setVisible(false);this.f6.setVisible(false);
	}
	else if(e.getSource()==this.boption)
	{
		this.f4.setVisible(false);this.f.setVisible(true);this.f1.setVisible(false);this.f2.setVisible(false);this.f5.setVisible(false);this.f6.setVisible(false);
		
	}
	else if(e.getSource()==this.addappb)
	{
		this.appointmentAction();
		this.statusFrame=new JFrame("status");
		JOptionPane.showMessageDialog(this.statusFrame,"appointment added succesfully");
	}
	else if(e.getSource()==this.p1b)
	{
		String emailIDs;
		if(this.emailID.getText().length()!=0)
		{
			emailIDs=emailID.getText();
		}
		else
		{
			emailIDs=this.emailIDlog.getText();
		}
		
		Date d=new Date();
		File file = fc.getSelectedFile();
		try{
			byte[] filedata = new byte[(int) file.length()];
			DataInputStream dis = new DataInputStream(new FileInputStream(file));
			dis.readFully(filedata);  // read from file into byte[] array
			dis.close();
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hiadb","root","");
			String sql = "INSERT INTO filetable (emailid, timeadded, filename, file) values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, emailIDs);
            statement.setString(2, d.toString());
            statement.setString(3, file.getName());
            statement.setBytes(4, filedata);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("A file was added in database.");
            }		
			con.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		System.out.println("Saving: " + file.getName() + ".");
		this.statusFrame=new JFrame("status");
		JOptionPane.showMessageDialog(this.statusFrame,"file added succesfully");
	}
	else if(e.getSource()==this.bb)
	{
		String emailIDs;
		if(this.emailID.getText().length()!=0)
		{
			emailIDs=emailID.getText();
		}
		else
		{
			emailIDs=this.emailIDlog.getText();
		}
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hiadb","root","");
			Statement stmt=con.createStatement();
			String sql = "SELECT emailid, filename, timeadded FROM filetable" +
		            " WHERE emailid = '"+emailIDs+"'";
			ResultSet rs1=stmt.executeQuery(sql);
			String ans="";
			while(rs1.next())
			{
				
				ans=ans+"file details:"+rs1.getString("filename")+" added on: "+rs1.getString("timeadded")+System.lineSeparator();
			}
			this.statusFrame=new JFrame("files added till now--");
			JOptionPane.showMessageDialog(this.statusFrame,ans);
			con.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
	}
		
}
public void itemStateChanged(ItemEvent event) {
	String emailIDs;
	String sortbyType=sortc.getItemAt(sortc.getSelectedIndex());
	if(this.emailID.getText().length()!=0)
	{
		emailIDs=emailID.getText();
	}
	else
	{
		emailIDs=this.emailIDlog.getText();
	}
	this.appointments.setText("Wanted Appointments:"+System.lineSeparator());
    
    	try{
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hiadb","root","");
    		Statement stmt=con.createStatement();
    		String sql = "SELECT Name,Details,Date,Time FROM appointments" +" WHERE emailid ='"+emailIDs+"' ORDER BY "+sortbyType+" ASC";
    		ResultSet rs1=stmt.executeQuery(sql);
    		while(rs1.next())
    		{
    			String datet[]=rs1.getString("Date").split("/");
    			String s="Appointment Name:"+rs1.getString("Name")+ " Date:"+datet[2]+"/"+datet[1]+"/"+datet[0]+" Time:"+rs1.getString("Time")+" Details:"+rs1.getString("Details")+System.lineSeparator();
    			this.appointments.append(s);
    		}
    		con.close();
    		}
    		catch(Exception ex)
    		{
    			ex.printStackTrace();
    		}
    	System.out.println(sortbyType);
       // do something with object
  
 }
public void signup()
{
	f=new JFrame("Main Page");
	p1f=new JPanel(new GridLayout(4,4,25,25));p2f=new JPanel();pmainf=new JPanel();p3f=new JPanel(new GridLayout(1,2));p4f=new JPanel();
	p2f.setLayout(new BoxLayout(p2f,BoxLayout.Y_AXIS));
    name=new JTextArea();
    this.d1f=new JComboBox<String>();this.d2f=new JComboBox<String>();this.d3f=new JComboBox<String>();
    d1f.addActionListener(this);d2f.addActionListener(this);d3f.addActionListener(this);
    for(int i=1;i<=31;++i)
	{
		d1f.addItem(String.valueOf(i));
	}
	for(int j=1;j<=12;++j)
	{
		d2f.addItem(String.valueOf(j));
	}
	for(int k=1990;k<=2020;++k)
	{
		d3f.addItem(String.valueOf(k));
	}
	d1f.setFont(new Font("Serif",Font.PLAIN,15));d2f.setFont(new Font("Serif",Font.PLAIN,15));d3f.setFont(new Font("Serif",Font.PLAIN,15));
	this.p4f.add(d1f);this.p4f.add(d2f);this.p4f.add(d3f);
    password=new JPasswordField(20);phoneno=new JTextArea();dateOfBirth=new JTextArea();occupation=new JTextArea();hobbys=new JTextArea();
    name.setFont(new Font("Serif",Font.PLAIN,15));emailID.setFont(new Font("Serif",Font.PLAIN,15));password.setFont(new Font("Serif",Font.PLAIN,15));phoneno.setFont(new Font("Serif",Font.PLAIN,15));dateOfBirth.setFont(new Font("Serif",Font.PLAIN,15));occupation.setFont(new Font("Serif",Font.PLAIN,15));hobbys.setFont(new Font("Serif",Font.PLAIN,15));
    namel=new JLabel("Name:");emailIDl=new JLabel("Email Id(required):");passwordl=new JLabel("Password:");phonenol=new JLabel("Phone NO.:");dateOfBirthl=new JLabel("Date Of Birth:");genderl=new JLabel("Select Gender:");occupationl=new JLabel("Occupation:");hobbysl=new JLabel("Hobbys:");
    namel.setHorizontalAlignment(JLabel.CENTER);emailIDl.setHorizontalAlignment(JLabel.CENTER);passwordl.setHorizontalAlignment(JLabel.CENTER);phonenol.setHorizontalAlignment(JLabel.CENTER);dateOfBirthl.setHorizontalAlignment(JLabel.CENTER);genderl.setHorizontalAlignment(JLabel.CENTER);occupationl.setHorizontalAlignment(JLabel.CENTER);hobbysl.setHorizontalAlignment(JLabel.CENTER);
    signUP=new JButton("Enter above details to signup");login=new JButton("else login if already signed up");
    this.signUP.setBackground(Color.RED);this.login.setBackground(Color.BLUE);this.signUP.setForeground(Color.white);this.login.setForeground(Color.white);
    signUP.addActionListener(this);
    login.addActionListener(this);
    rb1=new JRadioButton("Male");rb2=new JRadioButton("Female");
    ButtonGroup bg=new ButtonGroup();    
    bg.add(rb1);bg.add(rb2);
    p2f.add(rb1);p2f.add(rb2);
    p1f.add(namel);p1f.add(name);p1f.add(dateOfBirthl);p1f.add(p4f);p1f.add(genderl);p1f.add(p2f,BorderLayout.CENTER);p1f.add(emailIDl);p1f.add(emailID);
    p1f.add(phonenol);p1f.add(phoneno);p1f.add(passwordl);p1f.add(password);p1f.add(hobbysl);p1f.add(hobbys);p1f.add(occupationl);p1f.add(occupation);
    p3f.add(signUP);p3f.add(login);
    f.add(p1f,BorderLayout.CENTER);
    f.add(p3f,BorderLayout.SOUTH);
    f.setSize(600,450);     
    //f.setVisible(true);
}
public boolean signupAction()
{
	String names=name.getText(),emailIDs=this.emailID.getText(),phonenos=phoneno.getText(),occupations=occupation.getText(),hobbyss=hobbys.getText();//passwords=new String(password.getPassword());
	String passwords=new String(this.password.getPassword());
	System.out.println(passwords);
	String d1i=(d1f.getItemAt(d1f.getSelectedIndex())),d2i=d2f.getItemAt(d2f.getSelectedIndex()),d3i=d3f.getItemAt(d3f.getSelectedIndex()); ;
	String gender="";
	if(this.rb1.isSelected())
	{
		gender="male";
	}
	else if(this.rb2.isSelected())
	{
		gender="female";
	}
	try{
	Class.forName("com.mysql.jdbc.Driver");  
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hiadb","root","");
	Statement stmt=con.createStatement();
	String sql = "SELECT emailid FROM userdetails" +
            " WHERE emailid = '"+emailIDs+"'";
	ResultSet rs1=stmt.executeQuery(sql);
	if(emailIDs.length()==0)
		return false;
	while(rs1.next())
	{
		
		if(rs1.getString("emailid").compareTo(emailIDs)==0)
		{
			System.out.println("enailID got:"+rs1.getString("emailid"));
			return false;
		}
	}
	String dates=d1i+" "+d2i+" "+d3i;
	//s.executeUpdate("INSERT INTO `time_entry`(pid,tid,rid,tspend,description) VALUE ('"+pid+"','"+tid+"','"+rid+"',"+tspent+",'"+des+"')");
	int rs=stmt.executeUpdate("INSERT INTO `userdetails` (`emailid`, `password`, `name`, `phoneno`, `gender`, `occupation`, `hobbys`, `dateofbirth`) VALUES('"+emailIDs+"','"+passwords+"','"+names+"','"+phonenos+"','"+gender+"','"+occupations+"','"+hobbyss+"','"+dates+"')");
	//int rs=stmt.executeUpdate("INSERT INTO `userdetails` (`emailid`, `password`, `name`, `phoneno`, `gender`, `occupation`, `hobbys`, `dateofbirth`) VALUES ("+names+", 'pass123', '', '', '', '', '', '')");
	this.name.setText("");this.password.setText("");this.phoneno.setText("");this.occupation.setText("");this.hobbys.setText("");
	con.close();
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	System.out.println("name"+names+" emailID "+emailIDs+"phoneno"+phonenos+" d1i "+d1i+"gender"+gender+"passwords "+passwords);
	return true;
}
public void login()
{
	this.f1=new JFrame("Login");
	this.f.setVisible(false);
	this.p1f1=new JPanel();
	this.p2f1=new JPanel();
	this.p3f1=new JPanel();
	this.p4f1=new JPanel();
	this.p4f1.setLayout(new FlowLayout(FlowLayout.LEFT));
	//p2.setLayout(new FlowLayout(FlowLayout.LEFT));
	p3f1.setLayout(new BoxLayout(p3f1,BoxLayout.Y_AXIS));
	this.login2=new JButton("login");
	this.login2.setForeground(Color.white);
	this.login2.setBackground(Color.blue);
	this.login2.addActionListener(this);
	this.emailIDl=new JLabel("Email ID:");
	this.passwordl=new JLabel("password:");
	this.passwordlog=new JPasswordField(40);
	p4f1.add(this.gMainPage);
	this.gMainPage.setBackground(Color.ORANGE);
	this.gMainPage.setForeground(Color.white);
	this.gMainPage.addActionListener(this);
	p1f1.add(emailIDl);p1f1.add(emailIDlog);
	p2f1.add(passwordl);p2f1.add(passwordlog);
	p3f1.add(p4f1);
	p3f1.add(p1f1);
	p3f1.add(p2f1);
	f1.add(p3f1,BorderLayout.CENTER);
	f1.add(login2,BorderLayout.SOUTH);
	f1.setSize(600,450);     
    //f1.setVisible(true);
}
public boolean loginAction()
{
	String EmailIDs=this.emailIDlog.getText(),passwords=new String(this.passwordlog.getPassword());
	boolean b=false;
	try{
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hiadb","root","");
		Statement stmt=con.createStatement();
		String sql = "SELECT password FROM userdetails" +
                " WHERE emailid = '"+EmailIDs+"'";
		ResultSet rs=stmt.executeQuery(sql);
		
		while(rs.next())
		{
			
			if(rs.getString("password").compareTo(passwords)==0)
			{
				System.out.println("password got:"+rs.getString("password"));
				b=true;
			}
		}
		con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	System.out.println("EmailIdl"+EmailIDs+"passwords"+passwords);
	this.passwordlog.setText("");
	return b;
}
public void fileChooser()
{
	this.f2=new JFrame("File Chooser");
	this.p1f2=new JPanel();
	this.p2f2=new JPanel();
	this.p3f2=new JPanel();
	this.p1f2.setLayout(new BoxLayout(p1f2,BoxLayout.Y_AXIS));
	this.p3f2.setLayout(new FlowLayout(FlowLayout.LEFT));
	DefaultListModel<String> l1 = new DefaultListModel<>();
	l1.addElement("temp");
	this.p1list=new JList<String>();
	fc=new JFileChooser();   
	this.f2.setSize(600,450);
	this.p1l=new JLabel("Upload File");
	this.p1b=new JButton("Upload chosen File");
	this.p1b.addActionListener(this);
	this.p3f2.add(this.back);
	this.back.setBackground(Color.ORANGE);
	this.back.setForeground(Color.white);
	this.bb.setForeground(Color.white);
	this.bb.setBackground(Color.red);
	this.bb.addActionListener(this);
	this.back.addActionListener(this);
	this.p1f2.add(p1l);
	this.p1f2.add(fc);
	this.p1f2.add(p1b);
	this.f2.add(p3f2,BorderLayout.NORTH);
	this.f2.add(p1f2,BorderLayout.CENTER);
	this.f2.add(bb,BorderLayout.SOUTH);
	//this.f2.setVisible(true);
}
public void options()
{
	this.f4=new JFrame("Select options");
	this.addapp=new JButton("Add appointment");
	this.addapp.setBackground(Color.BLUE);
	this.addapp.setForeground(Color.white);
	addapp.setFont(new Font("Serif",Font.PLAIN,20));
	this.addapp.addActionListener(this);
	this.remind=new JButton("Remind");
	this.remind.setForeground(Color.white);
	this.remind.setBackground(Color.GREEN);
	remind.setFont(new Font("Serif",Font.PLAIN,20));
	this.remind.addActionListener(this);
	this.manage=new JButton("Manage Files");
	this.manage.setForeground(Color.white);
	this.manage.setBackground(Color.RED);
	this.manage.addActionListener(this);
	manage.setFont(new Font("Serif",Font.PLAIN,20));
	this.p1f4=new JPanel();
	this.p2f4=new JPanel();
	this.p3f4=new JPanel();
	this.boption.addActionListener(this);
	p3f4.add(this.boption);
	this.boption.setBackground(Color.ORANGE);
	this.boption.setForeground(Color.white);
	boption.setFont(new Font("Serif",Font.PLAIN,20));
	this.f4.add(p3f4,BorderLayout.NORTH);
	this.p1f4.setLayout(new GridLayout(2,1));
	this.p2f4.setLayout(new GridLayout(1,1));
	this.p1f4.add(addapp);
	this.p1f4.add(remind);
	this.p2f4.add(manage);
	this.f4.add(p1f4,BorderLayout.WEST);
	this.f4.add(p2f4,BorderLayout.CENTER);
	this.f4.setSize(600,450);
	//this.f4.setVisible(true);
}
public void addAppointment()
{
	this.f5=new JFrame("Appointment Adder");
	this.p1f5=new JPanel(new GridLayout(4,2));
	this.p2f5=new JPanel(/*new GridLayout(1,3)*/);
	this.p3f5=new JPanel();this.p4f5=new JPanel();
	this.backappointment.addActionListener(this);
	this.backappointment.setBackground(Color.ORANGE);
	this.backappointment.setForeground(Color.white);
	backappointment.setFont(new Font("Serif",Font.PLAIN,20));
	p3f5.add(this.backappointment);
	this.f5.add(p3f5,BorderLayout.NORTH);
	this.p3f5.setLayout(new FlowLayout(FlowLayout.LEFT));
	this.addappb=new JButton("Add Appointment");
	this.t1l=new JLabel("Hours");this.t2l=new JLabel("Minutes");
	addappb.setFont(new Font("Serif",Font.PLAIN,20));
	t1l.setFont(new Font("Serif",Font.PLAIN,20));
	t2l.setFont(new Font("Serif",Font.PLAIN,20));
	this.appname=new JTextArea();this.details=new JTextArea();this.appnamel=new JLabel("Appointment name");this.datel=new JLabel("Date");this.detailsl=new JLabel("Details");timel=new JLabel("Time:");
	appname.setFont(new Font("Serif",Font.PLAIN,20));details.setFont(new Font("Serif",Font.PLAIN,20));appnamel.setFont(new Font("Serif",Font.PLAIN,20));datel.setFont(new Font("Serif",Font.PLAIN,20));detailsl.setFont(new Font("Serif",Font.PLAIN,20));
	this.d1=new JComboBox<String>();this.d2=new JComboBox<String>();this.d3=new JComboBox<String>();
	this.t1=new JComboBox<String>();this.t2=new JComboBox<String>();
	for(int i=1;i<=31;++i)
	{
		if(i/10<1)
			d1.addItem("0"+String.valueOf(i));
		else
			d1.addItem(String.valueOf(i));
	}
	for(int j=1;j<=12;++j)
	{
		if(j/10<1)
			d2.addItem("0"+String.valueOf(j));
		else
			d2.addItem(String.valueOf(j));
	}
	for(int k=1990;k<=2020;++k)
	{
		d3.addItem(String.valueOf(k));
	}
	for(int i=0;i<=23;++i)
	{
		if(i/10<1)
			t1.addItem("0"+String.valueOf(i));
		else
			t1.addItem(String.valueOf(i));
	}
	for(int i=0;i<=59;++i)
	{
		if(i/10<1)
			t2.addItem("0"+String.valueOf(i));
		else
			t2.addItem(String.valueOf(i));
	}
	d1.setFont(new Font("Serif",Font.PLAIN,20));d2.setFont(new Font("Serif",Font.PLAIN,20));d3.setFont(new Font("Serif",Font.PLAIN,20));
	t1.setFont(new Font("Serif",Font.PLAIN,20));t2.setFont(new Font("Serif",Font.PLAIN,20));
	timel.setFont(new Font("Serif",Font.PLAIN,20));
	this.p2f5.add(d1);this.p2f5.add(d2);this.p2f5.add(d3);
	this.p4f5.add(t1);this.p4f5.add(t1l);this.p4f5.add(t2);this.p4f5.add(t2l);
	this.p1f5.add(appnamel);this.p1f5.add(appname);this.p1f5.add(datel);this.p1f5.add(p2f5);this.p1f5.add(timel);this.p1f5.add(p4f5);this.p1f5.add(detailsl);this.p1f5.add(details);
	this.f5.add(this.p1f5,BorderLayout.CENTER);
	this.addappb.setForeground(Color.white);
	this.addappb.setBackground(Color.blue);
	this.addappb.addActionListener(this);
	this.f5.add(this.addappb,BorderLayout.SOUTH);
	this.f5.setSize(600,500);
	//this.f5.setVisible(true);
}
public void appointmentAction()
{
	String appointmentnames=this.appname.getText(),detailss=this.details.getText();
	String d1i=d1.getItemAt(d1.getSelectedIndex()),d2i=d2.getItemAt(d2.getSelectedIndex()),d3i=d3.getItemAt(d3.getSelectedIndex()),t1i=t1.getItemAt(t1.getSelectedIndex()),t2i=t2.getItemAt(t2.getSelectedIndex());
	String emailIDs;
	if(this.emailID.getText().length()!=0)
	{
		emailIDs=emailID.getText();
	}
	else
	{
		emailIDs=this.emailIDlog.getText();
	}
	try{
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hiadb","root","");
		String dates=d3i+"/"+d2i+"/"+d1i;
		String times=t1i+"."+t2i;
		Statement stmt=con.createStatement();
		int rs=stmt.executeUpdate("INSERT INTO `appointments` (`emailid`, `Date`, `Time`, `Details`,`Name`) VALUES('"+emailIDs+"','"+dates+"','"+times+"','"+detailss+"','"+appointmentnames+"')");
		con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	System.out.println("appointmentmnames"+appointmentnames+" details "+detailss+"d1i"+d1i+"d2i"+d2i+"d3i"+d3i+"t1i"+t1i+"t2i"+t2i);
}
public void reminder()
{
	this.f6=new JFrame("Remind about appointments");
	this.p1f6=new JPanel();
	this.p3f6=new JPanel();
	this.p4f6=new JPanel();
	this.p4f6.setLayout(new BoxLayout(p4f6,BoxLayout.Y_AXIS));
	this.backReminder.addActionListener(this);
	this.backReminder.setBackground(Color.orange);
	this.backReminder.setForeground(Color.white);
	backReminder.setFont(new Font("Serif",Font.PLAIN,20));
	this.appointments=new JTextArea("Wanted Appointments:");
	this.p1f6.setLayout(new GridLayout(2,1));
	this.sortbyl=new JLabel("Sort By:");
	sortbyl.setFont(new Font("Serif",Font.PLAIN,20));
	this.p1f6.add(this.sortbyl);
	this.p3f6.setLayout(new FlowLayout(FlowLayout.LEFT));
	this.p3f6.add(backReminder);
	this.f6.add(p3f6,BorderLayout.NORTH);
	sortc=new JComboBox<String>(sortbyo);
	sortc.addItemListener(this);
	sortc.setFont(new Font("Serif",Font.PLAIN,20));
	this.p1f6.add(this.sortc);
	this.p2f6=new JPanel(new GridLayout(1,1));
	this.p2f6.add(this.appointments);
	p4f6.add(p1f6);
	p4f6.add(p2f6);
	this.f6.add(p4f6,BorderLayout.CENTER);
	//this.f6.setVisible(true);
	this.f6.setSize(600,450);
}
public static boolean isInteger(String s) {
    try { 
    	if(s.length()==0)
    		return true;
        Long.parseLong(s); 
    } catch(NumberFormatException e) { 
        return false; 
    } catch(NullPointerException e) {
        return false;
    }
    // only got here if we didn't return false
    return true;
}
public static void main(String[] args) throws Exception{  
	try {
	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	        if ("Nimbus".equals(info.getName())) {
	            UIManager.setLookAndFeel(info.getClassName());
	            break;
	        }
	    }
	} catch (Exception e) {
	    // If Nimbus is not available, you can set the GUI to another look and feel.
	}
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/hiadb","root","");
		con.close();
		UserInterface ui=new UserInterface();
	    ui.initialise();
		//here sonoo is database name, root is username and password  
		/*Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from emp"); */ 
		/*while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));*/
		}catch(Exception e){ System.out.println(e);}    
    //ui.signup();
}
}
class EmailValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	/**
	 * Validate hex with regular expression
	 *
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
}

