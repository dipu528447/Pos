package com.ShareClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InventoryCommonButton extends JPanel{
	public JButton btnItemCreate=new JButton("Item Create");
	public JButton btnSupplierCreate=new JButton("Supplier Create");
	public JButton btnPurchaseOrder=new JButton("Purchase Order");
	public JButton btnPurchaseReturn=new JButton("Purchase Return");
	public JButton btnSalesIR=new JButton("Sales / Sales Return");
	public JButton btnWastage=new JButton("Wastage");
	public JButton btnProductWiseStockReport=new JButton("Product Wise Stock");
	public JButton btnSupplierWiseStockReport=new JButton("Supplier Wise Stock");
	public JButton btnCatagoryWiseStockReport=new JButton("Catagory Wise Stock");
	public JButton btnInvoiceReportReport=new JButton("Invoice Report");
	public JButton btnSalesStatement=new JButton("Sales Statement");
	public JButton btnPurchaseStatement=new JButton("Purchase Statement");
	BufferedImage image;
	public InventoryCommonButton(){
		//FlowLayout flow=new FlowLayout();
		setOpaque(false);
		setLayout(new GridLayout());
		//flow.setAlignment(flow.LEFT);
		setPreferredSize(new Dimension(1350, 35));
		add(btnPurchaseOrder);
		btnPurchaseOrder.setBackground(new Color(49,151,200));
		add(btnPurchaseReturn);
		btnPurchaseReturn.setBackground(new Color(49,151,200));
		add(btnSalesIR);
		btnSalesIR.setBackground(new Color(49,151,200));
		//add(btnWastage);
		add(btnProductWiseStockReport);
		btnProductWiseStockReport.setBackground(new Color(49,151,200));
		add(btnSupplierWiseStockReport);
		btnSupplierWiseStockReport.setBackground(new Color(49,151,200));
		add(btnCatagoryWiseStockReport);
		btnCatagoryWiseStockReport.setBackground(new Color(49,151,200));
		add(btnInvoiceReportReport);
		btnInvoiceReportReport.setBackground(new Color(49,151,200));
		add(btnSalesStatement);
		btnSalesStatement.setBackground(new Color(49,151,200));
		add(btnPurchaseStatement);
		btnPurchaseStatement.setBackground(new Color(49,151,200));
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
}
