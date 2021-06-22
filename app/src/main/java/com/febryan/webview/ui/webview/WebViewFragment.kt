package com.febryan.webview.ui.webview

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.febryan.webview.R
import com.febryan.webview.databinding.WebViewFragmentBinding

class WebViewFragment : Fragment() {

    companion object {
        fun newInstance() = WebViewFragment()
    }

    private lateinit var viewModel: WebViewViewModel
    private var webViewBinding: WebViewFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.web_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = WebViewFragmentBinding.bind(view)
        webViewBinding = binding

        val loading = ProgressDialog(context)
        loading.setMessage("Loading web...")

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = object : WebViewClient(){

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                loading.show()

            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                loading.dismiss()

            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)

                loading.dismiss()

                binding.imgError.visibility = View.VISIBLE
                binding.webView.visibility = View.GONE

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    Toast.makeText(context, error?.description, Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Web Error", Toast.LENGTH_SHORT).show()
                }

            }
        }

        binding.webView.loadUrl("https://idn.id")  //url nya salahin aja kalo mau liat deskripsi errornya
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WebViewViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webViewBinding = null
    }

}