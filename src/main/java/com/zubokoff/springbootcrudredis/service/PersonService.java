package com.zubokoff.springbootcrudredis.service;

import com.zubokoff.springbootcrudredis.entity.Person;
import com.zubokoff.springbootcrudredis.respository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public void save(Person person) {
        this.personRepository.save(person);
    }

    public List<String> getKeys() {
        var resultKeys = this.redisTemplate.keys("persons:*");
        if (resultKeys != null) {
            List<String> keys = new ArrayList<>(resultKeys);
            return keys;
        }
        return null;
    }
}
