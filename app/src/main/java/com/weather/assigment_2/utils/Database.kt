package com.weather.assigment_2.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(var context:Context,var name:String):SQLiteOpenHelper(context,name,null,1) {


    private var TABLE_NAME:String="plants"
    private var NAME_COULMN:String="name"
    private var SCIENTFIC_NAME_COULMN:String="scientific_name"
    private var DISCRIPTION_COULMN:String="discription"
    private var URL_COULMN:String="url"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT,$NAME_COULMN TEXT,$SCIENTFIC_NAME_COULMN TEXT,$DISCRIPTION_COULMN TEXT,$URL_COULMN TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


    fun addPlant(plant: Plant):Boolean {
        return try {
            val writableDatabase = writableDatabase
            val contentValues = ContentValues()
            contentValues.put(NAME_COULMN, plant.name)
            contentValues.put(SCIENTFIC_NAME_COULMN, plant.scientfic_name)
            contentValues.put(DISCRIPTION_COULMN, plant.discription)
            contentValues.put(URL_COULMN, plant.url)

            writableDatabase.insert(TABLE_NAME, null, contentValues)
            true
        }catch (e:Exception){
            false
        }
    }

    fun deletePlant(plant: Plant):Boolean{
        return try {
            val writableDatabase = writableDatabase

            writableDatabase.delete(TABLE_NAME,"id=?", arrayOf(plant.id.toString()))
            true
        }catch (e:Exception){
            false
        }
    }


    fun editPlant(plant: Plant):Boolean{
        return try {
            val writableDatabase = writableDatabase
            val contentValues = ContentValues()
            contentValues.put(NAME_COULMN, plant.name)
            contentValues.put(SCIENTFIC_NAME_COULMN, plant.scientfic_name)
            contentValues.put(DISCRIPTION_COULMN, plant.discription)
            contentValues.put(URL_COULMN, plant.url)

            writableDatabase.update(TABLE_NAME,contentValues,"id=?", arrayOf(plant.id.toString()))
            true
        }catch (e:Exception){
            false
        }
    }



    @SuppressLint("Range")
    fun getAllPlants(): MutableList<Plant>? {
        val plants = mutableListOf<Plant>()
        val readableDatabase = readableDatabase

        val query = "SELECT * FROM $TABLE_NAME ORDER BY id DESC"

        val cursor = readableDatabase.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                var id=cursor.getInt(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex(NAME_COULMN))
                val scientificName = cursor.getString(cursor.getColumnIndex(SCIENTFIC_NAME_COULMN))
                val description = cursor.getString(cursor.getColumnIndex(DISCRIPTION_COULMN))
                val imageUrl = cursor.getString(cursor.getColumnIndex(URL_COULMN))

                plants.add(Plant(id,name, scientificName, description, imageUrl))
            } while (cursor.moveToNext())
            cursor.close()
            return  plants
        }else{
            cursor.close()
            return null
        }

    }


    //add placeholder data function
    fun addPlaceholderData(){

        val sriLankanPlants = listOf(
            // Existing entries...

            Plant(
                id = 1,
                name = "Holy Basil ",
                scientfic_name = "Ocimum tenuiflorum",
                discription = "Holy Basil, also known as Tulsi, is a sacred herb in Hinduism and Ayurveda. " +
                        "It's revered for its potential immune-boosting and stress-relieving properties. " +
                        "Holy Basil leaves are often consumed as tea or used in religious ceremonies." ,
                url = "https://i0.wp.com/images-prod.healthline.com/hlcmsresource/images/topic_centers/Food-Nutrition/1296x728_Holy_Basil.jpg?w=1155&h=1528"
        ),
        Plant(
            id = 2,
            name = "Sri Lankan Fennel ",
            scientfic_name = "Foeniculum vulgare",
            discription = "Sri Lankan Fennel is a flavorful herb with feathery leaves " +
                    "and a licorice-like aroma. It's used in cooking to add a subtle sweetness to " +
                    "curries, soups, and stews. Fennel seeds are also known for their digestive benefits " +
                    "and are often consumed after meals." ,
            url =  "https://kr.lakpura.com/cdn/shop/files/LK94009336-01-E.jpg?v=1654056387&width=3200"
        ),
        Plant(
            id = 3,
            name = "Korala",
            scientfic_name = "Spathodea campanulata",
            discription = "Korala, also known as 'The Fountain Tree,' is a stunning flowering " +
                    "plant native to Sri Lanka. Its vibrant orange flowers are a popular sight, " +
                    "and the leaves are traditionally used in Ayurveda for wound healing and skin " +
                    "conditions." ,
            url =  "https://www.zimbabweflora.co.zw/speciesdata/images/16/164250-2.jpg"
        ),
        Plant(
            id = 4,
            name = "Mukunuwenna",
            scientfic_name = "Acalypha indica",
            discription = "Mukunuwenna is a shrub-like plant with distinctive red flower spikes. " +
                    "In Sri Lankan Ayurveda, it's used for treating respiratory problems, coughs, and asthma. " +
                    "Research suggests it may possess anti-inflammatory properties." ,
            url =  "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBqW8f13ijBqcdtmuyw_Jut1f9T5lJ4YFjzIyS078Quw&s"
        ),
        Plant(
            id = 5,
            name = "Gotukola",
            scientfic_name = "Centella asiatica" ,
            discription = "This small, leafy herb, also known as Gotukola, is a powerhouse of potential " +
                    "health benefits. It's traditionally used in Ayurveda to improve cognitive " +
                    "function, memory, and wound healing. Studies suggest it may also promote skin" +
                    " health and circulation." ,
            url = "https://eoasorganics.com/wp-content/uploads/2021/11/gotukola-single-image.jpg"
        ),
            Plant(
                id = 6,
                name = "Karapincha ",
                scientfic_name = "Andrographis paniculata",
                discription = "Karapincha, also known as 'Green Chiretta,' is " +
                        "a bitter-tasting herb traditionally used in Sri Lankan Ayurveda " +
                        "for centuries. It's commonly consumed as a tea to treat coughs, colds, " +
                        "and fever. Studies suggest it may possess immune-boosting properties.  " +
                        "",
                url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTAKqkknRqPK7Apu006kE8z_zsV-9Iw6ligBVoBSSY9jA&s"
        ),
            Plant(
                id=7,
                name = "Neem ",
                scientfic_name = "Azadirachta indica",
                discription = "The Neem tree, often referred to as 'Nature's Pharmacy,' " +
                        "is a versatile plant with a long history of use in Sri Lankan " +
                        "traditional medicine. Its leaves, bark, and oil possess antibacterial, " +
                        "antifungal, and anti-inflammatory properties. Neem oil is " +
                        "used for wound healing, skin conditions, and as a natural insect " +
                        "repellent.",
                url =  "https://t4.ftcdn.net/jpg/05/34/57/61/360_F_534576101_We52CYuZO9zzJE1iCIBg7LXyLjdygsQX.jpg"
        ),
        )

        for (plant in sriLankanPlants){
            addPlant(plant)
        }
    }
}