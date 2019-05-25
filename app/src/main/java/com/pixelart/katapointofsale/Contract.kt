package com.pixelart.katapointofsale

interface Contract {
    interface View{
        fun showItems(items: String)
        fun showTotal(subTotal: String, discount:String, tax: String, total: String)
    }
    interface Presenter{
        fun addItem(name: String, price: Double, quantity: Int)
        fun calculateTotal(state: String)
        fun clearResults()
    }
}