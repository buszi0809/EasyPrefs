package com.kwdev.easyprefs

/**
 * Type adapter interface, to be used to convert your object of type [T] to nullable string value and backwards.
 * This interface is useful if you want to use [custom] or [customFlow] property.
 */
interface TypeAdapter<T> {
    /**
     * Convert your object of type [T] into nullable string value.
     */
    fun toString(value: T): String?

    /**
     * Recreate your object of type [T] from nullable string value.
     */
    fun fromString(value: String?): T
}
