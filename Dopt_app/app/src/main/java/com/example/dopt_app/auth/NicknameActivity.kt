package com.example.dopt_app.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dopt_app.BaseActivity
import com.example.dopt_app.R
import com.example.dopt_app.databinding.ActivityNicknameBinding
import kotlinx.android.synthetic.main.activity_nickname.*

// 프로필 입력 순서
// 닉네임 - 종 - 품종 - 성별 - 색 - 크기
class NicknameActivity : BaseActivity<ActivityNicknameBinding>
    (ActivityNicknameBinding::inflate),
    View.OnClickListener {

    override fun initAfterBinding() {
        binding.goProfileBtn.setOnClickListener(this)

        Glide.with(this)
            .load(R.drawable.nickname_cat_img)
            .into(binding.preferKindImg)
    }

    //입력 안하면
    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.goProfileBtn -> nickname()

        }
    }

    private fun nickname() {
        if (binding.joinNicknameJo.text.toString().isEmpty()) {
            Toast.makeText(this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        var userNm = binding.joinNicknameJo.text.toString()
        userNm += ","
        val intent = Intent(this,PreferActivity::class.java)
        intent.putExtra("userInfo",userNm)
        Log.d("USERINFO_NAME",userNm)
        startActivity(intent)

    }

}