package edu.uw.sunny121.dotify.repository
import edu.uw.sunny121.dotify.model.Userprofile
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
//https://raw.githubusercontent.com/echeeUW/codesnippets/master/user_info.json


class DataRepository {
    private val userProfileService = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserProfileService::class.java)

    suspend fun getUserProfile() : Userprofile = userProfileService.getUserProfile();



}
interface UserProfileService{
    @GET("echeeUW/codesnippets/master/user_info.json")
    suspend fun getUserProfile() : Userprofile
}