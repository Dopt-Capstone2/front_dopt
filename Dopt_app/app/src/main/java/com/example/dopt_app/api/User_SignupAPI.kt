package com.example.dopt_app.api

import com.example.dopt_app.data.After_Share
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.data.User_Signup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// https://stickode.tistory.com/43
// jsonparams
interface User_SignupAPI {
    @POST("User_Signup/post")
    fun POST_User_SignUp(
        @Body jsonparams: User_Signup
    ): Call<PostResult>

    //TODO: 처음 실행될 때 접속한 유저의 이메일을 저장해야한다.
    //여기서 받는 정보는 제너릭의 배열이 아닌, 그 유저의 정보뿐이기에
    //그대로 User_Signup을 리턴값으로 받는다
    //https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=nakim02&logNo=221419275273
    //https://angangmoddi.tistory.com/217

    //예를들어 Bookmark의 경우,
    //내가 Bookmark한 모든 값을 받아야하기 때문에
    //Bookmark의 리스트를 받아야한다.

    //sql에서 update는 node.js에서 app.put으로 구현
    //sql에서 delete는 node.js에서 app.delete로 구현
    @GET("User_Signup/get")
    fun GET_User_Signup(
        @Query("userEmail") userEmail: String
    ): Call<User_Signup>

    @POST("User_Signup/update")
    fun UPDATE_User_Signup(
        @Body jsonparams: User_Signup
    ): Call<PostResult>
}