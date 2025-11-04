package com.github.kououdev.crudroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.kououdev.crudroom.database.AppDatabase
import com.github.kououdev.crudroom.databinding.ActivityUserFormBinding
import com.github.kououdev.crudroom.entity.User

class UserFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserFormBinding
    private lateinit var db: AppDatabase
    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)

        val userId = intent.getIntExtra("userId", 0)
        if (userId != 0) {
            currentUser = db.userDao().getAll().find { it.id == userId }
            binding.edtName.setText(currentUser?.name)
            binding.edtEmail.setText(currentUser?.email)
        }

        binding.btnSave.setOnClickListener {
            val name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()

            if (currentUser == null) {
                db.userDao().insert(User(name = name, email = email))
            } else {
                currentUser!!.name = name
                currentUser!!.email = email
                db.userDao().update(currentUser!!)
            }
            finish()
        }

        binding.btnDelete.setOnClickListener {
            currentUser?.let { db.userDao().delete(it) }
            finish()
        }
    }
}