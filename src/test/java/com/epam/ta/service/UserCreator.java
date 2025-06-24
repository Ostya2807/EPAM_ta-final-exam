package com.epam.ta.service;

import com.epam.ta.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserCreator {
    public static final String TESTDATA_USER_NAME = "testdata.user.name";
    public static final String TESTDATA_USER_PASSWORD = "testdata.user.password";

    public static User withCredentialsFromProperty(){
        return new User(TestDataReader.getTestData(TESTDATA_USER_NAME),
                TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }
    public static User withEmptyUsername(){
        return new User("", TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }

    public static User withEmptyPassword(){
        return new User(TestDataReader.getTestData(TESTDATA_USER_NAME), "");
    }

    public static List<User> getAllUsersFromProperty() {
        String usernames = TestDataReader.getTestData("testdata.user.names");
        String password = TestDataReader.getTestData("testdata.user.password");
        String[] usernameArray = usernames.split(",");
        List<User> users = new ArrayList<>();
        for (String username : usernameArray) {
            users.add(new User(username.trim(), password));
        }
        return users;
    }
}
