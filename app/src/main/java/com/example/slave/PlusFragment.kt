package com.example.slave

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.slave.databinding.FragmentPlusBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlusFragment : Fragment() {
    private var _binding: FragmentPlusBinding? = null
    private lateinit var mainActivity: MainActivity
    private val binding get() = _binding!!
//    val helper = SqliteHelper(mainActivity,"numberBook",null,1)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlusBinding.inflate(inflater, container, false)
        mainActivity = context as MainActivity
        binding.plusBtn.setOnClickListener{
            if(binding.name.text.toString()==""){
                Toast.makeText(mainActivity,"이름을 입력해주세요.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(binding.number.text.toString()==""){
                Toast.makeText(mainActivity,"전화번호를 입력해주세요.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            saveNumberBook(binding.name.text.toString(),binding.number.text.toString())
        }
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun saveNumberBook(name: String, number: String){
        binding.plusBtn.setOnClickListener {
            val numberBook = NumberBook(null,name,number)
            mainActivity.helper.insertNumberBook(numberBook)
            Toast.makeText(mainActivity,"등록되었습니다.",Toast.LENGTH_SHORT).show()
            binding.name.setText("")
            binding.number.setText("")
        }
    }
}