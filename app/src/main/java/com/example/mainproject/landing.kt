package com.example.mainproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

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
            val queue = Volley.newRequestQueue(this)
            val url = "https://api.weatherapi.com/v1/current.json?key=bbe966c41757410fa8e132758242802&q=Emmen&aqi=yes"

// Request a string response from the provided URL.
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.



                },
                Response.ErrorListener {  })

// Add the request to the RequestQueue.
            queue.add(stringRequest)

        }




        return view
    }








}