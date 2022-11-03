package co.edu.uniandes.app.movil202215.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202215.R
import co.edu.uniandes.app.movil202215.databinding.DetailAlbumFragmentBinding
import co.edu.uniandes.app.movil202215.models.Album
import co.edu.uniandes.app.movil202215.view.adapters.DetailAlbumAdapter
import co.edu.uniandes.app.movil202215.viewmodels.DetailAlbumViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DetailAlbumFragment : Fragment() {
    private var _binding: DetailAlbumFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: DetailAlbumViewModel
    private var viewModelAdapter: DetailAlbumAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailAlbumFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = DetailAlbumAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.commentsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_comments)
        val args: DetailAlbumFragmentArgs by navArgs()
        Log.d("Args", args.albumId.toString())
        viewModel = ViewModelProvider(this, DetailAlbumViewModel.Factory(activity.application, args.albumId)).get(DetailAlbumViewModel::class.java)
        viewModel.comments.observe(viewLifecycleOwner, Observer<List<Album>> {
            it.apply {
                viewModelAdapter!!.detailAlbum = this
                if(this.isEmpty()){
                    binding.txtNoComments.visibility = View.VISIBLE
                }else{
                    binding.txtNoComments.visibility = View.GONE
                }
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
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