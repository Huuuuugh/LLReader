package qwq.reader;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ReaderMain extends Frame{
	/**
	 * 
	 */
	static String SuperTemp;
	public static int ccount=0;
	public static long k;
	static int count=1;
	static JButton c1 = new JButton();
	static JButton c2 = new JButton();
	static JButton c3 = new JButton();
	static JButton c4 = new JButton();
	private static final long serialVersionUID = 1L;
	static int fontSize=25;
	static int sWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
	static int sHeight = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height); 
	static int posX;
	static int posY;
	static int tempX;
	static int judge;
	static int tempY;
	static String flag;
	static int i=1;
	static int[] posA = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	static int[] posB = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	static int[] posC = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	static int[] posD = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	static int[] preA = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	static int[] preB = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	static int[] preC = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	static int[] preD = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	public static String str=null;
	static JLabel lab=new JLabel("  hello",JLabel.LEFT);
	static ReaderMain choose = new ReaderMain("选择界面");
	public static void main(String[] args) throws IOException {
		SearchUtil su=new SearchUtil();
		choose.setSize(500,300);
		Properties pro = new Properties();
		ReaderMain s = new ReaderMain("设置");
		ReaderMain f = new ReaderMain("请先在本窗口选一个点用鼠标点击~");
		s.setLayout(null);
		choose.setLocation(sWidth/3,sHeight/3);
		choose.setLayout(null);
		ReaderMain temp = new ReaderMain("");
		String fileName = System.getProperty("user.dir")+"\\剧本.txt";
		File fffff=new File(fileName);
		k=getLineNumber(fffff);
		if(!fffff.exists()) {
			fffff.createNewFile();
		}
		//readALine(fileName, 10880);
		su.SearchKeyword(fffff, "###1");
		posA = su.line;
		su.SearchKeyword(fffff, "###2");
		posB = su.line;
		su.SearchKeyword(fffff, "###3");
		posC = su.line;
		su.SearchKeyword(fffff, "###4");
		posD = su.line;
		
		
 
		File config = new File(System.getProperty("user.dir")+"\\config.properties");
		JButton reset=new JButton("重置配置");
		c1.setSize(300,50);
		c1.setLocation(c1.getWidth()/3,c1.getHeight());
		c2.setSize(300,50);
		c2.setLocation(c1.getWidth()/3,c1.getHeight()+60);
		c3.setSize(300,50);
		c3.setLocation(c1.getWidth()/3,c1.getHeight()+120);
		c4.setSize(300,50);
		c4.setLocation(c1.getWidth()/3,c1.getHeight()+180);
		choose.add(c1);
		choose.add(c2);
		choose.add(c3);
		choose.add(c4);
		if(!config.exists()) {
			config.createNewFile();
			FileOutputStream oFile = new FileOutputStream(System.getProperty("user.dir")+"\\config.properties", true);
			pro.setProperty("FontSize", fontSize+"");
			pro.setProperty("Process", i+"");
			pro.store(oFile, "Huugh Config");
			oFile.close();
		}else {
			InputStream in = new BufferedInputStream (new FileInputStream(System.getProperty("user.dir")+"/config.properties"));
			pro.load(in);  
			//fontSize=Integer.parseInt();

			fontSize=Integer.parseInt(pro.getProperty("FontSize"));
			i=Integer.parseInt(pro.getProperty("Process"));
			System.out.println(i);
			judge=0;
			if(getLineNumber(fffff)<i) {
				i=1;
			}
			fixT(fileName, i);
			judge=1;
			//i++;
		}
		//TextReader tr=new TextReader();
		//File awa = new File(fileName);
		//long line=tr.getLineNumber(awa);
		//System.out.println(line);
		f.addMouseListener(new MouseListener() {
			@Override
			
			
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				f.setTitle("");
//						i++;
				if(tempX == 0 && tempY==0) {
					tempX = e.getXOnScreen();
					tempY = e.getYOnScreen();
				}
				try {
					fixT(fileName, i);
				} catch (IOException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				}
				try {
					Robot robot = new Robot();
					
					robot.mouseMove(1492, 953);  
					//robot.delay(3);  
					//robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.delay(5);  
					//robot.mouseRelease(InputEvent.BUTTON1_MASK);
					//robot.delay(5); 
					//**for(int i=0;i<1000000;i++) {
					//robot.mousePress(InputEvent.BUTTON1_MASK);
					//robot.mouseWheel(1);
					robot.delay(1);
					//robot.delay(10); 
					
					robot.mouseWheel(1);//同步进程
					robot.mouseWheel(1);//加速
					//*robot.mouseRelease(InputEvent.BUTTON1_MASK);
					//**}
					robot.mouseMove(tempX, tempY);//复位 
					System.out.println("pos"+posX +" "+ posY);
					robot.mouseMove(tempX, tempY);
					System.out.println("temp"+tempX +" "+ tempY);
					f.getFocusableWindowState();
					
				} catch (AWTException e1) {
					// TODO 自动生成的 catch 块
					
					e1.printStackTrace();
				}  
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
		});
		
		
		temp.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				posX = e.getXOnScreen();
				posY = e.getYOnScreen();
				System.out.println(posX +" "+ posY);
				temp.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
		});
		
		f.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// TODO 自动生成的方法存根


				 if(e.getWheelRotation()==1){
						if(tempX == 0 && tempY==0) {
							tempX = e.getXOnScreen();
							tempY = e.getYOnScreen();
						}
						
						try {
							fixT(fileName, i);
						} catch (IOException e2) {
							// TODO 自动生成的 catch 块
							e2.printStackTrace();
						}
							try {
								Robot robot = new Robot();
								robot.mouseMove(posX, posY);  
								robot.delay(3);
								robot.mouseWheel(1);
								robot.delay(10); 
								robot.mouseWheel(1);
								robot.mouseMove(tempX, tempY); 
								System.out.println("pos"+posX +" "+ posY);
								robot.mouseMove(tempX, tempY);
								System.out.println("temp"+tempX +" "+ tempY);
								f.getFocusableWindowState();
							} catch (AWTException e1) {
								// TODO 自动生成的 catch 块
								
								e1.printStackTrace();
							}
						}
						  
			        
			}});
		
		temp.setSize(sWidth,sHeight);
		s.setSize(sWidth/2,sHeight/3);
		s.setLocation(sWidth/3,sHeight/3);
		f.setSize(sWidth/3+400,sHeight/6+60);
		Color qwq=new Color(0, 60, 89);
		f.setBackground(qwq);
		f.setLocation((sWidth)/2-380, (sHeight)/2+270);
		posX=(sWidth)/2+200;
		posY=(sHeight)/2;
		f.setAlwaysOnTop(true);
		choose.setAlwaysOnTop(true);
		Font font=new Font("黑体",Font.BOLD,fontSize);
		lab.setFont(font);
		lab.setForeground(Color.white);
		Point posLab = new Point(400,100);
		lab.setLocation(posLab);
		JButton Foup = new JButton();
		Foup.setSize(80, 30);
		Foup.setLocation((int) (f.getWidth()/100f),(int) (f.getHeight()/6.5));
		Foup.setText("设置");
		Foup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				s.setVisible(true);
				
			}
		});
		c1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choose.setVisible(false);
				i=posA[ccount];
				try {
					fixT(fileName,i);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				System.out.println(posA[ccount]);
				ccount++;
			}
		});
		c2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choose.setVisible(false);
				i=posB[ccount];
				try {
					fixT(fileName,i);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				ccount++;
			}
		});
		c3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choose.setVisible(false);
				i=posC[ccount];
				try {
					fixT(fileName,i);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				ccount++;
			}
		});
		c4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choose.setVisible(false);
				i=posD[ccount];
				try {
					fixT(fileName,i);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				ccount++;
			}
		});
		JButton swi = new JButton();
		swi.setBounds(80, 30,(int) (f.getWidth()/1.2f)+100,f.getHeight()/4);
		swi.setLocation((int) (f.getWidth()/1.2f),f.getHeight()/4);
		swi.setText("aaa");
		swi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				s.setVisible(true);
			}
		});
		

		JButton select = new JButton();
		select.setSize(200,80);
		select.setLocation((int) (f.getWidth()/12f),f.getHeight()/4);
		reset.setSize(200,80);
		reset.setLocation((int) (f.getWidth()/12f),f.getHeight()/2+80);
		select.setText("选择点击位置");

		
		
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//####
				System.gc();
				
				File config = new File(System.getProperty("user.dir")+"/config.properties");
						i=1;
						if(config.delete()) {
							
							System.out.println("配置重置");
							JOptionPane.showMessageDialog(null,"重置成功","消息",JOptionPane.PLAIN_MESSAGE);
						}else {
							System.out.println("失败");
							JOptionPane.showMessageDialog(null,"失败，请关闭本程序并删除config.properties","错误",JOptionPane.ERROR_MESSAGE);
						}
						System.gc();
						s.setVisible(false);
			}
		});
		s.add(reset);
		
		
		select.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				s.setVisible(false);
				temp.setVisible(true);
				temp.setCursor(Cursor.CROSSHAIR_CURSOR);
			}
		});
		f.add(Foup);
		f.add(lab);
		//f.add(reset);
		//f.add(swi);
		s.add(select);
		temp.setUndecorated(true);
		f.setVisible(true);
		temp.setOpacity(0.01f);	
		
		f.addWindowListener(new WindowAdapter()
		{
		public void windowClosing(WindowEvent e)
		{
			
			System.gc();
			File config = new File(System.getProperty("user.dir")+"/config.properties");
				try {
					
					if(config.delete()) {
						System.out.println("配置重置");
					}else {
						System.out.println("失败");
					}
					
					config.createNewFile();
					FileOutputStream oFile = new FileOutputStream(System.getProperty("user.dir")+"/config.properties", true);
					
					pro.setProperty("FontSize", fontSize+"");
					if(i==1) {
						pro.setProperty("Process", i+"");
					}else {
					pro.setProperty("Process", i-1+"");
					}
					pro.store(oFile, "Huugh Config");
					oFile.close();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
			

			f.dispose();
			System.exit(0);
		}
		});
		
		s.addWindowListener(new WindowAdapter()
		{
		public void windowClosing(WindowEvent e)
		{
		f.dispose();
		System.exit(0);
		}
		});
	}
	
	
	public ReaderMain(String a) {
		super(a);
	}
	//ReaderMain choose=new ReaderMain("选择");
	public static void fixT(String fileName, int number) throws IOException {
		File LineN=new File(fileName);
		if(flag!="message") {
		if(i<=getLineNumber(LineN)) {
			readALine(fileName, number);
		}else {
			i=1;
			readALine(fileName, number);
		}}
		
		if(str.contains("手机地址")||flag=="message") {
			flag="message";
			readMESSAGE(fileName, i);
			System.out.println("读完了");
			
		}
		if(str.contains("选择：")) {
    		
    		flag="select";
    		choose.setVisible(true);
    		System.out.println("SELECTING");
    		i++;
    		fixT(fileName,i);
    	}
		if(str.endsWith("#")){
    		while(str.endsWith("#")) {
			i++;
			flag="option";
			if(c1.getText()=="") {
				c1.setText(str);
				System.out.println(count);
			}else if(c2.getText()=="") {
				c2.setText(str);
				System.out.println(count);
				
			}
			else if(c3.getText()=="") {
				c3.setText(str);
				System.out.println(count);
				
			}
			else if(c4.getText()=="") {
				System.out.println(count);
				c4.setText(str);
			}
			fixT(fileName,i);
			count++;
			System.out.println("a"+count);
    		}
    		flag="normal";

		}
    	else if(str.equals("")||str.contains("---------")||str.equals("）")) {
    			i++;
    			fixT(fileName,i);
    			flag="space";
    			System.out.println("判断好了");
    			if(SuperTemp!=null) {
    				lab.setText("<html><body>"+SuperTemp+"</body></html>");
    			}
    		}
		
    	else{
				try {
					readALine(fileName, i);
					if(flag!="select"&&flag!="option") {
					lab.setText("<html>"+str);
		            i++;
					}
				} catch (IOException e2) {
					// TODO 自动生成的 catch 块
					
					e2.printStackTrace();
				}
    		}	
	}
	public static void readALine(String fileName, int number) throws IOException {
		String fileRe = System.getProperty("user.dir")+"\\剧本.txt";
		
		FileInputStream in = new FileInputStream(fileRe);
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8")); 
		for(int b=1;b!=(i+1);b++) {
			str=br.readLine();
		}
		in.close();
		br.close();
	}
	public static void readMESSAGE(String fileName, int number) throws IOException {
		String fileRe = System.getProperty("user.dir")+"\\剧本.txt";
		String temp = null;
		FileInputStream in = new FileInputStream(fileRe);
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8")); 
		for(int b=1;b!=(i+1);b++) {
			temp = br.readLine();
		}
		in.close();
		br.close();
		if(temp.equals("")) {
			System.out.println(str);
			lab.setText(str);
			System.out.println("done");
			flag="stop";
			SuperTemp=str;
		}else{
			
			str=str+"<br>"+temp;
			i++;
			//System.out.println("temp"+temp);
			//System.out.println(str);
			readMESSAGE(fileName,i);
		}

	}
	public static long getLineNumber(File file) {
	    if (file.exists()) {
	        try {
	            FileReader fileReader = new FileReader(file);
	            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
	            lineNumberReader.skip(Long.MAX_VALUE);
	            long lines = lineNumberReader.getLineNumber() + 1;
	            fileReader.close();
	            lineNumberReader.close();
	            return lines;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return 0;
	}
	
	
}
