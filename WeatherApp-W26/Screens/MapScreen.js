import { useEffect, useState } from 'react';
import MapView, { Marker } from 'react-native-maps';
import { StyleSheet, View } from 'react-native';
import * as Location from 'expo-location';
import Weather from '../Model/Weather';
import { useDispatch, useSelector } from 'react-redux';
import { fetchWeatherForLocation } from '../ReduxToolkit/WeatherSlice';

export default function MapScreen() {

    const [region, setRegion] = useState(null);
    const [marker, setMarker] = useState(null);
    const [weather, setWeather] = useState(null);

   // const weather = useSelector((state) => state.weather.current); 
    //const dispatch = useDispatch();
    
async function getCurrentLocation() {
        let { status } = await Location.requestForegroundPermissionsAsync();
        if (status !== 'granted') {
            setErrorMsg('Permission to access location was denied');
            return;
        }
        await Location.watchPositionAsync({
            accuracy: Location.Accuracy.BestForNavigation,
            distanceInterval: 10,
            timeInterval: 1000
        }, (location) => {
        const region = {
        latitude: location.coords.latitude,
        longitude: location.coords.longitude,
        latitudeDelta: 0.08,
        longitudeDelta: 0.08,
        };
        setRegion(region)
    })
    }
    useEffect(() => {
        getCurrentLocation();
    }, []);


   const fetchWeather = async (lat, lon) => {
    try {
    const API_KEY = 'ecf5553cc5b15522aea8026824cb8085';
    const response = await fetch(
        `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric`
    );
    if (!response.ok) throw new Error('City not found');
        const result = await response.json();
        console.log(result)
        setWeather(new Weather(result));
    } catch (err) {
        setError(err.message);
    } finally {
        setLoading(false);
    }
};
// get your api key from here: https://console.cloud.google.com/welcome?project=named-reporter-443011-h9
return (
    <View style={styles.container}>
        <MapView
            onLongPress={(e) => {
                setMarker(e.nativeEvent.coordinate)
                fetchWeather(e.nativeEvent.coordinate.latitude,e.nativeEvent.coordinate.longitude)
            }}
            // initialRegion={region}
            // region={region}
            style={styles.map} >
            {weather && <Marker
                coordinate={marker}
                title={`Temp: ${weather.temp} ${weather.description}`}
            ></Marker>}
        
            </MapView>
    </View>
);
}

const styles = StyleSheet.create({
container: {
    flex: 1,
},
map: {
    width: '100%',
    height: '100%',
},
});
