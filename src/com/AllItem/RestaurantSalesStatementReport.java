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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import com.ShareClass.db_coonection;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class RestaurantSalesStatementReport extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionbeam;
	public JPanel mainPanel=new JPanel();
	JPanel EditPanel=new JPanel();
	JPanel EditNorthPanel=new JPanel();
	JPanel EditCenterPanel=new JPanel();
	JPanel northPanel=new JPanel();
	JPanel centerPanel=new JPanel();
	JPanel southPanel=new JPanel();

	JLabel lblStatementType=new JLabel("Statement Type");
	String Type[]={"ALL","Indoor","Outdoor"};
	JComboBox cmbType=new JComboBox(Type);
	
	JLabel lblStartDate=new JLabel("Start Date");
	JLabel lblEndDate=new JLabel("End Date");
	JDateChooser txtStartDate=new JDateChooser();
	JDateChooser txtEndDate=new JDateChooser();
	JButton btnSearch=new JButton("Find",new ImageIcon("icon/find.png"));
	JButton btnPrint=new JButton("Print",new ImageIcon("icon/print.png"));
	JButton btnClose=new JButton(new ImageIcon("icon/close.png"));
	GridBagConstraints grid=new GridBagConstraints();

	String col[]={"Invoice No","Reg No","Gross Amount","Net Amount","Cash", "Card","Due","Sale Time","PRINT"};
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
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	String type="";
	BufferedImage image;
	public RestaurantSalesStatementReport(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addcmp();
		btnActionEvent();
		background();
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
	public void hitMethod(){
		rowadd();
		date_take();
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
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnPrintEvent();
			}
		});
	}
	private void btnPrintEvent() {
		try {
			JasperPrint jp=null;
			HashMap map=null;
			String company="",address="",mobile="",email="";
			ResultSet rs=db.sta.executeQuery("select *from tbcompanyinfo");
			while(rs.next()){
				company=rs.getString("CompanyName");
				address=rs.getString("address");
				mobile=rs.getString("Mobile");
				email=rs.getString("email");
			}
			for(int a=0;a<table.getRowCount();a++){
				if(table.getValueAt(a, 0).toString()!=""){
					map=new HashMap();
					map.put("company",company);
					map.put("address", address);
					map.put("cmobile", mobile);
					map.put("email", email);
					map.put("type",type);
					map.put("invoiceNo",table.getValueAt(a, 0));
					map.put("Customer",table.getValueAt(a, 1));
					map.put("totalBill",Double.parseDouble(table.getValueAt(a, 3).toString()));
					map.put("Cash",Double.parseDouble(table.getValueAt(a, 4).toString()));
					map.put("Card",Double.parseDouble(table.getValueAt(a, 5).toString()));
					map.put("Due",Double.parseDouble(table.getValueAt(a, 6).toString()));
					map.put("user","");
					map.put("startdate", new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate()));
					map.put("enddate", new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate()));
					list.add(map);
				}
			}
			String input = "pharmacyRpt/DailyStatement.jrxml";
			JasperReport com=JasperCompileManager.compileReport(input);
			jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
			JasperViewer.viewReport(jp, false);
			list.clear();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void submitBtnEvent() {
		try {
			refreshTable();
				String sql="select tbresinvoice.invoiceNo,tbresinvoice.RegNo,tbresinvoice.amount,tbresinvoice.netAmount,tbresinvoice.cash,tbresinvoice.card,tbresinvoice.netAmount-(tbresinvoice.cash+tbresinvoice.card) as due,tbresinvoice.entryTime from tbresinvoice where tbresinvoice.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' && type=3";
				System.out.println(sql);
				ResultSet rs=db.sta.executeQuery("select tbresinvoice.invoiceNo,tbresinvoice.RegNo,tbresinvoice.amount,tbresinvoice.netAmount,tbresinvoice.cash,tbresinvoice.card,tbresinvoice.netAmount-(tbresinvoice.cash+tbresinvoice.card) as due,tbresinvoice.entryTime from tbresinvoice where tbresinvoice.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' && type=3");
				while(rs.next()){
					model.addRow(new Object[]{rs.getString("invoiceNo"),rs.getString("RegNo"),rs.getString("amount"),rs.getString("netAmount"),rs.getString("cash"),rs.getString("card"),rs.getString("due"),rs.getString("entryTime"),new ImageIcon("icon/print.png")});
				}
				type="Daily Sales Statement";

			rowadd();
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
	private void rowadd(){
		for(int a=0;a<20;a++){
			model.addRow(new Object[]{"","","","","","","","",new ImageIcon("icon/print.png")});
		}
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
		DateMidnight now = new DateMidnight();
		DateMidnight beginningOfLastMonth = now.minusMonths(0).withDayOfMonth(1);
		//DateMidnight endOfLastMonth = now.withDayOfMonth(1).minusDays(1);
		txtStartDate.setDate(beginningOfLastMonth.toDate());
		//System.out.println(beginningOfLastMonth);
		txtStartDate.setFont(new Font("arial",Font.BOLD,14));

	}
	public void addcmp() {
		add(mainPanel);
		mainPanel.setOpaque(false);
		add(EditPanel);
		EditPanel.setOpaque(false);
		EditPanel_work();
		EditPanel.setVisible(false);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(1086,690));
		mainPanel.add(northPanel,BorderLayout.NORTH);
		northPanel.setOpaque(false);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		southPanel.setOpaque(false);
		northPanel_work();
		southPanel_work();
	}

	private void EditPanel_work() {
		EditPanel.setLayout(new BorderLayout());
		EditPanel.setPreferredSize(new Dimension(1086,680));
		EditPanel.add(EditNorthPanel,BorderLayout.NORTH);
		EditNorthPanel.setOpaque(false);
		EditNorthPanel_work();
		EditPanel.add(EditCenterPanel,BorderLayout.SOUTH);
		EditCenterPanel_work();
		EditCenterPanel.setOpaque(false);
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
		TitledBorder titlemain=BorderFactory.createTitledBorder("Daily Sales Statement");
		titlemain.setTitleJustification(titlemain.CENTER);
		northPanel.setBorder(titlemain);
		northPanel.setPreferredSize(new Dimension(12,90));
		FlowLayout flow=new FlowLayout();
		northPanel.setLayout(flow);
		
		//northPanel.add(lblStatementType);
		//northPanel.add(cmbType);
		//cmbType.setPreferredSize(new Dimension(160, 30));
		
		northPanel.add(lblStartDate);
		northPanel.add(txtStartDate);
		txtStartDate.setDateFormatString("dd-MM-yyyy");
		txtStartDate.setPreferredSize(new Dimension(150,28));
		
		northPanel.add(lblEndDate);
		northPanel.add(txtEndDate);
		txtEndDate.setDateFormatString("dd-MM-yyyy");
		txtEndDate.setPreferredSize(new Dimension(150,28));
		txtEndDate.setFont(new Font("arial",Font.BOLD,14));
		txtEndDate.setDate(new Date());
		northPanel.add(btnSearch);
		btnSearch.setPreferredSize(new Dimension(90,36));
		btnSearch.setMnemonic(KeyEvent.VK_F);
		northPanel.add(btnPrint);
		btnPrint.setPreferredSize(new Dimension(90,36));
		btnPrint.setMnemonic(KeyEvent.VK_P);
	}

	private void southPanel_work() {
		southPanel.setPreferredSize(new Dimension(12,600));
		//southPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		southPanel.setLayout(new BorderLayout());
		southPanel.add(scroll);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		Action print = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)

			{
				try {
					String report="pharmacyRpt/Invoicesalesreport.jrxml";
					JasperDesign jd=JRXmlLoader.load(report);
					JRDesignQuery jq=new JRDesignQuery();
					String sql="select (select tbcompanyinfo.CompanyName from tbcompanyinfo)as company,"
							+ "(select tbcompanyinfo.address from tbcompanyinfo)as address,"
							+ "(select tbcompanyinfo.Mobile from tbcompanyinfo)as cmobile,"
							+ "(select tbcompanyinfo.email from tbcompanyinfo)as email,"
							+ "tbrestaurantstore.invoiceNo,"
							+ "tbrestaurantstore.productId,"
							+ "tbrestaurantstore.productName,"
							+ "tbrestaurantstore.unit,"
							+ "tbrestaurantstore.qty,"
							+ "tbrestaurantstore.sellPrice,"
							+ "tbrestaurantstore.totalPrice,"
							+ "tbrestaurantstore.`type`,"
							+ "(select tbpatientinfo.PatientName from tbpatientinfo where tbpatientinfo.RegNo=tbresinvoice.RegNo)as customer,"
							+ "tbresinvoice.Mobile,"
							+ "tbresinvoice.date,"
							+ "tbresinvoice.amount,"
							+ "tbresinvoice.discount,"
							+ "tbresinvoice.netAmount,"
							+ "tbresinvoice.transportCost,"
							+ "tbresinvoice.cash,tbresinvoice.card, "
							+ "(select (select IFNULL(sum(tbrestaurantstore.totalPrice),0)) from "
							+ "tbrestaurantstore where tbrestaurantstore.type=4 and tbrestaurantstore.invoiceNo='"+table.getValueAt(table.getSelectedRow(), 0)+"')as returnamount,"
							+ " (select username from tblogin where user_id=tbresinvoice.createBy)as user"
							+ " from tbresinvoice join tbrestaurantstore on tbrestaurantstore.invoiceNo=tbresinvoice.invoiceNo"
							+ "  where tbresinvoice.type=3 && tbresinvoice.invoiceNo='"+table.getValueAt(table.getSelectedRow(), 0)+"' && tbrestaurantstore.type=3 ";
					System.out.println(sql);
					jq.setText(sql);
					jd.setQuery(jq);
					JasperReport jr=JasperCompileManager.compileReport(jd);
					JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
					JasperViewer.viewReport(jp, false);
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e2);
				}
			}
		};

		ButtonColumn buttonColumn1 = new ButtonColumn(table, print,8);
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
		table.getColumnModel().getColumn(7).setPreferredWidth(150);
		table.getColumnModel().getColumn(8).setPreferredWidth(40);
		for(int i=0;i<7;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setRowHeight(table.getRowHeight() + 15);
		table.setShowGrid(true);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
	}
}
