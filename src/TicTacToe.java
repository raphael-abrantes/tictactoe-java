import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class TicTacToe implements ActionListener{
	
	ImageIcon image; 
	Random random = new Random();
	JFrame frame = new JFrame();
	
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("Options");
	KeyStroke f5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
	JMenuItem resetGame = new JMenuItem("Reset Game ");
	JMenuItem exitGame = new JMenuItem("Exit Game");
	
	JPanel titlePanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9];
	
	boolean p1Turn;
	boolean isThereAWinner = false;
	int drawCounter = 0;
	//boolean player2_turn; #Not necessary, since two-player only game
	
	TicTacToe() {
		
		image = new ImageIcon("hashtag.png");
		frame.setIconImage(image.getImage());
		frame.setTitle("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 450);
		frame.getContentPane().setBackground(new Color(0x000000));
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
				
		resetGame.setAccelerator(f5);
		resetGame.addActionListener(this);
		exitGame.addActionListener(this);
		
		textfield.setBackground(new Color(50, 50, 50));
		textfield.setForeground(new Color(0x00FF00));
		textfield.setFont(new Font("Cooper Black",Font.PLAIN,30));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Loading...");
		textfield.setOpaque(true);
		
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0, 0, 400, 100);
		
		buttonPanel.setLayout(new GridLayout(3, 3, 7, 7));
		buttonPanel.setBackground(new Color(0, 0, 0));
		
		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			buttonPanel.add(buttons[i]);
			buttons[i].setBackground(new Color(50, 50, 50));
			buttons[i].setBorderPainted(false);;
			buttons[i].setFont(new Font("Arial",Font.BOLD,40));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		fileMenu.add(resetGame);
		fileMenu.add(exitGame);
		menuBar.add(fileMenu);
		
		titlePanel.add(textfield);		
				
		frame.add(titlePanel,BorderLayout.NORTH);
		frame.add(buttonPanel);
		frame.setJMenuBar(menuBar);
		
		firstTurn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 9; i++) {
			if (e.getSource() == buttons[i]) {
				if (p1Turn) {
					if (buttons[i].getText() == "") {
						buttons[i].setForeground(new Color(255, 255, 255));
						buttons[i].setText("X");
						p1Turn = false;
						textfield.setText("O Turn");
						drawCounter++;
						check();
					}
				} else {
					if (buttons[i].getText() == "") {
						buttons[i].setForeground(new Color(255, 255, 255));
						buttons[i].setText("O");
						p1Turn = true;
						textfield.setText("X Turn");
						drawCounter++;
						check();
					}
				}
			}
		}
		
		if (e.getSource() == resetGame) {
			reset();
		}
		
		if (e.getSource() == exitGame) {
			System.exit(0);
		}
		
	}

	public void firstTurn() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (random.nextInt(2) == 0) {
			p1Turn = true;
			textfield.setText("X turn");
		} else {
			p1Turn = false;
			textfield.setText("O turn");
		}
	}
	
	public void check() {
		if (
				(buttons[0].getText() == "X") &&
				(buttons[1].getText() == "X") &&
				(buttons[2].getText() == "X")
				) {
			xWins(0, 1, 2);
		}
		if (
				(buttons[3].getText() == "X") &&
				(buttons[4].getText() == "X") &&
				(buttons[5].getText() == "X")
				) {
			xWins(3, 4, 5);
		}
		if (
				(buttons[6].getText() == "X") &&
				(buttons[7].getText() == "X") &&
				(buttons[8].getText() == "X")
				) {
			xWins(6, 7, 8);
		}
		if (
				(buttons[0].getText() == "X") &&
				(buttons[3].getText() == "X") &&
				(buttons[6].getText() == "X")
				) {
			xWins(0, 3, 6);
		}
		if (
				(buttons[1].getText() == "X") &&
				(buttons[4].getText() == "X") &&
				(buttons[7].getText() == "X")
				) {
			xWins(1, 4, 7);
		}	
		if (
				(buttons[2].getText() == "X") &&
				(buttons[5].getText() == "X") &&
				(buttons[8].getText() == "X")
				) {
			xWins(2, 5, 8);
		}	
		if (
				(buttons[0].getText() == "X") &&
				(buttons[4].getText() == "X") &&
				(buttons[8].getText() == "X")
				) {
			xWins(0, 4, 8);
		}
		if (
				(buttons[2].getText() == "X") &&
				(buttons[4].getText() == "X") &&
				(buttons[6].getText() == "X")
				) {
			xWins(2, 4, 6);
		}
	
		//----------------------------------------------------
		
		if (
				(buttons[0].getText() == "O") &&
				(buttons[1].getText() == "O") &&
				(buttons[2].getText() == "O")
				) {
			oWins(0, 1, 2);
		}
		if (
				(buttons[3].getText() == "O") &&
				(buttons[4].getText() == "O") &&
				(buttons[5].getText() == "O")
				) {
			oWins(3, 4, 5);
		}
		if (
				(buttons[6].getText() == "O") &&
				(buttons[7].getText() == "O") &&
				(buttons[8].getText() == "O")
				) {
			oWins(6, 7, 8);
		}
		if (
				(buttons[0].getText() == "O") &&
				(buttons[3].getText() == "O") &&
				(buttons[6].getText() == "O")
				) {
			oWins(0, 3, 6);
		}
		if (
				(buttons[1].getText() == "O") &&
				(buttons[4].getText() == "O") &&
				(buttons[7].getText() == "O")
				) {
			oWins(1, 4, 7);
		}	
		if (
				(buttons[2].getText() == "O") &&
				(buttons[5].getText() == "O") &&
				(buttons[8].getText() == "O")
				) {
			oWins(2, 5, 8);
		}	
		if (
				(buttons[0].getText() == "O") &&
				(buttons[4].getText() == "O") &&
				(buttons[8].getText() == "O")
				) {
			oWins(0, 4, 8);
		}
		if (
				(buttons[2].getText() == "O") &&
				(buttons[4].getText() == "O") &&
				(buttons[6].getText() == "O")
				) {
			oWins(2, 4, 6);
		}
		
		//----------------------------------------------------
		
		if (drawCounter == 9 && !isThereAWinner) {
			draw();
		}
	}
	
	public void xWins(int a, int b, int c) {
		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
			buttons[i].setBackground(new Color(25, 25, 25));	
		}

		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);	
				
		textfield.setBackground(new Color(25, 25, 25));
		isThereAWinner = true;
		textfield.setText("X wins!!");
	}
	
	public void oWins(int a, int b, int c) {

		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
			buttons[i].setBackground(new Color(25, 25, 25));	
		}
		
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);	
		
		textfield.setBackground(new Color(25, 25, 25));
		isThereAWinner = true;
		textfield.setText("O wins!!");
	}	
	
	public void draw () {
		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
			buttons[i].setBackground(new Color(25, 25, 25));	
		}
		
		textfield.setBackground(new Color(25, 25, 25));
		textfield.setText("DRAW");	
	}
	
	public void reset () {
		for (int i = 0; i < 9; i++) {
			buttons[i].setText("");
			buttons[i].setEnabled(true);
			buttons[i].setBackground(new Color(50, 50, 50));	
		}
		
		textfield.setBackground(new Color(50, 50, 50));
		textfield.setText("Loading...");
		
		drawCounter = 0;
		isThereAWinner = false;
		firstTurn();
	}
}
