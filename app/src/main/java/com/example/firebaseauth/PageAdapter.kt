package com.example.firebaseauth

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseauth.databinding.PageitemBinding
import com.google.firebase.firestore.QueryDocumentSnapshot

data class PageItem(val name: String, val intro: String, val address: String){
    constructor(doc: QueryDocumentSnapshot) :
            this(doc.id, doc["introduce"].toString(), doc["address"].toString())
    constructor(key: String, map: Map<*, *>) :
            this(key, map["introduce"].toString(), map["address"].toString())
}

class PageViewHolder(val binding: PageitemBinding) : RecyclerView.ViewHolder(binding.root)

class PageAdapter(private val context: Context, private var pageitems: List<PageItem>)
    : RecyclerView.Adapter<PageViewHolder>() {
    fun interface OnItemClickListener {
        fun onItemClick(student_id: String)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    fun updateList(newList: List<PageItem>) {
        pageitems = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: PageitemBinding = PageitemBinding.inflate(inflater, parent, false)
        return PageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val item = pageitems[position]
        holder.binding.textPagename.text = item.name
        holder.binding.textPageintro.text = item.intro
        holder.binding.textPageaddress.text = item.address
        holder.binding.textPagename.setOnClickListener {
            //AlertDialog.Builder(context).setMessage("You clicked ${student.name}.").show()
            itemClickListener?.onItemClick(item.name)
        }
        holder.binding.textPageintro.setOnClickListener {
            //AlertDialog.Builder(context).setMessage("You clicked ${student.name}.").show()
            itemClickListener?.onItemClick(item.name)
        }
        holder.binding.textPageaddress.setOnClickListener {
            //AlertDialog.Builder(context).setMessage("You clicked ${student.name}.").show()
            itemClickListener?.onItemClick(item.name)
        }
    }

    override fun getItemCount() = pageitems.size
}