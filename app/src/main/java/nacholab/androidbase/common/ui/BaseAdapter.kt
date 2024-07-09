package nacholab.androidbase.common.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(
    private val context: Context,
    private val listener: Listener<T>?,
    itemComparator: DiffUtil.ItemCallback<T>,
    private val enableOnLongClick: Boolean = true
): ListAdapter<T, BaseViewHolder>(itemComparator){

    companion object{
        fun <T : Any>buildItemComparator(
            comparatorItemsTheSame: ((T, T) -> Boolean)? = null,
            comparatorContentsTheSame: ((T, T) -> Boolean)? = null
        ) = object: DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T) =
                comparatorItemsTheSame?.invoke(oldItem, newItem)?:(oldItem == newItem)

            override fun areContentsTheSame(oldItem: T, newItem: T) =
                comparatorContentsTheSame?.invoke(oldItem, newItem)?:(oldItem == newItem)
        }
    }

    abstract class Listener<T> {
        open fun onItemSelected(item: T){}
        open fun onItemLongClicked(item: T){}
    }

    abstract fun buildView(context: Context, viewType: Int = 0): View
    abstract fun bindView(view: View, item: T, position: Int = 0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(buildView(context, viewType))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val view = holder.itemView
        val item = getItem(position)

        bindView(view, item, position)
        if (listener != null){
            view.setOnClickListener { listener.onItemSelected(item) }
            if (enableOnLongClick) view.setOnLongClickListener {
                listener.onItemLongClicked(item)
                true
            }
        }
    }
}

class BaseViewHolder(v: View): RecyclerView.ViewHolder(v)