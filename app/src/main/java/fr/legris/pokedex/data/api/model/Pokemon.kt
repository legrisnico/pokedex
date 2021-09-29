package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class Pokemon (
        @SerializedName("id") val id : Int,
        @SerializedName("name") val name : String,
        @SerializedName("url") val url : String,
        @SerializedName("base_experience") val baseExperience : Int,
        @SerializedName("height") val height : Int,
        @SerializedName("is_default") val isDefault : Boolean,
        @SerializedName("order") val order : Int,
        @SerializedName("weight") val weight : Int,
        @SerializedName("abilities") val abilities : List<Abilities>,
        @SerializedName("forms") val forms : List<Forms>,
        @SerializedName("game_indices") val gameIndices : List<GameIndices>,
        @SerializedName("held_items") val heldItems : List<HeldItems>,
        @SerializedName("location_area_encounters") val locationAreaEncounters : String,
        @SerializedName("moves") val moves : List<Moves>,
        @SerializedName("species") val species : Species,
        @SerializedName("sprites") val sprites : Sprites,
        @SerializedName("stats") val stats : List<Stats>,
        @SerializedName("types") val types : List<Types>
)