package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	// 필드
	private Socket socket;

	// 생성자
	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	// 내보내서 해야할 일
	@Override
	public void run() {
		// 메세지 주고받기
		try {
			// 메세지 받기용 스트림
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			// 메세지 보내기용 스트림
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			while (true) {
				String msg = br.readLine();

				if (msg == null) {
					System.out.println("[클라이언트 접속 종료]");
					break;
				}

				System.out.println("받은 메세지: " + msg);

				bw.write(msg);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			System.out.println("통신 상태를 확인해주세요.");
		}

	}

}
