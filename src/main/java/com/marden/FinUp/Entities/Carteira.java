package com.marden.FinUp.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_CARTEIRA")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarteira;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;
    private String ticker;
    private double qtd;
    private double precoMedio;
    private double precoTotal;
    private LocalDate dataCompra;

    Carteira(){}

    public Carteira(Usuario idUsuario, String ticker, double qtd, double precoMedio, double precoTotal, LocalDate dataCompra) {
        this.idUsuario = idUsuario;
        this.ticker = ticker;
        this.qtd = qtd;
        this.precoMedio = precoMedio;
        this.precoTotal = precoTotal;
        this.dataCompra = dataCompra;
    }

    public Long getIdCarteira() {
        return idCarteira;
    }

    public void setIdCarteira(Long idCarteira) {
        this.idCarteira = idCarteira;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public double getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(double precoMedio) {
        this.precoMedio = precoMedio;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }
}
