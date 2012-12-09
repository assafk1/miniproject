
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;



public class MainWindow extends JFrame {

	private JPanel contentPane;
	private final String arr[] = {"1","2","3","4","5","6","7","8","9","10"};
	private JButton _btnManual, _btnRandom;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow.setDefaultLookAndFeelDecorated(true);
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		initUI();
		addActionListeners();
	}

	private void addActionListeners(){
		_btnManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String answer = (String) JOptionPane.showInputDialog(contentPane.getParent(), "Select number of agents:", "number of agents",  1, null, arr, "1");
				if (answer != null) {
					_btnManual.setEnabled(false);
					_btnRandom.setEnabled(false);
					int numOfAgents = Integer.parseInt(answer);
					initAgentButtons(numOfAgents);
					
				}
			}
		});
	}
	
	private void initUI()
	{
		setTitle("The Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("<HTML>Welcome to the DSCP Concurrent Backtracking Simulator!<BR> Please make your choice: </HTML>"));
		contentPane.add(panel);
		JPanel panel2 = new JPanel();
		_btnRandom = new JButton("Generate new random DCSP");
		_btnManual = new JButton("Generate new manual DCSP");
		panel2.add(_btnManual);
		panel2.add(_btnRandom);
		contentPane.add(panel2);
		contentPane.add(new JSeparator(JSeparator.HORIZONTAL));
	}
	
	private void initAgentButtons(int numOfAgents)
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Agents"));
		panel.setLayout(new GridLayout(5, 4, 5, 5));
		String[] buttons = new String[numOfAgents];
		for (int i = 0; i < numOfAgents; i++) {
			buttons[i] = "Customize agent #" + (i + 1);
			JButton btn = new JButton(buttons[i]);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//_domSel.setVisible(true);
				}
			});
			panel.add(btn);
		}
		add(panel);
		contentPane.add(new JSeparator(JSeparator.HORIZONTAL));
		contentPane.add(new JLabel("<HTML>Please define all agents before running simulation!</HTML>"));
		contentPane.add(new JSeparator(JSeparator.HORIZONTAL));
		contentPane.revalidate();
		contentPane.add(new JButton("GO!"));
	}
	
}


