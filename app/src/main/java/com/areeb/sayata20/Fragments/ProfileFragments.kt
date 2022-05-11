package com.areeb.sayata20.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.areeb.sayata20.R
import com.areeb.sayata20.activites.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


class ProfileFragments : Fragment() {
    lateinit var mAuth: FirebaseAuth
    lateinit var firebaseUser: FirebaseUser
    lateinit var fstore: FirebaseFirestore
    lateinit var logout:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mAuth= FirebaseAuth.getInstance()
        fstore= FirebaseFirestore.getInstance()


        val root=inflater.inflate(R.layout.fragment_profile_fragments, container, false)
        logout=root.findViewById(R.id.logout)
        logout.setOnClickListener {
            mAuth.signOut()
            val signout=Intent(activity,LoginActivity::class.java)
            startActivity(signout)
        }


        return root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragments.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragments().apply {
                arguments = Bundle().apply {

                }
            }
    }
}