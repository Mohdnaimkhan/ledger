package com.naim.ledger;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BrowserLauncher {

    @SuppressWarnings("deprecation")
	@EventListener(ApplicationReadyEvent.class)
    public void openBrowser() {
        try {
            Thread.sleep(5000); // wait for server

            Runtime.getRuntime().exec("cmd /c start http://localhost:8080");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}