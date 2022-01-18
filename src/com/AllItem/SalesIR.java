package com.AllItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.joda.time.DateMidnight;

import com.ShareClass.ButtonColumn;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class SalesIR extends JPanel{
	SessionBeam sessionbeam;
	db_coonection db=new db_coonection();
	JLabel lblstar=new JLabel("*");
	public JPanel mainPanel=new JPanel();
	JPanel mainNorthPanel=new JPanel();
	JPanel mainNorthWestPanel=new JPanel();
	JPanel mainNorthCenterPanel=new JPanel();
	JPanel mainNorthEastPanel=new JPanel();
	JPanel counterLayout=new JPanel();
	JPanel treeLayout=new JPanel();
	JPanel mainCenterPanel=new JPanel();
	JPanel mainSouthPanel=new JPanel();
	JPanel southToppanel=new JPanel();
	JPanel southTopEastpanel=new JPanel();
	JPanel southTopDown=new JPanel();

	JLabel lblPreviousDate=new JLabel(" Date");
	JLabel lblInNo=new JLabel("<html><font color=red>*</font>Invoice No</html>");
	JLabel lblStockQty=new JLabel("Stock Qty (Pcs)");
	JLabel lblCustomerName=new JLabel("<html><font color=red>*</font>Customer Name</html>");
	JLabel lblBrandName=new JLabel(" Brand Name");
	JLabel lblModelName=new JLabel(" Model Name");
	JLabel lblDate=new JLabel("<html><font color=red>*</font>Date</html>");
	JLabel lblDebit=new JLabel("Bank");
	JLabel lblQty=new JLabel("<html><font color=red>*</font>Qty</html>");
	JLabel lblSalesMan=new JLabel("Sales Man");
	JLabel lblTransportcost=new JLabel("Transport Cost");
	JTextField txtSalesMan=new JTextField(18);
	JTextField txtTransportCost=new JTextField(18);
	JLabel lblNote=new JLabel("Note");
	JLabel lblTotal=new JLabel("Total");
	JTextField txtStockQty=new JTextField(3);
	SuggestText cmbProductName=new SuggestText();
	SuggestText cmbBrandName=new  SuggestText();
	SuggestText cmbModelName=new  SuggestText();
	JTextField txtShortGenericName=new  JTextField(5);
	JTextField txtCustomerName=new  JTextField(16);
	JTextField txtTotalAmount=new JTextField(20);
	SuggestText cmbInvoiceNo=new SuggestText();
	SuggestText cmbRegNo=new SuggestText();
	JTextField txtNote=new JTextField(78);
	JDateChooser txtdate=new JDateChooser();
	JDateChooser txtPreviousdate=new JDateChooser();
	JLabel lblUnit=new JLabel("<html><font color=red>*</font>Unit</html>");
	JLabel lblQtyPerpack=new JLabel("Qty Per Pack");
	JLabel lblPricePerQty=new JLabel("Price Per Qty");




	JTextField txtQtyPerpack=new JTextField(4);
	JTextField txtPricePerQty=new JTextField(5);
	JTextField txtSoldQty=new JTextField(3);

	JLabel lblProductName=new JLabel("<html><font color=red>*</font>Product Name</html>");
	JLabel lblSoldQty=new JLabel("Sold Qty(Pcs)");
	JLabel lblDiscountPercent=new JLabel("Discount (%)");
	JTextField txtDiscountPercent=new JTextField(18);
	JLabel lblPatient=new JLabel("<html><font color=red>*</font>Customer Name</html>");
	JLabel lblChallanNo=new JLabel("Challan No");
	JLabel lblGrossAmount=new JLabel("Gross Amount");
	JLabel lblMamnualDiscount=new JLabel("Manual Discount");
	JLabel lblNetAmount=new JLabel("Net Amount");
	JLabel lblPaymentAmount=new JLabel("<html><font color=red>*</font>Amount</html>");
	JLabel lblPaymentType=new JLabel("Payment Type");



	JLabel lblDiscountAmount=new JLabel("0.0");

	String type[]={"Cash","Card"};
	JComboBox cmbType=new  JComboBox(type);

	String duetype[]={"Cash","Card"};
	JComboBox cmbDueType=new  JComboBox(duetype);

	JLabel lblDuePaymentType=new JLabel("Payment Type");
	JLabel lblDuePaymentAmount=new JLabel("Payment");
	JTextField txtDuePaymentAmount=new JTextField(6);

	JButton btnDueSubmit=new JButton(new ImageIcon("icon/save.png"));
	JButton btnPreviousInvoice=new JButton(new ImageIcon("icon/find.png"));

	JTextField txtChallanNo=new JTextField(18);
	JTextField txtGrossAmount=new JTextField(18);
	JTextField txtManualDiscount=new JTextField(18);
	JTextField txtNetAmount=new JTextField(18);
	JTextField txtPaymentAmount=new JTextField(16);

	String head[]={"","Revenue","Expense","Liability","Asset","Other"};
	JComboBox cmbHead=new JComboBox(head);
	JButton btnSubmit=new JButton("Submit",new ImageIcon("icon/save.png"));
	JButton btnSearch=new JButton(new ImageIcon("icon/search.png"));
	JButton btnConfrim=new JButton("Confrim",new ImageIcon("icon/save.png"));
	JButton btnReset=new JButton(new ImageIcon("icon/reresh.png"));
	JButton[] btnUpdate=new JButton[9];
	JLabel lblLedger= new JLabel("Ledger       ");
	JLabel lblProduct= new JLabel("Product Name       ");
	JLabel lblPrice= new JLabel("<html><font color=red>*</font>Price</html>");
	JTextField txtTableProductName=new JTextField(15);
	String ledgerName[] = {""};
	private JComboBox cmbLedgerName = new JComboBox(ledgerName);
	JTextField txtPrice=new JTextField(5);
	JTextField txtQty=new JTextField(3);
	GridBagConstraints grid=new GridBagConstraints();
	String col[]={" Auto ID","                         NAME","       QTY (Pcs)","       PRICE","        TOTAL PRICE","DEL"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table = new JTable(model){
		@Override
		public Component prepareRenderer 
		(TableCellRenderer renderer, int row, int column) 
		{ 
			Component c = super.prepareRenderer( renderer, row, column); 
			// We want renderer component to be 
			//transparent so background image is visible 
			if( c instanceof JComponent ) 
				((JComponent)c).setOpaque(false); 
			return c; 
		} 
		public boolean isCellEditable(int row,int col){
			
			if(col==2 || col==3 || col==5){
				return true;
			}
			return false;
		}
	};
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("icon/reresh.png"));

	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String transectionId="",jvno="",startDate="",invoiceid="",id="";
	double debit=0,credit=0;
	int i=0,search=0,update=0;
	int [] tranectionId = new int[100];
	String productId="",catagoryid="",autoId="",d_l_id="",c_l_id="";

	JButton btnCounter1=new JButton("C1");
	JButton btnCounter2=new JButton("C2");
	JButton btnCounter3=new JButton("C3");
	JCheckBox checkInvoice=new JCheckBox("Return (P)");
	int counter1=1,counter2=0,counter3=0,hold1=0,hold2,hold3,check=0;
	int pfind=0,findId=0;
	int psearchvalue[]=new int[100];
	int pfindvalue[]=new int[100];
	double returnPrice=0,InventoryPrice=0.0;

	String hcol[]={"Date","Cash","Card","Due"};
	Object hrow[][]={};
	DefaultTableModel hmodel=new DefaultTableModel(hrow,hcol);
	JTable htable=new JTable(hmodel){
		@Override
		public Component prepareRenderer 
		(TableCellRenderer renderer, int row, int column) 
		{ 
			Component c = super.prepareRenderer( renderer, row, column); 
			// We want renderer component to be 
			//transparent so background image is visible 
			if( c instanceof JComponent ) 
				((JComponent)c).setOpaque(false); 
			return c; 
		} 
		public boolean isCellEditable(int row,int col){
			
			return false;
		}
	};
	JScrollPane hScroll=new JScrollPane(htable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


	int totalqty=0;
	String FinaltransectionId="",f_d_l_id="",f_c_l_id="";
	DecimalFormat df = new DecimalFormat("#.00");
	int count=0;
	BufferedImage image;
	Double GrossAmount=0.0;
	public SalesIR(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addcmp();
		date_take();
		btnActionEvent();
		ptextEvent();
		loadBrandName();
		loadProductName();
		background();
		ModelActionEvent();
	}
	public void ModelActionEvent(){
		model.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType()==TableModelEvent.UPDATE){
					int col=e.getColumn();
					int row=e.getFirstRow();
					if(col==2 || col==3){
						try {
							if(table.getValueAt(table.getSelectedRow(), 2).toString()!="" && table.getValueAt(table.getSelectedRow(), 3).toString()!=""){
								double d=Double.parseDouble(table.getValueAt(row, 2).toString())*Double.parseDouble(table.getValueAt(row, 3).toString());
								table.setValueAt(d, row, 4);
								String sql="update tbpharmacystore set qty='"+table.getValueAt(row, 2)+"',buyPrice='"+table.getValueAt(row, 3)+"', totalPrice='"+table.getValueAt(row, 4)+"' where autoId='"+table.getValueAt(row, 0)+"'";
								System.out.println(sql);
								db.sta.executeUpdate(sql);
								GrossAmount();
								txtDiscountPercent.setText("0");
								txtManualDiscount.setText("0");
							}
						} catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
						}
					}
				}
			}
		});
	}
	public void HitWastage(){

		loaddRow();
		loadProductName();
		loadTableProductName();
		loadProductName();
		checkCounter();
		autoinvoiceId();
		showData();
		GrossAmount();
	}
	public void background(){
		try {                
			image = ImageIO.read(new File("icon/pattern.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
	}
	public void loadBrandName(){
		try {
			cmbBrandName.v.clear();
			cmbBrandName.v.add("");

			ResultSet rs=db.sta.executeQuery("select tbbrandinfo.brandName from tbbrandinfo");
			while(rs.next()){
				cmbBrandName.v.add(rs.getString("brandName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}

	public void loadTableProductName(){
		/*		try {
			cmbTableProductName.removeAllItems();
			cmbTableProductName.addItem("");

			ResultSet rs=db.sta.executeQuery("select productName from tbiteminformation");
			while(rs.next()){
				cmbTableProductName.addItem(rs.getString("productName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}*/
	}


	private void ptextEvent(){
		cmbInvoiceNo.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(!cmbInvoiceNo.txtMrNo.getText().trim().toString().isEmpty() && checkInvoice.isSelected()){
						ResultSet rs=db.sta.executeQuery("select tbinvoice.customer from tbinvoice where tbinvoice.invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"' && tbinvoice.type=3 && tbinvoice.selltype=1");
						while(rs.next()){
							txtCustomerName.setText(rs.getString("customer"));
						}
						cmbProductName.v.clear();
						cmbProductName.v.add("");
						ResultSet rs1=db.sta.executeQuery("select tbpharmacystore.productName from tbpharmacystore where tbpharmacystore.invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"' && tbpharmacystore.type=3 && tbpharmacystore.selltype=1");
						while(rs1.next()){
							cmbProductName.v.add(rs1.getString("productName"));
						}
					}
					if(!cmbInvoiceNo.txtMrNo.getText().trim().toString().isEmpty() && !checkInvoice.isSelected()){
						for(int a=table.getRowCount()-1;a>=0;a--){
							model.removeRow(a);
						}
						String sql="select * from tbpharmacystore where invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"' && type=3 && selltype=1";
						System.out.println(sql);
						ResultSet rs1=db.sta.executeQuery(sql);
						while(rs1.next()){
							model.addRow(new Object[]{rs1.getString("autoId"),rs1.getString("productName"),rs1.getString("qty"),rs1.getString("sellprice"),rs1.getString("totalPrice"),new ImageIcon("icon/delete.png")});
						}	
						for(int a=htable.getRowCount()-1;a>=0;a--){
							hmodel.removeRow(a);
						}
						loaddRow();
						ResultSet rs=db.sta.executeQuery("select tbinvoice.invoiceNo,"
								+ "tbinvoice.customer,"
								+ "tbinvoice.amount,"
								+ "tbinvoice.discountPer,"
								+ "tbinvoice.discountManual,"
								+ "tbinvoice.netAmount, "
								+ "tbinvoice.paid,"
								+ "tbinvoice.cash,"
								+ "tbinvoice.card,"
								+ "tbinvoice.date"
								+ " from tbinvoice where  tbinvoice.invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"' && type=3 && selltype=1");
						while(rs.next()){
							cmbInvoiceNo.txtMrNo.setText(rs.getString("invoiceNo"));
							txtCustomerName.setText(rs.getString("customer"));
							txtGrossAmount.setText(rs.getString("amount"));
							txtDiscountPercent.setText(rs.getString("discountPer"));
							txtManualDiscount.setText(rs.getString("discountManual"));
							txtNetAmount.setText(rs.getString("netAmount"));
							txtPaymentAmount.setText(rs.getString("paid"));
							txtdate.setDate((rs.getDate("date")));
							double due=Double.parseDouble(rs.getString("netAmount"))-Double.parseDouble(rs.getString("paid"));
							hmodel.addRow(new Object[]{rs.getDate("date"),rs.getString("cash"),rs.getString("card"),due});
							findId=1;
						}
						historyRow();
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
				}
				txtCustomerName.requestFocusInWindow();
			}
		});
		cmbBrandName.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					cmbProductName.txtMrNo.requestFocusInWindow();

				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtQty.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtPrice.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtDiscountPercent.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtManualDiscount.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtNetAmount.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtPaymentAmount.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		cmbBrandName.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				try {
					cmbBrandName.v.clear();
					cmbBrandName.v.add("");
					ResultSet rs=db.sta.executeQuery("select tbbrandinfo.brandName from tbbrandinfo");
					while(rs.next()){
						cmbBrandName.v.add(rs.getString("brandName"));
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					try {
						cmbModelName.v.clear();
						cmbModelName.v.add("");
						ResultSet rs=db.sta.executeQuery("select tbbrandwisedepartmentinfo.ModelName from tbbrandwisedepartmentinfo where tbbrandwisedepartmentinfo.brandid=(select tbbrandinfo.brandId from tbbrandinfo where tbbrandinfo.brandName='"+cmbBrandName.txtMrNo.getText().trim().toString()+"')");
						while(rs.next()){
							cmbModelName.v.add(rs.getString("ModelName"));
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}
					cmbModelName.txtMrNo.requestFocusInWindow();
				}
			}
		});
		cmbModelName.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub


			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					try {
						cmbProductName.v.clear();
						cmbProductName.v.add("");
						ResultSet rs=db.sta.executeQuery("select tbiteminformation.ProductName from tbiteminformation where tbiteminformation.ModelId=(select tbbrandwisedepartmentinfo.modelId from tbbrandwisedepartmentinfo where tbbrandwisedepartmentinfo.ModelName='"+cmbModelName.txtMrNo.getText().trim().toString()+"')");
						while(rs.next()){
							cmbProductName.v.add(rs.getString("ProductName"));
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}
					cmbProductName.txtMrNo.requestFocusInWindow();
				}
			}
		});
		cmbProductName.txtMrNo.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(checkInvoice.isSelected()){
						if(!cmbInvoiceNo.txtMrNo.getText().trim().toString().isEmpty() && cmbBrandName.txtMrNo.getText().trim().toString().isEmpty()){
							cmbProductName.v.clear();
							cmbProductName.v.add("");
							ResultSet rs=db.sta.executeQuery("select tbpharmacystore.productName from tbpharmacystore where tbpharmacystore.invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"' && tbpharmacystore.type=3 && tbpharmacystore.selltype=1");
							while(rs.next()){
								cmbProductName.v.add(rs.getString("productName"));
							}
						}
					}
					else{
						if(!cmbBrandName.txtMrNo.getText().trim().toString().isEmpty() && !cmbInvoiceNo.txtMrNo.getText().trim().toString().isEmpty()){
							cmbProductName.v.clear();
							cmbProductName.v.add("");
							ResultSet rs=db.sta.executeQuery("select tbiteminformation.ProductName from tbiteminformation where brandid=(select tbbrandinfo.brandId from tbbrandinfo where tbbrandinfo.brandName='"+cmbBrandName.txtMrNo.getText().trim().toString()+"') && tbiteminformation.ProductName like '"+cmbProductName.txtMrNo.getText().trim().toString()+"%' order by tbiteminformation.ProductName");
							while(rs.next()){
								cmbProductName.v.add(rs.getString("ProductName"));
							}
						}
						else{
							cmbProductName.v.clear();
							cmbProductName.v.add("");
							ResultSet rs=db.sta.executeQuery("select tbiteminformation.ProductName from tbiteminformation where tbiteminformation.ProductName like '"+cmbProductName.txtMrNo.getText().trim().toString()+"%' order by tbiteminformation.ProductName");
							while(rs.next()){
								cmbProductName.v.add(rs.getString("ProductName"));
							}
						}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		cmbProductName.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub


			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					txtQty.requestFocusInWindow();
				}
			}
		});

		cmbProductName.txtMrNo.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				try {
	
					int rqty=0,soldqty=0;
					ResultSet rs1=db.sta.executeQuery("select (select (select IFNULL(sum(tbpharmacystore.qty),0)) from tbpharmacystore where tbpharmacystore.returnInovice='"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"' && tbpharmacystore.type=4 ) as rqty,tbpharmacystore.productName,tbpharmacystore.qty from tbpharmacystore where tbpharmacystore.invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"' && tbpharmacystore.type=3 && tbpharmacystore.productName='"+cmbProductName.txtMrNo.getText().trim().toString()+"' ");
					while(rs1.next()){
						rqty=Integer.parseInt(rs1.getString("rqty"));
						soldqty=Integer.parseInt(rs1.getString("qty"));
					}
					txtSoldQty.setText(Integer.toString(soldqty-rqty));
					String query="select tbpharmacystore.productId," +
							"(select tbiteminformation.openingStock from tbiteminformation where tbiteminformation.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+cmbProductName.txtMrNo.getText().trim().toString()+"'))as opening," +
							"(select (select IFNULL(sum(tbpharmacystore.qty),0)) from tbpharmacystore where type=1 && tbpharmacystore.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+cmbProductName.txtMrNo.getText().trim().toString()+"'))as purchase," +
							"(select (select IFNULL(sum(tbpharmacystore.qty),0)) from tbpharmacystore where type=2 && tbpharmacystore.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+cmbProductName.txtMrNo.getText().trim().toString()+"'))as purchasereturn," +
							"(select (select IFNULL(sum(tbpharmacystore.qty),0)) from tbpharmacystore where type=3 && tbpharmacystore.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+cmbProductName.txtMrNo.getText().trim().toString()+"'))as sales," +
							"(select (select IFNULL(sum(tbpharmacystore.qty),0)) from tbpharmacystore where type=4 && tbpharmacystore.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+cmbProductName.txtMrNo.getText().trim().toString()+"'))as salesreturn," +
							"(select (select IFNULL(sum(tbpharmacystore.qty),0)) from tbpharmacystore where type=5 && tbpharmacystore.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+cmbProductName.txtMrNo.getText().trim().toString()+"')) as wastage" +
							" from tbpharmacystore where tbpharmacystore.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+cmbProductName.txtMrNo.getText().trim().toString()+"') group by tbpharmacystore.productId";
					System.out.println(query);
					ResultSet rs2=db.sta.executeQuery(query);
					int stock=0;
					int temp=0;
					while(rs2.next()){
						stock=stock+Integer.parseInt(rs2.getString("opening"))+Integer.parseInt(rs2.getString("purchase"))-(Integer.parseInt(rs2.getString("purchasereturn"))+Integer.parseInt(rs2.getString("wastage")))-(Integer.parseInt(rs2.getString("sales"))-Integer.parseInt(rs2.getString("salesreturn")));
						temp=1;
					}
					if(temp==0){
						ResultSet rs3=db.sta.executeQuery("select tbiteminformation.openingStock from tbiteminformation where tbiteminformation.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+cmbProductName.txtMrNo.getText().trim().toString()+"')");
						while(rs3.next()){
							txtStockQty.setText(rs3.getString("openingStock"));
						}
					}
					else{
						txtStockQty.setText(Integer.toString(stock));
					}

					//txtQty.requestFocusInWindow();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtQty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					double price=0.0;
					int perqty=0;
					ResultSet rs=db.sta.executeQuery("select tbiteminformation.sellPrice from tbiteminformation where tbiteminformation.ProductName='"+cmbProductName.txtMrNo.getText().trim().toString()+"'");
					while(rs.next()){
						price=Double.parseDouble(rs.getString("sellPrice"));
					}
					totalqty=Integer.parseInt(txtQty.getText().trim().toString());
					long totalprice=(long) (totalqty*price);
					txtPrice.setText(Double.toString(price));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e);
				}
				txtPrice.requestFocusInWindow();
			}
		});
		txtCustomerName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cmbProductName.txtMrNo.requestFocusInWindow();
			}
		});
		txtPrice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSubmitEvent();
				cmbProductName.txtMrNo.requestFocusInWindow();
			}
		});
		txtChallanNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtGrossAmount.requestFocusInWindow();
			}
		});
		txtGrossAmount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtDiscountPercent.requestFocusInWindow();
			}
		});
		txtDiscountPercent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtManualDiscount.requestFocusInWindow();
			}
		});
		txtManualDiscount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNetAmount.requestFocusInWindow();
			}
		});
		txtNetAmount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtPaymentAmount.requestFocusInWindow();
			}
		});
		txtPaymentAmount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNote.requestFocusInWindow();
			}
		});
		txtNote.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConfrimBtnEvent();
			}
		});


		checkInvoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkInvoice.isSelected()){
					cmbInvoiceNo.txtMrNo.setText("");
					txtGrossAmount.setEditable(true);
					txtNetAmount.setEditable(true);
					check=1;
					autoinvoiceId();
				}
				else{
					txtGrossAmount.setEditable(true);
					txtNetAmount.setEditable(true);
					autoinvoiceId();
					check=0;
				}
			}
		});

	}
	public void checkCounter(){
		try {
			hold1=0;
			hold2=0;
			hold3=0;
			ResultSet rs=db.sta.executeQuery("select counter from tbpharmacystore where type='3'and invoiceNo IS NULL group by counter");
			while(rs.next()){
				if(rs.getString("counter").toString().equals("1")){
					btnCounter1.setBackground(Color.red);
					System.out.println("as");
					hold1=1;
				}
				if(rs.getString("counter").toString().equals("2")){
					btnCounter2.setBackground(Color.red);
					hold2=1;
				}
				if(rs.getString("counter").toString().equals("3")){
					btnCounter3.setBackground(Color.red);
					hold3=1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void Returnproduct(){
		if(!cmbProductName.txtMrNo.getText().toString().isEmpty()){
			if(!txtQty.getText().toString().isEmpty()){
				if(!cmbInvoiceNo.txtMrNo.getText().toString().isEmpty()){
					try {
						int temp=0,quantity=0;
						double sellprice=0;
						String sql="select productName,productId,qty,sellPrice,discount,invoiceNo from tbpharmacystore where invoiceNo ='"+cmbInvoiceNo.txtMrNo.getText().toString()+"' && type=3";
						System.out.println(sql);
						ResultSet rs=db.sta.executeQuery(sql);
						while(rs.next()){
							if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
								//quantity=Integer.parseInt(rs.getString("qty"));
								temp=1;
								break;
							}
						}
						if(temp==1)
						{
								if(Integer.parseInt(txtSoldQty.getText().trim().toString())>=totalqty){
									checkCounter();
									if(counter1==1 ){
										if(!doplicateName()){
											autoId();
											autoinvoiceId();
											productDescription();
											double price=0.0,totalPrice=0.0;
											int qty=0;
											qty=Integer.parseInt(txtQty.getText().trim().toString());
											price=Double.parseDouble(txtPrice.getText().trim().toString());
											totalPrice=totalqty*price;
											String sql1="insert into tbpharmacystore (autoId,productId,productName,unit,catagoryId,qty,sellPrice,totalPrice,type,selltype,counter,returnInovice,date,entryTime,createBy) values" +
													"('"+autoId+"'," +
													"'"+productId+"'," +
													"'"+cmbProductName.txtMrNo.getText().toString()+"'," +
													"'Pcs'," +
													"'"+catagoryid+"'," +
													"'"+totalqty+"'," +
													"'"+price+"'," +
													"'"+df.format(totalPrice)+"'," +
													"'"+4+"'," +
													"'"+1+"'," +
													"'"+1+"'," +
													"'"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"'," +
													"'"+startDate+"'," +
													"'"+startDate+"'," +
													"'"+sessionbeam.getUserId()+"')";
											System.out.println(sql1);
											db.sta.executeUpdate(sql1);
											JOptionPane.showMessageDialog(null, "Sales Product Return Successfully Accept");
											autoId();
											showData();
											GrossAmount();
											checkCounter();
											ProudctClear();

										}
										else{
											double price=0.0,totalPrice=0.0;
											int qty=0;
											qty=Integer.parseInt(txtQty.getText().trim().toString());
											price=Double.parseDouble(txtPrice.getText().trim().toString());
											String rInvice="";
											ResultSet rs1=db.sta.executeQuery("select returnInovice,unitqty,qty from tbpharmacystore where productName='"+cmbProductName.txtMrNo.getText().toString()+"' && type='4' && invoiceNo IS NULL");
											while(rs1.next()){
												rInvice=rs1.getString("returnInovice");
												totalqty=totalqty+Integer.parseInt(rs1.getString("qty"));
												qty=qty+Integer.parseInt(rs1.getString("unitqty"));
											}
											totalPrice=totalqty*price;
											String query="update tbpharmacystore set unitqty='"+qty+"',qty='"+totalqty+"',sellPrice='"+price+"',totalPrice='"+df.format(totalPrice)+"' where productName='"+cmbProductName.txtMrNo.getText().toString()+"' && type='4' && counter=1 && invoiceNo IS NULL ";
											System.out.println(query);
											db.sta.executeUpdate(query);
											JOptionPane.showMessageDialog(null, "Sales Return Successfully Accept");
											autoId();
											showData();
											GrossAmount();
											checkCounter();
											ProudctClear();

										}
										findId=0;
									}
								}
								else{
									JOptionPane.showMessageDialog(null, "Sorry Return Proudct Qty Can't Be More Than Sell Qty");
								}
						}
						else if(temp==0){
							JOptionPane.showMessageDialog(null, "Sorry!!,Sorry This is Not Valid Invoice");
						}
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Please Provide Invoice No");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Please Provide Product Quantity");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Please Select Product Name");
		}
	}
	private void btnActionEvent(){
		btnPreviousInvoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					String sql="select tbinvoice.invoiceNo from tbinvoice where type=3 && tbinvoice.selltype=1 && tbinvoice.date='"+new SimpleDateFormat("yyyy-MM-dd").format(txtPreviousdate.getDate())+"'";
					System.out.println(sql);
					ResultSet rs=db.sta.executeQuery(sql);
					cmbInvoiceNo.v.clear();
					cmbInvoiceNo.v.add("");
					while(rs.next()){
						cmbInvoiceNo.v.add(rs.getString("invoiceNo"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
				}
			}
		});
		btnCounter1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				counter1=1;
				counter2=0;
				counter3=0;
				showData();
				GrossAmount();
			}
		});
		btnCounter2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				counter2=2;
				counter1=0;
				counter3=0;
				showData();
				GrossAmount();
			}
		});
		btnCounter3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				counter3=3;
				counter1=0;
				counter2=0;
				showData();
				GrossAmount();

			}
		});
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSubmitEvent();
				cmbProductName.txtMrNo.requestFocusInWindow();
			}
		});
		btnConfrim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ConfrimBtnEvent();
			}
		});
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				search=0;
				autoInvoice();
				cmbLedgerName.setSelectedItem("");
				txtPrice.setText("");
				txtQty.setText("");
				for(int a=table.getRowCount()-1;a>=0;a--){
					model.removeRow(a);
				}
				loaddRow();
				checkInvoice.setSelected(false);
			}
		});
		btnDueSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnDueSubmitEvent();
			}
		});
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				autoInvoice();
				autoId();
				try {
					db.sta.executeUpdate("delete from tbpharmacystore where invoiceNo IS NULL");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				cmbBrandName.txtMrNo.setText("");
				cmbModelName.txtMrNo.setText("");
				txtCustomerName.setText("");
				txtSoldQty.setText("");
				txtStockQty.setText("");
				cmbProductName.txtMrNo.setText("");
				txtShortGenericName.setText("");
				txtPrice.setText("");
				txtQty.setText("");
				for(int a=table.getRowCount()-1;a>=0;a--){
					model.removeRow(a);
				}
				loaddRow();
				txtGrossAmount.setText("0");
				txtDiscountPercent.setText("0");
				txtManualDiscount.setText("0");
				txtNetAmount.setText("0");
				txtPaymentAmount.setText("0");
				checkInvoice.setSelected(false);
				findId=0;
			}
		});
		txtDiscountPercent.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if(!txtDiscountPercent.getText().toString().isEmpty()){
					if(!txtManualDiscount.getText().toString().isEmpty()){
						grid.gridx=2;
						grid.gridy=1;
						southTopEastpanel.add(lblDiscountAmount,grid);
						double amount=Double.parseDouble(txtGrossAmount.getText().toString())*Integer.parseInt(txtDiscountPercent.getText().toString())/100;
						lblDiscountAmount.setText(Double.toString(amount));
						lblDiscountAmount.setForeground(new Color(183,24,7));
						double tamount=Double.parseDouble(txtGrossAmount.getText().toString())-amount-Double.parseDouble(txtManualDiscount.getText().toString());						txtNetAmount.setText(Double.toString(tamount));
						txtNetAmount.setText(Double.toString(tamount));
						txtPaymentAmount.setText(Double.toString(0));
					}
					else{
						grid.gridx=2;
						grid.gridy=1;
						southTopEastpanel.add(lblDiscountAmount,grid);
						double amount=Double.parseDouble(txtGrossAmount.getText().toString())*Integer.parseInt(txtDiscountPercent.getText().toString())/100;
						lblDiscountAmount.setText(Double.toString(amount));
						lblDiscountAmount.setForeground(new Color(183,24,7));
						double tamount=Double.parseDouble(txtGrossAmount.getText().toString())-amount-0;
						txtNetAmount.setText(Double.toString(tamount));
						txtPaymentAmount.setText(Double.toString(0));
					}
				}
				else{
					txtDiscountPercent.setText("0");
					lblDiscountAmount.setText("0.0");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {

			}
		});
		txtManualDiscount.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if(!txtDiscountPercent.getText().toString().isEmpty()){
					if(!txtManualDiscount.getText().toString().isEmpty()){
						grid.gridx=2;
						grid.gridy=1;
						southTopEastpanel.add(lblDiscountAmount,grid);
						double amount=Double.parseDouble(txtGrossAmount.getText().toString())*Integer.parseInt(txtDiscountPercent.getText().toString())/100;
						lblDiscountAmount.setText(Double.toString(amount));
						lblDiscountAmount.setForeground(new Color(183,24,7));
						double tamount=Double.parseDouble(txtGrossAmount.getText().toString())-amount-Double.parseDouble(txtManualDiscount.getText().toString());
						txtNetAmount.setText(Double.toString(tamount));
						txtPaymentAmount.setText(Double.toString(0));
					}
					else{
						grid.gridx=2;
						grid.gridy=1;
						southTopEastpanel.add(lblDiscountAmount,grid);
						double amount=Double.parseDouble(txtGrossAmount.getText().toString())*Integer.parseInt(txtDiscountPercent.getText().toString())/100;
						lblDiscountAmount.setText(Double.toString(amount));
						lblDiscountAmount.setForeground(new Color(183,24,7));
						double tamount=Double.parseDouble(txtGrossAmount.getText().toString())-amount-0;
						txtNetAmount.setText(Double.toString(tamount));
						txtPaymentAmount.setText(Double.toString(0));
					}
				}
				else{
					lblDiscountAmount.setText("0.0");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {

			}
		});
	}

	private void btnDueSubmitEvent() {
		try {
			date_take();

			if(!txtDuePaymentAmount.getText().trim().toString().isEmpty()){
				double cashAmount=0.0,cardAmount=0.0;
				if(cmbDueType.getSelectedIndex()==0){
					cashAmount=Double.parseDouble(txtDuePaymentAmount.getText().trim().toString());
					cardAmount=0.0;
				}
				else{
					cardAmount=Double.parseDouble(txtDuePaymentAmount.getText().trim().toString());
					cashAmount=0.0;
				}
				double paid=cashAmount+cardAmount;
				String sql="update tbinvoice set cash=cash+'"+cashAmount+"',card=card+'"+cardAmount+"',paid=paid+'"+paid+"', date=NOW(),entryTime=NOW(),edit='"+sessionbeam.getUserId()+"' where invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"' && type=3 && selltype=1";
				System.out.println(sql);
				db.sta.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Due Payment Withdraw Successfully!!");
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Due Payment amount");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void ClearAfterConfrim(){
		cmbBrandName.txtMrNo.setText("");
		txtShortGenericName.setText("");
		txtCustomerName.setText("");
		cmbProductName.txtMrNo.setText("");
		txtPrice.setText("");
		txtQty.setText("");
		txtGrossAmount.setText("0");
		txtDiscountPercent.setText("0");
		txtManualDiscount.setText("0");
		txtNetAmount.setText("0");
		lblDiscountAmount.setText("0");
		autoInvoice();
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
		loaddRow();
	}	
	private void ConfrimBtnEvent() {
		if(check==1){
			if(!cmbInvoiceNo.txtMrNo.getText().toString().trim().isEmpty()){
				if(!txtCustomerName.getText().toString().trim().isEmpty()){
					try {
						int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure to Sell This Product","Confrim...",JOptionPane.YES_NO_OPTION);
						if(confrim==JOptionPane.YES_OPTION){
							autoinvoiceId();
							String ledgerId="";
							autoinvoiceId();
							String sql="insert into tbinvoice (invoiceNo,customer,type,selltype,amount,netAmount,date,entryTime,createBy) " +
									"values('"+invoiceid+"'," +
									"'"+txtCustomerName.getText().toString()+"'," +
									"'"+4+"',"+
									"'"+1+"',"+
									"'"+txtGrossAmount.getText().trim().toString()+"'," +
									"'"+txtNetAmount.getText().toString()+"',"+
									"'"+startDate+"'," +
									"'"+startDate+"'," +
									"'"+sessionbeam.getUserId()+"')";
							System.out.println(sql);
							db.sta.executeUpdate(sql);
							
							
							String sql1="update tbpharmacystore set invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().toString()+"' where type=4 && invoiceNo IS NULL";
							db.sta.executeUpdate(sql1);
							
							double tDiscount=Double.parseDouble(lblDiscountAmount.getText().toString())+Double.parseDouble(txtManualDiscount.getText().toString());
							double CashAmount=0.0,CardAmount=0.0;
							if(cmbType.getSelectedIndex()==0){
								CashAmount=Double.parseDouble(txtPaymentAmount.getText().trim().toString());
								CardAmount=0;
							}
							else{
								CardAmount=Double.parseDouble(txtPaymentAmount.getText().trim().toString());
								CashAmount=0;
							}

							int confrim1=JOptionPane.showConfirmDialog(null, "Are You Want View Sells Report", "Confrim", JOptionPane.YES_NO_OPTION);
							if(confrim1==JOptionPane.YES_OPTION){
								String report="Sales/Invoicesalesreport.jrxml";
								JasperDesign jd=JRXmlLoader.load(report);
								JRDesignQuery jq=new JRDesignQuery();
								String query1="select (select tbcompanyinfo.CompanyName from tbcompanyinfo)as company,"
										+ "(select tbcompanyinfo.address from tbcompanyinfo)as address,"
										+ "(select tbcompanyinfo.Mobile from tbcompanyinfo)as cmobile,"
										+ "(select tbcompanyinfo.email from tbcompanyinfo)as email,"
										+ "tbpharmacystore.invoiceNo,"
										+ "tbpharmacystore.productId,"
										+ "tbpharmacystore.productName,"
										+ "tbpharmacystore.unit,"
										+ "tbpharmacystore.qty,"
										+ "tbpharmacystore.sellPrice,"
										+ "tbpharmacystore.totalPrice,"
										+ "tbpharmacystore.`type`,"
										+ "tbinvoice.Customer,"
										+ "tbinvoice.Mobile,"
										+ "tbinvoice.date,"
										+ "tbinvoice.amount,"
										+ "tbinvoice.discount,"
										+ "tbinvoice.netAmount,"
										+ "tbinvoice.transportCost,"
										+ "tbinvoice.cash,tbinvoice.card, "
										+ "(select (select IFNULL(sum(tbpharmacystore.totalPrice),0)) from "
										+ "tbpharmacystore where tbpharmacystore.type=4 and tbpharmacystore.invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().toString()+"')as returnamount,"
										+ " (select username from tblogin where user_id=tbinvoice.createBy)as user"
										+ " from tbinvoice join tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo"
										+ "  where tbinvoice.type=4 && tbinvoice.invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().toString()+"' && tbpharmacystore.`type`=4  ";
								jq.setText(query1);
								jd.setQuery(jq);
								JasperReport jr=JasperCompileManager.compileReport(jd);
								JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
								JasperViewer.viewReport(jp, false);
							}
							checkInvoice.setSelected(false);
							showData();
							txtClear();
						}

					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Customer Name");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Invoice No");
			}
		}
		else{
				if(!txtCustomerName.getText().toString().isEmpty()){
					if(txtdate.getDate()!=null){
							try {
								String mobile="",customerId="",cledgerId="";
								double tDiscount=0,transportCost;
								double paid=0;
								if(txtTransportCost.getText().toString().isEmpty()){
									transportCost=0;
								}
								else{
									transportCost=Double.parseDouble(txtTransportCost.getText().toString());
								}
								double grossAmount=Double.parseDouble(txtGrossAmount.getText().trim().toString());
								double netAmount=grossAmount-(grossAmount*Integer.parseInt(txtDiscountPercent.getText().trim().toString())/100);
								System.out.println("net"+netAmount);
								paid=Double.parseDouble(txtPaymentAmount.getText().toString());
								tDiscount=Double.parseDouble(lblDiscountAmount.getText().toString())+Double.parseDouble(txtManualDiscount.getText().toString());
								if(findId==1){
									int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure to Sell This Product","Confrim...",JOptionPane.YES_NO_OPTION);
									if(confrim==JOptionPane.YES_OPTION){
										String ledgerId="";
										double CashAmount=0.0,CardAmount=0.0;
										if(cmbType.getSelectedIndex()==0){
											CashAmount=Double.parseDouble(txtPaymentAmount.getText().trim().toString());
											CardAmount=0;
										}
										else{
											CardAmount=Double.parseDouble(txtPaymentAmount.getText().trim().toString());
											CashAmount=0;
										}
										String sql="update tbinvoice set amount='"+txtGrossAmount.getText().toString()+"',netAmount='"+txtNetAmount.getText().toString()+"',discountPer='"+txtDiscountPercent.getText().toString()+"',discountManual='"+txtManualDiscount.getText().toString()+"',discount='"+tDiscount+"',paid='"+paid+"',cash='"+CashAmount+"',card='"+CardAmount+"',date='"+new SimpleDateFormat("yyyy-MM-dd").format(txtdate.getDate())+"',entryTime='"+startDate+"',edit='"+sessionbeam.getUserId()+"' where invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().toString()+"' && type=3 && selltype=1";
										System.out.println(sql);
										db.sta.executeUpdate(sql);
										for(int i=0;i<table.getRowCount();i++){
											if(table.getValueAt(i, 1).toString()!=""){
												double discount=tDiscount*Double.parseDouble(table.getValueAt(i, 4).toString())/Double.parseDouble(txtGrossAmount.getText().toString());
												System.out.println("discount"+discount);
												System.out.println("tDiscount"+tDiscount);
												String query="update tbpharmacystore set discount='"+discount+"',invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().toString()+"',date='"+new SimpleDateFormat("yyyy-MM-dd").format(txtdate.getDate())+"',status='1',entryTime='"+startDate+"',edit='"+sessionbeam.getUserId()+"' where  counter='1' && autoId='"+table.getValueAt(i, 0).toString()+"' and invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().toString()+"' && type=3 && selltype=1";
												System.out.println(query);
												db.sta.executeUpdate(query);
											}
										}
										
										int confrim1=JOptionPane.showConfirmDialog(null, "Are You Want View Sells Report", "Confrim", JOptionPane.YES_NO_OPTION);
										if(confrim1==JOptionPane.YES_OPTION){
											String report="Sales/Invoicesalesreport.jrxml";
											JasperDesign jd=JRXmlLoader.load(report);
											JRDesignQuery jq=new JRDesignQuery();
											String view="select (select tbcompanyinfo.CompanyName from tbcompanyinfo)as company,"
													+ "(select tbcompanyinfo.address from tbcompanyinfo)as address,"
													+ "(select tbcompanyinfo.Mobile from tbcompanyinfo)as cmobile,"
													+ "(select tbcompanyinfo.email from tbcompanyinfo)as email,"
													+ "tbpharmacystore.invoiceNo,"
													+ "tbpharmacystore.productId,"
													+ "tbpharmacystore.productName,"
													+ "tbpharmacystore.unit,"
													+ "tbpharmacystore.qty,"
													+ "tbpharmacystore.sellPrice,"
													+ "tbpharmacystore.totalPrice,"
													+ "tbpharmacystore.`type`,"
													+ "tbinvoice.customer,"
													+ "tbinvoice.Mobile,"
													+ "tbinvoice.date,"
													+ "tbinvoice.amount,"
													+ "tbinvoice.discount,"
													+ "tbinvoice.netAmount,"
													+ "tbinvoice.transportCost,"
													+ "tbinvoice.cash,tbinvoice.card, "
													+ "(select (select IFNULL(sum(tbpharmacystore.totalPrice),0)) from "
													+ "tbpharmacystore where tbpharmacystore.type=4 and tbpharmacystore.invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().toString()+"')as returnamount,"
													+ " (select username from tblogin where user_id=tbinvoice.createBy)as user"
													+ " from tbinvoice join tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo"
													+ "  where tbinvoice.type=3 && tbinvoice.selltype=1 && tbinvoice.invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().toString()+"' && tbpharmacystore.`type`=3 && tbpharmacystore.selltype=1  ";
											System.out.println(view);
											jq.setText(view);
											jd.setQuery(jq);
											JasperReport jr=JasperCompileManager.compileReport(jd);
											JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
											JasperViewer.viewReport(jp, false);
										}
										checkCounter();
										showData();
										autoinvoiceId();
										autoId();
										ClearAfterConfrim();
										findId=0;
									}

								}
								else{
									int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure to Sell This Product","Confrim...",JOptionPane.YES_NO_OPTION);
									if(confrim==JOptionPane.YES_OPTION){
										double CashAmount=0.0,CardAmount=0.0;
										if(cmbType.getSelectedIndex()==0){
											CashAmount=Double.parseDouble(txtPaymentAmount.getText().trim().toString());
											CardAmount=0;
										}
										else{
											CardAmount=Double.parseDouble(txtPaymentAmount.getText().trim().toString());
											CashAmount=0;
										}
										autoinvoiceId();
										String sql="insert into tbinvoice (invoiceNo,Customer,type,selltype,salesMan,amount,netAmount,discountPer,discountManual,discount,paid,cash,card,p_type,transportCost,date,entryTime,createBy) " +
												"values('"+invoiceid+"'," +
												"'"+txtCustomerName.getText().toString()+"'," +
												"'"+3+"',"
												+"'"+1+"',"
												+"'"+txtSalesMan.getText().toString()+"',"
												+ "'"+txtGrossAmount.getText().trim().toString()+"'," +
												"'"+txtNetAmount.getText().trim().toString()+"',"
												+ "'"+txtDiscountPercent.getText().trim().toString()+"'," +
												"'"+txtManualDiscount.getText().trim().toString()+"'," +
												"'"+tDiscount+"'," +
												"'"+paid+"'," +
												"'"+CashAmount+"'," +
												"'"+CardAmount+"'," +
												"'"+cmbType.getSelectedItem().toString()+"'," +
												"'"+transportCost+"'," +
												"NOW()," +
												"NOW()," +
												"'"+sessionbeam.getUserId()+"')";
										System.out.println(sql);
										db.sta.executeUpdate(sql);
										for(int i=0;i<table.getRowCount();i++){
											if(table.getValueAt(i, 1).toString()!=""){
												double discount=tDiscount*Double.parseDouble(table.getValueAt(i, 4).toString())/Double.parseDouble(txtGrossAmount.getText().toString());
												System.out.println("discount"+discount);
												System.out.println("tDiscount"+tDiscount);
												String query="update tbpharmacystore set discount='"+discount+"',status='1',invoiceNo='"+invoiceid+"',date='"+new SimpleDateFormat("yyyy-MM-dd").format(txtdate.getDate())+"',entryTime=NOW(),createBy='"+sessionbeam.getUserId()+"' where  counter='1' && autoId='"+table.getValueAt(i, 0).toString()+"' and invoiceNo IS NULL";
												System.out.println(query);
												db.sta.executeUpdate(query);
											}
										}
										int confrim1=JOptionPane.showConfirmDialog(null, "Are You Want View Sells Report", "Confrim", JOptionPane.YES_NO_OPTION);
										if(confrim1==JOptionPane.YES_OPTION){
											String report="Sales/Invoicesalesreport.jrxml";
											JasperDesign jd=JRXmlLoader.load(report);
											JRDesignQuery jq=new JRDesignQuery();
											String view="select (select tbcompanyinfo.CompanyName from tbcompanyinfo)as company,"
													+ "(select tbcompanyinfo.address from tbcompanyinfo)as address,"
													+ "(select tbcompanyinfo.Mobile from tbcompanyinfo)as cmobile,"
													+ "(select tbcompanyinfo.email from tbcompanyinfo)as email,"
													+ "tbpharmacystore.invoiceNo,"
													+ "tbpharmacystore.productId,"
													+ "tbpharmacystore.productName,"
													+ "tbpharmacystore.unit,"
													+ "tbpharmacystore.qty,"
													+ "tbpharmacystore.sellPrice,"
													+ "tbpharmacystore.totalPrice,"
													+ "tbpharmacystore.`type`,"
													+ "tbinvoice.customer,"
													+ "tbinvoice.Mobile,"
													+ "tbinvoice.date,"
													+ "tbinvoice.amount,"
													+ "tbinvoice.discount,"
													+ "tbinvoice.netAmount,"
													+ "tbinvoice.transportCost,"
													+ "tbinvoice.cash,tbinvoice.card, "
													+ "(select (select IFNULL(sum(tbpharmacystore.totalPrice),0)) from "
													+ "tbpharmacystore where tbpharmacystore.type=4 and tbpharmacystore.invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().toString()+"')as returnamount,"
													+ " (select username from tblogin where user_id=tbinvoice.createBy)as user"
													+ " from tbinvoice join tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo"
													+ "  where tbinvoice.type=3 && tbinvoice.selltype=1 && tbinvoice.invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().toString()+"' && tbpharmacystore.`type`=3 && tbpharmacystore.selltype=1  ";
											System.out.println(view);
											jq.setText(view);
											jd.setQuery(jq);
											JasperReport jr=JasperCompileManager.compileReport(jd);
											JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
											JasperViewer.viewReport(jp, false);
										}
										checkCounter();
										showData();
										autoinvoiceId();
										autoId();
										btnCounter1.setBackground(new Color(2, 191, 185));
										checkCounter();
										ClearAfterConfrim();
										//JOptionPane.showMessageDialog(null, "Sales Transection Complete!!");

									}
									
								}
							} catch (Exception e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, "Error"+e);
							}
					}
					else {
						JOptionPane.showMessageDialog(null, "Please Provide Order Date");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Provide Customer Name");
				}

		}
	}

	private void UpdateInvoieValue(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *from tbpharmacystore where invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().toString()+"' && type=3 && selltype=1");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("autoId"),rs.getString("productName"),rs.getString("qty"),rs.getString("sellprice"),rs.getString("totalPrice"),new ImageIcon("icon/delete.png")});
			}
			loaddRow();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void btnSubmitEvent() {
		if(!txtCustomerName.getText().toString().isEmpty()){
			if(!cmbProductName.txtMrNo.getText().toString().isEmpty()){
					if(!checkInvoice.isSelected()){
						if(Integer.parseInt(txtStockQty.getText().trim().toString())>0){
							if(totalqty<=Integer.parseInt(txtStockQty.getText().trim().toString())){
								salesProudct();
							}
							else{
								JOptionPane.showMessageDialog(null, "Warning!!,Stock is insufficient for sell!!");
								ProudctClear();
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Stock is insufficient for sell!!");
							ProudctClear();
						}
					}
					else{
						salesProudct();
					}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Product Name");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Customer Name");
		}
	}
	public void salesProudct(){
		try {
			if(check==1){
				Returnproduct();
				//JOptionPane.showMessageDialog(null, "r");
			}
			else{

						if(findId==1){
							if(!updoplicateName()){
								autoId();
								productDescription();
								double price=0.0,totalPrice=0.0;
								int qty=0;
								qty=Integer.parseInt(txtQty.getText().trim().toString());
								price=Double.parseDouble(txtPrice.getText().trim().toString());
								totalPrice=totalqty*price;
								String sql="insert into tbpharmacystore (autoId,productId,productName,unit,catagoryId,qty,sellPrice,totalPrice,type,selltype,counter,date,invoiceNo,EntryTime,createBy) values" +
										"('"+autoId+"'," +
										"'"+productId+"'," +
										"'"+cmbProductName.txtMrNo.getText().toString()+"'," +
										"'Pcs'," +
										"'"+catagoryid+"'," +
										"'"+totalqty+"'," +
										"'"+price+"'," +
										"'"+df.format(totalPrice)+"'," +
										"'"+3+"'," +
										"'"+1+"'," +
										"'"+1+"'," +
										"'"+startDate+"'," +
										"'"+cmbInvoiceNo.txtMrNo.getText().toString()+"'," +
										"'"+startDate+"'," +
										"'"+sessionbeam.getUserId()+"')";
								System.out.println(sql);
								db.sta.executeUpdate(sql);
								JOptionPane.showMessageDialog(null, "Sales Product Successfully Accept");
								autoId();
								UpdateInvoieValue();
								GrossAmount();
								checkCounter();
								ProudctClear();
							}
							else{
								double price=0.0,totalPrice=0.0;
								int qty=0;
								qty=Integer.parseInt(txtQty.getText().trim().toString());
								price=Double.parseDouble(txtPrice.getText().trim().toString());
								ResultSet rs=db.sta.executeQuery("select qty from tbpharmacystore where productName='"+cmbProductName.txtMrNo.getText().toString()+"' && type='3' && selltype=1 && invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"'");
								while(rs.next()){
									totalqty=totalqty+Integer.parseInt(rs.getString("qty"));
								}
								totalPrice=totalqty*price;
								String query="update tbpharmacystore set qty='"+totalqty+"',sellPrice='"+price+"',totalPrice='"+df.format(totalPrice)+"',edit='"+sessionbeam.getUserId()+"' where productName='"+cmbProductName.txtMrNo.getText().toString()+"' && type='3' && selltype=1 && invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"' ";
								System.out.println(query);
								db.sta.executeUpdate(query);
								JOptionPane.showMessageDialog(null, "Product Order Successfully Accept");
								autoId();
								UpdateInvoieValue();
								GrossAmount();
								checkCounter();
								ProudctClear();
							}
						}
						else{
							if(!doplicateName()){
								autoId();
								productDescription();
								double price=0.0,totalPrice=0.0;
								int qty=0;
								qty=Integer.parseInt(txtQty.getText().trim().toString());
								price=Double.parseDouble(txtPrice.getText().trim().toString());
								totalPrice=totalqty*price;

								String sql="insert into tbpharmacystore (autoId,productId,productName,unit,catagoryId,qty,sellPrice,totalPrice,type,selltype,counter,date,entryTime,createBy) values" +
										"('"+autoId+"'," +
										"'"+productId+"'," +
										"'"+cmbProductName.txtMrNo.getText().toString()+"'," +
										"'Pcs'," +
										"'"+catagoryid+"'," +
										"'"+totalqty+"'," +
										"'"+price+"'," +
										"'"+df.format(totalPrice)+"'," +
										"'"+3+"'," +
										"'"+1+"'," +
										"'"+1+"'," +
										"'"+startDate+"'," +
										"'"+startDate+"'," +
										"'"+sessionbeam.getUserId()+"')";
								System.out.println(sql);
								db.sta.executeUpdate(sql);
								JOptionPane.showMessageDialog(null, "Sales Product Successfully Accept");
								autoId();
								showData();
								GrossAmount();
								checkCounter();
								autoinvoiceId();
								ProudctClear();

							}
							else{
								double price=0.0,totalPrice=0.0;
								int qty=0;
								qty=Integer.parseInt(txtQty.getText().trim().toString());
								price=Double.parseDouble(txtPrice.getText().trim().toString());

								ResultSet rs=db.sta.executeQuery("select qty from tbpharmacystore where productName='"+cmbProductName.txtMrNo.getText().toString()+"' && type='3' && selltype=1 && invoiceNo IS NULL");
								while(rs.next()){
									totalqty=totalqty+Integer.parseInt(rs.getString("qty"));
								}
								totalPrice=totalqty*price;
								String query="update tbpharmacystore set qty='"+totalqty+"',sellPrice='"+price+"',totalPrice='"+df.format(totalPrice)+"',edit='"+sessionbeam.getUserId()+"' where productName='"+cmbProductName.txtMrNo.getText().toString()+"' && type='3' && selltype=1 && counter=1 && invoiceNo IS NULL ";
								System.out.println(query);
								db.sta.executeUpdate(query);
								JOptionPane.showMessageDialog(null, "Sales Order Successfully Accept");
								autoId();
								showData();
								GrossAmount();
								checkCounter();
								autoinvoiceId();
								ProudctClear();
							}
						}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void ProudctClear() {
		cmbProductName.txtMrNo.setText("");
		cmbModelName.txtMrNo.setText("");
		txtSoldQty.setText("");
		txtStockQty.setText("");
		txtQty.setText("");
		txtPrice.setText("");
		txtShortGenericName.setText("");
	}
	public void showData(){
		try {

			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			if(counter1==1){

				ResultSet rs=db.sta.executeQuery("select * from tbpharmacystore where type in (3,4) && counter='1' && selltype=1 && invoiceNo IS NULL");
				while(rs.next()){
					model.addRow(new Object[]{rs.getString("autoId"),rs.getString("productName"),rs.getString("qty"),rs.getString("sellprice"),rs.getString("totalPrice"),new ImageIcon("icon/delete.png")});

				}
			}
			if(counter2==2){
				ResultSet rs=db.sta.executeQuery("select * from tbpharmacystore where type in (3,4) && selltype=1 && counter='2' && invoiceNo IS NULL");
				while(rs.next()){
					model.addRow(new Object[]{rs.getString("autoId"),rs.getString("productName"),rs.getString("qty"),rs.getString("sellprice"),rs.getString("totalPrice"),new ImageIcon("icon/delete.png")});
				}
			}
			if(counter3==3){
				ResultSet rs=db.sta.executeQuery("select *  from tbpharmacystore where type in (3,4) && selltype=1 && counter='3' && invoiceNo IS NULL");
				while(rs.next()){
					model.addRow(new Object[]{rs.getString("autoId"),rs.getString("productName"),rs.getString("qty"),rs.getString("sellprice"),rs.getString("totalPrice"),new ImageIcon("icon/delete.png")});
				}
			}
			for(int a=0;a<20;a++){
				model.addRow(new Object[]{"","","","","",new ImageIcon("icon/delete.png")});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private boolean ValidproductNameCheck(){
		try {
			ResultSet rs=db.sta.executeQuery("select productName from tbiteminformation");
			while(rs.next()){
				if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
		return false;
	}
	private boolean updoplicateName(){
		try {
			if(counter1==1){
				ResultSet rs=db.sta.executeQuery("select productName from tbpharmacystore where type='3' && selltype=1 && counter='1' && invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"'");
				while(rs.next()){

					if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
						return true;
					}
				}
			}
			if(counter2==2){
				ResultSet rs=db.sta.executeQuery("select productName from tbpharmacystore where type='3' && selltype=1 && counter='2' && invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"'");
				while(rs.next()){

					if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
						return true;
					}
				}
			}
			if(counter3==3){
				ResultSet rs=db.sta.executeQuery("select productName from tbpharmacystore where type='3' && counter='3' && invoiceNo='"+cmbInvoiceNo.txtMrNo.getText().trim().toString()+"'");
				while(rs.next()){

					if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;
	}

	private boolean doplicateName(){
		try {
			if(counter1==1){
				ResultSet rs=db.sta.executeQuery("select productName from tbpharmacystore where type='3' && selltype=1 && counter='1' && invoiceNo IS NULL");
				while(rs.next()){

					if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
						return true;
					}
				}
			}
			if(counter2==2){
				ResultSet rs=db.sta.executeQuery("select productName from tbpharmacystore where type='3' && selltype=1 && counter='2' && invoiceNo IS NULL");
				while(rs.next()){

					if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
						return true;
					}
				}
			}
			if(counter3==3){
				ResultSet rs=db.sta.executeQuery("select productName from tbpharmacystore where type='3' && selltype=1 && counter='3' && invoiceNo IS NULL");
				while(rs.next()){

					if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
						return true;
					}
				}
			}
			if(checkInvoice.isSelected() && counter1==1){
				ResultSet rs=db.sta.executeQuery("select productName from tbpharmacystore where type='4' && selltype=1 && counter='1' && invoiceNo IS NULL");
				while(rs.next()){

					if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
						return true;
					}
				}
			}
			if(checkInvoice.isSelected() && counter2==2){
				ResultSet rs=db.sta.executeQuery("select productName from tbpharmacystore where type='4' && selltype=1 && counter='2' && invoiceNo IS NULL");
				while(rs.next()){

					if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
						return true;
					}
				}
			}
			if(checkInvoice.isSelected() && counter3==3){
				ResultSet rs=db.sta.executeQuery("select productName from tbpharmacystore where type='4' && selltype=1 &&  counter='3' && invoiceNo IS NULL");
				while(rs.next()){

					if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;
	}

	public void autoinvoiceId(){
		try {
			if(checkInvoice.isSelected()){
				String sql="select (ifnull(max(invoiceNo),0)+1)as invoiceNo from tbinvoice where type=4";
				ResultSet rs=db.sta.executeQuery(sql);
				while(rs.next())
				{
					invoiceid=rs.getString("invoiceNo");
					cmbInvoiceNo.txtMrNo.setText(invoiceid);
				}
			}
			else if(!checkInvoice.isSelected()){
				String sql="select (ifnull(max(invoiceNo),0)+1)as invoiceNo from tbinvoice where type=3";
				ResultSet rs=db.sta.executeQuery(sql);
				while(rs.next())
				{
					invoiceid=rs.getString("invoiceNo");
					cmbInvoiceNo.txtMrNo.setText(invoiceid);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void GrossAmount(){
		double sum=0,sum1=0;
		for(int a=0;a<table.getRowCount();a++){
			if(table.getValueAt(a, 4).toString()!=""){
				sum=sum+Double.parseDouble(table.getValueAt(a, 4).toString());
			}
		}
		txtGrossAmount.setText(new DecimalFormat("##.##").format(sum));
		txtNetAmount.setText(new DecimalFormat("##.##").format(sum));
		txtPaymentAmount.setText(new DecimalFormat("##.##").format(sum));
	}
	private void productDescription(){
		try {
			ResultSet rs=db.sta.executeQuery("select *from tbiteminformation where productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+cmbProductName.txtMrNo.getText().toString()+"')");
			while(rs.next()){
				productId=rs.getString("productId");
				catagoryid=rs.getString("BrandId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void autoId(){
		try {
			String sql="select (ifnull(max(autoId),0)+1)as autoId from tbpharmacystore";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				autoId=rs.getString("autoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void loadProductName(){
		try {

			if(!cmbProductName.txtMrNo.getText().trim().toString().isEmpty()  ){
				cmbProductName.v.clear();
				cmbProductName.v.add("");
				ResultSet rs=db.sta.executeQuery("select ProductName from tbiteminformation where brandid=(select tbbrandinfo.brandId from tbbrandinfo where tbbrandinfo.brandName='"+cmbProductName.txtMrNo.getText().trim().toString()+"')");
				while(rs.next()){
					cmbProductName.v.add(rs.getString("ProductName"));
				}
			}
			else{
				cmbProductName.v.clear();
				cmbProductName.v.add("");

				ResultSet rs=db.sta.executeQuery("select ProductName from tbiteminformation");
				while(rs.next()){
					cmbProductName.v.add(rs.getString("ProductName"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void autoInvoice(){
		try {
			String sql="select (ifnull(max(invoiceNo),0)+1)as invoiceNo from tbinvoice where type=3";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbInvoiceNo.txtMrNo.setText(rs.getString("invoiceNo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loaddRow(){
		for(int a=0;a<19;a++){
			model.addRow(new Object[]{"","","","","",new ImageIcon("icon/delete.png")});
		}
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
		DateMidnight now = new DateMidnight();
		//DateMidnight beginningOfLastMonth = now.minusMonths(0).withDayOfMonth(1);
		DateMidnight endOfLastMonth = now.withDayOfMonth(1).minusDays(1);
		txtdate.setDate(date);
	}
	public void txtClear(){
		txtCustomerName.setText("");
		cmbProductName.txtMrNo.setText("");
		txtPrice.setText("");
		txtQty.setText("");
		txtGrossAmount.setText("0");
		txtDiscountPercent.setText("0");
		txtManualDiscount.setText("0");
		txtNetAmount.setText("0");
		txtPaymentAmount.setText("0");
		txtPaymentAmount.setText("");
	}
	private void addcmp() {
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(1250,670));
		mainPanel.add(mainNorthPanel,BorderLayout.NORTH);
		mainNorthPanel.setOpaque(false);
		mainPanel.add(mainCenterPanel,BorderLayout.CENTER);
		mainCenterPanel.setOpaque(false);
		mainPanel.add(mainSouthPanel,BorderLayout.SOUTH);
		mainSouthPanel.setOpaque(false);
		mainNorthPanel_work();
		mainCenterPanel_work();
		mainSouthPanel_work();
	}
	private void mainNorthPanel_work() {
		mainNorthPanel.setPreferredSize(new Dimension(1345,150));
		mainNorthPanel.setLayout(new BorderLayout());
		mainNorthPanel.add(mainNorthWestPanel, BorderLayout.WEST);
		mainNorthWestPanel.setOpaque(false);
		mainNorthWestPanel_work();
		mainNorthPanel.add(mainNorthCenterPanel, BorderLayout.CENTER);
		mainNorthCenterPanel.setOpaque(false);
		mainNorthCenterPanel_work();
		mainNorthPanel.add(mainNorthEastPanel, BorderLayout.EAST);
		mainNorthEastPanel.setOpaque(false);
		mainNorthEastPanel_work();
	}
	private void mainNorthCenterPanel_work() {
		mainNorthCenterPanel.setPreferredSize(new Dimension(200,150));
		//mainNorthCenterPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		mainNorthCenterPanel.setLayout(new FlowLayout());
/*		mainNorthCenterPanel.add(btnCounter1);
		mainNorthCenterPanel.add(btnCounter2);
		mainNorthCenterPanel.add(btnCounter3);*/
		mainNorthCenterPanel.add(checkInvoice);
		mainNorthCenterPanel.add(btnReset);
		btnReset.setPreferredSize(new Dimension(38, 36));
		checkInvoice.setPreferredSize(new Dimension(100, 15));
		checkInvoice.setBackground(new Color(151, 184, 192));
		btnCounter1.setPreferredSize(new Dimension(45,35));
		btnCounter2.setPreferredSize(new Dimension(45,35));
		btnCounter3.setPreferredSize(new Dimension(45,35));
		btnCounter1.setBackground(new Color(2, 191, 185));
		btnCounter2.setBackground(new Color(2, 191, 185));
		btnCounter3.setBackground(new Color(2, 191, 185));
	}
	private void mainNorthEastPanel_work() {
		mainNorthEastPanel.setPreferredSize(new Dimension(380, 150));
		//mainNorthEastPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		FlowLayout flow=new FlowLayout();
		mainNorthEastPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		mainNorthEastPanel.add(hScroll);
		htable.getTableHeader().setReorderingAllowed(false);
		htable.setRowHeight(htable.getRowHeight() + 10);
		htable.setShowGrid(true);
		htable.setOpaque(false);
		hScroll.setOpaque(false);
		hScroll.getViewport().setOpaque(false);
		hScroll.setPreferredSize(new Dimension(365,100));
		htable.getColumnModel().getColumn(0).setPreferredWidth(100);
		htable.getColumnModel().getColumn(1).setPreferredWidth(95);
		htable.getColumnModel().getColumn(0).setPreferredWidth(95);
		htable.getColumnModel().getColumn(0).setPreferredWidth(95);
		htable.setSelectionForeground(Color.white);
		htable.setFont(new Font("arial", Font.BOLD, 14));

		mainNorthEastPanel.add(lblDuePaymentType);
		mainNorthEastPanel.add(cmbDueType);
		cmbDueType.setPreferredSize(new Dimension(100,26));

		mainNorthEastPanel.add(lblDuePaymentAmount);
		mainNorthEastPanel.add(txtDuePaymentAmount);
		txtDuePaymentAmount.setPreferredSize(new Dimension(150,28));

		mainNorthEastPanel.add(btnDueSubmit);
		btnDueSubmit.setPreferredSize(new Dimension(40,36));
		historyRow();
	}
	private void historyRow(){
		for(int a=0;a<5;a++){
			hmodel.addRow(new Object[]{"","","",""});
		}
	}
	private void mainNorthWestPanel_work() {
		mainNorthWestPanel.setPreferredSize(new Dimension(360, 150));
		mainNorthWestPanel.setLayout(new GridBagLayout());
		//mainNorthWestPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(1, 1, 1, 1);
		mainNorthWestPanel.add(lblPreviousDate,grid);
		lblPreviousDate.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=0;
		mainNorthWestPanel.add(txtPreviousdate,grid);
		txtPreviousdate.setDateFormatString("dd-MM-yyyy");
		txtPreviousdate.setDate(new Date());
		txtPreviousdate.setFont(new Font("arial",Font.BOLD,15));
		txtPreviousdate.setPreferredSize(new Dimension(180,28));
		grid.gridx=2;
		grid.gridy=0;
		mainNorthWestPanel.add(btnPreviousInvoice,grid);
		btnPreviousInvoice.setPreferredSize(new Dimension(28, 28));
		grid.gridx=0;
		grid.gridy=1;
		mainNorthWestPanel.add(lblInNo,grid);
		lblInNo.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=1;
		mainNorthWestPanel.add(cmbInvoiceNo.combo,grid);
		cmbInvoiceNo.combo.setForeground(Color.WHITE);
		cmbInvoiceNo.combo.setBackground(new Color(148, 86, 97));
		cmbInvoiceNo.combo.setFont(new Font("arial",Font.BOLD,15));
		cmbInvoiceNo.combo.setPreferredSize(new Dimension(180,28));

		grid.gridx=0;
		grid.gridy=2;
		mainNorthWestPanel.add(lblCustomerName,grid);
		lblCustomerName.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=2;
		mainNorthWestPanel.add(txtCustomerName,grid);
		txtCustomerName.setFont(new Font("arial",Font.BOLD,14));
		txtCustomerName.setPreferredSize(new Dimension(180,28));
		grid.gridx=0;
		grid.gridy=3;
		mainNorthWestPanel.add(lblBrandName,grid);
		grid.gridx=1;
		grid.gridy=3;
		mainNorthWestPanel.add(cmbBrandName.combo,grid);
		cmbBrandName.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbBrandName.combo.setPreferredSize(new Dimension(180,28));
		grid.gridx=0;
		grid.gridy=4;
		mainNorthWestPanel.add(lblModelName,grid);
		grid.gridx=1;
		grid.gridy=4;
		mainNorthWestPanel.add(cmbModelName.combo,grid);
		cmbModelName.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbModelName.combo.setPreferredSize(new Dimension(180,28));
	}
	private void mainCenterPanel_work() {
		mainCenterPanel.setPreferredSize(new Dimension(1345,50));
		mainCenterPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0,0));
		FlowLayout flow=new FlowLayout();
		mainCenterPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		mainCenterPanel.add(lblProductName);
		lblProductName.setPreferredSize(new Dimension(103, 10));
		lblProductName.setFont(new Font("arial",Font.BOLD,13));
		mainCenterPanel.add(cmbProductName.combo);
		cmbProductName.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbProductName.combo.setPreferredSize(new Dimension(208,32));


		mainCenterPanel.add(lblStockQty);
		mainCenterPanel.add(txtStockQty);
		txtStockQty.setForeground(Color.WHITE);
		txtStockQty.setBackground(new Color(148, 86, 97));
		txtStockQty.setFont(new Font("arial",Font.BOLD,15));
		txtStockQty.setPreferredSize(new Dimension(90,32));
		txtStockQty.setEditable(false);

		mainCenterPanel.add(lblSoldQty);
		mainCenterPanel.add(txtSoldQty);
		txtSoldQty.setFont(new Font("arial",Font.BOLD,14));
		txtSoldQty.setPreferredSize(new Dimension(90,32));
		txtSoldQty.setEditable(false);

		mainCenterPanel.add(lblQty);
		lblQty.setFont(new Font("arial",Font.BOLD,13));
		mainCenterPanel.add(txtQty);
		txtQty.setFont(new Font("arial",Font.BOLD,14));
		txtQty.setPreferredSize(new Dimension(140,32));

		mainCenterPanel.add(lblPrice);
		lblPrice.setFont(new Font("arial",Font.BOLD,13));
		mainCenterPanel.add(txtPrice);
		//txtPrice.setEditable(false);
		txtPrice.setFont(new Font("arial",Font.BOLD,14));
		txtPrice.setPreferredSize(new Dimension(140,32));


		mainCenterPanel.add(btnSubmit);
		btnSubmit.setPreferredSize(new Dimension(96,36));
		btnSubmit.setMnemonic(KeyEvent.VK_S);
	}
	private void mainSouthPanel_work() {
		mainSouthPanel.setPreferredSize(new Dimension(1345,454));
		mainSouthPanel.setLayout(new BorderLayout());
		mainSouthPanel.setOpaque(false);
		mainSouthPanel.add(southToppanel,BorderLayout.NORTH);
		southToppanel_work();
		mainSouthPanel.add(southTopDown,BorderLayout.SOUTH);
		southTopDown_work();

	}
	private void southTopDown_work() {
		southTopDown.setPreferredSize(new Dimension(10, 110));
		southTopDown.setBorder(BorderFactory.createRaisedBevelBorder());
		southTopDown.setLayout(new FlowLayout());
		southTopDown.add(lblNote);
		southTopDown.add(txtNote);
		txtNote.setPreferredSize(new Dimension(800, 29));
		txtNote.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtNote.setFont(new Font("arial", Font.BOLD, 14));
		southTopDown.add(btnConfrim);
		btnConfrim.setPreferredSize(new Dimension(100, 36));
		btnConfrim.setMnemonic(KeyEvent.VK_C);
		southTopDown.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(100, 36));
		btnRefresh.setMnemonic(KeyEvent.VK_R);
		southTopDown.setOpaque(false);
	}
	private void southToppanel_work() {
		southToppanel.setPreferredSize(new Dimension(10, 330));
		southToppanel.setLayout(new BorderLayout());
		southToppanel.setOpaque(false);
		southToppanel.add(scroll,BorderLayout.WEST);
		scroll.setPreferredSize(new Dimension(850, 380));
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int confirm=JOptionPane.showConfirmDialog(null, "Are you sure to delete this product?","Confrim",JOptionPane.YES_NO_OPTION);
				if(confirm==JOptionPane.YES_OPTION){
					try {
						String sql="delete from tbpharmacystore where autoId='"+table.getValueAt(table.getSelectedRow(), 0)+"'";
						System.out.println("sql "+sql);
						db.sta.executeUpdate(sql);
						model.removeRow(table.getSelectedRow());
						JOptionPane.showMessageDialog(null, "Delete...");
						GrossAmount();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		};
		ButtonColumn btnUpdate = new ButtonColumn(table, delete, 5);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(280);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.getColumnModel().getColumn(5).setPreferredWidth(34);
		table.setShowGrid(true);
		table.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.setSelectionForeground(Color.WHITE);
		table.setFont(new Font("arial", Font.BOLD, 14));
		for(int i=2;i<5;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(txtTableProductName));
		table.setRowHeight(table.getRowHeight() + 10);
		southToppanel.add(southTopEastpanel, BorderLayout.EAST);
		southTopEastpanel_work();

	}

	private void southTopEastpanel_work() {
		southTopEastpanel.setPreferredSize(new Dimension(410, 350));
		southTopEastpanel.setLayout(new GridBagLayout());
		southTopEastpanel.setOpaque(false);
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(4, 4, 4, 4);
		southTopEastpanel.add(lblGrossAmount,grid);
		grid.gridx=1;
		grid.gridy=0;
		southTopEastpanel.add(txtGrossAmount,grid);
		txtGrossAmount.setFont(new Font("arial",Font.BOLD,14));
		txtGrossAmount.setPreferredSize(new Dimension(220, 32));
		txtGrossAmount.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtGrossAmount.setEditable(false);
		grid.gridx=0;
		grid.gridy=1;
		southTopEastpanel.add(lblDiscountPercent,grid);
		grid.gridx=1;
		grid.gridy=1;
		southTopEastpanel.add(txtDiscountPercent,grid);
		txtDiscountPercent.setFont(new Font("arial",Font.BOLD,14));
		txtDiscountPercent.setPreferredSize(new Dimension(220, 32));
		txtDiscountPercent.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtDiscountPercent.setText("0");
		grid.gridx=2;
		grid.gridy=1;
		southTopEastpanel.add(lblDiscountAmount,grid);
		lblDiscountAmount.setText("0.0");
		grid.gridx=0;
		grid.gridy=2;
		southTopEastpanel.add(lblMamnualDiscount,grid);
		grid.gridx=1;
		grid.gridy=2;
		southTopEastpanel.add(txtManualDiscount,grid);
		txtManualDiscount.setFont(new Font("arial",Font.BOLD,14));
		txtManualDiscount.setPreferredSize(new Dimension(220, 32));
		txtManualDiscount.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtManualDiscount.setText("0");
		grid.gridx=0;
		grid.gridy=3;
		southTopEastpanel.add(lblNetAmount,grid);
		grid.gridx=1;
		grid.gridy=3;
		southTopEastpanel.add(txtNetAmount,grid);
		txtNetAmount.setForeground(Color.WHITE);
		txtNetAmount.setBackground(new Color(148, 86, 97));
		txtNetAmount.setFont(new Font("arial",Font.BOLD,15));
		txtNetAmount.setPreferredSize(new Dimension(220, 32));
		txtNetAmount.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtNetAmount.setText("0");
		grid.gridx=0;
		grid.gridy=4;
		southTopEastpanel.add(lblPaymentType,grid);
		grid.gridx=1;
		grid.gridy=4;
		southTopEastpanel.add(cmbType,grid);
		cmbType.setFont(new Font("arial",Font.BOLD,14));
		cmbType.setPreferredSize(new Dimension(220, 32));
		grid.gridx=0;
		grid.gridy=5;
		southTopEastpanel.add(lblPaymentAmount,grid);
		lblPaymentAmount.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=5;
		southTopEastpanel.add(txtPaymentAmount,grid);
		txtPaymentAmount.setFont(new Font("arial",Font.BOLD,14));
		txtPaymentAmount.setPreferredSize(new Dimension(220, 32));
		grid.gridx=0;
		grid.gridy=6;
		southTopEastpanel.add(lblDate,grid);
		lblDate.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=6;
		southTopEastpanel.add(txtdate,grid);
		txtdate.setFont(new Font("arial",Font.BOLD,14));
		txtdate.setPreferredSize(new Dimension(220, 32));
		txtdate.setDateFormatString("dd-MM-yyyy");
		txtdate.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
	}

}
