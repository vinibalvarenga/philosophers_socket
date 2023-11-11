package exe2_socket_Hw;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client extends Thread {
	private Socket socket;

	public Client(String servidorAddr, int porta) throws IOException {
		this.socket = new Socket(servidorAddr, porta);
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			System.out.println("[Client] Cliente subiu");
			// Fluxo de leitura do servidor
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// Fluxo de escrita no servidor
			out = new PrintWriter(socket.getOutputStream(), true);

			// object of scanner class

			String line = null;

			while (!"exit".equalsIgnoreCase(line)) {
				System.out.println("[Client] Type client's message: ");
				// reading from user
				line = sc.nextLine();

				// sending the user input to server
				out.println(line);
				out.flush();

				// displaying server reply
				System.out.println("[Client] Client received message: " + in.readLine());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sc.close(); // Feche o scanner no bloco finally
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
					socket.close();
					System.out.println("[Client] Closing handler");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
