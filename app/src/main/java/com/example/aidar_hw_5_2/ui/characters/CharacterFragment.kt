package com.example.aidar_hw_5_2.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aidar_hw_5_2.databinding.FragmentCharacterBinding
import dev.androidbroadcast.vbpd.viewBinding

class CharacterFragment : Fragment() {

    private val binding by viewBinding(FragmentCharacterBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}