package hr.asee.android.template.domain.model.common

enum class Role(val value: String) {

	SEEKER("seeker"),
	GIVER("giver"),
	ADMIN("admin");

	companion object {
		fun fromString(value: String): Role {
			return when(value) {
				SEEKER.value -> SEEKER
				GIVER.value -> GIVER
				ADMIN.value -> ADMIN
				else -> throw IllegalArgumentException("Role doesn't exist")
			}
		}
	}
}