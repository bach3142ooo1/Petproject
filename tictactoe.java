package Swing;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

class MoveException extends RuntimeException{
	String message;
	public MoveException(String message) {
		this.message=message;
	}
}
public class tictactoe { 
	private static JTable table;
	
	public static void Movecheck(String[][] checkArr,int r,int c){
		if(checkArr[r][c].equals("X")) {
			throw new MoveException("Unvalid Move!");
		}else if(checkArr[r][c].equals("O")) {
			throw new MoveException("Unvalid Move!");
		}
	
	}
	public static boolean verticalCheck(String[][] arr) {
		int rowNumber= arr.length;
		int columnNumber= arr[0].length;
		boolean check=false;
		for(int c=0;c<columnNumber;c++) {
			for(int r=0;r<rowNumber-4;r++) {
				if(arr[r][c].equals("X") && arr[r+1][c].equals("X") && arr[r+2][c].equals("X") && arr[r+3][c].equals("X") && arr[r+4][c].equals("X")) {
					JOptionPane.showMessageDialog(new JFrame("Winner"), "Player X win ");
					check=true;
					break;
				}else if(arr[r][c].equals("O") && arr[r+1][c].equals("O") && arr[r+2][c].equals("O") && arr[r+3][c].equals("O") && arr[r+4][c].equals("O")) {
					JOptionPane.showMessageDialog(new JFrame("Winner"), "Player O win ");
					check= true;
					break;
				}
			}
		}
		return check;
	}
	
	public static boolean horizonCheck(String[][] arr) {
		int rowNumber= arr.length;
		int columnNumber= arr[0].length;
		boolean check=false;
		for(int r=0;r< rowNumber;r++) {
			for(int c=0;c<columnNumber-4;c++) {
				if(arr[r][c].equals("X") && arr[r][c+1].equals("X") && arr[r][c+2].equals("X") && arr[r][c+3].equals("X") && arr[r][c+4].equals("X")) {
					JOptionPane.showMessageDialog(new JFrame("Winner"), "Player X win ");
					check=true;
					break;
				}else if(arr[r][c].equals("O") && arr[r][c+1].equals("O") && arr[r][c+2].equals("O") && arr[r][c+3].equals("O") && arr[r][c+4].equals("O")) {
					JOptionPane.showMessageDialog(new JFrame("Winner"), "Player O win ");
					check=true;
					break;
				}
			}
		}
		return check;
	}
	
	public static boolean diagonalCheck1(String[][] arr) {
		int rowNumber= arr.length;
		int columnNumber= arr[0].length;
		boolean check=false;
		for(int r=0;r< rowNumber-4;r++) {
			for(int c=0;c<columnNumber-4;c++) {
				if(arr[r][c].equals("X") && arr[r+1][c+1].equals("X") && arr[r+2][c+2].equals("X") && arr[r+3][c+3].equals("X") && arr[r+4][c+4].equals("X")) {
					JOptionPane.showMessageDialog(new JFrame("Winner"), "Player X win ");
					check=true;
					break;
				}else if(arr[r][c].equals("O") && arr[r+1][c+1].equals("O") && arr[r+2][c+2].equals("O") && arr[r+3][c+3].equals("O") && arr[r+4][c+4].equals("O")) {
					JOptionPane.showMessageDialog(new JFrame("Winner"), "Player O win ");
					check=true;
					break;
				}
			}
		}
		return check;
	}
	
	public static boolean diagonalCheck2(String[][] arr) {
		int rowNumber= arr.length;
		int columnNumber= arr[0].length;
		boolean check=false;
		for(int r=rowNumber-1;r>4;r--) {
			for(int c=0;c<columnNumber-4;c++) {
				if(arr[r][c].equals("X") && arr[r-1][c+1].equals("X") && arr[r-2][c+2].equals("X") && arr[r-3][c+3].equals("X") && arr[r-4][c+4].equals("X")) {
					JOptionPane.showMessageDialog(new JFrame("Winner"), "Player X win ");
					check=true;
					break;
				}else if(arr[r][c].equals("O") && arr[r-1][c+1].equals("O") && arr[r-2][c+2].equals("O") && arr[r-3][c+3].equals("O") && arr[r-4][c+4].equals("O")) {
					JOptionPane.showMessageDialog(new JFrame("Winner"), "Player O win ");
					check=true;
					break;
				}
			}
		}
		return check;
	}
	
	public static void clearScreen(DefaultTableModel model, String[][] checkArr ) {
		for(int r=0;r<model.getRowCount();r++) {
			for(int c=0;c<model.getColumnCount();c++) {
				model.setValueAt("", r, c);
			}
		}
		for(int r=0;r<checkArr.length;r++) {
			for(int c=0;c<checkArr[0].length;c++) {
				checkArr[r][c]="";
			}
		}
	}
	
	public static void XMove(DefaultTableModel model, String[][] checkArr){
		int selectedColumn= table.getSelectedColumn();
		int selectedRow= table.getSelectedRow();
		Movecheck(checkArr, selectedRow, selectedColumn);
		checkArr[selectedRow][selectedColumn]="X";
		model.setValueAt("X", selectedRow,selectedColumn);
	}
	
