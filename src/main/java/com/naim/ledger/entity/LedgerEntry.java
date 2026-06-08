package com.naim.ledger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


import java.math.BigDecimal;
import java.time.LocalDate;

import com.naim.ledger.others.EntryType;

@Entity
@Table(name = "ledger_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LedgerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long slip;

    private LocalDate entryDate;

    @Enumerated(EnumType.STRING)
    private EntryType type;

    private BigDecimal amount;

    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}