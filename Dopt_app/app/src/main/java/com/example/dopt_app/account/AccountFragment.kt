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
import com.example.dopt_app.databinding.FragmentAccountBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.auth.emailInfo
import com.example.dopt_app.data.User_Signup
import com.google.android.material.textfield.TextInputEditText


class AccountFragment : Fragment() {
    private val TAG = "AccountFragment"

    lateinit var binding: FragmentAccountBinding

    private lateinit var profileNameTx: TextView
    private lateinit var userLocationTx: TextView
    private lateinit var userIdTx: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)

        var GET_user_Info = MutableLiveData<User_Signup>()
        //사용자 정보 GET
        //파라미터로 자신의 이메일을 넣는다
        RetrofitClient.User_Signup_instance.GET_User_Signup(emailInfo)
            .enqueue(object: Callback <User_Signup> {
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



        binding.profileDetailBtn.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ProfileFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileNameTx = view.findViewById(R.id.profile_name_tx)
        userLocationTx= view.findViewById(R.id.user_location_tx)
        userIdTx= view.findViewById(R.id.user_id_tx)
    }
}