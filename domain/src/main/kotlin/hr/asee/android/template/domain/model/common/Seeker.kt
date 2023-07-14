package hr.asee.android.template.domain.model.common

import org.threeten.bp.LocalDateTime

class Seeker(
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
}

/*-------For testing-------*/
val exampleSeeker = Seeker(
    firstName = "seeker",
    lastName = "one",
    email = "seeker1@test.com",
    createdDate = LocalDateTime.of(2023, 4, 18, 14, 1)
)
/*-------------------------*/
