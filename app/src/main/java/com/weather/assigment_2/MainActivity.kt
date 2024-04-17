package com.weather.assigment_2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weather.assigment_2.utils.Database
import com.weather.assigment_2.utils.Plant

class MainActivity : AppCompatActivity() {

    //variables for holding views
    lateinit var  re_view:RecyclerView
    lateinit var add_btn:Button
    lateinit var new_plant:Plant
    var plants:MutableList<Plant>?=null
    lateinit var database: Database


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //assigning values to the variables
        re_view =findViewById(R.id.recycle_view_layout)
        add_btn=findViewById(R.id.button)
        re_view.layoutManager=LinearLayoutManager(this)
        database= Database(this,"plants.db")


        //get all the plants from the database
        plants=database.getAllPlants()

        if(plants==null){
            database.addPlaceholderData()
            plants=database.getAllPlants()
        }


        //get the plants from the database
        val adapter= ItemAdapter(plants!!,this,database)
        if(intent.hasExtra("plant")){
            new_plant=intent.getSerializableExtra("plant")as Plant
            if(database.addPlant(new_plant)) {
                Toast.makeText(this, "Plant added successfully", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Plant not added", Toast.LENGTH_SHORT).show()
            }

            plants?.add(0,new_plant)
            adapter.notifyItemInserted(0)
        }

        //check for update call

        if(intent.hasExtra("updated_plant")){
            val updated_plant=intent.getSerializableExtra("updated_plant")as Plant
            if(database.editPlant(updated_plant)){
                Toast.makeText(this,"Plant updated successfully",Toast.LENGTH_SHORT).show()
                adapter.setDataset(dataset = database.getAllPlants()!!)

            }else{
                Toast.makeText(this,"Plant not updated",Toast.LENGTH_SHORT).show()
            }

        }

        re_view.adapter=adapter


        //event listeners

        //set the onclick event listener for the button
        add_btn.setOnClickListener { view->

            startActivity(Intent(view.context,AddItemActivity::class.java))
        }



    }

}