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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class WastageProduct extends JPanel{
	SessionBeam sessionbeam;
	db_coonection db=new db_coonection();
	public JPanel mainPanel=new JPanel();
	public JPanel mainNorthPanel=new JPanel();
	public JPanel northTopPanel=new JPanel();
	public JPanel northCenterPanel=new JPanel();
	public JPanel northSouthPanel=new JPanel();
	public JPanel mainCenterPanel=new JPanel();
	public JPanel mainSouthPanel=new JPanel();
	JLabel lblUnit=new JLabel("<html><font color=red>*</font>Unit</html>");
	JLabel lblQtyPerpack=new JLabel("Qty Per Pack");
	JLabel lblPricePerQty=new JLabel("Price Per Qty");
	
	String Unit[]={"","Pcs","Strip","Box"};
	JComboBox cmbunit=new JComboBox(Unit);
	
	JTextField txtNote=new JTextField(60);
	
	JTextField txtQtyPerpack=new JTextField(5);
	JTextField txtPricePerQty=new JTextField(8);
	
	JTextField txtInvoice=new JTextField(16);
	SuggestText cmbProductName=new SuggestText();
	JTextField txtQty=new JTextField(5);
	JTextField txtPrice=new JTextField(8);

	JDateChooser txtDate=new JDateChooser();

	JTextField txtPatientName=new JTextField(15);
	JTextField txtRegNo=new JTextField(15);



	ButtonGroup gpIn=new ButtonGroup();
	ButtonGroup gpOut=new ButtonGroup();

	GridBagConstraints grid=new GridBagConstraints();
	String col[]={"           AUTO ID","                 NAME","        QTY","         PRICE","      TOTAL","      DATE","DEL"};
	Object row[][]={};
	public DefaultTableModel model=new DefaultTableModel(row,col);
	public JTable table=new JTable(model){
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
			
			if(col==2 || col==3 || col==6){
				return true;
			}
			return false;
		}
	};
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JLabel lblNote=new JLabel("Remark");
	JLabel lblInvoice=new JLabel("<html><font color=red>*</font>Invoice No</html>");
	JLabel lblProdcutName=new JLabel("<html><font color=red>*</font>Product Name</html>");
	JLabel lblQty=new JLabel("Qty");
	JLabel lblDate=new JLabel("<html><font color=red>*</font>Date</html>");
	JLabel lblPrice=new JLabel("<html><font color=red>*</font>Price</html>");
	JButton btnSubmit=new JButton("Submit",new ImageIcon("icon/save.png"));
	JButton btnConfrim=new JButton("Confrim",new ImageIcon("icon/save.png"));
	String autoId="",startDate="",inPartyId="",outPartyId="",tranId="";
	String productId="",catagoryid="";
	int totalqty=0;
	BufferedImage image;
	public WastageProduct(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addcmp();
		background();
		loadProductName();
		btnActionEvent();
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
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
		DateMidnight now = new DateMidnight();
		//DateMidnight beginningOfLastMonth = now.minusMonths(0).withDayOfMonth(1);
		DateMidnight endOfLastMonth = now.withDayOfMonth(1).minusDays(1);
		txtDate.setDate(date);
	}
	public void btnActionEvent(){
		cmbProductName.txtMrNo.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c=e.getKeyChar();

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

		txtQty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					double price=0.0;
					int perqty=0;
					ResultSet rs=db.sta.executeQuery("select tbiteminformation.buyPrice from tbiteminformation where tbiteminformation.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+cmbProductName.txtMrNo.getText().trim().toString()+"')");
					while(rs.next()){
						price=Double.parseDouble(rs.getString("buyPrice"));
						System.out.println("perqty "+perqty);
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
		});;
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSubmitEvent();
			}
		});
		btnConfrim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnConfrimEvent();
			}
		});
	}
	protected void btnConfrimEvent() {
		if(txtDate.getDate()!=null){
			if(!txtInvoice.getText().toString().isEmpty()){
				try {
					double totalPrice=0.0;
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString()==""){
							break;
						}
						totalPrice=totalPrice+Double.parseDouble(table.getValueAt(a, 4).toString());
					}

					String invoice="insert into tbinvoice (invoiceNo,type,amount,date,remark,entryTime,createBy) values ('"+txtInvoice.getText().toString()+"','"+5+"','"+totalPrice+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate())+"','"+txtNote.getText().trim().toString()+"',NOW(),'"+sessionbeam.getUserId()+"')";
					System.out.println(invoice);
					db.sta.executeUpdate(invoice);

					String storeupdate="update tbpharmacystore set invoiceNo='"+txtInvoice.getText().toString()+"',status='1' where type=5 && invoiceNo IS NULL";
					System.out.println(storeupdate);
					db.sta.executeUpdate(storeupdate);

					JOptionPane.showMessageDialog(null, "Wastage Product Confrim Succesfully");
					autoinvoiceId();
					tableValue();
					txtPatientName.setText("");
					cmbProductName.txtMrNo.setText("");
					txtQty.setText("");
					txtPrice.setText("");

				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Invoice No");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please provide Date");
		}
	}
	public void btnSubmitEvent() {
		if(txtDate.getDate()!=null){
			if(!cmbProductName.txtMrNo.getText().toString().isEmpty()){
					Insertdata();
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Product Name");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please provide Date");
		}
	}
	private boolean InvoicedoplicateName(){
		try {
			ResultSet rs=db.sta.executeQuery("select productName from tbpharmacystore where type='5' and invoiceNo IS NULL");
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
	private void Insertdata() {
		try {
			date_take();
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
						+ "'"+totalPrice+"',"
						+ "'"+5+"',"
						+ "'"+startDate+"',"
						+ "'"+startDate+"',"
						+ "'"+sessionbeam.getUserId()+"')";
				System.out.println(sql);
				db.sta.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Product Purchase Successfully Accept");
				tableValue();
			}
			else{
				double price=0.0,totalPrice=0.0;
				int qty=0;
				qty=Integer.parseInt(txtQty.getText().trim().toString());
				totalPrice=Double.parseDouble(txtPrice.getText().trim().toString());
				price=totalPrice/qty;
				ResultSet rs=db.sta.executeQuery("select qty from tbpharmacystore where productName='"+cmbProductName.txtMrNo.getText().toString()+"' && type='5' && invoiceNo IS NULL");
				while(rs.next()){
					totalqty=totalqty+Integer.parseInt(rs.getString("qty"));
				}
				String query="update tbpharmacystore set qty='"+totalqty+"',buyPrice='"+price+"',totalPrice='"+price*qty+"' where productName='"+cmbProductName.txtMrNo.getText().toString()+"' && type='5' && invoiceNo IS NULL";
				System.out.println(query);
				db.sta.executeUpdate(query);
				JOptionPane.showMessageDialog(null, "Product Order Successfully Accept");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
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
	public void InvoiceAutoId(){
		try {
			String sql="select (ifnull(max(invoiceNo),0)+1)as invoiceNo from tbinvoice where type=5";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				txtInvoice.setText(rs.getString("invoiceNo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
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
	public void tableValue(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *from tbpharmacystore where invoiceNo IS NULL and type=5");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("autoId"),rs.getString("productName"),rs.getString("qty"),rs.getString("buyPrice"),rs.getString("totalPrice"),rs.getString("date"),new ImageIcon("icon/delete.png")});
			}
			rowAdd();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void loadProductName(){
		try {
			cmbProductName.v.clear();
			cmbProductName.v.add("");

			ResultSet rs=db.sta.executeQuery("select ProductName from tbiteminformation");
			while(rs.next()){
				cmbProductName.v.add(rs.getString("ProductName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void rowAdd(){
		for(int a=0;a<16;a++){
			model.addRow(new Object[]{"","","","","","",new ImageIcon("icon/delete.png")});
		}
	}
	public void autoinvoiceId(){
		try {
			String sql="select (ifnull(max(invoiceNo),0)+1)as invoiceNo from tbinvoice where type=5";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				txtInvoice.setText(rs.getString("invoiceNo"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}

	public void addcmp() {
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(1100,600));
		mainPanel.add(mainNorthPanel,BorderLayout.NORTH);
		mainNorthPanel.setOpaque(false);
		mainNorthPanel_work();
		mainPanel.add(mainCenterPanel,BorderLayout.CENTER);
		mainCenterPanel.setOpaque(false);
		mainCenterPanel_work();
		mainPanel.add(mainSouthPanel,BorderLayout.SOUTH);
		mainSouthPanel.setOpaque(false);
		mainSouthPanel_work();
	}
	private void mainNorthPanel_work() {
		mainNorthPanel.setPreferredSize(new Dimension(10, 140));
		//mainNorthPanel.setBorder(BorderFactory.createLineBorder(Color.red,2));
		mainNorthPanel.setLayout(new BorderLayout());
		mainNorthPanel.add(northTopPanel,BorderLayout.NORTH);
		northTopPanel_work();
		mainNorthPanel.add(northCenterPanel,BorderLayout.CENTER);
		northCenterPanel_work();
	}
	private void northTopPanel_work() {
		northTopPanel.setPreferredSize(new Dimension(10, 80));
		northTopPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 755));
		northTopPanel.setLayout(new GridBagLayout());
		northTopPanel.setOpaque(false);
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(5, 20, 1, 5);
		northTopPanel.add(lblInvoice, grid);
		lblInvoice.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=0;
		northTopPanel.add(txtInvoice, grid);
		txtInvoice.setEditable(false);
		txtInvoice.setPreferredSize(new Dimension(220, 32));
		txtInvoice.setFont(new Font("arial",Font.BOLD,14));
		txtInvoice.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		grid.gridx=0;
		grid.gridy=1;
		northTopPanel.add(lblDate, grid);
		lblDate.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=1;
		northTopPanel.add(txtDate, grid);		
		txtDate.setDateFormatString("dd-MM-yyyy");
		txtDate.setPreferredSize(new Dimension(220, 32));
		txtDate.setDate(new Date());
		txtDate.setFont(new Font("arial",Font.BOLD,14));
	}

	private void northCenterPanel_work() {
		northCenterPanel.setPreferredSize(new Dimension(10, 40));
		northCenterPanel.setOpaque(false);
		FlowLayout flow=new FlowLayout();
		northCenterPanel.setLayout(flow);
		flow.setAlignment(FlowLayout.LEFT);
		northCenterPanel.setBorder(BorderFactory.createEmptyBorder(0, 13, 0, 0));
		northCenterPanel.add(lblProdcutName);
		lblProdcutName.setFont(new Font("arial",Font.BOLD,13));
		northCenterPanel.add(cmbProductName.combo);
		cmbProductName.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbProductName.combo.setPreferredSize(new Dimension(200,32));

		
/*		northCenterPanel.add(lblUnit);
		lblUnit.setFont(new Font("arial",Font.BOLD,13));
		northCenterPanel.add(cmbunit);
		cmbunit.setFont(new Font("arial",Font.BOLD,14));
		cmbunit.setPreferredSize(new Dimension(90,32));*/
		
		northCenterPanel.add(lblQty);
		lblQty.setFont(new Font("arial",Font.BOLD,13));
		northCenterPanel.add(txtQty);
		txtQty.setFont(new Font("arial",Font.BOLD,14));
		txtQty.setPreferredSize(new Dimension(140,32));
		txtQty.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		
		northCenterPanel.add(lblPrice);
		lblPrice.setFont(new Font("arial",Font.BOLD,13));
		northCenterPanel.add(txtPrice);
		txtPrice.setEditable(false);
		txtPrice.setFont(new Font("arial",Font.BOLD,14));
		txtPrice.setPreferredSize(new Dimension(140,32));
		txtPrice.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		northCenterPanel.add(btnSubmit);
		btnSubmit.setPreferredSize(new Dimension(100,36));
		btnSubmit.setMnemonic(KeyEvent.VK_S);

	}
	private void mainCenterPanel_work() {
		mainCenterPanel.setPreferredSize(new Dimension(10, 400));
		mainCenterPanel.setLayout(new BorderLayout());
		table.setFont(new Font("arial", Font.BOLD, 12));
		mainCenterPanel.add(scroll);
		scroll.setPreferredSize(new Dimension(1330, 400));
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(160);
		table.getColumnModel().getColumn(6).setPreferredWidth(35);
		table.setShowGrid(true);
		table.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.setSelectionForeground(Color.white);
		table.setFont(new Font("arial", Font.BOLD, 14));
		
		for(int i=0;i<6;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setRowHeight(table.getRowHeight() + 12);
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
						tableValue();
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		};
		ButtonColumn btndelete = new ButtonColumn(table, delete, 6);
	}
	private void mainSouthPanel_work() {
		mainSouthPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		mainSouthPanel.setPreferredSize(new Dimension(10, 100));
		mainSouthPanel.setLayout(new FlowLayout());
		mainSouthPanel.add(lblNote);
		mainSouthPanel.add(txtNote);
		txtNote.setPreferredSize(new Dimension(200, 30));
		mainSouthPanel.add(btnConfrim);
		btnConfrim.setPreferredSize(new Dimension(100, 36));
		btnConfrim.setMnemonic(KeyEvent.VK_C);
	}
}
