package com.pixelart.katapointofsale

import java.lang.StringBuilder

class Presenter(private val view:Contract.View) : Contract.Presenter {
    private val items = ArrayList<Item>()

    override fun addItem(name: String, price: Double, quantity: Int) {
        items.add(Item(name, price, quantity))

        val stringBuilder = StringBuilder()
        var totalPrice = 0.0

        for (item in items){
            totalPrice = item.price * item.quantity
            stringBuilder.append(String.format("%-15s %-15d %-15.2f %-15.2f\n",
                item.name, item.quantity, item.price, totalPrice))
        }
        view.showItems(stringBuilder.toString())
    }

    override fun calculateTotal(state: String) {

    }

    override fun clearResults() {

    }
}