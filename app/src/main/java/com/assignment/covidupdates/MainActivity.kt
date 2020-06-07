package com.assignment.covidupdates

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCovidUpdates()
    }

    /**
     * Kotlin Concepts: Lambda Expression, Class reference using :: operator
     * This function makes an API call to get the current COvid-19 data using Volley String request.
     */
    private fun getCovidUpdates() {
        val stringRequest =
            StringRequest(
                Request.Method.GET,
                Constants.covidUpdateUrl, Response.Listener { response ->
                    try {
                        val worldCovidData = Gson().fromJson(response, WorldCovidData::class.java)
                        setGraphData(worldCovidData)


                        val affectedCountries = worldCovidData.affectedCountries

                        tv_affected_countries.text = "Total Affected Countries - $affectedCountries"
                        setStatsData(worldCovidData)

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this@MainActivity, error.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
            )
        getRequestQueue().add(stringRequest)
    }

    private fun setStatsData(worldCovidData: WorldCovidData) {
        cases_per_milion.text = getString(
            R.string.str_active_per_milion,
            (worldCovidData.casesPerOneMillion.toString())
        )
        deaths_per_milion.text = getString(
            R.string.str_deaths_per_milion,
            (worldCovidData.deathsPerOneMillion.toString())
        )
        active_per_milion.text =
            getString(
                R.string.str_active_per_milion,
                (worldCovidData.activePerOneMillion.toString())
            )
        tests_per_milion.text =
            getString(R.string.str_tests_per_milion, (worldCovidData.testsPerOneMillion.toString()))

        recovered_per_milion.text = getString(
            R.string.str_recovered_per_milion,
            (worldCovidData.recoveredPerOneMillion.toString())
        )

        critical_per_million.text =
            getString(
                R.string.str_critical_per_million,
                (worldCovidData.criticalPerOneMillion.toString())
            )
    }

    /**
     * Kotlin Concepts: Bang Bang operator, MutableList
     * Sets the graph data.
     *
     */
    private fun setGraphData(worldCovidData: WorldCovidData) {
        val entries: MutableList<PieEntry> = ArrayList()
        entries.add(PieEntry(worldCovidData.deaths!!.toFloat()))
        entries.add(PieEntry(worldCovidData.recovered!!.toFloat()))
        entries.add(PieEntry(worldCovidData.active!!.toFloat()))

        // Create dataset of created entries.
        val dataSet = PieDataSet(entries, "Covid-19")

        // Add colors.
        val colors = mutableListOf(
            getColor(R.color.color_death_cases),
            getColor(R.color.color_recovered_cases),
            getColor(R.color.color_active_cases)
        )
        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setDrawValues(false)
        covid_chart.data = data
        covid_chart.invalidate()

        // Animate the graph.
        covid_chart.animateX(100, Easing.EasingOption.EaseInOutQuad)

        // Disable rotation.
        covid_chart.isRotationEnabled = false

        // To hide the color indicators provided by the library.
        val legend: Legend = covid_chart.legend
        legend.isEnabled = false
        covid_chart.holeRadius = 60f

        // Hide the description which is by default enabled.
        covid_chart.description.isEnabled = false


        total_active.text = "Active Cases : ${worldCovidData.active}"
        total_cases.text = "Total Cases: ${worldCovidData.cases}"
        total_deaths.text = "Total Deaths: ${worldCovidData.deaths}"
        total_recovered.text = "Total Recovered: ${worldCovidData.recovered}"
    }

    /**
     * Kotlin Concept : A function as a single expression
     * Returns the  volley Request Queue.
     */
    private fun getRequestQueue(): RequestQueue = Volley.newRequestQueue(this)

    object Constants {
        const val covidUpdateUrl = "https://corona.lmao.ninja/v2/all/"
    }
}
