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

public class DailyIncome extends JPanel{
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
	JButton btnPrint=new JButton("Print",new ImageIcon("icon/cprint.png"));
	JButton btnClose=new JButton(new ImageIcon("icon/close.png"));
	GridBagConstraints grid=new GridBagConstraints();

	String col[]={"Product Name","Sell Time","Qty","Sell Price","Total Sell Price","Buy Price", "Total Buy Price","Profit"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table=new JTable(model){
		@Override
		public boolean isCellEditable(int row,int col){

			return false;
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
	public DailyIncome(SessionBeam sessionbeam) {
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
				JOptionPane.showMessageDialog(null, "Contact System Developer For Printiing Work");
				//btnPrintEvent();
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
			int i=0;
			double tprofit=0.0,totalsell=0,totalsellprice=0,totalbuy=0,totalbuyprice=0;
			int totalqty=0;
			ResultSet rs=db.sta.executeQuery("select tbpharmacystore.entryTime,tbpharmacystore.productName,tbpharmacystore.qty,tbpharmacystore.sellPrice,tbpharmacystore.totalPrice,(select tbiteminformation.buyPrice from tbiteminformation where tbiteminformation.productId=tbpharmacystore.productId)as buyprice,tbpharmacystore.qty*(select tbiteminformation.buyPrice from tbiteminformation where tbiteminformation.productId=tbpharmacystore.productId)as totalbuyprice from tbpharmacystore where type=3 && tbpharmacystore.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'");
			while(rs.next()){
				totalqty=totalqty+Integer.parseInt(rs.getString("qty"));
				totalsell=totalsell+Double.parseDouble(rs.getString("sellprice"));
				totalsellprice=totalsellprice+Double.parseDouble(rs.getString("totalprice"));
				totalbuy=totalbuy+Double.parseDouble(rs.getString("buyprice"));
				totalbuyprice=totalbuyprice+Double.parseDouble(rs.getString("totalbuyprice"));
				double profit=Double.parseDouble(rs.getString("totalprice"))-Double.parseDouble(rs.getString("totalbuyprice"));
				tprofit=tprofit+profit;
				model.addRow(new Object[]{rs.getString("productName"),rs.getString("entryTime"),rs.getString("qty"),rs.getString("sellprice"),rs.getString("totalprice"),rs.getString("buyprice"),rs.getString("totalbuyprice"),profit});
				i++;
			}
			rowadd();
			table.setValueAt("Total", i, 0);
			table.setValueAt(totalqty, i, 2);
			table.setValueAt(totalsell, i, 3);
			table.setValueAt(totalsellprice, i, 4);
			table.setValueAt(totalbuy, i, 5);
			table.setValueAt(totalbuyprice, i, 6);
			table.setValueAt(tprofit, i, 7);
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
		mainPanel.setPreferredSize(new Dimension(1250,640));
		mainPanel.add(northPanel,BorderLayout.NORTH);
		northPanel.setOpaque(false);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		southPanel.setOpaque(false);
		northPanel_work();
		southPanel_work();
	}

	private void EditPanel_work() {
		EditPanel.setLayout(new BorderLayout());
		EditPanel.setPreferredSize(new Dimension(1086,640));
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
		TitledBorder titlemain=BorderFactory.createTitledBorder("Daily Income Profit");
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
		southPanel.setPreferredSize(new Dimension(12,550));
		//southPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		southPanel.setLayout(new BorderLayout());
		southPanel.add(scroll);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);

		for(int i=1;i<6;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setRowHeight(table.getRowHeight() + 15);
		table.setShowGrid(true);
		table.setSelectionForeground(Color.WHITE);
		table.setFont(new Font("arial", Font.BOLD, 14));
	}
}
