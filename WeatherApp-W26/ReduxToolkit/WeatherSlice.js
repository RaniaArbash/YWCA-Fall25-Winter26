import { createSlice , createAsyncThunk} from "@reduxjs/toolkit";


export const fetchWeatherForCity = createAsyncThunk("weather/fetchWeatherByName",
    async (city) => {
        const res = await fetch("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=ecf5553cc5b15522aea8026824cb8085&units=metric");
    
        return await res.json();
    });

export const fetchWeatherForLocation = createAsyncThunk("weather/fetchWeatherForLocation",
    async ({ lat, lon }) => {
        console.log("In fetch weather for location")
        const res = await fetch("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=ecf5553cc5b15522aea8026824cb8085&units=metric");
        
        return await res.json();
    });

const weatherSlice = createSlice({
    name: "weather",
    initialState: {
        current: null,
        loading: false,
        error: null
    },
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(fetchWeatherForCity.fulfilled, (state, action) => {
            state.loading = false;
            state.current = action.payload
        })// networking is done ==> weatherForCity is available
        builder.addCase(fetchWeatherForCity.rejected, (state) => {
            state.error = "Faild To get the weather"
            state.loading = false
        }) // networking faild ==> weatherForCity is Notavailable
        builder.addCase(fetchWeatherForCity.pending, (state) => { state.loading = true }) // networking is in Process ==> weatherForCity is Not available Yet
    
        builder.addCase(fetchWeatherForLocation.rejected, (state) => {
            state.error = "Faild To get the weather"
            state.loading = false
        })// networking is done ==> weatherForLocation is available
        builder.addCase(fetchWeatherForLocation.fulfilled, (state, action) => {
            state.loading = false;
            state.current = action.payload
            console.log("get the weather here " + state.current.main.feels_like)

        }) // networking faild ==> weatherForLocation is Notavailable
        builder.addCase(fetchWeatherForLocation.pending, (state) => { state.loading = true }) // networking is in Process ==> weatherForLocation is Not available Yet
    
    
    }
});

export default weatherSlice.reducer;