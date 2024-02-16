package com.zubokoff.springbootcrudredis.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@AllArgsConstructor
@Data
@RedisHash
public class Address implements Serializable {

    @JsonProperty("logradouro")
    private String street;
    @JsonProperty("cidade")
    private String city;
    @JsonProperty("estado")
    private String state;
    @JsonProperty("cep")
    private String zipCode;
}
