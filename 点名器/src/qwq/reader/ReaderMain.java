package qwq.reader;
//Foup 轮回
import java.awt.Color;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ReaderMain extends Frame{
	/**
	 * @Auther 胡肸Hugh
	 * @Date 2021年5月23日15:06:39
	 * 点名器（不放回抽取）
	 */
	public static int count=0;
	public static List<String> nameList = new ArrayList<>();
	static String SuperTemp;
	public static int ccount=0;
	public static long k;
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
	public static String str=null;
	static JLabel lab=new JLabel("点击点名",JLabel.CENTER);
	static ReaderMain choose = new ReaderMain("选择界面");
	public static void main(String[] args) throws IOException {
		String fileName = System.getProperty("user.dir")+"\\Name.txt";
		FileInputStream inn = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(inn,"UTF-8"));
		while(true){
			if(br.readLine() == null){
				break;
			}else{
				count++;
			}
		}

		File fffff=new File(fileName);
		k=getLineNumber(fffff);
		if(!fffff.exists()) {
			fffff.createNewFile();
		}
		System.out.println(count);
		SearchUtil su=new SearchUtil();
		choose.setSize(500,300);
		Properties pro = new Properties();
		ReaderMain s = new ReaderMain("设置");
		ReaderMain f = new ReaderMain("请先在本窗口选一个点用鼠标点击~");
		s.setLayout(null);
		choose.setLocation(sWidth/3,sHeight/3);
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
			//
			//
			// fixT(fileName, i);
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
				getName();
			}
			public boolean getName(){
				if(nameList.size()>=count) {
					nameList.clear();
					System.out.println("complete!");
					lab.setText("新一轮");
					return true;
				}
				//System.out.println(nameList.size());
				try {
					Random i = new Random();
					int number = i.nextInt(count);
					String Name=null;
					number=number+1;
					FileInputStream in = new FileInputStream(fileName);
					BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
					for (int j = 0; j < number; j++) {
						Name = br.readLine();
					}
					if(nameList.contains(Name)){
						getName();
					}else {
						System.out.println(Name);
						nameList.add(Name);
						lab.setText(Name+"\n("+nameList.size()+"/"+count+")");

					}
				} catch (IOException e2) {
					// TODO 自动生成的 catch 块
					nameList.clear();
					System.out.println("complete!");
					lab.setText("新一轮");
					getName();
					//e2.printStackTrace();
				}
				return true;
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
			        
			}});

		s.setSize(sWidth/2,sHeight/3);
		s.setLocation(sWidth/3,sHeight/3);
		f.setSize(sWidth/18+230,sHeight/10+100);
		Color qwq=new Color(40, 97, 125);
		f.setBackground(qwq);
		f.setLocation((sWidth)/2-380, (sHeight)/2+270);
		posX=(sWidth)/2+200;
		posY=(sHeight)/2;
		f.setAlwaysOnTop(true);
		choose.setAlwaysOnTop(true);
		Font font=new Font("黑体",Font.BOLD,40);
		lab.setFont(font);
		lab.setForeground(Color.white);
		Point posLab = new Point(400,100);
		lab.setLocation(posLab);
		JButton Foup = new JButton();
		Foup.setSize(80, 30);
		Foup.setLocation((int) (f.getWidth()/100f),(int) (f.getHeight()/6.5));
		Foup.setText("刷新");//Setting
		Foup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//s.setVisible(true);
				nameList.clear();
				lab.setText("刷新成功");

			}
		});

		c1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choose.setVisible(false);
				i=posA[ccount];
				try {
					Random i = new Random();
					int number = i.nextInt(44);
					String Name=null;
					number=number+1;
					FileInputStream in = new FileInputStream("E:\\Name.txt");
					BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
					for (int j = 0; j < number; j++) {
						Name = br.readLine();
					}

					//System.out.println(Name);
					lab.setText(Name);
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
					Random i = new Random();
					int number = i.nextInt(44);
					String Name=null;
					number=number+1;
					FileInputStream in = new FileInputStream("E:\\Name.txt");
					BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
					for (int j = 0; j < number; j++) {
						Name = br.readLine();
					}

					//System.out.println(Name);
					lab.setText(Name);
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
					Random i = new Random();
					int number = i.nextInt(44);
					String Name=null;
					number=number+1;
					FileInputStream in = new FileInputStream("E:\\Name.txt");
					BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
					for (int j = 0; j < number; j++) {
						Name = br.readLine();
					}

					//System.out.println(Name);
					lab.setText(Name);

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
					Random i = new Random();
					int number = i.nextInt(44);
					String Name=null;
					number=number+1;
					FileInputStream in = new FileInputStream("E:\\Name.txt");
					BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
					for (int j = 0; j < number; j++) {
						Name = br.readLine();
					}

					//System.out.println(Name);
					lab.setText(Name);
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
			}
		});
		f.add(Foup);
		f.add(lab);
		//f.add(reset);
		//f.add(swi);
		s.add(select);
		f.setVisible(true);
		
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
					pro.store(oFile, "Huugh Config ——废稿");
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
