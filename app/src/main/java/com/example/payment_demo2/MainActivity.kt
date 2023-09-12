package com.example.payment_demo2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.razorpay.Checkout
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var pay: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pay = findViewById(R.id.pay)
        pay.setOnClickListener {
            makePayment()
        }
    }

    private fun makePayment() {
        val activity: Activity = this
        val co = Checkout()
        co.setKeyID("rzp_test_rhPGLOl4BO6wun")

        try {
            val options = JSONObject()
            options.put("name","Parthiv_Android")
            options.put("description","This is a demo")
            //Some functionalities are yet to be provided
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", "#3399cc")
            options.put("currency","INR")
            //options.put("order_id", "pkd_1234567890")
            options.put("amount","50000") //the money is in paisa (100 = 1 rupee)


            val prefill = JSONObject()
            prefill.put("email","")
            prefill.put("contact","")

            options.put("prefill",prefill)
            co.open(this,options)
        }catch (e: Exception){
            Toast.makeText(this,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

}
