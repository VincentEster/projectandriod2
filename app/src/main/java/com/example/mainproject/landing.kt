package com.example.mainproject

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toIcon
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URI
import java.net.URL
import java.net.URLConnection


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [landing.newInstance] factory method to
 * create an instance of this fragment.
 */
class landing : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var weer:Root
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_landing, container, false)

        view.findViewById<Button>(R.id.btn_settings).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_landing_to_settings)
        }


        view.findViewById<Button>(R.id.btn_reset).setOnClickListener {
            val queue = Volley.newRequestQueue(this.context)
            val url = "https://api.weatherapi.com/v1/current.json?key=bbe966c41757410fa8e132758242802&q=Emmen&aqi=yes"

// Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.

                    val gson= Gson()
                    weer = gson.fromJson(response, Root::class.java)
                    view.findViewById<TextView>(R.id.temp_en_plaats).text = weer.current.temp_c.toString() + "C" + weer.location.name


//                    view.findViewById<ImageView>(R.id.weersvoorspelling_lucht).setImageBitmap(getImageBitmap(weer.current.condition.icon))
//                    Log.println(Log.INFO,"icon", weer.current.condition.icon.toUri().toIcon().toString())



                },
                Response.ErrorListener {  })

// Add the request to the RequestQueue.
            queue.add(stringRequest)

        }




        return view
    }

    private fun getImageBitmap(url: String): Bitmap? {
        var bm: Bitmap? = null
        try {
            val aURL = URL( "https:" + url)
            val conn: URLConnection = aURL.openConnection()
            conn.connect()
            val `is`: InputStream = conn.getInputStream()
            val bis = BufferedInputStream(`is`)
            bm = BitmapFactory.decodeStream(bis)
            bis.close()
            `is`.close()
        } catch (e: IOException) {
            Log.e(TAG, "Error getting bitmap", e)
        }
        return bm
    }


}