package com.example.android.st0124.highlighttextsample.util

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults
import io.realm.kotlin.deleteFromRealm
import kotlin.reflect.KClass

interface RealmRepository<T: RealmModel> {
    fun getBy(id: String, type: KClass<T>): T? {
        return Realm.getDefaultInstance().where(type.java).equalTo("id", id).findFirst()
    }
    fun getAll(type: KClass<T>): RealmResults<T> {
        return Realm.getDefaultInstance().where(type.java).findAll()
    }
    fun store(item: T) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.executeTransaction {
                realm.insert(item)
                println("保存しました $item")
            }
        } catch (e: Throwable) {
            println("保存に失敗しました ${e.localizedMessage}")
        }
    }
    fun delete(item: T) {
        val realm = Realm.getDefaultInstance()
        try {
            realm.executeTransaction {
                item.deleteFromRealm()
                println("削除しました $item")
            }
        } catch (e: Throwable) {
            println("削除に失敗しました ${e.localizedMessage}")
        }
    }

    fun deleteAll() {
        val realm = Realm.getDefaultInstance()
        try {
            realm.executeTransaction {
                realm.deleteAll()
                println("DBをクリアしました")
            }
        } catch (e: Throwable) {
            println("削除に失敗しました ${e.localizedMessage}")
        }
    }
}