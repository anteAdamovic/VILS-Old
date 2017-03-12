package System;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import Utilities.*;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

@SuppressWarnings("unused")
public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	private String title =  "Hami v0.1";
	private String version = "0.1";
	
	public Window(){
		initialize();
	}
	
	private void initialize(){
        setTitle(title);
        setPreferredSize(new Dimension(900, 700));
        //setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        pack();
        
        // JMenuBar menuBar
        JMenuBar menuBar = new JMenuBar();
        	// JMenu File
        	JMenu FileMenu = getFileMenu();
        	menuBar.add(FileMenu);
        	// JMenu Options
        	JMenu OptionsMenu = getOptionsMenu();  
        	menuBar.add(OptionsMenu);
        	// JMenu Help
        	JMenu HelpMenu = getHelpMenu();
        	menuBar.add(HelpMenu);
        	
        add(menuBar, BorderLayout.PAGE_START);
        // JMenuBar menuBar END
        
        // JPanel modulePanel
        JPanel modulePanel = getModulePanel();
        Utilities.setModulePanel(modulePanel);
        add(modulePanel, BorderLayout.CENTER);
        // JPanel modulePanel END

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        bottomPanel.setSize(new Dimension(900, 450));
        add(bottomPanel, BorderLayout.PAGE_END);
        
        // JTextPane outputPane
        JTextPane outputPane = getOutputPane();
        Utilities.setOutputPane(outputPane);
        JScrollPane scrollPane = new JScrollPane(outputPane);
        scrollPane.setMaximumSize(new Dimension(900, 350));
        bottomPanel.add(scrollPane, BorderLayout.PAGE_START);
        // JTextPane outputPane END
        
        // JPanel inputPanel
        JPanel inputPanel = getInputPanel();
        bottomPanel.add(inputPanel, BorderLayout.PAGE_END);
        // JPanel inputPanel END
        
        Utilities.setWindow(this);
	}

	private JPanel getInputPanel() {
		JTextField inputField = new JTextField();
		inputField.setPreferredSize(new Dimension(800, 25));
		inputField.setMargin(new Insets(1, 1, 1, 5));
		inputField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		inputField.addKeyListener(Utilities.Window.InputKeyListener);
		
		JLabel label = new JLabel("Input :");
		label.setPreferredSize(new Dimension(40, 25));
		
		JButton button = new JButton("Send");
		button.setPreferredSize(new Dimension(60, 25));
		button.setMargin(new Insets(1, 1, 1, 1));
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(inputField.getText().trim().isEmpty())
					return;
				
				if(inputField.getText().equals("/console")){
					if(Utilities.Console.getConsole() == null){
						EventQueue.invokeLater(new Runnable() {
							@Override
							public void run() {
								Console console = new Console();
								console.setVisible(true);
								Utilities.Console.setConsole(console);
							}
						});
					}
				}
				else{
					return;
				}
				
				String output = Utilities.getOutputPane().getText();
				output = output.substring(0, output.lastIndexOf("</body>"));
				if(inputField.getText().length() <= 50)
					output += "<p color=\"blue\" style=\"margin-top: 0\">User: " + inputField.getText() + "</p>" + String.format("%n");
				else{
					String data = inputField.getText();
					output += "<p color=\"blue\" style=\"margin-top: 0\">User: " + data.substring(0, 50) + "</p>" + String.format("%n");
					data = data.substring(50, data.length());
					while(true){
						if(data.length() >= 55){
							output += "<p color=\"blue\" style=\"margin-top: 0\">" + data.substring(0, 55) + "</p>" + String.format("%n");
							data = data.substring(55, data.length());
						}else{
							output += "<p color=\"blue\" style=\"margin-top: 0\">" + data.substring(0, data.length()) + "</p>" + String.format("%n");
							break;
						}							
					}
				}
				output += "</body></html>";
				Utilities.getOutputPane().setText(output);
				Utilities.fixOutputSpacing();
				
				inputField.setText("");
				System.out.println(output);
			}

			private void TestOutput() throws InterruptedException {
				for(int i = 0; i < 300; i++){
					String output = Utilities.getOutputPane().getText();
					output = output.substring(0, output.lastIndexOf("</body>"));
					String line = "";
					for(int j = 0; j < i; j++){
						line += "#";
					}
					if(line.length() <= 90)
						output += "<p color=\"blue\" style=\"margin-top: 0\">User: " + line + "</p>" + String.format("%n");
					else{
						String data = line;
						output += "<p color=\"blue\" style=\"margin-top: 0\">User: " + data.substring(0, 90) + "</p>" + String.format("%n");
						data = data.substring(90, data.length());
						while(true){
							if(data.length() >= 95){
								output += "<p color=\"blue\" style=\"margin-top: 0\">" + data.substring(0, 95) + "</p>" + String.format("%n");
								data = data.substring(95, data.length());
							}else{
								output += "<p color=\"blue\" style=\"margin-top: 0\">" + data.substring(0, data.length()) + "</p>" + String.format("%n");
								break;
							}							
						}
					}
					output += "</body></html>";
					Utilities.getOutputPane().setText(output);
					Utilities.fixOutputSpacing();
				}
				
			}	
		});
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		inputPanel.add(label, BorderLayout.WEST);
		inputPanel.add(inputField, BorderLayout.CENTER);
		inputPanel.add(button, BorderLayout.EAST);
		
		return inputPanel;
	}

	private JTextArea getInputArea() {
		JTextArea inputArea = new JTextArea();
		inputArea.setPreferredSize(new Dimension(900, 25));
		inputArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		return inputArea;
	}

	private JTextPane getOutputPane() {
		JTextPane outputPane = new JTextPane();
        outputPane.setContentType("text/html");
        outputPane.setPreferredSize(new Dimension(900, 350));
        outputPane.setMaximumSize(new Dimension(900, 350));
        MutableAttributeSet set = new SimpleAttributeSet(outputPane.getParagraphAttributes());
        StyleConstants.setLineSpacing(set, new Float(0.4));
        //outputPane.setText("<p color=\"red\">User1: Bla </p>" + String.format("%n") + "<p color=\"blue\">User1: Bla </p>");
        outputPane.setParagraphAttributes(set, false);
        outputPane.setEditable(false);
        return outputPane;
	}

	private JPanel getModulePanel() {
		JPanel modulePanel = new JPanel();
        modulePanel.setLayout(new GridLayout(0, 2));
        //modulePanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        //modulePanel.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);
        modulePanel.setPreferredSize(new Dimension(900,400));
        modulePanel.setMaximumSize(modulePanel.getPreferredSize());
        modulePanel.setMinimumSize(modulePanel.getPreferredSize());
       
        // TEST !!!
        for(int t = 1; t <= 20; ++t) modulePanel.add(new Label("Module " + t));
        
        return modulePanel;
	}

	private JMenu getHelpMenu() {
		JMenu HelpMenu = new JMenu("Help");
		return HelpMenu;
	}

	private JMenu getOptionsMenu() {
		JMenu OptionsMenu = new JMenu("Options");
		return OptionsMenu;
	}

	private JMenu getFileMenu() {
		JMenu FileMenu = new JMenu("File");
		JMenuItem fileStart = new JMenuItem("Start");
        fileStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.SHIFT_MASK));
        fileStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Start clicked.");
			}
        });
        
        JMenuItem fileStop = new JMenuItem("Stop");
        fileStop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.SHIFT_MASK));
        fileStop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Stop clicked.");
			}
        });
        
        JMenuItem fileExit = new JMenuItem("Exit");
        
        FileMenu.add(fileStart);
        FileMenu.add(fileStop);
        FileMenu.addSeparator();
        FileMenu.add(fileExit);
        
        return FileMenu;
	}

}
