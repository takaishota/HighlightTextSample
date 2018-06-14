package com.example.android.st0124.highlighttextsample

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Highlight(
        @PrimaryKey open var id : String = UUID.randomUUID().toString(),
        @Required open var text : String = "",
        open var startIndex : Int = 0,
        open var endIndex : Int = 0,
        open var created : Date = Date()
) : RealmObject() {}