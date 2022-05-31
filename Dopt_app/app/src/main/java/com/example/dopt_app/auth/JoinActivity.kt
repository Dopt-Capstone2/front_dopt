package com.example.dopt_app.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.*
// import com.example.dopt_app.data.JoinModel
// import com.example.dopt_app.data.JoinResult
import com.example.dopt_app.shelter.ShelterMainActivity
import com.example.dopt_app.shelter.ShelterNameActivity
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class JoinActivity : AppCompatActivity() {

    private val TAG = "JoinActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener{
//            //모든 더미데이터는 userEmail: 123@123에 있습니다.
//            //아래 예시에 적은 모든 파라미터값은 더미데이터이며 실제 로직과 다를 수 있습니다.

//            //사용자 회원가입하기 POST
//            //파라미터로 사용자의 정보를 데이터클래스 객체로 만들어 넣는다
//            val userEmail= findViewById<TextInputEditText>(R.id.join_email_jo)
//            val userPw = findViewById<TextInputEditText>(R.id.join_pw_jo)
//            val POST_User_Signup_Data = User_Signup(userEmail.text.toString(), userPw.text.toString(), "hmin","Ilsan","nick")
            startActivity(intent)
//            Log.d(TAG, "clicked join btn!!!")
//            Log.d(TAG, data.toString())
//            RetrofitClient.User_Signup_instance.POST_User_SignUp(data)
//                .enqueue(object: Callback<PostResult> {
//                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
//                        // Log.d(TAG, "Request Failed start")
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
//                        Log.d(TAG, "failed")
//                        Log.d(TAG, t.message.toString())
//                        // Log.d(TAG, "Request Failed end")
//                    }
//                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
//                        // Log.d(TAG,"Request successful start")
//                        // Log.d(TAG, response.body().toString())
//                        // Log.d(TAG, "Request successful end")
//                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
//                        Log.d(TAG, "successed")
//                        Log.d(TAG, response.body().toString())
//                    }
//                }
//            )

            //사용자 정보 GET
            //파라미터로 자신의 이메일을 넣는다
            RetrofitClient.User_Signup_instance.GET_User_Signup("123@123")
                .enqueue(object: Callback <User_Signup> {
                    override fun onFailure(call: Call<User_Signup>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<User_Signup>, response: Response<User_Signup>) {
                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        Log.d(TAG, "succeeded")
                        Log.d(TAG, response.body().toString())
                    }
                }
                )

            //사용자 정보 UPDATE
            //userEmail을 primarykey로 모든 정보를 수정합니다.
            var UPDATE_User_Signup_Data = User_Signup("123@123","123@123", "테스트", "서울특별시", "테스트")
            RetrofitClient.User_Signup_instance.UPDATE_User_Signup(UPDATE_User_Signup_Data)
                .enqueue(object: Callback<PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        // Log.d(TAG, "Request Failed start")
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "UPDATE USfailed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        Log.d(TAG, "UPDATE US succeeded")
                        Log.d(TAG, response.body().toString())
                    }
                }
            )


            //근황공유 정보 GET
            var GET_after_Share_Response = MutableLiveData<After_Share_List>()
            RetrofitClient.After_Share_instance.GET_After_Share("123@123")
                .enqueue(object: Callback <After_Share_List> {
                    override fun onFailure(call: Call<After_Share_List>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Get AS failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<After_Share_List>, response: Response<After_Share_List>) {
                        Toast.makeText(
                            applicationContext,
                            response.body().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d(TAG, "Get AS succeeded")
                        Log.d(TAG, response.body().toString())

                        //지정한 데이터 클래스 객체로 저장
                        GET_after_Share_Response.value = response.body() as After_Share_List
                        // 값을 복사
                        val GET_after_ShareRaw = GET_after_Share_Response.value?.copy()
                        // 데이터 클래스들의 배열 출력
                        Log.d("after_ShareRaw", GET_after_ShareRaw.toString())
                        //요소별 접근
                        //response의 각 데이터 클래스 접근
                        if (GET_after_ShareRaw != null) {
                            Log.d("After_Share", GET_after_ShareRaw.After_Share[0].toString())
                        }
                    }
                }
                )

            //근황공유 정보 POST
            val POST_After_Share_Data = After_Share("123@123", "푸앙보호소", "desertionNo10", 1, "image.jpg", "입양 1주차")
            RetrofitClient.After_Share_instance.POST_After_Share(POST_After_Share_Data)
                .enqueue(object: Callback <PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Post AS failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Toast.makeText(
                            applicationContext,
                            response.body().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d(TAG, "Post AS succeeded")
                        Log.d(TAG, response.body().toString())
                    }
                }
                )

            //근황공유 정보 DELETE
            //지우려는 데이터를 After_Share_Delete 서식에 맞게 넣어줍니다.
            val DELETE_After_Share_Data = After_Share_Delete("123@123", "desertionNo10", 1)
            RetrofitClient.After_Share_instance.DELETE_After_Share(DELETE_After_Share_Data)
                .enqueue(object: Callback <PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Delete AS failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Toast.makeText(
                            applicationContext,
                            response.body().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d(TAG, "Delete AS succeeded")
                        Log.d(TAG, response.body().toString())
                    }
                }
                )

            //근황공유 정보 UPDATE
            //userEmail, desertionNo, week을 primaryKey로 image와 script를 수정
            //careNm은 사용하지 않으나 데이터 형식상 필요
            val UPDATE_After_Share_Data = After_Share("123@123", "푸앙보호소", "desertionNo10", 1, "image.jpg", "입양 1주차 수정완료")
            RetrofitClient.After_Share_instance.UPDATE_After_Share(UPDATE_After_Share_Data)
                .enqueue(object: Callback <PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "UPDATE AS failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Toast.makeText(
                            applicationContext,
                            response.body().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d(TAG, "UPDATE AS succeeded")
                        Log.d(TAG, response.body().toString())
                    }
                }
                )

            //북마크 정보 GET
            //북마크들의 배열을 호출한다
            //파라미터로 사용자의 이메일을 입력한다

            //데이터 클래스로 저장은 아래처럼 합니다.
            //정보 저장 객체
            var GET_bookmark_Response = MutableLiveData<Bookmark_List>()
            RetrofitClient.Bookmark_instance.GET_Bookmark("123@123")
                .enqueue(object: Callback <Bookmark_List> {
                    override fun onFailure(call: Call<Bookmark_List>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "GET B failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<Bookmark_List>, response: Response<Bookmark_List>) {
                        Toast.makeText(
                            applicationContext,
                            response.body().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d(TAG, "GET B succeeded")
                        Log.d(TAG, response.body().toString())

                        //지정한 데이터 클래스 객체로 저장
                        GET_bookmark_Response.value = response.body() as Bookmark_List
                        // 값을 복사
                        val GET_bookmarkRaw = GET_bookmark_Response.value?.copy()
                        // 데이터 클래스들의 배열 출력
                        Log.d("GET_bookmarkRaw", GET_bookmarkRaw.toString())
                        //요소별 접근
                        //response의 각 데이터 클래스 접근
                        if (GET_bookmarkRaw != null) {
                            Log.d("GET_bookmark", GET_bookmarkRaw.Bookmark[0].toString())
                        }
                    }
                }
                )
            //북마크 정보 POST
            //Bookmark 데이터클래스는 다음과 같습니다.
            //OpenAPI에서 받은 동물의 정보들 모두와
            //마지막에 isConsidered를 추가하면 됩니다.
            //isConsidered는 4가지 값을 가질 수 있습니다.
            //0: 북마크에 추가
            //1: 입양 신청 상태
            //2: 입양 허가
            //3: 반려
            //deconstructor 등을 이용해서 Item 정보와 isCondisered를 묶어서
            //Bookmark 객체로 만든 다음, 파라미터로 넘겨주세요.
            //더미데이터
            val POST_Bookmark_Data = Bookmark("123@123", "2021년생", "careAddr2", "careNm2", "02-222-2222", "chargeNm2", "하양색", "desertionNo22", "file.jpg", 20220527, "서울특별시 동작구", "[개] 골드 리트리버", "Y", 20220527, "22", 20220610, "02-222-2212", "orgNm2", "pofile2", "보호중", "M", "목에 흉터가 있음", "3kg", 0)
            RetrofitClient.Bookmark_instance.POST_Bookmark(POST_Bookmark_Data)
                .enqueue(object: Callback <PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Post B failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Toast.makeText(
                            applicationContext,
                            response.body().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d(TAG, "Post B succeeded")
                        Log.d(TAG, response.body().toString())
                    }
                }
                )

            //북마크 정보 DELETE
            //지우려는 데이터를 Bookmark_Delete 서식에 맞게 넣어줍니다.
            //지우려는 데이터의 animalId, userEmail을 가져옵니다.
            val DELETE_Bookmark_Data = Bookmark_Delete("123@123", "desertionNo22")
            RetrofitClient.Bookmark_instance.DELETE_Bookmark(DELETE_Bookmark_Data)
                .enqueue(object: Callback <PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Delete B failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Toast.makeText(
                            applicationContext,
                            response.body().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d(TAG, "Delete B succeeded")
                        Log.d(TAG, response.body().toString())
                    }
                }
                )
            //Checklist GET
            var GET_Checklist_Response = MutableLiveData<Checklist>()
            RetrofitClient.Checklist_instance.GET_Checklist("123@123")
                .enqueue(object: Callback <Checklist> {
                    override fun onFailure(call: Call<Checklist>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "GET C failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<Checklist>, response: Response<Checklist>) {
                        Toast.makeText(
                            applicationContext,
                            response.body().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d(TAG, "GET C succeeded")
                        Log.d(TAG, response.body().toString())

                        //지정한 데이터 클래스 객체로 저장
                        GET_Checklist_Response.value = response.body() as Checklist
                        // 값을 복사
                        val GET_ChecklistRaw = GET_Checklist_Response.value?.copy()
                        // 데이터 클래스들의 배열 출력
                        Log.d("GET_ChecklistRaw", GET_ChecklistRaw.toString())
                        if (GET_ChecklistRaw != null) {
                            Log.d("GET_checklist", GET_ChecklistRaw.toString())
                        }
                    }
                }
                )

            //Checklist POST
            val POST_Checklist_Data = Checklist("123@123", 0,0,0,0,0,0,0,0,0,0,0,0)
            RetrofitClient.Checklist_instance.POST_Checklist(POST_Checklist_Data)
                .enqueue(object: Callback <PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Post C failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Toast.makeText(
                            applicationContext,
                            response.body().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d(TAG, "Post C succeeded")
                        Log.d(TAG, response.body().toString())
                    }
                }
                )

            //MatchAPI
            //MainActivity의 matchfragment 호출부에 있습니다.

            //Monthly Statistics API
            var Monthly_Statistics_Response = MutableLiveData<Monthly_Statistics>()
            RetrofitClient.Monthly_Statistics__instance.GET_Monthly_Statistics()
                .enqueue(object: Callback<Monthly_Statistics> {
                    override fun onFailure(call: Call<Monthly_Statistics>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<Monthly_Statistics>, response: Response<Monthly_Statistics>) {
                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        Log.d(TAG, "succeeded")
                        Log.d(TAG, response.body().toString())
                        Monthly_Statistics_Response.value = response.body() as Monthly_Statistics
                        // 값을 복사
                        val Monthly_Statistics_Raw = Monthly_Statistics_Response.value?.copy()
                        // 데이터 클래스들의 배열 출력
                        Log.d("Monthly_Statistics_Raw", Monthly_Statistics_Raw.toString())
                        //요소별 접근
                        //response의 각 데이터 클래스 접근
                        if (Monthly_Statistics_Raw != null) {
                            Log.d("Monthly_Statistics_", Monthly_Statistics_Raw.data[0].toString())
                        }
                    }
                }
                )

            

        }

        val shelterJoinBtn = findViewById<Button>(R.id.shelterJoinBtn)
        shelterJoinBtn.setOnClickListener{
            val intent = Intent(this, ShelterNameActivity::class.java)
            startActivity(intent)
        }

    }
}