package cards.pay.paycardsrecognizer.sdk.ndk;

import android.support.annotation.RestrictTo;


public interface TorchStatusListener {

    void onTorchStatusChanged(boolean turnTorchOn);

}
