package co.edu.uniandes.app.movil202215.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import co.edu.uniandes.app.movil202215.R
import co.edu.uniandes.app.movil202215.models.Album
import co.edu.uniandes.app.movil202215.viewmodels.AlbumViewModel
import co.edu.uniandes.app.movil202215.viewmodels.CreateAlbumViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateAlbumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateAlbumFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModel: CreateAlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }



        val viewFragment = inflater.inflate(R.layout.fragment_create_album, container, false)



        viewModel = ViewModelProvider(this, CreateAlbumViewModel.Factory(activity.application)).get(CreateAlbumViewModel::class.java)
        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }

        val fab = viewFragment.findViewById(R.id.button_create_album) as Button
        fab.setOnClickListener { view ->

            val name = viewFragment.findViewById<View?>(R.id.input_album_name) as EditText
            val description = viewFragment.findViewById<View?>(R.id.input_album_description) as EditText
            val cover = viewFragment.findViewById<View?>(R.id.input_album_cover) as EditText
            val date = viewFragment.findViewById<View?>(R.id.input_album_date) as EditText
            val genre = viewFragment.findViewById<View?>(R.id.spinner_genre) as Spinner
            val record = viewFragment.findViewById<View?>(R.id.spinner_record) as Spinner

            if (name.text.toString().isEmpty() || description.text.toString().isEmpty() || cover.text.toString().isEmpty() ||
                date.text.toString().isEmpty()) {
                Toast.makeText(getActivity(), "Existen campos sin datos",Toast.LENGTH_LONG).show();
            }else{
                val album = Album(albumId = -1, name = name.text.toString(), cover = cover.text.toString(), recordLabel = record.getSelectedItem().toString(),
                    releaseDate = date.text.toString(), genre = genre.getSelectedItem().toString(),description = description.text.toString(),  tracks = listOf())
                viewModel.createObject(album)
                Toast.makeText(getActivity(), "Álbum creado",Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).navigate(R.id.albumsFragment)

            }




        }

        val spinner = viewFragment.findViewById<View?>(R.id.spinner_genre) as Spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        getActivity()?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.genre_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }
        val spinnerRecord = viewFragment.findViewById<View?>(R.id.spinner_record) as Spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        getActivity()?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.record_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinnerRecord.adapter = adapter
            }
        }
        return viewFragment

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        val args: DetailAlbumFragmentArgs by navArgs()



    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateAlbumFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateAlbumFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}