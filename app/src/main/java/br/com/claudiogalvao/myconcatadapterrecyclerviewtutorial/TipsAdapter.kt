package br.com.claudiogalvao.myconcatadapterrecyclerviewtutorial

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.claudiogalvao.myconcatadapterrecyclerviewtutorial.TipsAdapter.*
import br.com.claudiogalvao.myconcatadapterrecyclerviewtutorial.databinding.ItemTipBinding

class TipsAdapter: ListAdapter<Tip, TipsViewHolder>(DIFF_CALLBACK) {

    var gotItItemClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipsViewHolder {
        return TipsViewHolder.create(parent, gotItItemClickListener)
    }

    override fun onBindViewHolder(holder: TipsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Tip>() {
            override fun areItemsTheSame(oldItem: Tip, newItem: Tip): Boolean {
                return oldItem.description == newItem.description
            }

            override fun areContentsTheSame(oldItem: Tip, newItem: Tip): Boolean {
                return oldItem == newItem
            }

        }
    }

    class TipsViewHolder(
        private val itemBinding: ItemTipBinding,
        private val gotItItemClickListener: (() -> Unit)?
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(tip: Tip) {
            itemBinding.run {
                textTip.text = tip.description
                textGotIt.setOnClickListener {
                    gotItItemClickListener?.invoke()
                }
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                gotItItemClickListener: (() -> Unit)?
            ): TipsViewHolder {
                val itemBinding = ItemTipBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return TipsViewHolder(itemBinding, gotItItemClickListener)
            }
        }

    }
}