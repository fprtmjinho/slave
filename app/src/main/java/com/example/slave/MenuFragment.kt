package com.example.slave

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slave.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private lateinit var mainActivity: MainActivity
    private val binding get() = _binding!!
//    val helper = SqliteHelper(mainActivity,"numberBook",null,1)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        mainActivity = context as MainActivity
        setNumberBook()
        return binding.root
    }
    private fun setNumberBook() {
        var numberBookListAdapter = NumberBookListAdapter()
        numberBookListAdapter.listData.addAll(mainActivity.helper.selectNumberBook())
        numberBookListAdapter.helper = mainActivity.helper
        binding.numberBookListRc.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = numberBookListAdapter
            numberBookListAdapter.notifyDataSetChanged()
        }
    }
}