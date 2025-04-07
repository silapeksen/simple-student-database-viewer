package pckg1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(10, 33, 69, 13);
		contentPane.add(lblStudentId);
		
		txtStudentId = new JTextField();
		txtStudentId.setBounds(92, 30, 96, 19);
		contentPane.add(txtStudentId);
		txtStudentId.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				
				try {
					String input = txtStudentId.getText();
					if (input.isEmpty()) {
						return;//If it is null 
					}
					
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentlist", "root", "S44l44pn_");
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM students WHERE id = " + input);
					
					if (resultSet.next()) {
						int id = resultSet.getInt("id");
						String first_name = resultSet.getString("first_name");
						String last_name = resultSet.getString("last_name");
						String faculty = resultSet.getString("faculty");
						String department = resultSet.getString("department");
						BigDecimal gpa = resultSet.getBigDecimal("gpa");
							
						model.addRow(new Object[] {id, first_name, last_name, faculty, department, gpa});
						
						//Clearing the text field after input
						txtStudentId.setText("");
					}
					else {
						//If the student could not found
						JOptionPane.showMessageDialog(contentPane, "Student could not found!!!");
					}
					
					//Closing the connection
					connection.close();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(92, 69, 85, 21);
		contentPane.add(btnSearch);
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				
				try {
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentlist", "root", "S44l44pn_");
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM students;");
					
					//In order to visit all the rows, we need a while loop
					while(resultSet.next()) {
						int id = resultSet.getInt("id");
						String first_name = resultSet.getString("first_name");
						String last_name = resultSet.getString("last_name");
						String faculty = resultSet.getString("faculty");
						String department = resultSet.getString("department");
						BigDecimal gpa = resultSet.getBigDecimal("gpa");
						
						model.addRow(new Object[] {id, first_name, last_name, faculty, department, gpa});
					}
					
					connection.close();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPrint.setBounds(92, 100, 85, 21);
		contentPane.add(btnPrint);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(221, 32, 486, 372);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "NAME", "SURNAME", "FACULTY", "DEPARTMENT", "GPA"
			}
		));
	}
}
