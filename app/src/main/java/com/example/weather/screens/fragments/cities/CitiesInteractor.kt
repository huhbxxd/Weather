package com.example.weather.screens.fragments.cities

import com.example.weather.core.base.BaseInteractor
import com.example.weather.data.response.cities.Cities
import com.example.weather.data.response.cities.CitiesFields
import com.example.weather.data.repositories.cities.CitiesRepository
import com.example.weather.data.repositories.stored.StoredCitiesRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class CitiesInteractor(
    private val repository: CitiesRepository,
    private val repositoryStored: StoredCitiesRepository
): BaseInteractor() {

    companion object {
        const val DELAY_TIME = 300L
        const val PAGE_COUNT = 30
    }

//    private val queryCitiesSubject = PublishSubject.create<Pair<String, Int>>()
//
//    // get cities list that saved cities that user chosen from search list
//    // get from local preferences
//    fun getListCities(onComplete: (List<CitiesFields>) -> Unit, onError: (Throwable) -> Unit) {
//        disposable.add(repositoryStored.getListCities()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(onComplete, onError))
//    }
//
//    // find all cities by query
//    fun subscribeOnCitiesSearch(onSuccess: (Cities) -> Unit, onError: (Throwable) -> Unit) {
//        disposable.add(queryCitiesSubject.debounce(DELAY_TIME, TimeUnit.MILLISECONDS)
//            .switchMapSingle { repository.getListCities(it.first, it.second) }
//            .doOnError(onError)
//            .retry()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(onSuccess, onError))
//    }
//
//    fun postQuery(q: String, page: Int, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
//        disposable.add(Completable.fromCallable { queryCitiesSubject.onNext(Pair(q, page)) }
//            .subscribe(onSuccess, onError))
//    }

}