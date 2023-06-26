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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author Christoph Strobl
 * @since 2023/06
 */
@Component
public class InfiniteReader implements CommandLineRunner, SmartLifecycle {

	PersonRepository repository;
	AtomicBoolean run = new AtomicBoolean(false);

	public InfiniteReader(PersonRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {

		if(!isRunning()) {
			run.set(true);
		}

		while (isRunning()) {
			System.out.println(repository.findAll());
			Thread.sleep(2000);
		}
	}

	@Override
	public void start() {
		try {
			run();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void stop() {
		run.set(false);
	}

	@Override
	public boolean isRunning() {
		return run.get();
	}
}
