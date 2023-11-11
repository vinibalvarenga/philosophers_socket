package exe2_socket_Hw;

import java.io.*;
import java.net.*;

public class ClienteHandler extends Thread {
	private Socket clienteSocket;

	public ClienteHandler(Socket socket) {
		this.clienteSocket = socket;
	}

	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			// Cria fluxos para leitura e escrita
			in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
			out = new PrintWriter(clienteSocket.getOutputStream(), true);

			// Lê a mensagem do cliente
			String mensagemDoCliente;
			
			while (( mensagemDoCliente = in.readLine()) != null) {
				System.out.println("[Handler] Cliente diz: " + mensagemDoCliente);
				out.println(" Hello " + mensagemDoCliente + "!");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clienteSocket.close();
                    System.out.println("[Handler] Closing handler");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
