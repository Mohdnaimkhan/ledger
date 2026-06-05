package com.naim.ledger.dto;

import com.naim.ledger.others.EntryType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EntryView {
    private long id;
    private LocalDate entryDate;
    private EntryType type;
    private BigDecimal amount;
    private String description;
    private BigDecimal balance;
}