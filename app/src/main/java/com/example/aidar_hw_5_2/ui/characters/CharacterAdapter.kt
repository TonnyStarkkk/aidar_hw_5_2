package com.example.aidar_hw_5_2.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.aidar_hw_5_2.R
import com.example.aidar_hw_5_2.data.model.characters.Character
import com.example.aidar_hw_5_2.databinding.ItemCharacterBinding

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private val characterList = mutableListOf<Character>()

    inner class ViewHolder(private val binding: ItemCharacterBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun onBind(character: Character) = with(binding) {
                    characterName.text = character.name
                    characterLocation.text = character.location.name
                    characterFirstSeen.text = character.origin.name
                    characterStatus.text = character.status
                    imgCharacter.load(character.image) {
                        crossfade(true)
                    }
                    colorIndicator.setImageResource(
                        when {
                            character.status?.contains("Dead") == true -> R.drawable.ic_circle_red
                            character.status?.contains("Alive") == true -> R.drawable.ic_circle_green
                            else -> R.drawable.ic_circle_grey
                        }
                    )
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]
        holder.onBind(character)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}