package com.example.rickandmorty.presentation.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.presentation.setCircleImageFromUri

class CharactersAdapter (private val delegate: Delegate) :
    ListAdapter<Character, CharactersAdapter.ViewHolder>(CharactersDiffing) {

    interface Delegate {
        fun onClickItem(character: Character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemCharacterBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)

    class ViewHolder(private val vb: ItemCharacterBinding) : RecyclerView.ViewHolder(vb.root),
        CharacterItemView {
        override fun bind(character: Character, delegate: Delegate) {
            with(vb) {
                character?.let {
                    imageCharacter.setCircleImageFromUri(it.avatarURL)
                    nameCharacter.text = it.name
                    speciesCharacter.text = it.species
                    genderCharacter.text = it.gender
                }
                root.setOnClickListener { delegate.onClickItem(character) }
            }
        }
    }
}