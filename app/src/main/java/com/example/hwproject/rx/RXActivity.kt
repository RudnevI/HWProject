package com.example.hwproject.rx


import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hwproject.R
import com.example.hwproject.rx.model.Comment
import com.example.hwproject.rx.model.User
import com.example.hwproject.rx.model.Users
import com.example.hwproject.rx.recycler.CommentsAdapter
import com.example.hwproject.rx.recycler.RecyclerAdapter
import com.example.hwproject.rx.retrofit.RetrofitClient
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RXActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapter
    private lateinit var progressBar: ProgressBar
    lateinit var commentsAdapter: CommentsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxactivity)

        recyclerView = findViewById(R.id.userRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter
        commentsAdapter = CommentsAdapter()
        progressBar = findViewById(R.id.progressBar)
        fetchUsers()
    }

    private inner class Wrapper(
        val users: ArrayList<User>,
        val comments: ArrayList<Comment>
    )

    private fun fetchData() {

        progressBar.visibility = View.VISIBLE

        Observable.zip(RetrofitClient.getUsers(),
        RetrofitClient.getComments(),
            { t, u -> Wrapper(t.data, u.data) }
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Wrapper>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Wrapper) {
                   hideProgress()
                    adapter.setData(t.users)
                    commentsAdapter.setData(t.comments)
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }

            })

    }

    private fun fetchUsers() {
        progressBar.visibility = View.VISIBLE
        RetrofitClient.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Users> {
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