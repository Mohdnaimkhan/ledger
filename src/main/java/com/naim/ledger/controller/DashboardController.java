package com.naim.ledger.controller;

import com.naim.ledger.service.CustomerService;
import com.naim.ledger.service.LedgerEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

        private final CustomerService customerService;
        private final LedgerEntryService ledgerEntryService;

        @GetMapping("/")
        public String dashboard(Model model) {

                model.addAttribute(
                                "totalCustomers",
                                customerService.getAllCustomers().size());

                model.addAttribute(
                                "totalGiven",
                                ledgerEntryService.getTotalGiven());

                model.addAttribute(
                                "totalReceived",
                                ledgerEntryService.getTotalReceived());

                model.addAttribute(
                                "netBalance",
                                ledgerEntryService.getNetBalance());
                model.addAttribute(
                                "recentEntries",
                                ledgerEntryService.getRecentEntries());

                return "dashboard";
        }

}