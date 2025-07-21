package org.example.bishopprototype;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.synthetichumancorestarter.commands.CommandDTO;
import org.example.synthetichumancorestarter.commands.CommandService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/commands")
public class CommandController {

    private final CommandService commandService;

    @Autowired
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public ResponseEntity<String> addCommand(@Valid @RequestBody CommandDTO command) {
        commandService.executeCommand(command);
        return ResponseEntity.accepted().body("Command accepted");
    }
}