package io.korostenskyi.chestnut.domain.model

data class Credits(
    val cast: List<Person>,
    val crew: List<Person>
) {

    data class Person(
        val name: String,
        val photoPath: String? = null,
        val character: String? = null
    )
}