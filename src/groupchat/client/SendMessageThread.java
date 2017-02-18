package groupchat.client;

import groupchat.util.StreamUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端发送消息线程
 * 
 * @author chen7
 * 
 */
public class SendMessageThread extends Thread {

	private PrintWriter out;

	public SendMessageThread(Socket socket) throws IOException {
		out = StreamUtil.getOutFromSocket(socket);
	}

	@Override
	public void run() {
		try {
			System.out.println("请输入您想要发送的消息");
			while (true) {
				String message = StreamUtil.getKey().readLine();
				if (message == null) {
					break;
				} else if (message.trim().equals("")) {
					System.out.println("不能发送空消息");
					continue;
				} else {
					out.println(message);
					out.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
			System.exit(0);
		}
	}
}
