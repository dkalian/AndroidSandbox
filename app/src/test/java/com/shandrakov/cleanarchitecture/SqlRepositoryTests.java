package com.shandrakov.cleanarchitecture;

import com.shandrakov.cleanarchitecture.db.SqlRepoExample;
import com.shandrakov.cleanarchitecture.db.entity.SqlUser;
import com.shandrakov.cleanarchitecture.db.specifications.AllRows;
import com.shandrakov.cleanarchitecture.functionals.Maybe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import rx.observers.TestSubscriber;

import static org.junit.Assert.assertFalse;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class SqlRepositoryTests {

    @Test
    public void crudTest() {
        SqlRepoExample repoExample = new SqlRepoExample(RuntimeEnvironment.application);

        SqlUser user = new SqlUser(
                Maybe.nothing(),
                Maybe.nothing(),
                "firstName",
                "lastName",
                "email");

        repoExample.add(user);

        TestSubscriber<List<SqlUser>> testSubscriber = new TestSubscriber<>();
        repoExample.query(new AllRows())
                .doOnNext(users -> {
                    users.size();
                })
                .toBlocking()
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();

        List<SqlUser> users = testSubscriber.getOnNextEvents().get(0);

        assertFalse(users.isEmpty());
    }
}
