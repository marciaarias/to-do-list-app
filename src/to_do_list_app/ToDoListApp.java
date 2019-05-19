package to_do_list_app;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.proteanit.sql.DbUtils;

public class ToDoListApp {

	private JFrame frmTodoList;
	private JTable table;
	private JTextField textTask;
	private JTextField textTaskDetails;
	private JCheckBox chckbxCompleted;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToDoListApp window = new ToDoListApp();
					window.frmTodoList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ToDoListApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTodoList = new JFrame();
		frmTodoList.setResizable(false);
		frmTodoList.setTitle("To-do List");
		frmTodoList.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\arias\\eclipse-workspace\\to-do-list-app\\resources\\to-do-list.png"));
		frmTodoList.setBounds(100, 100, 549, 544);
		frmTodoList.setLocationRelativeTo(null);
		frmTodoList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTodoList.getContentPane().setLayout(null);
		
		//Implement button 'Load Tasks'.
		JButton btnLoadTasks = new JButton("Load Tasks");
		btnLoadTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DataModule dm = new DataModule();
				
				try {
					Connection connection = dm.getConnection();
					
					String query = "SELECT id, completed, task, task_details FROM tasks";
					PreparedStatement statement = connection.prepareStatement(query);
					ResultSet rs = statement.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e2) {
					  e2.printStackTrace();
				}
				
			}
		});
		btnLoadTasks.setBounds(10, 10, 110, 31);
		frmTodoList.getContentPane().add(btnLoadTasks);
		
		//Implement button 'Add'.
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textTask.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "\"Task\" field cannot be empty.", "Error", JOptionPane.WARNING_MESSAGE);
					
				} else {
					DataModule dm = new DataModule();
					
					try {
						Connection connection = dm.getConnection();
						
						String chckbxSelected = "N";
						if(chckbxCompleted.isSelected() == true) {
							chckbxSelected = "Y";
						}
						
						String taskDetails = "";
						if(textTaskDetails.getText().isEmpty()){
							taskDetails = "NULL";
						} else {
							taskDetails = "'"+ textTaskDetails.getText() +"'";
						}
						
						String query = "INSERT INTO tasks (completed, task, task_details) "
										+ "VALUES('" + chckbxSelected + "', '" + textTask.getText() + "', " + taskDetails + ")";
						PreparedStatement statement = connection.prepareStatement(query);
					    statement.executeUpdate(query);
						ResultSet rs = statement.executeQuery("SELECT id, completed, task, task_details FROM tasks");
						
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
						textTask.setText("");
						textTaskDetails.setText("");
						chckbxCompleted.setSelected(false);
						
					} catch (Exception e2) {
						  e2.printStackTrace();
					}
				}
				
			}
		});
		btnAdd.setBounds(413, 390, 87, 23);
		frmTodoList.getContentPane().add(btnAdd);
		
		//Implement button 'Update'.
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRowIndex = table.getSelectedRow();
				
				if(table.isRowSelected(selectedRowIndex) == true){
				    int clickedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to modify this task?", "Confirm Update", JOptionPane.YES_NO_OPTION);
				    
				    if(clickedOption == JOptionPane.YES_OPTION){	
						DataModule dm = new DataModule();
						DefaultTableModel model = (DefaultTableModel)table.getModel();
					
						try {
							Connection connection = dm.getConnection();
							
							String chckbxSelected = "N";
							if(chckbxCompleted.isSelected() == true) {
								chckbxSelected = "Y";
							}
							
							String query = "UPDATE tasks SET completed = '" + chckbxSelected 
											+ "' , task = '" + textTask.getText() 
											+ "' , task_details = '" + textTaskDetails.getText() 
											+ "' WHERE id = " + (int)(model.getValueAt(selectedRowIndex, 0));
						
							PreparedStatement statement = connection.prepareStatement(query);
						    statement.executeUpdate(query);
							ResultSet rs = statement.executeQuery("SELECT id, completed, task, task_details FROM tasks");
							
							table.setModel(DbUtils.resultSetToTableModel(rs));
							
							textTask.setText("");
							textTaskDetails.setText("");
							chckbxCompleted.setSelected(false);
						
						} catch (Exception e2) {
							e2.printStackTrace();
						}
				    }
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a task first.");
				}
				
			}
		});
		btnUpdate.setBounds(316, 390, 87, 23);
		frmTodoList.getContentPane().add(btnUpdate);
		
		//Implement button 'Delete'.
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRowIndex = table.getSelectedRow();
				
				if(table.isRowSelected(selectedRowIndex) == true){
				    int clickedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this task?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
				    
				    if(clickedOption == JOptionPane.YES_OPTION){	
						DataModule dm = new DataModule();
						DefaultTableModel model = (DefaultTableModel)table.getModel();
					
						try {
							Connection connection = dm.getConnection();
						
							String query = "DELETE FROM tasks WHERE id = " + (int)(model.getValueAt(selectedRowIndex, 0));
						
							PreparedStatement statement = connection.prepareStatement(query);
						    statement.executeUpdate(query);
							ResultSet rs = statement.executeQuery("SELECT id, completed, task, task_details FROM tasks");
							
							table.setModel(DbUtils.resultSetToTableModel(rs));
						
						} catch (Exception e2) {
							e2.printStackTrace();
						}
				    }
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a task first.");
				}
				
			}
		});
		btnDelete.setBounds(219, 390, 87, 23);
		frmTodoList.getContentPane().add(btnDelete);
		
		//Implement button 'Quit'.
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int clickedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Confirm Quit", JOptionPane.YES_NO_OPTION);
			    if(clickedOption == JOptionPane.YES_OPTION){
			    	frmTodoList.dispose();
			    }
				
			}
		});
		btnQuit.setBounds(432, 460, 89, 31);
		frmTodoList.getContentPane().add(btnQuit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 511, 182);
		frmTodoList.getContentPane().add(scrollPane);
		
		//Implement MouseClicked on 'table'.
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				
				textTask.setText(model.getValueAt(selectedRowIndex, 2).toString());
				
				if(model.getValueAt(selectedRowIndex, 3) == null) {
					textTaskDetails.setText("");
				} else {
					textTaskDetails.setText(model.getValueAt(selectedRowIndex, 3).toString());
				}
				
				if(model.getValueAt(selectedRowIndex, 1).toString().equals("N")) {
					chckbxCompleted.setSelected(false);
				} else {
					chckbxCompleted.setSelected(true);
				}
				
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 424, 511, 2);
		frmTodoList.getContentPane().add(separator);
		
		JLabel lblTask = new JLabel("Task:");
		lblTask.setBounds(61, 293, 36, 14);
		frmTodoList.getContentPane().add(lblTask);
		
		JLabel lblTaskDetails = new JLabel("Task Details:");
		lblTaskDetails.setBounds(20, 324, 77, 14);
		frmTodoList.getContentPane().add(lblTaskDetails);
		
		textTask = new JTextField();
		textTask.setBounds(104, 286, 396, 20);
		frmTodoList.getContentPane().add(textTask);
		textTask.setColumns(100);
		
		textTaskDetails = new JTextField();
		textTaskDetails.setBounds(104, 318, 396, 20);
		frmTodoList.getContentPane().add(textTaskDetails);
		textTaskDetails.setColumns(300);
		
		JButton btnHelp = new JButton("?");
		btnHelp.setBounds(479, 10, 42, 31);
		frmTodoList.getContentPane().add(btnHelp);
		
		JLabel lblManageTask = new JLabel("Manage Task:");
		lblManageTask.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblManageTask.setBounds(15, 248, 120, 23);
		frmTodoList.getContentPane().add(lblManageTask);
		
		JSeparator separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setBounds(10, 272, 12, 153);
		frmTodoList.getContentPane().add(separator2);
		
		JSeparator separator3 = new JSeparator();
		separator3.setOrientation(SwingConstants.VERTICAL);
		separator3.setBounds(520, 272, 12, 153);
		frmTodoList.getContentPane().add(separator3);
		
		JSeparator separator4 = new JSeparator();
		separator4.setBounds(10, 271, 511, 2);
		frmTodoList.getContentPane().add(separator4);
		
		//Implement button 'Clear'.
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textTask.setText("");
				textTaskDetails.setText("");
				chckbxCompleted.setSelected(false);
				
			}
		});
		btnClear.setBounds(30, 390, 87, 23);
		frmTodoList.getContentPane().add(btnClear);
		
		JLabel lblCompleted = new JLabel("Completed:");
		lblCompleted.setBounds(29, 356, 68, 14);
		frmTodoList.getContentPane().add(lblCompleted);
		
		chckbxCompleted = new JCheckBox("");
		chckbxCompleted.setBounds(100, 351, 28, 23);
		frmTodoList.getContentPane().add(chckbxCompleted);
	}
}
