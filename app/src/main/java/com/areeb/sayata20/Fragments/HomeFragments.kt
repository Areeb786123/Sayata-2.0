package com.areeb.sayata20.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.areeb.sayata20.Adapters.HomeAdapter
import com.areeb.sayata20.Models.PostModels
import com.areeb.sayata20.R
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*

class HomeFragments : Fragment() {

    lateinit var mAuth:FirebaseAuth
    lateinit var firebaseUser:FirebaseUser
    lateinit var fstore:FirebaseFirestore
    lateinit var homerec:RecyclerView
    lateinit var greetings:TextView
    lateinit var postList:ArrayList<PostModels>
    lateinit var homeAdapter: HomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth= FirebaseAuth.getInstance()

        fstore= FirebaseFirestore.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val root = inflater.inflate(R.layout.fragment_home_fragments, container, false)

        homerec= root.findViewById(R.id.homeRecycler)
        greetings = root.findViewById(R.id.greetings)

//        greetingsFun()
        homerec.setHasFixedSize(true)
        homerec.layoutManager=LinearLayoutManager(activity)
        postList= ArrayList()
        homeAdapter=HomeAdapter(postList,requireContext())
        homerec.adapter=homeAdapter

        getAllPosts()


        return root
    }

    private fun getAllPosts() {

//       val query =  fstore.collection("Posts").orderBy("posts",Query.Direction.DESCENDING)
         fstore.collection("Posts").addSnapshotListener(object :EventListener<QuerySnapshot>{

             override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                        if(error != null){
                            Log.e("mes","some error occur")
                        }

                 for(dc:DocumentChange in value?.documentChanges!!){
                     postList.add(dc.document.toObject(PostModels::class.java))
                 }
                 homeAdapter.notifyDataSetChanged()
             }

         })



    }

//    private fun greetingsFun(){
//        var currentUser:FirebaseUser=mAuth.currentUser!!
//        if (firebaseUser !=null){
//            var df :DocumentReference=fstore.collection("Soldier").document(currentUser.uid!!)
//            df.get().addOnSuccessListener(OnSuccessListener {
//                OnSuccessListener<DocumentSnapshot> {documentSnapshot ->
//                if (documentSnapshot.exists()){
//                    var activeUser = documentSnapshot.getString("UserName")
//                    Log.e("chName",activeUser.toString())
//                    greetings.setText("Hello" + activeUser)
//                }else{
//                    greetings.setText("Hello Guest")
//                }
//
//                }
//            })
//        }
//    }


}