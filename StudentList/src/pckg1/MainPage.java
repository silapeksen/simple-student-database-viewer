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
import java.sql.PreparedStatement;
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
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtFaculty;
	private JTextField txtDepartment;
	private JTextField txtGPA;

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
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 56, 69, 13);
		contentPane.add(lblFirstName);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(92, 53, 96, 19);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 79, 69, 13);
		contentPane.add(lblLastName);
		
		txtLastName = new JTextField();
		txtLastName.setBounds(92, 76, 96, 19);
		contentPane.add(txtLastName);
		txtLastName.setColumns(10);
		
		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setBounds(10, 102, 69, 13);
		contentPane.add(lblFaculty);
		
		txtFaculty = new JTextField();
		txtFaculty.setBounds(92, 99, 96, 19);
		contentPane.add(txtFaculty);
		txtFaculty.setColumns(10);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(10, 125, 69, 13);
		contentPane.add(lblDepartment);
		
		txtDepartment = new JTextField();
		txtDepartment.setBounds(92, 122, 96, 19);
		contentPane.add(txtDepartment);
		txtDepartment.setColumns(10);
		
		JLabel lblGPA = new JLabel("GPA");
		lblGPA.setBounds(10, 148, 69, 13);
		contentPane.add(lblGPA);
		
		txtGPA = new JTextField();
		txtGPA.setBounds(92, 145, 96, 19);
		contentPane.add(txtGPA);
		txtGPA.setColumns(10);
		
		JButton btnCreate = new JButton("CREATE");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentlist", "root", "S44l44pn_");
					
					Student stu = new Student(Integer.parseInt(txtStudentId.getText()), txtFirstName.getText(), txtLastName.getText(), txtFaculty.getText(), txtDepartment.getText(), new BigDecimal(txtGPA.getText()));
					String insertQuery = "INSERT INTO students VALUES(?,?,?,?,?,?)";
					PreparedStatement ps = con.prepareStatement(insertQuery);
					ps.setInt(1, stu.getId());// Assigning the question marks, (question mark index, value)
					ps.setString(2, stu.getFirst_name());
					ps.setString(3, stu.getLast_name());
					ps.setString(4, stu.getFaculty());
					ps.setString(5, stu.getDepartment());
					ps.setBigDecimal(6, stu.getGpa());
					ps.executeUpdate();					
					
					txtStudentId.setText("");// Clearing the menu.
					txtFirstName.setText("");
					txtLastName.setText("");
					txtFaculty.setText("");
					txtDepartment.setText("");
					txtGPA.setText("");
					
					JOptionPane.showMessageDialog(contentPane, "Student saved!");// Informing the user.
					
					con.close();// Closing the connection
					
				} catch (SQLException e1) {// Catches if there is an error.
					e1.printStackTrace();
				}
				
			}
		});
		btnCreate.setBounds(92, 208, 85, 21);
		contentPane.add(btnCreate);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();// For adding rows to the JTable.
				model.setRowCount(0);// Starting from the beginning.
				
				try {
					String input = txtStudentId.getText();
					if (input.isEmpty()) {
						return;// If it is null 
					}
					
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentlist", "root", "S44l44pn_");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM students WHERE id = " + input);
					
					if (rs.next()) {// If database table has value in them, next function returns 1.
						int id = rs.getInt("id");
						String first_name = rs.getString("first_name");
						String last_name = rs.getString("last_name");
						String faculty = rs.getString("faculty");
						String department = rs.getString("department");
						BigDecimal gpa = rs.getBigDecimal("gpa");
							
						model.addRow(new Object[] {id, first_name, last_name, faculty, department, gpa});// Adding rows to the JTable.
						
						txtStudentId.setText("");// Clearing the text field after input
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Student could not found!!!");// If the student could not found
					}
					
					con.close();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(92, 239, 85, 21);
		contentPane.add(btnSearch);
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentlist", "root", "S44l44pn_");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM students;");
					
					while(rs.next()) {// In order to visit all the rows, we need a while loop
						int id = rs.getInt("id");
						String first_name = rs.getString("first_name");
						String last_name = rs.getString("last_name");
						String faculty = rs.getString("faculty");
						String department = rs.getString("department");
						BigDecimal gpa = rs.getBigDecimal("gpa");
						
						model.addRow(new Object[] {id, first_name, last_name, faculty, department, gpa});
					}
					
					con.close();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPrint.setBounds(92, 270, 85, 21);
		contentPane.add(btnPrint);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(txtStudentId.getText());
				
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentlist", "root", "S44l44pn_");					
					
					if (txtFirstName.getText().isEmpty() != true) {
						String updateQuery = "UPDATE students SET first_name =? WHERE id =?";
						PreparedStatement ps = con.prepareStatement(updateQuery);
						ps.setString(1, txtFirstName.getText());
						ps.setInt(2, id);
						ps.executeUpdate();
					}
					if (txtLastName.getText().isEmpty() != true) {
						String updateQuery = "UPDATE students SET last_name =? WHERE id =?";
						PreparedStatement ps = con.prepareStatement(updateQuery);
						ps.setString(1, txtLastName.getText());
						ps.setInt(2, id);
						ps.executeUpdate();
					}
					if (txtFaculty.getText().isEmpty() != true) {
						String updateQuery = "UPDATE students SET faculty =? WHERE id =?";
						PreparedStatement ps = con.prepareStatement(updateQuery);
						ps.setString(1, txtFaculty.getText());
						ps.setInt(2, id);
						ps.executeUpdate();
					}
					if (txtDepartment.getText().isEmpty() != true) {
						String updateQuery = "UPDATE students SET department =? WHERE id =?";
						PreparedStatement ps = con.prepareStatement(updateQuery);
						ps.setString(1, txtDepartment.getText());
						ps.setInt(2, id);
						ps.executeUpdate();
					}
					if (txtGPA.getText().isEmpty() != true) {
						String updateQuery = "UPDATE students SET gpa =? WHERE id =?";
						PreparedStatement ps = con.prepareStatement(updateQuery);
						ps.setString(1, txtGPA.getText());
						ps.setInt(2, id);
						ps.executeUpdate();
					}
					
					txtStudentId.setText("");// Clearing the menu.
					txtFirstName.setText("");
					txtLastName.setText("");
					txtFaculty.setText("");
					txtDepartment.setText("");
					txtGPA.setText("");
					
					JOptionPane.showMessageDialog(contentPane, "Update has been saved!");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(92, 301, 85, 21);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentlist", "root", "S44l44pn_");
					
					String deleteQuery = "DELETE FROM students WHERE id =?";
					PreparedStatement ps = con.prepareStatement(deleteQuery);
					ps.setString(1, txtStudentId.getText());
					ps.executeUpdate();
					
					txtStudentId.setText("");
					
					JOptionPane.showMessageDialog(contentPane, "Student deleted!");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnDelete.setBounds(92, 332, 85, 21);
		contentPane.add(btnDelete);
		
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
