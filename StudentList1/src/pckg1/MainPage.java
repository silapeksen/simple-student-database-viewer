package pckg1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtStudentId;
	private JTable table;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage() {
		setTitle("Student List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentId = new JLabel("Stu Id");
		lblStudentId.setBounds(26, 54, 45, 13);
		contentPane.add(lblStudentId);
		
		txtStudentId = new JTextField();
		txtStudentId.setBounds(64, 51, 96, 19);
		contentPane.add(txtStudentId);
		txtStudentId.setColumns(10);
		
		JLabel lblSearchHere = new JLabel("SEARCH HERE!");
		lblSearchHere.setBounds(26, 10, 154, 34);
		contentPane.add(lblSearchHere);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(64, 77, 85, 21);
		contentPane.add(btnSearch);
		
		JButton btnPrintAll = new JButton("PRINT ALL");
		btnPrintAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MySQLConnection db = new MySQLConnection();
				table.setModel(DbUtils.resultSetToTableModel(db.getResultSet()));
			}
		});
		btnPrintAll.setBounds(64, 108, 85, 21);
		contentPane.add(btnPrintAll);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(219, 22, 609, 497);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Surname", "Faculty", "Department", "GPA"
			}
		));
	}
}
