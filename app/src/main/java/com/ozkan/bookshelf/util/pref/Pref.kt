package com.ozkan.bookshelf.util.pref

interface Pref {
    fun <T> put(key: String, value: T)

    fun <T> get(key: String): T?

    fun <T> get(key: String, defaultValue: T): T

    fun delete(key: String)

    fun init()
}