package groupchat.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 群聊客户端
 * 
 * @author chen7
 * 
 */
public class Client {

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Socket clientSocket = new Socket("127.0.0.1", 11111);
		new SendMessageThread(clientSocket).start();
		new ReceiveMessageThread(clientSocket).start();
	}
}
