package com.example.hwproject.fragments.abctransactionfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hwproject.R
import com.example.hwproject.databinding.TransactionFragmentABinding

class FragmentA : Fragment() {

    interface CallbacksFragmentA {
        fun onTransactButtonClickFragmentA()
    }

    private lateinit var binding: TransactionFragmentABinding

    private var callbacks: CallbacksFragmentA? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.transaction_fragment_a, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TransactionFragmentABinding.bind(view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as CallbacksFragmentA?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onStart() {
        super.onStart()
        binding.goToFragmentBButton.setOnClickListener {
            callbacks?.onTransactButtonClickFragmentA()
        }

    }

}