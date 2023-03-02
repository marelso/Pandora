package com.pandora.backend.fixture;

import com.pandora.backend.domain.Account;
import com.pandora.backend.domain.PermissionType;
import java.util.Random;

public class AccountFixture {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private PermissionType permissionType;
    private boolean hasToSetPassword = true;
    private boolean deleted;

    public static AccountFixture get() {
        return new AccountFixture();
    }

    private AccountFixture() {

    }

    public AccountFixture withId(Integer id) {
        this.id = id;
        return this;
    }

    public AccountFixture withUsername(String username) {
        this.username = username;
        return this;
    }

    public AccountFixture withName(String name) {
        this.name = name;
        return this;
    }

    public AccountFixture withEmail(String email) {
        this.email = email;
        return this;
    }

    public AccountFixture withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AccountFixture withPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
        return this;
    }

    public AccountFixture hasToSetPassword(Boolean hasToSetPassword) {
        this.hasToSetPassword = hasToSetPassword;
        return this;
    }

    public AccountFixture withPassword(String password) {
        this.password = password;
        return this;
    }

    public AccountFixture deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public Account build() {
        return new Account(
                id
                , username
                , email
                , password
                , name
                , lastName
                , permissionType
                , hasToSetPassword
                , deleted
        );
    }

    public AccountFixture random() {
        this.id = new Random().nextInt(100000);
        this.username = "user" + new Random().nextInt(1000);
        this.email = "user" + new Random().nextInt(1000) + "@example.com";
        this.password = generateRandomPassword();
        this.name = generateRandomName();
        this.lastName = generateRandomLastName();
        this.permissionType = generateRandomPermissionType();
        this.hasToSetPassword = new Random().nextBoolean();
        this.deleted = new Random().nextBoolean();

        return this;
    }

    private static String generateRandomPassword() {
        // generate a random password with 8 characters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            char c = (char) (new Random().nextInt(26) + 'a'); // generate a random lowercase letter
            sb.append(c);
        }
        return sb.toString();
    }

    private static String generateRandomName() {
        // generate a random name with 5 characters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char c = (char) (new Random().nextInt(26) + 'a'); // generate a random lowercase letter
            sb.append(c);
        }
        return sb.toString();
    }

    private static String generateRandomLastName() {
        // generate a random last name with 7 characters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            char c = (char) (new Random().nextInt(26) + 'a'); // generate a random lowercase letter
            sb.append(c);
        }
        return sb.toString();
    }

    private static PermissionType generateRandomPermissionType() {
        return PermissionType.values()[new Random().nextInt(PermissionType.values().length)];
    }
}