package com.naim.ledger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naim.ledger.entity.Settings;
import com.naim.ledger.service.SettingsServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

@Controller

@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private SettingsServices settingsServices;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("settings", settingsServices.getSettings());
        return "settings/form";
    }

    @PostMapping("/save")
    public String saveString(@ModelAttribute Settings settings) {
        settingsServices.save(settings);

        return "redirect:/settings";
    }

    @GetMapping("/settings/details")
    public String getSeetings(Model model) {
        model.addAttribute("settings", settingsServices.getSettings());
        return "settings/details";
    }

}
