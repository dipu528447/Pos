package com.AllItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.ShareClass.CommonButton;
import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;


public class ModelCreate extends JPanel{
	db_coonection db=new db_coonection();
	CommonButton cButton=new CommonButton("New","Save","","Edit","","Find","","Refresh","","","","");
	SessionBeam sessionBeam;
	JPanel panelSouth=new JPanel();
	public JPanel mainPanel=new JPanel();
	JPanel panelNorthSouth=new JPanel();
	JPanel panelSouthSouth=new JPanel();

	JLabel lblModelId=new JLabel("Model. Id:        ");
	JLabel lblModelName=new JLabel("Model. Name:");
	JLabel lblBrandName=new JLabel("Brand Name: ");

	JTextField txtModelId=new JTextField(6);
	JTextField txtModelName=new JTextField(25);


	String BrandName[]={""};
	JComboBox cmbBrandName=new JComboBox(BrandName);
	int update=0;
	String secId="";
	BufferedImage image;
	public ModelCreate(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		btnActionEvent();
		autoId();
		IsEnable(true);
		background();
		
	}
	public void loadBrandName(){
		try {
			cmbBrandName.removeAllItems();
			cmbBrandName.addItem("");
			ResultSet rs=db.sta.executeQuery("select tbBrandinfo.BrandName from tbBrandinfo where tbBrandinfo.BrandId!='1' order by tbBrandinfo.BrandName");
			while(rs.next()){
				cmbBrandName.addItem(rs.getString("BrandName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
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
	private void btnActionEvent(){
		cButton.btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnRefreshEvent();
			}
		});
		cButton.btnNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnNewEvent();
			}
		});
		cButton.btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSaveEvent();
			}
		});
		cButton.btnFind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnFindEvent();

			}
		});
		cButton.btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update=1;
				cButton.btnEdit.setEnabled(false);
				txtModelName.setEnabled(true);
				cmbBrandName.setEnabled(true);
			}
		});

	}
	public void setData(String sec){
		try {
			ResultSet rs=db.sta.executeQuery("select (select tbbrandinfo.brandName from tbbrandinfo where tbbrandinfo.brandId=tbbrandwisedepartmentinfo.brandid)as brandName,tbbrandwisedepartmentinfo.modelId,tbbrandwisedepartmentinfo.ModelName from tbbrandwisedepartmentinfo where tbbrandwisedepartmentinfo.modelId='"+sec+"' ");
			while(rs.next()){
				cmbBrandName.setSelectedItem(rs.getString("brandName"));
				txtModelId.setText(rs.getString("modelId"));
				txtModelName.setText(rs.getString("ModelName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnFindEvent() {
		final FindModelName find=new FindModelName(sessionBeam);
		find.addRow();
		find.table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String secId=find.table.getValueAt(find.table.getSelectedRow(), 1).toString();
				System.out.println("1secId "+secId);
				setData(secId);
				find.dispose();
				cButton.btnEdit.setEnabled(true);
				cButton.btnSave.setEnabled(true);
				cButton.btnNew.setEnabled(false);
				cButton.btnRefresh.setEnabled(true);

			}
		});
	}
	private void btnSaveEvent() {
		try {
			if(cmbBrandName.getSelectedIndex()!=0){
				if(!txtModelId.getText().trim().toString().isEmpty()){
					if(!txtModelName.getText().trim().toString().isEmpty()){
						if(update==0){
							if(!checkDoplicateSection()){
								autoId();
								String BrandId="";
								ResultSet rs=db.sta.executeQuery("select tbBrandinfo.BrandId from tbBrandinfo where tbBrandinfo.BrandName='"+cmbBrandName.getSelectedItem().toString()+"'");
								while(rs.next()){
									BrandId=rs.getString("BrandId");
								}
								String sql="insert into tbbrandwisedepartmentinfo value ('"+BrandId+"','"+txtModelId.getText().trim().toString()+"','"+txtModelName.getText().trim().toString()+"',NOW(),'"+sessionBeam.getUserId()+"','')";
								System.out.println(sql);
								db.sta.executeUpdate(sql);
								JOptionPane.showMessageDialog(null, "Model Create Successfully");
								btnRefreshEvent();
							}
							else{
								JOptionPane.showMessageDialog(null, "Warning!!,Doplicate Model Name!");
							}
						}
						else if(update==1){
							String BrandId="";
							ResultSet rs=db.sta.executeQuery("select tbBrandinfo.BrandId from tbBrandinfo where tbBrandinfo.BrandName='"+cmbBrandName.getSelectedItem().toString()+"'");
							while(rs.next()){
								BrandId=rs.getString("BrandId");
							}
							String sql="update tbbrandwisedepartmentinfo set Brandid='"+BrandId+"',ModelName='"+txtModelName.getText().trim().toString()+"',entrytime=NOW(),updateBy='"+sessionBeam.getUserId()+"' where ModelId='"+txtModelId.getText().trim().toString()+"'";
							System.out.println(sql);
							db.sta.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Model Update Successfully");
							btnRefreshEvent();
							update=0;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warrning!!,Please Provide Modelartment Name");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warrning!!,Please Provide Modelartment Id");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warrning!!,Please Provide Brand Name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private boolean checkDoplicateSection(){
		try {
			ResultSet rs=db.sta.executeQuery("select ModelName from tbbrandwisedepartmentinfo");
			while(rs.next()){
				if(txtModelName.getText().trim().toString().equalsIgnoreCase(rs.getString("ModelName"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void btnNewEvent() {
		IsEnable(false);
		cmbBrandName.setSelectedItem("");
		txtModelName.setText("");
		cButton.btnNew.setEnabled(false);
		cButton.btnRefresh.setEnabled(true);
		cButton.btnEdit.setEnabled(false);
		autoId();
	}
	private void IsEnable(boolean t){
		cmbBrandName.setEnabled(!t);
		txtModelName.setEnabled(!t);
		cButton.btnSave.setEnabled(!t);
		cButton.btnEdit.setEnabled(!t);
		cButton.btnRefresh.setEnabled(!t);
	}
	private void btnRefreshEvent() {
		cmbBrandName.setSelectedItem("");
		txtModelName.setText("");
		IsEnable(true);
		autoId();
		cButton.btnNew.setEnabled(true);
		cButton.btnRefresh.setEnabled(false);
		cButton.btnEdit.setEnabled(false);
	}
	public void autoId(){
		
		try {
			String sql="select (ifnull(max(ModelId),0)+1)as ModelId from tbbrandwisedepartmentinfo";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				txtModelId.setText(rs.getString("ModelId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void addCmp() {
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(520,200));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panelNorthSouth,BorderLayout.NORTH);
		panelNorthSouth.setOpaque(false);
		panelNorthSouth_work();
		mainPanel.add(panelSouthSouth,BorderLayout.SOUTH);
		panelSouthSouth.setOpaque(false);
		panelSouthSouth_work();
	}
	private void panelNorthSouth_work() {
		panelNorthSouth.setPreferredSize(new Dimension(10,110));
		panelNorthSouth.setBackground(new Color(117,135,131));
		panelNorthSouth.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		//panelNorthSouth.setBorder(BorderFactory.createLineBorder(Color.red));
		FlowLayout flow=new FlowLayout();
		panelNorthSouth.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		panelNorthSouth.add(lblBrandName);
		panelNorthSouth.add(cmbBrandName);
		cmbBrandName.setPreferredSize(new Dimension(170, 28));

		JLabel lblBlank=new JLabel("                                                                                  ");
		panelNorthSouth.add(lblBlank);

		panelNorthSouth.add(lblModelId);
		panelNorthSouth.add(txtModelId);
		txtModelId.setEditable(false);

		JLabel lblBlank1=new JLabel("                                                                                                                ");
		panelNorthSouth.add(lblBlank1);

		panelNorthSouth.add(lblModelName);
		panelNorthSouth.add(txtModelName);
	}
	private void panelSouthSouth_work() {
		panelSouthSouth.setPreferredSize(new Dimension(10,90));
		panelSouthSouth.setBackground(new Color(117,135,131));
		panelSouthSouth.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		FlowLayout flow=new FlowLayout();
		panelSouthSouth.setLayout(flow);
		flow.setAlignment(flow.LEFT);


		panelSouthSouth.add(cButton);
		cButton.setOpaque(false);
		cButton.setBackground(new Color(117,135,131));
	}
}
