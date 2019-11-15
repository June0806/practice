package com.june.practice.service.impl;

import rx.Observable;
import rx.Subscriber;

public class rxJavaTest {
    public static void main(String[] args) {
        //发布事件
        Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello 测试1");
                subscriber.onNext("hello 测试2");
                subscriber.onCompleted();
            }
        });
        //订阅者
        Subscriber<String> stringSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String o) {
                System.out.println(o);
            }
        };
        //订阅事件
        stringObservable.subscribe(stringSubscriber);

    }
}
