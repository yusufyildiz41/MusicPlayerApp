package com.yusufyildiz.musicapp.presentation.albumdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.common.extension.gone
import com.yusufyildiz.musicapp.common.extension.visible
import com.yusufyildiz.musicapp.databinding.FragmentAlbumDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AlbumDetailFragment : Fragment() {

    private val albumDetailViewModel: AlbumDetailViewModel by viewModels()
    private var _binding: FragmentAlbumDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var albumDetailRecyclerAdapter: AlbumDetailRecyclerAdapter
    private val args: AlbumDetailFragmentArgs by navArgs()

    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumDetailViewModel.getAlbumDetailsByAlbumId(args.albumId)
        albumDetailRecyclerAdapter = AlbumDetailRecyclerAdapter(glide)
        binding.songListRecyclerView.adapter = albumDetailRecyclerAdapter

        albumDetailRecyclerAdapter.onFavouriteClick = { song ->
            albumDetailViewModel.favouriteSong(song)
        }

        albumDetailRecyclerAdapter.onSongClick = { song ->
            albumDetailViewModel.playSongWithSongURL(song.songPreview.orEmpty())
        }

        initCollectors()
    }

    private fun initCollectors() {
        with(binding) {
            with(albumDetailViewModel) {
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.CREATED) {
                        albumDetailsStateFlow.collectLatest {
                            when (it) {
                                is Resource.Success -> {
                                    loadingProgressBar.gone()
                                    albumDetailRecyclerAdapter.submitList(it.data)
                                    albumNameTextView.text = args.albumName
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

    override fun onStop() {
        super.onStop()
        albumDetailViewModel.stopSong()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}