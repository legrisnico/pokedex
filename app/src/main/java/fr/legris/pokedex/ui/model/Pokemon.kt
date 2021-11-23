package fr.legris.pokedex.ui.model

data class Pokemon(
    val id: Int,
    val name: String,
    val mainPictureUrl: String,
    val types : List<Type>,
    val weight: Int,
    val height: Int,
    val baseExperience: Int,
) : ModelUi