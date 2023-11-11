package exe1;

public class Forchette {
	private String state = "free";
	private String owner = "none";
	private int id;
	private final Object lock = new Object(); // Objeto de bloqueio associado ao garfo

	// Exercicio 3
	public Forchette(int id) {
		this.id = id;
	}

	// Exercicio 1
	public Forchette() {

	}

	public int get_id() {
		return this.id;
	}

	public void set_state(String state) {
		this.state = state;
	}

	public String get_state() {
		return this.state;
	}

	public void set_owner(String owner) {
		this.owner = owner;
	}

	public String get_owner() {
		return this.owner;
	}

	public Object getLock() {
		return lock; // Retorna o objeto de bloqueio associado ao garfo
	}
}
