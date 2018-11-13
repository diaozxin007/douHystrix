package com.xilidou;


import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

public class Commond {

	public static void main(String[] args) {

		List<String> list = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			list.add("hello i:" + i);
		}

		String[] objects = list.toArray(new String[0]);

		Flowable.fromArray(objects).window(4).subscribe(new Subscriber<Flowable<String>>() {

			Subscription subscription;

			@Override
			public void onSubscribe(Subscription s) {
				subscription = s;
				subscription.request(1);
			}

			@Override
			public void onNext(Flowable<String> stringFlowable) {
				subscription.request(1);
				stringFlowable.subscribe(new Subscriber<String>() {

					Subscription subscription;

					@Override
					public void onSubscribe(Subscription s) {
						subscription = s;
						subscription.request(1);
					}

					@Override
					public void onNext(String s) {
						subscription.request(1);
						System.out.println(s);
					}

					@Override
					public void onError(Throwable t) {

					}

					@Override
					public void onComplete() {

					}
				});
			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onComplete() {

			}
		});
	}

}
