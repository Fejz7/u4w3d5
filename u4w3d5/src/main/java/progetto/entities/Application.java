package progetto.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.github.javafaker.Faker;


public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4w3d5");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker();


        aggiungiLibro(em, faker.code().isbn13(), faker.book().title(), faker.number().numberBetween(1800, 2023),
                faker.number().numberBetween(50, 1000), faker.book().author(), faker.book().genre());


        aggiungiRivista(em, faker.code().isbn13(), faker.book().title(), faker.number().numberBetween(1800, 2023),
                faker.number().numberBetween(50, 200), Rivista.Periodicita.MENSILE);



        em.close();
        emf.close();
    }


    private static void aggiungiLibro(EntityManager em, String isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        em.getTransaction().begin();

        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitolo(titolo);
        libro.setAnnoPubblicazione(annoPubblicazione);
        libro.setNumeroPagine(numeroPagine);
        libro.setAutore(autore);
        libro.setGenere(genere);

        em.persist(libro);

        em.getTransaction().commit();
    }


    private static void aggiungiRivista(EntityManager em, String isbn, String titolo, int annoPubblicazione, int numeroPagine, Rivista.Periodicita periodicita) {
        em.getTransaction().begin();

        Rivista rivista = new Rivista();
        rivista.setIsbn(isbn);
        rivista.setTitolo(titolo);
        rivista.setAnnoPubblicazione(annoPubblicazione);
        rivista.setNumeroPagine(numeroPagine);
        rivista.setPeriodicita(periodicita);

        em.persist(rivista);

        em.getTransaction().commit();
    }


}
