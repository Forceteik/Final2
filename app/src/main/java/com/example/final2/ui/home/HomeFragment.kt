package com.example.final2.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final2.*
import com.example.final2.databinding.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), onCharacterClickListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        val recyclerView : RecyclerView = binding.myRecyclerView

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getPosts()

        call.enqueue(object: Callback<MutableList<PostModel>> {
            override fun onResponse(
                call: Call<MutableList<PostModel>>,
                response: Response<MutableList<PostModel>>
            ) {
                if(response.isSuccessful){
                    recyclerView.apply {
                        layoutManager = GridLayoutManager(activity,2)
                        adapter = PostAdapter(response.body()!!, this@HomeFragment)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<PostModel>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onCharClick(character: PostModel, position: Int) {
        val intent = Intent(activity, CharacterActivity::class.java)
        intent.putExtra("name", character.name)
        intent.putExtra("description", character.description)
        intent.putExtra("weapon", character.weapon)
        intent.putExtra("vision", character.vision)
        intent.putExtra("weapon", character.weapon)
        intent.putExtra("nation", character.nation)
        intent.putExtra("affiliation", character.affiliation)
        intent.putExtra("constellation", character.constellation)
        intent.putExtra("birthday", character.birthday)
        println(character.skills.toString())
//        intent.putParcelableArrayListExtra("skills", character.skills)
        startActivity(intent)
    }
}