package com.javidkhalilov.panacea.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "result", indexes = {
        @Index(name = "idx_result_analyse_id", columnList = "analyse_id")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal value;

    @Column(nullable = false)
    LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analyse_id", nullable = false, foreignKey = @ForeignKey(name = "fk_result_analysis"))
    Analysis analysis;

    @Column(length = 255)
    String comment;
}

