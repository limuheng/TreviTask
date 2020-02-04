package com.muheng.trevitask

import android.content.Context
import android.widget.BaseAdapter

class MainPresenterImpl(private val context: Context): IMainPresenter {

    private var col: Int = DEFAULT_VAL
    private  var row: Int = DEFAULT_VAL

    private val gridViewPresenter: IGridViewPresenter = GridViewPresenterImpl(context)

    override fun setCol(col: Int) {
        if (col > 0) {
            this.col = col
        }
    }

    override fun getCol(): Int {
        return col
    }

    override fun setRow(row: Int) {
        if (row > 0) {
            this.row = row
        }
    }

    override fun getRow(): Int {
        return row
    }

    override fun createAdapter(): BaseAdapter? {
        gridViewPresenter.init(col, row) {
            Item(false)
        }
        return gridViewPresenter.getAdapter()
    }

    override fun selectPoint(x: Int, y: Int): Boolean {
        return gridViewPresenter.setSelectedPoint(x, y)
    }

}