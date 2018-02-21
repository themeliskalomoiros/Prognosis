package gr.kalymnos.sk3m3l10.prognosis.view_mvc;

import android.os.Bundle;
import android.view.View;

/**
 * A MVC View
 */

public interface ViewMvc {

    /*
    *   Gets the root Android View that is used internally by this MVC View.
    * */
    public View getRootView();


    /*
    *   Gets the direct Android root View state (indirectly the state of the MVC View),
    *   which is stored in a Bundle.
    * */
    public Bundle getViewState();
}
