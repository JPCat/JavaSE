package groupchat.server;

/**
 * 服务端监控在线人数线程
 * 
 * @author chen7
 * 
 */
public class MonitorOnlineNumThread extends Thread {

	@Override
	public void run() {
		while (true) {
			System.out.println("服务端在线人数为" + ServerThread.onlineNum);
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
