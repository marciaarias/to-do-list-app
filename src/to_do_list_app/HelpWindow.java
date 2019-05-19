package to_do_list_app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelpWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpWindow frame = new HelpWindow();
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
	public HelpWindow() {
		setTitle("Help");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\arias\\eclipse-workspace\\to-do-list-app\\resources\\information.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 462, 317);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("How to use the To-do List app:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 22, 424, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u00B0 \"Load Tasks\" button: display all previously added tasks.");
		lblNewLabel_1.setBounds(10, 66, 424, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u00B0 \"Task\" field: type a new task to the to-do list.");
		lblNewLabel_2.setBounds(10, 140, 424, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u00B0 \"Task Details\" field: type an optional description or details about the task.");
		lblNewLabel_3.setBounds(10, 165, 424, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u00B0 \"Completed\" field: check whether the task is or is not been completed.");
		lblNewLabel_4.setBounds(10, 190, 424, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u00B0 \"Manage Task\" section: update and delete previously selected tasks,");
		lblNewLabel_5.setBounds(10, 103, 424, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Select a task to fill the \"Manage Task\" section in order to manage it.");
		lblNewLabel_6.setBounds(10, 78, 424, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("or add new ones. Use the \"Clear\" button to clear all fields.");
		lblNewLabel_7.setBounds(10, 115, 424, 14);
		contentPane.add(lblNewLabel_7);
		
		//Implement button 'Close'.
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				dispose();
				
			}
		});
		btnNewButton.setBounds(187, 230, 89, 31);
		contentPane.add(btnNewButton);
	}
}
