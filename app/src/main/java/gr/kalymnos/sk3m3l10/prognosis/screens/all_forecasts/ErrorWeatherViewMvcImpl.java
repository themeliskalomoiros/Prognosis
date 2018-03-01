package gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.view_mvc.ErrorViewMvc;

public class ErrorWeatherViewMvcImpl implements ErrorViewMvc {

    private View rootView;
    private Button reloadButton;

    private ReloadListener listener;

    public ErrorWeatherViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        this.rootView = inflater.inflate(R.layout.activity_main_error,parent,false);
        this.reloadButton = this.rootView.findViewById(R.id.reload_button);
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    public void setListener(ReloadListener listener) {

    }
}
