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
    @Column(name = "id_reseña")
    private Integer id_reseña;

    @Column(name = "f_publicacion")
    private LocalDate f_publicacion = LocalDate.now();

    @Column(name = "comentario", length = 500)
    private String comentario;

    @Column(name = "calificacion")
    private Integer calificacion; // 1..5

    @Column(name = "f_baneo")
    private LocalDate f_baneo;

    @Column(name = "motivo_baneo", length = 300)
    private String motivo_baneo;
}
