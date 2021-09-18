package com.rxjava2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@SpringBootApplication
public class RxJava2Application {

	public static void main(String[] args) {
		SpringApplication.run(RxJava2Application.class, args);

		Mono.just(1l)
			.map(x -> x*2)
			.or(Mono.delay(Duration.ofMillis(100)))
			.subscribe(System.out::println);
	}
}
