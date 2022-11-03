package com.example.ns_poc
import com.rollbar.api.payload.data.Person
import com.rollbar.notifier.provider.Provider;

public class PersonProvider : Provider<Person> {

    fun PersonProvider() {}


    override fun provide(): Person {

        // Optionally add details about the current user
        // If you do return a Person object only the id field is required
        return Person.Builder()
            .id("123")
            .username("Jane Smith")
            .build()
    }

}