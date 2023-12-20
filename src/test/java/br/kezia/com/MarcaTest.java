package br.kezia.com;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MarcaTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("ExemploJPA");
        em = emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();
    }

    @After
    public void tearDown() {
        tx.rollback();
        em.close();
        emf.close();
    }

    @Test
    public void testMarcaPersistence() {
        Marca marca = new Marca();
        marca.setNome("Marca Teste");

        Carro carro = new Carro();
        carro.setModelo("Modelo Teste");
        marca.setCarro(carro);

        em.persist(marca);

        assertNotNull(marca.getId());
        assertNotNull(marca.getCarro().getId());

        Marca persistedMarca = em.find(Marca.class, marca.getId());
        assertEquals("Marca Teste", persistedMarca.getNome());
        assertEquals("Modelo Teste", persistedMarca.getCarro().getModelo());
    }
}
