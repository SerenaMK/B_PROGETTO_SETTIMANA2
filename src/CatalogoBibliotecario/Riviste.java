package CatalogoBibliotecario;

public class Riviste extends Pubblicazioni {
	
	private Periodicita periodicita;

	public Riviste(String isbn, String titolo, int anno, int pagine, Periodicita periodicita) {
		super(isbn, titolo, anno, pagine);
		this.periodicita = periodicita;
	}

	public Periodicita getPeriodicita() {
		return periodicita;
	}
	
	@Override
	public String toString() {
		return ( super.toString() +
				"\nPeriodicità: " + getPeriodicita() + "\n");
	}
	
	@Override
	public String toSave() {
		return ( super.toSave() + "@" +
				getPeriodicita());
	}
}
