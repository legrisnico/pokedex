package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class PokemonDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("is_default") val isDefault: Boolean,
    @SerializedName("order") val order: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("abilities") val abilities: List<AbilitiesDTO>,
    @SerializedName("forms") val forms: List<FormsDTO>,
    @SerializedName("game_indices") val gameIndexDTOS: List<GameIndicesDTO>,
    @SerializedName("held_items") val heldItemDTOS: List<HeldItemsDTO>,
    @SerializedName("location_area_encounters") val locationAreaEncounters: String,
    @SerializedName("moves") val moves: List<MovesDTO>,
    @SerializedName("species") val speciesDTO: SpeciesDTO,
    @SerializedName("sprites") val spritesDTO: SpritesDTO,
    @SerializedName("stats") val stats: List<StatsDTO>,
    @SerializedName("types") val types: List<TypesDTO>
) : ApiModel
