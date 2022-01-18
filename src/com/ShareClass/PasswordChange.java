package com.ShareClass;

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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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

public class PasswordChange extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionbeam;
	public JPanel mainPanel=new JPanel();

	JLabel lbluserName=new JLabel("  User Name       ");
	JLabel lblOldPassword=new JLabel("  Old Password  ");
	JLabel lblNewPassword=new JLabel("  New Password");

	JTextField txtUsername=new JTextField(12);
	JPasswordField txtOldpassword=new JPasswordField(12);
	JPasswordField txtNewpassword=new JPasswordField(12);
	
	JButton btnChange=new JButton("Change");
	JButton btnReset=new JButton("Reset");
	BufferedImage image;
	public PasswordChange(SessionBeam sessionbeam) {
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
	private void btnActionEvent(){
		btnChange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!txtUsername.getText().trim().toString().isEmpty()){
					if(!txtOldpassword.getText().trim().toString().isEmpty()){
						if(!txtNewpassword.getText().trim().toString().isEmpty()){
							if(checkuser()){
								try {
									String sql="update tblogin set password='"+txtNewpassword.getText().trim().toString()+"' where username='"+txtUsername.getText().trim().toString()+"'";
									db.sta.executeUpdate(sql);
									JOptionPane.showMessageDialog(null, "Password Change Successfully");
									txtUsername.setText("");
									txtOldpassword.setText("");
									txtNewpassword.setText("");
								} catch (Exception e2) {
									e2.printStackTrace();
									JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Sorry!!,Invalid User Name && Password");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Please Provide New Password");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Old Password");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Username");
				}
			}
		});
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtUsername.setText("");
				txtOldpassword.setText("");
				txtNewpassword.setText("");
			}
		});
	}
	public boolean checkuser(){
		try {
			ResultSet rs=db.sta.executeQuery("select tblogin.username,password from tblogin");
			while(rs.next()){
				if(txtUsername.getText().trim().toString().equalsIgnoreCase(rs.getString("username")) && txtOldpassword.getText().trim().toString().equalsIgnoreCase(rs.getString("password"))){
					return true;
				}
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	public void addcmp() {
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(250,190));
		FlowLayout flow=new FlowLayout();
		flow.setAlignment(flow.LEFT);
		mainPanel.setLayout(flow);
		mainPanel.add(lbluserName);
		mainPanel.add(txtUsername);
		

		
		mainPanel.add(lblOldPassword);
		mainPanel.add(txtOldpassword);
		

		
		mainPanel.add(lblNewPassword);
		mainPanel.add(txtNewpassword);
		

	
		mainPanel.add(btnChange);
		mainPanel.add(btnReset);
		btnChange.setPreferredSize(new Dimension(100, 34));
		btnReset.setPreferredSize(new Dimension(100, 34));
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
}
