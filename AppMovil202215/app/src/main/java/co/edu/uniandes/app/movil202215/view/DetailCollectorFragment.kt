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
import co.edu.uniandes.app.movil202215.databinding.DetailCollectorFragmentBinding
import co.edu.uniandes.app.movil202215.view.adapters.DetailCollectorAdapter
import co.edu.uniandes.app.movil202215.view.adapters.DetailCollectorAlbumsAdapter
import co.edu.uniandes.app.movil202215.viewmodels.DetailCollectorViewModel

class DetailCollectorFragment : Fragment() {
    private var _binding: DetailCollectorFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAlbum: RecyclerView
    private lateinit var viewModel: DetailCollectorViewModel
    private var viewModelAdapter: DetailCollectorAdapter? = null
    private var viewModelAdapterAlbum: DetailCollectorAlbumsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailCollectorFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = DetailCollectorAdapter()
        viewModelAdapterAlbum = DetailCollectorAlbumsAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.commentsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter

        recyclerViewAlbum = binding.albumsRv
        recyclerViewAlbum.layoutManager = LinearLayoutManager(context)
        recyclerViewAlbum.adapter = viewModelAdapterAlbum
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_detail_collectors)
        val args: DetailCollectorFragmentArgs by navArgs()
        Log.d("Args", args.collectorId.toString())
        viewModel = ViewModelProvider(this, DetailCollectorViewModel.Factory(activity.application, args.collectorId)).get(
            DetailCollectorViewModel::class.java)
        viewModel.comments.observe(viewLifecycleOwner) {
            it.apply {
                viewModelAdapter!!.detailCollector = this
                viewModelAdapterAlbum!!.detailCollectorAlbum = this[0].collectorAlbums
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
