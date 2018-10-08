package miraikeitai2018.github.com.recyclerviewsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    // RecyclerViewのアダプターにセットするlistener
    private val listener = object : MainRecyclerAdapter.OnMainItemClickListener {
        // RecyclerViewの要素がタップされた時に呼ばれる
        override fun onItemClick(title: String) {
            val msg = title + "がタップされました"
            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
        }
    }

    private var recyclerAdapter: MainRecyclerAdapter = MainRecyclerAdapter(listener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
        val items = createItems()
        showList(items)
    }

    // RecyclerViewの初期設定
    private fun setUpRecyclerView() {
        recyclerView = findViewById(R.id.recycler)

        // applyを使うとまとめて設定できるので便利
        recyclerView.apply {

            // パフォーマンスが上がる
            setHasFixedSize(true)
            // アダプターを設定
            adapter = recyclerAdapter

            // リストに仕切りを表示する
            val manager = LinearLayoutManager(context)
            layoutManager = manager
            val dividerItemDecoration = DividerItemDecoration(this.context,
                    manager.orientation)
            addItemDecoration(dividerItemDecoration)
        }
    }

    // リストに表示するダミーデータを50個作る
    private fun createItems(): List<Item> {
        val items = ArrayList<Item>()
        val imgUrl = "https://www.scripts-lab.co.jp/images/Android_Robot_200.png"
        for (i in 1..50) {
            val title = "%d番目".format(i)
            items.add(Item(title, imgUrl))
        }
        return items.toList()
    }

    // RecyclerViewでリストを表示する
    private fun showList(items: List<Item>) {
        // Adapterに表示する内容を追加
        recyclerAdapter.items.addAll(items)
        // 表示内容に変更があったことをAdapterに通知する
        recyclerAdapter.notifyDataSetChanged()
    }
}
