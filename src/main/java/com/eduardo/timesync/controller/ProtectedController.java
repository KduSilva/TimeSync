package com.eduardo.timesync.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping
public class ProtectedController {
    
    @RequestMapping("/admin/teste")
    public ResponseEntity<String>adminRoute(){
        return ResponseEntity.ok("Acesso liberado para ADMIN");
    }

    @RequestMapping("/lider/teste")
    public ResponseEntity<String>liderRoute(){
        return ResponseEntity.ok("Acesso liberado para LIDER ou ADMIN!");
    }

    public ResponseEntity<String>membroRoute(){
        return ResponseEntity.ok("Acesso liberado para MEMBRO, LIDER ou ADMIN!");
    }
}
