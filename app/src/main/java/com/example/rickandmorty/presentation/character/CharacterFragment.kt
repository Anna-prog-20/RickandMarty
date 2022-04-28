package com.example.rickandmorty.presentation.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.data.cloud.CloudDataSource
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import com.example.rickandmorty.presentation.MainActivity
import com.example.rickandmorty.presentation.general.MvpFragment
import com.example.rickandmorty.presentation.setCircleImageFromUri
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class CharacterFragment(private val character: Character) :
    MvpFragment(R.layout.fragment_character), CharacterView {

    companion object {
        fun newInstance(character: Character) = CharacterFragment(character)
    }

    @Inject
    lateinit var characterData: CloudDataSource

    @Inject
    lateinit var router: Router

    private val presenter: CharacterPresenter by moxyPresenter {
        CharacterPresenter(character, characterData, router)
    }

    private val vb: FragmentCharacterBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        vb.root

    override fun showCharacter(character: Character) {
        vb.imageCharacter.setCircleImageFromUri(character.avatarURL)
        vb.nameCharacter.text = character.name
        vb.genderCharacter.text = character.gender
        vb.statusCharacter.text = character.status
        vb.speciesCharacter.text = character.species
        vb.locationCharacter.text = character.location.name
        vb.countEpisodesCharacter.text = character.countEpisodesToString()
        vb.btnNext.setOnClickListener {
            presenter.backPressed()
        }
    }

    override fun showError(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).title = character.name
    }

}