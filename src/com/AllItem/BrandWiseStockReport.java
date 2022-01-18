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
import java.text.DecimalFormat;
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
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;

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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class BrandWiseStockReport extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionbeam;
	JPanel mainPanel=new JPanel();
	JPanel northPanel=new JPanel();
	JPanel northWestPanel=new JPanel();
	JPanel northEastPanel=new JPanel();
	JPanel centerPanel=new JPanel();
	JPanel southPanel=new JPanel();
	JLabel lblBrandName=new JLabel("<html><font color=red>*</font>Brand Name</html>");
	JLabel lblStartDate=new JLabel("From");
	JLabel lblEndDate=new JLabel("To");
	JDateChooser txtStartDate=new JDateChooser();
	JDateChooser txtEndDate=new JDateChooser();
	SuggestText cmbBrandName=new SuggestText();
	JButton btnSearch=new JButton("Find",new ImageIcon("icon/find.png"));
	JButton btnPrint=new JButton("Print",new ImageIcon("icon/cprint.png"));
	GridBagConstraints grid=new GridBagConstraints();
	KeyEvent esc;
	String col[]={"      Product Name","OP Qty","Op Amt.","Purchase Qty","Purchase Amt.","Purchase R Qty","Purchase R Amt.","Wastage Qty","Wastage Amt.","Total Stock","Total Stock Amt.","Sales Qty","Sales Qty Amt","Sales R Qty","Sales R Amt","Total Sales","Total Sales Amt"," C/QTY","PRICE"," C/VALUE"};
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
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	String startDate="",headvalue="";
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();

	DecimalFormat df = new DecimalFormat("#.00");
	BufferedImage image;
	JCheckBox checkTodaysTransection=new JCheckBox("Todays Transection");
	public BrandWiseStockReport(SessionBeam sessionbeam) {
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
	public void loadProductName(){
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
	public void setHead(){
		date_take();
		rowAdd();
	}
	private void btnActionEvent(){
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SearchBtnEvent();
			}
		});
		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, "Contact System Developer For Printiing Work");
				btnPrintEvent();
			}
		});
		cmbBrandName.txtMrNo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SearchBtnEvent();
			}
		});
	}
	public void btnPrintEvent() {
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
					map.put("mobile", mobile);
					map.put("emailname", email);
					map.put("name",table.getValueAt(a, 0).toString());
					map.put("openingstock",table.getValueAt(a, 1));
					map.put("opamt",Double.parseDouble(table.getValueAt(a, 2).toString()));
					map.put("purchaseqty",table.getValueAt(a, 3));
					map.put("purchaseAmt",Double.parseDouble(table.getValueAt(a, 4).toString()));
					map.put("prqty",table.getValueAt(a, 5));
					map.put("purRamt",Double.parseDouble(table.getValueAt(a, 6).toString()));
					map.put("wastageQty",table.getValueAt(a, 7));
					map.put("wasAmt",Double.parseDouble(table.getValueAt(a, 8).toString()));
					map.put("tstockqty",table.getValueAt(a, 9));
					map.put("tStockAmt",Double.parseDouble(table.getValueAt(a, 10).toString()));
					map.put("salesqty",table.getValueAt(a, 11));
					map.put("salesAmt",Double.parseDouble(table.getValueAt(a, 12).toString()));
					map.put("srqty",table.getValueAt(a,13));
					map.put("saleRamt",Double.parseDouble(table.getValueAt(a, 14).toString()));
					map.put("tsqty",table.getValueAt(a, 15));
					map.put("tSalesAmt",Double.parseDouble(table.getValueAt(a, 16).toString()));
					map.put("cqty",table.getValueAt(a, 17));
					map.put("cprice",Double.parseDouble(table.getValueAt(a, 18).toString()));
					map.put("cvalue",Double.parseDouble(table.getValueAt(a,19).toString()));
					map.put("user","");
					map.put("fromdate", new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate()));
					map.put("todate", new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate()));
					list.add(map);
				}
			}
			String input = "Sales/StockProductRptWithValue.jrxml";
			JasperReport com=JasperCompileManager.compileReport(input);
			jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
			JasperViewer.viewReport(jp, false);
			list.clear();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void rowAdd(){
		for(int a=0;a<24;a++){
			model.addRow(new Object[]{"","","","","","","","","","",""});
		}
	}
	private void SearchBtnEvent() {
		if(txtStartDate.getDate()!=null){
			if(txtEndDate.getDate()!=null){
				try {
					for(int a=table.getRowCount()-1;a>=0;a--){
						model.removeRow(a);
					}
					if(!cmbBrandName.txtMrNo.getText().trim().toString().isEmpty()){
						if(checkTodaysTransection.isSelected()){
							String catId="";
							ResultSet rs1=db.sta.executeQuery("select tbbrandinfo.brandId from tbbrandinfo where tbbrandinfo.brandName='"+cmbBrandName.txtMrNo.getText().trim().toString()+"'");
							while(rs1.next())
							{
								catId=rs1.getString("brandId");
							}
							String brandName=cmbBrandName.txtMrNo.getText().trim().toString();
							ResultSet rs=db.sta.executeQuery("select tbpharmacystore.productName from tbpharmacystore where tbpharmacystore.catagoryId='"+catId+"' && tbpharmacystore.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by tbpharmacystore.productName ");
							int i=0;
							while(rs.next()){
								model.addRow(new Object[]{rs.getString("productName")});
								i++;
							}
							for(int a=0;a<i;a++){
								String modelName=table.getValueAt(a, 0).toString();
								getProductWiseValue(modelName,a);
							}
/*							int oqty=0,openqty=0,purchaseqty=0,purchaserqty=0,wastageqty=0,totalstockqty=0,salesqty=0,salesrqty=0,totalsale=0,cqty=0;
							double cpirce=0,totalbalance=0;
							for(int a=0;a<i;a++){
								openqty=openqty+Integer.parseInt(table.getValueAt(a, 1).toString());
								purchaseqty=purchaseqty+Integer.parseInt(table.getValueAt(a, 2).toString());
								purchaserqty=purchaserqty+Integer.parseInt(table.getValueAt(a, 3).toString());
								wastageqty=wastageqty+Integer.parseInt(table.getValueAt(a, 4).toString());
								totalstockqty=totalstockqty+Integer.parseInt(table.getValueAt(a, 5).toString());
								salesqty=salesqty+Integer.parseInt(table.getValueAt(a, 6).toString());
								salesrqty=salesrqty+Integer.parseInt(table.getValueAt(a, 7).toString());
								totalsale=totalsale+Integer.parseInt(table.getValueAt(a, 8).toString());
								cqty=cqty+Integer.parseInt(table.getValueAt(a, 9).toString());
								cpirce=cpirce+Double.parseDouble(table.getValueAt(a, 10).toString());
								totalbalance=totalbalance+Double.parseDouble(table.getValueAt(a, 11).toString());
								
							}
							model.addRow(new Object[]{"Total",openqty,purchaseqty,purchaserqty,wastageqty,totalstockqty,salesqty,salesrqty,totalsale,cqty,cpirce,totalbalance});*/
						}
						else{
							String brandName=cmbBrandName.txtMrNo.getText().trim().toString();
							ResultSet rs=db.sta.executeQuery("select tbiteminformation.ProductName from tbiteminformation where tbiteminformation.BrandId=(select tbbrandinfo.brandId from tbbrandinfo where tbbrandinfo.brandName='"+brandName+"')");
							int i=0;
							while(rs.next()){
								model.addRow(new Object[]{rs.getString("ProductName")});
								i++;
							}
							for(int a=0;a<i;a++){
								String modelName=table.getValueAt(a, 0).toString();
								getProductWiseValue(modelName,a);
							}
/*							int oqty=0,openqty=0,purchaseqty=0,purchaserqty=0,wastageqty=0,totalstockqty=0,salesqty=0,salesrqty=0,totalsale=0,cqty=0;
							double cpirce=0,totalbalance=0;
							for(int a=0;a<i;a++){
								openqty=openqty+Integer.parseInt(table.getValueAt(a, 1).toString());
								purchaseqty=purchaseqty+Integer.parseInt(table.getValueAt(a, 2).toString());
								purchaserqty=purchaserqty+Integer.parseInt(table.getValueAt(a, 3).toString());
								wastageqty=wastageqty+Integer.parseInt(table.getValueAt(a, 4).toString());
								totalstockqty=totalstockqty+Integer.parseInt(table.getValueAt(a, 5).toString());
								salesqty=salesqty+Integer.parseInt(table.getValueAt(a, 6).toString());
								salesrqty=salesrqty+Integer.parseInt(table.getValueAt(a, 7).toString());
								totalsale=totalsale+Integer.parseInt(table.getValueAt(a, 8).toString());
								cqty=cqty+Integer.parseInt(table.getValueAt(a, 9).toString());
								cpirce=cpirce+Double.parseDouble(table.getValueAt(a, 10).toString());
								totalbalance=totalbalance+Double.parseDouble(table.getValueAt(a, 11).toString());
								
							}
							model.addRow(new Object[]{"Total",openqty,purchaseqty,purchaserqty,wastageqty,totalstockqty,salesqty,salesrqty,totalsale,cqty,cpirce,totalbalance});*/
						}
					}
					else{
						if(checkTodaysTransection.isSelected()){
							System.out.println("Hye");
							String brandName=cmbBrandName.txtMrNo.getText().trim().toString();
							ResultSet rs=db.sta.executeQuery("select tbpharmacystore.productName from tbpharmacystore where tbpharmacystore.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by tbpharmacystore.productName ");
							int i=0;
							while(rs.next()){
								model.addRow(new Object[]{rs.getString("productName")});
								i++;
							}
							for(int a=0;a<i;a++){
								String modelName=table.getValueAt(a, 0).toString();
								getProductWiseValue(modelName,a);
							}
/*							int oqty=0,openqty=0,purchaseqty=0,purchaserqty=0,wastageqty=0,totalstockqty=0,salesqty=0,salesrqty=0,totalsale=0,cqty=0;
							double cpirce=0,totalbalance=0;
							for(int a=0;a<i;a++){
								openqty=openqty+Integer.parseInt(table.getValueAt(a, 1).toString());
								purchaseqty=purchaseqty+Integer.parseInt(table.getValueAt(a, 2).toString());
								purchaserqty=purchaserqty+Integer.parseInt(table.getValueAt(a, 3).toString());
								wastageqty=wastageqty+Integer.parseInt(table.getValueAt(a, 4).toString());
								totalstockqty=totalstockqty+Integer.parseInt(table.getValueAt(a, 5).toString());
								salesqty=salesqty+Integer.parseInt(table.getValueAt(a, 6).toString());
								salesrqty=salesrqty+Integer.parseInt(table.getValueAt(a, 7).toString());
								totalsale=totalsale+Integer.parseInt(table.getValueAt(a, 8).toString());
								cqty=cqty+Integer.parseInt(table.getValueAt(a, 9).toString());
								cpirce=cpirce+Double.parseDouble(table.getValueAt(a, 10).toString());
								totalbalance=totalbalance+Double.parseDouble(table.getValueAt(a, 11).toString());
								
							}
							model.addRow(new Object[]{"Total",openqty,purchaseqty,purchaserqty,wastageqty,totalstockqty,salesqty,salesrqty,totalsale,cqty,cpirce,totalbalance});*/
						}
						else{
							ResultSet rs=db.sta.executeQuery("select tbiteminformation.productName from tbiteminformation order by tbiteminformation.BrandId");
							int i=0;
							while(rs.next()){
								model.addRow(new Object[]{rs.getString("productName")});
								i++;
							}
							for(int a=0;a<i;a++){
								String modelName=table.getValueAt(a, 0).toString();
								getProductWiseValue(modelName,a);
							}
/*							int oqty=0,openqty=0,purchaseqty=0,purchaserqty=0,wastageqty=0,totalstockqty=0,salesqty=0,salesrqty=0,totalsale=0,cqty=0;
							double cpirce=0,totalbalance=0;
							for(int a=0;a<i;a++){
								openqty=openqty+Integer.parseInt(table.getValueAt(a, 1).toString());
								purchaseqty=purchaseqty+Integer.parseInt(table.getValueAt(a, 2).toString());
								purchaserqty=purchaserqty+Integer.parseInt(table.getValueAt(a, 3).toString());
								wastageqty=wastageqty+Integer.parseInt(table.getValueAt(a, 4).toString());
								totalstockqty=totalstockqty+Integer.parseInt(table.getValueAt(a, 5).toString());
								salesqty=salesqty+Integer.parseInt(table.getValueAt(a, 6).toString());
								salesrqty=salesrqty+Integer.parseInt(table.getValueAt(a, 7).toString());
								totalsale=totalsale+Integer.parseInt(table.getValueAt(a, 8).toString());
								cqty=cqty+Integer.parseInt(table.getValueAt(a, 9).toString());
								cpirce=cpirce+Double.parseDouble(table.getValueAt(a, 10).toString());
								totalbalance=totalbalance+Double.parseDouble(table.getValueAt(a, 11).toString());
								
							}
							model.addRow(new Object[]{"Total",openqty,purchaseqty,purchaserqty,wastageqty,totalstockqty,salesqty,salesrqty,totalsale,cqty,cpirce,totalbalance});*/
						}
						}

					rowAdd();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Please Select End Date");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Please Select Start Date");
		}
	}
	public void getProductWiseValue(String productName,int a){
		try {
				
				int OS=0,purchase=0,purchaser=0,wastage=0,sales=0,salesr=0;
				double OB=0.0,purchasevalue=0.0,purchaservalue=0.0,wastageValue=0.0,salesValue=0.0,salesRvalue=0.0;
					int stock=0,opeingstock=0;
					ResultSet rs=db.sta.executeQuery("select tbiteminformation.openingStock,(select sum(tbiteminformation.openingStock*tbiteminformation.buyPrice) from tbiteminformation where tbiteminformation.ProductName='"+productName+"')as OB from tbiteminformation where tbiteminformation.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+productName+"')");
					while(rs.next()){
						opeingstock=opeingstock+Integer.parseInt(rs.getString("openingStock"));
						OB=OB+Double.parseDouble(rs.getString("OB"));
					}
					String stockquery="select tbpharmacystore.productId," +
							"(select (select IFNULL(sum(tbpharmacystore.qty),0)) from tbpharmacystore where type=1 && tbpharmacystore.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+productName+"') && tbpharmacystore.date<'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"')as purchase," +
							"(select (select IFNULL(sum(tbpharmacystore.qty),0)) from tbpharmacystore where type=2 && tbpharmacystore.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+productName+"') && tbpharmacystore.date<'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"')as purchasereturn," +
							"(select (select IFNULL(sum(tbpharmacystore.qty),0)) from tbpharmacystore where type=3 && tbpharmacystore.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+productName+"') && tbpharmacystore.date<'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"')as sales," +
							"(select (select IFNULL(sum(tbpharmacystore.qty),0)) from tbpharmacystore where type=4 && tbpharmacystore.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+productName+"') && tbpharmacystore.date<'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"')as salesreturn," +
							"(select (select IFNULL(sum(tbpharmacystore.qty),0)) from tbpharmacystore where type=5 && tbpharmacystore.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+productName+"') && tbpharmacystore.date<'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"') as wastage" +
							" from tbpharmacystore where tbpharmacystore.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+productName+"') group by tbpharmacystore.productId";
					System.out.println(stockquery);
					ResultSet stockResult=db.sta.executeQuery(stockquery);
					while(stockResult.next()){
						opeingstock=opeingstock+Integer.parseInt(stockResult.getString("purchase"))-(Integer.parseInt(stockResult.getString("purchasereturn"))+Integer.parseInt(stockResult.getString("wastage")))-(Integer.parseInt(stockResult.getString("sales"))-Integer.parseInt(stockResult.getString("salesreturn")));
					}
					/*					String query="select (select IFNULL(sum(tbiteminformation.openingStock),0))as OS,(select IFNULL(sum(tbiteminformation.openingStock*tbiteminformation.PerQtySalesPrice),0))as OB from tbiteminformation where productName='"+table.getValueAt(a, 0).toString()+"'";
					System.out.println(query);
					ResultSet rs1=db.sta.executeQuery(query);
					while(rs1.next()){
						OS=OS+Integer.parseInt(rs1.getString("OS"));
						OB=OB+Double.parseDouble(rs1.getString("OB"));
					}*/
					//table.setValueAt(cmbBrandName.txtMrNo.getText().trim().toString(), a, 0);
					table.setValueAt(opeingstock, a, 1);
					table.setValueAt(OB, a, 2);
					String sql="select (select IFNULL(sum(tbpharmacystore.qty),0))as purchase,(select IFNULL(sum(tbpharmacystore.qty*tbpharmacystore.buyPrice),0))as purchasevalue from tbpharmacystore where productName='"+productName+"' and type=1 and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'";
					//System.out.println(sql);
					ResultSet rs2=db.sta.executeQuery("select (select IFNULL(sum(tbpharmacystore.qty),0))as purchase,(select IFNULL(sum(tbpharmacystore.qty*tbpharmacystore.buyPrice),0))as purchasevalue from tbpharmacystore where productName='"+productName+"' and type=1 and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'");
					while(rs2.next()){
						purchase=purchase+Integer.parseInt(rs2.getString("purchase"));
						purchasevalue=purchasevalue+Double.parseDouble(rs2.getString("purchasevalue"));
					}
					table.setValueAt(purchase, a, 3);
					table.setValueAt(purchasevalue, a, 4);

					ResultSet rs3=db.sta.executeQuery("select (select IFNULL(sum(tbpharmacystore.qty),0))as purchaser,(select IFNULL(sum(tbpharmacystore.qty*tbpharmacystore.buyPrice),0))as purchaservalue from tbpharmacystore where productName='"+productName+"' and type=2 and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'");
					while(rs3.next()){
						purchaser=purchaser+Integer.parseInt(rs3.getString("purchaser"));
						purchaservalue=purchaservalue+Double.parseDouble(rs3.getString("purchaservalue"));
					}
					table.setValueAt(purchaser, a, 5);
					table.setValueAt(purchaservalue, a, 6);

					ResultSet rs6=db.sta.executeQuery("select (select IFNULL(sum(tbpharmacystore.qty),0))as wastage from tbpharmacystore where productName='"+productName+"' and type=5 and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'");
					while(rs6.next()){
						wastage=wastage+Integer.parseInt(rs6.getString("wastage"));
					}
					table.setValueAt(wastage, a, 7);
					table.setValueAt(wastageValue, a, 8);
					table.setValueAt(opeingstock+purchase-(purchaser+wastage), a, 9);
					table.setValueAt(OB+purchasevalue-(purchaservalue+wastageValue), a, 10);

					ResultSet rs4=db.sta.executeQuery("select (select IFNULL(sum(tbpharmacystore.qty),0))as sales from tbpharmacystore where productName='"+productName+"' and type=3 and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'");
					while(rs4.next()){
						sales=sales+Integer.parseInt(rs4.getString("sales"));
					}
					table.setValueAt(sales, a, 11);
					table.setValueAt(salesValue, a, 12);

					ResultSet rs5=db.sta.executeQuery("select (select IFNULL(sum(tbpharmacystore.qty),0))as salesr from tbpharmacystore where productName='"+productName+"' and type=4 and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'");
					while(rs5.next()){
						salesr=salesr+Integer.parseInt(rs5.getString("salesr"));
					}
					table.setValueAt(salesr, a, 13);
					table.setValueAt(salesRvalue, a, 14);
					table.setValueAt(sales-salesr, a, 15);
					table.setValueAt(salesValue-salesRvalue, a, 16);
					int closing=opeingstock+(purchase-(purchaser+wastage))-(sales-salesr);
					table.setValueAt(closing, a, 17);
					int temp=0;
					double bprice=0.0;
					ResultSet rs7=db.sta.executeQuery("select tbpharmacystore.qty,tbpharmacystore.totalPrice from tbpharmacystore where tbpharmacystore.productName='"+productName+"' && type=1 && date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' order by tbpharmacystore.entryTime desc");
					while(rs7.next()){
						bprice=Double.parseDouble(rs7.getString("totalPrice"))/Double.parseDouble(rs7.getString("qty"));
						temp=1;
						break;
					}
					if(temp==0){
						ResultSet rs8=db.sta.executeQuery("select tbiteminformation.buyPrice from tbiteminformation where tbiteminformation.productId=(select tbiteminformation.productId from tbiteminformation where tbiteminformation.ProductName='"+productName+"')");
						while(rs8.next()){
							bprice=Double.parseDouble(rs8.getString("buyPrice"));
							System.out.println("bprice "+bprice);
							break;
						}
					}
					table.setValueAt(df.format(bprice), a, 18);
					System.out.println("closing "+closing);
					//table.setValueAt(BigInteger.valueOf((long) (closing*bprice)), a, 10);
					table.setValueAt(df.format(closing*bprice), a, 19);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error,"+e);
		}
	}
	private void refreshTable(){
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
	}

	public void dailyreport_work(){
		try {
			String report="pharmacyRpt/StockProductRpt.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText("select (select tbcompanyinfo.CompanyName from tbcompanyinfo)as company,(select tbcompanyinfo.address from tbcompanyinfo)as address,(select tbcompanyinfo.Mobile from tbcompanyinfo)as cmobile,(select tbcompanyinfo.email from tbcompanyinfo)as email,tbsummerystockcatagory.CatagoryName,tbsummerystockcatagory.OpeningStock,tbsummerystockcatagory.purchaseQty,tbsummerystockcatagory.SalesQty,tbsummerystockcatagory.ClosingStock,tbsummerystockcatagory.ClosingValue,tbsummerystockcatagory.date,tbsummerystockcatagory.username, ('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"') as fromdate,('"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') as todate from  tbsummerystockcatagory");
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
	private void addcmp() {
		add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(false);
		//mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setPreferredSize(new Dimension(1250,620));
		mainPanel.setBackground(new Color(151, 184, 192));
		mainPanel.add(northPanel,BorderLayout.NORTH);
		northPanel.setOpaque(false);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		southPanel.setOpaque(false);
		northPanel_work();
		southPanel_work();
	}
	private void northPanel_work() {

		northPanel.setBorder(BorderFactory.createTitledBorder("Stock Report Brand Wise"));
		northPanel.setPreferredSize(new Dimension(12,90));
		northPanel.setLayout(new FlowLayout());
		
		northPanel.add(checkTodaysTransection);
		
		checkTodaysTransection.setPreferredSize(new Dimension(200, 15));
		checkTodaysTransection.setForeground(Color.red);
		
		checkTodaysTransection.setFont(new Font("arial", Font.BOLD, 13));
		northPanel.add(lblBrandName);
		lblBrandName.setFont(new Font("arial",Font.BOLD,13));

		northPanel.add(cmbBrandName.combo);
		cmbBrandName.combo.setPreferredSize(new Dimension(260, 30));
		northPanel.add(lblStartDate);
		northPanel.add(txtStartDate);
		txtStartDate.setDateFormatString("dd-MM-yyyy");
		txtStartDate.setPreferredSize(new Dimension(135,28));
		northPanel.add(lblEndDate);
		northPanel.add(txtEndDate);
		txtEndDate.setDateFormatString("dd-MM-yyyy");
		txtEndDate.setPreferredSize(new Dimension(135,28));
		northPanel.add(btnSearch);
		btnSearch.setMnemonic(KeyEvent.VK_F);
		btnSearch.setPreferredSize(new Dimension(90,36));
		northPanel.add(btnPrint);
		btnPrint.setPreferredSize(new Dimension(90,36));
		btnPrint.setMnemonic(KeyEvent.VK_P);
	}
	private void southPanel_work() {

		southPanel.setPreferredSize(new Dimension(12,540));
		//southPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		southPanel.setLayout(new FlowLayout());
		/*		southPanel.add(bar,new BorderLayout().NORTH);
		bar.setStringPainted(true);
		bar.setBackground(Color.red);
		bar.setForeground(Color.green);
		bar.setPreferredSize(new Dimension(200,20));*/
		southPanel.add(scroll);
		scroll.setPreferredSize(new Dimension(1250, 510));
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
				dailyreport_work();
			}
		};
		ButtonColumn buttonColumn1 = new ButtonColumn(table, print, 6);
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setPreferredWidth(250);
		for(int i=1;i<11;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		table.setRowHeight(table.getRowHeight() +14);
		table.setFont(new Font("arial",Font.BOLD,13));
		table.setShowGrid(true);
		table.setSelectionForeground(Color.WHITE);
		table.setFont(new Font("arial", Font.BOLD, 14));
	}
}
