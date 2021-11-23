package fr.legris.pokedex.ui.model

import androidx.compose.ui.graphics.Color
import fr.legris.pokedex.ui.theme.*

enum class Type(
    val typeName: String,
    val typeColor: Color
) {
    NORMAL("normal", normalTypeColor),
    FIRE("fire", fireTypeColor),
    WATER("water", waterTypeColor),
    GRASS("grass", grassTypeColor),
    ELECTRIC("electric", electricTypeColor),
    ICE("ice", iceTypeColor),
    FIGHTING("fighting", fightingTypeColor),
    POISON("poison", poisonTypeColor),
    GROUND("ground", groundTypeColor),
    FLYING("flying", flyingTypeColor),
    PSYCHIC("psychic", psychicTypeColor),
    BUG("bug", bugTypeColor),
    ROCK("rock", rockTypeColor),
    GHOST("ghost", ghostTypeColor),
    DRAGON("dragon", dragonTypeColor),
    DARK("dark", darkTypeColor),
    STEEL("steel", steelTypeColor),
    FAIRY("fairy", fairyTypeColor),
    UNKNOWN("unknown", fairyTypeColor),
}