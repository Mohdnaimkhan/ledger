package com.naim.ledger.repo;

import com.naim.ledger.entity.LedgerEntry;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerEntryRepository
                extends JpaRepository<LedgerEntry, Long> {

        List<LedgerEntry> findByCustomerId(Long customerId);

        List<LedgerEntry> findByCustomerIdOrderByEntryDateDesc(Long customerId);

        List<LedgerEntry> findTop10ByOrderByIdDesc();

        long countByCustomerId(Long customerId);

        long countByCustomer_IdAndEntryDate(
                        Long customerId,
                        LocalDate entryDate);

        List<LedgerEntry> findByCustomer_IdAndDescriptionContainingIgnoreCase(
                        Long customerId,
                        String keyword);
}
