package com.marden.FinUp.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_HISTORICO")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idHistorico;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String ticker;
    private LocalDate dataConsulta;
    private String resultado;

    Historico(){}

    public Historico(Usuario usuario, String ticker, LocalDate dataConsulta, String resultado) {
        this.usuario = usuario;
        this.ticker = ticker;
        this.dataConsulta = dataConsulta;
        this.resultado = resultado;
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

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Long getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Long idHistorico) {
        this.idHistorico = idHistorico;
    }
}
