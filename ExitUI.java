package bcccp.carpark.exit;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class ExitUI extends JFrame implements IExitUI {

	private JPanel contentPanel_;
	private JTextField displayTextField_;
	private JTextField ticketReaderTextField_;
	private IExitController controller_;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExitUI frame = new ExitUI(100, 100);
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
	public ExitUI(int x, int y) {
		setTitle("Exit Pillar UI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, 340, 380);
		contentPanel_ = new JPanel();
		contentPanel_.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel_);
		contentPanel_.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "LCD Display", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 316, 106);
		contentPanel_.add(panel);
		panel.setLayout(null);
		
		displayTextField_ = new JTextField();
		displayTextField_.setHorizontalAlignment(SwingConstants.CENTER);
		//displayTextField.setText("Push Button");
		displayTextField_.setFont(new Font("Tahoma", Font.PLAIN, 28));
		displayTextField_.setEditable(false);
		displayTextField_.setBounds(10, 15, 296, 82);
		panel.add(displayTextField_);
		displayTextField_.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ticket Reader", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(15, 115, 306, 153);
		contentPanel_.add(panel_1);
		panel_1.setLayout(null);
		
		ticketReaderTextField_ = new JTextField();
		ticketReaderTextField_.setHorizontalAlignment(SwingConstants.CENTER);
		ticketReaderTextField_.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ticketReaderTextField_.setBounds(10, 21, 285, 53);
		panel_1.add(ticketReaderTextField_);
		ticketReaderTextField_.setColumns(10);
		
		JButton btnNewButton = new JButton("Read Ticket");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readTicket();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton.setBounds(10, 85, 285, 45);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Take Ticket");
		btnNewButton_1.setBounds(25, 279, 285, 45);
		contentPanel_.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeTicket();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
	}

	
	
	@Override
	public void registerController(IExitController controller) {
		this.controller = controller;
	}

	
	
	@Override
	public void deregisterController() {
		this.controller = null;	
	}
	
	
	
	@Override
	public void display(String message) {
		displayTextField_.setText(message);	
	}

	
	
	@Override
	public void beep() {
		Toolkit.getDefaultToolkit().beep();	
	}

	
	
	private void readTicket() {
		String ticketStr = ticketReaderTextField_.getText();
		controller.ticketInserted(ticketStr);	
	}
	
	
	
	private void takeTicket() {
		controller.ticketTaken();
		ticketReaderTextField_.setText("");
	}

	
	
	@SuppressWarnings("unused")
	private void log(String message) {
		System.out.println("EntryUI : " + message);
	}

	
	
	
	@Override
	public void discardTicket() {
		ticketReaderTextField_.setText("");
	}

	
}
