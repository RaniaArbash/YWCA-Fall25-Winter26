import { configureStore } from "@reduxjs/toolkit";
import WeatherReducer from "../ReduxToolkit/WeatherSlice"

const store = configureStore({
    reducer: { weather: WeatherReducer },
})

export default store
