package org.example.bishopprototype;

import org.example.synthetichumancorestarter.androidaudit.WeylandWatchingYou;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @WeylandWatchingYou
    @GetMapping
    public String demoMethod(String param) {
        return "Audit demo successful with param: " + param;
    }
}