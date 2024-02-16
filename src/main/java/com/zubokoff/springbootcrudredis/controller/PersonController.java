package com.zubokoff.springbootcrudredis.controller;

import com.zubokoff.springbootcrudredis.entity.Person;
import com.zubokoff.springbootcrudredis.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public String save(@RequestBody Person person) {
        this.personService.save(person);
        return "ok";
    }

    @GetMapping("/keys")
    public List<String> getKeys() {
        return this.personService.getKeys();
    }
}
