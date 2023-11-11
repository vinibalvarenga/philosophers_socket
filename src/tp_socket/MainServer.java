package tp_socket;

import exe3_philosophes_remote.Server;

public class MainServer {

	public static void main(String[] args) {
		int porta = Integer.parseInt(args[0]) ;
		String out_type = args[1];
		// Inicia o servidor em uma Thread separada
		Server server = new Server(porta, out_type);
		server.start();
	}

}
