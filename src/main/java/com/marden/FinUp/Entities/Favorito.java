package com.marden.FinUp.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_FAVORITO")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFavorito;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String ticker;
    private String nomeAtivo;
    private LocalDate dataAdicao;

    public Favorito(){}

    public Favorito(Usuario usuario, String ticker, String nomeAtivo, LocalDate dataAdicao) {
        this.usuario = usuario;
        this.ticker = ticker;
        this.nomeAtivo = nomeAtivo;
        this.dataAdicao = dataAdicao;
    }

    public Long getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Long idFavorito) {
        this.idFavorito = idFavorito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getNomeAtivo() {
        return nomeAtivo;
    }

    public void setNomeAtivo(String nomeAtivo) {
        this.nomeAtivo = nomeAtivo;
    }

    public LocalDate getDataAdicao() {
        return dataAdicao;
    }

    public void setDataAdicao(LocalDate dataAdicao) {
        this.dataAdicao = dataAdicao;
    }
}
