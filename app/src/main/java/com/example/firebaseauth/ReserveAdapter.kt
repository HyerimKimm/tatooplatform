package com.example.firebaseauth

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauth.databinding.ReservationitemBinding
import com.google.firebase.firestore.QueryDocumentSnapshot

data class ReserveItem(val id: String, val pagename: String, val date: String, val state: String){
    constructor(doc: QueryDocumentSnapshot) :
            this(doc.id, doc["pagename"].toString(), doc["date"].toString(), doc["state"].toString())
}

class ReserveViewHolder(val binding: ReservationitemBinding) : RecyclerView.ViewHolder(binding.root)

class ReserveAdapter(private val context: Context, private var reserveitems: List<ReserveItem>)
    : RecyclerView.Adapter<ReserveViewHolder>() {
    fun interface OnItemClickListener {
        fun onItemClick(student_id: String)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    fun updateList(newList: List<ReserveItem>) {
        reserveitems = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReserveViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ReservationitemBinding = ReservationitemBinding.inflate(inflater, parent, false)
        return ReserveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReserveViewHolder, position: Int) {
        val reserveItem = reserveitems[position]
        holder.binding.textPagename.text = reserveItem.pagename
        holder.binding.textDate.text = reserveItem.date
        holder.binding.textState.text = reserveItem.state
        holder.binding.textPagename.setOnClickListener {
            //AlertDialog.Builder(context).setMessage("You clicked ${student.name}.").show()
            itemClickListener?.onItemClick(reserveItem.pagename)
        }
        holder.binding.textDate.setOnClickListener {
            //AlertDialog.Builder(context).setMessage("You clicked ${student.name}.").show()
            itemClickListener?.onItemClick(reserveItem.date)
        }
        holder.binding.textState.setOnClickListener {
            //AlertDialog.Builder(context).setMessage("You clicked ${student.name}.").show()
            itemClickListener?.onItemClick(reserveItem.state)
        }
    }

    override fun getItemCount() = reserveitems.size
}