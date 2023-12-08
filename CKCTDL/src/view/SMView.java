package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.SMModel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controller.SMController;

public class SMView extends JFrame {
	public ControllerView contr;
	public JComboBox comboBox_SelectedUniversitySearch ;
	public JPanel contentPane;
	public SMModel model;
	public JTextField textField_SelectedProgramNameSearch;
	public JTable table;
	public JTextField textField_ProgramCode;
	public JTextField textField_ProgramName;
	public JTextField textField_UniversityCode;
	public JTextField textField_AdmissionScore;
	public JTextField textField_Address;
	public JTextField textField_TuitionFee;
	public JTextField textField_UniversityName;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SMView frame = new SMView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SMView() {
		this.model = new SMModel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 999, 683);
		
		
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 985, 27);
		contentPane.add(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(menuFile);
		
		JMenuItem menuOpen = new JMenuItem("Open");
		menuOpen.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuFile.add(menuOpen);
		
		JMenuItem menuSave = new JMenuItem("Save");
		menuSave.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuFile.add(menuSave);
		
		JSeparator separator = new JSeparator();
		menuFile.add(separator);
		
		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuFile.add(menuExit);
		
		JMenu menuAbout = new JMenu("About");
		menuAbout.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(menuAbout);
		
		JMenuItem menuAboutMe = new JMenuItem("About Me");
		menuAboutMe.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuAbout.add(menuAboutMe);
		
		JLabel lable_TenTruong = new JLabel("Tên Trường");
		lable_TenTruong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lable_TenTruong.setBounds(10, 57, 89, 34);
		contentPane.add(lable_TenTruong);
		
		JLabel lable_TenNganhDaChon = new JLabel("Tên Ngành");
		lable_TenNganhDaChon.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lable_TenNganhDaChon.setBounds(374, 57, 89, 34);
		contentPane.add(lable_TenNganhDaChon);
		
		textField_SelectedProgramNameSearch = new JTextField();
		textField_SelectedProgramNameSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_SelectedProgramNameSearch.setBounds(481, 57, 158, 35);
		contentPane.add(textField_SelectedProgramNameSearch);
		textField_SelectedProgramNameSearch.setColumns(10);
		
		JButton button_TimKiem = new JButton("Tìm Kiếm");
		button_TimKiem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button_TimKiem.setBounds(663, 57, 118, 35);
		contentPane.add(button_TimKiem);
		
		comboBox_SelectedUniversitySearch = new JComboBox();
		comboBox_SelectedUniversitySearch.addItem("");
		comboBox_SelectedUniversitySearch.setForeground(Color.GRAY);
		comboBox_SelectedUniversitySearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(comboBox_SelectedUniversitySearch);
		comboBox_SelectedUniversitySearch.setBounds(109, 58, 245, 34);
		this.setVisible(true);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBackground(Color.BLACK);
		horizontalStrut.setBounds(0, 105, 985, 6);
		contentPane.add(horizontalStrut);
		
		JLabel lable_dsNganhHoc = new JLabel("Danh Sách Ngành Học");
		lable_dsNganhHoc.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lable_dsNganhHoc.setBounds(10, 121, 169, 34);
		contentPane.add(lable_dsNganhHoc);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 Ng\u00E0nh", "T\u00EAn Ng\u00E0nh", "\u0110i\u1EC3m chu\u1EA9n", "M\u00E3 Tr\u01B0\u1EDDng", "T\u00EAn Tr\u01B0\u1EDDng", "\u0110\u1ECBa Ch\u1EC9", "H\u1ECDc Ph\u00ED"
			}
		));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 165, 985, 181);
		contentPane.add(scrollPane);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBackground(Color.BLACK);
		horizontalStrut_1.setBounds(0, 356, 985, 6);
		contentPane.add(horizontalStrut_1);
		
		JLabel lable_ThongTin = new JLabel("Thông Tin Ngành Học");
		lable_ThongTin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lable_ThongTin.setBounds(10, 372, 169, 34);
		contentPane.add(lable_ThongTin);
		
		JLabel lable_MaNganh_1 = new JLabel("Mã Ngành");
		lable_MaNganh_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lable_MaNganh_1.setBounds(20, 417, 95, 34);
		contentPane.add(lable_MaNganh_1);
		
		textField_ProgramCode = new JTextField();
		textField_ProgramCode.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_ProgramCode.setColumns(10);
		textField_ProgramCode.setBounds(144, 416, 232, 35);
		contentPane.add(textField_ProgramCode);
		
		JLabel lable_TenNganh = new JLabel("Tên Ngành");
		lable_TenNganh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lable_TenNganh.setBounds(20, 462, 95, 34);
		contentPane.add(lable_TenNganh);
		
		textField_ProgramName = new JTextField();
		textField_ProgramName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_ProgramName.setColumns(10);
		textField_ProgramName.setBounds(144, 461, 232, 35);
		contentPane.add(textField_ProgramName);
		
		JLabel lable_MaTruong = new JLabel("Mã Trường");
		lable_MaTruong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lable_MaTruong.setBounds(20, 513, 95, 34);
		contentPane.add(lable_MaTruong);
		
		JLabel lable_TenTruong_1_1 = new JLabel("Tên Trường");
		lable_TenTruong_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lable_TenTruong_1_1.setBounds(20, 557, 95, 34);
		contentPane.add(lable_TenTruong_1_1);
		
		textField_UniversityCode = new JTextField();
		textField_UniversityCode.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_UniversityCode.setColumns(10);
		textField_UniversityCode.setBounds(144, 506, 232, 35);
		contentPane.add(textField_UniversityCode);
		
		JLabel lable_DiemChuan = new JLabel("Điểm chuẩn");
		lable_DiemChuan.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lable_DiemChuan.setBounds(481, 416, 95, 34);
		contentPane.add(lable_DiemChuan);
		
		textField_AdmissionScore = new JTextField();
		textField_AdmissionScore.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_AdmissionScore.setColumns(10);
		textField_AdmissionScore.setBounds(604, 415, 232, 35);
		contentPane.add(textField_AdmissionScore);
		
		JLabel lable_DiaChi = new JLabel("Địa Chỉ");
		lable_DiaChi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lable_DiaChi.setBounds(481, 463, 95, 34);
		contentPane.add(lable_DiaChi);
		
		JLabel lable_HocPhi = new JLabel("Học Phí");
		lable_HocPhi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lable_HocPhi.setBounds(481, 510, 95, 34);
		contentPane.add(lable_HocPhi);
		
		textField_Address = new JTextField();
		textField_Address.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_Address.setColumns(10);
		textField_Address.setBounds(604, 461, 232, 35);
		contentPane.add(textField_Address);
		
		textField_TuitionFee = new JTextField();
		textField_TuitionFee.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_TuitionFee.setColumns(10);
		textField_TuitionFee.setBounds(604, 506, 232, 35);
		contentPane.add(textField_TuitionFee);
		
		JButton button_Them = new JButton("Thêm");
		button_Them.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button_Them.setBounds(23, 603, 118, 35);
		contentPane.add(button_Them);
		
		JButton button_Xoa = new JButton("Xóa");
		button_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button_Xoa.setBounds(219, 603, 118, 35);
		contentPane.add(button_Xoa);
		
		JButton button_CapNhat = new JButton("Cập Nhật");
		button_CapNhat.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button_CapNhat.setBounds(408, 603, 118, 35);
		contentPane.add(button_CapNhat);
		
		JButton button_HuyBo = new JButton("Hủy Bỏ");
		button_HuyBo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button_HuyBo.setBounds(812, 603, 118, 35);
		contentPane.add(button_HuyBo);
		
		JButton button_SapXepTheoDiem = new JButton("Sắp Xếp Theo Điểm");
		
		button_SapXepTheoDiem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button_SapXepTheoDiem.setBounds(306, 121, 232, 35);
		contentPane.add(button_SapXepTheoDiem);
		
		JButton button_SapXepTheoHocPhi = new JButton("Sắp Xếp Theo Học Phí");
		
		button_SapXepTheoHocPhi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button_SapXepTheoHocPhi.setBounds(646, 121, 239, 35);
		contentPane.add(button_SapXepTheoHocPhi);
		
		JButton button_Luu = new JButton("Lưu");
	
		button_Luu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button_Luu.setBounds(617, 603, 118, 35);
		contentPane.add(button_Luu);
		
		textField_UniversityName = new JTextField();
		textField_UniversityName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_UniversityName.setColumns(10);
		textField_UniversityName.setBounds(144, 557, 232, 35);
		contentPane.add(textField_UniversityName);
		
		JButton button_HuyTimKiem = new JButton("Quay về danh sách");
		
		button_HuyTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button_HuyTimKiem.setBounds(791, 57, 184, 35);
		contentPane.add(button_HuyTimKiem);
		
		
		contr = new ControllerView(this);
		Action action = new SMController(contr);
		
		menuOpen.addActionListener(action);
		menuSave.addActionListener(action);
		menuExit.addActionListener(action);
		menuAboutMe.addActionListener(action);
		button_TimKiem.addActionListener(action);
		button_Them.addActionListener(action);
		button_Xoa.addActionListener(action);
		button_CapNhat.addActionListener(action);
		button_HuyBo.addActionListener(action);
		button_SapXepTheoDiem.addActionListener(action);
		button_SapXepTheoHocPhi.addActionListener(action);
		button_Luu.addActionListener(action);
		button_HuyTimKiem.addActionListener(action);
		button_HuyTimKiem.addActionListener(action);
	}
}