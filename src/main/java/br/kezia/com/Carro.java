package br.kezia.com;

import jakarta.persistence.*;


@Entity
@Table(name = "carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Acessorio getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(Acessorio acessorio) {
        this.acessorio = acessorio;
    }

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @OneToOne(mappedBy = "carro", cascade = CascadeType.ALL)
    private Marca marca;

    @OneToOne(mappedBy = "carro", cascade = CascadeType.ALL)
    private Acessorio acessorio;

}
