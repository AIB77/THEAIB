package com.example.theaib

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewUsers : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)


        val name=findViewById<EditText>(R.id.editTextusername)
        val pk = findViewById<EditText>(R.id.editTextID)
        val location = findViewById<EditText>(R.id.editTextuserlocation)
        val SaveBTN = findViewById<Button>(R.id.buttonSave)

        SaveBTN.setOnClickListener {

            var f=MyDataItem(name.text.toString(),location.text.toString(),pk.text.toString().toInt())

            addSingleuser(f, onResult = {
                name.setText("")
                location.setText("")
                pk.setText("")
                })


        }


    }

    private fun addSingleuser(f: MyDataItem, onResult: () -> Unit)
    {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        if(apiInterface != null)
        {
            apiInterface.addData(f).enqueue(object : Callback<MyDataItem> {
                override fun onResponse(call: Call<MyDataItem>, response:Response<MyDataItem>) {
                    onResult()

                }

                override fun onFailure(call: Call<MyDataItem>, t: Throwable) {
                    onResult()
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                }


            })
        }


    }

    fun funview(view: android.view.View) {
        intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
    fun addnew(view: android.view.View) {
        intent = Intent(applicationContext, NewUsers::class.java)
        startActivity(intent)


    }

}