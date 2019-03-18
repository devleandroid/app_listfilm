package android.br.com.lebronx.kodeapp.Activity

import android.app.ProgressDialog
import android.br.com.lebronx.kodeapp.Adapter.MyRecyclerAdapter
import android.br.com.lebronx.kodeapp.Interface.ApiInterface
import android.br.com.lebronx.kodeapp.Model.DataResult
import android.br.com.lebronx.kodeapp.R
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.recycler_film.*
import kotlinx.android.synthetic.main.recylcerview_adapter.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.concurrent.timerTask

class ListFilm : AppCompatActivity() {

    lateinit var progerssProgressDialog: ProgressDialog
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: MyRecyclerAdapter
    lateinit var resultList: List<DataResult>
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var snackbar: Snackbar
    var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_film)

        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerAdapter = MyRecyclerAdapter(this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        recyclerAdapter.setOnItemClickListener(object : MyRecyclerAdapter.ClickListener {
            override fun onClick(position: Int, v: View) {
                recyclerAdapter.notifyDataSetChanged()
            }
        })

        progerssProgressDialog=ProgressDialog(this)
        progerssProgressDialog.setTitle("Carregando ... ")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()

        getResultTypes()
    }

    fun getResultTypes(){
        val apiService = ApiInterface.create().getResults()

        apiService.enqueue(object : Callback<List<DataResult>> {
            override fun onResponse(call: Call<List<DataResult>>, response: Response<List<DataResult>>) {
                if (response?.body() != null)
                    recyclerAdapter.setListItems(response.body()!!)

                else
                    Toast.makeText(applicationContext, response.toString(), Toast.LENGTH_LONG).show()
                Log.d("Error_Response", response.toString())
                progerssProgressDialog.dismiss()
            }

            override fun onFailure(call: Call<List<DataResult>>, t: Throwable) {
                progerssProgressDialog.dismiss()
                call.toString()
                Log.d("Error_Response", "throwable: " + t.toString() + " - call_error: " + call.toString())
            }
        })
    }

    override fun onBackPressed() {

        if (doubleBackToExitPressedOnce){
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        snackbar = Snackbar.make(coordinatorLayout, "Clique novamente para sair", Snackbar.LENGTH_LONG)
        snackbar.show()
        Timer().schedule(timerTask { doubleBackToExitPressedOnce }, 3000)

    }
}