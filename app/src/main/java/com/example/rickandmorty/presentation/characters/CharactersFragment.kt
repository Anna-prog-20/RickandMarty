package com.example.rickandmorty.presentation.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.internal.findRootView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.data.cloud.CloudDataSource
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.presentation.MainActivity
import com.example.rickandmorty.presentation.characters.adapter.CharactersAdapter
import com.example.rickandmorty.presentation.general.MvpFragment
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CharactersFragment : MvpFragment(R.layout.fragment_characters),
    CharactersView,
    CharactersAdapter.Delegate {
    companion object {
        fun newInstance() = CharactersFragment()
    }

    @Inject
    lateinit var characterData: CloudDataSource

    @Inject
    lateinit var router: Router
    private val presenter: CharactersPresenter by moxyPresenter {
        CharactersPresenter(characterData, router)
    }
    private val vb: FragmentCharactersBinding by viewBinding(CreateMethod.INFLATE)
    private var adapter: CharactersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        vb.root

    override fun showCharacters(characters: List<Character>) {
        vb.characters.layoutManager = LinearLayoutManager(context)
        adapter = CharactersAdapter(this)
        vb.characters.adapter = adapter
        vb.pageNext.setOnClickListener {
            presenter.showCharactersPage("next")
        }
        vb.pagePrev.setOnClickListener {
            presenter.showCharactersPage("prev")
        }
        adapter?.submitList(characters)
        adapter?.notifyDataSetChanged()
    }

    override fun updateListCharacters(characters: List<Character>) {
        adapter?.submitList(characters)
        adapter?.notifyDataSetChanged()
    }

    override fun showError(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onClickItem(character: Character) {
        presenter.displayCharacter(character)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).title = "Rick and Morty"
    }
}