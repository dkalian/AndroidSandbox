package com.shandrakov.sandbox;

import android.content.Context;

import com.shandrakov.sandbox.db.SqlUsersRepository;
import com.shandrakov.sandbox.db.entity.SqlUser;
import com.shandrakov.sandbox.db.specification.AllRows;
import com.shandrakov.sandbox.functional.Maybe;
import com.shandrakov.sandbox.repository.Repository;
import com.shandrakov.sandbox.screens.user.entity.UserName;
import com.shandrakov.sandbox.screens.user.show.UserListView;
import com.shandrakov.sandbox.screens.user.show.UsersPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class UserPresentersTest {

    @Before
    public void init() {
        Repository<SqlUser> userRepository = new SqlUsersRepository(RuntimeEnvironment.application);
        userRepository.remove(new AllRows());
    }

    @Test
    public void createUserTest() {
        Repository<SqlUser> userRepository = new SqlUsersRepository(RuntimeEnvironment.application);

        String email = "email@email.com";
        String firstName = "Valera";
        String lastName = "Chernojukov";

        SqlUser user = new SqlUser(
                Maybe.nothing(),
                Maybe.nothing(),
                firstName,
                lastName,
                email);

        userRepository.add(user);

        final List<UserName> users = new ArrayList<>();
        UserListView userListView = new UserListView() {

            @Override
            public void removeUserInPosition(UserName userName, int position) {
                users.remove(userName);
            }

            @Override
            public void addUser(UserName userName) {
                users.add(userName);
            }

            @Override
            public Context context() {
                return RuntimeEnvironment.application;
            }
        };

        UsersPresenter usersPresenter = new UsersPresenter(userListView);

        usersPresenter.onStarted();

        assertFalse(users.isEmpty());

        usersPresenter.onUserItemSwiped(users.get(0), 0);

        assertTrue(users.isEmpty());
    }
}
