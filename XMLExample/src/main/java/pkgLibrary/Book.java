package pkgLibrary;

import java.util.Date;
import java.util.ArrayList; 
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;



public class Book {

	private String id;
	private String author;
	private String title;
	private String genre;
	private double price;
	private Date publish_date;
	private String description;
	private double cost;

	public static Book GetBook(String ID) throws BookException {

		Catalog C = ReadXMLFile();

		ArrayList<Book> books = C.getBooks();

		Book Book1 = null;

		for (Book a : books) {
			if (a.getId().equals(ID)) {
				Book1 = a;
				}
		}
		if (Book1 != null) {
			return Book1;
		}
		else{
			
			throw new BookException("Book does not find!");}

	}
	
	public Book(String id) {
		id = id;
		try {
			author = GetBook(id).getAuthor();
			title = GetBook(id).getTitle();
			genre = GetBook(id).getGenre();
			price = GetBook(id).getPrice();
			publish_date = GetBook(id).getPublish_date();
			description = GetBook(id).getDescription();
			cost = price * .8;
		} catch (BookException exc) {
			System.out.println(exc.getMessage());
		}

	}

	
	private static void WriteXMLFile(Catalog C) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
			File file = new File(basePath);

			JAXBContext JAX = JAXBContext.newInstance(Catalog.class);
			Marshaller MAR = JAX.createMarshaller();

			MAR.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			MAR.marshal(C, file);
		    MAR.marshal(C, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	

	public static void addBook(String catID, Book newBook) throws BookException {
		Catalog C = ReadXMLFile();

		ArrayList<Book> books = C.getBooks();

		Book Book1 = null;

		for (Book b : books) {

			if (b.getId().equals(newBook.getId())) {

				Book1 = b;

			}
		}
		if (Book1 == null) {

			ArrayList<Book> catBooks = C.getBooks();

			catBooks.add(newBook);

			C.setBooks(catBooks);
			WriteXMLFile(C);
		}
		else{
			
			throw new BookException("Book is already inside!");}

		


		}

	private static Catalog ReadXMLFile() {

		Catalog C = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext JAX = JAXBContext.newInstance(Catalog.class);
			Unmarshaller UNM = JAX.createUnmarshaller();
			C = (Catalog) UNM.unmarshal(file);
			System.out.println(C);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return C;

	}
 

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	@XmlElement
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getPrice() {
		return price;
	}

	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPublish_date() {
		return publish_date;
	}

	@XmlElement
	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	public Book(String id, String author, String title, String genre, double price, Date publish_date, String description)
	{
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.genre = genre;		
		this.price = price;
		this.publish_date = publish_date;
		this.description = description;
	}
	
	
	

}
