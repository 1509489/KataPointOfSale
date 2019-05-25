package com.pixelart.katapointofsale

import java.lang.StringBuilder

class Presenter(private val view:Contract.View) : Contract.Presenter {
    private val items = ArrayList<Item>()
    private var totalNoTax:Double = 0.0

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
        totalNoTax += totalPrice
    }

    override fun calculateTotal(state: String) {
        val tax = String.format(String.format("%.2f", applyTax(state, totalNoTax)))
        val discount = String.format("%.2f", applyDiscount(totalNoTax))
        val subTotal = String.format("%.2f", totalNoTax)
        val total = (totalNoTax + this.applyTax(state, totalNoTax) - this.applyDiscount(totalNoTax))

        view.showTotal(subTotal, discount, tax, String.format("%.2f", total))
    }

    override fun clearResults() {
        items.clear()
        totalNoTax = 0.0
        view.showItems("")
        view.showTotal("", "", "", "")
    }

    private fun applyDiscount(subTotal: Double):Double{
        val discountRate: Double
        val discount: Double

        when{
            subTotal > 1000 ->{
                discountRate = 0.03
                discount = subTotal * discountRate
                return discount
            }
            subTotal > 5000 ->{
                discountRate = 0.05
                discount = subTotal * discountRate
                return discount
            }
            subTotal > 7000 ->{
                discountRate = 0.07
                discount = subTotal * discountRate
                return discount
            }
            subTotal > 10000 ->{
                discountRate = 0.10
                discount = subTotal * discountRate
                return discount
            }
            subTotal > 15000 ->{
                discountRate = 0.15
                discount = subTotal * discountRate
                return discount
            }
        }
        return 0.0
    }

    private fun applyTax(state: String, subTotal: Double):Double{
        val tax: Double
        val taxRate: Double
        when(state) {
            "UT" -> {
                taxRate = 0.0685
                tax = subTotal * taxRate
                return tax
            }
            "NV" -> {
                taxRate = 0.08
                tax = subTotal * taxRate
                return tax
            }
            "TX" -> {
                taxRate = 0.0625
                tax = subTotal * taxRate
                return tax
            }
            "AL" -> {
                taxRate = 0.04
                tax = subTotal * taxRate
                return tax
            }
            "CA" -> {
                taxRate = 0.0825
                tax = subTotal * taxRate
                return tax
            }
            "LA" -> {
                taxRate = 0.04
                tax = subTotal * taxRate
                return tax
            }
        }
        return 0.0
    }
}