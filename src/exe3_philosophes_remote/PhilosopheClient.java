package exe3_philosophes_remote;

import java.io.*;
import java.net.*;
import java.util.Random;
import exe1.Printa;

public class PhilosopheClient extends Thread {
	private Socket socket;
	private String actualState;
	private String name;
	private int f_left;
	private int f_right;
	private String type;
	private int fois_mange = 0;

	public PhilosopheClient(String servidorAddr, int porta, String name, int f_left, int f_right, String type)
			throws UnknownHostException, IOException {
		this.socket = new Socket(servidorAddr, porta);
		this.actualState = "reflechir";
		this.name = name;
		this.f_left = f_left;
		this.f_right = f_right;
		this.type = type;
		Printa.limparArquivo("timeline.txt");
	}

	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;

		try {
			Printa.print(type, "[Client] Philosophe " + get_name() + " subiu");
			// Fluxo de leitura do servidor
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// Fluxo de escrita no servidor
			out = new PrintWriter(socket.getOutputStream(), true);

			while (true) {
				if (get_state().equals("affame")) {
					
					// Aguarde até que o garfo esquerdo esteja livre
					while (!get_forchette_status_from_server(in, out, f_left).equals("free"))
						continue;
					set_forchette_state_at_server(in, out, f_left, "occupied");
					set_forchette_owner_at_server(in, out, f_left, this.get_name());
					Printa.print(type, get_name() + " get the left fork (" + f_left + ")");
					
					// Aguarde até que o garfo direito esteja livre
					while (!get_forchette_status_from_server(in, out, f_right).equals("free"))
						continue;
					set_forchette_state_at_server(in, out, f_right, "occupied");
					set_forchette_owner_at_server(in, out, f_right, this.get_name());
					Printa.print(type, get_name() + " get the right fork (" + f_right + ")");

					set_state("eating");
					eat();
					
					set_forchette_state_at_server(in, out, f_left, "free");
					set_forchette_owner_at_server(in, out, f_left, "none");
					Printa.print(type, get_name() + " drops left fork (" + f_left + ")");
					
					set_forchette_state_at_server(in, out, f_right, "free");
					set_forchette_owner_at_server(in, out, f_right, "none");
					Printa.print(type, get_name() + " drops right fork (" + f_right + ")");
				} else if (get_state().equals("reflechir")) {
					reflechir();
				}

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
					socket.close();
					Printa.print(type, "[Client] Closing Philosophe " + get_name());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void reflechir() {
		Random rand = new Random();
		int timeReflects = rand.nextInt(256);
		Printa.print(type,  get_name() + " reflects for " + timeReflects + " ms");
		try {
			Thread.sleep(timeReflects);
		} catch (Exception e) {
		}
		set_state("affame");
		Printa.print(type, get_name() + " becomes affame");
	}

	public void eat() {
		Random rand = new Random();
		int timeSleeping = rand.nextInt(256);
		fois_mange++;
		Printa.print(type,  get_name() + " eats for " + timeSleeping + " ms, he's eating for the " + fois_mange + "th time");
		try {
			Thread.sleep(timeSleeping);
		} catch (Exception e) {
		}
		set_state("reflechir");
	}

	public String get_name() {
		return this.name;
	}

	public void set_state(String state) {
		this.actualState = state;
	}

	public String get_state() {
		return this.actualState;
	}

	private String get_forchette_status_from_server(BufferedReader in, PrintWriter out, int forchette_id) {
		try {
			// Envia uma solicitação ao servidor para obter o status da forquilha com o ID
			// especificado
			out.println("GET_FORK_STATUS " + forchette_id);
			out.flush();

			// Lê a resposta do servidor
			String response = in.readLine();

			// A resposta deve conter o status da forquilha
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	private void set_forchette_state_at_server(BufferedReader in, PrintWriter out, int forchette_id, String state) {
		// Envia uma solicitação ao servidor para definir o estado da forquilha com o ID
		// especificado
		out.println("SET_FORK_STATE " + forchette_id + " " + state);
		out.flush();
	}

	private void set_forchette_owner_at_server(BufferedReader in, PrintWriter out, int forchette_id, String owner) {
		// Envia uma solicitação ao servidor para definir o proprietário da forquilha
		// com o ID especificado
		out.println("SET_FORK_OWNER " + forchette_id + " " + owner);
		out.flush();
	}

}
