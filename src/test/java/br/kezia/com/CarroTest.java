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

public class CarroTest {

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
    public void testCarroPersistence() {
        Carro carro = new Carro();
        carro.setModelo("Modelo Teste");

        Marca marca = new Marca();
        marca.setNome("Marca Teste");
        carro.setMarca(marca);

        Acessorio acessorio = new Acessorio();
        acessorio.setDescricao("Acessorio Teste");
        carro.setAcessorio(acessorio);

        em.persist(carro);

        assertNotNull(carro.getId());
        assertNotNull(carro.getMarca().getId());
        assertNotNull(carro.getAcessorio().getId());

        Carro persistedCarro = em.find(Carro.class, carro.getId());
        assertEquals("Modelo Teste", persistedCarro.getModelo());
        assertEquals("Marca Teste", persistedCarro.getMarca().getNome());
        assertEquals("Acessorio Teste", persistedCarro.getAcessorio().getDescricao());
    }
}
