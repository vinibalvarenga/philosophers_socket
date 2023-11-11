package exe1;

import java.util.Random;

public class Philosophe extends Thread {
	private String actualState;
	private String name;
	private Forchette f_left;
	private Forchette f_right;
	private String type;
	private int fois_mange = 0;

	public Philosophe(String name, Forchette f_left, Forchette f_right, String type) {
		this.actualState = "reflechir";
		this.name = name;
		this.f_left = f_left;
		this.f_right = f_right;
		this.type = type;
		Printa.limparArquivo("timeline.txt");
	}

	public void run() {
		try {
			while (true) {
				if (get_state().equals("affame")) {
					synchronized (f_left.getLock()) { // Bloqueia o garfo esquerdo
						// Aguarde até que o garfo esquerdo esteja livre
						while (!f_left.get_state().equals("free")) {
							f_left.getLock().wait();
						}
						f_left.set_state("occupee");
						f_left.set_owner(get_name());
						Printa.print(type, get_name() + " get the left fork");

						synchronized (f_right.getLock()) { // Bloqueia o garfo direito
							// Aguarde até que o garfo direito esteja livre
							while (!f_right.get_state().equals("free")) {
								f_right.getLock().wait();
							}
							f_right.set_state("occupee");
							f_right.set_owner(get_name());
							Printa.print(type, get_name() + " get the right fork");

							set_state("eating");
							eat();
							f_left.set_state("free");
							f_left.set_owner("none");
							Printa.print(type, get_name() + " drops left fork");
							f_right.set_state("free");
							f_right.set_owner("none");
							Printa.print(type, get_name() + " drops right fork");

							// Notifique outras threads que estão esperando por esses garfos
							f_left.getLock().notify();
							f_right.getLock().notify();
						}
					}
				} else if (get_state().equals("reflechir")) {
					reflechir();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reflechir() {
		Random rand = new Random();
		Printa.print(type, "philosophe " + get_name() + " reflects");
		try {
			Thread.sleep(rand.nextInt(256));
		} catch (Exception e) {
		}
		set_state("affame");
		Printa.print(type, get_name() + " becomes affame");
	}

	public void eat() {
		Random rand = new Random();
		Printa.print(type, "philosophe " + get_name() + " eats");
		fois_mange++;
		Printa.print(type, get_name() + " manges pour la " + fois_mange + " fois");
		try {
			Thread.sleep(rand.nextInt(256));
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
}
