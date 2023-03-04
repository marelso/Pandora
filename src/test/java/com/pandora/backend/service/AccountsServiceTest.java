package com.pandora.backend.service;

import com.pandora.backend.domain.Account;
import com.pandora.backend.dto.AccountDto;
import com.pandora.backend.dto.factory.AccountFactory;
import com.pandora.backend.exception.NotFoundException;
import com.pandora.backend.fixture.AccountDtoFixture;
import com.pandora.backend.fixture.AccountFixture;
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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class AccountsServiceTest {
    @Mock
    private AccountsRepository repository;
    @Mock
    private AccountFactory factory;
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

    @Test
    public void shouldCreateAccountWithSuccess() {
        final Account dto = AccountFixture.get()
                .random()
                .build();
        final Account account = mock(Account.class);

        given(repository.save(dto)).willReturn(account);


        Account result = service.save(dto);


        assertThat(result, equalTo(account));
        then(repository).should().save(dto);
    }
}