package com.example.nursultan.testapp.ui.photo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.nursultan.testapp.R
import com.example.nursultan.testapp.dummy.DummyContent
import com.example.nursultan.testapp.ui.base.BaseViewHolder
import com.squareup.picasso.Picasso
import java.util.*
import javax.inject.Inject


class PhotoAdapter @Inject
constructor(private val picasso: Picasso) : RecyclerView.Adapter<BaseViewHolder>() {

    private var mCallback: Callback? = null
    private val photoList: MutableList<DummyContent.DummyItem>?

    init {
        photoList = ArrayList<DummyContent.DummyItem>()
    }

    fun setCallback(callback: Callback) {
        mCallback = callback
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false))
    }

    override fun getItemCount(): Int {
        return if (photoList != null && photoList.size > 0) {
            photoList.size
        } else {
            0
        }
    }

    private fun getItem(pos: Int): DummyContent.DummyItem {
        return photoList!![pos]
    }

    fun swapItems(photos: List<DummyContent.DummyItem>) {
        photoList!!.clear()
        photoList.addAll(photos)
        notifyDataSetChanged()
    }

    interface Callback {
        fun onPhotoClick(item: DummyContent.DummyItem)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {

        @BindView(R.id.photo_tv)
        lateinit var _photo_tv: TextView
        @BindView(R.id.cell_iv)
        lateinit var cell_iv: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun clear() {
            cell_iv.setImageDrawable(null)
            _photo_tv.text = ""
        }

        override fun onBind(position: Int) {
            super.onBind(position)

            val item = photoList!![position]
            picasso.load(item.path).into(cell_iv)

            _photo_tv.text = item.title


            itemView.setOnClickListener {
                val pos = adapterPosition
                if (mCallback != null) {
                    val instance = getItem(pos)
                    mCallback!!.onPhotoClick(instance)
                }
            }
        }
    }
}

