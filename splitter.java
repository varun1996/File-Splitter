import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
class Split extends JFrame implements ActionListener,ItemListener
{
	
	JButton split_but,exit_but,in_but,out_but,reset_but;
	JTextField in_text,out_text,user_size;
	TextArea chunks_text;
	JTextArea helparea,howarea;
	JPanel panel2;
	FileDialog dialog1,dialog2;
	Label size_type,l1,l2,l3;
	ButtonGroup rbg;
	JRadioButton cb_size1,cb_size2,cb_size3,cb_size4,cb_num;	
	JMenuBar jmb;
	JMenu file,help;
	JMenuItem open,exit,about;
	Container con,dcon,sidcon;
	String d,t;
	Date dd;
	int rbs;			//// Radio buton selected is stored here	
	FileInputStream filein;	
	FileOutputStream fileout;
	long size;	
	long filesize;
	JDialog jdg;
	JDialog sid;
	JMenuItem how;
		public Split(String title,String input)
	{
		super(title);
		size=0;
		filesize=0;
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		con=getContentPane();
		
		jdg=new JDialog(this,"Help",true);
		sid = new JDialog(this,"how",true);


		dcon=jdg.getContentPane();
		sidcon=sid.getContentPane();
		
		setdate();
		setResizable(true);
	        con.setLayout(null);
		in_text=new JTextField(20);
		out_text=new JTextField(20);
		user_size=new JTextField(20);	
		JPanel panel2=new JPanel();
		chunks_text=new TextArea(20,10);
		user_size.setEditable(false);
		chunks_text.setEditable(false);

		l1=new Label("");	
		l2=new Label(d);	
		l3=new Label(t);
		size_type=new Label("");

		if(input!="")
		{
			in_text.setText(input);
			File fileread =new File(in_text.getText());
			filesize=fileread.length();
			chunks_text.setText("File Size is "+filesize+" bytes");
		}

		helparea=new JTextArea(5,50);
		helparea.append("PRASHANT BHARDWAJ");		
		helparea.append("\nPRIYANKA AGGARWAL ");
                helparea.append("\nABHISHEK GOEL");
                helparea.append("\nVaish College of Engineering ");		
		helparea.append("\nROHTAK ");
		helparea.setEditable(false);
		helparea.setForeground(new Color(210,210,219));		
		helparea.setBackground(new Color(0,0,110));		






howarea=new JTextArea(100,50);
howarea.append("   To split a file first click on the open in file menu or  ");		
howarea.append("\n input button and then select the file. then open the   ");		
howarea.append("\n file. and then click on the output button and select   ");		
howarea.append("\n destination directory and output file name. and then ");
howarea.append("\n click  the radio button sprcifuying the size of the      ");
howarea.append("\n chunks, and then click the SPLIT button.Nowthere  ");
howarea.append("\n will a msg print that u'r file has been splitted             ");
howarea.append("\n Now if you want to merge the chunks to get the        ");
howarea.append("\n file then there is a batch file created in your               ");
howarea.append("\n specified output directory where chunks were           ");
howarea.append("\n created, and by clicking on this we can get back       ");
howarea.append("\n our files.");
howarea.setEditable(false);
howarea.setForeground(new Color(210,210,219));		
howarea.setBackground(new Color(0,0,110));		



		split_but=new JButton(new ImageIcon("SPLIT.jpg"));

		exit_but=new JButton("EXIT");
		in_but=new JButton("Input...");
		out_but=new JButton("Output...");
		reset_but=new JButton("Reset");

		split_but.setMnemonic('S');
		exit_but.setMnemonic('X');
		in_but.setMnemonic('I');
		out_but.setMnemonic('O');
		reset_but.setMnemonic('R');

		dcon.add(helparea);	
		sidcon.add(howarea);




		rbg=new ButtonGroup();

		cb_size1=new JRadioButton("1.44 MB",true);
		rbs=1;
		cb_size2=new JRadioButton("1.0 MB");
		cb_size3=new JRadioButton("0.5 MB");
		cb_size4=new JRadioButton("User Defined");
		cb_num=new JRadioButton("No of Chunks");

		rbg.add(cb_size1);
		rbg.add(cb_size2);
		rbg.add(cb_size3);
		rbg.add(cb_size4);
		rbg.add(cb_num);


		in_text.setBounds(20,50,200,30);
		out_text.setBounds(20,90,200,30);
		in_but.setBounds(230,50,100,30);
		out_but.setBounds(230,90,100,30);
		chunks_text.setBounds(20,140,200,200);

		l1.setBounds(0,420,250,30);
		l2.setBounds(250,420,150,30);
		l3.setBounds(400,420,100,30);

	
		user_size.setBounds(330,300,130,30);
		size_type.setBounds(465,307,20,30);
		exit_but.setBounds(360,380,100,30);
		split_but.setBounds(345,50,140,70);
		reset_but.setBounds(60,380,100,30);

		panel2.setLayout(new GridLayout(5,1));
		panel2.setBounds(230,140,100,200);
		panel2.add(cb_size1);
		panel2.add(cb_size2);
		panel2.add(cb_size3);
		panel2.add(cb_size4);
		panel2.add(cb_num);

		con.add(in_text);
		con.add(split_but);
		con.add(in_but);
		con.add(out_text);
		con.add(out_but);
		con.add(chunks_text);
		con.add(user_size);
		con.add(exit_but);
		con.add(reset_but);
		con.add(panel2);
		con.add(size_type);
		con.add(l1);
		con.add(l2);
		con.add(l3);
                
		jmb= new JMenuBar();
		file=new JMenu("File");
		help=new JMenu("Help");
		open=new JMenuItem("Open");
		how=new JMenuItem("How to use");
		exit=new JMenuItem("Exit");
		about=new JMenuItem("About");

		file.add(open);
		help.add(how);
		file.add(exit);
		help.add(about);

		jmb.add(file);
		jmb.add(help);

		setJMenuBar(jmb);
		split_but.addActionListener(this);
		out_but.addActionListener(this);
		in_but.addActionListener(this);
		exit_but.addActionListener(this);
		exit.addActionListener(this);
		open.addActionListener(this);
		reset_but.addActionListener(this);		
		about.addActionListener(this);
		how.addActionListener(this);		

		cb_num.addItemListener(this);
		
		cb_size4.addItemListener(this);
		cb_size1.addItemListener(this);
		cb_size2.addItemListener(this);
		cb_size3.addItemListener(this);
	}
public void setdate()
	{
		dd=new Date();
		d=new String();
		t=new String();

		d="   "+dd.getDate();
		d=d+"/"+(dd.getMonth()+1);
		d=d+"/"+(dd.getYear()+1900);
		if(dd.getHours()>12)
			t="   "+(dd.getHours()-12);
		else
			t="   "+dd.getHours();

		t=t+":"+dd.getMinutes();
		t=t+":"+dd.getSeconds();
		if(dd.getHours()>12)
			t+=" PM ";
		else
			t+=" AM ";
	}	

public void actionPerformed(ActionEvent e) 
	{
		File fileread =new File(in_text.getText());
		File filewrite=new File(out_text.getText());
		if(e.getSource()==exit_but||e.getSource()==exit)
			{
			System.exit(0);
			}
		else if(e.getSource()==in_but||e.getSource()==open)	
			{
			dialog1 =new FileDialog(this,"Open file to Split",0);
			dialog1.setVisible(true);
			in_text.setText(dialog1.getDirectory()+"\\"+dialog1.getFile());
			fileread =new File(in_text.getText());
			filesize=fileread.length();
			chunks_text.setText("File Size is "+filesize+" bytes");
			}
		else if(e.getSource()==out_but)	
			{
			dialog2 =new FileDialog(this,"Save File",1);
			dialog2.setVisible(true);
			filewrite=new File(out_text.getText());
			out_text.setText(dialog2.getDirectory()+"\\"+dialog2.getFile());
			}
		else if(e.getSource()==reset_but)	
			{
			in_text.setText("");
			out_text.setText("");
			user_size.setText("");
			chunks_text.setText("");
			}		
		else if(e.getSource()==about)	
			{
			jdg.setBounds(200,200,180,100);
			jdg.setResizable(false);
			jdg.show();
			}		

		else if(e.getSource()==how)	
			{
			sid.setBounds(200,200,180,100);
			jdg.setResizable(false);
			sid.show();
			}		



		else if(e.getSource()==split_but)
		{
		try		
		{
		File ff=new File(out_text.getText());
		if(out_text.getText().length()!=0&&(new File(ff.getParent())).exists()==true)
		{
		{
			int chunks=0;
	                if(fileread.isFile()==true)
				{
				fileread =new File(in_text.getText());
				filesize=fileread.length();
				chunks_text.setText("File Size is "+filesize+" bytes");

				filein=new FileInputStream(fileread);
				
				if(rbs==1)
					size=(long)(1.37*1024*1024);
				else if(rbs==2)
					size=1024*1024;
				else if(rbs==3)
					size=(long)(0.5*1024*1024);
				else if(rbs==4)
					{
					if(user_size!=null)
						size=Integer.parseInt(user_size.getText())*1024;
					else
						l1.setText("Specify size of chunks");	
					}
				else if(rbs==5)
					{
					size=(filesize+2)/Integer.parseInt(user_size.getText());
					//System.out.println(Integer.parseInt(user_size.getText()));
					}
				byte bytearray[]=new byte[(int)(size+1)];
	
				String comm="copy /b ";
				for(int i=0;i<filesize;i+=(size+1))
					{
					chunks++;
					fileout=new FileOutputStream(filewrite.getAbsolutePath()+chunks);
					comm=comm+(filewrite.getName()+chunks);
					filein.read(bytearray);
					if(fileread.length()-i<size)
						{
						fileout.write(bytearray,0,(int)(filesize-i));
						}
					else		
						{
						fileout.write(bytearray);
						comm=comm+"+";
						}
					chunks_text.append("\nFile "+ (filewrite.getName()+chunks) + " Completed.");	
					fileout.close();
					}
					comm=comm+" \""+(fileread.getName())+"\"";
					byte[] temp;
					temp=comm.getBytes();
					fileout=new FileOutputStream(out_text.getText()+".bat");
					fileout.write(temp);		
	
					chunks_text.append("\n No of chunks is "+chunks);
						
					filein.close();
					fileout.close();
					dialog d1=new dialog(this,"Congratulations!!!!!","Your File has been splitted succesfully.");
					d1.setVisible(true);
	
			}
		else
		{
		dialog d1=new dialog(this,"Error!!!!!","Input Path does not exists");
		d1.setVisible(true);
		}
		
			}		
		
		}
	else
		{
		dialog d1=new dialog(this,"Error!!!!!","Output path does not exists.");
		d1.setVisible(true);
		}
	                }
		catch(IOException ee)
			{
			l1.setText("Specify Output file");
			}
		catch(NumberFormatException ee)
			{
			l1.setText("Not a valid size");
			}
		catch(Exception ee)
		{
		dialog d1=new dialog(this,"Error!!!!!","Output path does not exists.");
		d1.setVisible(true);
		}	
	}
	
	}


public void itemStateChanged(ItemEvent e)
	{
	size_type.setText("");
	if(e.getItemSelectable()==cb_size1)
		{
		rbs=1;
		user_size.setEditable(false);
		}
	else if(e.getItemSelectable()==cb_size2)
		{		
		rbs=2;
		user_size.setEditable(false);
		}
	else if(e.getItemSelectable()==cb_size3)
		{
		rbs=3;
		user_size.setEditable(false);
		}
	else if(e.getItemSelectable()==cb_size4)
		{
		rbs=4;
		size_type.setText("Kb");
		user_size.setEditable(true);
		}
	else if(e.getItemSelectable()==cb_num)
		{
		rbs=5;
		size_type.setText("Ck");
		user_size.setEditable(true);
		}
	}

}

class dialog extends Dialog implements ActionListener
{
	dialog(Frame parent,String msg1,String msg2)
	{
	super(parent,msg1,true);
	setLayout(null);
	setSize(350,200);
	setLocation(362,275);
	Label l;
	Button b;
	l=new Label(msg2);
	b=new Button("Ok");
	l.setBounds(60,50,300,30);	
	b.setBounds(150,130,50,30);
	l.setFont(new Font("Times New Roman",Font.ITALIC,15));		
	b.setFont(new Font("Times New Roman",Font.ITALIC,15));			

	add(b); 	
	add(l);

	b.addActionListener(this);
	}
public void actionPerformed(ActionEvent e)
	{
	dispose();
	}
}
public class Splitter
{
	public static void main(String args[]) throws Exception
	{
		String str;
		if(args.length==0)
			str="";
		else
			str=args[0];
		Split s1=new Split("File Splitter",str);
		s1.show();
		s1.setSize(500,500);
		s1.validate();
 		s1.setLocation(200,50);
	}
}
