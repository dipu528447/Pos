package com.ShareClass;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CommonButton extends JPanel{
	public JButton btnNew= new JButton("New");
	public JButton btnEdit= new JButton("Edit");
	public JButton btnSave= new JButton("Save");
	public JButton btnSubmit= new JButton("Submit");
	public JButton btnConfirm= new JButton("Confirm");
	public JButton btnRefresh= new JButton("Refresh");
	public JButton btnDelete= new JButton("Delete");
	public JButton btnFind= new JButton("Find");
	public JButton btnCancel= new JButton("Cancel");
	public JButton btnPreview= new JButton("Preview");
	public JButton btnChequePrint= new JButton("Print");
	public JButton btnExit= new JButton("Exit");
	public CommonButton(String New, String Save,String Submit, String Edit, String Delete, String Find,String Confrim, String Refresh, String Cancel, String Preview, String Print,String Exit){
		if(New.equals("New")){
			SetButtonSize(btnNew);
			btnNew.setIcon(new ImageIcon("icon/new.png"));	
			add(btnNew);
		}
		if(Save.equals("Save")){
			SetButtonSize(btnSave);
			btnSave.setIcon(new ImageIcon("icon/save.png"));	
			add(btnSave);
		}
		if(Submit.equals("Submit")){
			SetButtonSize(btnSubmit);
			btnSubmit.setIcon(new ImageIcon("icon/save.png"));	
			add(btnSubmit);
		}
		if(Edit.equals("Edit")){
			SetButtonSize(btnEdit);
			btnEdit.setIcon(new ImageIcon("icon/edt.png"));	
			add(btnEdit);
		}
		if(Delete.equals("Delete")){
			SetButtonSize(btnDelete);
			btnDelete.setIcon(new ImageIcon("icon/delete.png"));	
			add(btnDelete);
		}
		if(Find.equals("Find")){
			SetButtonSize(btnFind);
			btnFind.setIcon(new ImageIcon("icon/find.png"));	
			add(btnFind);
		}
		if(Confrim.equals("Confirm")){
			SetButtonSize(btnConfirm);
			btnConfirm.setIcon(new ImageIcon("icon/save.png"));	
			add(btnConfirm);
		}
		if(Refresh.equals("Refresh")){
			SetButtonSize(btnRefresh);
			btnRefresh.setIcon(new ImageIcon("icon/reresh.png"));	
			add(btnRefresh);
		}
		if(Cancel.equals("Cancel")){
			SetButtonSize(btnCancel);
			btnCancel.setIcon(new ImageIcon("icon/reresh.png"));	
			add(btnCancel);
		}
		if(Preview.equals("Preview")){
			SetButtonSize(btnPreview);
			btnPreview.setIcon(new ImageIcon("icon/Preview.png"));	
			add(btnPreview);
		}
		if(Print.equals("Print")){
			SetButtonSize(btnChequePrint);
			btnChequePrint.setIcon(new ImageIcon("icon/print.png"));	
			add(btnChequePrint);
		}
		if(Exit.equals("Exit")){
			SetButtonSize(btnExit);
			btnExit.setIcon(new ImageIcon("icon/Exit.png"));	
			add(btnExit);
		}
	}
	private void SetButtonSize(JButton Btn){
		Btn.setPreferredSize(new Dimension(95,33));
	}
}
