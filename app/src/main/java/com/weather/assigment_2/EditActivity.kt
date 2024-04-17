package com.weather.assigment_2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.weather.assigment_2.utils.Plant


class EditActivity : AppCompatActivity() {


    private lateinit var name_txt:EditText
    private lateinit var sub_txt:EditText
    private lateinit var img:EditText
    private lateinit var discription_txt:EditText
    private lateinit var update_btn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit)

        var plant:Plant=intent.getSerializableExtra("item")as Plant


        //assigning values to the variables
        name_txt=findViewById(R.id.edit_plant_name_input)
        sub_txt=findViewById(R.id.edit_scientific_name_input)
        discription_txt=findViewById(R.id.edit_description_input)
        img=findViewById(R.id.edit_url)
        update_btn=findViewById(R.id.edit_save_btn)


        //assigning pervious values to the fileds

        name_txt.setText(plant.name)
        discription_txt.setText(plant.discription)
        sub_txt.setText(plant.scientfic_name)
        img.setText(plant.url)


        //update button click listener
        update_btn.setOnClickListener{

            var name:String=name_txt.text.toString()
            var sub:String=sub_txt.text.toString()
            var discription:String=discription_txt.text.toString()
            var url:String=img.text.toString()

            if (name.isEmpty() || sub.isEmpty() || discription.isEmpty() || url.isEmpty()){

                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updated_plant=Plant(plant.id,name,sub,discription,url)

            val intent= Intent(this,MainActivity::class.java).apply {
                putExtra("updated_plant",updated_plant)
            }
            startActivity(intent)
        }





    }


}