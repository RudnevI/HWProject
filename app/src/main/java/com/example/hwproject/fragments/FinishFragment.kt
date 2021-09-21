package com.example.hwproject.fragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.hwproject.R
import com.example.hwproject.databinding.FragmentFinishBinding



class FinishFragment : Fragment() {

    val args: FinishFragmentArgs by navArgs()

    private lateinit var binding: FragmentFinishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finish, container, false)

    }

    override fun onStart() {
        super.onStart()
        view?.findViewById<TextView>(R.id.finishFragmentTextView)?.text = args.title
        view?.findViewById<TextView>(R.id.subtitle)?.text = args.subtitle
    }

}