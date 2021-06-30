package kg.tutorialapp.forecast.ui.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tutorialapp.forecast.models.DailyForeCast

class DailyForeCastAdapter: RecyclerView.Adapter<DailyForeCastVH>() {

    private val items = arrayListOf<DailyForeCast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForeCastVH {
        return DailyForeCastVH.create(parent)
    }

    override fun onBindViewHolder(holder: DailyForeCastVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()

    fun setItemsDaily(newItems: List<DailyForeCast>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}