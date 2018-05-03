package com.example.steven.kotlinicecream

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_order_history.*

class OrderHistoryActivity : AppCompatActivity() {

    var orders = ArrayList<OrderItem>()
    var stringOrders = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intent = intent
        orders = intent.getSerializableExtra("DATA") as ArrayList<OrderItem>
        for (order in orders) {
            stringOrders.add(order.toString())
        }
        val adapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, stringOrders)
        historyView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
