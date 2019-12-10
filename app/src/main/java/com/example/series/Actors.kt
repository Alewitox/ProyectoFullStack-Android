package com.example.series

class Actors {
    var character_name: String? = null
    var actor_name: String? = null
    var image_url_actor: String? = null

    constructor() {}
    constructor(
        character_name: String?,
        actor_name: String?,
        image_url: String?
    ) {
        this.character_name = character_name
        this.actor_name = actor_name
        this.image_url_actor = image_url
    }

}
