package com.shandrakov.cleanarchitecture;

import com.shandrakov.cleanarchitecture.db.SqlUsersRepository;
import com.shandrakov.cleanarchitecture.db.entity.SqlUser;
import com.shandrakov.cleanarchitecture.db.specifications.AllRows;
import com.shandrakov.cleanarchitecture.functional.Maybe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import rx.observers.TestSubscriber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class SqlRepositoryTests {

    @Before
    public void init() {
        SqlUsersRepository repoExample = new SqlUsersRepository(RuntimeEnvironment.application);
        repoExample.remove(new AllRows());
    }

    @Test
    public void crudTest() {
        SqlUsersRepository repoExample = new SqlUsersRepository(RuntimeEnvironment.application);

        SqlUser user = new SqlUser(
                Maybe.nothing(),
                Maybe.nothing(),
                "firstName",
                "lastName",
                "email");

        repoExample.add(user);

        TestSubscriber<List<SqlUser>> testSubscriber = new TestSubscriber<>();
        repoExample.query(new AllRows())
                .toBlocking()
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();

        List<SqlUser> users = testSubscriber.getOnNextEvents().get(0);

        assertFalse(users.isEmpty());

        SqlUser changedUser = new SqlUser(
                users.get(0).id(),
                Maybe.value("server_id"),
                "name",
                "last",
                "email");

        repoExample.update(changedUser);

        repoExample.query(new AllRows())
                .toBlocking()
                .subscribe(list -> {
                    SqlUser updatedUser = list.get(0);

                    assertTrue(updatedUser.id().value().equals(changedUser.id().value()));
                    assertTrue(updatedUser.email().equals(changedUser.email()));
                    assertTrue(updatedUser.firstName().equals(changedUser.firstName()));
                    assertTrue(updatedUser.lastName().equals(changedUser.lastName()));
                });

    }
}
