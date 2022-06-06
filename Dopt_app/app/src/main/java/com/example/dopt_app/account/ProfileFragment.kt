package com.example.dopt_app.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.auth.emailInfo
import com.example.dopt_app.data.Preference_List
import com.example.dopt_app.data.User_Signup
import com.example.dopt_app.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {
    private val TAG = "ProfileFragment"

    lateinit var binding: FragmentProfileBinding

    private lateinit var profileNameTx: TextView
    private lateinit var userLocationTx: TextView
    private lateinit var userIdTx: TextView

    private lateinit var userPreferKind: TextView
    private lateinit var userPreferBreed: TextView
    private lateinit var userPreferGender: TextView
    private lateinit var userPreferColor: TextView
    private lateinit var userPreferAge: TextView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        var GET_user_Info = MutableLiveData<User_Signup>()
        //사용자 정보 GET
        //파라미터로 자신의 이메일을 넣는다
        RetrofitClient.User_Signup_instance.GET_User_Signup(emailInfo)
            .enqueue(object: Callback<User_Signup> {
                override fun onFailure(call: Call<User_Signup>, t: Throwable) {
//                  Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                    Log.d(TAG, "GET U failed")
                    Log.d(TAG, t.message.toString())
                }
                override fun onResponse(call: Call<User_Signup>, response: Response<User_Signup>) {
//                  Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                    GET_user_Info.value=response.body() as User_Signup
                    val GET_user_NewInfo = GET_user_Info.value?.copy()
                    Log.d(TAG, "GET U succeeded")
                    Log.d(TAG, response.body().toString())
                    profileNameTx?.text=GET_user_NewInfo?.nicknm
                    userLocationTx?.text=GET_user_NewInfo?.userLoc
                    userIdTx.text = GET_user_NewInfo?.userEmail
                }
            })

        //Preference 정보 GET
        //사용자가 등록한 preference의 list를 get합니다.
            var GET_Preference_Response = MutableLiveData<Preference_List>()
            RetrofitClient.Preference_instance.GET_Preference(emailInfo)
                .enqueue(object: Callback <Preference_List> {
                    override fun onFailure(call: Call<Preference_List>, t: Throwable) {
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Get P failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<Preference_List>, response: Response<Preference_List>) {
//                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Get P succeeded")
                        Log.d(TAG, response.body().toString())

                        //지정한 데이터 클래스 객체로 저장
                        GET_Preference_Response.value = response.body() as Preference_List
                        // 값을 복사
                        val GET_PreferenceRaw = GET_Preference_Response.value?.copy()
                        // 데이터 클래스들의 배열 출력
                        Log.d("GET_PreferenceRaw", GET_PreferenceRaw.toString())
                        //요소별 접근
                        //response의 각 데이터 클래스 접근
//                        if (GET_PreferenceRaw != null) {
//                            Log.d("Preference", GET_PreferenceRaw.Preference[0].toString())
//                        }
                        userPreferKind?.text = GET_PreferenceRaw!!.Preference[0].type
                        userPreferBreed?.text = GET_PreferenceRaw!!.Preference[0].breed
                        userPreferGender?.text = GET_PreferenceRaw!!.Preference[0].sex
                        userPreferColor?.text = GET_PreferenceRaw!!.Preference[0].color
                        userPreferAge?.text = GET_PreferenceRaw!!.Preference[0].age


                    }
                })

        binding.backAccountBt.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, AccountFragment())
                .commitAllowingStateLoss()
        }

        binding.profileFixBt.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ProfiledetailFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileNameTx = view.findViewById(R.id.profile_name_tx)
        userLocationTx= view.findViewById(R.id.user_location_tx)
        userIdTx= view.findViewById(R.id.profile_email_tx)
        userPreferKind=view.findViewById(R.id.user_prefer_kind_tx)
        userPreferBreed=view.findViewById(R.id.user_prefer_breed_tx)
        userPreferGender=view.findViewById(R.id.user_prefer_gender_tx)
        userPreferColor=view.findViewById(R.id.user_prefer_color_tx)
        userPreferAge=view.findViewById(R.id.user_prefer_age_tx)
    }
}