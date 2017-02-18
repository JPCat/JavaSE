package groupchat.server;

import groupchat.util.StreamUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * 服务端连接客户端线程
 * 
 * @author chen7
 * 
 */
public class ServerThread extends Thread {

	private List<Socket> serverSocketList;
	private Socket serverSocket;
	private BufferedReader in;
	private PrintWriter out;
	private String clientAddress;
	public static int onlineNum;

	/**
	 * 监控用户上线
	 * 
	 * @param serverSocketList
	 * @param serverSocket
	 */
	public ServerThread(List<Socket> serverSocketList, Socket serverSocket) {
		synchronized (serverSocketList) {
			this.serverSocket = serverSocket;
			this.serverSocketList = serverSocketList;
			this.clientAddress = serverSocket.getRemoteSocketAddress()
					.toString().substring(1);
			System.out.println(clientAddress + "上线了");
			try {
				for (Socket s : serverSocketList) {
					out = StreamUtil.getOutFromSocket(s);
					out.println(clientAddress + "上线了");
					out.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				serverSocketList.add(serverSocket);
				onlineNum++;
			}
		}
	}

	/**
	 * 接收、发送消息
	 */
	@Override
	public void run() {
		try {
			while (true) {
				in = StreamUtil.getInFromSocket(serverSocket);
				String message = in.readLine();
				if (message == null) {
					break;
				} else if (message.trim().equals("endup")) {
					break;
				} else {
					System.out.println(clientAddress + "说:" + message);
					synchronized (serverSocketList) {
						for (Socket s : serverSocketList) {
							out = StreamUtil.getOutFromSocket(s);
							out.println(clientAddress + "说:" + message);
							out.flush();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			/**
			 * 监控用户下线
			 */
			System.out.println(clientAddress + "下线了");
			synchronized (serverSocketList) {
				serverSocketList.remove(serverSocket);
				onlineNum--;
				try {
					for (Socket s : serverSocketList) {
						out = StreamUtil.getOutFromSocket(s);
						out.println(clientAddress + "下线了");
						out.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						serverSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
