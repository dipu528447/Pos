package com.ShareClass;

public class CtgComMainClass {
	static SessionBeam sessionBeam=new SessionBeam();
	public static void main(String args[]){
		try {
			try {
				
				db_coonection db=new db_coonection();
				db.conect();
			} catch (Exception e) {
				e.printStackTrace();
			}
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			Login lg=new Login(sessionBeam);
		} catch (Exception e) {
			// TODO: handle exception]
			e.printStackTrace();
		}
		
	}

}
