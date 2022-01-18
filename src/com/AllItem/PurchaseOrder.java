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
import java.util.ArrayList;
import java.util.Calendar;
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

public class PurchaseOrder extends JPanel{
	SessionBeam sessionbeam;
	db_coonection db=new db_coonection();
	public JPanel mainPanel=new JPanel();
	public JPanel mainNorthPanel=new JPanel();
	public JPanel NorthWestPanel=new JPanel();
	public JPanel NorthEastPanel=new JPanel();
	public JPanel mainCenterPanel=new JPanel();
	public JPanel mainSouthPanel=new JPanel();
	public JPanel southToppanel=new JPanel();
	public JPanel southTopEastpanel=new JPanel();
	public JPanel southTopDown=new JPanel();


	JLabel lblInNo=new JLabel("<html><font color=red>*</font>Invoice No</html>");
	JLabel lblBrandName=new JLabel(" Brand Name");
	JLabel lblModelName=new JLabel(" Model Name");
	JLabel lblDate=new JLabel("<html><font color=red>*</font>Date</html>");
	JLabel lblDebit=new JLabel("Bank");
	JLabel lblQty=new JLabel("<html><font color=red>*</font>Qty</html>");
	JLabel lblNote=new JLabel("Note");
	JLabel lblTotal=new JLabel("Total");
	SuggestText cmbProductName=new SuggestText();
	SuggestText cmbSupplierName=new SuggestText();
	JTextField txtTotalAmount=new JTextField(20);
	JTextField txtInvoiceNo=new JTextField(20);
	JTextField txtVoucherNo=new JTextField(20);
	JTextField txtNote=new JTextField(66);
	JTextField txtPrice=new JTextField(6);
	JTextField txtQty=new JTextField(4);
	SuggestText cmbProductBrand=new  SuggestText();
	SuggestText cmbModelName=new  SuggestText();
	JTextField txtShortBrandName=new  JTextField(10);
	JDateChooser txtdate=new JDateChooser();
	JCheckBox check=new JCheckBox();
	JDateChooser txtCheckdate=new JDateChooser();

	JLabel lblUnit=new JLabel("<html><font color=red>*</font>Unit</html>");
	JLabel lblQtyPerpack=new JLabel("Qty Per Pack");
	JLabel lblPricePerQty=new JLabel("Price Per Qty");

	String Unit[]={"","Pcs","Strip","Box"};
	JComboBox cmbunit=new JComboBox(Unit);

	JTextField txtQtyPerpack=new JTextField(4);
	JTextField txtPricePerQty=new JTextField(6);

	JDateChooser txtStartDate=new JDateChooser();
	JDateChooser txtEndDate=new JDateChooser();

	JLabel lblStartDate=new JLabel("Start Date");
	JLabel lblEndDate=new JLabel("End Date");
	JLabel lblProductName=new JLabel("<html><font color=red>*</font>Product Name</html>");
	JLabel lblExpireDate=new JLabel("Expire Date");
	JLabel lblDiscountPercent=new JLabel("Discount (%)");
	JTextField txtDiscountPercent=new JTextField(16);
	JLabel lblIncompleInvoice=new JLabel("Incomple Invoice");

	JLabel lblSupplier=new JLabel("<html><font color=red>*</font>Supplier Name</html>");
	JLabel lblChallanNo=new JLabel("Challan No");
	JLabel lblGrossAmount=new JLabel("Gross Amount");
	JLabel lblMamnualDiscount=new JLabel("Manual Discount");
	JLabel lblNetAmount=new JLabel("Net Amount");
	JLabel lblPaymentAmount=new JLabel("<html><font color=red>*</font>Amount</html>");
	JLabel lblPaymentType=new JLabel("Payment Type");
	JLabel lblDiscountAmount=new JLabel("0.0");

	String type[]={"Cash"};
	JComboBox cmbType=new  JComboBox(type);
	JTextField txtChallanNo=new JTextField(16);
	JTextField txtGrossAmount=new JTextField(16);
	JTextField txtManualDiscount=new JTextField(16);
	JTextField txtNetAmount=new JTextField(16);
	JTextField txtPaymentAmount=new JTextField(16);

	JDateChooser txtExpireDate=new JDateChooser();
	DecimalFormat df = new DecimalFormat("#.00");

