package hr.asee.android.template.data.interactor.offering

fun interface DeleteOfferingInteractor {

	suspend operator fun invoke(offerId: Int)
}