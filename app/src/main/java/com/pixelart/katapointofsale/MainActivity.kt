package com.pixelart.katapointofsale

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Contract.View {
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter(this)
    }

    override fun showItems(items: String) {
        tvItems.text = items
    }

    override fun showTotal(subTotal: String, discount: String, tax: String, total: String) {
        tvTotal.text = total
        tvSubTotal.text = subTotal
        tvDiscount.text = discount
        tvTax.text = tax
    }

    fun onButtonClick(view: View){
        when(view){
            btnAdd ->{
                val name = etItemLabel.text.toString()
                val price = etPrice.text.toString()
                val quantity = etQuantity.text.toString()

                if (name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()) {
                    presenter.addItem(name, price.toDouble(), quantity.toInt())
                    tvInfo.text = ""
                    hideKeyboard(view)
                }
                else
                    tvInfo.text = resources.getString(R.string.text_field_blank)

                etItemLabel.setText("")
                etPrice.setText("")
                etQuantity.setText("")
            }
            btnTotal ->{
                val state = spState.selectedItem.toString()
                if (state.contains("Select State", true)){
                    tvInfo.text = resources.getString(R.string.select_state)
                    return
                }else{
                    presenter.calculateTotal(state)
                    tvInfo.text = ""
                }
            }
            btnClear ->{
                presenter.clearResults()
            }
        }
    }

    private fun hideKeyboard(view: View){
        val inputManager: InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.applicationWindowToken,0)
    }
}
