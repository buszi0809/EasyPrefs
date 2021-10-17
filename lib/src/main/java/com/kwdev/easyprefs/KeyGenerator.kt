package com.kwdev.easyprefs

import kotlin.reflect.KProperty

/**
 * The function generating keys for SharedPreferences. It makes sure that the keys
 * are unique, by using qualified name of parent class and property name.
 * For example "com.kwdev.easyprefs.EasyPrefs_string".
 */
internal fun getKeyFor(thisRef: EasyPrefs, property: KProperty<*>): String =
    "${thisRef::class.qualifiedName}_${property.name}"
