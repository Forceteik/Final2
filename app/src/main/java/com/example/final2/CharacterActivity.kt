package com.example.final2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class CharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        val name = findViewById<TextView>(R.id.name)
        val name1 = intent.getStringExtra("name")
        name.text = name1

        val description = findViewById<TextView>(R.id.description)
        description.text = intent.getStringExtra("description")

        val nation = findViewById<TextView>(R.id.nation)
        nation.text = "Nation: " + intent.getStringExtra("nation")
        val nation1 = intent.getStringExtra("nation")?.lowercase()
        val nation_icon = findViewById<ImageView>(R.id.nation_icon)
        Picasso.get().load("https://api.genshin.dev/nations/$nation1/icon").transform(RoundedTransformation(100, 0)).into(nation_icon);


        val affiliation = findViewById<TextView>(R.id.affiliation)
        affiliation.text = "Affiliation: " +intent.getStringExtra("affiliation")


        val birthday = findViewById<TextView>(R.id.birthday)
        birthday.text = "Birthday: " +intent.getStringExtra("birthday")


        val weapon = findViewById<TextView>(R.id.weapon)
        weapon.text = "Weapon: " +intent.getStringExtra("weapon")

        val constellation = findViewById<TextView>(R.id.constellation)
        constellation.text = "Constellation: " +intent.getStringExtra("constellation")

        val vision = findViewById<TextView>(R.id.vision)
        val vision1 = intent.getStringExtra("vision")
        val vision_icon = findViewById<ImageView>(R.id.vision_icon)
        val vision2 = intent.getStringExtra("vision")?.lowercase()
        Picasso.get().load("https://api.genshin.dev/elements/$vision2/icon").transform(RoundedTransformation(100, 0)).into(vision_icon);

        vision.text = "Vision: " +vision1
        val image = findViewById<ImageView>(R.id.image)
        if (name1 == "Kamisato Ayaka") {
            Picasso.get().load("https://api.genshin.dev/characters/ayaka/card").transform(RoundedTransformation(100, 0)).into(image);
        }
        else if (name1== "Kaedehara Kazuha") {
            Picasso.get().load("https://api.genshin.dev/characters/kazuha/card").transform(RoundedTransformation(100, 0)).into(image);
        }
        else if (name1 == "Sangonomiya Kokomi") {
            Picasso.get().load("https://api.genshin.dev/characters/kokomi/card").transform(RoundedTransformation(100, 0)).into(image);
        }
        else if (name1 == "Raiden Shogun") {
            Picasso.get().load("https://api.genshin.dev/characters/raiden/card").transform(RoundedTransformation(100, 0)).into(image);
        }
        else if (name1 == "Kujou Sara") {
            Picasso.get().load("https://api.genshin.dev/characters/sara/card").transform(RoundedTransformation(100, 0)).into(image);
        }
        else if (name1 == "Traveler" && vision1 == "Anemo") {
            Picasso.get().load("https://api.genshin.dev/characters/traveler-anemo/card").transform(RoundedTransformation(100, 0)).into(image);
        }
        else if (name1 == "Traveler" && vision1 == "Electro") {
            Picasso.get().load("https://api.genshin.dev/characters/traveler-electro/card").transform(RoundedTransformation(100, 0)).into(image);
        }
        else if (name1 == "Traveler" && vision1 == "Geo") {
            Picasso.get().load("https://api.genshin.dev/characters/traveler-geo/card").transform(RoundedTransformation(100, 0)).into(image);
        }
        else if (name1== "Yae Miko") {
            Picasso.get().load("https://static.wikia.nocookie.net/gensin-impact/images/2/2a/Character_Yae_Miko_Card.png/revision/latest?cb=20211231161334").transform(RoundedTransformation(100, 0)).into(image);
        }
        else {
            val name = name1?.replace(' ','-')?.lowercase()
            Picasso.get().load("https://api.genshin.dev/characters/$name/card").transform(RoundedTransformation(100, 0)).into(image);
        }

//        val normal = findViewById<TextView>(R.id.normal)
//        normal.text = intent.getParcelableExtra("skills")
//        val skill = findViewById<TextView>(R.id.skill)
//        skill.text = (intent.getSerializableExtra("skills") as SkillModel).toString()
//        val burst = findViewById<TextView>(R.id.burst)
//        burst.text = intent.getParcelableExtra("skills")
    }
}