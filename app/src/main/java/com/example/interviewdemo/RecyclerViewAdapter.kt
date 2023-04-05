package com.example.interviewdemo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val context: Context, private val pinCodeResponse: List<PostOfficeList>) :
    RecyclerView.Adapter<PincodeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PincodeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pincode_item, parent, false)
        return PincodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: PincodeViewHolder, position: Int) {
        val currentItem = pinCodeResponse[position]
        holder.idNum.text = (position + 1).toString()
        holder.name.text = currentItem.Name
        holder.pincode.text = "Pincode :  ${currentItem.Pincode}"
        holder.cardView.setOnClickListener {
            val intent = Intent(context, MapsActivity::class.java)
            intent.putExtra("PinCode",currentItem.Pincode)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return pinCodeResponse.size
    }

}

class PincodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val idNum = itemView.findViewById<TextView>(R.id.idnum)
    val name = itemView.findViewById<TextView>(R.id.name)
    val pincode = itemView.findViewById<TextView>(R.id.pinCode)
    val cardView = itemView.findViewById<ConstraintLayout>(R.id.cardView)
}