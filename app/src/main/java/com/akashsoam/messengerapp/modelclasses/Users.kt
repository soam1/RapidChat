package com.akashsoam.messengerapp.modelclasses

class Users {
    private var uid: String = ""
    private var username: String = ""
    private var profile: String = ""
    private var cover: String = ""
    private var status: String = ""
    private var search: String = ""
    private var facebook: String = ""
    private var instagram: String = ""
    private var website: String = ""

    constructor()
    constructor(
        uid: String,
        username: String,
        profile: String,
        cover: String,
        status: String,
        search: String,
        facebook: String,
        instagram: String,
        website: String
    ) {
        this.uid = uid
        this.username = username
        this.profile = profile
        this.cover = cover
        this.status = status
        this.search = search
        this.facebook = facebook
        this.instagram = instagram
        this.website = website
    }

    fun getUid(): String {
        return this.uid
    }

    fun getUsername(): String {
        return this.username
    }

    fun getProfile(): String {
        return this.profile
    }

    fun getCover(): String {
        return this.cover
    }

    fun getStatus(): String {
        return this.status
    }

    fun getSearch(): String {
        return this.search
    }

    fun getFacebook(): String {
        return this.facebook
    }

    fun getInstagram(): String {
        return this.instagram
    }

    fun getWebsite(): String {
        return this.website
    }

    fun setUid(uid: String) {
        this.uid = uid
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun setProfile(profile: String) {
        this.profile = profile
    }

    fun setCover(cover: String) {
        this.cover = cover
    }

    fun setStatus(status: String) {
        this.status = status
    }

    fun setSearch(search: String) {
        this.search = search
    }

    fun setFacebook(facebook: String) {
        this.facebook = facebook
    }

    fun setInstagram(instagram: String) {
        this.instagram = instagram
    }

    fun setWebsite(website: String) {
        this.website = website
    }


}