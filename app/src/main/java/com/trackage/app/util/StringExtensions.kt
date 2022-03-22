package com.trackage.app.util

/**
 * Extension function which uses regex to check if a String is Letters only
 *
 * @return Boolean - returns true if value is letters only
 */
internal fun String.containsOnlyLetters(): Boolean {
    return this.contains(Regex("^[a-zA-Z ]*\$"))
}

/**
 * Extension function which splits itself by a space
 *
 * @return List<String> - Returns list of strings split by the space
 */
internal fun String.splitBySpace(): List<String> {
    return this.split(" ")
}