package kg.tutorialapp.forecast.network

import kg.tutorialapp.forecast.models.Comments
import kg.tutorialapp.forecast.models.Post
import retrofit2.Call
import retrofit2.http.*

interface PostsApi {

/*    @GET("posts/4")
    fun fetchPostById(): Call<Post>*/

    @GET("posts/{id}")
    fun fetchPostById(
            @Path("id") id: Int
    ): Call<Post>

    @POST("posts")
    fun createPost(
            @Body post: Post
    ): Call<Post>

    @POST("posts")
    @FormUrlEncoded
    fun createPostUsingFields(
            @Field("userId") userId: Int,
            @Field("title") title: String,
            @Field("body") body: String
    ): Call<Post>

    @POST("posts")
    @FormUrlEncoded
    fun createPostUsingFieldMap(
            @FieldMap map: Map<String, Any>
    ): Call<Post>


    @GET("comments")
    fun getCommentUsingQueryMap(
            @QueryMap map: Map<String, Any>
    ): Call<Comments>

    @PUT("posts/{id}")
    fun putPost(
            @Path("id") id: Int,
            @Body post: Post
    ): Call<Post>

    @PATCH("posts/{id}")
    fun patchPost(
            @Path("id") id: Int,
            @Body post: Post
    ): Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(
            @Path("id") id : Int
    ): Call<Unit>
}