package com.github.kououdev.crudroom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kououdev.crudroom.adapter.UserAdapter
import com.github.kououdev.crudroom.database.AppDatabase
import com.github.kououdev.crudroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)
        adapter = UserAdapter(mutableListOf()) { user ->
            val intent = Intent(this, UserFormActivity::class.java)
            intent.putExtra("userId", user.id)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, UserFormActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.updateData(db.userDao().getAll())
    }
}