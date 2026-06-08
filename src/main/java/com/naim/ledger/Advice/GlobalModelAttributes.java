package com.naim.ledger.Advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.naim.ledger.service.SettingsServices;


@ControllerAdvice

public class GlobalModelAttributes {
    @Autowired
    private SettingsServices settingsService;

    @ModelAttribute
    public void addGlobalAttributes(Model model) {

        model.addAttribute(
                "settings",
                settingsService.getSettings());
    }
}