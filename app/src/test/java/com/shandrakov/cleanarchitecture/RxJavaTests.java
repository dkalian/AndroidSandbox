package com.shandrakov.cleanarchitecture;

import android.util.Log;

import com.shandrakov.cleanarchitecture.functionals.ListUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.objectweb.asm.commons.StaticInitMerger;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.junit.Assert.assertTrue;


@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class RxJavaTests {

    enum SportType {
        RUNNING(1),
        SWIMMING(2),
        BOXING(3),
        SNOW_BOARDING(4),
        GYM(5);

        SportType(int priority) {
            _priority = priority;
        }

        public int priority() {
            return _priority;
        }

        private final int _priority;
    }
    @Test
    public void testObservable() {
        Observable<SportType> sportTypes = Observable.from(SportType.values());

        TestSubscriber<SportType> testSubscriber = TestSubscriber.create();

        sportTypes.subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();

        testSubscriber.assertNoErrors();
        testSubscriber.assertValues(SportType.values());
        testSubscriber.onCompleted();
    }
}
