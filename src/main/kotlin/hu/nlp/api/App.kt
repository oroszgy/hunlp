package hu.nlp.api

import com.fasterxml.jackson.databind.ObjectMapper
import spark.Spark.*

/**
 * Created by gorosz on 2017. 05. 29..
 */

fun main(args: Array<String>) {
    val nlp = HuNlp()
    val om = ObjectMapper()

    threadPool(12)
    port(9090)

    post("/v1/annotate", "application/json", { request, _ ->
        val body: String = request.body()
        val text: String = om.readTree(body).get("text").asText()
        val doc: Document = nlp(text)
        om.writeValueAsString(doc)
    })

    post("/v1/entities", "application/json", { request, _ ->
        val body: String = request.body()
        val text: String = om.readTree(body).get("text").asText()
        val doc: Document = nlp.entities(text)
        om.writeValueAsString(doc)
    })
}