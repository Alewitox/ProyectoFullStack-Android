package com.example.series.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.series.*

/**
 * A simple [Fragment] subclass.
 */
class ActorsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.activity_actors, container, false)

        val actors: ArrayList<Actors> = ArrayList<Actors>()
        actors.add(Actors("Samuel", "como Drama"))
        actors.add(Actors("Francis", "como Sosa"))
        actors.add(Actors("Gustavo", "como Vega"))
        actors.add(Actors("Juan", "como Sosa"))
        actors.add(Actors("Jacinto", "como Ramos Moreno"))
        actors.add(Actors("Javier", "como Santana"))
        actors.add(Actors("Olga", "como Cruz"))
        //2º) Uso un RecyclerView para mostrar un conjunto de items en general.
        val recyclerViewActors: RecyclerView = root.findViewById(R.id.recyclerViewActors)
        //3º) Indico la disposición en la que se mostrarán los items en el RecyclerView (P.Ej: GridLayout de 2 columnas)
        val layoutManagerActors: RecyclerView.LayoutManager = GridLayoutManager(root.context, 1)
        recyclerViewActors.setLayoutManager(layoutManagerActors)
        //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
        val actorsAdapter = ActorsAdapter(actors)
        recyclerViewActors.setAdapter(actorsAdapter)


        return root
    }


}
