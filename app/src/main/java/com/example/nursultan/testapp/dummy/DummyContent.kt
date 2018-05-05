package com.example.nursultan.testapp.dummy

import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    var ITEMS: MutableList<DummyItem> = ArrayList()

    var ITEM_MAP: MutableMap<String, DummyItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createDummyItem(i, "http://167.99.199.39/media/uploads/book_images/gal_bg.jpg", getRandomString(6)))
        }
    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createDummyItem(position: Int, path: String, title: String): DummyItem {
        return DummyItem(position.toString(), path, title)
    }

    private fun getRandomString(len: Int): String {
        val regex = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val salt = StringBuilder()
        val rnd = Random()
        while (salt.length < len) {
            val index = (rnd.nextFloat() * regex.length).toInt()
            salt.append(regex[index])
        }
        return salt.toString()
    }


    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    class DummyItem(val id: String, val path: String, val title: String) {

        override fun toString(): String {
            return path
        }
    }
}
