package com.tasteguys.foorrng_owner.presentation.main

import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import com.tasteguys.foorrng_owner.presentation.base.BaseFragment

abstract class MainBaseFragment<B : ViewBinding>(
    private val bind: (View) -> B,
    @LayoutRes layoutResId: Int
) : BaseFragment<B>(
    bind,layoutResId
), IToolbarFragment {
    protected val mainViewModel: MainViewModel by activityViewModels()
    protected lateinit var mainActivity: MainActivity

    override fun setToolbar() {
        mainViewModel.changeToolbar(MainToolbarControl())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onStart() {
        super.onStart()
        setToolbar()
    }

    override fun onStop() {
        super.onStop()
        mainViewModel.changeToolbar(MainToolbarControl())
    }
}