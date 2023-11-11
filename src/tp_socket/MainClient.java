package tp_socket;

import java.io.IOException;
import java.net.UnknownHostException;

import exe3_philosophes_remote.PhilosopheClient;

public class MainClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String host = args[0];
		int porta = Integer.parseInt(args[1]);
		String out_type = args[2];
		// Instancia os filosofos
		PhilosopheClient philosophe_1 = new PhilosopheClient(host, porta, "[1] Anaximandro", 5, 1, out_type);
		PhilosopheClient philosophe_2 = new PhilosopheClient(host, porta, "[2] Descartes", 1, 2, out_type);
		PhilosopheClient philosophe_3 = new PhilosopheClient(host, porta, "[3] Rousseu", 2, 3, out_type);
		PhilosopheClient philosophe_4 = new PhilosopheClient(host, porta, "[4] Socrates", 3, 4, out_type);
		PhilosopheClient philosophe_5 = new PhilosopheClient(host, porta, "[5] Sun Tsu", 4, 5, out_type);

		// Start
		philosophe_1.start();
		philosophe_2.start();
		philosophe_3.start();
		philosophe_4.start();
		philosophe_5.start();
	}

}
