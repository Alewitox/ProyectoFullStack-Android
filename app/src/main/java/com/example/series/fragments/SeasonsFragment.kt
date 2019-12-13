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
class SeasonsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.activity_seasons, container, false)

        val seasons: ArrayList<Seasons> = ArrayList<Seasons>()


        val recyclerViewSeasons: RecyclerView = root.findViewById(R.id.recyclerViewSeasons)




        //3º) Indico la disposición en la que se mostrarán los items en el RecyclerView (P.Ej: GridLayout de 2 columnas)
        val layoutManagerSeasons: RecyclerView.LayoutManager = GridLayoutManager(root.context, 1)
        recyclerViewSeasons.setLayoutManager(layoutManagerSeasons)
        //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
        val seasonsAdapter = SeasonsAdapter(seasons)
        recyclerViewSeasons.setAdapter(seasonsAdapter)



        return root
    }


}
