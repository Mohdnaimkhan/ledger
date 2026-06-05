package com.naim.ledger.service;

import com.naim.ledger.dto.*;
import com.naim.ledger.entity.LedgerEntry;
import com.naim.ledger.others.EntryType;
import com.naim.ledger.repo.LedgerEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LedgerEntryService {

    private final LedgerEntryRepository ledgerEntryRepository;

    public LedgerEntry save(LedgerEntry ledgerEntry) {
        return ledgerEntryRepository.save(ledgerEntry);
    }

    public List<LedgerEntry> getAllEntries() {
        return ledgerEntryRepository.findAll();
    }

    public List<LedgerEntry> getEntriesByCustomerId(Long customerId) {
        return ledgerEntryRepository.findByCustomerIdOrderByEntryDateDesc(customerId);
    }

    public BigDecimal calculateBalance(Long customerId) {

        List<LedgerEntry> entries = ledgerEntryRepository.findByCustomerId(customerId);

        BigDecimal balance = BigDecimal.ZERO;

        for (LedgerEntry entry : entries) {

            if (entry.getType() == EntryType.GIVEN) {
                balance = balance.add(entry.getAmount());
            } else {
                balance = balance.subtract(entry.getAmount());
            }
        }

        return balance;
    }

    public List<EntryView> getEntryViews(Long customerId) {

        List<LedgerEntry> entries = ledgerEntryRepository.findByCustomerId(customerId);

        BigDecimal runningBalance = BigDecimal.ZERO;

        List<EntryView> result = new ArrayList<>();

        for (LedgerEntry entry : entries) {

            if (entry.getType() == EntryType.GIVEN) {
                runningBalance = runningBalance.add(entry.getAmount());
            } else {
                runningBalance = runningBalance.subtract(entry.getAmount());
            }

            result.add(
                    new EntryView(
                            entry.getId(),
                            entry.getEntryDate(),
                            entry.getType(),
                            entry.getAmount(),
                            entry.getDescription(),
                            runningBalance));
        }

        return result;
    }

    public LedgerEntry getById(Long id) {
        return ledgerEntryRepository.findById(id)
                .orElseThrow();
    }

    public void deleteById(Long id) {
        ledgerEntryRepository.deleteById(id);
    }

    public BigDecimal getTotalGiven() {

        return ledgerEntryRepository.findAll()
                .stream()
                .filter(e -> e.getType() == EntryType.GIVEN)
                .map(LedgerEntry::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalReceived() {

        return ledgerEntryRepository.findAll()
                .stream()
                .filter(e -> e.getType() == EntryType.RECEIVED)
                .map(LedgerEntry::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getNetBalance() {

        return getTotalGiven()
                .subtract(getTotalReceived());
    }

    public List<LedgerEntry> getRecentEntries() {
        return ledgerEntryRepository
                .findTop10ByOrderByIdDesc();
    }

    public long getTotalEntries(Long customerId) {
        return ledgerEntryRepository.countByCustomerId(customerId);
    }

    public long getTodayEntries(Long customerId) {

        return ledgerEntryRepository
                .countByCustomer_IdAndEntryDate(
                        customerId,
                        LocalDate.now());
    }

    public List<LedgerEntry> searchEntries(
            Long customerId,
            String keyword) {

        if (keyword == null || keyword.isBlank()) {

            return ledgerEntryRepository
                    .findByCustomerIdOrderByEntryDateDesc(customerId);
        }

        return ledgerEntryRepository
                .findByCustomer_IdAndDescriptionContainingIgnoreCase(
                        customerId,
                        keyword);
    }
}