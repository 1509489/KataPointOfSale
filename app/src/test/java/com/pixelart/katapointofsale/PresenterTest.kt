package com.pixelart.katapointofsale

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class PresenterTest {
    private val view = mock<Contract.View>()
    private lateinit var presenter: Presenter

    @Before
    fun setUp() {
        presenter = Presenter(view)
    }

    @Test
    fun `Add Item`() {
        presenter.addItem("Item", 10.23, 3)
        verify(view).showItems(anyString())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `Calculate Total`() {
        presenter.calculateTotal("CA")
        verify(view).showTotal(anyString(), anyString(), anyString(), anyString())
        verifyNoMoreInteractions(view)
    }
}