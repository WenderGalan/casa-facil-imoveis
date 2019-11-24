package com.casafacilimoveis.model.entities.interceptor;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: Header.java
 * Criado por : Wender Galan
 * Data da criação : 10/11/2018
 * Observação :
 * *********************************************
 */
@Entity
@Table(name = "header", schema = "public")
@Data
@Builder
public class Header implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_header")
    @SequenceGenerator(name = "seq_header", sequenceName = "header_id_seq", schema = "public", allocationSize = 1)
    private Integer id;

    @Column(name = "key", nullable = false, length = 500)
    @NotNull
    private String key;

    @Column(name = "value", nullable = false, length = 500)
    @NotNull
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_crashlytics", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_header_crashlytics"))
    private Crashlytics crashlytics;
}
