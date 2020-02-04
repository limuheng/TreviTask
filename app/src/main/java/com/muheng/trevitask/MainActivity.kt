package com.muheng.trevitask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {

    private var editCol: EditText? = null
    private var editRow: EditText? = null
    private var drawBtn: Button? = null

    private var gridView: GridView? = null

    private val mainPresent: IMainPresenter = MainPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editCol = findViewById(R.id.input_col)
        editRow = findViewById(R.id.input_row)
        drawBtn = findViewById(R.id.drow)

        gridView = findViewById(R.id.gridview)

        editCol?.doOnTextChanged { text, _, _, _ ->
            try {
                mainPresent.setCol(text.toString().toInt())
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        editRow?.doOnTextChanged { text, _, _, _ ->
            try {
                mainPresent.setRow(text.toString().toInt())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        drawBtn?.setOnClickListener {
            mainPresent.createAdapter()?.let {
                gridView?.numColumns = mainPresent.getCol()
                gridView?.adapter = it

                mainPresent.selectPoint(2, 3)
            } ?: run {
                Toast.makeText(this, "Unable to create adapter for GridView", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
