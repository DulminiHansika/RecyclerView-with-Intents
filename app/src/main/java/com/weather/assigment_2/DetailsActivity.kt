package com.weather.assigment_2

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.weather.assigment_2.utils.Plant


class DetailsActivity : AppCompatActivity() {

    //variables to hold the reciving plat
    private lateinit var plant:Plant

    //variables to hold the views
    lateinit var name_text:TextView
    lateinit var sub_name_txt:TextView
    lateinit var discription_txt:TextView
    lateinit var progress:ProgressBar
    lateinit var img:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.show()

        //initialization
        plant= intent.getSerializableExtra("item") as Plant

        name_text=findViewById(R.id.details_name_txt)
        sub_name_txt=findViewById(R.id.details_sub_text)
        discription_txt=findViewById(R.id.detail_discriiption_txt)
        progress=findViewById(R.id.progress)
        img=findViewById(R.id.details_image)



        name_text.text=makeFirstLetterCapital(plant.name.trim())
        sub_name_txt.text=makeFirstLetterCapital(plant.scientfic_name?.trim())
        discription_txt.text=makeFirstLetterCapital(plant.discription.trim())

        Glide.with(this)
            .load(Uri.parse(plant.url))
            .error(R.drawable.er)
            .listener(object : RequestListener<Drawable?> {
                @SuppressLint("UseCompatLoadingForDrawables")
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    img.visibility= View.VISIBLE
                    img.setImageDrawable(getDrawable(R.drawable.er))
                    progress.visibility= View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {

                    progress.visibility= View.GONE
                    img.visibility= View.VISIBLE
                    return false
                }
            })
            .into(img)

    }

    //function to make the first letter capital
    private fun makeFirstLetterCapital(text:String?):String{

        if (text != null) {
            return text[0].uppercase()+text.subSequence(1,text.length)
        }
        return "Not Found!"
    }
}