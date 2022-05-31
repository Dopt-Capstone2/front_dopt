package com.example.dopt_app.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.BaseActivity
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.api.RetrofitClient.Preference_instance
import com.example.dopt_app.data.Preference
import com.example.dopt_app.databinding.ActivityPreferBinding
import kotlinx.android.synthetic.main.activity_prefer.*

class PreferActivity : BaseActivity<ActivityPreferBinding>(ActivityPreferBinding::inflate), View.OnClickListener{

    private lateinit var preferInfo : String
    private lateinit var kind : String

    override fun initAfterBinding() {
        binding.dogbreedPreviousBtn.setOnClickListener(this)
        binding.dogbreedNextBtn.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            it.getStringExtra("preferInfo")?.let{ content->
                preferInfo=content
            }
        }
        preferSpinner()
    }

    private fun preferSpinner() {
        binding.dogPreferKindSp.adapter=
            ArrayAdapter.createFromResource(this, R.array.kind,android.R.layout.simple_spinner_dropdown_item)

        binding.dogPreferKindSp.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                kind=binding.dogPreferKindSp.selectedItem.toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.dogbreedPreviousBtn -> startActivityWithClear(NicknameActivity::class.java)
            binding.dogbreedNextBtn -> kind()

        }
    }

    private fun kind() {
        if (kind.isEmpty()) {
            Toast.makeText(this, "선호하는 동물이 선택되지 않았습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        kind += ","
        preferInfo += kind
        val intent = Intent(this,PreferbreadActivity::class.java)
        intent.putExtra("preferInfo",preferInfo)
        Log.d("PREFERINFO",preferInfo)
        startActivity(intent)

    }
}