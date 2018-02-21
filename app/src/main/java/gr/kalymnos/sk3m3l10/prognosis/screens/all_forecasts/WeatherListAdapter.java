package gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;

/**
 * This adapter will bind Weather objects to a recycler view
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>{

    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;

    private Context context;
    private List<Weather> items;

    public WeatherListAdapter(Context context, List<Weather> items) {
        this.context=context;
        this.items=items;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(this.context);

        switch (viewType){
            case VIEW_TYPE_FUTURE_DAY:
                return new WeatherViewHolder(inflater.inflate(R.layout.forecast_list_item,parent,false));
            case VIEW_TYPE_TODAY:
                return new WeatherViewHolderForToday(inflater.inflate(R.layout.forecast_list_item_for_today,parent,false));
            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        Weather w = this.items.get(position);
        if (holder instanceof WeatherViewHolderForToday){
            ((WeatherViewHolderForToday) holder).bindViews(w.getQueryTitle(),w.getDate(),
                    w.getMainWeather(),w.getTempHighWithSymbol(),w.getTempLowWithSymbol());
        }else {
            holder.bindViews(w.getDate(),w.getMainWeather(),w.getTempHighWithSymbol(),w.getTempLowWithSymbol());
        }
    }

    @Override
    public int getItemCount() {
        if (this.items!=null){
            return items.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            // The first item in the list will be the todays forecast
            return VIEW_TYPE_TODAY;
        }else{
            return VIEW_TYPE_FUTURE_DAY;
        }
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder{

        private TextView date,weather,tempHigh,tempLow;
        private ImageView imageView;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            this.imageView =itemView.findViewById(R.id.img_weather);
            this.date=itemView.findViewById(R.id.tv_date);
            this.weather=itemView.findViewById(R.id.tv_weather);
            this.tempHigh=itemView.findViewById(R.id.tv_temp_high);
            this.tempLow=itemView.findViewById(R.id.tv_temp_low);
        }

        void bindViews(String date,String weather,String tempHigh,String tempLow){
            this.imageView.setImageResource(R.drawable.sunny);
            this.date.setText(date);
            this.weather.setText(weather);
            this.tempHigh.setText(tempHigh);
            this.tempLow.setText(tempLow);
        }
    }

    class WeatherViewHolderForToday extends WeatherViewHolder{
        private TextView queryTitle; // City name, location etc...

        public WeatherViewHolderForToday(View itemView) {
            super(itemView);
            this.queryTitle=itemView.findViewById(R.id.tv_query_title);
        }

        void bindViews(String queryTitle,String date, String weather, String tempHigh, String tempLow) {
            super.bindViews(date, weather, tempHigh, tempLow);
            this.queryTitle.setText(queryTitle);
        }
    }
}
