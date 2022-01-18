package com.AllItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;

public class ItemCreate extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionbeam;

	JPanel NorthPanel=new JPanel();
	JPanel centerPanel=new JPanel();
	JPanel SouthPanel=new JPanel();

	JLabel lblFProductBrand=new JLabel("Product Band");
	JLabel lblFProductBand=new JLabel("Product Band");

	JLabel lblProductBrand=new JLabel("Band Name");
	JLabel lblProductBand=new JLabel("Model Name");
	JLabel lblProductName=new JLabel("Product Name");
	JLabel lblProductSl=new JLabel("Serial No");
	JLabel lblBuyPrice=new JLabel("Buy Price (Pcs)");
	JLabel lblSellPrice=new JLabel("Sell Price (Pcs)");
	JLabel lblOpeningStock=new JLabel("Opening Stock");

	String FBrand[]={""};
	JComboBox cmbFBrand=new JComboBox(FBrand);

	SuggestText cmbFProductCode=new SuggestText();

	String Brand[]={""};
	JComboBox cmbBrand=new JComboBox(Brand);

	SuggestText cmbProductCode=new SuggestText();
	JTextField txtProductSl=new JTextField(3);
	JTextField txtProductName=new JTextField(10);
	JTextField txtBuyPrice=new JTextField(5);
	JTextField txtSellPrice=new JTextField(5);
	JTextField txtOpeningStock=new JTextField(3);


	String col[]={"P.Id","Product Name","Product Model","Product Brand","Buy Price","Sell Price","Opening Qty"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table=new JTable(model){
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
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JButton btnPrint=new JButton("Print",new ImageIcon("icon/cprint.png"));

	JButton btnFind=new JButton("Find",new ImageIcon("icon/find.png"));
	JButton btnSubmit=new JButton("Submit",new ImageIcon("icon/save.png"));
	JButton btnRefresh=new JButton(new ImageIcon("icon/reresh.png"));
	String pId="";
	BufferedImage image;
	public ItemCreate(SessionBeam sessionBeam){
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.sessionbeam=sessionBeam;
		cmp();
		textEvent();
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
	public void autoId() {
		try {
			String sql="select (ifnull(max(productId),0)+1)as productId from tbiteminformation";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				txtProductSl.setText(rs.getString("productId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	public void btnActionEvent(){
		
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					ResultSet rs=db.sta.executeQuery("select *,(select tbbrandinfo.brandName from tbbrandinfo where tbbrandinfo.brandId=tbiteminformation.BrandId)as brandName,(select tbbrandwisedepartmentinfo.ModelName from tbbrandwisedepartmentinfo where tbbrandwisedepartmentinfo.modelId=tbiteminformation.ModelId)as modelName from tbiteminformation where productId='"+table.getValueAt(table.getSelectedRow(), 0).toString()+"'");
					while(rs.next()){
						txtProductSl.setText(rs.getString("productId"));
						cmbBrand.setSelectedItem(rs.getString("brandName"));
						cmbProductCode.txtMrNo.setText(rs.getString("modelName"));
						txtProductName.setText(rs.getString("ProductName"));
						txtBuyPrice.setText(rs.getString("buyPrice"));
						txtSellPrice.setText(rs.getString("sellPrice"));
						txtOpeningStock.setText(rs.getString("openingStock"));
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtClear();
				autoId();
				loadProductItem();
			}
		});
		btnFind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnFindEvent();
			}
		});
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSubmitEvent();
			}
		});
	}
	public void btnFindEvent() {
		if(cmbFBrand.getSelectedIndex()!=0){
			if(!cmbFProductCode.txtMrNo.getText().trim().toString().isEmpty()){
				try {
					for(int a=table.getRowCount()-1;a>=0;a--){
						model.removeRow(a);
					}
					ResultSet rs=db.sta.executeQuery("select *,(select tbbrandinfo.brandName from tbbrandinfo where tbbrandinfo.brandId=tbiteminformation.BrandId)as brandName,(select tbbrandwisedepartmentinfo.ModelName from tbbrandwisedepartmentinfo where tbbrandwisedepartmentinfo.modelId=tbiteminformation.ModelId)as modelName from tbiteminformation where ModelId=(select tbbrandwisedepartmentinfo.modelId from tbbrandwisedepartmentinfo where tbbrandwisedepartmentinfo.ModelName='"+cmbFProductCode.txtMrNo.getText().trim().toString()+"')");
					while(rs.next()){
						txtProductSl.setText(rs.getString("productId"));
						cmbBrand.setSelectedItem(rs.getString("brandName"));
						cmbProductCode.txtMrNo.setText(rs.getString("modelName"));
						txtProductName.setText(rs.getString("ProductName"));
						txtBuyPrice.setText(rs.getString("buyPrice"));
						txtSellPrice.setText(rs.getString("sellPrice"));
						txtOpeningStock.setText(rs.getString("openingStock"));

						model.addRow(new Object[]{rs.getString("productId"),rs.getString("ProductName"),rs.getString("modelName"),rs.getString("brandName"),rs.getString("buyPrice"),rs.getString("sellPrice"),rs.getString("openingStock")});
					}
					for(int a=0;a<10;a++){
						model.addRow(new Object[]{"","","","","","",""});
					}

				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!, Please Provide Mobile Modal");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!, Please Provide Mobile Brand");
		}
	}
	public void btnSubmitEvent() {

				if(!txtProductSl.getText().trim().toString().isEmpty()){
					if(!txtProductName.getText().trim().toString().isEmpty()){
						if(!txtBuyPrice.getText().trim().toString().isEmpty()){
							if(!txtSellPrice.getText().trim().toString().isEmpty()){
								if(!txtOpeningStock.getText().trim().toString().isEmpty()){
									try {
										if(!checkDoplicateItemCode()){
											if(!checkDoplicateItemName()){
												autoId();
												String bandId="",modelId="";
												ResultSet rs=db.sta.executeQuery("select tbbrandwisedepartmentinfo.brandid,tbbrandwisedepartmentinfo.modelId from tbbrandwisedepartmentinfo where tbbrandwisedepartmentinfo.ModelName='"+cmbProductCode.txtMrNo.getText().trim().toString()+"'");
												while(rs.next()){
													bandId=rs.getString("brandid");
													modelId=rs.getString("modelId");
												}
												String insertsql="insert into tbiteminformation values ('"+txtProductSl.getText().trim().toString()+"','"+txtProductName.getText().trim().toString()+"','"+bandId+"','"+modelId+"','"+txtBuyPrice.getText().trim().toString()+"','"+txtSellPrice.getText().trim().toString()+"','"+txtOpeningStock.getText().trim().toString()+"',NOW(),'"+sessionbeam.getUserId()+"')";
												System.out.println("insert "+insertsql);
												db.sta.executeUpdate(insertsql);
												JOptionPane.showMessageDialog(null, "Data Entry Successfully!");
												loadProductItem();
												txtClear();
												autoId();
											}
											else{
												JOptionPane.showMessageDialog(null, "Sorry Doplicate Product Name !!");
											}

										}
										else{
												String bandId="",modelId="";
												ResultSet rs=db.sta.executeQuery("select tbbrandwisedepartmentinfo.brandid,tbbrandwisedepartmentinfo.modelId from tbbrandwisedepartmentinfo where tbbrandwisedepartmentinfo.ModelName='"+cmbProductCode.txtMrNo.getText().trim().toString()+"'");
												while(rs.next()){
													bandId=rs.getString("brandid");
													modelId=rs.getString("modelId");
												}
												String updatesql="update tbiteminformation set ProductName='"+txtProductName.getText().trim().toString()+"',BrandId='"+bandId+"',buyPrice='"+txtBuyPrice.getText().trim().toString()+"',sellPrice='"+txtSellPrice.getText().trim().toString()+"',openingStock='"+txtOpeningStock.getText().trim().toString()+"',entryTime=NOW(),createBy='"+sessionbeam.getUserId()+"' where ModelId='"+modelId+"'";
												System.out.println("update "+updatesql);
												db.sta.executeUpdate(updatesql);
												JOptionPane.showMessageDialog(null, "Data Edit Successfully!");
												loadProductItem();
												txtClear();
												autoId();
		
										}
									} catch (Exception e) {
										e.printStackTrace();
										JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
									}
								}
								else{
									JOptionPane.showMessageDialog(null, "Warning!!, Please Provide The Opening Stock Of This Mobile");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Warning!!, Please Provide The Sell Price Of This Mobile");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!, Please Provide The Buy Price Of This Mobile");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Item Name");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!, Please Provide Item  Serial No");
				}

	}
	private void txtClear(){
		cmbBrand.setSelectedItem("");
		cmbProductCode.txtMrNo.setText("");
		txtProductSl.setText("");
		txtBuyPrice.setText("");
		txtSellPrice.setText("");
		txtOpeningStock.setText("");
		txtProductName.setText("");
	}
	public boolean checkDoplicateItemCode(){
		try {
			ResultSet rs=db.sta.executeQuery("select tbiteminformation.productId from tbiteminformation where tbiteminformation.productId='"+txtProductSl.getText().trim().toString()+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	public boolean checkDoplicateItemName(){
		try {
			ResultSet rs=db.sta.executeQuery("select tbiteminformation.ProductName from tbiteminformation ");
			while(rs.next()){
				if(txtProductName.getText().trim().toString().equalsIgnoreCase(rs.getString("ProductName"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	public void loadProductItem(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *,(select tbbrandinfo.brandName from tbbrandinfo where tbbrandinfo.brandId=tbiteminformation.BrandId)as brandName,(select tbbrandwisedepartmentinfo.ModelName from tbbrandwisedepartmentinfo where tbbrandwisedepartmentinfo.modelId=tbiteminformation.ModelId)as modelName from tbiteminformation order by tbiteminformation.productId asc");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("productId"),rs.getString("ProductName"),rs.getString("ModelName"),rs.getString("brandName"),rs.getString("buyPrice"),rs.getString("sellPrice"),rs.getString("openingStock")});
			}
			for(int a=0;a<10;a++){
				model.addRow(new Object[]{"","","","","","",""});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void loadBrand(){
		try {
			cmbBrand.removeAllItems();
			cmbFBrand.removeAllItems();
			ResultSet rs=db.sta.executeQuery("select tbbrandinfo.brandName from tbbrandinfo ");
			cmbBrand.addItem("");
			cmbFBrand.addItem("");
			while(rs.next()){
				cmbBrand.addItem(rs.getString("brandName"));
				cmbFBrand.addItem(rs.getString("brandName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void textEvent(){
		cmbFBrand.addKeyListener(new KeyListener() {

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
					if(cmbFBrand.getSelectedIndex()!=0){
						try {
							cmbFProductCode.v.clear();
							cmbFProductCode.v.add("");
							ResultSet rs=db.sta.executeQuery("select tbbrandwisedepartmentinfo.ModelName from tbbrandwisedepartmentinfo where tbbrandwisedepartmentinfo.brandid=(select tbbrandinfo.brandId from tbbrandinfo where tbbrandinfo.brandName='"+cmbFBrand.getSelectedItem().toString()+"' )");
							while(rs.next()){
								cmbFProductCode.v.add(rs.getString("ModelName"));
							}
							cmbFProductCode.txtMrNo.requestFocusInWindow();
						} catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
						}
					}
				}
			}
		});
		
		cmbBrand.addKeyListener(new KeyListener() {

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
					if(cmbBrand.getSelectedIndex()!=0){
						try {
							cmbProductCode.v.clear();
							cmbProductCode.v.add("");
							ResultSet rs=db.sta.executeQuery("select tbbrandwisedepartmentinfo.ModelName from tbbrandwisedepartmentinfo where tbbrandwisedepartmentinfo.brandid=(select tbbrandinfo.brandId from tbbrandinfo where tbbrandinfo.brandName='"+cmbBrand.getSelectedItem().toString()+"' )");
							while(rs.next()){
								cmbProductCode.v.add(rs.getString("ModelName"));
							}
							cmbProductCode.txtMrNo.requestFocusInWindow();
						} catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
						}
					}
				}
			}
		});
		cmbProductCode.txtMrNo.addKeyListener(new KeyListener() {

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
					txtProductName.requestFocusInWindow();
				}
			}
		});
		txtProductName.addKeyListener(new KeyListener() {

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
					txtBuyPrice.requestFocusInWindow();
				}
			}
		});
		txtBuyPrice.addKeyListener(new KeyListener() {

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
					txtSellPrice.requestFocusInWindow();
				}
			}
		});
		txtSellPrice.addKeyListener(new KeyListener() {

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
					txtOpeningStock.requestFocusInWindow();
				}
			}
		});
	}
/*	private boolean checkBand(){
		try {
			ResultSet rs=db.sta.executeQuery("select tbbrandinfo.brandName from tbbrandinfo");
			while(rs.next()){
				if(cmbBrand.getSelectedItem().toString().equalsIgnoreCase(rs.getString("brandName"))){
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	private boolean checkModel(){
		try {
			ResultSet rs=db.sta.executeQuery("select tbbrandwisedepartmentinfo.ModelName from tbbrandwisedepartmentinfo");
			while(rs.next()){
				if(cmbProductCode.txtMrNo.getText().trim().toString().equalsIgnoreCase(rs.getString("ModelName"))){
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}*/
	public void cmp(){
		setOpaque(false);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1340, 600));
		add(NorthPanel, BorderLayout.NORTH);
		NorthPanel.setOpaque(false);
		NorthPanel_work();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setOpaque(false);
		centerPanel_work();
		add(SouthPanel, BorderLayout.SOUTH);
		SouthPanel.setOpaque(false);
		SouthPanel_work();
	}
	private void NorthPanel_work() {
		NorthPanel.setPreferredSize(new Dimension(400, 45));
		//NorthPanel.setBorder(BorderFactory.createTitledBorder(""));
		FlowLayout flow=new FlowLayout();
		flow.setAlignment(flow.LEFT);
		NorthPanel.setLayout(flow);
		NorthPanel.setBackground(Color.white);

		NorthPanel.add(lblProductSl);
		NorthPanel.add(txtProductSl);
		txtProductSl.setEditable(false);
		txtProductSl.setPreferredSize(new Dimension(85, 32));

		NorthPanel.add(lblProductBrand);
		NorthPanel.add(cmbBrand);
		cmbBrand.setPreferredSize(new Dimension(140, 32));

		NorthPanel.add(lblProductBand);
		NorthPanel.add(cmbProductCode.combo);
		cmbProductCode.combo.setPreferredSize(new Dimension(100, 32));

		NorthPanel.add(lblProductName);
		NorthPanel.add(txtProductName);
		txtProductName.setPreferredSize(new Dimension(85, 32));
		
		NorthPanel.add(lblBuyPrice);
		NorthPanel.add(txtBuyPrice);
		txtBuyPrice.setPreferredSize(new Dimension(85, 32));

		NorthPanel.add(lblSellPrice);
		NorthPanel.add(txtSellPrice);
		txtSellPrice.setPreferredSize(new Dimension(85, 32));

		NorthPanel.add(lblOpeningStock);
		NorthPanel.add(txtOpeningStock);
		txtOpeningStock.setPreferredSize(new Dimension(85, 32));

		NorthPanel.add(btnSubmit);
		btnSubmit.setMnemonic(KeyEvent.VK_S);
		btnSubmit.setPreferredSize(new Dimension(95, 32));

		NorthPanel.add(btnRefresh);
		btnRefresh.setMnemonic(KeyEvent.VK_R);
		btnRefresh.setPreferredSize(new Dimension(45, 32));
	}
	private void centerPanel_work() {
		centerPanel.setPreferredSize(new Dimension(400, 60));
		centerPanel.setBorder(BorderFactory.createTitledBorder(""));
		centerPanel.setBackground(Color.white);
		centerPanel.add(lblFProductBrand);
		centerPanel.add(cmbFBrand);
		cmbFBrand.setPreferredSize(new Dimension(140, 32));

		centerPanel.add(lblFProductBand);
		centerPanel.add(cmbFProductCode.combo);
		cmbFProductCode.combo.setPreferredSize(new Dimension(110, 32));
		centerPanel.add(btnFind);
		btnFind.setMnemonic(KeyEvent.VK_F);
		btnFind.setPreferredSize(new Dimension(90, 32));
	}
	private void SouthPanel_work() {
		SouthPanel.setPreferredSize(new Dimension(400, 490));
		SouthPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		SouthPanel.setLayout(new BorderLayout());
		SouthPanel.add(scroll);
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(true);
		table.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.setRowHeight(table.getRowHeight()+10);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(260);
		table.getColumnModel().getColumn(2).setPreferredWidth(140);
		table.getColumnModel().getColumn(3).setPreferredWidth(180);
		table.getColumnModel().getColumn(4).setPreferredWidth(140);
		table.getColumnModel().getColumn(5).setPreferredWidth(140);
		table.getColumnModel().getColumn(6).setPreferredWidth(140);
		table.setSelectionForeground(Color.WHITE);
		table.setFont(new Font("arial", Font.BOLD, 14));
		SouthPanel.add(btnPrint, BorderLayout.SOUTH);
		btnPrint.setMnemonic(KeyEvent.VK_P);
		btnPrint.setPreferredSize(new Dimension(90, 40));
	}
}
