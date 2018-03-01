package gr.kalymnos.sk3m3l10.prognosis.view_mvc;

/**
 * This view will be displayed to the user in case of a weather forecast impossibility.
 */

public interface ErrorViewMvc extends ViewMvc {

    interface ReloadListener{
        /*
        *   This will be invoked when the user orders a reload
        * */
        void onReloadClicked();
    }

    void setListener(ReloadListener listener);
}
