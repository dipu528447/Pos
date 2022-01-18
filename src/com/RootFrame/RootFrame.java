package com.RootFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import com.ShareClass.Login;
import com.AllItem.BrandCreate;
import com.AllItem.BrandWiseStockReport;
import com.AllItem.DailyIncome;
import com.AllItem.DueReport;
import com.AllItem.ItemCreate;
import com.AllItem.ModelCreate;
import com.AllItem.ProductTransectionReport;
import com.AllItem.ProductWiseStock;
import com.AllItem.PurchaseOrder;
import com.AllItem.PurchaseReturn;
import com.AllItem.SalesIR;
import com.AllItem.SalesStatement;
import com.AllItem.WastageProduct;
import com.ShareClass.InventoryCommonButton;
import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;


public class RootFrame extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	InventoryCommonButton inventoryCommonButton=new InventoryCommonButton();

	JFrame fr=new JFrame();
	JPanel panelNorth=new JPanel();
	JPanel panelWest=new JPanel();
	JScrollPane panelScroll=new JScrollPane(panelWest,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JPanel panelCenter=new JPanel();
	JLabel lblClock=new JLabel(); 
	JLabel lblWelcome=new JLabel("Welcome : ");
	
	JLabel lblUser=new JLabel("");
	JLabel lblHome=new JLabel("gsgf");

	JButton btnBrandCreate=new JButton(new ImageIcon("icon/create_add.png"));
	JLabel lblBrandCreate=new JLabel("Brand Create");
	
	JButton btnModelCreate=new JButton(new ImageIcon("icon/create_add.png"));
	JLabel lblModelCreate=new JLabel("Model Create");
	
	JButton btnItemCreate=new JButton(new ImageIcon("icon/create_add.png"));
	JLabel lblItemCreate=new JLabel("Item Create");
	
	JButton btnPurchaseOrder=new JButton(new ImageIcon("icon/purchase_order.png"));
	JLabel lblPurchaseOrder=new JLabel("Purchase Order");
	
	JButton btnPurchaseReturn=new JButton(new ImageIcon("icon/purchase_order_icon.png"));
	JLabel lblPurchaseReturn=new JLabel("Purchase Return");
	
	JButton btnSales_Return=new JButton(new ImageIcon("icon/statement_report.png"));
	JLabel lblSalesIReturn=new JLabel("Sales/Sales Return");
	
	JButton btnWastage=new JButton(new ImageIcon("icon/wastage.jpg"));
	JLabel lblWastage=new JLabel("Wastage Entry");
	
	JButton btnProductWiseStockReport=new JButton(new ImageIcon("icon/stock.jpg"));
	JLabel lblProductWiseStockReport=new JLabel("Product Wise Stock");
	
	JButton btnBrandWiseStockReport=new JButton(new ImageIcon("icon/stock.jpg"));
	JLabel lblBrandWiseStockReport=new JLabel("Brand Wise Stock");
	
	JButton btnDailySale=new JButton(new ImageIcon("icon/statement_report.png"));
	JLabel lblDailySale=new JLabel("Daily Sales");
	
	JButton btnDueReport=new JButton(new ImageIcon("icon/report.png"));
	JLabel lblDueReport=new JLabel("Due Report");
	
	JButton btnDailyIncome=new JButton(new ImageIcon("icon/report.png"));
	JLabel lblDailyIncome=new JLabel("Daily Income");
	
	
	JButton btnPasswordChange=new JButton(new ImageIcon("icon/password.png"));
	JLabel lblPasswordChange=new JLabel("Password Change");
	
	JButton btnLogout=new JButton(new ImageIcon("icon/transection.jpg"));
	JLabel lblLogout=new JLabel("Logout");
	
	ButtonGroup gp=new ButtonGroup();
	GridBagConstraints grid=new GridBagConstraints();
	BufferedImage image;

	
	JLabel lblbg=new JLabel(new ImageIcon("icon/bg.png"));

	String pharmacy="";
	final JDesktopPane dtp = new JDesktopPane(){
		//private static final long serialVersionUID = 1L;
		ImageIcon icon = new ImageIcon("icon/693439_3_original.jpg");
		Image image = icon.getImage();

		Image newimage = image.getScaledInstance(1360, 760, Image.SCALE_SMOOTH);

		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(newimage, 0, 0, this);
		}
	};	
	public RootFrame(JFrame frm,SessionBeam sessionBeam) {
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.sessionBeam=sessionBeam;
		this.fr=frm;
		this.fr.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.fr.setLocationRelativeTo(null);
		this.fr.setVisible(true);
		this.fr.setTitle("POINT OF SALES (DEVELOPED BY-WWW.CURSORBD.COM)..............");
		this.fr.setResizable(true);
		addCmp();
		background();
		btnActionEvent();
	}
	public void btnActionEvent(){
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Logout From System ?","Confrim......",JOptionPane.YES_NO_OPTION);
				if(confrim==JOptionPane.YES_OPTION){
					
					try {
						String date=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
						System.out.println("date "+date);
						String executeCmd = "mysqldump -uroot -p123456 ctgcomtech>"+"ctgcomtech"+date+".sql";
				        Process runtimeProcess;
				        runtimeProcess = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", executeCmd });
			            System.out.println(executeCmd);
//			            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			            int processComplete = runtimeProcess.waitFor();
			            System.out.println("processComplete"+processComplete);
			            if (processComplete == 0) {
			                System.out.println("Backup created successfully");

			            } else {
			                System.out.println("Could not create the backup");
			            }
					} catch (Exception e) {
						// TODO: handle exception
					}
					Login log=new Login(sessionBeam);
					fr.dispose();
				}
			}
		});
		btnItemCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame itemCreate = new JInternalFrame();
				dtp.add(itemCreate);
				itemCreate.setTitle("Item Create :: Chittagong Computer Technology     (Every Time Press Enter When You Select Product Band To Find Accurate Model)");
				itemCreate.setLocation(0,5);
				itemCreate.setSize(1340, 620);
				itemCreate.setVisible(true);
				itemCreate.setClosable(true);
				ItemCreate itemCreate1=new ItemCreate(sessionBeam);
				itemCreate.add(itemCreate1);
				itemCreate1.loadBrand();
				itemCreate1.loadProductItem();
				itemCreate1.autoId();
			}
		});
		btnPurchaseOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame purchaseOrder = new JInternalFrame();
				dtp.add(purchaseOrder);
				purchaseOrder.setTitle("Purchase Order :: Chittagong Computer Technology");
				purchaseOrder.setLocation(50,0);
				purchaseOrder.setSize(1250, 640);
				purchaseOrder.setVisible(true);
				purchaseOrder.setClosable(true);
				PurchaseOrder purchaseOrder1=new PurchaseOrder(sessionBeam);
				purchaseOrder.add(purchaseOrder1);
				purchaseOrder1.loaddRow();
				purchaseOrder1.autoInvoice();
				purchaseOrder1.autoinvoiceId();
				purchaseOrder1.loadSuppplier();
				
			}
		});
		btnPurchaseReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame purchaseReturn = new JInternalFrame();
				dtp.add(purchaseReturn);
				purchaseReturn.setTitle("Purchase Return :: Chittagong Computer Technology");
				purchaseReturn.setLocation(50,0);
				purchaseReturn.setSize(1250, 640);
				purchaseReturn.setVisible(true);
				purchaseReturn.setClosable(true);
				PurchaseReturn purchaseReturn1=new PurchaseReturn(sessionBeam);
				purchaseReturn.add(purchaseReturn1);
				purchaseReturn1.loaddRow();
				purchaseReturn1.autoInvoice();
				purchaseReturn1.autoinvoiceId();
				purchaseReturn1.loadSuppplier();
			}
		});
		btnSales_Return.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame salesIR = new JInternalFrame();
				dtp.add(salesIR);
				salesIR.setTitle("Sales/Sales Return :: Chittagong Computer Technology");
				salesIR.setLocation(50,0);
				salesIR.setSize(1250, 640);
				salesIR.setVisible(true);
				salesIR.setClosable(true);
				SalesIR salesIR1=new SalesIR(sessionBeam);
				salesIR.add(salesIR1);
				salesIR1.loaddRow();
				salesIR1.autoInvoice();
				salesIR1.autoinvoiceId();
			}
		});
		btnWastage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame WastageProduct = new JInternalFrame();
				dtp.add(WastageProduct);
				WastageProduct.setTitle("Wastage Product :: Chittagong Computer Technology");
				WastageProduct.setLocation(50,0);
				WastageProduct.setSize(1100,600);
				WastageProduct.setVisible(true);
				WastageProduct.setClosable(true);
				WastageProduct WastageProduct1=new WastageProduct(sessionBeam);
				WastageProduct.add(WastageProduct1);
				WastageProduct1.tableValue();
				WastageProduct1.autoinvoiceId();
			}
		});
		btnProductWiseStockReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame productWiseStock = new JInternalFrame();
				dtp.add(productWiseStock);
				productWiseStock.setTitle("Product Wise Stock Report :: Chittagong Computer Technology");
				productWiseStock.setLocation(50,0);
				productWiseStock.setSize(1250, 640);
				productWiseStock.setVisible(true);
				productWiseStock.setClosable(true);
				ProductWiseStock productWiseStock1=new ProductWiseStock(sessionBeam);
				productWiseStock.add(productWiseStock1);
				productWiseStock1.setHead();
			}
		});
		btnBrandWiseStockReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame brandWiseStock = new JInternalFrame();
				dtp.add(brandWiseStock);
				brandWiseStock.setTitle("Brand Wise Stock Report :: Chittagong Computer Technology");
				brandWiseStock.setLocation(50,0);
				brandWiseStock.setSize(1250, 640);
				brandWiseStock.setVisible(true);
				brandWiseStock.setClosable(true);
				BrandWiseStockReport brandWiseStock1=new BrandWiseStockReport(sessionBeam);
				brandWiseStock.add(brandWiseStock1);
				brandWiseStock1.setHead();
			}
		});
		btnDailyIncome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame dailyIncomeprofit = new JInternalFrame();
				dtp.add(dailyIncomeprofit);
				dailyIncomeprofit.setTitle("Daily Income Profit :: Chittagong Computer Technology");
				dailyIncomeprofit.setLocation(50,0);
				dailyIncomeprofit.setSize(1250, 640);
				dailyIncomeprofit.setVisible(true);
				dailyIncomeprofit.setClosable(true);
				DailyIncome dailyIncomeprofit1=new DailyIncome(sessionBeam);
				dailyIncomeprofit.add(dailyIncomeprofit1);
				dailyIncomeprofit1.date_take();
				dailyIncomeprofit1.rowadd();
			}
		});
		btnDailySale.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame dailysale = new JInternalFrame();
				dtp.add(dailysale);
				dailysale.setTitle("Daily Sale  :: Chittagong Computer Technology");
				dailysale.setLocation(50,0);
				dailysale.setSize(1250, 640);
				dailysale.setVisible(true);
				dailysale.setClosable(true);
				SalesStatement dailysale1=new SalesStatement(sessionBeam);
				dailysale.add(dailysale1);
				dailysale1.date_take();
			}
		});
		btnDueReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame dueReport = new JInternalFrame();
				dtp.add(dueReport);
				dueReport.setTitle("Due Report  :: Chittagong Computer Technology");
				dueReport.setLocation(50,0);
				dueReport.setSize(1250, 640);
				dueReport.setVisible(true);
				dueReport.setClosable(true);
				DueReport dailysale1=new DueReport(sessionBeam);
				dueReport.add(dailysale1);
				dailysale1.hitMethod();
			}
		});
		btnBrandCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame brandCreate = new JInternalFrame();
				dtp.add(brandCreate);
				brandCreate.setTitle("Brand Create  :: Chittagong Computer Technology");
				brandCreate.setLocation(250,0);
				brandCreate.setSize(520,180);
				brandCreate.setVisible(true);
				brandCreate.setClosable(true);
				BrandCreate brandCreate1=new BrandCreate(sessionBeam);
				brandCreate.add(brandCreate1);
				brandCreate1.autoId();
			}
		});
		btnModelCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame modelCreate = new JInternalFrame();
				dtp.add(modelCreate);
				modelCreate.setTitle("Model Create  :: Chittagong Computer Technology");
				modelCreate.setLocation(250,0);
				modelCreate.setSize(520,200);
				modelCreate.setVisible(true);
				modelCreate.setClosable(true);
				ModelCreate modelCreate1=new ModelCreate(sessionBeam);
				modelCreate.add(modelCreate1);
				modelCreate1.autoId();
				modelCreate1.loadBrandName();
			}
		});
	btnPasswordChange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame changePassword = new JInternalFrame();
				dtp.add(changePassword);
				changePassword.setTitle("Change Password  :: Chittagong Computer Technology");
				changePassword.setLocation(480,220);
				changePassword.setSize(250,190);
				changePassword.setVisible(true);
				changePassword.setClosable(true);
				com.ShareClass.PasswordChange changePassword1=new com.ShareClass.PasswordChange(sessionBeam);
				changePassword.add(changePassword1);
			}
		});
	}
	public void background(){
		try {                
			image = ImageIO.read(new File("icon/bg.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
	}


	private void addCmp(){
		setOpaque(false);
		setLayout(new BorderLayout());
		add(panelNorth,BorderLayout.NORTH);
		panelNorth_work(" ");
		//add(panelScroll,BorderLayout.WEST);
		//panelScroll.setOpaque(false);
		//panelScroll.getVerticalScrollBar().setUnitIncrement(24);
		//panelWest_work();
		add(panelCenter,BorderLayout.CENTER);
		panelCenter_work();
	}

	private void panelNorth_work(String s) {
		panelNorth.setPreferredSize(new Dimension(230, 85));
		panelNorth.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(2, 191, 185)));
		//panelNorth.setBackground(new Color(2, 191, 185));
		panelNorth.setLayout(new BorderLayout());
		panelNorth.add(lblbg);
		//panelNorth.setBackground(new Color(2, 191, 185));
		lblbg.setPreferredSize(new Dimension(1360, 85));
		lblbg.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(0, 2, 0, 2);
		lblbg.add(btnBrandCreate, grid);
		
		grid.gridx=0;
		grid.gridy=1;
		lblbg.add(lblBrandCreate, grid);
		
		grid.gridx=1;
		grid.gridy=0;
		lblbg.add(btnModelCreate, grid);
		
		grid.gridx=1;
		grid.gridy=1;
		lblbg.add(lblModelCreate, grid);
		
		grid.gridx=2;
		grid.gridy=0;
		lblbg.add(btnItemCreate, grid);
		grid.gridx=2;
		grid.gridy=1;
		lblbg.add(lblItemCreate, grid);
		
		grid.gridx=3;
		grid.gridy=0;
		lblbg.add(btnPurchaseOrder, grid);
		grid.gridx=3;
		grid.gridy=1;
		lblbg.add(lblPurchaseOrder, grid);
		
		grid.gridx=4;
		grid.gridy=0;
		lblbg.add(btnPurchaseReturn, grid);
		grid.gridx=4;
		grid.gridy=1;
		lblbg.add(lblPurchaseReturn, grid);
		grid.gridx=5;
		grid.gridy=0;
		lblbg.add(btnSales_Return, grid);
		grid.gridx=5;
		grid.gridy=1;
		lblbg.add(lblSalesIReturn, grid);
		grid.gridx=6;
		grid.gridy=0;
		lblbg.add(btnWastage, grid);
		grid.gridx=6;
		grid.gridy=1;
		lblbg.add(lblWastage, grid);
		grid.gridx=7;
		grid.gridy=0;
		lblbg.add(btnProductWiseStockReport, grid);
		grid.gridx=7;
		grid.gridy=1;
		lblbg.add(lblProductWiseStockReport, grid);
		grid.gridx=8;
		grid.gridy=0;
		lblbg.add(btnBrandWiseStockReport, grid);
		grid.gridx=8;
		grid.gridy=1;
		lblbg.add(lblBrandWiseStockReport, grid);
		grid.gridx=9;
		grid.gridy=0;
		lblbg.add(btnDailyIncome, grid);
		grid.gridx=9;
		grid.gridy=1;
		lblbg.add(lblDailyIncome, grid);
		grid.gridx=10;
		grid.gridy=0;
		lblbg.add(btnDailySale, grid);
		grid.gridx=10;
		grid.gridy=1;
		lblbg.add(lblDailySale, grid);
		grid.gridx=11;
		grid.gridy=0;
		lblbg.add(btnDueReport, grid);
		grid.gridx=11;
		grid.gridy=1;
		lblbg.add(lblDueReport, grid);
		grid.gridx=12;
		grid.gridy=0;
		lblbg.add(btnPasswordChange, grid);
		grid.gridx=12;
		grid.gridy=1;
		lblbg.add(lblPasswordChange, grid);
		grid.gridx=13;
		grid.gridy=0;
		lblbg.add(btnLogout, grid);
		grid.gridx=13;
		grid.gridy=1;
		lblbg.add(lblLogout, grid);

		//panelNorth.add(new JButton("Item"));
		//panelNorth.add(new JButton("Item1"));

/*		flow.setAlignment(flow.LEFT);
		//panelNorth.add(lblWelcome);
		//panelNorth.add(lblUser);
		lblUser.setText(sessionBeam.getUserName());
		lblWelcome.setFont(new Font("arial", Font.BOLD, 13));
		lblUser.setFont(new Font("arial", Font.BOLD, 13));
		lblUser.setForeground(Color.red);
		lblUser.setPreferredSize(new Dimension(590, 20));
		panelNorth.add(lblClock);
		lblClock.setFont(new Font("arial", Font.BOLD, 23));
		lblClock.setPreferredSize(new Dimension(565, 18));
		Timer t = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Calendar now = Calendar.getInstance();
				int h = now.get(Calendar.HOUR_OF_DAY);
				int m = now.get(Calendar.MINUTE);
				int s = now.get(Calendar.SECOND);
				lblClock.setText("" + h + ":" + m + ":" + s);
			}
		});
		t.start();
		lblClock.setForeground(new Color(2, 191, 185));*/
	

	}
	private void checkAccessModule(){
		try {
/*			sessionBeam.PatientModule=false;
			sessionBeam.ManagementModule=false;
			sessionBeam.IndoorPharmacyModule=false;
			sessionBeam.OutdoorPharmacyModule=false;
			sessionBeam.RestaurantModule=false;
			sessionBeam.hospitalStockModule=false;
			sessionBeam.settingModule=false;
			sessionBeam.billingModule=false;
			sessionBeam.labModule=false;
			sessionBeam.accountsModule=false;
			sessionBeam.misModule=false;
			ResultSet rs=db.sta.executeQuery("select tblogindetails.moduleName from tblogindetails where tblogindetails.userId='"+sessionBeam.getUserId()+"'");
			while(rs.next()){
				if(rs.getString("moduleName").equalsIgnoreCase("PATIENT MODULE")){
					sessionBeam.PatientModule=true;
				}
				if(rs.getString("moduleName").equalsIgnoreCase("MANEGEMENT MODULE")){
					sessionBeam.ManagementModule=true;
				}
				if(rs.getString("moduleName").equalsIgnoreCase("INDOOR PHARMACY MODULE")){
					sessionBeam.IndoorPharmacyModule=true;
				}
				if(rs.getString("moduleName").equalsIgnoreCase("OUTDOOR PHARMACY MODULE")){
					sessionBeam.OutdoorPharmacyModule=true;
				}
				if(rs.getString("moduleName").equalsIgnoreCase("RESTAURANT MODULE")){
					sessionBeam.RestaurantModule=true;
				}
				if(rs.getString("moduleName").equalsIgnoreCase("HOSPITAL STOCK MODULE")){
					sessionBeam.hospitalStockModule=true;
				}
				if(rs.getString("moduleName").equalsIgnoreCase("SETTING MODULE")){
					sessionBeam.settingModule=true;
				}
				if(rs.getString("moduleName").equalsIgnoreCase("BILLING MODULE")){
					sessionBeam.billingModule=true;
				}
				if(rs.getString("moduleName").equalsIgnoreCase("LAB MODULE")){
					sessionBeam.labModule=true;
				}
				if(rs.getString("moduleName").equalsIgnoreCase("ACCOUNTS MODULE")){
					sessionBeam.accountsModule=true;
				}
				if(rs.getString("moduleName").equalsIgnoreCase("MIS MODULE")){
					sessionBeam.misModule=true;
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}

	private boolean checkAccessModuleItem(String module,String item){
		try {
			ResultSet rs=db.sta.executeQuery("select tbauthentication.Block from tbauthentication where  tbauthentication.moduleName='"+module+"' && tbauthentication.moduleItemName='"+item+"' && tbauthentication.userId='"+sessionBeam.getUserId()+"'");
			while(rs.next()){
				if(rs.getString("Block").equals("1")){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}

	private void panelCenter_work() {
		panelCenter.setPreferredSize(new Dimension(1100, 640));
		panelCenter.setBackground(Color.white);
		panelCenter.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 0, new Color(2, 191, 185)));
		panelCenter.setLayout(new BorderLayout());
		panelCenter.add(dtp);
		dtp.add(lblHome);
		//panelCenter.add(lblHome);
		//panelCenter.setOpaque(false);

	}

}
