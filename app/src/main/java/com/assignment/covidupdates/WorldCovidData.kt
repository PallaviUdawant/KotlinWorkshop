package com.assignment.covidupdates


import com.google.gson.annotations.SerializedName

data class WorldCovidData(
    @SerializedName("active")
    val active: Int?,
    @SerializedName("activePerOneMillion")
    val activePerOneMillion: Double?,
    @SerializedName("affectedCountries")
    val affectedCountries: Int?,
    @SerializedName("cases")
    val cases: Int?,
    @SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Int?,
    @SerializedName("critical")
    val critical: Int?,
    @SerializedName("criticalPerOneMillion")
    val criticalPerOneMillion: Double?,
    @SerializedName("deaths")
    val deaths: Int?,
    @SerializedName("deathsPerOneMillion")
    val deathsPerOneMillion: Double?,
    @SerializedName("oneCasePerPeople")
    val oneCasePerPeople: Int?,
    @SerializedName("oneDeathPerPeople")
    val oneDeathPerPeople: Int?,
    @SerializedName("oneTestPerPeople")
    val oneTestPerPeople: Int?,
    @SerializedName("population")
    val population: Long?,
    @SerializedName("recovered")
    val recovered: Int?,
    @SerializedName("recoveredPerOneMillion")
    val recoveredPerOneMillion: Double?,
    @SerializedName("tests")
    val tests: Int?,
    @SerializedName("testsPerOneMillion")
    val testsPerOneMillion: Double?,
    @SerializedName("todayCases")
    val todayCases: Int?,
    @SerializedName("todayDeaths")
    val todayDeaths: Int?,
    @SerializedName("todayRecovered")
    val todayRecovered: Int?,
    @SerializedName("updated")
    val updated: Long?
)