	String head[]={"","Revenue","Expense","Liability","Asset","Other"};
	JComboBox cmbHead=new JComboBox(head);
	JButton btnSubmit=new JButton("Submit",new ImageIcon("icon/save.png"));
	JButton btnSearch=new JButton(new ImageIcon("icon/find.png"));
	JButton btnConfrim=new JButton("Confrim",new ImageIcon("icon/save.png"));
	JButton btnReset=new JButton(new ImageIcon("icon/refresh.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("icon/reresh.png"));

	JButton[] btnUpdate=new JButton[9];
	JLabel lblLedger= new JLabel("Ledger       ");
	JLabel lblProduct= new JLabel("Product Name       ");
	JLabel lblPrice= new JLabel("<html><font color=red>*</font>Price (Pcs)</html>");

	String invoice[]={};
	JComboBox cmbinvoice=new JComboBox(invoice);
	JTextField txtTableProductName=new JTextField(15);
	String ledgerName[] = {""};
	private JComboBox cmbLedgerName = new JComboBox(ledgerName);

	GridBagConstraints grid=new GridBagConstraints();
	String col1[]={"Product ID", "     Product Name","Order Unit","Order Qty"," Sold Unit","Sold Qty","Stock Qty","LU Cost Rate"};
	Object row1[][]={};
	public DefaultTableModel model1=new DefaultTableModel(row1,col1);
	public JTable table1=new JTable(model1){
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

	};
	JScrollPane scroll1=new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

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

	String col[]={"AUTO ID","            NAME","        QTY","      PRICE","TOTAL","DEL"};
	Object row[][]={};
	public DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table = new JTable(model) {
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

	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String tranId="",jvno="",supId="",startDate="",invoiceid="",FinaltransectionId="",f_d_l_id="",f_c_l_id="";
	double debit=0,credit=0,GrossAmount=0.0;
	int i=0,search=0,update=0;
	int tledger=0,findId=0;
	ArrayList list=new ArrayList();
	String productId="",unit="",catagoryid="",subCatagoryId="",autoId="",d_l_id="",c_l_id="";

	int totalqty=0;
	JButton btnDueSubmit=new JButton("Due Payment",new ImageIcon("icon/save.png"));
	JLabel lblDuePaymentType=new JLabel("Payment Type");
	String duetype[]={"Cash"};
	JComboBox cmbDueType=new  JComboBox(duetype);
	JLabel lblDuePaymentAmount=new JLabel("<html><font color=red>*</font>Payment</html>");
	JTextField txtDuePaymentAmount=new JTextField(10);
	BufferedImage image;
	public PurchaseOrder(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addcmp();
		date_take();
		btnActionEvent();
		//loadSupplierName();
		loadProductName();
		ptextEvent();
		GrossAmount();
		loadBrandName();
		setDateBetween7();
		loadRow();
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
	private void ProductTxtClear(){
		cmbModelName.txtMrNo.setText("");
		cmbProductName.txtMrNo.setText("");
		txtShortBrandName.setText("");
		cmbunit.setSelectedItem("");
		txtQty.setText("");
		txtPrice.setText("");
		cmbProductName.txtMrNo.requestFocusInWindow();
	}
	public void FinaltransectionId(){
		try {
			String sql="select (ifnull(max(transectionid),0)+1)as transectionid from accftransection";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				FinaltransectionId=rs.getString("transectionid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void loadRow(){
		for(int a=0;a<5;a++){
			model1.addRow(new Object[]{"","","","","","",""});
		}
	}
	public void setDateBetween7(){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		Date date = new Date();
		String todate = dateFormat.format(date);
		//txtStartDate.setDate(date);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +7);
		Date todate1 = cal.getTime();    
		String fromdate = dateFormat.format(todate1);
		txtEndDate.setDate(todate1);
	}

	public void ClearAfterConfrim(){
		txtVoucherNo.setText("");
		cmbSupplierName.txtMrNo.setText("");
		cmbProductName.txtMrNo.setText("");
		txtPrice.setText("");
		txtQty.setText("");
		txtGrossAmount.setText("0");
		txtDiscountPercent.setText("0");
		txtManualDiscount.setText("0");
		txtNetAmount.setText("0");
		txtPaymentAmount.setText("0");
		autoInvoice();
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
		loaddRow();
	}		

	private Date getDate(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	private void ptextEvent(){
		txtStartDate.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Hi");
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
		txtQtyPerpack.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if(!txtPrice.getText().trim().toString().isEmpty()){
					if(!txtQty.getText().trim().toString().isEmpty()){
						if(!txtQtyPerpack.getText().trim().toString().isEmpty()){
							int qty=Integer.parseInt(txtQty.getText().trim().toString())*Integer.parseInt(txtQtyPerpack.getText().trim().toString());
							double totalPrice=Double.parseDouble(txtPrice.getText().trim().toString())*Integer.parseInt(txtQty.getText().trim().toString());
							txtPricePerQty.setText(Double.toString(totalPrice/qty));
						}
					}
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		cmbunit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cmbunit.getSelectedIndex()==1){
					txtQtyPerpack.setEditable(false);
					txtQtyPerpack.setText("");
					txtPricePerQty.setEditable(false);
					txtPricePerQty.setText("");
				}
				else{
					txtPricePerQty.setEditable(true);
					txtQtyPerpack.setEditable(true);
				}
			}
		});
		txtVoucherNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cmbSupplierName.txtMrNo.requestFocusInWindow();
			}
		});
		cmbSupplierName.txtMrNo.addKeyListener(new KeyListener() {

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
					if(!cmbSupplierName.txtMrNo.getText().trim().toString().isEmpty()){
						cmbProductName.txtMrNo.requestFocusInWindow();
					}
				}
			}
		});
		cmbProductBrand.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				try {
					cmbProductBrand.v.clear();
					cmbProductBrand.v.add("");
					ResultSet rs=db.sta.executeQuery("select tbbrandinfo.brandName from tbbrandinfo");
					while(rs.next()){
						cmbProductBrand.v.add(rs.getString("brandName"));
					}
				} catch (Exception e2) {
					e2.printStackTrace();
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
						ResultSet rs=db.sta.executeQuery("select tbbrandwisedepartmentinfo.ModelName from tbbrandwisedepartmentinfo where tbbrandwisedepartmentinfo.brandid=(select tbbrandinfo.brandId from tbbrandinfo where tbbrandinfo.brandName='"+cmbProductBrand.txtMrNo.getText().trim().toString()+"')");
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
		cmbProductName.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
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
		txtQty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					double price=0.0;
					int perqty=0;
					ResultSet rs=db.sta.executeQuery("select tbiteminformation.buyPrice from tbiteminformation where tbiteminformation.ProductName='"+cmbProductName.txtMrNo.getText().trim().toString()+"'");
					while(rs.next()){
						price=Double.parseDouble(rs.getString("buyPrice"));
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
		txtPrice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSubmitEvent();
				cmbProductName.txtMrNo.requestFocusInWindow();
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
				cmbType.requestFocusInWindow();
			}
		});
		cmbType.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					txtPaymentAmount.requestFocusInWindow();
				}
			}
		});
		cmbProductName.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				loadProductName();

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				char c=e.getKeyChar();
				if(c==KeyEvent.VK_ENTER){
					if(!cmbProductName.txtMrNo.getText().trim().toString().isEmpty()){
						try {


							txtQty.requestFocusInWindow();

						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
			}
		});
		txtdate.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				btnConfrim.requestFocusInWindow();
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		cmbunit.addKeyListener(new KeyListener() {

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
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					txtQty.requestFocusInWindow();
				}

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
				btnConfrimEvent();
			}
		});
		btnDueSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnDueSubmitEvent() ;
			}
		});

	}
	private void btnDueSubmitEvent() {
		try {
			date_take();
			if(!txtDuePaymentAmount.getText().trim().toString().isEmpty()){

				String d_l_id="",c_l_id="116";
				double cashAmount=0.0,cardAmount=0.0;
				/*				if(cmbDueType.getSelectedIndex()==0){
					ResultSet rs1=db.sta.executeQuery("select ledgerId from accfledger where ledgerTitle='Cash'");
					while(rs1.next()){
						d_l_id=rs1.getString("ledgerId");
					}
					cashAmount=Double.parseDouble(txtDuePaymentAmount.getText().trim().toString());
					cardAmount=0.0;
				}
				else{
					ResultSet rs1=db.sta.executeQuery("select ledgerId from accfledger where ledgerTitle='Card'");
					while(rs1.next()){
						d_l_id=rs1.getString("ledgerId");
					}
					cardAmount=Double.parseDouble(txtDuePaymentAmount.getText().trim().toString());
					cashAmount=0.0;
				}*/

				if(cmbDueType.getSelectedIndex()==0){
					cashAmount=Double.parseDouble(txtDuePaymentAmount.getText().trim().toString());
					cardAmount=0.0;
				}

				/*				ResultSet rs1=db.sta.executeQuery("select ledgerId from accfledger where ledgerTitle='"+cmbSupplierName.txtMrNo.getText().trim().toString()+"' && depId='701'");
				while(rs1.next()){
					d_l_id=rs1.getString("ledgerId");
				}*/

				double paid=cashAmount+cardAmount;
				System.out.println("paid "+paid);
				String sql="update tbinvoice set cash=cash+'"+cashAmount+"',card=card+'"+cardAmount+"',paid=paid+'"+paid+"', date=NOW(),entryTime=NOW(),createBy='"+sessionbeam.getUserId()+"' where invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' && type=1";
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
	public void loadLedgerName(){
		try {
			cmbLedgerName.removeAllItems();
			cmbLedgerName.addItem("");
			String sql="select ledgerTitle from accfledger";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbLedgerName.addItem(rs.getString("ledgerTitle"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void btnActionEvent(){
		mainPanel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "1");
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "2");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "3");
			}
		});
		txtInvoiceNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					try {
						for(int a=table.getRowCount()-1;a>=0;a--){
							model.removeRow(a);
						}
						for(int a=htable.getRowCount()-1;a>=0;a--){
							hmodel.removeRow(a);
						}
						String sql="select * from tbpharmacystore where invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' && type=1";
						System.out.println(sql);
						ResultSet rs1=db.sta.executeQuery(sql);
						while(rs1.next()){
							model.addRow(new Object[]{rs1.getString("autoId"),rs1.getString("ProductName"),rs1.getString("qty"),rs1.getString("buyPrice"),rs1.getString("totalPrice"),new ImageIcon("icon/delete.png")});
						}	
						loaddRow();
						String sql1="select tbinvoice.invoiceNo,"
								+ "tbinvoice.customer,"
								+ "tbinvoice.amount,"
								+ "tbinvoice.discountPer,"
								+ "tbinvoice.discountManual,"
								+ "tbinvoice.netAmount, "
								+ "tbinvoice.cash,"
								+ "tbinvoice.card,"
								+ "tbinvoice.paid,"
								+ "tbinvoice.date"
								+ " from tbinvoice where tbinvoice.invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' && tbinvoice.type=1";
						System.out.println("sql1 "+sql1);
						ResultSet rs=db.sta.executeQuery(sql1);
						while(rs.next()){
							txtInvoiceNo.setText(rs.getString("invoiceNo"));
							cmbSupplierName.txtMrNo.setText(rs.getString("customer"));
							txtGrossAmount.setText(rs.getString("amount"));
							txtDiscountPercent.setText(rs.getString("discountPer"));
							txtManualDiscount.setText(rs.getString("discountManual"));
							txtNetAmount.setText(rs.getString("netAmount"));
							txtPaymentAmount.setText(rs.getString("paid"));
							//txtBankName.setText(rs.getString("card_type"));
							txtdate.setDate(rs.getDate("date"));
							double due=Double.parseDouble(rs.getString("netAmount"))-Double.parseDouble(rs.getString("paid"));
							hmodel.addRow(new Object[]{rs.getDate("date"),rs.getString("cash"),rs.getString("card"),due});
							findId=1;
						}

						historyRow();
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}
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
				loadProductName();
			}
		});
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSubmitEvent();
				cmbProductName.txtMrNo.requestFocusInWindow();
			}
		});
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtClear();
			}
		});
		btnConfrim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnConfrimEvent();
			}
		});
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSearchEvent();
			}
		});
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				search=0;
				autoInvoice();
				txtVoucherNo.setText("");
				cmbLedgerName.setSelectedItem("");
				txtPrice.setText("");
				txtQty.setText("");
				for(int a=table.getRowCount()-1;a>=0;a--){
					model.removeRow(a);
				}
				loaddRow();
				findId=0;
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
				cmbProductBrand.txtMrNo.setText("");
				cmbSupplierName.txtMrNo.setText("");
				cmbProductName.txtMrNo.setText("");
				txtShortBrandName.setText("");
				cmbunit.setSelectedItem("");
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
	public void btnSearchEvent() {
		if(!txtVoucherNo.getText().toString().isEmpty()){
			try {
				int temp=0;
				for(int a=table.getRowCount()-1;a>=0;a--){
					model.removeRow(a);
				}
				ResultSet rs=db.sta.executeQuery("select voucherNo from tbstore");
				while(rs.next()){
					if(txtVoucherNo.getText().toString().equalsIgnoreCase(rs.getString("voucherNo"))){
						temp=1;
					}
				}
				if(temp!=0){

					ResultSet rs1=db.sta.executeQuery("select *from tbstore where voucherNo='"+txtVoucherNo.getText().toString()+"' and type=1");
					while(rs1.next()){
						model.addRow(new Object[]{rs1.getString("autoId"),rs1.getString("productName"),rs1.getString("qty"),rs1.getString("buyPrice"),rs1.getString("totalPrice"),new ImageIcon("icon/delete.png")});
					}
					loaddRow();
				}
				else{
					loaddRow();
					JOptionPane.showMessageDialog(null, "There ara no data found");
				}

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error!!,"+e);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warring!!, Please provide Voucher No");
		}
	}
	private boolean UpInvoicedoplicateName(){
		try {
			ResultSet rs=db.sta.executeQuery("select productName,buyPrice from tbpharmacystore where type='1' and invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"'");
			while(rs.next()){
				if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
					return true;
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;
	}
	private boolean InvoicedoplicateName(){
		try {
			ResultSet rs=db.sta.executeQuery("select productName,buyPrice from tbpharmacystore where type='1' and invoiceNo IS NULL");
			while(rs.next()){
				if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
					return true;
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;
	}
	public void btnSubmitEvent() {
		if(!cmbSupplierName.txtMrNo.getText().toString().isEmpty()){
			if(!cmbProductName.txtMrNo.getText().toString().isEmpty()){
				Insertdata();
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Product Name");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Supplier Name");
		}
	}
	private void Insertdata(){
		try {
			if(findId==1){
				if(! UpInvoicedoplicateName()){
					productDescription();
					autoId();
					double price=0.0,totalPrice=0.0;
					int qty=0;
					qty=Integer.parseInt(txtQty.getText().trim().toString());
					price=Double.parseDouble(txtPrice.getText().trim().toString());
					totalPrice=totalqty*price;
					String sql="insert into tbpharmacystore (autoId,productId,productName,unit,catagoryId,qty,buyPrice,totalPrice,type,date,invoiceNo,entryTime,createBy) values('"+autoId+"',"
							+ "'"+productId+"',"
							+ "'"+cmbProductName.txtMrNo.getText().toString()+"',"
							+ "'"+unit+"',"
							+ "'"+catagoryid+"',"
							+ "'"+totalqty+"',"
							+ "'"+price+"',"
							+ "'"+df.format(totalPrice)+"',"
							+ "'"+1+"',"
							+ "'"+startDate+"',"
							+ "'"+txtInvoiceNo.getText().toString()+"',"
							+ "'"+startDate+"',"
							+ "'"+sessionbeam.getUserId()+"')";
					System.out.println(sql);
					db.sta.executeUpdate(sql);

					JOptionPane.showMessageDialog(null, "Product Purchase Successfully");
					UpdateInvoieValue();
					GrossAmount();
					txtDiscountPercent.setText("0");
					txtManualDiscount.setText("0");
					ProductTxtClear();
					//txtCard.setText("0");
				}
				else{

					double price=0.0,totalPrice=0.0;
					int qty=0;
					qty=Integer.parseInt(txtQty.getText().trim().toString());
					price=Double.parseDouble(txtPrice.getText().trim().toString());
					ResultSet rs=db.sta.executeQuery("select qty from tbpharmacystore where productName='"+cmbProductName.txtMrNo.getText().toString()+"' && type='1' && invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"'");
					while(rs.next()){
						totalqty=totalqty+Integer.parseInt(rs.getString("qty"));
					}
					totalPrice=totalqty*price;
					String query="update tbpharmacystore set qty='"+totalqty+"',buyPrice='"+price+"',totalPrice='"+df.format(totalPrice)+"' where productName='"+cmbProductName.txtMrNo.getText().toString()+"' && type='1' && invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"'";
					System.out.println(query);
					db.sta.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Product Order Successfully Accept");
					UpdateInvoieValue();
					GrossAmount();
					ProductTxtClear();
				}
			}
			else{
				if(!InvoicedoplicateName()){
					productDescription();
					autoId();
					double price=0.0,totalPrice=0.0;
					int qty=0;
					qty=Integer.parseInt(txtQty.getText().trim().toString());
					price=Double.parseDouble(txtPrice.getText().trim().toString());
					totalPrice=totalqty*price;

					String sql="insert into tbpharmacystore (autoId,productId,productName,unit,catagoryId,qty,buyPrice,totalPrice,type,date,entryTime,createBy) values('"+autoId+"',"
							+ "'"+productId+"',"
							+ "'"+cmbProductName.txtMrNo.getText().toString()+"',"
							+ "'"+cmbunit.getSelectedItem().toString()+"',"
							+ "'"+catagoryid+"',"
							+ "'"+totalqty+"',"
							+ "'"+price+"',"
							+ "'"+df.format(totalPrice)+"',"
							+ "'"+1+"',"
							+ "'"+startDate+"',"
							+ "'"+startDate+"',"
							+ "'"+sessionbeam.getUserId()+"')";
					System.out.println(sql);
					db.sta.executeUpdate(sql);
					//db.sta.executeUpdate("update tbiteminformation set updatePrice='"+price+"' where productName='"+cmbProductName.txtMrNo.getText().trim().toString()+"'");
					JOptionPane.showMessageDialog(null, "Product Purchase Successfully Accept");
					tableValue();
					GrossAmount();
					ProductTxtClear();
				}
				else{
					double price=0.0,totalPrice=0.0;
					int qty=0;
					qty=Integer.parseInt(txtQty.getText().trim().toString());
					price=Double.parseDouble(txtPrice.getText().trim().toString());

					ResultSet rs=db.sta.executeQuery("select qty from tbpharmacystore where productName='"+cmbProductName.txtMrNo.getText().toString()+"' && type='1' && invoiceNo IS NULL");
					while(rs.next()){
						totalqty=totalqty+Integer.parseInt(rs.getString("qty"));
					}
					totalPrice=totalqty*price;
					String query="update tbpharmacystore set qty='"+totalqty+"',buyPrice='"+price+"',totalPrice='"+df.format(totalPrice)+"' where productName='"+cmbProductName.txtMrNo.getText().toString()+"' && type='1' && invoiceNo IS NULL";
					System.out.println(query);
					db.sta.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Product Order Successfully Accept");
					tableValue();
					GrossAmount();
					ProductTxtClear();
				}

				//txtClear();
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnConfrimEvent() {
		if(!cmbSupplierName.txtMrNo.getText().toString().isEmpty()){
			if(txtdate.getDate()!=null){
				if(Double.parseDouble(txtGrossAmount.getText().toString())>0){
					try {
						String mobile="",supplierId="";
						double tDiscount=0;
						double paid=0;
						double grossAmount=Double.parseDouble(txtGrossAmount.getText().trim().toString());
						double netAmount=grossAmount-grossAmount*Integer.parseInt(txtDiscountPercent.getText().trim().toString())/100;
						System.out.println("net"+netAmount);
						paid=Double.parseDouble(txtPaymentAmount.getText().toString());
						tDiscount=Double.parseDouble(lblDiscountAmount.getText().toString())+Double.parseDouble(txtManualDiscount.getText().toString());
						if(paid>Double.parseDouble(txtNetAmount.getText().trim().toString())){
							JOptionPane.showMessageDialog(null, "Paid Amount Can't Be More Than Net Amount");
						}
						else{
							if(findId==1){
								int confrim=JOptionPane.showConfirmDialog(null, "Are you Sure To Confrim Purchase Invoice","Confrim.........",JOptionPane.YES_NO_OPTION);
								if(confrim==JOptionPane.YES_OPTION){
									double CashAmount=0.0,CardAmount=0.0;
									if(cmbType.getSelectedIndex()==0){
										CashAmount=Double.parseDouble(txtPaymentAmount.getText().trim().toString());
										CardAmount=0;
									}
									String sql="update tbinvoice set amount='"+txtGrossAmount.getText().toString()+"',netAmount='"+txtNetAmount.getText().toString()+"',discountPer='"+txtDiscountPercent.getText().toString()+"',discountManual='"+txtManualDiscount.getText().toString()+"',discount='"+tDiscount+"',paid='"+paid+"',cash='"+CashAmount+"',card='"+CardAmount+"',date='"+new SimpleDateFormat("yyyy-MM-dd").format(txtdate.getDate())+"',entryTime='"+startDate+"',createBy='"+sessionbeam.getUserId()+"' where invoiceNo='"+txtInvoiceNo.getText().toString()+"' && type=1";
									System.out.println(sql);
									db.sta.executeUpdate(sql);

									for(int i=0;i<table.getRowCount();i++){
										if(table.getValueAt(i, 0).toString()!=""){
											double discount=tDiscount*Double.parseDouble(table.getValueAt(i, 4).toString())/Double.parseDouble(txtGrossAmount.getText().toString());
											System.out.println("discount"+discount);
											String query="update tbpharmacystore set discount='"+discount+"',status='1',entryTime='"+startDate+"',createBy='"+sessionbeam.getUserId()+"' where autoId='"+table.getValueAt(i, 0)+"'";
											System.out.println(query);
											db.sta.executeUpdate(query);
										}
									}

									autoSupplier();
									if(!checkDoplicateSupplierName()){
										String insetQuery="insert into tbsupplierinfo values ('"+supId+"','"+cmbSupplierName.txtMrNo.getText().trim().toString()+"',NOW(),'"+sessionbeam.getUserId()+"')";
										System.out.println(insetQuery);
										db.sta.executeUpdate(insetQuery);
									}
									JOptionPane.showMessageDialog(null, "Purchase Transection Complete");
									ClearAfterConfrim();
									findId=0;
								}
							}
							else{
								int confrim=JOptionPane.showConfirmDialog(null, "Are you Sure To Confrim Purchase Invoice","Confrim.........",JOptionPane.YES_NO_OPTION);
								if(confrim==JOptionPane.YES_OPTION){

									double Cashamount=0.0,CardAmount=0.0;
									autoinvoiceId();
									if(cmbType.getSelectedIndex()==0){
										Cashamount=Double.parseDouble(txtPaymentAmount.getText().trim().toString());
										CardAmount=0.0;
									}
									String d_l_id="",c_l_id="",ledgerId="";
									String sql="insert into tbinvoice (invoiceNo,Customer,type,amount,netAmount,discountPer,discountManual,discount,paid,cash,card,p_type,remark,date,entryTime,createBy) " +
											"values('"+invoiceid+"'," +
											"'"+cmbSupplierName.txtMrNo.getText().trim().toString()+"'," +
											"'"+1+"',"
											+ "'"+txtGrossAmount.getText().trim().toString()+"'," +
											"'"+txtNetAmount.getText().trim().toString()+"',"
											+ "'"+txtDiscountPercent.getText().trim().toString()+"'," +
											"'"+txtManualDiscount.getText().trim().toString()+"',"
											+ "'"+tDiscount+"'," +
											"'"+paid+"'," +
											"'"+Cashamount+"'," +
											"'"+CardAmount+"'," +
											"'"+cmbType.getSelectedItem().toString()+"'," +
											"'"+txtNote.getText().toString()+"'," +
											"'"+startDate+"'," +
											"'"+startDate+"'," +
											"'"+sessionbeam.getUserId()+"')";
									System.out.println(sql);
									db.sta.executeUpdate(sql);

									for(int i=0;i<table.getRowCount();i++){
										if(table.getValueAt(i, 0).toString()!=""){
											double discount=tDiscount*Double.parseDouble(table.getValueAt(i, 4).toString())/Double.parseDouble(txtGrossAmount.getText().toString());
											System.out.println("discount"+discount);
											String query="update tbpharmacystore set discount='"+discount+"',invoiceNo='"+invoiceid+"',status='1',entryTime='"+startDate+"',createBy='"+sessionbeam.getUserId()+"' where autoId='"+table.getValueAt(i, 0)+"'";
											System.out.println(query);
											db.sta.executeUpdate(query);
										}
									}			
									autoSupplier();
									if(!checkDoplicateSupplierName()){
										String insetQuery="insert into tbsupplierinfo values ('"+supId+"','"+cmbSupplierName.txtMrNo.getText().trim().toString()+"',NOW(),'"+sessionbeam.getUserId()+"')";
										System.out.println(insetQuery);
										db.sta.executeUpdate(insetQuery);
									}

									loadSuppplier();
									JOptionPane.showMessageDialog(null, "Purchase Transection Complete!!");
									autoInvoice();
									tableValue();
									txtClear();
								}

							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warring!!,Sorry No Product Order!!");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warring!!,Please Provide Date");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Please Provide Supplier Name");
		}
	}
	public void loadSuppplier(){
		try {
			cmbSupplierName.v.clear();
			ResultSet rs=db.sta.executeQuery("select tbsupplierinfo.supplierName from tbsupplierinfo");
			cmbSupplierName.v.add("");
			while(rs.next()){
				cmbSupplierName.v.add(rs.getString("supplierName"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void autoinvoiceId(){
		try {
			String sql="select (ifnull(max(invoiceNo),0)+1)as invoiceNo from tbinvoice where type=1 ";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				invoiceid=rs.getString("invoiceNo");

			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void GrossAmount(){
		double sum=0;
		for(int a=0;a<table.getRowCount();a++){
			if(table.getValueAt(a, 4)!=""){
				sum=sum+Double.parseDouble(table.getValueAt(a, 4).toString());
			}
		}
		txtGrossAmount.setText(df.format(sum));
		txtNetAmount.setText(df.format(sum));
		txtPaymentAmount.setText(new DecimalFormat("##.##").format(sum));
	}
	public void tableValue(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select * from tbpharmacystore where invoiceNo IS NULL and type=1");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("autoId"),rs.getString("productName"),rs.getString("qty"),rs.getString("buyPrice"),rs.getString("totalPrice"),new ImageIcon("icon/delete.png")});
			}
			loaddRow();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void UpdateInvoieValue(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select * from tbpharmacystore where (invoiceNo='"+txtInvoiceNo.getText().toString()+"' or invoiceNo IS NULL) and type=1");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("autoId"),rs.getString("productName"),rs.getString("qty"),rs.getString("buyPrice"),rs.getString("totalPrice"),new ImageIcon("icon/delete.png")});
			}
			loaddRow();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void productDescription(){
		try {
			ResultSet rs=db.sta.executeQuery("select *from tbiteminformation where tbiteminformation.ProductName='"+cmbProductName.txtMrNo.getText().trim().toString()+"'");
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
	public void loadBrandName(){
		try {
			cmbProductBrand.v.clear();
			cmbProductBrand.v.add("");

			ResultSet rs=db.sta.executeQuery("select tbbrandinfo.brandName from tbbrandinfo");
			while(rs.next()){
				cmbProductBrand.v.add(rs.getString("brandName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void loadProductName(){
		try {

			if(!cmbProductBrand.txtMrNo.getText().trim().toString().isEmpty()  ){
				cmbProductName.v.clear();
				cmbProductName.v.add("");
				ResultSet rs=db.sta.executeQuery("select ProductName from tbiteminformation where brandid=(select tbbrandinfo.brandId from tbbrandinfo where tbbrandinfo.brandName='"+cmbProductBrand.txtMrNo.getText().trim().toString()+"')");
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
	public void loadTableProductName(){
		/*		try {
			txtpr.removeAllItems();
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
	/*	public void loadSupplierName(){
		try {
			ResultSet rs=db.sta.executeQuery("select tboutsupplierinfo.supplierName from tboutsupplierinfo");
			cmbSupplierName.v.clear();
			cmbSupplierName.v.add("");
			while(rs.next()){
				cmbSupplierName.v.add(rs.getString("supplierName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}*/
	public void autoInvoice(){
		try {
			String sql="select (ifnull(max(invoiceNo),0)+1)as invoiceNo from tbinvoice where type=1";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				txtInvoiceNo.setText(rs.getString("invoiceNo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void autoSupplier(){
		try {
			String sql="select (ifnull(max(supplierId),0)+1)as supplierId from tbsupplierinfo";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				supId=rs.getString("supplierId");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private boolean checkDoplicateSupplierName(){
		try {
			ResultSet rs=db.sta.executeQuery("select tbsupplierinfo.supplierName from tbsupplierinfo");
			while(rs.next()){
				if(cmbSupplierName.txtMrNo.getText().trim().toString().equalsIgnoreCase(rs.getString("supplierName"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	private boolean checkTotal() {
		try {
			ResultSet rs=db.sta.executeQuery("select (select sum(accftransection.amount) from accftransection where voucherNo='"+txtVoucherNo.getText().toString()+"' and d_l_id!=0)-(select sum(accftransection.amount) from accftransection where voucherNo='"+txtVoucherNo.getText().toString()+"' and c_l_id!=0)as total from accftransection ");
			while(rs.next()){
				if(rs.getString("total").toString().equals("0")){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
		txtVoucherNo.setText("");
		cmbSupplierName.txtMrNo.setText("");
		cmbProductName.txtMrNo.setText("");
		txtPrice.setText("");
		txtQty.setText("");
		txtGrossAmount.setText("0");
		txtDiscountPercent.setText("0");
		txtManualDiscount.setText("0");
		txtNetAmount.setText("0");
		txtPaymentAmount.setText("0");
		lblDiscountAmount.setText("0");
		txtNote.setText("");
	}
	public void addcmp() {
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(1250,640));
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
		//mainNorthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		//mainNorthPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 710, new Color(214, 217, 223)));
		mainNorthPanel.add(NorthWestPanel,BorderLayout.WEST);
		NorthWestPanel.setOpaque(false);
		NorthWestPanel_work();
		mainNorthPanel.add(NorthEastPanel,BorderLayout.EAST);
		NorthEastPanel.setOpaque(false);
		NorthEastPanel_work();
	}
	private void NorthWestPanel_work() {
		NorthWestPanel.setPreferredSize(new Dimension(400,245));
		//NorthWestPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		NorthWestPanel.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 0, 2,1);
		NorthWestPanel.add(lblInNo,grid);
		lblInNo.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=0;
		NorthWestPanel.add(txtInvoiceNo,grid);
		txtInvoiceNo.setForeground(Color.WHITE);
		txtInvoiceNo.setBackground(new Color(148, 86, 97));
		txtInvoiceNo.setFont(new Font("arial",Font.BOLD,15));
		//txtInvoiceNo.setEditable(false);
		txtInvoiceNo.setPreferredSize(new Dimension(180,30));
		txtInvoiceNo.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		grid.gridx=0;
		grid.gridy=1;
		NorthWestPanel.add(lblSupplier,grid);
		lblSupplier.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=1;
		NorthWestPanel.add(cmbSupplierName.combo,grid);
		cmbSupplierName.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbSupplierName.combo.setPreferredSize(new Dimension(180,30));
		/*		grid.gridx=0;
		grid.gridy=2;
		NorthWestPanel.add(lblExpireDate,grid);
		grid.gridx=1;
		grid.gridy=2;
		NorthWestPanel.add(txtExpireDate,grid);
		txtExpireDate.setFont(new Font("arial",Font.BOLD,14));
		txtExpireDate.setPreferredSize(new Dimension(180,30));
		txtExpireDate.setDate(new Date());
		txtExpireDate.setDateFormatString("dd-MM-yyyy");
		txtExpireDate.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));*/
		grid.gridx=0;
		grid.gridy=2;
		NorthWestPanel.add(lblBrandName,grid);
		grid.gridx=1;
		grid.gridy=2;
		NorthWestPanel.add(cmbProductBrand.combo,grid);
		cmbProductBrand.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbProductBrand.combo.setPreferredSize(new Dimension(180,30));
		
		grid.gridx=0;
		grid.gridy=3;
		NorthWestPanel.add(lblModelName,grid);
		grid.gridx=1;
		grid.gridy=3;
		NorthWestPanel.add(cmbModelName.combo,grid);
		cmbModelName.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbModelName.combo.setPreferredSize(new Dimension(180,30));
	}
	private void NorthEastPanel_work() {
		NorthEastPanel.setPreferredSize(new Dimension(700,245));
		//NorthEastPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		FlowLayout flow=new FlowLayout();
		NorthEastPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		/*		NorthEastPanel.add(lblStartDate);
		NorthEastPanel.add(txtStartDate);
		txtStartDate.setDateFormatString("dd-MM-yyyy");
		txtStartDate.setPreferredSize(new Dimension(160,28));
		txtStartDate.setDate(new Date());

		NorthEastPanel.add(lblEndDate);
		NorthEastPanel.add(txtEndDate);
		txtEndDate.setDateFormatString("dd-MM-yyyy");
		txtEndDate.setPreferredSize(new Dimension(160,28));
		txtEndDate.setDate(new Date());
		NorthEastPanel.add(scroll1);
		scroll1.setPreferredSize(new Dimension(690,60));
		table1.getTableHeader().setReorderingAllowed(false);
		table1.setRowHeight(table1.getRowHeight()+10);
		table1.getColumnModel().getColumn(0).setPreferredWidth(100);
		table1.getColumnModel().getColumn(1).setPreferredWidth(220);
		table1.getColumnModel().getColumn(2).setPreferredWidth(90);
		table1.getColumnModel().getColumn(3).setPreferredWidth(90);
		table1.getColumnModel().getColumn(4).setPreferredWidth(90);
		table1.getColumnModel().getColumn(5).setPreferredWidth(90);
		table1.getColumnModel().getColumn(6).setPreferredWidth(90);
		table1.getColumnModel().getColumn(7).setPreferredWidth(110);
		table1.setShowGrid(true);
		table1.setOpaque(false);
		scroll1.setOpaque(false);
		scroll1.getViewport().setOpaque(false);
		table1.setSelectionForeground(Color.red);
		table1.setFont(new Font("arial", Font.BOLD, 13));*/

		NorthEastPanel.add(hScroll);
		hScroll.setPreferredSize(new Dimension(690,100));
		htable.getTableHeader().setReorderingAllowed(false);
		htable.setRowHeight(htable.getRowHeight()+10);
		htable.getColumnModel().getColumn(0).setPreferredWidth(100);
		htable.getColumnModel().getColumn(1).setPreferredWidth(95);
		htable.getColumnModel().getColumn(2).setPreferredWidth(95);
		htable.getColumnModel().getColumn(3).setPreferredWidth(95);
		htable.setSelectionForeground(Color.white);
		htable.setFont(new Font("arial", Font.BOLD, 14));
		htable.setShowGrid(true);
		htable.setOpaque(false);
		hScroll.setOpaque(false);
		hScroll.getViewport().setOpaque(false);


		NorthEastPanel.add(lblDuePaymentType);
		NorthEastPanel.add(cmbDueType);
		cmbDueType.setPreferredSize(new Dimension(150,28));

		NorthEastPanel.add(lblDuePaymentAmount);
		lblDuePaymentAmount.setFont(new Font("arial",Font.BOLD,13));
		NorthEastPanel.add(txtDuePaymentAmount);
		txtDuePaymentAmount.setPreferredSize(new Dimension(150,30));

		NorthEastPanel.add(btnDueSubmit);
		btnDueSubmit.setMnemonic(KeyEvent.VK_D);
		btnDueSubmit.setPreferredSize(new Dimension(130,36));
		historyRow();
	}
	private void historyRow(){
		for(int a=0;a<5;a++){
			hmodel.addRow(new Object[]{"","","",""});
		}

	}
	private void mainCenterPanel_work() {
		mainCenterPanel.setPreferredSize(new Dimension(1345,45));
		mainCenterPanel.setBorder(BorderFactory.createEmptyBorder(5, 22, 0,0));
		FlowLayout flow=new FlowLayout();
		mainCenterPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		mainCenterPanel.add(lblProductName);
		lblProductName.setFont(new Font("arial",Font.BOLD,13));
		mainCenterPanel.add(cmbProductName.combo);
		cmbProductName.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbProductName.combo.setPreferredSize(new Dimension(210,30));

		//mainCenterPanel.add(txtShortBrandName);
		txtShortBrandName.setEditable(false);
		txtShortBrandName.setFont(new Font("arial",Font.BOLD,14));
		txtShortBrandName.setPreferredSize(new Dimension(200,30));

		mainCenterPanel.add(lblQty);
		lblQty.setFont(new Font("arial",Font.BOLD,13));
		mainCenterPanel.add(txtQty);
		txtQty.setFont(new Font("arial",Font.BOLD,14));
		txtQty.setPreferredSize(new Dimension(140,30));

		mainCenterPanel.add(lblPrice);
		lblPrice.setFont(new Font("arial",Font.BOLD,13));
		mainCenterPanel.add(txtPrice);
		txtPrice.setEditable(false);
		txtPrice.setFont(new Font("arial",Font.BOLD,14));
		txtPrice.setPreferredSize(new Dimension(140,30));

		mainCenterPanel.add(btnSubmit);
		btnSubmit.setPreferredSize(new Dimension(100,36));
		btnSubmit.setMnemonic(KeyEvent.VK_S);
	}
	private void mainSouthPanel_work() {
		mainSouthPanel.setPreferredSize(new Dimension(1345,445));
		mainSouthPanel.setLayout(new BorderLayout());
		mainSouthPanel.add(southToppanel,BorderLayout.NORTH);
		southToppanel.setOpaque(false);
		southToppanel_work();
		mainSouthPanel.add(southTopDown,BorderLayout.SOUTH);
		southTopDown.setOpaque(false);
		southTopDown_work();
	}
	private void southTopDown_work() {
		southTopDown.setPreferredSize(new Dimension(10, 85));
		southTopDown.setBorder(BorderFactory.createRaisedBevelBorder());
		southTopDown.setLayout(new FlowLayout());
		southTopDown.add(lblNote);
		southTopDown.add(txtNote);
		txtNote.setPreferredSize(new Dimension(600, 32));
		txtNote.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtNote.setFont(new Font("arial", Font.BOLD, 14));
		txtNote.setForeground(Color.blue);
		southTopDown.add(btnConfrim);
		btnConfrim.setPreferredSize(new Dimension(100, 36));
		btnConfrim.setMnemonic(KeyEvent.VK_C);
		southTopDown.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(100, 36));
		btnRefresh.setMnemonic(KeyEvent.VK_R);
	}
	private void southToppanel_work() {
		southToppanel.setPreferredSize(new Dimension(10, 340));
		southToppanel.setLayout(new BorderLayout());
		southToppanel.add(scroll,BorderLayout.WEST);
		scroll.setPreferredSize(new Dimension(790, 340));
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(true);
		table.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete Item","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						String sql="delete from tbpharmacystore where autoId='"+table.getValueAt(table.getSelectedRow(), 0)+"'";
						System.out.println("sql "+sql);
						db.sta.executeUpdate(sql);
						model.removeRow(table.getSelectedRow());
						GrossAmount();
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		};
		ButtonColumn btnUpdate = new ButtonColumn(table, delete, 5);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(34);
		for(int i=2;i<5;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(txtTableProductName));
		table.setSelectionForeground(Color.white);
		table.setFont(new Font("arial", Font.BOLD, 14));
		table.setRowHeight(table.getRowHeight() + 10);
		southToppanel.add(southTopEastpanel, BorderLayout.EAST);
		southTopEastpanel_work();
	}
	private void southTopEastpanel_work() {
		southTopEastpanel.setPreferredSize(new Dimension(460, 320));
		southTopEastpanel.setLayout(new GridBagLayout());
		southTopEastpanel.setOpaque(false);
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(4, 0, 4, 4);
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
		txtNetAmount.setEditable(false);
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
		txtPaymentAmount.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtPaymentAmount.setText("0");
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
