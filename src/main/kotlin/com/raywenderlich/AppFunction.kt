package com.raywenderlich;

import com.fasterxml.jackson.databind.ObjectMapper
import com.faunadb.client.FaunaClient
import io.micronaut.function.executor.FunctionInitializer
import io.micronaut.function.FunctionBean;
import javax.inject.*;
import java.util.function.Function;

@FunctionBean("app")
class AppFunction() : FunctionInitializer(), Function<Payload, HandleOutPut> {

    val postService: PostService = PostService()

    override fun apply(payload: Payload): HandleOutPut {
        var serverClient = FaunaClient.builder()
                .withSecret("fnADq_1T0DACAH0Ut_hKsEOWXwMyQSRbaxLsFZCp")
                .build()
        val objectMapper = ObjectMapper()
        val post = try {
            objectMapper.readValue(payload.body,Post::class.java)
        }
        catch (e: Exception) {
            return HandleOutPut( "Error parsing json post", 500)
        }
        postService.createPost(serverClient, post)
        serverClient.close()
        return HandleOutPut("success", 201)
    }
}


/**
 * This main method allows running the function as a CLI application using: echo '{}' | java -jar function.jar 
 * where the argument to echo is the JSON to be parsed.
 */
fun main(args : Array<String>) { 
    val function = AppFunction()
    function.run(args, { context -> function.apply(context.get(Payload::class.java))})
}