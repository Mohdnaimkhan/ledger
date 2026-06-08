package com.naim.ledger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naim.ledger.entity.Settings;
import com.naim.ledger.repo.SettingsRepository;

@Service
public class SettingsServices {
    @Autowired
    private SettingsRepository settingsRepository;

    public Settings getSettings() {
        return settingsRepository.findById(1L).orElse(new Settings());
    }

    public Settings save(Settings settings) {
        settings.setId(1L);
        return settingsRepository.save(settings);
    }

}
