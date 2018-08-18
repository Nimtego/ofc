package com.example.pto6.ofc.service;

import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.repository.Repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DBHelperStubTest {

    @Mock
    private Repository<Debit> debitRepository;

    @Mock
    private Repository<Credit> creditRepository;

    @Test
    public void debitListTest() {
        DBHelperStub dbHelper = new DBHelperStub(debitRepository, creditRepository);
        List<Debit> mockList = Arrays.asList(Debit.builder().id(1L).build(), Debit.builder().id(0L).build());
        when(debitRepository.getAll()).thenReturn(mockList);
        List<Debit> actual = dbHelper.debitList();
        assertEquals(mockList, actual);
    }

    @Test
    public void creditList() {
        DBHelperStub dbHelper = new DBHelperStub(debitRepository, creditRepository);
        List<Credit> mockList = Arrays.asList(Credit.builder().id(1L).build(), Credit.builder().id(0L).build());
        when(creditRepository.getAll()).thenReturn(mockList);
        List<Credit> actual = dbHelper.creditList();
        assertEquals(mockList, actual);
    }

    @Test
    public void putDebitList() {
    }

    @Test
    public void putCreditList() {
    }

    @Test
    public void getDebitById() {
        DBHelperStub dbHelper = new DBHelperStub(debitRepository, creditRepository);
        Debit mock = Debit.builder().id(1L).build();
        when(dbHelper.getDebitById(1L)).thenReturn(mock);
        Debit actual = dbHelper.getDebitById(1L);
        assertEquals(mock, actual);
    }

    @Test
    public void getCreditByID() {
        DBHelperStub dbHelper = new DBHelperStub(debitRepository, creditRepository);
        Credit mock = Credit.builder().id(1L).build();
        when(dbHelper.getCreditByID(1L)).thenReturn(mock);
        Credit actual = dbHelper.getCreditByID(1L);
        assertEquals(mock, actual);
    }

    @Test
    public void putCredit() {
        DBHelperStub dbHelper = new DBHelperStub(debitRepository, creditRepository);
        Credit mock = Credit.builder().id(1L).build();
        assertTrue(dbHelper.putCredit(mock));
        verify(creditRepository, times(1)).save(mock);
    }

    @Test
    public void putDebit() {
        DBHelperStub dbHelper = new DBHelperStub(debitRepository, creditRepository);
        Debit mock = Debit.builder().id(1L).build();
        assertTrue(dbHelper.putDebit(mock));
        verify(debitRepository, times(1)).save(mock);
    }

    @Test
    public void removeDebit() {
        DBHelperStub dbHelper = new DBHelperStub(debitRepository, creditRepository);
        Debit mock = Debit.builder().id(1L).build();
        dbHelper.removeDebit(mock);
        verify(debitRepository, times(1)).delete(1L);
    }

    @Test
    public void removeByNameDebit() {
        DBHelperStub dbHelper = new DBHelperStub(debitRepository, creditRepository);
        Debit mock = Debit.builder().name("test").id(1L).build();
        when(debitRepository.getAll()).thenReturn(Collections.singleton(mock));
        when(debitRepository.delete(1L)).thenReturn(mock);
        dbHelper.removeByNameDebit("test");
        verify(debitRepository, times(1)).delete(1L);
    }

    @Test
    public void removeCredit() {
        DBHelperStub dbHelper = new DBHelperStub(debitRepository, creditRepository);
        Credit mock = Credit.builder().id(1L).build();
        dbHelper.removeCredit(mock);
        verify(creditRepository, times(1)).delete(1L);
    }

    @Test
    public void removeByNameCredit() {
        DBHelperStub dbHelper = new DBHelperStub(debitRepository, creditRepository);
        Credit mock = Credit.builder().name("test").id(1L).build();
        when(creditRepository.getAll()).thenReturn(Collections.singleton(mock));
        when(creditRepository.delete(1L)).thenReturn(mock);
        dbHelper.removeByNameCredit("test");
        verify(creditRepository, times(1)).delete(1L);
    }
}