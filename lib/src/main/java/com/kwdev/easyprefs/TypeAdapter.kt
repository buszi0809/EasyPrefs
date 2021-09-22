package com.kwdev.easyprefs

interface TypeAdapter<T> {
    fun toString(value: T): String?
    fun fromString(value: String?): T
}
