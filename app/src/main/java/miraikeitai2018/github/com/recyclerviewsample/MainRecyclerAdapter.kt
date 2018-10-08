package miraikeitai2018.github.com.recyclerviewsample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso

class MainRecyclerAdapter(val listener: OnMainItemClickListener) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    val items = ArrayList<Item>()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val v = LayoutInflater.from(context)
                .inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // タイトルの表示
        holder.title.text = items[position].title

        // アイコンの表示
        val image = items[position].icon
        Picasso.get().load(image).into(holder.icon) // Picassoは画像ライブラリです

        // リストがタップされた時の処理を追加
        holder.content.setOnClickListener {
            listener.onItemClick(items[position].title)
        }
    }

    // Adapterを使うActivity/Fragmentに実装させる
    interface OnMainItemClickListener {
        fun onItemClick(title: String)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val content = v.findViewById(R.id.list_content) as LinearLayout
        val title = v.findViewById(R.id.title) as TextView
        val icon = v.findViewById(R.id.icon) as ImageView
    }
}