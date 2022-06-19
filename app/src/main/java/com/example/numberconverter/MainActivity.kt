package com.example.numberconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : AppCompatActivity() {

    private lateinit var input: AppCompatEditText
    private lateinit var button: AppCompatButton
    private lateinit var output: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        output = findViewById(R.id.textView)

        val unit19 = listOf(
            "ერთი",
            "ორი",
            "სამი",
            "ოთხი",
            "ხუთი",
            "ექვსი",
            "შვიდი",
            "რვა",
            "ცხრა",
            "ათი",
            "თერთმეტი",
            "თორმეტი",
            "ცამეტი",
            "თოთხმეტი",
            "თხუთმეტი",
            "თექვსმეტი",
            "ჩვიდმეტი",
            "თვრამეტი",
            "ცხრამეტი"
        )
        val dozen = listOf(
            "ათი",
            "ოცი",
            "ოცდაათი",
            "ორმოცი",
            "ორმოცდაათი",
            "სამოცი",
            "სამოცდაათი",
            "ოთხმოცი",
            "ოთხმოცდაათი"
        )
        val dozen2 = listOf("", "ოცდა", "ორმოცდა", "სამოცდა", "ოთხმოცდა")
        val hundred = listOf(
            "ასი",
            "ორასი",
            "სამასი",
            "ოთხასი",
            "ხუთასი",
            "ექვსასი",
            "შვიდასი",
            "რვაასი",
            "ცხრაასი",
            "ათასი"
        )

        fun converter(number: Int): String {

            return if (number !in 1..1000) "Please enter a valid value!"
            else if (number in 1..19) unit19[number - 1]                                                                                                // 1,2,3,19
            else if (number in 10..99 && number % 10 == 0) dozen[number / 10 - 1]                                                                       // 10, 20, 30
            else if (number / 100 < 1 && number / 10 != 0 && number.toString().length == 2) dozen2[number / 20] + unit19[number % 20 - 1]               // 32,87,45
            else if (number % 100 == 0) hundred[number / 100 - 1]                                                                                       // 100, 200, 900
            else if (number / 100 > 0 && number % 10 == 0) hundred[number / 100 - 1].substring(
                0, (hundred[number / 100 - 1]).length - 1
            ) + " " + dozen[(number % 100) / 10 - 1]                                                                                                    // 120, 430, 990
            else if (number / 100 > 0 && number % 10 != 0) hundred[number / 100 - 1].substring(
                0, (hundred[number / 100 - 1]).length - 1
            ) + " " + dozen2[(number % 100) / 20] + unit19[(number % 100) % 20 - 1]                                                                      // 121, 199, 999
            else "Please enter a valid value!"
        }

        button.setOnClickListener {

            val number = input.text.toString().toInt()
            val result = converter(number)
            output.text = result
            input.text?.clear()

        }
    }
}