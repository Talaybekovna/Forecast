package kg.tutorialapp.forecast.models

data class Comments(
        var id: Int? = null,
        var postId: Int? = null,
        var name: String? = null,
        var email: String? = null,
        var body: String? = null
)
