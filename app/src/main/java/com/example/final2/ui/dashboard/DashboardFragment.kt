package com.example.final2.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.grocerylist.data.db.ResourceDatabase
import com.example.final2.*
import com.example.final2.databinding.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashboardFragment : Fragment() {
//    override val kodein by kodein()
    val database = activity?.let { ResourceDatabase(it) }
    val repository = database?.let { ResourceRepository(it) }
    val factory = repository?.let { ResourceViewModelFactory(it) }
    var viewModel = factory?.let { ViewModelProvider(this, it) }?.get(ResourceViewModel::class.java)

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView : RecyclerView = root.findViewById(R.id.rvResourceItems)
        val adapter = viewModel?.let { ResourceItemAdapter(listOf(), it) }

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        viewModel?.getAllResourceItems()?.observe(viewLifecycleOwner) {
            adapter?.items = it
            adapter?.notifyDataSetChanged()
        }

        fab.setOnClickListener {
            context?.let { it1 ->
                AddResourceItemDialog(
                    it1,
                    object : AddDialogListener {
                        override fun onAddButtonClicked(item: ResourceItem) {
                            println(item.toString())
                            viewModel?.upsert(item)
                        }
                    }).show()
            }
        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


