package com.ShareClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints.Key;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.RootFrame.RootFrame;

public class Login extends  JFrame{

	db_coonection db=new db_coonection();
	JTable table=new JTable();
	JPanel MainPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	JPanel CenterPanel=new JPanel();
	JPanel SouthPanel=new JPanel();

	JButton btnLogin=new JButton("Login",new ImageIcon("icon/login_icon.gif"));
	JLabel lblusername=new JLabel("Username");
	JLabel lblpassword=new JLabel("Password");
	JTextField txtusername=new JTextField("Admin",15);
	JPasswordField txtPassword=new JPasswordField("sa",15);
	GridBagConstraints grid=new GridBagConstraints();

	JLabel lblLeftTitle=new JLabel(" POINT OF SALES");
	JLabel lblRightTitle=new JLabel("GENERAL HOSPITAL");

	String msg=" SURGISCOPE";
	String msg1="HOSPITAL LTD.";
	Thread t;
	SessionBeam sessionBeam=new SessionBeam();
	public Login(SessionBeam sessionbeam){
		this.sessionBeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		init();
		setCmp();
		btnActionEvent();
/*		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int cofrim=JOptionPane.showConfirmDialog(null, "Are you sure to exit from whole system", "Confrim......",JOptionPane.YES_NO_OPTION);
				if(cofrim==JOptionPane.YES_OPTION){
					dispose();
				}
				else{
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});*/
	}
	private void btnActionEvent(){
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnLoginEvent();
			}
		});
	}
	private void btnLoginEvent() {
		if(!txtusername.getText().trim().toString().isEmpty()){
			if(!txtPassword.getText().trim().toString().isEmpty()){
				String sql="select *from tblogin";
				if(checkCurrentUser(sql)){
						openFrame();
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Invalid Username Or Password");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Password");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Username");
		}
	}
	private boolean checkCurrentUser(String sql) {
		try {
			db.conect();
			PreparedStatement pst = db.con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				if(txtusername.getText().trim().toString().equalsIgnoreCase(rs.getString("username")) && txtPassword.getText().trim().toString().equalsIgnoreCase(rs.getString("password"))){
					sessionBeam.setUserId(rs.getString("user_id"));
					sessionBeam.setUserName(txtusername.getText().trim().toString());
					return true;
				}
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		finally {
			try {
				db.con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	private boolean checkLoginUserName(){
		try {
			db.conect();
			PreparedStatement pst = db.con.prepareStatement("select username from tbCheckUserLogin where username='"+txtusername.getText().trim().toString()+"'");
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				return true;
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		finally {
			try {
				db.con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	private void openFrame(){
		RootFrame rf=new RootFrame(this,sessionBeam);
		add(rf);
		MainPanel.setVisible(false);
		rf.setVisible(true);
	}
	private void init(){
		this.setSize(520,300);
		this.setVisible(true);
		this.setTitle("Please Provide Your Valid Username & Password");
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("icon/34.jpg"));
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.lightGray);
	}
	private void setCmp(){
		add(MainPanel);
		MainPanel.setPreferredSize(new Dimension(520,450));
		MainPanel.setLayout(new BorderLayout());
		MainPanel.add(NorthPanel, BorderLayout.NORTH);
		NorthPanel_work();
		MainPanel.add(CenterPanel, BorderLayout.CENTER);
		CenterPanel_work();
		MainPanel.add(SouthPanel, BorderLayout.SOUTH);
		SouthPanel_work();
	}
	private void NorthPanel_work() {
		NorthPanel.setPreferredSize(new Dimension(500, 90));
		//NorthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		NorthPanel.setBackground(new Color(72, 72, 72));
		NorthPanel.setLayout(new GridBagLayout());
		//flow.setAlignment(flow.LEFT);
		NorthPanel.add(lblLeftTitle);
		lblLeftTitle.setPreferredSize(new Dimension(260, 20));
		lblLeftTitle.setFont(new Font("arial", Font.BOLD, 25));
		lblLeftTitle.setForeground(Color.white);

/*		NorthPanel.add(new JLabel(new ImageIcon("icon/34.jpg")));

		NorthPanel.add(lblRightTitle);
		lblRightTitle.setPreferredSize(new Dimension(185, 20));
		lblRightTitle.setFont(new Font("arial", Font.BOLD, 18));
		lblRightTitle.setForeground(Color.white);*/
	}
	private void CenterPanel_work() {
		CenterPanel.setPreferredSize(new Dimension(500, 120));
		CenterPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		CenterPanel.setBackground(Color.white);
		CenterPanel.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=new GridBagConstraints().BOTH;
		grid.insets=new Insets(3, 3, 3, 3);
		CenterPanel.add(lblusername,grid);
		lblusername.setFont(new Font("arial", Font.BOLD, 14));


		grid.gridx=1;
		grid.gridy=0;
		CenterPanel.add(lblpassword,grid);
		lblpassword.setFont(new Font("arial", Font.BOLD, 14));


		grid.gridx=0;
		grid.gridy=1;
		CenterPanel.add(txtusername,grid);
		txtusername.setPreferredSize(new Dimension(230, 38));
		txtusername.setBackground(Color.lightGray);
		txtusername.setFont(new Font("arial", Font.BOLD, 16));
		txtusername.requestFocusInWindow();

		grid.gridx=1;
		grid.gridy=1;
		CenterPanel.add(txtPassword,grid);
		txtPassword.setBackground(Color.lightGray);
		txtPassword.setPreferredSize(new Dimension(230, 38));
		txtPassword.setFont(new Font("arial", Font.BOLD, 16));
		
		final Component ob[] = {txtusername,txtPassword,btnLogin};	
		new FocusMoveByEnter(ob);
	}
	private void SouthPanel_work() {
		SouthPanel.setPreferredSize(new Dimension(500, 75));
		//SouthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		SouthPanel.setBackground(new Color(72, 72, 72));
		SouthPanel.setLayout(new GridBagLayout());
		SouthPanel.add(btnLogin);
		btnLogin.setPreferredSize(new Dimension(460, 38));
		btnLogin.setBackground(new Color(72, 72, 72));
		btnLogin.setForeground(Color.white);
		btnLogin.setFont(new Font("arial", Font.BOLD, 16));
		btnLogin.setMnemonic(KeyEvent.VK_L);
	}

}

