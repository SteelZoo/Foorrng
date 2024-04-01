package com.tasteguys.foorrng_owner.presentation.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentArticleDetailBinding
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment

class ArticleDetailFragment : MainBaseFragment<FragmentArticleDetailBinding>(
    FragmentArticleDetailBinding::bind, R.layout.fragment_article_detail
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}