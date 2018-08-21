package com.example.repository.sqlite;

import com.example.repository.Entity;

import org.junit.Test;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertEquals;

@Slf4j
public class GsonConverterTest {

    @Test
    public void serializeDeserializeTest() {
        MockEntity entity = MockEntity.builder()
                .id(1L)
                .name("Name")
                .value(42.00000001D)
                .date(new Date().getTime())
                .build();
        GsonConverter<MockEntity> converter = GsonConverter.forEntity(MockEntity.class);
        String serialized = converter.serialize(entity);
        log.debug("Serialized entity: {}", serialized);
        MockEntity deserialized = converter.deserialize(serialized);
        log.debug("Deserialized entity: {}", deserialized);
        assertEquals(entity.getId(), deserialized.getId());
        assertEquals(entity.getName(), deserialized.getName());
        assertEquals(entity.getValue(), deserialized.getValue());
        assertEquals(entity.getDate(), deserialized.getDate());
        assertEquals(entity, deserialized);
    }

    @Data
    @Builder
    private static class MockEntity implements Entity {
        private Long id;
        private String name;
        private Double value;
        private Long date;
    }
}