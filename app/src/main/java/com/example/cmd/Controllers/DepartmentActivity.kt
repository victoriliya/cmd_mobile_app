package com.example.cmd.Controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmd.Adapters.DepartmentRecyclerView
import com.example.cmd.R
import com.example.cmd.Services.Data
import kotlinx.android.synthetic.main.activity_department.*
import kotlinx.android.synthetic.main.activity_gallery.*

class DepartmentActivity : AppCompatActivity() {

    lateinit var adapter: DepartmentRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_department)

        adapter = DepartmentRecyclerView(this, Data.department){departments ->
            println(departments.departmentName)
        }
        departmentView.adapter = adapter

        val linearLayoutManager =  LinearLayoutManager(this)

        departmentView.layoutManager = linearLayoutManager
        departmentView.setHasFixedSize(true)

    }
}
