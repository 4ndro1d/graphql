package com.example.ggwp.graphql.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ggwp.graphql.R
import com.example.ggwp.graphql.presenter.Presenter
import com.example.ggwp.graphql.toast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), View {

    private val presenter: Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attach(this)

        button.setOnClickListener { presenter.doSomething() }
    }

    override fun showToast(text: String) {
        toast(text)
    }

    override fun showText(text: String) {
        textView.text = text
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }
}
