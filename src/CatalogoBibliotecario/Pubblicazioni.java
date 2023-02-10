package CatalogoBibliotecario;

public abstract class Pubblicazioni {
	
	private String ISBN;
	private String titolo;
	private int anno;
	private int pagine;
	
	public Pubblicazioni(String isbn, String titolo, int anno, int pagine) {
		super();
		ISBN = isbn;
		this.titolo = titolo;
		this.anno = anno;
		this.pagine = pagine;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getTitolo() {
		return titolo;
	}

	public int getAnno() {
		return anno;
	}

	public int getPagine() {
		return pagine;
	}
	
	@Override
	public String toString() {
		return ("\nISBN: " + getISBN() +
				"\nTitolo: \"" + getTitolo() + "\"" +
				"\nAnno: " + getAnno() +
				"\nPagine : " + getPagine());
	}
}
