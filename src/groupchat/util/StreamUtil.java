package groupchat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ��������
 * 
 * @author chen7
 * 
 */
public class StreamUtil {

	private StreamUtil() {
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public static BufferedReader getKey() {
		return new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * ��Socket�л�ȡ������
	 * 
	 * @param socket
	 * @return
	 * @throws IOException
	 */
	public static BufferedReader getInFromSocket(Socket socket)
			throws IOException {
		return new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
	}

	/**
	 * ��Socket�л�ȡ�����
	 * 
	 * @param socket
	 * @return
	 * @throws IOException
	 */
	public static PrintWriter getOutFromSocket(Socket socket)
			throws IOException {
		return new PrintWriter(socket.getOutputStream());
	}

}
