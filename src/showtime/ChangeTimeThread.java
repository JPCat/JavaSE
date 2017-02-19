package showtime;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class ChangeTimeThread extends Thread {

	private JLabel showTimeLabel;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public ChangeTimeThread(JLabel showTimeLabel) {
		this.showTimeLabel = showTimeLabel;
	}

	@Override
	public void run() {
		while (true) {
			String currentTime = sdf.format(new Date());
			showTimeLabel.setText(currentTime);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
