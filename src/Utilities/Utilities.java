package Utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Utilities {
	private static JFrame Window;
	private static JPanel ModulePanel;
	private static JTextPane OutputPane;
	
	public static enum CommandCode {
		OK, ERROR, NOT_FOUND
	}
	
	public static void setWindow(JFrame window){
		Window = window;
	}
	
	public static JFrame getWindow(){
		return Window;
	}
	
	public static void setModulePanel(JPanel modulePanel){
		ModulePanel = modulePanel;
	}
	
	public static JPanel getModulePanel(){
		return ModulePanel;
	}
	
	public static void setOutputPane(JTextPane outputPane){
		OutputPane = outputPane;
	}
	
	public static JTextPane getOutputPane(){
		return OutputPane;
	}
	
	public static void fixOutputSpacing(){
		MutableAttributeSet set = new SimpleAttributeSet(OutputPane.getParagraphAttributes());
        StyleConstants.setLineSpacing(set, new Float(0.4));
        OutputPane.setParagraphAttributes(set, false);
	}
	
	public static Console getConsole(){
		return new Console();
	}
	
	public static class Console {
		private static Object console = null;
		
		public static KeyListener InputKeyListener = new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0){
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					System.out.println("ENTER (CONSOLE)");
				
				// String data = (JTextField)arg0.getSource().getText();
				// Console.formatUserMessage(data);
				// (JTextField)arg0.getSource().setText("");
				}	
			}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {}
		};
		public static ActionListener InputActionListener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		};
		public static Object getConsole(){
			return console;
		}
		
		public static void setConsole(Object obj){
			console = obj;
		}
		
		public static boolean formatUserMessage(String message){
			return false;
		}
		
		public static String formatSystemMessage(String message){
			return null;
		}
		
		public static String formatErrorMessage(String message){
			return null;
		}
	}
	
	public static class Window {
		public static KeyListener InputKeyListener = new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0){
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					System.out.println("ENTER (WINDOW)");
				
				// String data = (JTextField)arg0.getSource().getText();
				// Console.formatUserMessage(data);
				// (JTextField)arg0.getSource().setText("");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {}
		};
		public static ActionListener InputActionListener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		};
		public static String formatUserMessage(String message){
			return null;
		}
		
		public static String formatSystemMessage(String message){
			return null;
		}
	}
}
