package com.example.nursultan.testapp.network

import com.google.gson.*
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import java.lang.reflect.Type

class DateTimeConverter : JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

    override fun serialize(src: DateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val fmt = ISODateTimeFormat.dateTimeParser()
        return JsonPrimitive(fmt.print(src))
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): DateTime? {
        // Do not try to deserialize null or empty values
        if (json.asString == null || json.asString.isEmpty()) {
            return null
        }

        val fmt = ISODateTimeFormat.dateTimeParser()
        return fmt.parseDateTime(json.asString)
    }
}
