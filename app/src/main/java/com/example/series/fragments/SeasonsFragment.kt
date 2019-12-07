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
        seasons.add(Seasons("Temporada 1", "1", "30 agosto 2018", "Piloto", "7.4", "En el primer episodio de la serie, el analista de la CIA Jack Ryan descubre una serie de transferencias de dinero sospechosas que lo llevan, junto a su jefe James Greer, a salir de su despacho para investigar sobre el terreno una grave amenaza a nivel mundial. Hanin comienza a sospechar de los asuntos que su marido se trae entre manos cuando recibe en casa a un misterioso extranjero."))
        seasons.add(Seasons("", "2", "30 agosto 2018", "Conexión francesa", "8.1", "Jack y Greer descifran una nueva información que los lleva a París y los acerca al escurridizo Suleiman. El esposo de Hanin vuelve a casa con un fervor renovado por su misión secreta, y ella empieza a temer por el futuro de su familia."))
        seasons.add(Seasons("", "3", "30 agosto 2018", "22 Negro", "8.0", "El piloto de drones Victor se debate interiormente por la inmensa responsabilidad que conlleva su trabajo. Jack y Greer se unen a un equipo de la inteligencia francesa para localizar al hermano de Suleiman. Hanin se ve obligada a tomar una decisión muy arriesgada por el bien de sus hijos."))
        seasons.add(Seasons("", "4", "30 agosto 2018", "El Lobo", "8.2", "A medida que Jack y Cathy van intimando, la doble vida de Jack se ve puesta a prueba. El poder de Suleiman entre sus seguidores aumenta y eso lo acerca más a su próximo ataque."))

        //2º) Uso un RecyclerView para mostrar un conjunto de items en general.
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
