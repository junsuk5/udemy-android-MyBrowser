package suwonsmartapp.com.mybrowser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private EditText mAddressEdit;
    private WebView myWebView;
    private Button mMoveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = (WebView) findViewById(R.id.web_view);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());

        mAddressEdit = (EditText) findViewById(R.id.address_edit);
        mMoveButton = (Button) findViewById(R.id.move_button);

        mMoveButton.setOnClickListener(this);

        mAddressEdit.setOnEditorActionListener(this);

    }

    @Override
    public void onClick(View v) {
        String address = mAddressEdit.getText().toString();
        if (address.startsWith("http://") == false) {
            address = "http://" + address;
        }
        myWebView.loadUrl(address);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            mMoveButton.callOnClick();
            return true;
        }
        return false;
    }
}
