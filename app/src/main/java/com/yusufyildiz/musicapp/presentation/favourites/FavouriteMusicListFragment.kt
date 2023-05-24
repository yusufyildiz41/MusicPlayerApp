package com.yusufyildiz.musicapp.presentation.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.RequestManager
import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.common.extension.gone
import com.yusufyildiz.musicapp.common.extension.visible
import com.yusufyildiz.musicapp.databinding.FragmentFavouriteMusicListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class FavouriteMusicListFragment : Fragment() {

    private var _binding: FragmentFavouriteMusicListBinding ?=null
    private val binding get() = _binding!!

    private val favouriteMusicListViewModel: FavouriteMusicListViewModel by viewModels()

    @Inject
    lateinit var glide: RequestManager

    private lateinit var favouriteMusicListRecyclerAdapter: FavouriteMusicListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavouriteMusicListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        favouriteMusicListRecyclerAdapter = FavouriteMusicListRecyclerAdapter(glide)
        binding.favouriteSongListRecyclerView.adapter = favouriteMusicListRecyclerAdapter

        favouriteMusicListRecyclerAdapter.onSongClick = { song ->
            favouriteMusicListViewModel.playSongWithSongURL(song.songPreview.toString())
        }

        favouriteMusicListRecyclerAdapter.onFavouriteClick = { song ->
            favouriteMusicListViewModel.deleteSongFromFavourites(song)
        }

        initCollectors()

    }

    private fun initCollectors(){

        with(binding){
            with(favouriteMusicListViewModel){
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.CREATED){
                        favouriteSongListStateFlow.collectLatest { songListResource ->
                            when(songListResource){
                                is Resource.Success -> {
                                    loadingProgressBar.gone()
                                    favouriteMusicListRecyclerAdapter.favouriteMusicList = songListResource.data
                                    favouriteMusicListRecyclerAdapter.notifyDataSetChanged()
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

    override fun onResume() {
        super.onResume()
        favouriteMusicListViewModel.getFavouriteSongList()
    }

    override fun onStop() {
        super.onStop()
        favouriteMusicListViewModel.stopSong()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}