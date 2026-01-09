// models/Weather.js
export default class Weather {
    constructor(json) {
        this.cityName = json.name;
        this.temp = Math.round(json.main.temp);
        this.feelsLike = Math.round(json.main.feels_like);
        this.description = json.weather[0].description;
        this.iconCode = json.weather[0].icon;
        this.humidity = json.main.humidity;
    }

    // Getter to simplify the URL generation
    get iconUrl() {
        return `https://openweathermap.org/img/wn/${this.iconCode}@4x.png`;
    }
}