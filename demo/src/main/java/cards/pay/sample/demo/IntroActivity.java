package cards.pay.sample.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cards.pay.paycardsrecognizer.sdk.Card;
import cards.pay.paycardsrecognizer.sdk.ui.InlineViewCallback;
import cards.pay.paycardsrecognizer.sdk.ui.InlineViewFragment;
import cards.pay.paycardsrecognizer.sdk.ui.ScanCardActivity;

public class IntroActivity extends AppCompatActivity implements InlineViewCallback {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void openFullScreenScanner(View view) {
        startActivity(new Intent(this, ScanCardActivity.class));
    }

    public void openInlineView(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.inline_container, new InlineViewFragment())
                .commit();
    }

    @Override
    public void onScanCardCanceled() {
        Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScanCardFailed(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScanCardFinished(Card card, byte[] cardImage) {
        Toast.makeText(this, card.getCardNumber(), Toast.LENGTH_SHORT).show();
    }
}
