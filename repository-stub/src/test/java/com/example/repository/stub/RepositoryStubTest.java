package com.example.repository.stub;

import com.example.repository.Entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;

import lombok.Builder;
import lombok.Data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class RepositoryStubTest {

    @Test
    public void saveAndGetAndDeleteTest() {
        RepositoryStub<MockEntity> repo = new RepositoryStub<>();
        MockEntity mock = MockEntity.builder().name("mock").build();
        repo.save(mock);
        assertNotNull(mock.getId());
        MockEntity get = repo.getOne(mock.id);
        assertEquals(mock, get);
        MockEntity delete = repo.delete(mock.id);
        assertEquals(mock, delete);
        MockEntity _null = repo.getOne(mock.id);
        assertNull(_null);
    }

    @Test
    public void getAllTest() {
        RepositoryStub<MockEntity> repo = new RepositoryStub<>();
        MockEntity mock0 = MockEntity.builder()
                .name("mock")
                .build();
        MockEntity mock1 = MockEntity.builder()
                .name("mock")
                .build();
        repo.save(mock0);
        repo.save(mock1);
        Collection<MockEntity> all = repo.getAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(mock0));
        assertTrue(all.contains(mock1));
    }

    @Data
    @Builder
    private static class MockEntity implements Entity {
        private Long id;
        private String name;
    }
}