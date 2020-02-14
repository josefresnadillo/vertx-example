package com.example.starter.rxjavaexamples;

import io.reactivex.*;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

public class RxjavaExamples {

    @Test
    public void test1() {
        System.out.println("test1");
        Observable.range(1, 10)
                .observeOn(Schedulers.computation())
                .subscribe(this::compute);
    }

    @Test
    public void tests2() {
        FlowableOnSubscribe<Integer> flowableOnSubscribe = flowable -> flowable.onNext(1);
        Flowable<Integer> integerFlowable = Flowable.create(flowableOnSubscribe, BackpressureStrategy.BUFFER);
        integerFlowable.subscribe(this::compute);
        integerFlowable.subscribe(this::compute);
    }

    @Test
    public void parallelExecution1() throws Exception {
        System.out.println("Thread: " + Thread.currentThread().getName());
        System.out.println("Number of processors: " + Runtime.getRuntime().availableProcessors());

        Observable<Integer> vals = Observable.range(1, 3);

        vals.flatMap(val -> Observable.just(val)
                .subscribeOn(Schedulers.computation())
                .map(i -> this.compute(i))
        ).subscribe(val -> System.out.println("Final Thread: " + Thread.currentThread().getName()));

        Thread.sleep(20000);
    }

    @Test
    public void parallelExecution2() throws Exception {
        System.out.println("Thread: " + Thread.currentThread().getName());
        System.out.println("Number of processors: " + Runtime.getRuntime().availableProcessors());

        Flowable.just("Some string", "Some other string")
                .parallel()
                .runOn(Schedulers.computation())
                .map(str -> {
                    System.out.println("Map 1 Thread: " + Thread.currentThread().getName());
                    return str.length();
                })
                .map(length -> {
                    System.out.println("Map 2 Thread: " + Thread.currentThread().getName());
                    return this.compute(length);
                })
                .sequential()
                .subscribe(val -> System.out.println(Thread.currentThread().getName()));

        Thread.sleep(20000);
    }

    private Integer compute(Integer v) {
        try {
            System.out.println("compute integer v: " + v + " in thread: " + Thread.currentThread().getName());
            Thread.sleep(10000);
            System.out.println("Done in thread: " + Thread.currentThread().getName());
            return v + 10;
        } catch (Exception e) {
            e.printStackTrace();
            return v + 10;
        }
    }
}
