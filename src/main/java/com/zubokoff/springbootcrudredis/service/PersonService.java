package com.zubokoff.springbootcrudredis.service;

import com.zubokoff.springbootcrudredis.entity.Person;
import com.zubokoff.springbootcrudredis.respository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public Person save(Person person) {
        return this.personRepository.save(person);
    }

    public List<String> getKeys() {
        var resultKeys = this.redisTemplate.keys("persons:*");
        if (resultKeys != null) {
            List<String> keys = new ArrayList<>(resultKeys);
            return keys;
        }
        return null;
    }

    public Person findByKey(String id) {
        Optional<Person> person = this.personRepository.findById(id);
        return person.orElse(null);
    }

    public void delete(String id) {
        this.personRepository.deleteById(id);
    }
}
