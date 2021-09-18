package com.javarx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class JavaRxApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaRxApplication.class, args);

		Flux<String> flux = Flux.just("A");
		Flux<String> flux2 = flux.map(s->"foo"+s);
		flux.subscribe(x-> System.out.println(x));
		flux2.subscribe(x-> System.out.println(x));
	}

}
