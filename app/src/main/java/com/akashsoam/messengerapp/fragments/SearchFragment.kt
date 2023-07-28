package com.akashsoam.messengerapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.messengerapp.R
import com.akashsoam.messengerapp.adapterclasses.UserAdapter
import com.akashsoam.messengerapp.modelclasses.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchFragment : Fragment() {

    private var userAdapter: UserAdapter? = null
    private var mUsersList: List<Users>? = null
    private var searchedUsersRV: RecyclerView? = null
    private var searchEditText: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        searchedUsersRV = view.findViewById(R.id.search_list)
        searchedUsersRV!!.setHasFixedSize(true)
        searchedUsersRV!!.layoutManager = LinearLayoutManager(context)

        searchEditText = view.findViewById<EditText>(R.id.search_user_edt)

        mUsersList = ArrayList()
        retrieveAllUsers()

        searchEditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {

            }

            override fun onTextChanged(
                cs: CharSequence?, start: Int, before: Int, count: Int
            ) {
                searchForUsers(cs.toString().lowercase())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })


        return view
    }

    private fun retrieveAllUsers() {
        var firebaseUserID = FirebaseAuth.getInstance().currentUser!!.uid
        val refUsers = FirebaseDatabase.getInstance().reference.child("users")

        refUsers.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                (mUsersList as ArrayList<Users>).clear()
                if (searchEditText!!.text.toString() == "") {
                    for (snapshot in dataSnapshot.children) {
                        val user: Users? = snapshot.getValue(Users::class.java)
                        if (!(user!!.getUid()).equals(firebaseUserID)) {
                            (mUsersList as ArrayList<Users>).add(user)
                        }
                    }
                    userAdapter = UserAdapter(context!!, mUsersList!!, false)
                    searchedUsersRV!!.adapter = userAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

    private fun searchForUsers(str: String) {
        var firebaseUserID = FirebaseAuth.getInstance().currentUser!!.uid
        val queryUsers =
            FirebaseDatabase.getInstance().reference.child("users").child("search").startAt(str)
                .endAt(str + "\uf8ff")
        queryUsers.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                (mUsersList as ArrayList<Users>).clear()
                for (snapshot in dataSnapshot.children) {
                    val user: Users? = snapshot.getValue(Users::class.java)
                    if (!(user!!.getUid()).equals(firebaseUserID)) {
                        (mUsersList as ArrayList<Users>).add(user)
                    }
                }

                userAdapter = UserAdapter(context!!, mUsersList!!, false)
                searchedUsersRV!!.adapter = userAdapter
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}