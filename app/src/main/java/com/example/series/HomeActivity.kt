package com.example.series

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val series: ArrayList<Series> = ArrayList<Series>()
        series.add(Series("Samuel", "Drama", "9.0", "Amazon" ))
        series.add(Series("Francis", "Sosa", "",  "Amazon" ))
        series.add(Series("Gustavo", "Vega","", ""))
        series.add(Series("Juan", "Sosa","", ""))
        series.add(Series("Jacinto", "Ramos Moreno","", ""))
        series.add(Series("Javier", "Santana","", ""))
        series.add(Series("Olga", "Cruz","", ""))
        //2º) Uso un RecyclerView para mostrar un conjunto de items en general.
        val recyclerViewSeries: RecyclerView = findViewById(R.id.recyclerViewSeries)
        //3º) Indico la disposición en la que se mostrarán los items en el RecyclerView (P.Ej: GridLayout de 2 columnas)
        val layoutManagerStudents: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        recyclerViewSeries.setLayoutManager(layoutManagerStudents)
        //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
        val seriesAdapter = SeriesAdapter(series)
        recyclerViewSeries.setAdapter(seriesAdapter)
    }
}
