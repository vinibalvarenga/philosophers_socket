package exe2_socket_Hw;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int porta = 5345;

		// Inicia o servidor em uma thread separada
		Server server = new Server(porta);
		server.start();
		
		// Inicia o cliente
		Client cliente = new Client("localhost", porta);
		cliente.start();

	}
}
