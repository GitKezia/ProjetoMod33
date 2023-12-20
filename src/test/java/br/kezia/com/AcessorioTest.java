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

public class AcessorioTest {

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
    public void testAcessorioPersistence() {
        Acessorio acessorio = new Acessorio();
        acessorio.setDescricao("Acessorio Teste");

        Carro carro = new Carro();
        carro.setModelo("Modelo Teste");
        acessorio.setCarro(carro);

        em.persist(acessorio);

        assertNotNull(acessorio.getId());
        assertNotNull(acessorio.getCarro().getId());

        Acessorio persistedAcessorio = em.find(Acessorio.class, acessorio.getId());
        assertEquals("Acessorio Teste", persistedAcessorio.getDescricao());
        assertEquals("Modelo Teste", persistedAcessorio.getCarro().getModelo());
    }
}
