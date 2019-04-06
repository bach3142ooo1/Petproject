package Swing;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Comparator;
class EmailFormatException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1245599292951405931L;
	private final String message;
	public EmailFormatException(String message) {
		this.message=message;
	}
	public String getMessage() {
		return this.message;
	}
}
public class Student {
	private static JTable table;
	private static JTextField Name;
	private static JTextField ImmaNr;
	private static JTextField Email;
	public static void CheckFormatEmail(String email) {
		int count1=0;//count @
		int count2=0;//count .
		for(int i=0;i<email.length()-1;i++) {
			if(email.charAt(i)=='@') {
				count1++;
			}else if(email.charAt(i)=='@') {
				count2++;
			}
		}
		if(count1!=1 && count2<1 ) {
			throw new EmailFormatException("Keine richtige Form von Email");
		}
	}
	public static void main(String[] args) {
		JFrame frame= new JFrame("Student Management");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(921, 576);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, -264, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "ImmaNr", "Email"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(56);
		table.getColumnModel().getColumn(2).setPreferredWidth(94);
		scrollPane.setViewportView(table);
		
		JLabel lblName = new JLabel("Name");
		springLayout.putConstraint(SpringLayout.EAST, lblName, -646, SpringLayout.EAST, frame.getContentPane());
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 44, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblName, 22, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblName, -452, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(lblName);
		
		JLabel lblImmanr = new JLabel("ImmaNr");
		springLayout.putConstraint(SpringLayout.NORTH, lblImmanr, 26, SpringLayout.SOUTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblImmanr, 22, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblImmanr, 0, SpringLayout.EAST, lblName);
		lblImmanr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblImmanr);
		
		JLabel lblEmail = new JLabel("Email");
		springLayout.putConstraint(SpringLayout.SOUTH, lblImmanr, -32, SpringLayout.NORTH, lblEmail);
		springLayout.putConstraint(SpringLayout.EAST, lblEmail, 0, SpringLayout.EAST, lblName);
		springLayout.putConstraint(SpringLayout.NORTH, lblEmail, 165, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblEmail, 22, SpringLayout.WEST, frame.getContentPane());
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		springLayout.putConstraint(SpringLayout.SOUTH, lblEmail, -70, SpringLayout.NORTH, scrollPane);
		frame.getContentPane().add(lblEmail);
		
		Name = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, Name, 97, SpringLayout.EAST, lblName);
		springLayout.putConstraint(SpringLayout.EAST, Name, 542, SpringLayout.EAST, lblName);
		Name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		springLayout.putConstraint(SpringLayout.NORTH, Name, 39, SpringLayout.NORTH, frame.getContentPane());
		frame.getContentPane().add(Name);
		Name.setColumns(10);
		
		ImmaNr = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, ImmaNr, 38, SpringLayout.SOUTH, Name);
		springLayout.putConstraint(SpringLayout.WEST, ImmaNr, 0, SpringLayout.WEST, Name);
		springLayout.putConstraint(SpringLayout.EAST, ImmaNr, 0, SpringLayout.EAST, Name);
		ImmaNr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(ImmaNr);
		ImmaNr.setColumns(10);
		
		Email = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, Email, 36, SpringLayout.SOUTH, ImmaNr);
		springLayout.putConstraint(SpringLayout.WEST, Email, 0, SpringLayout.WEST, Name);
		springLayout.putConstraint(SpringLayout.EAST, Email, 0, SpringLayout.EAST, Name);
		Email.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(Email);
		
		Email.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 19, SpringLayout.SOUTH, lblEmail);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 22, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -739, SpringLayout.EAST, frame.getContentPane());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				try {
					String ImmaContent=ImmaNr.getText();
					Integer.parseInt(ImmaContent);
					String EmailContent=Email.getText();
					CheckFormatEmail(EmailContent);
					model.addRow(new String[] {
						Name.getText(),
						ImmaContent,
						EmailContent,
				});
					}catch(NumberFormatException k){
						JOptionPane.showMessageDialog(frame, "Muss Zahlen sein");
					}catch(EmailFormatException m) {
						JOptionPane.showMessageDialog(frame, m.getMessage());
					}
				Name.setText("");
				ImmaNr.setText("");
				Email.setText("");
			}});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 0, SpringLayout.NORTH, btnNewButton);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRow= table.getSelectedRow();
				model.removeRow(selectedRow);
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, -24, SpringLayout.WEST, btnNewButton_2);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_2, -159, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_2, -22, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_2, -10, SpringLayout.EAST, frame.getContentPane());
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for(int i=model.getRowCount()-1;i>=0;i--) {
				model.removeRow(i);
			}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Sort by Name");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 219, SpringLayout.EAST, btnNewButton_3);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_3, -555, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_3, -1, SpringLayout.NORTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_3, 42, SpringLayout.EAST, btnNewButton);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_3, -22, SpringLayout.NORTH, scrollPane);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String[][] arrtemp= new String[model.getRowCount()][model.getColumnCount()];
				for(int m=0;m<model.getColumnCount();m++) {
					for(int n=0; n<model.getRowCount();n++ ) {
						arrtemp[n][m]= (String) model.getValueAt(n, m);				}
				}
				Arrays.sort(arrtemp,new Comparator<String[]>() {
					public int compare(String[] a,String[] b ) {
						return a[0].compareTo(b[0]);
					}
				});
				for(int c=0; c<model.getColumnCount();c++) {
					for(int r=0;r<model.getRowCount();r++) {
						model.setValueAt(arrtemp[r][c], r, c);
					}
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Sort by ImmaNr");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				String[][] arrtemp= new String[model.getRowCount()][model.getColumnCount()];
				for(int m=0;m<model.getColumnCount();m++) {
					for(int n=0; n<model.getRowCount();n++ ) {
						arrtemp[n][m]= (String) model.getValueAt(n, m);				}
				}
				Arrays.sort(arrtemp,new Comparator<String[]>() {
					public int compare(String[] a,String[] b ) {
						return a[1].compareTo(b[1]);
					}
				});
				for(int c=0; c<model.getColumnCount();c++) {
					for(int r=0;r<model.getRowCount();r++) {
						model.setValueAt(arrtemp[r][c], r, c);
					}
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_4, 0, SpringLayout.NORTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_4, 37, SpringLayout.EAST, btnNewButton_3);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_4, 29, SpringLayout.NORTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_4, -29, SpringLayout.WEST, btnNewButton_1);
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnNewButton_4);
		frame.setVisible(true);
		
	}
	}
