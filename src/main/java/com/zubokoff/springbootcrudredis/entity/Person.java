package com.zubokoff.springbootcrudredis.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@AllArgsConstructor
@Data
@RedisHash(value = "persons", timeToLive = 100)
public class Person implements Serializable {

    @Id
    private Long id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("email")
    private String email;

    @JsonProperty("endereco")
    private Address address;
}
