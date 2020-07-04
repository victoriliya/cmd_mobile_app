package com.example.cmd.Controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cmd.Adapters.LeadershipRecyclerView
import com.example.cmd.R
import com.example.cmd.Services.Data
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    lateinit var adapter: LeadershipRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        adapter = LeadershipRecyclerView(this, Data.leaders)

        leadershipView.adapter = adapter
        val linearLayout  = LinearLayoutManager(this)

        leadershipView.layoutManager = linearLayout
        leadershipView.setHasFixedSize(true)

    }
}
