package com.rxjava3;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Supplier;

public class StepVerifierPractice {
    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then completes successfully.
    void expectFooBarComplete(Flux<String> flux) {
        StepVerifier.create(flux)
                .expectNext("foo")
                .expectNext("bar")
                .verifyComplete();
    }

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
    void expectFooBarError(Flux<String> flux) {
        StepVerifier.create(flux)
                .expectNext("foo")
                .expectNext("bar")
                .verifyError(RuntimeException.class);
    }

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits a User with "swhite"username
    // and another one with "jpinkman" then completes successfully.
    void expectSkylerJesseComplete(Flux<User> flux) {
        StepVerifier.create(flux)
                .assertNext(x->assertThat(x.getUsername()).isEqualTo("swhite"))
                .assertNext(x->assertThat(x.getUsername()).isEqualTo("jpinkman"))
                .verifyComplete();
    }

//========================================================================================

    // TODO Expect 10 elements then complete and notice how long the test takes.
    void expect10Elements(Flux<Long> flux) {
        StepVerifier.create(flux)
                .expectNextCount(10)
                .verifyComplete();
    }

//========================================================================================

    // TODO Expect 3600 elements at intervals of 1 second, and verify quicker than 3600s
    // by manipulating virtual time thanks to StepVerifier#withVirtualTime, notice how long the test takes
    void expect3600Elements(Supplier<Flux<Long>> supplier) {
        StepVerifier.withVirtualTime(supplier)
                .thenAwait(Duration.ofHours(1))
                .expectNextCount(3600)
                .verifyComplete();
    }

    void expectWithLongTime(){
        StepVerifier.withVirtualTime(() -> Mono.delay(Duration.ofHours(3)))
                .expectSubscription()
                .expectNoEvent(Duration.ofHours(2))
                .thenAwait(Duration.ofHours(1))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    private void fail() {
        throw new AssertionError("workshop not implemented");
    }
}
