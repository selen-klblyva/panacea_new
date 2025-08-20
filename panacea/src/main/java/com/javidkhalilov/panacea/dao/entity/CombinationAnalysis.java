package com.javidkhalilov.panacea.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@IdClass(CombinationAnalysisId.class)
@Table(
        name = "combination_analysis",
        indexes = {
                @Index(name = "idx_combination_analysis_combo", columnList = "combination_id"),
                @Index(name = "idx_combination_analysis_analysis", columnList = "analysis_id")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CombinationAnalysis {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "combination_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_combination_analysis__combination")
    )
    Combination combination;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "analysis_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_combination_analysis__analysis")
    )
    Analysis analysis;
}