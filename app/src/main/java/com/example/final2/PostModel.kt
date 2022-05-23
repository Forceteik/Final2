package com.example.final2


data class PostModel(
    val name: String? = null,
    val vision: String? = null,
    val weapon: String? = null,
    val nation: String? = null,
    val affiliation: String? = null,
    val rarity: Int? = null,
    val constellation: String? = null,
    val birthday: String? = null,
    val description: String? = null,
    val skills: Array<SkillModel>?,
    val talents: MutableList<TalentModel>? = null,
    val constellations: MutableList<ConstellationModel>? = null,

        ){
    data class SkillModel(
        val name: String? = null,
        val unlock: String? = null,
        val description: String? = null,
        val type : String? = null
    )
    data class TalentModel(
        val name: String? = null,
        val unlock: String? = null,
        val description: String? = null,
    )
    data class ConstellationModel(
        val name: String? = null,
        val unlock: String? = null,
        val description: String? = null,
        val level : Int? = null
    )
}

