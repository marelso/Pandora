package com.pandora.backend.fixture;

import com.pandora.backend.domain.PermissionType;
import com.pandora.backend.dto.AccountDto;

import java.util.Random;

public class AccountDtoFixture {
    private Integer id;
    private String username;
    private String email;
    private String name;
    private String lastName;
    private PermissionType permissionType;
    private boolean hasToSetPassword = true;
    private boolean deleted;

    public static AccountDtoFixture get() {
        return new AccountDtoFixture();
    }

    private AccountDtoFixture() {

    }

    public AccountDtoFixture withId(Integer id) {
        this.id = id;
        return this;
    }

    public AccountDtoFixture withUsername(String username) {
        this.username = username;
        return this;
    }

    public AccountDtoFixture withName(String name) {
        this.name = name;
        return this;
    }

    public AccountDtoFixture withEmail(String email) {
        this.email = email;
        return this;
    }

    public AccountDtoFixture withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AccountDtoFixture withPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
        return this;
    }

    public AccountDtoFixture hasToSetPassword(Boolean hasToSetPassword) {
        this.hasToSetPassword = hasToSetPassword;
        return this;
    }

    public AccountDtoFixture deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public AccountDto build() {
        return new AccountDto(
                id
                , username
                , email
                , name
                , lastName
                , permissionType
                , hasToSetPassword
                , deleted
        );
    }

    public AccountDtoFixture random() {
        this.id = new Random().nextInt(100000);
        this.username = "user" + new Random().nextInt(1000);
        this.email = "user" + new Random().nextInt(1000) + "@example.com";
        this.name = generateRandomName();
        this.lastName = generateRandomLastName();
        this.permissionType = generateRandomPermissionType();
        this.hasToSetPassword = new Random().nextBoolean();
        this.deleted = new Random().nextBoolean();

        return this;
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
