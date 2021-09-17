package com.example.hwproject.fragments.abctransactionfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hwproject.R
import com.example.hwproject.databinding.TransactionFragmentCBinding

class FragmentC : Fragment() {


    private lateinit var binding: TransactionFragmentCBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.transaction_fragment_c, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TransactionFragmentCBinding.bind(view)
    }




}