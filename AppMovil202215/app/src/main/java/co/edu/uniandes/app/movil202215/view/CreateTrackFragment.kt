package co.edu.uniandes.app.movil202215.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import co.edu.uniandes.app.movil202215.R
import co.edu.uniandes.app.movil202215.models.Track
import co.edu.uniandes.app.movil202215.viewmodels.CreateTrackViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateAlbumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateTrackFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModel: CreateTrackViewModel

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

        val args: CreateTrackFragmentArgs by navArgs()
        Log.d("","agumento"+ args.albumId)

        val viewFragment = inflater.inflate(R.layout.album_add_song_fragment, container, false)



        viewModel = ViewModelProvider(this, CreateTrackViewModel.Factory(activity.application)).get(CreateTrackViewModel::class.java)
        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }

        val fab = viewFragment.findViewById(R.id.add_song_button) as Button
        fab.setOnClickListener { view ->

            val name = viewFragment.findViewById<View?>(R.id.add_song_name) as EditText
            val duration = viewFragment.findViewById<View?>(R.id.add_song_duration) as EditText

            if (name.text.toString().isEmpty() || duration.text.toString().isEmpty()) {
                Toast.makeText(getActivity(), "Existen campos sin datos",Toast.LENGTH_LONG).show()
            }else{
                val newTrack = Track(trackId = -1, name = name.text.toString(), duration = duration.text.toString())
                viewModel.createObject(args.albumId, newTrack)
                Toast.makeText(getActivity(), "Track creado",Toast.LENGTH_LONG).show()

                val action = CreateTrackFragmentDirections.actionAddTrackFloatingToDetailAlbumFragment(args.albumId)
                Navigation.findNavController(view).navigate(action)
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
