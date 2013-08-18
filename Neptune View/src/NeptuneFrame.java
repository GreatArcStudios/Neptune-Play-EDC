/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JScrollPane;
import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author ericzhu
 */
public class NeptuneFrame extends javax.swing.JInternalFrame {

	public NeptuneFrame(String image) {
		JScrollPane Scroll = new JScrollPane();
		JLabel label = new JLabel();
		this.setClosable(true);
		this.setIconifiable(true);
		this.setLocation(100, 100);
		this.setResizable(true);
		this.setSize(600, 600);
		this.getAccessibleContext().setAccessibleName("Neptune View IF");
		this.getAccessibleContext().setAccessibleDescription(
				"Neptune View Internal Frame");
		Scroll.setViewportView(label);
		this.getContentPane().add(Scroll, BorderLayout.CENTER);
		this.setTitle(image);
		label.setIcon(new ImageIcon(image));
		this.pack();

	}

}
