package com.example.rickandmorty.presentation.characters

import android.util.Log
import com.example.rickandmorty.data.cloud.CloudDataSource
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.presentation.AndroidScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class CharactersPresenter(
    private val characters: CloudDataSource,
    private val router: Router
) : MvpPresenter<CharactersView>() {

    private var disposable = CompositeDisposable()
    private var page: Int = 1

    override fun onFirstViewAttach() {
        disposable.add(
            characters
                .getCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    viewState::showCharacters
                ) { error ->

                    viewState.showError(error.message)
                }
        )
    }

    fun displayCharacter(character: Character) =
        router.navigateTo(AndroidScreens().character(character))

    fun showCharactersPage(navigation: String) {
        if (page <= 1) {
            disposable.add(
                characters
                    .getInfoCharacters()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { info ->
                            if (navigation == "next")
                                page = info.infoCharacters.nextPage()
                            if (navigation == "prev")
                                page = info.infoCharacters.prevPage() ?: 0
                            disposable.add(
                                characters
                                    .getCharacters(page)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                        {
                                            viewState.updateListCharacters(it)
                                        },
                                        { error ->
                                            viewState.showError(error.message)
                                        }
                                    )
                            )
                        },
                        { error ->
                            viewState.showError(error.message)
                        }
                    )

            )
        } else {
            disposable.add(
                characters
                    .getInfoCharacters(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { info ->
                            if (navigation == "next")
                                page = info.infoCharacters.nextPage()
                            if (navigation == "prev")
                                page = info.infoCharacters.prevPage() ?: 0
                            disposable.add(
                                characters
                                    .getCharacters(page)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                        {
                                            viewState.updateListCharacters(it)
                                        },
                                        { error ->
                                            viewState.showError(error.message)
                                        }
                                    )
                            )
                        },
                        { error ->
                            viewState.showError(error.message)
                        }
                    )

            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}