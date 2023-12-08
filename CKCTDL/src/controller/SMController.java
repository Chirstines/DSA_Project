package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import view.ControllerView;

public class SMController implements Action{
	ControllerView contr;
	public SMController(ControllerView contr) {
		this.contr = contr;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionComand = e.getActionCommand();
		switch (actionComand) {
		case "Thêm":
			this.contr.clearFields();
			break;
		case "Lưu":
			try {
				this.contr.addToTable();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			break;
		case "Cập Nhật":
			this.contr.displayProgram();
			break;
		case "Xóa":
			this.contr.deleteTableRow();
			break;
		case "Hủy Bỏ":
			this.contr.clearFields();
			break;
		case "Tìm Kiếm":
			this.contr.search();
			break;
		case "Hủy Tìm Kiếm":
			this.contr.cancel();
			break;
		case "Sắp Xếp Theo Điểm":
			this.contr.sortByScore();
			break;
		case "Sắp Xếp Theo Học Phí":
			this.contr.sortByTuition();
			break;
		case "About Me":
			this.contr.disPlayAbout();
			break;
		case "Exit":
			this.contr.exit();
			break;
		case "Open":
			this.contr.readFile();
			this.contr.setDataToTable();
			this.contr.setBoxSearch();
			break;
		case "Save":
			this.contr.saveFile();
			break;
		}
	}
	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
