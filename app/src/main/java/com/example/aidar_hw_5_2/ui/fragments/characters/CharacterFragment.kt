package com.example.aidar_hw_5_2.ui.fragments.characters

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aidar_hw_5_2.R
import com.example.aidar_hw_5_2.data.model.characters.Character
import com.example.aidar_hw_5_2.databinding.FragmentCharacterBinding
import com.example.aidar_hw_5_2.ui.fragments.base.BaseFragment
import com.example.aidar_hw_5_2.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import dev.androidbroadcast.vbpd.viewBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment :
    BaseFragment<FragmentCharacterBinding, CharacterViewModel>(FragmentCharacterBinding::inflate) {

    override val viewModel: CharacterViewModel by viewModels()
    private lateinit var charactersAdapter: CharacterAdapter

    override fun setupViews() {
        charactersAdapter = CharacterAdapter { model -> onCharacterClick(model) }
        binding.rvCharacter.apply {
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun onCharacterClick(model: Character) {
        val action =
            CharacterFragmentDirections.actionCharacterFragmentToCharactersDetailFragment(model.id)
        findNavController().navigate(action)
    }

    override fun setupObservers() {
        viewModel.getAllCharacters().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> viewLifecycleOwner.lifecycleScope.launch {
                    binding.pgCharacter.visibility = View.GONE
                    charactersAdapter.submitData(resource.data)
                }

                is Resource.Error -> {
                    binding.pgCharacter.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    binding.pgCharacter.visibility = View.VISIBLE
                }
            }
        }
    }
}