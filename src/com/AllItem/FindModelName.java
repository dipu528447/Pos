package com.AllItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;



public class FindModelName extends JDialog {
	ModelCreate brand;
	SessionBeam sessionBeam;
	db_coonection db=new db_coonection();
	JPanel panel=new JPanel();
	
	String col[]={" Brand Name"," Model Id","Model Name"};
	Object row[][]={}; 
	DefaultTableModel model=new DefaultTableModel(row,col);
	public JTable table=new JTable(model){
		public boolean isCellEditable(int row,int col){
			return false;
		}
	};
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String secId="";
	public FindModelName(SessionBeam sessionbeam){
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.sessionBeam=sessionbeam;
		init();
		addCmp();
		addRow();
		//TableActionEvent();
	}
	public void TableActionEvent(){
		table.addMouseListener(new MouseListener() {
			
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
				secId=table.getValueAt(table.getSelectedRow(), 1).toString();
				System.out.println("secId "+secId);
				brand=new ModelCreate(sessionBeam);
				brand.setData(secId);
				dispose();
			}
		});
	}
	public void addRow(){
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
		try {
			ResultSet rs=db.sta.executeQuery("select (select tbbrandinfo.brandName from tbbrandinfo where tbbrandinfo.brandId=tbbrandwisedepartmentinfo.brandid)as brandName,tbbrandwisedepartmentinfo.modelId,tbbrandwisedepartmentinfo.ModelName from tbbrandwisedepartmentinfo");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("brandName"),rs.getString("modelId"),rs.getString("ModelName")});
			}
			for(int i=0;i<14;i++)
			{
				model.addRow(new Object[]{"",""});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void addCmp(){
		add(panel);
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(550,400));
		panel.setBackground(new Color(117,135,131));
		panel.add(scroll);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(table.getRowHeight()+12);
		table.getColumnModel().getColumn(0).setPreferredWidth(180);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.setShowGrid(true);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int i=0;i<3;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}
	private void init(){
		setSize(550,400);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Find Model Information");
		setAlwaysOnTop(true);
	}
}
