package kg.tutorialapp.forecast.ui.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tutorialapp.forecast.models.HourlyForeCast

class HourlyForeCastAdapter: RecyclerView.Adapter<HourlyForeCastVH>(){

    private val items = arrayListOf<HourlyForeCast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForeCastVH {
        return HourlyForeCastVH.create(parent)
    }

    override fun getItemCount()= items.count()


    override fun onBindViewHolder(holder: HourlyForeCastVH, position: Int) {
        holder.bind(items[position])
    }

    fun setItemsHourly(newItems: List<HourlyForeCast>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}