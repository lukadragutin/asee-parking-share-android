package hr.asee.android.template.domain.model.common

import org.threeten.bp.LocalDateTime

class Admin(
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
): User(
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
)
