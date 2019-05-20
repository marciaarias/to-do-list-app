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
		
		JLabel lblHelp = new JLabel("How to use the To-do List app:");
		lblHelp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHelp.setBounds(10, 22, 424, 20);
		contentPane.add(lblHelp);
		
		JLabel lblHelp2 = new JLabel("\u00B0 \"Load Tasks\" button: display all previously added tasks.");
		lblHelp2.setBounds(10, 66, 424, 14);
		contentPane.add(lblHelp2);
		
		JLabel lblHelp6 = new JLabel("\u00B0 \"Task\" field: type a new task to the to-do list.");
		lblHelp6.setBounds(10, 140, 424, 14);
		contentPane.add(lblHelp6);
		
		JLabel lblHelp7 = new JLabel("\u00B0 \"Task Details\" field: type an optional description or details about the task.");
		lblHelp7.setBounds(10, 165, 424, 14);
		contentPane.add(lblHelp7);
		
		JLabel lblHelp8 = new JLabel("\u00B0 \"Completed\" field: check whether the task is or is not been completed.");
		lblHelp8.setBounds(10, 190, 424, 14);
		contentPane.add(lblHelp8);
		
		JLabel lblHelp4 = new JLabel("\u00B0 \"Manage Task\" section: update and delete previously selected tasks,");
		lblHelp4.setBounds(10, 103, 424, 14);
		contentPane.add(lblHelp4);
		
		JLabel lblHelp3 = new JLabel("Select a task to fill the \"Manage Task\" section in order to manage it.");
		lblHelp3.setBounds(10, 78, 424, 14);
		contentPane.add(lblHelp3);
		
		JLabel lblHelp5 = new JLabel("or add new ones. Use the \"Clear\" button to clear all fields.");
		lblHelp5.setBounds(10, 115, 424, 14);
		contentPane.add(lblHelp5);
		
		//Implement button 'Close'.
		
		JButton btnClose = new JButton("Close");
		btnClose.setToolTipText("Close Help window");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				dispose();
				
			}
		});
		btnClose.setBounds(187, 230, 89, 31);
		contentPane.add(btnClose);
	}
}
