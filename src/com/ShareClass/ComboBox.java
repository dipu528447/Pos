package com.ShareClass;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ComboBox extends JComboBox {
    private List<String> array;
    public final JTextField textfield;
    public ComboBox(List<String> array) {
        super(array.toArray());
        this.array = array;
        this.setEditable(true);
        textfield = (JTextField) this.getEditor().getEditorComponent();
        textfield.addMouseListener(new MouseListener() {
			
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
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
					public void run() {
                        comboFilter(textfield.getText());
                    }
                });
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
       textfield.addKeyListener(new KeyAdapter() {
            @Override
			public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
					public void run() {
                        comboFilter(textfield.getText());
                    }
                });
            }
        });

    }
    public void comboFilter(String enteredText) {
        List<String> filterArray= new ArrayList<String>();
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
                filterArray.add(array.get(i));
            }
        }
        if (filterArray.size() >=0) {
            this.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            this.setSelectedItem(enteredText);
            this.showPopup();
        }
/*        else {
            this.hidePopup();
        }*/
    }

    /* Testing Codes */
    public static List<String> populateArray() {
        List<String> test = new ArrayList<String>();
        test.add("");
        test.add("Mountain Flight");
        test.add("Mount Climbing");
        test.add("Trekking");
        test.add("Rafting");
        test.add("Jungle Safari");
        test.add("Bungie Jumping");
        test.add("Para Gliding");
        return test;
    }
    
    public static void makeUI() {
        JFrame frame = new JFrame("Adventure in Nepal - Combo Filter Test");
        ComboBox acb = new ComboBox(populateArray());
        frame.getContentPane().add(acb);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {

        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        makeUI();
    }
}