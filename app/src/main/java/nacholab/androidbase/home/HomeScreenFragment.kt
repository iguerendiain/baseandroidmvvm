package nacholab.androidbase.home

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import nacholab.androidbase.R
import nacholab.androidbase.common.ui.BaseScreenFragment
import nacholab.androidbase.databinding.FragmentHomeBinding
import nacholab.androidbase.domain.MainViewModel

@AndroidEntryPoint
class HomeScreenFragment: BaseScreenFragment<FragmentHomeBinding>() {
    override fun getLayoutResInt() = R.layout.fragment_home
    override fun getLightStatusBar() = false
    override fun isFullScreen() = true

    private val homeViewModel: MainViewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        bindToViewModel()
    }

    override fun setupUI() {
        binding?.apply {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                requireActivity().finish()
            }
        }
    }

    override fun bindToViewModel() {
        binding?.apply {
            viewModel = homeViewModel
        }
    }
}
