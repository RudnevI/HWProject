package com.example.hwproject.rx


import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hwproject.R
import com.example.hwproject.rx.model.Comment
import com.example.hwproject.rx.model.Gender
import com.example.hwproject.rx.model.User
import com.example.hwproject.rx.model.Users
import com.example.hwproject.rx.recycler.CommentsAdapter
import com.example.hwproject.rx.recycler.RecyclerAdapter
import com.example.hwproject.rx.retrofit.RetrofitClient
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RXActivity : AppCompatActivity(), AdapterView.OnItemClickListener{

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapter
    private lateinit var progressBar: ProgressBar
    lateinit var commentsAdapter: CommentsAdapter

    private lateinit var createUserButton: AppCompatButton

    private var userGenderSelected: Gender = Gender.male



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxactivity)

        recyclerView = findViewById(R.id.userRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter
        commentsAdapter = CommentsAdapter()
        progressBar = findViewById(R.id.progressBar)

        val spinner: Spinner = findViewById(R.id.gender_menu)
        ArrayAdapter.createFromResource(
            this,
            R.array.gender,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }



        fetchUsers()
        createUserButton = findViewById(R.id.create_user_button)
        createUserButton.setOnClickListener {
            addUser()
        }
    }

    private inner class Wrapper(
        val users: ArrayList<User>,
        val comments: ArrayList<Comment>
    )

/*    private fun fetchData() {

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

    }*/

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

    private fun fetchUserFromData(): User {
        return User(

            name = findViewById<EditText>(R.id.user_name).text.toString(),
            email = findViewById<EditText>(R.id.user_email).text.toString(),
            gender = userGenderSelected

        )
    }

    private fun addUser() {
        progressBar.visibility = View.VISIBLE
        RetrofitClient.createUser(
            fetchUserFromData()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<User> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: User) {

                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {
                    fetchUsers()
                }


            })
    }

    private fun setUsers(list: ArrayList<User>) {
        adapter.setData(list)
    }

    private fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(id == 0L) userGenderSelected = Gender.female
    }


}