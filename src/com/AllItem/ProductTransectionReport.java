package com.AllItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
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

public class ProductTransectionReport extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionbeam;

	public JPanel mainPanel=new JPanel();
	JPanel EditPanel=new JPanel();
	JPanel EditNorthPanel=new JPanel();
	JPanel EditCenterPanel=new JPanel();
	JPanel northPanel=new JPanel();
	JPanel centerPanel=new JPanel();
	JPanel southPanel=new JPanel();

	JLabel lblType=new JLabel("Type");
	JLabel lblPeroductName=new JLabel("Product Name");
	JLabel lblSupplier=new JLabel("Supplier");
	JLabel lblStartDate=new JLabel("Start Date");
	JLabel lblEndDate=new JLabel("End Date");
	String type[]={"","Purchase","Purchase Return","Sale","Sale Return"};
	JComboBox cmbType=new JComboBox(type);

	SuggestText cmbProductName=new SuggestText();

	JDateChooser txtStartDate=new JDateChooser();
	JDateChooser txtEndDate=new JDateChooser();
	JButton btnSearch=new JButton("Find",new ImageIcon("icon/find.png"));
	JButton btnClose=new JButton(new ImageIcon("icon/close.png"));
	GridBagConstraints grid=new GridBagConstraints();

	String col[]={"INVOICE NO","PRODUCT ID","      PRODUCT NAME","           Qty","           AMOUNT","              NAME","        ENTRY TIME","     BY","PRINT"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table=new JTable(model){
		@Override
		public boolean isCellEditable(int row,int col){
			col=convertColumnIndexToModel(col);
			row=convertRowIndexToModel(row);
			if(col==0){
				return false;
			}
			return true;
		}
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
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String startDate="";
	int bercode=0,Choose=0;
	String tablecatagory="",tablesubcatagory="";

	BufferedImage image;
	public ProductTransectionReport(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addcmp();
		btnActionEvent();
		loadProductName();
		background();
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
	public void hitMethod(){
		rowadd();
		date_take();
	}
	public void loadProductName(){
		try {
			cmbProductName.v.clear();
			cmbProductName.v.add("");
			ResultSet rs=db.sta.executeQuery("SELECT tbiteminformation.productCode from tbiteminformation");
			while(rs.next()){
				cmbProductName.v.add(rs.getString("productCode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnActionEvent(){
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EditPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//InvoiceEdit in=new InvoiceEdit(sessionbeam);
				submitBtnEvent();
			}
		});
		cmbProductName.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c=e.getKeyChar();
				if(c==KeyEvent.VK_ENTER){
					submitBtnEvent();
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
	}
	private void submitBtnEvent() {
		try {
			refreshTable();
			if(cmbType.getSelectedIndex()!=0){
				if(txtStartDate.getDate()!=null){
					if(txtEndDate.getDate()!=null){
						try {
							if(cmbType.getSelectedIndex()==1){
								if(!cmbProductName.txtMrNo.getText().trim().toString().isEmpty()){
									ResultSet rs=db.sta.executeQuery("select tbpharmacystore.productId, tbpharmacystore.productName,tbpharmacystore.qty,tbpharmacystore.totalPrice,tbpharmacystore.invoiceNo,tbpharmacystore.entryTime,(select tblogin.username from tblogin where tblogin.user_id=tbpharmacystore.createBy)as user,tbinvoice.customer as suppliername from  tbinvoice join  tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo where tbinvoice.`type`=1 && tbpharmacystore.`type`=1 && tbpharmacystore.productName='"+cmbProductName.txtMrNo.getText().trim().toString()+"' && tbpharmacystore.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by  tbpharmacystore.invoiceNo ");
									while(rs.next()){
										model.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("productId"),rs.getString("productName"),rs.getString("qty"),rs.getString("totalPrice"),rs.getString("suppliername"),rs.getString("entryTime"),rs.getString("user"),new ImageIcon("icon/cprint.png")});
									}
									rowadd();
								}
								else{
									ResultSet rs=db.sta.executeQuery("select tbpharmacystore.productId, tbpharmacystore.productName,tbpharmacystore.qty,tbpharmacystore.totalPrice,tbpharmacystore.invoiceNo,tbpharmacystore.entryTime,(select tblogin.username from tblogin where tblogin.user_id=tbpharmacystore.createBy)as user,tbinvoice.customer as suppliername from  tbinvoice join  tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo where tbinvoice.`type`=1 && tbpharmacystore.`type`=1 && tbpharmacystore.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by  tbpharmacystore.invoiceNo ");
									while(rs.next()){
										model.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("productId"),rs.getString("productName"),rs.getString("qty"),rs.getString("totalPrice"),rs.getString("suppliername"),rs.getString("entryTime"),rs.getString("user"),new ImageIcon("icon/cprint.png")});
									}
									rowadd();
								}
							}
							if(cmbType.getSelectedIndex()==2){
								if(!cmbProductName.txtMrNo.getText().trim().toString().isEmpty()){
									ResultSet rs=db.sta.executeQuery("select tbpharmacystore.productId, tbpharmacystore.productName,tbpharmacystore.qty,tbpharmacystore.totalPrice,tbpharmacystore.invoiceNo,tbpharmacystore.entryTime,(select tblogin.username from tblogin where tblogin.user_id=tbpharmacystore.createBy)as user,tbinvoice.customer as suppliername from  tbinvoice join  tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo where tbinvoice.`type`=2 && tbpharmacystore.`type`=2 && tbpharmacystore.productName='"+cmbProductName.txtMrNo.getText().trim().toString()+"' && tbpharmacystore.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by  tbpharmacystore.invoiceNo ");
									while(rs.next()){
										model.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("productId"),rs.getString("productName"),rs.getString("qty"),rs.getString("totalPrice"),rs.getString("suppliername"),rs.getString("entryTime"),rs.getString("user"),new ImageIcon("icon/cprint.png")});
									}
									rowadd();
								}
								else{
									ResultSet rs=db.sta.executeQuery("select tbpharmacystore.productId, tbpharmacystore.productName,tbpharmacystore.qty,tbpharmacystore.totalPrice,tbpharmacystore.invoiceNo,tbpharmacystore.entryTime,(select tblogin.username from tblogin where tblogin.user_id=tbpharmacystore.createBy)as user,tbinvoice.customer as suppliername from  tbinvoice join  tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo where tbinvoice.`type`=2 && tbpharmacystore.`type`=2 && tbpharmacystore.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by  tbpharmacystore.invoiceNo ");
									while(rs.next()){
										model.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("productId"),rs.getString("productName"),rs.getString("qty"),rs.getString("totalPrice"),rs.getString("suppliername"),rs.getString("entryTime"),rs.getString("user"),new ImageIcon("icon/cprint.png")});
									}
									rowadd();
								}
							}
							if(cmbType.getSelectedIndex()==3){

								if(!cmbProductName.txtMrNo.getText().trim().toString().isEmpty()){
									ResultSet rs=db.sta.executeQuery("select tbpharmacystore.productId, tbpharmacystore.productName,tbpharmacystore.qty,tbpharmacystore.totalPrice,tbpharmacystore.invoiceNo,tbpharmacystore.entryTime,(select tblogin.username from tblogin where tblogin.user_id=tbpharmacystore.createBy)as user,tbinvoice.customer from  tbinvoice join  tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo where tbinvoice.`type`=3 && tbpharmacystore.`type`=3 && tbpharmacystore.productName='"+cmbProductName.txtMrNo.getText().trim().toString()+"' && tbpharmacystore.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by  tbpharmacystore.invoiceNo ");
									while(rs.next()){
										model.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("productId"),rs.getString("productName"),rs.getString("qty"),rs.getString("totalPrice"),rs.getString("customer"),rs.getString("entryTime"),rs.getString("user"),new ImageIcon("icon/cprint.png")});
									}
									rowadd();
								}
								else{
									ResultSet rs=db.sta.executeQuery("select tbpharmacystore.productId, tbpharmacystore.productName,tbpharmacystore.qty,tbpharmacystore.totalPrice,tbpharmacystore.invoiceNo,tbpharmacystore.entryTime,(select tblogin.username from tblogin where tblogin.user_id=tbpharmacystore.createBy)as user,tbinvoice.customer from  tbinvoice join  tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo where tbinvoice.`type`=3 && tbpharmacystore.`type`=3 && tbpharmacystore.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by  tbpharmacystore.invoiceNo ");
									while(rs.next()){
										model.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("productId"),rs.getString("productName"),rs.getString("qty"),rs.getString("totalPrice"),rs.getString("customer"),rs.getString("entryTime"),rs.getString("user"),new ImageIcon("icon/cprint.png")});
									}
									rowadd();
								}
							}
							if(cmbType.getSelectedIndex()==4){

								if(!cmbProductName.txtMrNo.getText().trim().toString().isEmpty()){
									ResultSet rs=db.sta.executeQuery("select tbpharmacystore.productId, tbpharmacystore.productName,tbpharmacystore.qty,tbpharmacystore.totalPrice,tbpharmacystore.invoiceNo,tbpharmacystore.entryTime,(select tblogin.username from tblogin where tblogin.user_id=tbpharmacystore.createBy)as user,tbinvoice.customer from  tbinvoice join  tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo where tbinvoice.`type`=4 && tbpharmacystore.`type`=4 && tbpharmacystore.productName='"+cmbProductName.txtMrNo.getText().trim().toString()+"' && tbpharmacystore.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by  tbpharmacystore.invoiceNo ");
									while(rs.next()){
										model.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("productId"),rs.getString("productName"),rs.getString("qty"),rs.getString("totalPrice"),rs.getString("customer"),rs.getString("entryTime"),rs.getString("user"),new ImageIcon("icon/cprint.png")});
									}
									rowadd();
								}
								else{
									ResultSet rs=db.sta.executeQuery("select tbpharmacystore.productId, tbpharmacystore.productName,tbpharmacystore.qty,tbpharmacystore.totalPrice,tbpharmacystore.invoiceNo,tbpharmacystore.entryTime,(select tblogin.username from tblogin where tblogin.user_id=tbpharmacystore.createBy)as user,tbinvoice.customer from  tbinvoice join  tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo where tbinvoice.`type`=4 && tbpharmacystore.`type`=4 && tbpharmacystore.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by  tbpharmacystore.invoiceNo ");
									while(rs.next()){
										model.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("productId"),rs.getString("productName"),rs.getString("qty"),rs.getString("totalPrice"),rs.getString("customer"),rs.getString("entryTime"),rs.getString("user"),new ImageIcon("icon/cprint.png")});
									}
									rowadd();
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e);
						}
					}
					else{
						rowadd();
						JOptionPane.showMessageDialog(null, "Please Provide Enddate");
					}
				}
				else{
					rowadd();
					JOptionPane.showMessageDialog(null, "Please Provide StartDate");
				}
			}
			else{
				rowadd();
				JOptionPane.showMessageDialog(null, "Warning!!,Please Select Type");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, ""+e);
		}
	}
	private void refreshTable(){
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
	}
	public void rowadd(){
		for(int a=0;a<20;a++){
			model.addRow(new Object[]{"","","","","","","","",new ImageIcon("icon/cprint.png")});
		}
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
		txtEndDate.setDate(date);
		txtEndDate.setFont(new Font("arial",Font.BOLD,14));
		txtEndDate.setForeground(new Color(255,0,124));
		DateMidnight now = new DateMidnight();
		DateMidnight beginningOfLastMonth = now.minusMonths(0).withDayOfMonth(1);
		//DateMidnight endOfLastMonth = now.withDayOfMonth(1).minusDays(1);
		txtStartDate.setDate(beginningOfLastMonth.toDate());
		//System.out.println(beginningOfLastMonth);
		txtStartDate.setFont(new Font("arial",Font.BOLD,14));
		txtStartDate.setForeground(new Color(255,0,124));
	}
	public void addcmp() {
		add(mainPanel);
		mainPanel.setOpaque(false);
		add(EditPanel);
		EditPanel.setOpaque(false);
		EditPanel_work();
		EditPanel.setVisible(false);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(1250,580));
		mainPanel.add(northPanel,BorderLayout.NORTH);
		northPanel.setOpaque(false);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		southPanel.setOpaque(false);
		northPanel_work();
		southPanel_work();
	}
	private void EditPanel_work() {
		EditPanel.setLayout(new BorderLayout());
		EditPanel.setPreferredSize(new Dimension(1100,680));
		EditPanel.add(EditNorthPanel,BorderLayout.NORTH);
		EditNorthPanel.setOpaque(false);
		EditNorthPanel_work();
		EditPanel.add(EditCenterPanel,BorderLayout.SOUTH);
		EditCenterPanel.setOpaque(false);
		EditCenterPanel_work();
	}
	private void EditNorthPanel_work() {
		EditNorthPanel.setPreferredSize(new Dimension(1100, 30));
		EditNorthPanel.setLayout(new FlowLayout());
		EditNorthPanel.add(btnClose);
		btnClose.setPreferredSize(new Dimension(26, 26));
	}
	private void EditCenterPanel_work() {
		EditCenterPanel.setPreferredSize(new Dimension(1100, 650));
		EditCenterPanel.setLayout(new BorderLayout());
	}
	private void northPanel_work() {
		TitledBorder titlemain=BorderFactory.createTitledBorder("Report Catagory");
		titlemain.setTitleJustification(titlemain.CENTER);
		northPanel.setBorder(titlemain);
		northPanel.setPreferredSize(new Dimension(12,90));
		FlowLayout flow=new FlowLayout();
		northPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		northPanel.add(lblType);
		northPanel.add(cmbType);
		cmbType.setPreferredSize(new Dimension(150,28));
		northPanel.add(lblPeroductName);
		northPanel.add(cmbProductName.combo);
		cmbProductName.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbProductName.combo.setPreferredSize(new Dimension(200,30));
		northPanel.add(lblStartDate);
		northPanel.add(txtStartDate);
		txtStartDate.setDateFormatString("yyyy-MM-dd");
		txtStartDate.setPreferredSize(new Dimension(150,28));
		northPanel.add(lblEndDate);
		northPanel.add(txtEndDate);
		txtEndDate.setDateFormatString("yyyy-MM-dd");
		txtEndDate.setPreferredSize(new Dimension(150,28));
		northPanel.add(btnSearch);
		btnSearch.setPreferredSize(new Dimension(90,36));
		btnSearch.setMnemonic(KeyEvent.VK_F);
	}

	private void southPanel_work() {
		southPanel.setPreferredSize(new Dimension(12,490));
		//southPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		southPanel.setLayout(new BorderLayout());
		southPanel.add(scroll);
		scroll.setOpaque(false);
		table.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		Action print = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)

			{
				try {
					if(cmbType.getSelectedIndex()==1){
						String report="pharmacyRpt/Invoicepurchasereport.jrxml";
						JasperDesign jd=JRXmlLoader.load(report);
						JRDesignQuery jq=new JRDesignQuery();
						String sql="select (select tbcompanyinfo.CompanyName from tbcompanyinfo)as company,"
								+ "(select tbcompanyinfo.address from tbcompanyinfo)as address,"
								+ "(select tbcompanyinfo.Mobile from tbcompanyinfo)as cmobile,"
								+ "(select tbcompanyinfo.email from tbcompanyinfo)as email,"
								+ "tbpharmacystore.invoiceNo,"
								+ "tbpharmacystore.productId,"
								+ "tbpharmacystore.productName,"
								+ "tbpharmacystore.unit,"
								+ "tbpharmacystore.qty,"
								+ "tbpharmacystore.buyPrice,"
								+ "tbpharmacystore.totalPrice,"
								+ "(select ledgerTitle from accfledger where ledgerId=tbinvoice.ledgerId)as supplier,"
								+ "tbinvoice.Mobile,"
								+ "tbinvoice.date,"
								+ "tbinvoice.amount,"
								+ "tbinvoice.discount,"
								+ "tbinvoice.netAmount,"
								+ "tbinvoice.transportCost,"
								+ "tbinvoice.cash,"
								+ "tbinvoice.card, "
								+ "(select username from tblogin where user_id=tbinvoice.createBy)as user "
								+ "from tbinvoice join tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo "
								+ " where tbinvoice.type=1 && tbinvoice.invoiceNo='"+table.getValueAt(table.getSelectedRow(), 0)+"' &&  tbpharmacystore.type=1";
						System.out.println(sql);
						jq.setText(sql);
						jd.setQuery(jq);
						JasperReport jr=JasperCompileManager.compileReport(jd);
						JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
						JasperViewer.viewReport(jp, false);
					}
					if(cmbType.getSelectedIndex()==2){
						String report="pharmacyRpt/Invoicepurchasereport.jrxml";
						JasperDesign jd=JRXmlLoader.load(report);
						JRDesignQuery jq=new JRDesignQuery();
						String sql="select (select tbcompanyinfo.CompanyName from tbcompanyinfo)as company,"
								+ "(select tbcompanyinfo.address from tbcompanyinfo)as address,"
								+ "(select tbcompanyinfo.Mobile from tbcompanyinfo)as cmobile,"
								+ "(select tbcompanyinfo.email from tbcompanyinfo)as email,"
								+ "tbpharmacystore.invoiceNo,"
								+ "tbpharmacystore.productId,"
								+ "tbpharmacystore.productName,"
								+ "tbpharmacystore.unit,"
								+ "tbpharmacystore.qty,"
								+ "tbpharmacystore.buyPrice,"
								+ "tbpharmacystore.totalPrice,"
								+ "(select ledgerTitle from accfledger where ledgerId=tbinvoice.ledgerId)as supplier,"
								+ "tbinvoice.Mobile,"
								+ "tbinvoice.date,"
								+ "tbinvoice.amount,"
								+ "tbinvoice.discount,"
								+ "tbinvoice.netAmount,"
								+ "tbinvoice.transportCost,"
								+ "tbinvoice.cash,"
								+ "tbinvoice.card, "
								+ "(select username from tblogin where user_id=tbinvoice.createBy)as user "
								+ "from tbinvoice join tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo "
								+ " where tbinvoice.type=2 && tbinvoice.invoiceNo='"+table.getValueAt(table.getSelectedRow(), 0)+"' &&  tbpharmacystore.type=2";
						System.out.println(sql);
						jq.setText(sql);
						jd.setQuery(jq);
						JasperReport jr=JasperCompileManager.compileReport(jd);
						JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
						JasperViewer.viewReport(jp, false);
					}
					if(cmbType.getSelectedIndex()==3){
						String report="pharmacyRpt/Invoicesalesreport.jrxml";
						JasperDesign jd=JRXmlLoader.load(report);
						JRDesignQuery jq=new JRDesignQuery();
						String sql="select (select tbcompanyinfo.CompanyName from tbcompanyinfo)as company,"
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
								+ "tbinvoice.RegNo as Customer,"
								+ "tbinvoice.Mobile,"
								+ "tbinvoice.date,"
								+ "tbinvoice.amount,"
								+ "tbinvoice.discount,"
								+ "tbinvoice.netAmount,"
								+ "tbinvoice.transportCost,"
								+ "tbinvoice.cash,tbinvoice.card, "
								+ "(select (select IFNULL(sum(tbpharmacystore.totalPrice),0)) from "
								+ "tbpharmacystore where tbpharmacystore.type=4 and tbpharmacystore.invoiceNo='"+table.getValueAt(table.getSelectedRow(), 0)+"')as returnamount,"
								+ " (select username from tblogin where user_id=tbinvoice.createBy)as user"
								+ " from tbinvoice join tbpharmacystore on tbpharmacystore.invoiceNo=tbinvoice.invoiceNo"
								+ "  where tbinvoice.type=3 && tbinvoice.invoiceNo='"+table.getValueAt(table.getSelectedRow(), 0)+"' &&  tbpharmacystore.type=3";
						System.out.println(sql);
						jq.setText(sql);
						jd.setQuery(jq);
						JasperReport jr=JasperCompileManager.compileReport(jd);
						JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
						JasperViewer.viewReport(jp, false);
					}

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e2);
				}
			}
		};

		ButtonColumn buttonColumn1 = new ButtonColumn(table, print,8);
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(140);
		table.getColumnModel().getColumn(5).setPreferredWidth(180);
		table.getColumnModel().getColumn(6).setPreferredWidth(170);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.getColumnModel().getColumn(8).setPreferredWidth(35);
		for(int i=0;i<7;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
		centerRenderer1.setHorizontalAlignment( JLabel.LEFT );
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer1);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer1);
		table.setRowHeight(table.getRowHeight() + 12);
		table.setShowGrid(true);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
	}
}
