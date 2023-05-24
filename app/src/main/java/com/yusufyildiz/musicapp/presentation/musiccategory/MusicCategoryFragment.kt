package com.yusufyildiz.musicapp.presentation.musiccategory

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
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import com.yusufyildiz.musicapp.common.Resource
import com.yusufyildiz.musicapp.common.extension.gone
import com.yusufyildiz.musicapp.common.extension.showSnackBar
import com.yusufyildiz.musicapp.common.extension.visible
import com.yusufyildiz.musicapp.data.model.musiccategorymodel.MusicCategoryDetailModel
import com.yusufyildiz.musicapp.databinding.FragmentMusicCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MusicCategoryFragment : Fragment() {

    private var _binding: FragmentMusicCategoryBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var glide: RequestManager

    private lateinit var musicCategoryRecyclerAdapter: MusicCategoryRecyclerAdapter

    private val musicCategoryListViewModel: MusicCategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMusicCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        musicCategoryRecyclerAdapter = MusicCategoryRecyclerAdapter(glide)

        musicCategoryRecyclerAdapter.onCategoryClick = { category ->
            val action =
                MusicCategoryFragmentDirections.actionMusicCategoryFragmentToArtistListFragment(
                    category.name.toString(),
                    category.id ?: 0
                )
            findNavController().navigate(action)
        }


        musicCategoryListViewModel.getMusicCategoryList()
        initCollectors()
    }

    private fun initCollectors() {
        with(binding) {
            with(musicCategoryListViewModel) {
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.CREATED) {
                        musicCategoryListStateFlow.collectLatest {
                            when (it) {
                                is Resource.Success -> {
                                    loadingProgressBar.gone()
                                    initRecyclerView(it.data.musicCategoryList.orEmpty())
                                }

                                is Resource.Error -> {
                                    loadingProgressBar.gone()
                                    requireView().showSnackBar(it.throwable.toString())
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

    private fun initRecyclerView(musicCategoryList: List<MusicCategoryDetailModel>) {
        with(binding) {
            musicCategoryListRecyclerView.adapter = musicCategoryRecyclerAdapter
            musicCategoryListRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            musicCategoryRecyclerAdapter.musicCategoryList = musicCategoryList
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}