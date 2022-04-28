package com.example.rickandmorty.presentation.character

import com.example.rickandmorty.data.cloud.CloudDataSource
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.presentation.AndroidScreens
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class CharacterPresenter(
    private val character: Character,
    private val dataSource: CloudDataSource,
    private val router: Router
) : MvpPresenter<CharacterView>() {

    private var disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposable.add(
            dataSource
                .getCharacterById(character.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    viewState::showCharacter
                ) { error ->
                    viewState.showError(error.message)
                }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    fun backPressed() {
        router.backTo(AndroidScreens().characters())
    }
}