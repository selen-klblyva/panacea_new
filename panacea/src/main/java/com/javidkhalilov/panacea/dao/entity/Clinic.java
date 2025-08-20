package com.javidkhalilov.panacea.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "clinic")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, length = 30)
    String name;

    @Column(name = "address", length = 50)
    String address;

    @Column(name = "contact_number", length = 12)
    String contactNumber;
}
