package org.example.bishopprototype;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commands")
public class CommandController {
    private final CommandService commandService;

    @PostMapping
    public ResponseEntity<String> addCommand(@Valid @RequestBody CommandDTO command) {
        commandService.executeCommand(command);
        return ResponseEntity.accepted().body("Command accepted");
    }

    @WeylandWatchingYou
    @GetMapping("/demo")
    public String demoMethod() {
        return "Audit demo successful";
    }
}