package com.naim.ledger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.naim.ledger.entity.Customer;
import com.naim.ledger.service.CustomerService;
import com.naim.ledger.service.LedgerEntryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    private final LedgerEntryService ledgerEntryService;

    @GetMapping
    public String list(Model model) {

        model.addAttribute("customers", customerService.getAllCustomers());

        return "customer/list";
    }

    @GetMapping("/new")
    public String newCustomer(Model model) {

        model.addAttribute(
                "customer",
                new Customer());

        return "customer/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Customer customer) {

        customerService.save(customer);

        return "redirect:/customers";
    }

    // Details page for a customer
    @GetMapping("/{id}")
    public String details(
            @PathVariable Long id,
            Model model,
            @RequestParam(required = false) String keyword) {

        Customer customer = customerService.getById(id);

        model.addAttribute("customer", customer);

        model.addAttribute(
                "entries",
                ledgerEntryService.getEntryViews(id));
        model.addAttribute(
                "balance",
                ledgerEntryService.calculateBalance(id));
        model.addAttribute(
                "totalEntries",
                ledgerEntryService.getTotalEntries(id));
        model.addAttribute(
                "todayEntries",
                ledgerEntryService.getTodayEntries(id));
        model.addAttribute(
                "entries",
                ledgerEntryService.searchEntries(
                        id,
                        keyword));

        return "customer/details";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(
            @PathVariable Long id,
            Model model) {

        Customer customer = customerService.getById(id);

        model.addAttribute("customer", customer);

        return "customer/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(
            @PathVariable Long id) {

        customerService.deleteById(id);

        return "redirect:/customers";
    }
}