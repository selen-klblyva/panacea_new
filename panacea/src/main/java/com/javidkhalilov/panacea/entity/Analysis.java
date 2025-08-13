package com.javidkhalilov.panacea.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "analysis")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, length = 50)
    String name;

    @Column(name = "unit", nullable = false, length = 10)
    String unit;

    @Column(name = "normal_range_min", precision = 10, scale = 2)
    BigDecimal normalRangeMin;

    @Column(name = "normal_range_max", precision = 10, scale = 2)
    BigDecimal normalRangeMax;
}
