package com.example.theaib


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://dojo-recipes.herokuapp.com/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        getMyData()

        var AddBTN=findViewById<Button>(R.id.addusers)
        var Delet=findViewById<Button>(R.id.buttonooo)

        AddBTN.setOnClickListener {
            intent = Intent(applicationContext, NewUsers::class.java)
            startActivity(intent)
        }

        Delet.setOnClickListener {

            intent = Intent(applicationContext, DeleteUpdate::class.java)
            startActivity(intent)

        }




    }

    private fun getMyData() {
       val retorfitBuilder= Retrofit.Builder()
           .addConverterFactory(GsonConverterFactory.create())
           .baseUrl(BASE_URL)
           .build()
           .create(APIInterface::class.java)

        val retrofitData =retorfitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>,
            ) {
                val responseBody=response.body()!!

              //  val myStringBuilder= StringBuilder()
                var stringToBePritined:String? = ""
                for(myData in responseBody){
                    stringToBePritined = stringToBePritined+myData.pk+"\n" +myData.name+ "\n"+myData.location + "\n"+"\n"
                  //  myStringBuilder.append(myData.pk, myData.name, myData.location)
                   // myStringBuilder.append("\n")
                   // myStringBuilder.append( myData.name)
                   // myStringBuilder.append("\n")
                   // myStringBuilder.append(  myData.location)
                 //   myStringBuilder.append("\n")
                    //myStringBuilder.append("\n")
                }
               val txt = findViewById<TextView>(R.id.txt)
                //txt.text=myStringBuilder
                txt.text= stringToBePritined

            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {

            }
        })
    }


}