	public static void OMove(DefaultTableModel model, String[][] checkArr){
		int selectedColumn= table.getSelectedColumn();
		int selectedRow= table.getSelectedRow();
		Movecheck(checkArr, selectedRow, selectedColumn);
		checkArr[selectedRow][selectedColumn]="O";
		model.setValueAt("O", selectedRow,selectedColumn);
	}
		
	public static void anotherRound(JFrame frame,DefaultTableModel model, String[][] checkArr, JRadioButton XTurn, JRadioButton OTurn) {
		if(horizonCheck(checkArr) | verticalCheck(checkArr) | diagonalCheck1(checkArr) | diagonalCheck2(checkArr)) {
			int newGameDecision = JOptionPane.showConfirmDialog(frame, "Do you want to play another round ", "Question", JOptionPane.YES_NO_OPTION);
			if(newGameDecision==JOptionPane.YES_OPTION) {
				Object[] XOoption= {"X","O"};
				int XOChose = JOptionPane.showOptionDialog(frame, "Which one start first X or O ?: ","Question",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,XOoption,null);
				JOptionPane.showMessageDialog(frame,("Let the game beginn !!"));
				table.setVisible(true);
				if(XOChose==0) {				
					XTurn.setSelected(true);
				} else {
					OTurn.setSelected(true);
				}
				clearScreen(model, checkArr);
			}
			else if (newGameDecision == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Tic-Tac-Toe");
		String[][] checkArr= new String[26][80];
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1038,553);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		table = new JTable();
		table.setBorder(new LineBorder(Color.BLACK, 2));
		springLayout.putConstraint(SpringLayout.SOUTH, table, 426, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, table, 998, SpringLayout.WEST, frame.getContentPane());
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		
		springLayout.putConstraint(SpringLayout.NORTH, table, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, table, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(table);
		table.setVisible(false);
		
		JRadioButton XTurn = new JRadioButton("X turn");
		springLayout.putConstraint(SpringLayout.NORTH, XTurn, 432, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, XTurn, 907, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, XTurn, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, XTurn, -7, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(XTurn);
		XTurn.setVisible(false);
		
		JRadioButton OTurn = new JRadioButton("O Turn");
		springLayout.putConstraint(SpringLayout.NORTH, OTurn, 442, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, OTurn, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, OTurn, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, OTurn, -909, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(OTurn);
		OTurn.setVisible(false);
		
		JButton startButton = new JButton("Start game");
		springLayout.putConstraint(SpringLayout.NORTH, startButton, 10, SpringLayout.NORTH, XTurn);
		springLayout.putConstraint(SpringLayout.WEST, startButton, 6, SpringLayout.EAST, OTurn);
		springLayout.putConstraint(SpringLayout.SOUTH, startButton, 0, SpringLayout.SOUTH, XTurn);
		springLayout.putConstraint(SpringLayout.EAST, startButton, -760, SpringLayout.EAST, frame.getContentPane());
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] option= {"X","O"};
				int XOChose = JOptionPane.showOptionDialog(frame, "Which one start first X or O ?: ","Question",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,option,null);
				JOptionPane.showMessageDialog(frame,("Let the game beginn !!"));
				table.setVisible(true);
				if(XOChose==0) {				
					XTurn.setSelected(true);
				} else {
					OTurn.setSelected(true);
				}
			};
		});
		frame.getContentPane().add(startButton);
		JButton resetButton = new JButton("Reset game");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				clearScreen(model,checkArr);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, resetButton, 0, SpringLayout.NORTH, OTurn);
		springLayout.putConstraint(SpringLayout.WEST, resetButton, 144, SpringLayout.EAST, startButton);
		springLayout.putConstraint(SpringLayout.SOUTH, resetButton, 0, SpringLayout.SOUTH, XTurn);
		frame.getContentPane().add(resetButton);
		
		for(int row=0; row<26;row++) {
			for(int column=0; column<80;column++) {
				checkArr[row][column]="";
			}
		}
		
		JButton btnNewButton = new JButton("End turn");
		springLayout.putConstraint(SpringLayout.EAST, resetButton, -122, SpringLayout.WEST, btnNewButton);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, 0, SpringLayout.SOUTH, XTurn);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 669, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -95, SpringLayout.WEST, XTurn);
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 10, SpringLayout.NORTH, XTurn);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				try {
					if(XTurn.isSelected()) {
						XMove(model,checkArr);
						OTurn.setSelected(true);
						XTurn.setSelected(false);
						anotherRound(frame, model, checkArr, XTurn, OTurn);
					}else if(OTurn.isSelected()) {
						OMove(model, checkArr);
						XTurn.setSelected(true);
						OTurn.setSelected(false);
						anotherRound(frame, model, checkArr, XTurn, OTurn);
					}
				}catch(MoveException ex) {
					JOptionPane.showMessageDialog(frame, ex.message);
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}
}
