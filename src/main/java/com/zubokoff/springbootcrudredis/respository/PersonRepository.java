package com.zubokoff.springbootcrudredis.respository;

import com.zubokoff.springbootcrudredis.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
}
