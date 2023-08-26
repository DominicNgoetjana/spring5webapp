package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repos.AuthorRepo;
import guru.springframework.spring5webapp.repos.BookRepo;
import guru.springframework.spring5webapp.repos.PublisherRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final PublisherRepo publisherRepo;

    public BootStrapData(AuthorRepo authorRepo, BookRepo bookRepo, PublisherRepo publisherRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.publisherRepo = publisherRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Publisher books2world = new Publisher("Books2World", "100 Street Lane", "New Byork", "Cali", "3003003");
        publisherRepo.save(books2world);

        System.out.println("Publisher Count: " + publisherRepo.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        Book ddds = new Book("Domain Driven Design Successor", "456456");

        eric.getBooks().add(ddd);
        eric.getBooks().add(ddds);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(books2world);
        books2world.getBooks().add(ddd);

        authorRepo.save(eric);
        bookRepo.save(ddd);
        bookRepo.save(ddds);
        publisherRepo.save(books2world);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "13152548646");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(books2world);
        books2world.getBooks().add(noEJB);

        authorRepo.save(rod);
        bookRepo.save(noEJB);
        publisherRepo.save(books2world);

        System.out.println("Number of Books: " + bookRepo.count());
        System.out.println("Publisher Number of Books: " + books2world.getBooks().size());
    }
}
