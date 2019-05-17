package to_do_list_app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class ToDoListApp {

	private JFrame frmTodoList;
	private JTable table;

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
		frmTodoList.setBounds(100, 100, 549, 344);
		frmTodoList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTodoList.getContentPane().setLayout(null);
		
		JButton btnLoadData = new JButton("Load Data");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DataModule dm = new DataModule();
				
				try {
					Connection connection = dm.getConnection();
					
					String query = "SELECT done, task, task_details FROM tasks";
					PreparedStatement statement = connection.prepareStatement(query);
					ResultSet rs = statement.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e2) {
					  e2.printStackTrace();
				}
				
			}
		});
		btnLoadData.setBounds(10, 11, 110, 31);
		frmTodoList.getContentPane().add(btnLoadData);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(287, 40, 70, 23);
		frmTodoList.getContentPane().add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(367, 40, 70, 23);
		frmTodoList.getContentPane().add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(447, 40, 70, 23);
		frmTodoList.getContentPane().add(btnDelete);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(432, 262, 89, 31);
		frmTodoList.getContentPane().add(btnSave);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(333, 262, 89, 31);
		frmTodoList.getContentPane().add(btnQuit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 511, 182);
		frmTodoList.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}
}
