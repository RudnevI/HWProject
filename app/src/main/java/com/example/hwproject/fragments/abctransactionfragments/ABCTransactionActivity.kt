package com.example.hwproject.fragments.abctransactionfragments


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hwproject.R

class ABCTransactionActivity : AppCompatActivity(), FragmentA.CallbacksFragmentA, FragmentB.CallbacksFragmentB {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abctransaction)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_abc)
        if(currentFragment == null) {
            val fragment = FragmentA()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_abc, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onTransactButtonClickFragmentA() {
        val fragmentB = FragmentB()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_abc, fragmentB)
            .addToBackStack(null)
            .commit()
    }

    override fun onTransactButtonClickFragmentB() {
        val fragmentC = FragmentC()

        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()

            .replace(R.id.fragment_container_abc, fragmentC)
            .addToBackStack(null)

            .commit()
    }
}