package com.raywenderlich;

import com.faunadb.client.FaunaClient
import io.micronaut.function.executor.FunctionInitializer
import io.micronaut.function.FunctionBean;
import javax.inject.*;
import java.util.function.Function;

@FunctionBean("app")
class AppFunction() : FunctionInitializer(), Function<Post, HandleOutPut> {

    val postService: PostService = PostService()

    override fun apply(post: Post): HandleOutPut {
        var serverClient = FaunaClient.builder()
                .withSecret("fnADq_1T0DACAH0Ut_hKsEOWXwMyQSRbaxLsFZCp")
                .build()
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
    function.run(args, { context -> function.apply(context.get(Post::class.java))})
}