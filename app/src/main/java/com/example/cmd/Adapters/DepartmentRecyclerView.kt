package com.example.cmd.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cmd.Models.Departments
import com.example.cmd.R
import kotlinx.android.synthetic.main.department_activity.view.*
import java.util.zip.Inflater

class DepartmentRecyclerView(val context: Context, val departments: List<Departments>, val itemClick: (Departments) -> Unit) : RecyclerView.Adapter<DepartmentRecyclerView.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.department_activity, parent, false)
        return Holder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return departments.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindHolder(context, departments[position])
    }

    inner class Holder(itemView: View,val itemClick: (Departments) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val departmentName = itemView?.findViewById<TextView>(R.id.departmentName)
        fun bindHolder(context: Context, department: Departments){
            departmentName.text = department.departmentName
            itemView.setOnClickListener {
                itemClick(department)
            }
        }
    }

}