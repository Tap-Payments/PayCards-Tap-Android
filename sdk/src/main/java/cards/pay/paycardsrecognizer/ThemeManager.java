package cards.pay.paycardsrecognizer;

/**
 * Created by AhlaamK on 6/1/20.
 * <p>
 * Copyright (c) 2020    Tap Payments.
 * All rights reserved.
 **/
public class ThemeManager {

    /**
     *  Frame Background
     */
    private int                     frameColour;


    /**
     * Frame color
     * @param frameColour
     */
    public ThemeManager setFrameColor(int frameColour) {
        this.frameColour = frameColour;
        return this;
    }

    /**
     * @return frame color
     */
    public int getFrameColor() {
        return this.frameColour;
    }
    //////////////////////////////////////////  Single Instance ////////////////////////

    /**
     * Get Shared instance of ThemeManager
     *
     * @return ThemeManager
     */
    public static ThemeManager getInstance() {
        return SingleInstanceAdmin.instance;
    }


    private static class SingleInstanceAdmin {
        /**
         * The Instance.
         */
        public final static ThemeManager instance = new ThemeManager();

    }

}