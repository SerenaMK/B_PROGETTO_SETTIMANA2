package CatalogoBibliotecario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Archivio {
	
	static Logger log = LoggerFactory.getLogger(Archivio.class);
	static ArrayList<Pubblicazioni> archivio = new ArrayList<Pubblicazioni>();
	static File file = new File("dirFile/archivio.txt");

	public static void main(String[] args) {
		
		// Aggiungo libri e riviste
		addElement("978-1-78663-729-1", "Full Surrogacy Now: Feminism Against Family", 2019, 200, "Sophie Lewis", "Politica");
		addElement("978-0-425-27399-7", "Captive Prince", 2017, 140, "C. S. Pacat", "Fantasy");
		addElement("978-0-425-27399-8", "Prince's Gambit", 2018, 145, "C. S. Pacat", "Fantasy");
		addElement("978-0-425-27399-9", "Kings Rising", 2019, 150, "C. S. Pacat", "Fantasy");
		addElement("978-1-420-69420-6", "Libro a caso da rimuovere", 2020, 50, "Tizio Caio", "Thriller");
		addElement("978-3-16-148410-0", "Il Manifesto", 2022, 20, Periodicita.MENSILE);
		
		// Stampa l'archivio
		getArchivio();
		
		// Rimuovi un libro
		removeElement("978-1-420-69420-6");
		
		// Stampa di nuovo l'archivio aggiornato
		getArchivio();
		
		// Ricerca per ISBN, anno e autore
		searchByISBN("978-1-78663-729-1");
		searchByYear(2019);
		searchByAuthor("C. S. Pacat");
		
		// Salva l'archivio su file
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Carica l'archivio da file
		try {
			load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	// Stampa l'archivio
	public static void getArchivio() {
		System.out.println("\n-------ARCHIVIO--------");
		System.out.println(archivio);
		System.out.println("-----------------------");
	}
	
	// Aggiunta di un elemento
	public static void addElement(String isbn, String titolo, int anno, int pagine, String autore, String genere) {
		Libri l = new Libri(isbn, titolo, anno, pagine, autore, genere);
		archivio.add(l);
		System.out.println("\n> Il libro " + titolo + " è stato aggiunto all'archivio.");
	}
	
	public static void addElement(String isbn, String titolo, int anno, int pagine, Periodicita periodicita) {
		Riviste r = new Riviste(isbn, titolo, anno, pagine, periodicita);
		archivio.add(r);
		System.out.println("\n> La rivista " + titolo + " è stata aggiunta all'archivio.");
	}
	
	// Rimozione di un elemento dato un codice ISBN
	public static void removeElement(String isbn) {
		archivio.removeIf(e -> e.getISBN().equals(isbn));
		System.out.println("\n> La pubblicazione con codice ISBN " + isbn + " è stata rimossa dall'archivio.");
	}
	
	// Ricerca per ISBN
	public static void searchByISBN(String isbn) {
		System.out.println("\n> RICERCA PER ISBN");
		System.out.println("Il titolo della pubblicazione con codice ISBN " + isbn + " è:");
		archivio.stream()
				.filter(e -> e.getISBN().equals(isbn))
				.forEach(e -> System.out.println("\"" + e.getTitolo() + "\""));
	}
	
	// Ricerca per anno pubblicazione
	public static void searchByYear(int anno) {
		System.out.println("\n> RICERCA PER ANNO DI PUBBLICAZIONE");
		System.out.println("I titoli pubblicati nell'anno " + anno + " sono:");
		archivio.stream()
				.filter(e -> e.getAnno() == (anno))
				.forEach(e -> System.out.println("\"" + e.getTitolo() + "\""));
	}
	
	// Ricerca per autore
	public static void searchByAuthor(String autore) {
		System.out.println("\n> RICERCA PER AUTORE");
		System.out.println("I libri scritti da " + autore + " sono:");
		archivio.stream()
				.filter(e -> e instanceof Libri)
				.filter(e -> ((Libri) e).getAutore() == (autore))
				.forEach(e -> System.out.println("\"" + e.getTitolo() + "\""));
	}
	
	// Salvataggio su disco dell'archivio
	public static void save() throws IOException {
		System.out.println("\n> SALVATAGGIO ARCHIVIO SU FILE...");
		FileUtils.writeLines(file, archivio);
		System.out.println("Archivio salvato.");
	}
	
	// Caricamento dal disco dell'archivio
	public static void load() throws IOException {
		System.out.println("\n> CARICAMENTO ARCHIVIO DA FILE");
		System.out.println("Archivio caricato:");
		String loaded = FileUtils.readFileToString(file, "UTF-8");
		System.out.println(loaded);
	}

}
