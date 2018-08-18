package com.example.pto6.ofc.service;

import android.util.LongSparseArray;

import com.example.pto6.ofc.model.Entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;

import lombok.Builder;
import lombok.Data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryStubTest {

    @Mock
    private LongSparseArray<MockEntity> longSparseArray;

    @Test
    public void saveTest() {
        RepositoryStub<MockEntity> repo = new RepositoryStub<>();
        repo.setEntities(longSparseArray);
        MockEntity mock = MockEntity.builder()
                .name("mock")
                .build();
        repo.save(mock);
        verify(longSparseArray, times(1)).append(1, mock);
    }

    @Test
    public void getOneTest() {
        RepositoryStub<MockEntity> repo = new RepositoryStub<>();
        repo.setEntities(longSparseArray);
        MockEntity mock = MockEntity.builder()
                .name("mock")
                .build();
        when(longSparseArray.get(1)).thenReturn(mock);
        MockEntity actual = repo.getOne(1);
        assertEquals(mock, actual);
    }

    @Test
    public void deleteTest() {
        RepositoryStub<MockEntity> repo = new RepositoryStub<>();
        repo.setEntities(longSparseArray);
        MockEntity mock = MockEntity.builder()
                .id(1)
                .name("mock")
                .build();
        when(longSparseArray.get(1)).thenReturn(mock);
        MockEntity actual = repo.delete(1);
        assertEquals(mock, actual);
        verify(longSparseArray, times(1)).delete(1);
    }

    @Test
    public void getAllTest() {
        RepositoryStub<MockEntity> repo = new RepositoryStub<>();
        repo.setEntities(longSparseArray);
        MockEntity mock0 = MockEntity.builder()
                .id(0)
                .name("mock")
                .build();
        MockEntity mock1 = MockEntity.builder()
                .id(1)
                .name("mock")
                .build();
        when(longSparseArray.size()).thenReturn(2);
        when(longSparseArray.get(0)).thenReturn(mock0);
        when(longSparseArray.get(1)).thenReturn(mock1);
        Collection<MockEntity> all = repo.getAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(mock0));
        assertTrue(all.contains(mock1));
    }

    @Data
    @Builder
    private static class MockEntity implements Entity {
        private long id;
        private String name;
    }
}