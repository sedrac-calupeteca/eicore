package com.eig.eicore.wrapper

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonCreator.Mode
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.io.Serializable

class PageableResponse<T> @JsonCreator(mode = Mode.PROPERTIES) constructor(
    @JsonProperty("content") content: List<T>?,
    @JsonProperty("number") number: Int,
    @JsonProperty("size") size: Int,
    @JsonProperty("totalElements") totalElements: Int,
    @param:JsonProperty("last") private val last: Boolean,
    @param:JsonProperty("first") private val first: Boolean,
    @param:JsonProperty("totalPages") private val totalPages: Int,
    @param:JsonProperty("numberOfElements") private val numberOfElements: Int,
    @JsonProperty("pageable") pageable: JsonNode?,
    @JsonProperty("sort") sort: JsonNode?
) : PageImpl<T>(content!!, PageRequest.of(number, size), totalElements.toLong()), Serializable{

    constructor(content: List<T>): this(content, 0,10, content.size,false,true,content.size / 10, 10, null,null)

}