package com.yusufyildiz.musicapp.presentation.artistlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.common.extension.gone
import com.yusufyildiz.musicapp.common.extension.visible
import com.yusufyildiz.musicapp.data.model.artistlistmodel.ArtistModel
import com.yusufyildiz.musicapp.databinding.FragmentArtistListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ArtistListFragment : Fragment() {

    private var _binding: FragmentArtistListBinding ?=null
    private val binding get() = _binding!!

    private val artistListViewModel: ArtistListViewModel by viewModels()
    private lateinit var artistListRecyclerAdapter: ArtistListRecyclerAdapter

    private val args: ArtistListFragmentArgs by navArgs()

    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artistListRecyclerAdapter = ArtistListRecyclerAdapter(glide)

        artistListRecyclerAdapter.onArtistClick = { artist ->
            val action = ArtistListFragmentDirections.actionArtistListFragmentToArtistDetailFragment(artist.id?:0)
            findNavController().navigate(action)
        }

        with(binding){
            categoryNameTextView.text = args.categoryName
            artistListViewModel.getArtistListByCategory(args.categoryId)
            initCollectors()
        }

    }

    private fun initCollectors(){
        with(binding){
            with(artistListViewModel){
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.CREATED){
                        artistListStateFlow.collectLatest {
                            when(it){
                                is Resource.Success -> {
                                    loadingProgressBar.gone()
                                    initRecyclerView(it.data.artistList.orEmpty())
                                }
                                is Resource.Error -> {
                                    loadingProgressBar.visible()
                                }
                                is Resource.Loading -> {
                                    loadingProgressBar.visible()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initRecyclerView(artistList: List<ArtistModel>){
        with(binding){
            artistListRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
            artistListRecyclerView.adapter = artistListRecyclerAdapter
            artistListRecyclerAdapter.artistList = artistList
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}