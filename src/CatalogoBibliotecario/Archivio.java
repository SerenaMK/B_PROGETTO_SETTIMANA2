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
		// PER L'ESERCIZIO, USARE JAVA STREAMS E LAMBDA EXPRESSIONS
		
		addElement("978-1-78663-729-1", "Full Surrogacy Now: Feminism Against Family", 2019, 200, "Sophie Lewis", "Politica");
		addElement("978-0-425-27399-7", "Captive Prince", 2017, 140, "C. S. Pacat", "Fantasy");
		addElement("978-0-425-27399-8", "Prince's Gambit", 2018, 145, "C. S. Pacat", "Fantasy");
		addElement("978-0-425-27399-9", "Kings Rising", 2019, 150, "C. S. Pacat", "Fantasy");
		addElement("978-3-16-148410-0", "Il Manifesto", 2022, 20, Periodicita.MENSILE);
		
		//System.out.println(archivio);
		
//		removeElement("978-3-16-148410-0");
//		
//		System.out.println(archivio);
//		
//		searchByISBN("978-1-78663-729-1");
//		searchByYear(2019);
//		searchByAuthor("C. S. Pacat");
//		
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// System.out.println(archivio.toString());

	}
	
	// Aggiunta di un elemento
	public static void addElement(String isbn, String titolo, int anno, int pagine, String autore, String genere) {
		Libri l = new Libri(isbn, titolo, anno, pagine, autore, genere);
		archivio.add(l);
	}
	
	public static void addElement(String isbn, String titolo, int anno, int pagine, Periodicita periodicita) {
		Riviste r = new Riviste(isbn, titolo, anno, pagine, periodicita);
		archivio.add(r);
	}
	
	// Rimozione di un elemento dato un codice ISBN
	public static void removeElement(String isbn) {
		archivio.removeIf(e -> e.getISBN().equals(isbn)); 
	}
	
	// Ricerca per ISBN
	public static void searchByISBN(String isbn) {
		System.out.println("\n> RICERCA PER ISBN");
		System.out.println("Il titolo della pubblicazione con codice ISBN " + isbn + " è:");
		archivio.stream().filter(e -> e.getISBN().equals(isbn)).forEach(e -> System.out.println("\"" + e.getTitolo() + "\""));
	}
	
	// Ricerca per anno pubblicazione
	public static void searchByYear(int anno) {
		System.out.println("\n> RICERCA PER ANNO DI PUBBLICAZIONE");
		System.out.println("I titoli pubblicati nell'anno " + anno + " sono:");
		archivio.stream().filter(e -> e.getAnno() == (anno)).forEach(e -> System.out.println("\"" + e.getTitolo() + "\""));
	}
	
	// Ricerca per autore
	public static void searchByAuthor(String autore) {
		System.out.println("\n> RICERCA PER AUTORE");
		System.out.println("I libri scritti da " + autore + " sono:");
		archivio.stream().filter(e -> ((Libri) e).getAutore() == (autore)).forEach(e -> System.out.println("\"" + e.getTitolo() + "\""));
	}
	
	// salvataggio su disco dell'archivio
	public static void save() throws IOException {
		System.out.println("\n> SALVATAGGIO ARCHIVIO SU FILE...");
		FileUtils.writeLines(file, archivio);
		System.out.println("> ARCHIVIO SALVATO");
	}
	
	// caricamento dal disco dell'archivio
	public static void load() throws IOException {
		System.out.println("\n> CARICA ARCHIVIO DA FILE");
//		List<String> loaded = FileUtils.readLines(file, "UTF-8");
		String loaded = FileUtils.readFileToString(file, "UTF-8");
		System.out.println(loaded);
	}

}
