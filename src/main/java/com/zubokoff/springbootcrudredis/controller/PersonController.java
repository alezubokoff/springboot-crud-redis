package com.zubokoff.springbootcrudredis.controller;

import com.zubokoff.springbootcrudredis.dto.ResponseDefaultDto;
import com.zubokoff.springbootcrudredis.entity.Person;
import com.zubokoff.springbootcrudredis.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        var personCreated = this.personService.save(person);
        String url = "localhost:8080/persons/" + personCreated.getId();
        return ResponseEntity.created(URI.create(url)).body(personCreated);
    }

    @GetMapping("/keys")
    public ResponseEntity<List<String>> getKeys() {
        return ResponseEntity.of(Optional.ofNullable(this.personService.getKeys()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.personService.findByKey(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDefaultDto> delete(@PathVariable String id) {
        this.personService.delete(id);
        var response =  new ResponseDefaultDto("Pessoa: [" + id + "] deletada com sucesso");
        return ResponseEntity.ok(response);
    }
}
