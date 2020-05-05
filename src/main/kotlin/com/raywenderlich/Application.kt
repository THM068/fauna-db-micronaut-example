package com.raywenderlich

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.raywenderlich")
                .mainClass(Application.javaClass)
                .start()
    }
}