package showtime;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 窗口上显示时间
 * 
 * @author chen7
 * 
 */
public class TestFrame extends JFrame
{
	private static final long serialVersionUID = -1983593599021549629L;

	public TestFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPanel = (JPanel) getContentPane();
		JLabel showTimeLabel = new JLabel();
		new ChangeTimeThread(showTimeLabel).start();
		showTimeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPanel.add(showTimeLabel);
		pack();
	}

	public static void main(String[] args)
	{
		new TestFrame().setVisible(true);
	}
}
