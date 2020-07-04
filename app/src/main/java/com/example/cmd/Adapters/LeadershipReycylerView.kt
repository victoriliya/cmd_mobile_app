package com.example.cmd.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmd.Models.Leadership
import com.example.cmd.R
import kotlinx.android.synthetic.main.leadership_activity.view.*

class LeadershipRecyclerView(val context: Context,val leadershipList: List<Leadership>) : RecyclerView.Adapter<LeadershipRecyclerView.Holder>() {

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){

        val leaderImage = itemView?.findViewById<ImageView>(R.id.leadershipImage)
        val leadershipName = itemView?.findViewById<TextView>(R.id.leadershipName)
        val leadershipPosition = itemView?.findViewById<TextView>(R.id.leadershipPosition)

        fun bindItem(context: Context, leader: Leadership){
            val resourceId = context.resources.getIdentifier(leader.image, "drawable", context.packageName )
            leaderImage?.setImageResource(resourceId)
            leadershipName?.text = leader.name
            leadershipPosition?.text = leader.position
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.leadership_activity, parent, false)
        return Holder(view)
    }


    override fun getItemCount(): Int {
        return leadershipList.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItem(context, leadershipList[position])
    }

}