package android.br.com.lebronx.kodeapp.Adapter


import android.br.com.lebronx.kodeapp.Model.DataResult
import android.br.com.lebronx.kodeapp.R
import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MyRecyclerAdapter(var context: Context): RecyclerView.Adapter<MyRecyclerAdapter.rViewHolder>() {

    var resultList : List<DataResult> = listOf()
    lateinit var mClickListener: ClickListener
    lateinit var toast : Toast


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recylcerview_adapter, parent, false)
        return rViewHolder(view)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: rViewHolder, position: Int) {
        holder.txtName.text = resultList.get(position).title
        Glide.with(context).load(resultList.get(position).image)
                .apply(RequestOptions().centerCrop())
                .into(holder.txtImage)
        // Old method for click in recyclerview
        /*holder.itemView.image.setOnClickListener(object : View.OnClickListener{
             @SuppressLint("ShowToast")
             override fun onClick(v: View?) {
                 toast = Toast.makeText(context, holder.txtName.text, Toast.LENGTH_LONG)
                 toast.setGravity(Gravity.CENTER, 0, 0)
                 toast.show()
             }
        })*/
    }

    inner class rViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val txtName: TextView = itemView!!.findViewById(R.id.name)
        val txtImage: ImageView = itemView!!.findViewById(R.id.image)
        override fun onClick(v:View){
            mClickListener.onClick(adapterPosition, v)
            Toast.makeText(context, txtName.text, Toast.LENGTH_LONG).show()
        }

        init {
            // itemView.setOnClickListener(this)
            txtImage.setOnClickListener(this)
        }
    }

    fun setListItems(listResults: List<DataResult>){
        this.resultList = listResults;
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }

    fun getId(position: Int){
        resultList.get(position)
    }
}





