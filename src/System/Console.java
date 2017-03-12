package System;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
//import javax.swing.text.Utilities;
import Utilities.*;

public class Console extends JFrame {
	private static final long serialVersionUID = 2L;
	private String title = "Console";
	
	public Console(){
		initialize();
	}
	
	private void initialize(){
		setTitle(title);
		setLayout(new BorderLayout());
		setResizable(false);
		setPreferredSize(new Dimension(450,450));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
	
		// Output panel
		JPanel outputPanel = getOutputPanel();
		JScrollPane outputScrollPane = new JScrollPane(outputPanel);
		add(outputScrollPane, BorderLayout.CENTER);
	
		// Input panel
		JPanel inputPanel = getInputPanel();
		add(inputPanel, BorderLayout.PAGE_END);
	}
	
	private JPanel getOutputPanel(){
		JPanel outputPanel = new JPanel();
		
		return outputPanel;
	}
	
	private JPanel getInputPanel(){
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		
		JTextField inputField = new JTextField();
		inputField.setPreferredSize(new Dimension(800, 25));
		inputField.setMargin(new Insets(1, 1, 1, 5));
		inputField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		inputField.addKeyListener(Utilities.Console.InputKeyListener);
		
		JLabel label = new JLabel("Input :");
		label.setPreferredSize(new Dimension(40, 25));
		
		JButton button = new JButton("Send");
		button.setPreferredSize(new Dimension(60, 25));
		button.setMargin(new Insets(1, 1, 1, 1));
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				if(inputField.getText().trim().isEmpty())
					return;
				
				
			}
		});
		
		inputPanel.add(label, BorderLayout.WEST);
		inputPanel.add(inputField, BorderLayout.CENTER);
		inputPanel.add(button, BorderLayout.EAST);
		
		return inputPanel;
	}
}
