/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.crac.mongocracdemo;

import java.util.List;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;

/**
 * @author Christoph Strobl
 * @since 2023/06
 */
//@RestController
public class MyController {

	PersonRepository repository;

	public MyController(PersonRepository repository) {
		this.repository = repository;
	}

//	@GetMapping(path = "init")
	public String init() {
		repository.deleteAll();
		repository.save(new Person("hans", "peter"));
		return "OK";
	}

//	@GetMapping(path = "hp")
	public List<Person> list() {
		return repository.findAll();
	}
}
