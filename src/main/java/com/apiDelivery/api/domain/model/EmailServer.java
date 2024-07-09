package com.apiDelivery.api.domain.model;

import com.apiDelivery.api.domain.model.dto.EmailServerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "emailServer")
public class EmailServer implements Serializable {


    @Id
    @EqualsAndHashCode.Include
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    private String protocolo;

    private String host;

    private Integer porta;

    //private String senha;

    private Boolean tlsSslHabilitado;

    public void atualizarEntityParaDTO(EmailServerDTO emailServerDTO) {
        emailServerDTO.atualizarEntity(this);
    }

}
