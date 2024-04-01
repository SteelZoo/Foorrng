package com.tasteguys.foorrng_owner.presentation.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentArticleListBinding
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment


class ArticleListFragment : MainBaseFragment<FragmentArticleListBinding>(
    FragmentArticleListBinding::bind, R.layout.fragment_article_list
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}