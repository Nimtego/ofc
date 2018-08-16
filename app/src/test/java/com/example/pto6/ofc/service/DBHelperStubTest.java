package com.example.pto6.ofc.service;

import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;

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
        DBHelperStub dbHelper = new DBHelperStub();
        dbHelper.setDebitRepository(debitRepository);
        List<Debit> mockList = Arrays.asList(Debit.builder().id(1).build(), Debit.builder().id(0).build());
        when(debitRepository.getAll()).thenReturn(mockList);
        List<Debit> actual = dbHelper.debitList();
        assertEquals(mockList, actual);
    }

    @Test
    public void creditList() {
        DBHelperStub dbHelper = new DBHelperStub();
        dbHelper.setCreditRepository(creditRepository);
        List<Credit> mockList = Arrays.asList(Credit.builder().id(1).build(), Credit.builder().id(0).build());
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
        DBHelperStub dbHelper = new DBHelperStub();
        dbHelper.setDebitRepository(debitRepository);
        Debit mock = Debit.builder().id(1).build();
        when(dbHelper.getDebitById(1)).thenReturn(mock);
        Debit actual = dbHelper.getDebitById(1);
        assertEquals(mock, actual);
    }

    @Test
    public void getCreditByID() {
        DBHelperStub dbHelper = new DBHelperStub();
        dbHelper.setCreditRepository(creditRepository);
        Credit mock = Credit.builder().id(1).build();
        when(dbHelper.getCreditByID(1)).thenReturn(mock);
        Credit actual = dbHelper.getCreditByID(1);
        assertEquals(mock, actual);
    }

    @Test
    public void putCredit() {
        DBHelperStub dbHelper = new DBHelperStub();
        dbHelper.setCreditRepository(creditRepository);
        Credit mock = Credit.builder().id(1).build();
        assertTrue(dbHelper.putCredit(mock));
        verify(creditRepository, times(1)).save(mock);
    }

    @Test
    public void putDebit() {
        DBHelperStub dbHelper = new DBHelperStub();
        dbHelper.setDebitRepository(debitRepository);
        Debit mock = Debit.builder().id(1).build();
        assertTrue(dbHelper.putDebit(mock));
        verify(debitRepository, times(1)).save(mock);
    }

    @Test
    public void removeDebit() {
        DBHelperStub dbHelper = new DBHelperStub();
        dbHelper.setDebitRepository(debitRepository);
        Debit mock = Debit.builder().id(1).build();
        dbHelper.removeDebit(mock);
        verify(debitRepository, times(1)).delete(1);
    }

    @Test
    public void removeByNameDebit() {
        DBHelperStub dbHelper = new DBHelperStub();
        dbHelper.setDebitRepository(debitRepository);
        Debit mock = Debit.builder().name("test").id(1).build();
        when(debitRepository.getAll()).thenReturn(Collections.singleton(mock));
        when(debitRepository.delete(1)).thenReturn(mock);
        dbHelper.removeByNameDebit("test");
        verify(debitRepository, times(1)).delete(1);
    }

    @Test
    public void removeCredit() {
        DBHelperStub dbHelper = new DBHelperStub();
        dbHelper.setCreditRepository(creditRepository);
        Credit mock = Credit.builder().id(1).build();
        dbHelper.removeCredit(mock);
        verify(creditRepository, times(1)).delete(1);
    }

    @Test
    public void removeByNameCredit() {
        DBHelperStub dbHelper = new DBHelperStub();
        dbHelper.setCreditRepository(creditRepository);
        Credit mock = Credit.builder().name("test").id(1).build();
        when(creditRepository.getAll()).thenReturn(Collections.singleton(mock));
        when(creditRepository.delete(1)).thenReturn(mock);
        dbHelper.removeByNameCredit("test");
        verify(creditRepository, times(1)).delete(1);
    }
}