package cards.pay.sample.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cards.pay.paycardsrecognizer.sdk.FrameManager;
import cards.pay.paycardsrecognizer.sdk.Card;
import cards.pay.paycardsrecognizer.sdk.ScanCardIntent;
import cards.pay.paycardsrecognizer.sdk.ui.InlineViewCallback;
import cards.pay.paycardsrecognizer.sdk.ui.InlineViewFragment;
import cards.pay.paycardsrecognizer.sdk.ui.ScanCardActivity;

public class IntroActivity extends AppCompatActivity implements InlineViewCallback {

    private static final int REQUEST_CODE_SCAN_CARD = 101;

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        //Set the frame color of your choice
        FrameManager.getInstance().setFrameColor(Color.RED);

    }

    public void openFullScreenScanner(View view) {
        startActivityForResult(new Intent(this, ScanCardActivity.class), REQUEST_CODE_SCAN_CARD);
    }

    public void openInlineView(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.inline_container, new InlineViewFragment())
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN_CARD) {
            if (resultCode == Activity.RESULT_OK) {
                Card card = data.getParcelableExtra(ScanCardIntent.RESULT_PAYCARDS_CARD);
                showCardDialog(card);
            }
        }
    }

    private void showCardDialog(Card card) {
        String cardData = "Card number: " + card.getCardNumberRedacted() + "\n"
                + "Card holder: " + card.getCardHolderName() + "\n"
                + "Card expiration date: " + card.getExpirationDate();

        new AlertDialog.Builder(this)
                .setTitle("Card Info")
                .setMessage(cardData)
                .setPositiveButton(android.R.string.yes, null)
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
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
        getSupportFragmentManager().beginTransaction().
                remove(getSupportFragmentManager().findFragmentById(R.id.inline_container))
                .commit();
        showCardDialog(card);
    }
}
