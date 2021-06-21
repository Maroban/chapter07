package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = new ServerSocket();
		serverSocket.bind(new InetSocketAddress("211.177.56.195", 10001));

		System.out.println("<서버 시작>");
		System.out.println("====================================");
		System.out.println("[연결을 기다리고 있습니다.]");

		// 반복 구간. 연결 될 때마다 소켓 통신을 해야하기 때문
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("[클라이언트가 연결되었습니다.]");

			// 세팅 + 메세지 주고 받기
			Thread thread = new ServerThread(socket); // 누구랑 연결됐는지 알기 위함. (필드, 생성자 만들어줘야함)
			thread.start();

			// 탈출 조건 - 생략 (서버 무한 대기)
		}

	}

}
