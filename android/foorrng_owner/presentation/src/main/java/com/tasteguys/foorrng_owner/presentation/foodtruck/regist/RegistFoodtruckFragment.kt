package com.tasteguys.foorrng_owner.presentation.foodtruck.regist

import android.os.Bundle
import android.view.View
import com.google.android.flexbox.FlexboxLayoutManager
import com.tasteguys.foorrng_owner.presentation.R
import com.tasteguys.foorrng_owner.presentation.databinding.FragmentRegistFoodtruckBinding
import com.tasteguys.foorrng_owner.presentation.foodtruck.info.FoodtruckInfoFragment
import com.tasteguys.foorrng_owner.presentation.main.MainBaseFragment
import com.tasteguys.foorrng_owner.presentation.main.MainToolbarControl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistFoodtruckFragment : MainBaseFragment<FragmentRegistFoodtruckBinding>(
    FragmentRegistFoodtruckBinding::bind, R.layout.fragment_regist_foodtruck
) {
    private var menuCategoryAdapter: MenuCategoryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategoryView()
        setCategoryViewAdapter(dummyCategory.sortedBy { it.length })
    }

    override fun setToolbar() {
        MainToolbarControl(
            visible = true,
            title = resources.getString(R.string.regist_foodtruck_title),
            menuRes = R.menu.menu_check
        ).addMenuItemClickListener {
            showToast("등록 메뉴!")
            parentFragmentManager.beginTransaction()
                .replace(R.id.layout_main_fragment,FoodtruckInfoFragment())
                .commit()
        }.addNavIconClickListener {
            showToast("등록 뒤로가기")
        }.also {
                mainViewModel.changeToolbar(it)
        }
    }

    private fun initCategoryView(){
        binding.rvMenuCategory.layoutManager = FlexboxLayoutManager(_activity)
    }

    private fun setCategoryViewAdapter(menuList: List<String>){
        if (menuCategoryAdapter == null){
            menuCategoryAdapter = MenuCategoryAdapter(menuList)
        }
        binding.rvMenuCategory.adapter = menuCategoryAdapter
    }

    private val dummyCategory = listOf("덮밥", "전기구이통닭", "꼬치", "타코야끼", "타코 & 케밥", "분식(떡볶이 만두 순대 어묵 튀김)", "빵(국화빵 붕어빵 호떡)", "구황작물(군고구마, 찐옥수수, 군밤)", "카페 & 디저트", "기타")
}