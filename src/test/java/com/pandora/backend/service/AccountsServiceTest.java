package com.pandora.backend.service;

import com.pandora.backend.exception.NotFoundException;
import com.pandora.backend.repository.AccountsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class AccountsServiceTest {
    @Mock
    private AccountsRepository repository;

    @InjectMocks
    private AccountsService service;

    @Test
    public void shouldThrowExceptionWhenThereIsNoAccountWithGivenId() {
        //given
        final Integer accountId = Integer.valueOf(10);

        given(repository.findById(accountId)).willReturn(Optional.empty());

        //when
        catchException(() -> service.findById(accountId));

        //then
        assertThat(caughtException(), instanceOf(NotFoundException.class));
        then(repository).should().findById(accountId);
        verifyNoMoreInteractions(repository);
    }
}