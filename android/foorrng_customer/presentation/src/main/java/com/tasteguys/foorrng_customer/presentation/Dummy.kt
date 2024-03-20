package com.tasteguys.foorrng_customer.presentation

import com.tasteguys.foorrng_customer.presentation.model.TruckInfo
import com.tasteguys.foorrng_customer.presentation.model.TruckMenu
import com.tasteguys.foorrng_customer.presentation.model.TruckWithFavorite

object Dummy{
    val category = mutableListOf(
        "햄버거", "아이스크림", "곱창 & 막창",
        "치킨", "디저트 & 커피", "분식",
        "케밥 & 타코", "닭꼬치", "핫도그",
        "타코야끼", "츄러스", "스테이크"
    )

    val trucks = mutableListOf(
        TruckWithFavorite(
            0, 0, "야미족발", "", true, 17, 17, listOf(
                "족발"
            )
        ),
        TruckWithFavorite(
            0, 0, "야미족발2", "", false, 18, 18, listOf(
                "치킨"
            )
        ),
    )

    val truckInfo = TruckInfo(
        0, "야미족발", "", listOf("치킨"), "12가1234", "010-1234-1234",
        "", "1234-1234-1234", mutableListOf(
            TruckMenu("닭꼬치", 3000, ""),
            TruckMenu("떡꼬치", 3000, ""),
            TruckMenu("산적꼬치", 3000, ""),
            TruckMenu("은행꼬치", 3000, ""),
            TruckMenu("파꼬치", 3000, ""),
        )
    )

}