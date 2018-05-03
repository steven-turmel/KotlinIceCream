package com.example.steven.kotlinicecream

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    private val finalPrices: Map<String, Double> = addPrices()
    private var subTotal: Double = 0.0
    private val author = "Steven Turmel"
    private var orderHistory = ArrayList<OrderItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fudgeBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                fudgeAmount.text = Integer.toString(progress) + " oz."
                processCheckBoxChange(seekBar.rootView)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                //this is purposely left blank
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //this is purposely left blank
            }
        })
        sizeSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //Toast.makeText(MainActivity.this, "You selected: " + Integer.toString(position),Toast.LENGTH_SHORT).show();
                processCheckBoxChange(view)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //this is purposely left blank
            }
        }
    }

    fun processOrder(view: View) {
        Toast.makeText(this, "Your order is on the way!", Toast.LENGTH_LONG)
        updatePrice()
        val order = OrderItem(getToppings(), subTotal, fudgeBar.progress, sizeSpinner.selectedItem.toString(), flavorSpinner.selectedItem.toString())
        orderHistory.add(order)
    }

    fun processCheckBoxChange(view: View) {
        updatePrice()
    }

    fun processReset(view: View) {
        Log.d("DEBUG", "Reset Pressed")
        peanutsBox.isChecked = false
        almondsBox.isChecked = false
        oreosBox.isChecked = false
        gummiesBox.isChecked = false
        marshmallowsBox.isChecked = false
        strawberriesBox.isChecked = false
        browniesBox.isChecked = false
        mandmsBox.isChecked = false
        fudgeBar.progress = 0
        fudgeAmount.text = Integer.toString(fudgeBar.progress) + " oz."
        sizeSpinner.setSelection(0)
        updatePrice()
    }

    fun processTheWorks(view: View) {
        Log.d("DEBUG", "'The Works' Pressed.")
        peanutsBox.isChecked = true
        almondsBox.isChecked = true
        oreosBox.isChecked = true
        gummiesBox.isChecked = true
        marshmallowsBox.isChecked = true
        strawberriesBox.isChecked = true
        browniesBox.isChecked = true
        mandmsBox.isChecked = true
        updatePrice()
    }

    private fun updatePrice() {
        var price = 0.0
        if (peanutsBox.isChecked) {
            price += finalPrices["peanuts"] ?: 0.0
        }
        if (almondsBox.isChecked) {
            price += finalPrices["almonds"] ?: 0.0
        }
        if (oreosBox.isChecked) {
            price += finalPrices["oreos"] ?: 0.0
        }
        if (gummiesBox.isChecked) {
            price += finalPrices["gummy bears"] ?: 0.0
        }
        if (marshmallowsBox.isChecked) {
            price += finalPrices["marshmallows"] ?: 0.0
        }
        if (strawberriesBox.isChecked) {
            price += finalPrices["strawberries"] ?: 0.0
        }
        if (browniesBox.isChecked) {
            price += finalPrices["brownies"] ?: 0.0
        }
        if (mandmsBox.isChecked) {
            price += finalPrices["m&ms"] ?: 0.0
        }
        if (fudgeBar.progress == 1) {
            price += finalPrices["1oz"] ?: 0.0
        }
        if (fudgeBar.progress == 2) {
            price += finalPrices["2oz"] ?: 0.0
        }
        if (fudgeBar.progress == 3) {
            price += finalPrices["3oz"] ?: 0.0
        }
        price += finalPrices[sizeSpinner.selectedItem.toString().toLowerCase()] ?: 0.0
        priceView.text = ("$" + String.format("%.2f", price))
        subTotal = price
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_menu_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                intent.putExtra("KEY", author)
                startActivity(intent)
                return true
            }
            R.id.action_menu_order_history -> {
                val intent = Intent(this, OrderHistoryActivity::class.java)
                intent.putExtra("DATA", orderHistory)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addPrices(): Map<String, Double> {
        val prices = HashMap<String, Double>()
        prices["peanuts"] = 0.15
        prices["m&ms"] = 0.25
        prices["almonds"] = 0.15
        prices["brownies"] = 0.20
        prices["strawberries"] = 0.20
        prices["oreos"] = 0.20
        prices["gummy bears"] = 0.20
        prices["marshmallows"] = 0.15
        prices["1oz"] = 0.15
        prices["2oz"] = 0.25
        prices["3oz"] = 0.30
        prices["single"] = 2.99
        prices["double"] = 3.99
        prices["triple"] = 4.99
        return prices
    }

    private fun getToppings(): Array<String> {
        val temp = ArrayList<String>()
        if (peanutsBox.isChecked) {
            temp.add("Peanuts")
        }
        if (almondsBox.isChecked) {
            temp.add("Almonds")
        }
        if (strawberriesBox.isChecked) {
            temp.add("Strawberries")
        }
        if (gummiesBox.isChecked) {
            temp.add("Gummy Bears")
        }
        if (mandmsBox.isChecked) {
            temp.add("M&Ms")
        }
        if (browniesBox.isChecked) {
            temp.add("Brownies")
        }
        if (oreosBox.isChecked) {
            temp.add("Oreos")
        }
        if (marshmallowsBox.isChecked) {
            temp.add("Marshmallows")
        }
        return temp.toTypedArray()
    }
}
