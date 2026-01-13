// models/Weather.js
export default class Weather {
    constructor(json) {
        this.lat = json.coord.lat;
        this.lon = json.coord.lon;
        this.cityName = json.name;
        this.temp = Math.round(json.main.temp);
        this.feelsLike = Math.round(json.main.feels_like);
        this.description = json.weather[0].description;
        this.iconCode = json.weather[0].icon;
        this.humidity = json.main.humidity;
    } 

}