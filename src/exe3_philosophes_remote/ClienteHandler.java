package exe3_philosophes_remote;

import java.io.*;
import java.net.*;
import java.util.List;

import exe1.Forchette;

public class ClienteHandler extends Thread {
	private Socket clienteSocket;
	private Server server;

	public ClienteHandler(Socket socket, List<Forchette> forchettes, Server server) {
		this.clienteSocket = socket;
		this.server = server;
	}

	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            out = new PrintWriter(clienteSocket.getOutputStream(), true);
            
            while (true) {
                String request = in.readLine();
                if (request == null) {
                    // Cliente desconectado
                    break;
                }

                if (request.startsWith("GET_FORK_STATUS")) {
                    int forchetteId = Integer.parseInt(request.split(" ")[1]);
                   String forchette_status = server.getForkStatus(forchetteId-1);
                    out.println(forchette_status);
                } else if (request.startsWith("SET_FORK_STATE")) {
                	 int forchetteId = Integer.parseInt(request.split(" ")[1]);
                	 server.setForkState(forchetteId-1, request.split(" ")[2]);
                } else if (request.startsWith("SET_FORK_OWNER")) {
                	 int forchetteId = Integer.parseInt(request.split(" ")[1]);
                	 server.setForkOwner(forchetteId-1, request.split(" ")[2]);
                }
            }

            clienteSocket.close();

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
