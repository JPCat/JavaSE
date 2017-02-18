package groupchat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 流工具类
 * 
 * @author chen7
 * 
 */
public class StreamUtil {

	private StreamUtil() {
	}

	/**
	 * 获取键盘输入
	 * 
	 * @return
	 */
	public static BufferedReader getKey() {
		return new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * 从Socket中获取输入流
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
	 * 从Socket中获取输出流
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
