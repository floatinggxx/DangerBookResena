package com.DangerBook.Resena.API.Resena.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "resena")
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private Integer idResena;

    @Column(name = "f_publicacion")
    private LocalDate fPublicacion;

    @Column(name = "comentario", length = 500)
    private String comentario;

    @Column(name = "calificacion")
    private Integer calificacion;

    @Column(name = "f_baneo")
    private LocalDate fBaneo;

    @Column(name = "motivo_baneo", length = 300)
    private String motivoBaneo;
}