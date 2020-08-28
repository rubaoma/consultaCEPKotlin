package com.example.consultacepkotlin

import com.google.gson.*
import java.lang.reflect.Type
import kotlin.jvm.Throws

class CEPDeserializer : JsonDeserializer<Any?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Any? {
        var element: JsonElement? = json.asJsonObject
        if (json.asJsonObject != null) {
            element = json.asJsonObject
        }
        return Gson().fromJson(element, CEP::class.java)
    }
}