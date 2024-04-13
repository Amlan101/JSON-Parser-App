package com.example.jsonparserapp
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var items: List<PokemonItem>
    private lateinit var recyclerView: RecyclerView
    private lateinit var switch: SwitchCompat
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        switch = findViewById(R.id.switch1)

        val jsonString = loadJSONFromAsset(applicationContext, "pokemon.json")
        items = parseJSON(jsonString)
        adapter = RecyclerAdapter(items)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val frenchNames = items.map { it.name.french }
                adapter.updateData(frenchNames)
            } else {
                val englishNames = items.map { it.name.english }
                adapter.updateData(englishNames)
            }
        }

    }
        private fun loadJSONFromAsset(context: Context, fileName: String): String {
            return context.assets.open(fileName).bufferedReader().use { it.readText() }
        }

        private fun parseJSON(jsonString: String?): List<PokemonItem> {
            val items = mutableListOf<PokemonItem>()
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val nameObject = jsonObject.getJSONObject("name")
                val englishName = nameObject.getString("english")
                val frenchName = nameObject.getString("french")
                val id = jsonObject.getInt("id")
                val pokemonItem = PokemonItem(id, PokemonItem.Name(englishName, frenchName))
                items.add(pokemonItem)
            }
            return items
        }

    }

