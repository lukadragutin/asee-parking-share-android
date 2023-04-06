package hr.asee.android.template.data.model.common

// Marker interface for serialization obfuscation problem fix (proguard-rules.pro)
// Gson and similar serializers will not know how to serialize/deserialize obfuscated models, so those models should be left out of the obfuscation process.
interface SerializableDataModel
