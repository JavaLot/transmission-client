package org.transmission.client.args;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface FieldsSelector {
    static List<String> getProperties(Class<?> clazz) {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.getSerializationConfig().introspect(mapper.getTypeFactory().constructType(clazz))
                .findProperties().stream().map(BeanPropertyDefinition::getName).sorted().collect(Collectors.toList());
    }

    @SafeVarargs
    static List<String> concat(List<String> ... lists) {
        return Stream.of(lists).flatMap(Collection::stream).sorted().distinct().collect(Collectors.toList());
    }
}
