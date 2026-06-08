package com.naim.ledger.controller;

import com.naim.ledger.service.CustomerService;
import com.naim.ledger.service.LedgerEntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class DashboardController {
        
        @Autowired
        private  CustomerService customerService;
        @Autowired
        private  LedgerEntryService ledgerEntryService;

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

        @GetMapping("/about")
        public String getAbout() {
                return "about";
        }

}