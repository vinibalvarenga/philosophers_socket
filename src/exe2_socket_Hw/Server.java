package exe2_socket_Hw;

import java.io.*;
import java.net.*;

public class Server extends Thread {
	private int port;
	private ServerSocket serverSocket;

	public Server(int port) {
		this.port = port;
		this.serverSocket = null;
	}

	public void run() {
		Socket clienteSocket = null;

		try {

			// Inicializa o servidor socket
			serverSocket = new ServerSocket(port);
			System.out.println("[Server] Servidor aguardando conexões na porta " + port);

			while (true) {
				// Aguarda a conexão de um cliente
				clienteSocket = serverSocket.accept();
				System.out.println("[Server] Conexão estabelecida com o cliente " + clienteSocket.getInetAddress());

				// Cria uma thread para lidar com a comunicação do cliente
				ClienteHandler clienteHandler = new ClienteHandler(clienteSocket);
				clienteHandler.start();

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("[Server] Closing server");
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void encerrar() {
		try {
			serverSocket.close();
			System.out.println("Servidor encerrado.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
