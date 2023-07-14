package hr.asee.android.template.domain.model.common

import hr.asee.android.template.domain.model.common.service.Offer
import org.threeten.bp.LocalDateTime

class Giver(
    id: Int = generateId(),
    firstName: String,
    lastName: String,
    email: String,
    activated: Boolean = true,
    langKey: String = "English",
    createdBy: String = "admin",
    createdDate: LocalDateTime,
    lastModifiedBy: String = createdBy,
    lastModifiedDate: LocalDateTime = createdDate,
    authorities: List<String> = listOf(),
    resetDate: LocalDateTime? = null
):
    User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        activated = activated,
        langKey = langKey,
        createdBy = createdBy,
        createdDate = createdDate,
        lastModifiedBy = lastModifiedBy,
        lastModifiedDate = lastModifiedDate,
        authorities = authorities,
        resetDate = resetDate
    ) {

    fun addOffer(offer: Offer) {
        offers.add(offer)
    }
}

/*-------For testing-------*/
val exampleGiver = Giver(
    firstName = "Test",
    lastName = "User",
    email = "test.user@test.com",
    createdDate = LocalDateTime.of(2023, 4, 17, 17, 8)
)
/*-------------------------*/
