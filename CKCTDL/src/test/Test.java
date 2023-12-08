package test;

import javax.swing.UIManager;

import view.SMView;

public class Test{
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new SMView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
