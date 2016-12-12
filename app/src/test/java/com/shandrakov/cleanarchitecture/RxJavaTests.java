package com.shandrakov.cleanarchitecture;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.objectweb.asm.commons.StaticInitMerger;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;


@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class RxJavaTests {

    @Test
    public void testObservable() {
        final Observable<String> myObservable = Observable.just("value1", "value2");

        final Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted() called");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: e = [" + e + "]");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext() called with: s = [" + s + "]");
            }
        };

        final Action1<String> onNext = s -> Log.d(TAG, "next(" + s + ")");
        final Action1<Throwable> onError = s -> Log.d(TAG, "on error(" + s + ")");
        final Action0 onComplete = () -> Log.d(TAG, "on complete");

        myObservable.subscribe(mySubscriber);
        myObservable.subscribe(onNext, onError, onComplete);
    }

    private static final String TAG = RxJavaTests.class.getSimpleName();
}
