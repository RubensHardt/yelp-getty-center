package com.rubenshardt.yelpgettycenter.modules.businessdetails

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.rubenshardt.yelpgettycenter.model.business.Business
import com.rubenshardt.yelpgettycenter.model.business.Coordinates
import com.rubenshardt.yelpgettycenter.utils.repositories.YelpRepositoryInterface
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class BusinessDetailsViewModelTest {

    private lateinit var viewModel: BusinessDetailsViewModel
    private var businessObserver: Observer<Business> = mock()
    private var refreshingObserver: Observer<Boolean> = mock()
    private var loadingObserver: Observer<Boolean> = mock()
    private var errorObserver: Observer<String> = mock()
    private var coordinatesObserver: Observer<Coordinates> = mock()
    private var hoursOfOperationObserver: Observer<Unit> = mock()
    private var phoneNumberObserver: Observer<String> = mock()
    private var externalUrlObserver: Observer<String> = mock()
    private var photoUrlObserver: Observer<String> = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `init should get business`() {
        /* Given */
        val repository = mockYelpRepository(true)

        /* When */
        viewModel = mockViewModel(repository)

        /* Then */
        verify(repository).getBusiness(any(), any())
    }

    @Test
    fun `init should remove loading when get business succeeds`() {
        /* Given */
        val repository = mockYelpRepository(true)

        /* When */
        viewModel = mockViewModel(repository)

        /* Then */
        inOrder(repository, loadingObserver) {
            verify(repository).getBusiness(any(), any())
            verify(loadingObserver).onChanged(false)
        }
    }

    @Test
    fun `init should show error message when get business fails`() {
        /* Given */
        val repository = mockYelpRepository(false)

        /* When */
        viewModel = mockViewModel(repository)

        /* Then */
        inOrder(repository, errorObserver, loadingObserver) {
            verify(repository).getBusiness(any(), any())
            verify(errorObserver).onChanged(any())
            loadingObserver.onChanged(false)
        }
    }

    @Test
    fun `refreshBusinessDetails should show refreshing loading`() {
        /* Given */
        val repository = mockYelpRepository(true)
        viewModel = mockViewModel(repository)

        /* When */
        viewModel.refreshBusinessDetails()

        /* Then */
        inOrder(repository, refreshingObserver) {
            refreshingObserver.onChanged(true)
            verify(repository).refreshBusiness(any(), any())
        }
    }

    @Test
    fun `refreshBusinessDetails success should remove loading`() {
        /* Given */
        val repository = mockYelpRepository(true)
        viewModel = mockViewModel(repository)

        /* When */
        viewModel.refreshBusinessDetails()

        /* Then */
        inOrder(repository, refreshingObserver) {
            verify(repository).refreshBusiness(any(), any())
            refreshingObserver.onChanged(false)
        }
    }

    @Test
    fun `refreshBusinessDetails fail should show error message`() {
        /* Given */
        val repository = mockYelpRepository(false)
        viewModel = mockViewModel(repository)

        /* When */
        viewModel.refreshBusinessDetails()

        /* Then */
        inOrder(repository, errorObserver) {
            verify(repository).refreshBusiness(any(), any())
            verify(errorObserver).onChanged(any())
            refreshingObserver.onChanged(false)
        }
    }

    @Test
    fun `onMapClicked should update coordinatesLiveEvent`() {
        /* Given */
        viewModel = mockViewModel()
        val coordinates = Coordinates()

        /* When */
        viewModel.onMapClicked(coordinates)

        /* Then */
        verify(coordinatesObserver).onChanged(coordinates)
    }

    @Test
    fun `onHoursOfOperationClicked should update hoursOfOperationLiveEvent`() {
        /* Given */
        viewModel = mockViewModel()

        /* When */
        viewModel.onHoursOfOperationClicked()

        /* Then */
        verify(hoursOfOperationObserver).onChanged(anyOrNull())
    }

    @Test
    fun `onCallClicked should update phoneNumberLiveEvent`() {
        /* Given */
        viewModel = mockViewModel()
        val phoneNumber = "phone"

        /* When */
        viewModel.onCallClicked(phoneNumber)

        /* Then */
        verify(phoneNumberObserver).onChanged(phoneNumber)
    }

    @Test
    fun `onVisitWebsiteClicked should update externalUrlLiveEvent`() {
        /* Given */
        viewModel = mockViewModel()
        val url = "url"

        /* When */
        viewModel.onVisitWebsiteClicked(url)

        /* Then */
        verify(externalUrlObserver).onChanged(url)
    }

    @Test
    fun `onPhotoClicked should update photoUrlLiveEvent`() {
        /* Given */
        viewModel = mockViewModel()
        val url = "url"

        /* When */
        viewModel.onPhotoClicked(url)

        /* Then */
        verify(photoUrlObserver).onChanged(url)
    }

    @Suppress("UNCHECKED_CAST")
    private fun mockYelpRepository(requestSuccess: Boolean): YelpRepositoryInterface {
        return mock {
            on { getBusiness(anyOrNull(), anyOrNull()) }.doAnswer {
                val onSuccess = it.arguments[0] as? (() -> Unit)?
                val onError = it.arguments[1] as? ((Throwable) -> Unit)?
                if (requestSuccess) {
                    onSuccess?.invoke()
                } else {
                    onError?.invoke(Throwable("error"))
                }
                mock()
            }
            on { refreshBusiness(anyOrNull(), anyOrNull()) }.doAnswer {
                val onSuccess = it.arguments[0] as? (() -> Unit)?
                val onError = it.arguments[1] as? ((Throwable) -> Unit)?
                if (requestSuccess) {
                    onSuccess?.invoke()
                } else {
                    onError?.invoke(Throwable("error"))
                }
            }
        }
    }

    private fun mockViewModel(yelpRepository: YelpRepositoryInterface = mockYelpRepository(true)): BusinessDetailsViewModel {
        return BusinessDetailsViewModel(yelpRepository).apply {
            businessLiveData.observeForever(businessObserver)
            refreshingLiveData.observeForever(refreshingObserver)
            loadingLiveData.observeForever(loadingObserver)
            errorLiveEvent.observeForever(errorObserver)
            coordinatesLiveEvent.observeForever(coordinatesObserver)
            hoursOfOperationLiveEvent.observeForever(hoursOfOperationObserver)
            phoneNumberLiveEvent.observeForever(phoneNumberObserver)
            externalUrlLiveEvent.observeForever(externalUrlObserver)
            photoUrlLiveEvent.observeForever(photoUrlObserver)
        }
    }
}