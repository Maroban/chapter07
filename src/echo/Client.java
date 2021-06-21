package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {

		Socket socket = new Socket();

		System.out.println("<클라이언트 시작>");
		System.out.println("====================================");

		System.out.println("[서버에 연결을 요청합니다.]");
		socket.connect(new InetSocketAddress("211.177.56.195", 10001));

		System.out.println("[서버에 연결되었습니다.]");
		
		Scanner sc = new Scanner(System.in);
		
		// 메세지 보내기용 스트림
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		// 메세지 받기용 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// 1:1 채팅 메신저처럼 메세지 보내기(반복문 사용)
		while (true) {

			// 키보드 입력
			String msg = sc.nextLine();

			// /q 가 입력되면 종료.
			if ("/q".equals(msg)) {
				System.out.println("[접속이 종료되었습니다.]");
				break;
			}

			// 보내기
			bw.write("(유승범)"+msg);
			bw.newLine();
			bw.flush();

			// 메세지 출력
			String reMsg = br.readLine();
			System.out.println("[Server: " + reMsg + "]");
		}

		System.out.println("====================================");
		System.out.println("<클라이언트 종료>");
		
		sc.close();
		br.close();
		bw.close();
		socket.close();
	}

}
