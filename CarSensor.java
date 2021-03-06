package bcccp.carpark;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CarSensor extends JFrame implements ICarSensor {

	private JPanel contentPane_;
	private boolean carDetected_;
	private String detectorId_;
	
	private List<ICarSensorResponder> responders_;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarSensor frame = new CarSensor("Car Detector", 100, 100);
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
	public CarSensor(String detectorId, int x, int y) {
		detectorId_ = detectorId;
		responders_ = new ArrayList<>();
		setTitle(detectorId);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, 306, 223);
		contentPane_ = new JPanel();
		contentPane_.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_);
		contentPane_.setLayout(null);
		
		carDetected_ = false;
		JButton detectorButton = new JButton();
		detectorButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		detectorButton.setBounds(28, 24, 238, 135);
		detectorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carDetected_ = !carDetected_;
				if (carDetected_) {
					detectorButton.setBackground(Color.GREEN);
					detectorButton.setText("Car Detected");
				}
				else {
					detectorButton.setBackground(Color.RED);
					detectorButton.setText("No Car Detected");
				}
				for (ICarSensorResponder responder : responders_ ) {
					responder.carEventDetected(detectorId_, carDetected_);
				}
			}
		});
		detectorButton.setBackground(Color.RED);
		detectorButton.setText("No Car Detected");
		contentPane_.add(detectorButton);
	}
	
	public void registerResponder(ICarSensorResponder responder) {
		if (!responders_.contains(responder)) {
			responders_.add(responder);
		}
	}
	
	public void deregisterResponder(ICarSensorResponder responder) {
		if (responders_.contains(responder)) {
			responders_.remove(responder);
		}
	}

	@Override
	public String getId() {
		return detectorId_;
	}

	@Override
	public boolean carIsDetected() {
		return carDetected_;
	}
	
	

}
