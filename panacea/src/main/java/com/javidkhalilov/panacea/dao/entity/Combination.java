package com.javidkhalilov.panacea.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "combination", indexes = {
        @Index(name = "idx_combination_clinic_id", columnList = "clinic_id")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Combination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, length = 50)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false, foreignKey = @ForeignKey(name = "fk_combination_clinic"))
    Clinic clinic;
}
