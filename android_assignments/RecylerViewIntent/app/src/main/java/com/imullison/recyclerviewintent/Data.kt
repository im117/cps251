package com.imullison.recyclerviewintent

class Data {
    companion object {
        val images = Array(8) {
            //initializer function
            arrayOf(
                R.drawable.android_image_1,
                R.drawable.android_image_2,
                R.drawable.android_image_3,
                R.drawable.android_image_4,
                R.drawable.android_image_5,
                R.drawable.android_image_6,
                R.drawable.android_image_7,
                R.drawable.android_image_8,
            ).random()
        }
        val titles = Array<String>(8) {
            //initializer function
            arrayOf(
                "Chapter One",
                "Chapter Two",
                "Chapter Three",
                "Chapter Four",
                "Chapter Five",
                "Chapter Six",
                "Chapter Seven",
                "Chapter Eight",
            ).random()
        }
        val details = Array<String>(8) {
            //initializer function
            arrayOf(
                "Item one details",
                "Item two details",
                "Item three details",
                "Item four details",
                "Item five details",
                "Item six details",
                "Item seven details",
                "Item eight details",
            ).random()
        }



    }
}
