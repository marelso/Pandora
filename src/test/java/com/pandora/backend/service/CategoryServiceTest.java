package com.pandora.backend.service;

import com.pandora.backend.domain.Category;
import com.pandora.backend.exception.NotFoundException;
import com.pandora.backend.repository.CategoryRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository repository;
    @InjectMocks
    private CategoryService subject;

    @Test
    public void shouldThrowExceptionWhenThereIsNoAccountWithGivenId() {
        //given
        final Integer categoryId = Integer.valueOf(10);

        given(repository.findById(categoryId)).willReturn(Optional.empty());


        //when
        catchException(() -> subject.findById(categoryId));


        //then
        assertThat(caughtException(), instanceOf(NotFoundException.class));
        then(repository).should().findById(categoryId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void shouldDeleteAccountWithGivenId() {
        //given
        final Integer categoryId = Integer.valueOf(10);


        //when
        subject.delete(categoryId);


        //then
        then(repository).should().softDelete(categoryId);
        verify(repository).softDelete(categoryId);
    }

    @Test
    public void shouldReturnExpectedCategoryWhenSearchingById() {
        //given
        final Integer categoryId = 1;
        final Category expected = mock(Category.class);
        given(repository.findById(categoryId)).willReturn(Optional.of(expected));


        //when
        final Category category = subject.findById(categoryId);


        //then
        assertThat(category, equalTo(expected));
        then(repository).should().findById(categoryId);
    }
}