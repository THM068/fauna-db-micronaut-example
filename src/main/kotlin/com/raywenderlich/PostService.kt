package com.raywenderlich

import com.faunadb.client.FaunaClient
import com.faunadb.client.query.Language

public class PostService {

    fun createPost( serverClient: FaunaClient,  post: Post) {
        serverClient.query(
                Language.Create(
                        Language.Collection(Language.Value("post")),
                        post.toFauna()
                )
        ).get()
    }
}