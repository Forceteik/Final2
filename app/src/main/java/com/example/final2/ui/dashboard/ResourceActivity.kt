package com.example.final2.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.grocerylist.data.db.ResourceDatabase
import com.example.final2.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ResourceActivity: AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    val database = ResourceDatabase(this)
    val repository = ResourceRepository(database)
    val factory = ResourceViewModelFactory(repository)
    var viewModel = ViewModelProviders.of(this, factory)[ResourceViewModel::class.java]

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_dashboard)


        val adapter = ResourceItemAdapter(listOf(), viewModel)

        rvResourceItems.layoutManager = LinearLayoutManager(this)
        rvResourceItems.adapter = adapter

        viewModel.getAllResourceItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddResourceItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ResourceItem) {
                        println(item.toString())
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}