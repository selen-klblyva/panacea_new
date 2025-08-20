package com.javidkhalilov.panacea.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "uploaded_file")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UploadedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String filename;

    @Column(nullable = false)
    String url;
}