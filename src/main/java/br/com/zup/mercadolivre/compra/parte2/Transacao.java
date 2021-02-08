package br.com.zup.mercadolivre.compra.parte2;

import br.com.zup.mercadolivre.compra.Compra;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private StatusTransacao status;
    @Column(nullable = false)
    private String idTransacao;
    @Column(nullable = false)
    private LocalDateTime instante;
    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao() {
    }

    public Transacao(StatusTransacao status, @NotBlank String idTransacao, Compra compra) {
        this.status = status;
        this.idTransacao = idTransacao;
        this.compra = compra;
        this.instante = LocalDateTime.now();
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacao.sucesso);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return status == transacao.status && idTransacao.equals(transacao.idTransacao) && compra.equals(transacao.compra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, idTransacao, compra);
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", status=" + status +
                ", idTransacao='" + idTransacao + '\'' +
                ", instante=" + instante +
                '}';
    }
}
