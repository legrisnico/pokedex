package fr.legris.pokedex.ui.model

data class Pokemon(
    val id: Int,
    val name: String,
    val mainPictureUrl: String,
    val types : List<Type>,
    val weight: Float,
    val height: Float,
    val baseExperience: Int,
) : ModelUi