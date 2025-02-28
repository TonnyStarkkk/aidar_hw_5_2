package com.example.aidar_hw_5_2.ui.fragments.character_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.aidar_hw_5_2.R
import com.example.aidar_hw_5_2.databinding.FragmentCharacterDetailBinding
import com.example.aidar_hw_5_2.ui.fragments.base.BaseFragment
import com.example.aidar_hw_5_2.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import dev.androidbroadcast.vbpd.viewBinding

@AndroidEntryPoint
class CharactersDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>(
        FragmentCharacterDetailBinding::inflate
    ) {

    override val viewModel: CharacterDetailViewModel by viewModels()
    private val args: CharactersDetailFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun setupObservers() = with(binding) {
        viewModel.getCharacterById(args.id).observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    characterName.text = resource.data.name
                    characterStatus.text = resource.data.status
                    characterLocation.text = resource.data.location.name
                    characterGender.text = resource.data.gender

                    Glide.with(this@CharactersDetailFragment)
                        .load(resource.data.image)
                        .into(characterImage)

                    expandable.secondLayout.findViewById<TextView>(R.id.tv_character_info)?.text =
                        "ID: ${resource.data.id}\nSpecies: ${resource.data.species}\nType: ${resource.data.type}"

                    expandable2.secondLayout.findViewById<TextView>(R.id.tv_origin)?.text =
                        "Origin: ${resource.data.origin.name}"

                    expandable3.secondLayout.findViewById<TextView>(R.id.tv_first_seen)?.text =
                        "First seen in: ${resource.data.episode.firstOrNull() ?: "Unknown"}"

                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()

                }

                is Resource.Loading -> {}
            }
        }
    }

    override fun setupViews() {
        val expandables = listOf(
            binding.expandable,
            binding.expandable2,
            binding.expandable3
        )
        expandables.forEach { expandable ->
            expandable.parentLayout.setOnClickListener {
                if (expandable.isExpanded) {
                    expandable.collapse()
                } else {
                    expandable.expand()
                }
            }
        }
    }
}