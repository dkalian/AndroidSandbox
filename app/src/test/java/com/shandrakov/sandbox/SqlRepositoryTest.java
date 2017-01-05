package com.shandrakov.sandbox;

import com.shandrakov.sandbox.db.SqlUsersRepository;
import com.shandrakov.sandbox.db.entity.SqlUser;
import com.shandrakov.sandbox.db.specification.AllRows;
import com.shandrakov.sandbox.functional.Maybe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class SqlRepositoryTest {

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

        List<SqlUser> users = repoExample.query(new AllRows());

        assertFalse(users.isEmpty());

        SqlUser changedUser = new SqlUser(
                users.get(0).id(),
                Maybe.value("server_id"),
                "name",
                "last",
                "email");

        repoExample.update(changedUser);

        assertFalse(repoExample.query(new AllRows()).isEmpty());
    }
}
