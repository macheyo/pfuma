package com.materialstudies.reply.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.materialstudies.reply.R
import com.materialstudies.reply.databinding.ProductItemLayoutBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    private var productList = ArrayList<String>()
    private var clickListener: ClickListener? = null
    private var longClickListener: LongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun getItem(position: Int): String {
        return productList[position]
    }


    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    fun setOnItemLongClickListener(longClickListener: LongClickListener){
        this.longClickListener = longClickListener
    }

    fun setData(products: ArrayList<String>) {
        productList = products
        notifyDataSetChanged()
    }

    //to filter the list
    fun filterList(products: ArrayList<String>) {
        this.productList = products
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener  {
        private val binding = ProductItemLayoutBinding.bind(itemView)

        init {
            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }
            if (longClickListener != null){
                itemView.setOnLongClickListener(this)
            }
        }

        fun bind(productName: String) {
            binding.senderTextView.text = productName
            binding.subjectTextView.text = "US20"
            binding.bodyPreviewTextView.text = "10 units available"
        }

        override fun onClick(v: View?) {
            if (v != null) {
                clickListener?.onItemClick(v,adapterPosition)
            }
        }

        override fun onLongClick(v: View?): Boolean {
            if(v !=null){
                longClickListener?.onItemLongClick(v,adapterPosition)
            }
            return true
        }
    }

    interface ClickListener {
        fun onItemClick(v: View, position: Int)
    }

    interface LongClickListener {
        fun onItemLongClick(v: View, position: Int)
    }
}