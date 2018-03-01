package gr.kalymnos.sk3m3l10.prognosis.common.weather;

/*
* A Weather obj acquired by the OpenWeatherMap.org service.
*
* */

import android.text.TextUtils;

import gr.kalymnos.sk3m3l10.prognosis.common.weather_units.WeatherUnits;
import gr.kalymnos.sk3m3l10.prognosis.model_mvc.OpenWeatherImageProvider;

public class OpenWeather extends Weather {

    // The iconFileName is the name of the icon that OpenWeatherMap.org set to a specific weather.
    private String cityName,countryCode,iconFileName;
    private boolean obtainedFromDeviceLocation = false;

    public OpenWeather(String cityName, String countryCode, String iconFileName, long timeMilli, String mainWeather,
                       String description, int tempHigh, int tempLow, int humidity,
                       int pressure, double wind, WeatherUnits weatherUnit) {
        super(timeMilli, mainWeather, description, tempHigh, tempLow, humidity, pressure, wind, weatherUnit);
        this.cityName=cityName;
        this.countryCode=countryCode;
        this.iconFileName=iconFileName;
    }

    public boolean isObtainedFromDeviceLocation() {
        return obtainedFromDeviceLocation;
    }

    public void setObtainedFromDeviceLocation(boolean obtainedFromDeviceLocation) {
        this.obtainedFromDeviceLocation = obtainedFromDeviceLocation;
    }

    @Override
    public String toString() {
        if (isObtainedFromDeviceLocation()){
            String msg = "Your location";
            if (!TextUtils.isEmpty(this.countryCode)){
                return msg+"\n"+this.cityName+", "+countryCode;
            }else{
                return msg+"\n"+this.cityName;
            }
        }else{
            if (!TextUtils.isEmpty(this.countryCode)){
                return this.cityName+", "+countryCode;
            }else{
                return this.cityName;
            }
        }
    }

    @Override
    public int getImage() {
        return OpenWeatherImageProvider.getInstance().getImage(this.iconFileName);
    }
}
