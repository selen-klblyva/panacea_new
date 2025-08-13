package com.javidkhalilov.panacea.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CombinationAnalysisId implements Serializable {
    Long combination;
    Long analysis;
}
