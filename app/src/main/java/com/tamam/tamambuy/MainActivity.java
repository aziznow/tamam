package com.tamam.tamambuy;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String homeUrl = "https://tamamb.myshopify.com/";
    private final String cartUrl = "https://tamamb.myshopify.com/cart";
    private final String profileUrl = "https://shopify.com/67492970749/auth/lookup?destination_uuid=97a11aa3-f1e1-499f-a1b8-042d642fe446&redirect_uri=https%3A%2F%2Fshopify.com%2F67492970749%2Faccount%2Fcallback&rid=bc816d35-fe56-4bfc-b3e9-1c4e7564f1fc&ui_locales=en&verify=1719945896-6BB9sqHBatde5lcFv2iehxgMk4rHLaMkEJaDEG544dg%3D";
    private final String menuUrl = "https://tamamb.myshopify.com/collections/all";
    private WebView webView;

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setTextZoom(100);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setDefaultTextEncodingName("UTF-8");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("https://api.whatsapp.com/") || url.startsWith("whatsapp://")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    return true; // Return true indicating we've handled the URL
                }
                view.loadUrl(url);
                return false; // Return false to let the WebView load the URL
            }
        });

        // Load the initial URL
        webView.loadUrl(homeUrl);

        // Set up the footer buttons
        Button btnHome = findViewById(R.id.btn_home);
        Button btnCart = findViewById(R.id.btn_cart);
        Button btnProfile = findViewById(R.id.btn_profile);
        Button btnMenu = findViewById(R.id.btn_menu);

        btnHome.setOnClickListener(v -> {
            webView.loadUrl(homeUrl);
        });

        btnCart.setOnClickListener(v -> {
            webView.loadUrl(cartUrl);
        });

        btnProfile.setOnClickListener(v -> {
            // You can set up a URL for the Profile button or handle it differently
            // For demonstration, showing a toast message
            // Toast.makeText(MainActivity.this, "Profile clicked", Toast.LENGTH_SHORT).show();
            webView.loadUrl(profileUrl);
        });

        btnMenu.setOnClickListener(v -> {
            // You can set up a URL for the Menu button or handle it differently
            // For demonstration, showing a toast message
            // Toast.makeText(MainActivity.this, "Menu clicked", Toast.LENGTH_SHORT).show();
            webView.loadUrl(menuUrl);
        });
    }
}
