package com.image.loader.library;

import android.content.Context;

/**
 * @author obo
 * @date 2018/1/30
 */

public class OpicLoader {
    public static OpicRequest with(Context context) {
        return new OpicRequest(context);
    }
}
