package com.shandrakov.cleanarchitecture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import rx.Observable;
import rx.observers.TestSubscriber;

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
