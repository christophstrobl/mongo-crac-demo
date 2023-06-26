package com.example.crac.mongocracdemo;

import org.springframework.data.repository.ListCrudRepository;

/**
 * @author Christoph Strobl
 */
public interface PersonRepository extends ListCrudRepository<Person, String> {

}
