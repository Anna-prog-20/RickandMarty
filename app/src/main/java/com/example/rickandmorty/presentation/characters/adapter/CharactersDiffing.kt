package com.example.rickandmorty.presentation.characters.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.data.model.Character

object CharactersDiffing : DiffUtil.ItemCallback<Character>() {

    private val payload = Any()

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Character, newItem: Character) = payload

}