package exe3_philosophes_remote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import exe1.Forchette;
import exe1.Printa;

public class Server extends Thread {
	private int port;
	private ServerSocket serverSocket;
	private List<Forchette> forchettes;
	private String type;

	public Server(int port, String type) {
		this.port = port;
		this.serverSocket = null;
		this.type = type;
		this.forchettes = new ArrayList<>();
		// Crie os garfos
		for (int i = 1; i <= 5; i++) {
			forchettes.add(new Forchette(i));
		}

	}

	public void run() {
		Socket clienteSocket = null;

		try {

			// Inicializa o servidor socket
			serverSocket = new ServerSocket(port);
			Printa.print(type, "[Server] Servidor aguardando conexões na porta " + port);

			while (true) {
				// Aguarda a conexão de um cliente
				clienteSocket = serverSocket.accept();
				Printa.print(type, "[Server] Conexão estabelecida com o cliente " + clienteSocket.getInetAddress());

				// Cria uma thread para lidar com a comunicação do cliente
				if (clienteSocket != null) {
					ClienteHandler clienteHandler = new ClienteHandler(clienteSocket, this.forchettes, this);
					clienteHandler.start();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				Printa.print(type, "[Server] Closing server");
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public synchronized String getForkStatus(int forkId) {
	    // Verifica o status do garfo com o ID especificado e retorna "free" ou "occupied".
		Forchette fork = forchettes.get(forkId);
        return fork.get_state();
	}

	public synchronized void setForkState(int forkId, String state) {
	    // Define o estado do garfo com o ID especificado como "free" ou "occupied".
		Forchette fork = forchettes.get(forkId);
        fork.set_state(state);
	}

	public synchronized void setForkOwner(int forkId, String owner) {
	    // Define o proprietário do garfo com o ID especificado.
		Forchette fork = forchettes.get(forkId);
        fork.set_owner(owner);
	}

	public void encerrar() {
		try {
			serverSocket.close();
			Printa.print(type, "Servidor encerrado.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
