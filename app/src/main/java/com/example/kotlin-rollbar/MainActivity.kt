package com.example.ns_poc

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.ns_poc.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

import com.rollbar.android.Rollbar
import com.rollbar.notifier.config.ConfigBuilder
import com.rollbar.android.provider.ClientProvider
import com.rollbar.android.provider.ClientProvider.Builder


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRollbar()
        Rollbar.instance().error(Exception("This is a test error")) //remove this after initial testing

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun initRollbar(): Void? {

        // Access token is read from AndroidManifest.xml
        Rollbar.init(this)

        // Returns config object with accessToken and default config settings
        val config = Rollbar.instance().config()

        // Add data about the client, app version, whether to capture IP data etc.
        val clientBuilder = Builder()
        // clientBuilder.captureIp("anonymize")

        val client = clientBuilder.build()

        // environment and codeVersion should be read dynamically from where
        // your application gets it's configration data
        val environment = "qa"
        // Ideally use the commit SHA for codeVersion
        // See also https://docs.rollbar.com/docs/code-context
        val codeVersion = "abf7ce6"

        /*
        Optional configurations
        custom - Optionally add addition key/value pairs to every occurrence
        person - Add details about the user
        client - Details about app details to send captureIP address, app version etc.
        transformer - A mechanism to add/remove data from the payload just before it
                      is sent to Rollbar. Often used to
                      - Do data scrubbing/data removal
                      - Add data that is only available at the time the error occurs
                      - Set the context property based on something in the payload
                        e.g. owning team based on some details about the error
                      - Move data within the payload data hierarchy
         filter - A mechanism to decide to NOT send the data to Rollbar
                  based on some aspect of the data

         See the following links for further details
             https://docs.rollbar.com/docs/android
             https://github.com/rollbar/rollbar-java/
             https://github.com/rollbar/rollbar-java/tree/master/rollbar-android/
         */

        val provider = ConfigBuilder.withConfig(config)
            .environment(environment)
            .codeVersion(codeVersion)
            .custom(CustomProvider())
            .person(PersonProvider())
            .client(client)
            .transformer(PayloadTransformer()) // optionally define mechanism to modify teh payload befo
            .filter(PayloadFilter())
            .build()

        Rollbar.instance().configure(provider)
        Rollbar.instance().info("Rollbar in configured and initialized ...")

        return null
    }
}