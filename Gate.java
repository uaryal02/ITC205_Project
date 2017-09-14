package bcccp.carpark;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

@SuppressWarnings("serial")
public class Gate extends JFrame implements IGate {

	private JPanel contentPane_;
	private JTextField gateStatusTextField_;
	private boolean raised_;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Gate frame = new Gate(100, 100);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			Thread.sleep(2000);
			frame.raise();
			Thread.sleep(2000);
			frame.lower();			
		}
		catch (InterruptedException e) {}
	}

	/**
	 * Create the frame.
	 */
	public Gate(int x, int y) {
		setTitle("Gate");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, 310, 116);
		contentPane_ = new JPanel();
		contentPane_.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_);
		contentPane_.setLayout(null);
		
		gateStatusTextField_ = new JTextField();
		gateStatusTextField_.setBackground(Color.RED);
		gateStatusTextField_.setEditable(false);
		gateStatusTextField_.setHorizontalAlignment(SwingConstants.CENTER);
		gateStatusTextField_.setFont(new Font("Tahoma", Font.PLAIN, 24));
		gateStatusTextField_.setText("Gate Down");
		gateStatusTextField_.setBounds(5, 5, 279, 64);
		contentPane_.add(gateStatusTextField_);
		gateStatusTextField_.setColumns(10);
	}

	
	
	@Override
	public void raise() {
		gateStatusTextField_.setBackground(Color.GREEN);
		gateStatusTextField_.setText("Gate Up");
		raised_ = true;		
	}

	
	
	@Override
	public void lower() {
		gateStatusTextField_.setBackground(Color.RED);
		gateStatusTextField_.setText("Gate Down");
		raised_ = false;		
	}

	
	
	@Override
	public boolean isRaised() {
		return raised_;
	}

	
}
