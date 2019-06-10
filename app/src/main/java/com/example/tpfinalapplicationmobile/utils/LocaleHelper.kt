package com.example.tpfinalapplicationmobile.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.preference.PreferenceManager
import java.util.*

class LocaleHelper {

    private var SelectedLanguage : String = "Locale.Helper.Selected.Language"

    fun onAttach(context: Context) : Context? {
        var lang : String? = getPersistedData(context, Locale.getDefault().language)
        return setLocale(context, lang.toString())
    }

    fun onAttach(context: Context, defaultLanguage: String) : Context? {
        var lang : String? = getPersistedData(context, defaultLanguage)
        return setLocale(context, lang.toString())
    }

    fun setLocale(context: Context, language: String) : Context? {
        persist(context, language)
        return updateResourcesLegacy(context, language)
    }

    private fun getPersistedData(context: Context, defaultLanguage: String) : String? {
        val preferences : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(SelectedLanguage, defaultLanguage)
    }

    private fun persist(context: Context, language: String) {
        val preferences : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor : SharedPreferences.Editor = preferences.edit()

        editor.putString(SelectedLanguage, language)
        editor.apply()

    }

    @SuppressWarnings("deprecation")
    private fun updateResourcesLegacy(context: Context, language: String) : Context?{
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources : Resources = context.resources

        val configuration : Configuration = resources.configuration
        configuration.locale = locale

        resources.updateConfiguration(configuration, resources.displayMetrics)

        return context
    }
}

