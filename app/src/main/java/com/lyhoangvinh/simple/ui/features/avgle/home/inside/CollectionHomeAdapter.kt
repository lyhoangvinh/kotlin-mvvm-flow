package com.lyhoangvinh.simple.ui.features.avgle.home.inside

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import androidx.recyclerview.widget.DiffUtil
import com.lyhoangvinh.simple.BR
import com.lyhoangvinh.simple.R
import com.vinh.domain.model.entities.avgle.Collection
import com.lyhoangvinh.simple.databinding.ItemCollectionHomeBinding
import com.lyhoangvinh.simple.ui.base.adapter.BaseListAdapter
import com.lyhoangvinh.simple.ui.base.adapter.BaseViewHolder
import dagger.hilt.android.qualifiers.ActivityContext


class CollectionHomeAdapter(@ActivityContext context: Context) :
    BaseListAdapter<Collection, ItemCollectionHomeBinding>(
        context,
        DiffCallBack
    ) {

    private var mWidth = 0

    private var mHeight = 0

    fun setLayoutParams(mWidth: Int, mHeight: Int): CollectionHomeAdapter {
        this.mWidth = mWidth
        this.mHeight = mHeight
        return this
    }

    private var onClickItemListener: ((Collection) -> Unit?)? = null

    fun setOnClickItemListener(onClickItemListener: (Collection) -> Unit): CollectionHomeAdapter {
        this.onClickItemListener = onClickItemListener
        return this
    }


    override fun itemLayoutResource() = R.layout.item_collection_home

    override fun createViewHolder(itemView: View) = CollectionViewHolder(itemView)

    override fun onBindViewHolder(binding: ItemCollectionHomeBinding, dto: Collection, position: Int) {
        binding.imv.layoutParams.width = mWidth
        binding.imv.layoutParams.height = mHeight
        binding.lnlMain.layoutParams = RelativeLayout.LayoutParams(mWidth, RelativeLayout.LayoutParams.WRAP_CONTENT)
        binding.imv.requestLayout()
        binding.lnlMain.setOnClickListener { onClickItemListener?.invoke(dto) }
        binding.setVariable(BR.collection, dto)
    }

    class CollectionViewHolder(itemView: View) : BaseViewHolder<ItemCollectionHomeBinding>(itemView)

    private object DiffCallBack : DiffUtil.ItemCallback<Collection>() {
        override fun areItemsTheSame(currentItem: Collection, nextItem: Collection): Boolean {
            return currentItem.idCLS == nextItem.idCLS
        }

        override fun areContentsTheSame(currentItem: Collection, nextItem: Collection): Boolean {
            return currentItem == nextItem
        }
    }
}
