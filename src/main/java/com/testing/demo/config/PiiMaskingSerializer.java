package com.testing.demo.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PiiMaskingSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null || value.isEmpty()) {
            gen.writeString(value);
            return;
        }

        if (value.contains("@")) {
            int atIndex = value.indexOf("@");
            String masked = value.charAt(0) + "***" + value.substring(atIndex);
            gen.writeString(masked);
        } else {
            gen.writeString("********");
        }
    }
}