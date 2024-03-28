//package com.tasteguys.foorrng_owner.presentation.location
//
//import android.app.Dialog
//import android.content.Context
//import android.graphics.Color
//import android.graphics.drawable.ColorDrawable
//import android.os.Bundle
//import android.view.LayoutInflater
//import com.tasteguys.foorrng_owner.presentation.databinding.DialogAddRundayBinding
//import com.tasteguys.foorrng_owner.presentation.model.run.RunDay
//import java.time.DayOfWeek
//
//
//class AddRundayDialog(
//    private val context: Context,
//    private val selectedDay: DayOfWeek,
//) :
//    Dialog(context) {
//
//    private var binding: DialogAddRundayBinding = DialogAddRundayBinding.inflate(LayoutInflater.from(context))
//    private var cancelListener: (dialog: Dialog) -> Unit = { _ -> }
//    private var registListener: (dialog: Dialog, RunDay) -> Unit = { _, _ -> }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        // 다이얼로그의 기본 배경색을 투명하게
//        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//        applyCreateListener()
//        applyCancleListener()
//    }
//
//    private fun applyCancleListener() {
//        binding.tvCancel.setOnClickListener {
//            cancelListener(this)
//        }
//    }
//
//    private fun applyCreateListener() {
//        binding.tvRegist.setOnClickListener {
//            registListener(this, RunDay(selectedDay, ))
//        }
//    }
//
//
//    fun setCancelListener(cancelListener: (dialog: Dialog) -> Unit): AddRundayDialog{
//        this.cancelListener = cancelListener
//        applyCancleListener()
//        return this
//    }
//
//    fun setCreateListener(registListener: (dialog: Dialog, runDay: RunDay) -> Unit): AddRundayDialog{
//        this.registListener = registListener
//        applyCreateListener()
//        return this
//    }
//}