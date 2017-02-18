package groupchat.server;

import groupchat.util.StreamUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * ��������ӿͻ����߳�
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
	 * ����û�����
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
			System.out.println(clientAddress + "������");
			try {
				for (Socket s : serverSocketList) {
					out = StreamUtil.getOutFromSocket(s);
					out.println(clientAddress + "������");
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
	 * ���ա�������Ϣ
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
					System.out.println(clientAddress + "˵:" + message);
					synchronized (serverSocketList) {
						for (Socket s : serverSocketList) {
							out = StreamUtil.getOutFromSocket(s);
							out.println(clientAddress + "˵:" + message);
							out.flush();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			/**
			 * ����û�����
			 */
			System.out.println(clientAddress + "������");
			synchronized (serverSocketList) {
				serverSocketList.remove(serverSocket);
				onlineNum--;
				try {
					for (Socket s : serverSocketList) {
						out = StreamUtil.getOutFromSocket(s);
						out.println(clientAddress + "������");
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
