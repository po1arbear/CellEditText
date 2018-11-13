package com.po1arbear.custom.celledittext

import android.annotation.SuppressLint
import android.content.Context

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.input_cell_layout.view.*

class CellInputView : RelativeLayout {

    private val number = 6
    private val textViews: Array<TextView?> = arrayOfNulls(number)
    private var inputContent: String = ""
    private var isPassword = false
    private var inputType = 0

    constructor (context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @SuppressLint("Recycle")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        View.inflate(context, R.layout.input_cell_layout, this)
        var textColor = Color.parseColor("#396AFC")
        var textSize = 16f
        attrs?.let {
            context.obtainStyledAttributes(attrs, R.styleable.CellInputView)?.let {
                isPassword = it.getBoolean(R.styleable.CellInputView_isPassword, false)
                textColor = it.getColor(R.styleable.CellInputView_textColor, Color.parseColor("#1B1B4E"))
                textSize = it.getFloat(R.styleable.CellInputView_textSize, 16f)
                inputType = it.getInt(R.styleable.CellInputView_inputType, EditorInfo.TYPE_CLASS_NUMBER)
                it.recycle()
            }
        }

        val width = (context.resources.displayMetrics.widthPixels - UIUtils.dp2px(context, 16f) * 7) / 6
        for (i in 0 until ll_verity.childCount) {
            val child = ll_verity.getChildAt(i)
            val layoutParams = child.layoutParams
            layoutParams.width = width
            layoutParams.height = width
            child.layoutParams = layoutParams
            textViews[i] = child as TextView
            textViews[i]?.setTextColor(textColor)
            textViews[i]?.textSize = textSize
        }
        val layoutParams = et_input.layoutParams as LayoutParams
        layoutParams.height = width
        et_input.layoutParams = layoutParams
        et_input.inputType = inputType
        textViews[0]?.isSelected = true
        setEditTextListener()
    }

    private fun setEditTextListener() {
        et_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                inputContent = et_input.text.toString()
                if (inputContent.length >= number) {
                    inputCompleteListener?.inputComplete()
                }
                for (i in 0 until number) {
                    textViews[i]?.isSelected = false
                    if (i < inputContent.length) {
                        textViews[i]?.text = if (isPassword) "●" else inputContent[i].toString()
                    } else {
                        textViews[i]?.text = ""
                    }
                }
                when {
                    number - 1 <= inputContent.length -> textViews[5]?.isSelected = true
                    inputContent.isEmpty() -> textViews[0]?.isSelected = true
                    else -> textViews[inputContent.length]?.isSelected = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })


        et_input.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                inputContent = et_input.text.toString()
                if (inputContent.length >= number) {
                    inputCompleteListener?.inputComplete()
                }
                return@OnEditorActionListener true
            }
            false
        })

    }

    fun clearContent() {
        et_input.setText("")
    }

    private var inputCompleteListener: InputCompleteListener? = null

    fun setInputCompleteListener(inputCompleteListener: InputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener
    }

    interface InputCompleteListener {

        fun inputComplete()

        fun invalidContent() {}
    }

    fun setDigitsKeyListener(digitsKeyListener: DigitsKeyListener) {
        et_input.keyListener = digitsKeyListener
    }

    fun setIsPassword(isPassword: Boolean) {
        this.isPassword = isPassword
    }

    fun setTextColor(color: Int) {
        textViews.forEach { it?.setTextColor(ContextCompat.getColor(context, color)) }
    }

    fun getEditContent(): String {
        return inputContent
    }

    fun showKeyboard() {
        et_input.isFocusable = true
        et_input.isFocusableInTouchMode = true
        et_input.requestFocus()
        val inputManager = context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(et_input, 0)
    }

    fun hideKeyboard() {
        val inputManager: InputMethodManager? =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager?.hideSoftInputFromWindow(windowToken, 0)
    }


}