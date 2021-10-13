package com.example.theaib

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DeleteUpdate: AppCompatActivity()
{
    lateinit var userid:EditText
    lateinit var name:EditText
    lateinit var location: EditText
    lateinit var deleteBTN:Button
    lateinit var updateBTN:Button
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.deleteuser)

        userid=findViewById(R.id.editTextID)
        name=findViewById(R.id.editTextusername)
        location=findViewById(R.id.editTextuserlocation)
        deleteBTN=findViewById(R.id.buttondelet)
        updateBTN=findViewById(R.id.buttonupdate)


        updateBTN.setOnClickListener {

            var f = MyDataItem(name.text.toString(), location.text.toString(),userid.text.toString().toInt())
            updateusers(f, onResult =
            {
                name.setText("")
                location.setText("")
                userid.setText("")
                Toast.makeText(applicationContext, "Update Success!", Toast.LENGTH_SHORT).show();
            })
        }

        deleteBTN.setOnClickListener {
            var f = MyDataItem(name.text.toString(), location.text.toString(),userid.text.toString().toInt())
            deleteusers (f, onResult =
            {
                name.setText("")
                location.setText("")
                userid.setText("")

                Toast.makeText(applicationContext, "Delete Success!", Toast.LENGTH_SHORT).show();
            })
        }

    }

    private fun deleteusers(f: MyDataItem, onResult: () -> Unit) {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.deleteusers(userid.text.toString().toInt())?.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                Toast.makeText(applicationContext, "User Deleted", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun updateusers(f: MyDataItem, onResult: () -> Unit) {

       val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.updateusers(userid)?.enqueue(object : retrofit2.Callback<MyDataItem> {
            override fun onResponse(call: retrofit2.Call<MyDataItem>, response:Response<MyDataItem>) {

                onResult()
                Toast.makeText(applicationContext, "User Updated", Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: retrofit2.Call<MyDataItem>, t: Throwable) {
                onResult()
                Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
            }


        })




    }


}