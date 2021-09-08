package com.example.hwproject.lesson_10.ui.dashboard


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hwproject.R
import com.example.hwproject.lesson_10.model.PaymentsModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentsFragment : Fragment() {



    private val paymentsModel: PaymentsModel by inject()
    private  val paymentsViewModel: PaymentsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.payments_fragment, container, false)
    }

    // TODO: 08.09.2021 custom widgets

    override fun onStart() {
        super.onStart()
        view?.findViewById<TextView>(R.id.payment)?.text = paymentsModel.payments.first()
    }


}