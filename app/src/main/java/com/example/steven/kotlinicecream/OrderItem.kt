package com.example.steven.kotlinicecream

import android.icu.text.SimpleDateFormat
import java.io.Serializable
import java.text.DateFormat
import java.util.*

class OrderItem(toppings: Array<String>, subTotal: Double, fudge: Int, size: String, flavor: String) : Serializable {
    val df: DateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT)
    private val toppings: Array<String>
    private val orderTotal: Double
    private val taxPaid: Double
    private val subTotal: Double
    private val size: String
    private val fudge: Int //cannot be > 3;
    private val orderDate: Date
    private val flavor: String

    override fun toString(): String {
        return "Order: $size $flavor cone\n" +
                "Order placed: " + df.format(orderDate) + "\n" +
                "Toppings: " + getToppings() + "\n" +
                "Sub-total: " + "$" + String.format("%.2f", subTotal) + "\n" +
                "Tax paid: " + "$" + String.format("%.2f", taxPaid) + "\n" +
                "Total Cost: " + "$" + String.format("%.2f", orderTotal)
    }

    init {
        this.toppings = toppings
        this.size = size
        this.subTotal = subTotal
        this.fudge = fudge
        this.flavor = flavor
        orderDate = Date()
        taxPaid = 0.07 * subTotal
        orderTotal = subTotal + taxPaid
    }

    private fun getToppings(): String {
        var temp = ""
        if (toppings.isEmpty() && fudge == 0)
            return "none"
        else {
            for (i in toppings.indices) {
                temp += toppings[i] + ", "
            }
        }
        temp += fudge.toString() + " oz. fudge"
        return temp
    }
}