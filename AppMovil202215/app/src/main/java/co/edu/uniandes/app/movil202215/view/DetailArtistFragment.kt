package co.edu.uniandes.app.movil202215.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202215.R
import co.edu.uniandes.app.movil202215.databinding.DetailArtistFragmentBinding
import co.edu.uniandes.app.movil202215.view.adapters.DetailArtistAdapter
import co.edu.uniandes.app.movil202215.view.adapters.DetailArtistAlbumsAdapter
import co.edu.uniandes.app.movil202215.viewmodels.DetailArtistViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DetailArtistFragment : Fragment() {
    private var _binding: DetailArtistFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAlbum: RecyclerView
    private lateinit var viewModel: DetailArtistViewModel
    private var viewModelAdapter: DetailArtistAdapter? = null
    private var viewModelAdapterAlbum: DetailArtistAlbumsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailArtistFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = DetailArtistAdapter()
        viewModelAdapterAlbum = DetailArtistAlbumsAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.commentsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter

        recyclerViewAlbum = binding.tracksRv
        recyclerViewAlbum.layoutManager = LinearLayoutManager(context)
        recyclerViewAlbum.adapter = viewModelAdapterAlbum
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.artist_comments)
        val args: DetailArtistFragmentArgs by navArgs()
        Log.d("Args", args.artistId.toString())
        viewModel = ViewModelProvider(this, DetailArtistViewModel.Factory(activity.application, args.artistId)).get(DetailArtistViewModel::class.java)
        viewModel.comments.observe(viewLifecycleOwner) {
            it.apply {
                viewModelAdapter!!.detailArtist = this
                viewModelAdapterAlbum!!.albums = this[0].albums
                if (this.isEmpty()) {
                    binding.txtNoComments.visibility = View.VISIBLE
                } else {
                    binding.txtNoComments.visibility = View.GONE
                }
            }
        }
        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}
