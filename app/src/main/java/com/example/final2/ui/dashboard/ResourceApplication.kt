package com.example.final2.ui.dashboard

import android.app.Application
import com.androiddevs.grocerylist.data.db.ResourceDatabase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ResourceApplication: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ResourceApplication))
        bind() from singleton { ResourceDatabase(instance()) }
        bind() from singleton {
            ResourceRepository(
                instance()
            )
        }
        bind() from provider {
            ResourceViewModelFactory(
                instance()
            )
        }
    }
}