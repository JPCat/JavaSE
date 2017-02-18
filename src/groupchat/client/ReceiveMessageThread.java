package groupchat.client;

import groupchat.util.StreamUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

/**
 * 客户端接收消息线程
 * 
 * @author chen7
 * 
 */
public class ReceiveMessageThread extends Thread {

	private BufferedReader in;

	public ReceiveMessageThread(Socket socket) throws IOException {
		in = StreamUtil.getInFromSocket(socket);
	}

	@Override
	public void run() {
		try {
			while (true) {
				String message = in.readLine();
				if (message == null) {
					break;
				}
				System.out.println(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				System.exit(0);
			}
		}
	}
}
