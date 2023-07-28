package com.akashsoam.messengerapp.adapterclasses

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akashsoam.messengerapp.R
import com.akashsoam.messengerapp.modelclasses.Users
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(
    mContext: Context,
    mUsersList: List<Users>,
    isChatCheck: Boolean
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val mContext: Context
    private var mUserList: List<Users>
    private var isChatCheck: Boolean

    init {
        this.mUserList = mUsersList
        this.isChatCheck = isChatCheck
        this.mContext = mContext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.user_search_item_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mUserList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.usernameTxt.text = mUserList[position].getUsername()
        Picasso.get().load(mUserList[position].getProfile()).placeholder(R.drawable.profile_image)
            .into(holder.profileImageView)

    }


    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var usernameTxt: TextView
        var profileImageView: CircleImageView
        var onlineImageView: CircleImageView
        var offlineImageView: CircleImageView
        var lastMessageTxt: TextView

        init {
            usernameTxt = itemView.findViewById(R.id.user_name_user_search)
            profileImageView = itemView.findViewById(R.id.profile_image_user_search)
            onlineImageView = itemView.findViewById(R.id.online_image_user_search)
            offlineImageView = itemView.findViewById(R.id.offline_image_user_search)
            lastMessageTxt = itemView.findViewById(R.id.last_message_user_search)
        }
    }

}