package com.github.kououdev.crudroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.github.kououdev.crudroom.adapter.UserAdapter
import com.github.kououdev.crudroom.database.AppDatabase
import com.github.kououdev.crudroom.entity.User
import com.github.kououdev.crudroom.repository.UserRepository

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var repo: UserRepository
    private lateinit var adapter: UserAdapter
    private lateinit var rvUsers: RecyclerView
    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getInstance(this)
        repo = UserRepository(db.userDao())

        rvUsers = findViewById(R.id.rvUsers)
        btnAdd = findViewById(R.id.btnAdd)

        adapter = UserAdapter(listOf())
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = adapter

        loadUsers()

        btnAdd.setOnClickListener {
            lifecycleScope.launch {
                val name = "User ${System.currentTimeMillis() % 1000}"
                val email = "$name@example.com"
                repo.insert(User(name = name, email = email))
                Toast.makeText(this@MainActivity, "User ditambah", Toast.LENGTH_SHORT).show()
                loadUsers()
            }
        }
    }

    private fun loadUsers() {
        lifecycleScope.launch {
            val users = repo.getAll()
            adapter.updateData(users)
        }
    }
}