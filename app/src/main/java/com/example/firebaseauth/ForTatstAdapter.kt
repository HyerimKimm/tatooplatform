package com.example.firebaseauth

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauth.databinding.PageitemBinding
import com.example.firebaseauth.databinding.ReservefortatooistitemBinding
import com.google.firebase.firestore.QueryDocumentSnapshot

data class ReserveForTatooistItem(val id: String, val cid: String, val date: String, val design: String, val state: String){
    constructor(doc: QueryDocumentSnapshot) :
            this(doc.id, doc["cid"].toString(), doc["date"].toString(), doc["design"].toString(), doc["state"].toString())
}

class ForTatstViewHolder(val binding: ReservefortatooistitemBinding) : RecyclerView.ViewHolder(binding.root)

class ForTatstAdapter(private val context: Context, private var reservefortatooistitems: List<ReserveForTatooistItem>)
    : RecyclerView.Adapter<ForTatstViewHolder>() {
    fun interface OnItemClickListener {
        fun onItemClick(student_id: String)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    fun updateList(newList: List<ReserveForTatooistItem>) {
        reservefortatooistitems = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForTatstViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ReservefortatooistitemBinding = ReservefortatooistitemBinding.inflate(inflater, parent, false)
        return ForTatstViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForTatstViewHolder, position: Int) {
        val item = reservefortatooistitems[position]
        holder.binding.textCid.text = item.cid
        holder.binding.textDate.text = item.date
        holder.binding.textDesign.text = item.design
        holder.binding.textState.text = item.state
        holder.binding.textCid.setOnClickListener {
            //AlertDialog.Builder(context).setMessage("You clicked ${student.name}.").show()
            itemClickListener?.onItemClick(item.id)
        }
        holder.binding.textState.setOnClickListener {
            //AlertDialog.Builder(context).setMessage("You clicked ${student.name}.").show()
            itemClickListener?.onItemClick(item.id)
        }
        holder.binding.textDate.setOnClickListener {
            //AlertDialog.Builder(context).setMessage("You clicked ${student.name}.").show()
            itemClickListener?.onItemClick(item.id)
        }
        holder.binding.textDesign.setOnClickListener {
            //AlertDialog.Builder(context).setMessage("You clicked ${student.name}.").show()
            itemClickListener?.onItemClick(item.id)
        }
    }

    override fun getItemCount() = reservefortatooistitems.size
}