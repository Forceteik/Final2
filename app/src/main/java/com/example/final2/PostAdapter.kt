package com.example.final2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.final2.ui.dashboard.DashboardFragment
import com.example.final2.ui.home.HomeFragment
import com.squareup.picasso.Picasso
import java.util.*


class PostAdapter(val postModel: MutableList<PostModel>, var clickListener: HomeFragment): RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.bindView(postModel[position],clickListener)

    }

    override fun getItemCount(): Int {
        return postModel.size
    }
}

class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    private val tvBody: TextView = itemView.findViewById(R.id.tvBody)
    private val tvImage: ImageView = itemView.findViewById(R.id.tvImage)

    @SuppressLint("SetTextI18n")
    fun bindView(postModel: PostModel, action:onCharacterClickListener) {

        itemView.setOnClickListener{
            action.onCharClick(postModel, adapterPosition)
        }
        tvTitle.text = postModel.name

        if (postModel.name == "Kamisato Ayaka") {
            Picasso.get().load("https://api.genshin.dev/characters/ayaka/card").transform(RoundedTransformation(100, 0)).into(tvImage);
        }
        else if (postModel.name == "Kaedehara Kazuha") {
            Picasso.get().load("https://api.genshin.dev/characters/kazuha/card").transform(RoundedTransformation(100, 0)).into(tvImage);
        }
        else if (postModel.name == "Sangonomiya Kokomi") {
            Picasso.get().load("https://api.genshin.dev/characters/kokomi/card").transform(RoundedTransformation(100, 0)).into(tvImage);
        }
        else if (postModel.name == "Raiden Shogun") {
            Picasso.get().load("https://api.genshin.dev/characters/raiden/card").transform(RoundedTransformation(100, 0)).into(tvImage);
        }
        else if (postModel.name == "Kujou Sara") {
            Picasso.get().load("https://api.genshin.dev/characters/sara/card").transform(RoundedTransformation(100, 0)).into(tvImage);
        }
        else if (postModel.name == "Traveler" && postModel.vision == "Anemo") {
            Picasso.get().load("https://api.genshin.dev/characters/traveler-anemo/card").transform(RoundedTransformation(100, 0)).into(tvImage);
        }
        else if (postModel.name == "Traveler" && postModel.vision == "Electro") {
            Picasso.get().load("https://api.genshin.dev/characters/traveler-electro/card").transform(RoundedTransformation(100, 0)).into(tvImage);
        }
        else if (postModel.name == "Traveler" && postModel.vision == "Geo") {
            Picasso.get().load("https://api.genshin.dev/characters/traveler-geo/card").transform(RoundedTransformation(100, 0)).into(tvImage);
        }
        else if (postModel.name == "Yae Miko") {
            Picasso.get().load("https://static.wikia.nocookie.net/gensin-impact/images/2/2a/Character_Yae_Miko_Card.png/revision/latest?cb=20211231161334").transform(RoundedTransformation(100, 0)).into(tvImage);
        }
        else {
            val name = postModel.name?.replace(' ','-')?.lowercase()
            Picasso.get().load("https://api.genshin.dev/characters/$name/card").transform(RoundedTransformation(100, 0)).into(tvImage);
        }

        if (postModel.rarity ==5) {
            tvBody.text = "⭐⭐⭐⭐⭐"
        }
        else{
            tvBody.text = "⭐⭐⭐⭐"}

        }
    }
interface onCharacterClickListener {
    fun onCharClick(character : PostModel,position: Int)
}
