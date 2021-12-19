package com.sutton.jokeapp.testing

/**
 * NOTE: This is not my work, this was found in a medium article.
 *
 * It allows classes to be open for testing only in debug builds.
 */

/**
 * This annotation allows us to open some classes for mocking purposes while they are final in
 * release builds.
 */
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class OpenClass

/**
 * Annotate a class with [OpenForTesting] if you want it to be extendable in debug builds.
 */
@OpenClass
@Target(AnnotationTarget.CLASS)
annotation class OpenForTesting