package org.transmission.client;

import java.util.ArrayList;

public class Records {
    public static <R extends Record> R updateNotNull(R template, R update) {
        try {
            var types = new ArrayList<Class<?>>();
            var values = new ArrayList<>();
            for (var component : template.getClass().getRecordComponents()) {
                types.add(component.getType());
                var overridden = component.getAccessor().invoke(update);
                values.add(overridden != null? overridden : component.getAccessor().invoke(template));
            }
            var canonical = template.getClass().getDeclaredConstructor(types.toArray(Class[]::new));
            @SuppressWarnings("unchecked")
            var result = (R) canonical.newInstance(values.toArray(Object[]::new));
            return result;
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Reflection failed: " + e, e);
        }
    }
}
