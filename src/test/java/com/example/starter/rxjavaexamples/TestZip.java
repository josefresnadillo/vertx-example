package com.example.starter.rxjavaexamples;

import io.reactivex.Observable;
import io.reactivex.Single;

public class TestZip {

    public static void main(String args[]) {

        Observable<String> todoObservable = Observable.create(emitter -> {
            try {
                System.out.println("Emmiting");
                emitter.onNext("a");
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });

        Single<String> originalSingle = Single.fromObservable(todoObservable);

        Single<String> mapX = originalSingle.map(original -> original + " X");
        Single<String> mapY = originalSingle.map(original -> original + " Y");
        Single<String> masXmergeMasY = mapX.zipWith(mapY, (original, mapped) -> "Whatever ");

        System.out.println(masXmergeMasY.blockingGet());
    }
}