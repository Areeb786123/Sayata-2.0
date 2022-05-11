package com.areeb.sayata20.Fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.areeb.sayata20.R
import com.areeb.sayata20.activites.PostDone
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class UploadPost : Fragment() {
    lateinit var story:EditText
    lateinit var storyPost:CardView
    var soldierName: String? = null
    var soldierEmail: String? = null
    var soldierPhone: String? = null


    lateinit var mAuth :FirebaseAuth
    lateinit var fstore:FirebaseFirestore
    lateinit var firebaseUser:FirebaseUser
//    lateinit var soldierName:String
//    lateinit var soldierEmail:String
//    lateinit var soldierPhone:String


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
        fetchingCurrentUserData()



        val root = inflater.inflate(R.layout.fragment_upload_post, container, false)
        story = root.findViewById(R.id.story)
        storyPost= root.findViewById(R.id.storyPost)

        storyPost.setOnClickListener {
            uploadStory()

        }

        return root
    }

    private fun uploadStory() {

        var uploadStory = story.text.toString()
        val currentUser =FirebaseAuth.getInstance().currentUser

        val df:DocumentReference=fstore.collection("Posts").document()
        var postStory:HashMap<String,Any> = HashMap()
        postStory.put("post",uploadStory)
        postStory.put("AuthorName", soldierName!!)
        postStory.put("AuthorEmail", soldierEmail!!)
        postStory.put("AuthorPhone", soldierPhone!!)
        df.set(postStory)

        var postIntent = Intent(activity,PostDone::class.java)
        startActivity(postIntent)



    }
    private fun fetchingCurrentUserData() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val df = fstore.collection("Soldier").document(
            currentUser!!.uid
        )
        df.get().addOnSuccessListener { documentSnapshot ->
            soldierName = documentSnapshot.getString("UserName")
            soldierPhone= documentSnapshot.getString("UserPhone")
            soldierEmail = documentSnapshot.getString("UserEmail")
        }
    }


}
