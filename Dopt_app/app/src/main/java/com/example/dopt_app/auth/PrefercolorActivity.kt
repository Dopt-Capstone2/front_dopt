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
import com.example.dopt_app.databinding.ActivityPreferbreadBinding
import com.example.dopt_app.databinding.ActivityPrefercolorBinding
import kotlinx.android.synthetic.main.activity_prefercolor.*

class PrefercolorActivity : BaseActivity<ActivityPrefercolorBinding>(ActivityPrefercolorBinding::inflate), View.OnClickListener{

    private lateinit var preferInfo : String
    private lateinit var color : String

    override fun initAfterBinding() {
        binding.colorPreviousBtn.setOnClickListener(this)
        binding.colorNextBtn.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            it.getStringExtra("colorInfo")?.let{ content->
                preferInfo=content
            }
        }

        preferSpinner()

    }

    private fun preferSpinner() {
        binding.dogColorSp.adapter=

            ArrayAdapter.createFromResource(this, R.array.bread_color,android.R.layout.simple_spinner_dropdown_item)

        binding.dogColorSp.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                color=binding.dogColorSp.selectedItem.toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.colorPreviousBtn -> startActivityWithClear(PreferbreadActivity::class.java)
            binding.colorNextBtn -> color()

        }
    }

    private fun color() {
        if (color.isEmpty()) {
            Toast.makeText(this, "선호하는 동물의 색상이 선택되지 않았습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        color += ","
        preferInfo += color
        val intent = Intent(this,PrefergenderActivity::class.java)
        intent.putExtra("preferInfo",preferInfo)
        Log.d("PREFERINFO",preferInfo)
        startActivity(intent)

    }
}