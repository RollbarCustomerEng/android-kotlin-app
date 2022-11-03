package com.example.ns_poc

import com.rollbar.notifier.provider.Provider;

public class CustomProvider : Provider<Map<String, Any?>> {

    fun CustomProvider() {}


    override fun provide(): Map<String, Any?> {

        // Additional custom data field that you want added to every occurrence sent to Rollbar
        val customFields = HashMap<String, Any?>()
        customFields["abc"] = "def"

        return customFields
    }

}