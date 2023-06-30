package com.example.weatherappkg.ui.searchFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherappkg.databinding.FragmentSearchBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchFragment(private val onClick: Result) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.appCompatButton.setOnClickListener {
            val name = binding.editTextSearch.text.toString().trim()
            onClick.click(name)
            onDestroyView()
        }
    }

    interface Result {
        fun click(name: String)
    }
}
