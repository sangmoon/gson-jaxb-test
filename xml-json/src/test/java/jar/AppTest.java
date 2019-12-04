package jar;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXB;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tmax.sangmoon.Book;
import com.tmax.sangmoon.Catalog;
import com.tmax.sangmoon.CustomFieldNamePolicy;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

//	public void testObjectToXmltoObject() throws ParseException {
//		File xmlFile = new File("xmlfile.xml");
//		while (xmlFile.exists()) {
//			xmlFile.delete();
//		}
//
//		Catalog catalog = new Catalog();
//
//		Book book1 = new Book();
//		book1.setId("bk101");
//		book1.setTitle("Pride and Prejudice");
//		book1.setAuthor("Jane Austen");
//		book1.setGenre("Romance");
//		book1.setPrice(new BigDecimal("6.99"));
//		book1.setPubdate(new SimpleDateFormat("yyyy-MM-dd").parse("2010-04-01"));
//		book1.setDescription(
//				"\"It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife.\" So begins Pride and Prejudice, Jane Austen's witty comedy of manners-one of the most popular novels of all time-that features splendidly civilized sparring between the proud Mr. Darcy and the prejudiced Elizabeth Bennet as they play out their spirited courtship in a series of eighteenth-century drawing-room intrigues.");
//		catalog.getBooks().add(book1);
//
//		Book book2 = new Book();
//		book2.setId("bk102");
//		book2.setTitle("To Kill a Mockingbird");
//		book2.setAuthor("Harper Lee");
//		book2.setGenre("Drama");
//		book2.setPrice(new BigDecimal("6.39"));
//		book2.setPubdate(new SimpleDateFormat("yyyy-MM-dd").parse("2005-07-05"));
//		book2.setDescription(
//				"One of the best-loved stories of all time, To Kill a Mockingbird has been translated into more than forty languages, sold more than forty million copies worldwide, served as the basis for an enormously popular motion picture, and was voted one of the best novels of the twentieth century by librarians across the country. A gripping, heart-wrenching, and wholly remarkable tale of coming-of-age in a South poisoned by virulent prejudice, it views a world of great beauty and savage inequities through the eyes of a young girl, as her father-a crusading local lawyer-risks everything to defend a black man unjustly accused of a terrible crime.");
//		catalog.getBooks().add(book2);
//
//		JAXB.marshal(catalog, new File("xmlfile.xml"));
//		assertTrue(true);
//
//		Catalog newCatalog = JAXB.unmarshal(new File("xmlfile.xml"), Catalog.class);
//		Book book3 = newCatalog.getBooks().get(0);
//		Book book4 = newCatalog.getBooks().get(1);
//		assertEquals(book1.getAuthor(), book3.getAuthor());
//		assertEquals(book1.getId(), book3.getId());
//		assertEquals(book1.getTitle(), book3.getTitle());
//		assertEquals(book1.getGenre(), book3.getGenre());
//		assertEquals(book1.getPrice(), book3.getPrice());
//		assertEquals(book1.getPubdate(), book3.getPubdate());
//		assertEquals(book1.getDescription(), book3.getDescription());
//
//		while (xmlFile.exists()) {
//			xmlFile.delete();
//		}
//	}

	public void testObjectToJsonToObject() throws ParseException, IOException {
		Path jsonFile = Paths.get("jsonFile.json");
		while (jsonFile.toFile().exists()) {
			jsonFile.toFile().delete();
		}
		
		Catalog catalog = new Catalog();

		Book book1 = new Book();
		book1.setId("bk101");
		book1.setTitle("Pride and Prejudice");
		book1.setAuthor("Jane Austen");
		book1.setGenre("Romance");
		book1.setPrice(new BigDecimal("6.99"));
		book1.setPubdate(new SimpleDateFormat("yyyy-MM-dd").parse("2010-04-01"));
		book1.setDescription(
				"\"It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife.\" So begins Pride and Prejudice, Jane Austen's witty comedy of manners-one of the most popular novels of all time-that features splendidly civilized sparring between the proud Mr. Darcy and the prejudiced Elizabeth Bennet as they play out their spirited courtship in a series of eighteenth-century drawing-room intrigues.");
		catalog.getBooks().add(book1);

		Book book2 = new Book();
		book2.setId("bk102");
		book2.setTitle("To Kill a Mockingbird");
		book2.setAuthor("Harper Lee");
		book2.setGenre("Drama");
		book2.setPrice(new BigDecimal("6.39"));
		book2.setPubdate(new SimpleDateFormat("yyyy-MM-dd").parse("2005-07-05"));
		book2.setDescription(
				"One of the best-loved stories of all time, To Kill a Mockingbird has been translated into more than forty languages, sold more than forty million copies worldwide, served as the basis for an enormously popular motion picture, and was voted one of the best novels of the twentieth century by librarians across the country. A gripping, heart-wrenching, and wholly remarkable tale of coming-of-age in a South poisoned by virulent prejudice, it views a world of great beauty and savage inequities through the eyes of a young girl, as her father-a crusading local lawyer-risks everything to defend a black man unjustly accused of a terrible crime.");
		catalog.getBooks().add(book2);
		
		
		GsonBuilder builder = new GsonBuilder();
		builder.setFieldNamingStrategy(new CustomFieldNamePolicy());
		Gson gson = builder.create();
		String json = gson.toJson(catalog, Catalog.class);
		Files.write(jsonFile, json.getBytes(), StandardOpenOption.CREATE);
		assertTrue(true);
		
		Catalog newCatalog = gson.fromJson(new String(Files.readAllBytes(jsonFile),Charset.forName("UTF-8")), Catalog.class);
		Book book3 = newCatalog.getBooks().get(0);
		Book book4 = newCatalog.getBooks().get(1);
		assertEquals(book1.getAuthor(), book3.getAuthor());
		assertEquals(book1.getId(), book3.getId());
		assertEquals(book1.getTitle(), book3.getTitle());
		assertEquals(book1.getGenre(), book3.getGenre());
		assertEquals(book1.getPrice(), book3.getPrice());
		assertEquals(book1.getPubdate(), book3.getPubdate());
		assertEquals(book1.getDescription(), book3.getDescription());
		
		while (jsonFile.toFile().exists()) {
			jsonFile.toFile().delete();
		}
	}
}
