package com.example.series

class Series {
    var name: String? = null
    var rating: String? = null
    var categorie: String? = null
    var studio: String? = null
    var image_url: String? = null

    constructor() {}
    constructor(
        name: String?,
        rating: String?,
        categorie: String?,
        studio: String?,
        image_url: String?
    ) {
        this.name = name
        this.rating = rating
        this.categorie = categorie
        this.studio = studio
        this.image_url = image_url
    }

}