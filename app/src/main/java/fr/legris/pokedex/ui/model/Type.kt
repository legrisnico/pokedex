package fr.legris.pokedex.ui.model

import androidx.compose.ui.graphics.Color
import fr.legris.pokedex.ui.theme.*

enum class Type(
    val typeName: String,
    val typeColor: Color,
    val typeColorLight: Color,
    val typeColorDark: Color
) {
    NORMAL("normal", normalTypeColor, normalTypeColorLight, normalTypeColorDark),
    FIRE("fire", fireTypeColor, fireTypeColorLight, fireTypeColorDark),
    WATER("water", waterTypeColor, waterTypeColorLight, waterTypeColorDark),
    GRASS("grass", grassTypeColor, grassTypeColorLight, grassTypeColorDark),
    ELECTRIC("electric", electricTypeColor, electricTypeColorLight, electricTypeColorDark),
    ICE("ice", iceTypeColor, iceTypeColorLight, iceTypeColorDark),
    FIGHTING("fighting", fightingTypeColor, fightingTypeColorLight, fightingTypeColorDark),
    POISON("poison", poisonTypeColor, poisonTypeColorLight, poisonTypeColorDark),
    GROUND("ground", groundTypeColor, groundTypeColorLight, groundTypeColorDark),
    FLYING("flying", flyingTypeColor, flyingTypeColorLight, flyingTypeColorDark),
    PSYCHIC("psychic", psychicTypeColor, psychicTypeColorLight, psychicTypeColorDark),
    BUG("bug", bugTypeColor, bugTypeColorLight, bugTypeColorDark),
    ROCK("rock", rockTypeColor, rockTypeColorLight, rockTypeColorDark),
    GHOST("ghost", ghostTypeColor, ghostTypeColorLight, ghostTypeColorDark),
    DRAGON("dragon", dragonTypeColor, dragonTypeColorLight, dragonTypeColorDark),
    DARK("dark", darkTypeColor, darkTypeColorLight, darkTypeColorDark),
    STEEL("steel", steelTypeColor, steelTypeColorLight, steelTypeColorDark),
    FAIRY("fairy", fairyTypeColor, fairyTypeColorLight, fairyTypeColorDark),
    UNKNOWN("unknown", fairyTypeColor, fairyTypeColorLight, fairyTypeColorDark),
}