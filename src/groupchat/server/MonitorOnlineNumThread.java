package groupchat.server;

/**
 * ������������߳�
 * 
 * @author chen7
 * 
 */
public class MonitorOnlineNumThread extends Thread {

	@Override
	public void run() {
		while (true) {
			System.out.println("��ǰ��������Ϊ��" + ServerThread.onlineNum);
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
