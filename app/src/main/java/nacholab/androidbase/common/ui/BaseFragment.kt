package nacholab.androidbase.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment<T: ViewBinding> : Fragment() {

    protected var binding: T? = null

    private val insetsController: WindowInsetsControllerCompat? by lazy {
        activity?.window?.let { window -> WindowInsetsControllerCompat(window, window.decorView) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            getLayoutResInt(),
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        bindToViewModel()
    }

    protected abstract fun getLayoutResInt(): Int
    protected abstract fun setupUI()
    protected abstract fun bindToViewModel()

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    protected fun navigate(target: Int){
        findNavController().navigate(target)
    }

    protected fun navigate(target: Int, arguments: Bundle){
        findNavController().navigate(target, arguments)
    }

    protected fun navigateUp(){
        findNavController().navigateUp()
    }

    protected fun <V>collectLatestOnStart(stateFlow: Flow<V>, collectFn: (V) -> Unit){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                stateFlow.collectLatest (collectFn)
            }
        }
    }

    protected fun <V>collectOnStart(stateFlow: Flow<V>, collectFn: (V) -> Unit){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                stateFlow.collect (collectFn)
            }
        }
    }

}