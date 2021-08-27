package com.example.hwproject.rx


import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hwproject.R
import com.example.hwproject.rx.model.User
import com.example.hwproject.rx.model.Users
import com.example.hwproject.rx.recycler.RecyclerAdapter
import com.example.hwproject.rx.retrofit.RetrofitClient
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import kotlin.collections.ArrayList

class RXActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapter
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxactivity)
        recyclerView = findViewById(R.id.userRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter


        progressBar = findViewById(R.id.progressBar)

        fetchUsers()
    }

    private fun fetchUsers() {
        progressBar.visibility = View.VISIBLE
        RetrofitClient.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<Users>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Users) {
                   setUsers(t.data)
                }

                override fun onError(e: Throwable) {
                    hideProgress()
                    println(e)
                }

                override fun onComplete() {
                    hideProgress()
                }

            })

    }

    private fun setUsers(list: ArrayList<User>) {
        adapter.setData(list)
    }

    private fun hideProgress() {
        progressBar.visibility = View.GONE
    }


}