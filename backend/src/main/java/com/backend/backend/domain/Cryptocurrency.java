package com.backend.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cryptocurrency {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String abbreviation;
    private String icon;
    private Long currentValue;
    private Collection<Date> valuesHistory = new ArrayList<>();
}
