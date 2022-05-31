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
import com.example.dopt_app.databinding.ActivityPreferBinding
import com.example.dopt_app.databinding.ActivityPreferbreadBinding
import kotlinx.android.synthetic.main.activity_preferbread.*

class PreferbreadActivity : BaseActivity<ActivityPreferbreadBinding>(ActivityPreferbreadBinding::inflate), View.OnClickListener{

    private lateinit var preferInfo : String
    private lateinit var breed : String

    override fun initAfterBinding() {
        binding.breedPreviousBtn.setOnClickListener(this)
        binding.breedNextBtn.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            it.getStringExtra("breedInfo")?.let{ content->
                preferInfo=content
            }
        }

        preferSpinner()

    }

    private fun preferSpinner() {
        binding.dogBreedSp.adapter=

            ArrayAdapter.createFromResource(this, R.array.bread_cat,android.R.layout.simple_spinner_dropdown_item)

        binding.dogBreedSp.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                breed=binding.dogBreedSp.selectedItem.toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.breedPreviousBtn -> startActivityWithClear(JoinActivity::class.java)
            binding.breedNextBtn -> breed()

        }
    }

    private fun breed() {
        if (breed.isEmpty()) {
            Toast.makeText(this, "선호하는 동물이 선택되지 않았습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        breed += ","
        preferInfo += breed
        val intent = Intent(this,PrefercolorActivity::class.java)
        intent.putExtra("preferInfo",preferInfo)
        Log.d("PREFERINFO",preferInfo)
        startActivity(intent)

    }
}