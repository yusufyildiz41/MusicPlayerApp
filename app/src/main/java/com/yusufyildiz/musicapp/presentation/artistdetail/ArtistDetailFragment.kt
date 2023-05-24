package com.yusufyildiz.musicapp.presentation.artistdetail

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.common.extension.gone
import com.yusufyildiz.musicapp.common.extension.visible
import com.yusufyildiz.musicapp.data.model.albumlistmodel.AlbumListModel
import com.yusufyildiz.musicapp.databinding.FragmentArtistDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ArtistDetailFragment : Fragment() {

    private var _binding: FragmentArtistDetailBinding? = null
    private val binding get() = _binding!!
    private val artistDetailViewModel: ArtistDetailViewModel by viewModels()
    private lateinit var albumListRecyclerAdapter: AlbumListRecyclerAdapter
    private val args: ArtistDetailFragmentArgs by navArgs()

    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumListRecyclerAdapter = AlbumListRecyclerAdapter(glide)

        albumListRecyclerAdapter.onAlbumClick = { album ->
            val action =
                ArtistDetailFragmentDirections.actionArtistDetailFragmentToAlbumDetailFragment(
                    album.id ?: 0L, album.title.toString()
                )
            findNavController().navigate(action)
        }
        artistDetailViewModel.getArtistDetailByArtistId(args.artistId)
        artistDetailViewModel.getAlbumListByArtistId(args.artistId)
        initCollectors()
    }

    private fun initCollectors() {
        with(binding) {
            with(artistDetailViewModel) {
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.CREATED) {
                        artistDetailStateFlow.collectLatest {
                            when (it) {
                                is Resource.Success -> {
                                    loadingProgressBar.gone()
                                    artistNameTextView.text = it.data.name
                                    glide.load(it.data.pictureBig)
                                        .override(1300, 360)
                                        .fitCenter()
                                        .into(artistImageView)
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

                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.CREATED) {
                        albumListStateFlow.collectLatest {
                            when (it) {
                                is Resource.Success -> {
                                    loadingProgressBar.gone()
                                    initRecyclerView(it.data.albumList.orEmpty())
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

    private fun initRecyclerView(albumList: List<AlbumListModel>) {
        with(binding) {
            artistListRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            artistListRecyclerView.adapter = albumListRecyclerAdapter
            albumListRecyclerAdapter.albumList = albumList
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}