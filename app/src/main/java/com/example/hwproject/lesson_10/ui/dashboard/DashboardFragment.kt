package com.example.hwproject.lesson_10.ui.dashboard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hwproject.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {


    private val dashboardViewModel: DashboardViewModel by activityViewModels()
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

       binding.goToPayments.setOnClickListener {
           val direction = DashboardFragmentDirections.actionNavigationDashboardToPaymentsFragment()
           findNavController().navigate(direction)
       }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}