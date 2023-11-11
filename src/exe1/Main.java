package exe1;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Forchette forchette_1 = new Forchette();
		Forchette forchette_2 = new Forchette();
		Forchette forchette_3 = new Forchette();
		Forchette forchette_4 = new Forchette();
		Forchette forchette_5 = new Forchette();

		Philosophe philosophe_1 = new Philosophe("Anaximandro", forchette_5, forchette_1, args[0]);
		Philosophe philosophe_2 = new Philosophe("Descartes", forchette_1, forchette_2, args[0]);
		Philosophe philosophe_3 = new Philosophe("Rousseu", forchette_2, forchette_3, args[0]);
		Philosophe philosophe_4 = new Philosophe("Socrates", forchette_3, forchette_4, args[0]);
		Philosophe philosophe_5 = new Philosophe("Sun Tsu", forchette_4, forchette_5, args[0]);

		philosophe_1.start();
		philosophe_2.start();
		philosophe_3.start();
		philosophe_4.start();
		philosophe_5.start();

		try {
			philosophe_1.join();
		} catch (Exception e) {
		}
		try {
			philosophe_2.join();
		} catch (Exception e) {
		}
		try {
			philosophe_3.join();
		} catch (Exception e) {
		}
		try {
			philosophe_4.join();
		} catch (Exception e) {
		}
		try {
			philosophe_5.join();
		} catch (Exception e) {
		}

	}

}
