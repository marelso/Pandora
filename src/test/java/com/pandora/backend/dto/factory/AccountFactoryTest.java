package com.pandora.backend.dto.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountFactoryTest {
    @InjectMocks
    private AccountFactory subject;

    @Test
    @DisplayName("")
    public void shouldCreateAccountDtoWhenAccountIsActive() {

    }
}