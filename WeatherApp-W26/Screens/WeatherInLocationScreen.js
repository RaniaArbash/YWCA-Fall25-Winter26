import { useEffect, useState } from 'react';
import { ActivityIndicator, Image, StyleSheet, Text, View } from 'react-native';
import Weather from '../Model/Weather';
import * as Location from 'expo-location';
import MapView from 'react-native-maps';
import { useDispatch, useSelector } from 'react-redux';
import WeatherCard from '../Components/WeatherCard';
import { fetchWeatherForLocation } from '../ReduxToolkit/WeatherSlice';

const WeatherInLocationScreen = () => {
//const route = useRoute();

//const [weather, setWeather] = useState(null);
//const [loading, setLoading] = useState(true);
//const [error, setError] = useState(null);
const [region, setRegion] = useState(null);

    const weather = useSelector((state) => state.weather.current); 
    const loading = useSelector((state) => state.weather.loading);
    const error = useSelector((state) => state.weather.error);

    const dispatch = useDispatch();
    
//     const fetchWeather = async (lat, lon) => {
//         console.log(lat)
//         console.log(lon)
//     try {
//     const API_KEY = 'ecf5553cc5b15522aea8026824cb8085';
//     const response = await fetch(
//         `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric`
//     );
//     if (!response.ok) throw new Error('City not found');
//         const result = await response.json();
//         setWeather(new Weather(result));
//     } catch (err) {
//         setError(err.message);
//     } finally {
//         setLoading(false);
//     }
// };
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
        console.log("region is : " + region.latitude)
        setRegion(region)
        //fetchWeather(location.coords.latitude, location.coords.longitude);
        dispatch(fetchWeatherForLocation({ lat: location.coords.latitude, lon: location.coords.longitude }));
        })
    }
    useEffect(() => {
        getCurrentLocation();
    }, []);
if (loading) return <ActivityIndicator size='large' style={styles.center} />;

if (error)
    return (
    <View style={styles.center}>
        <Text>{error}</Text>        
    </View>
    );

return (
    <View style={styles.parent}>
    <WeatherCard weather={weather}/>
    <MapView
            initialRegion={region}
            region={region}
            style={styles.map} />
    </View>
);
};


// // const WeatherCard = ({
// //     lat,
// //     lon,
// // temp,
// // description,
// // feelsLike,
// // iconCode,
// // humidity,
// // }) => {
// // const iconUrl = `https://openweathermap.org/img/wn/${iconCode}@2x.png`;
// // return (
// //     <View style={styles.card}>
// //         <Text style={styles.cityText}>{lon} - {lat}</Text>

// //       <Image
// //         source={{ uri: iconUrl }}
// //         style={styles.weatherIcon}
// //       />

// //       <Text style={styles.tempText}>{temp}°C</Text>
// //       <Text style={styles.descText}>{description?.toUpperCase()}</Text>

// //     <View style={styles.detailsRow}>
// //         <Text style={styles.detail}>Feels like: {feelsLike}°C</Text>
// //         <Text style={styles.detail}>Humidity: {humidity}%</Text>
// //         </View>
// //     </View>
// //     );
      
// };


const styles = StyleSheet.create({
parent: {
    flex: 1,
    backgroundColor: '#f5f5f5',
    alignItems: 'center',
    justifyContent: 'flex-start',
  },
  center: {
    flex: 1,
    alignItems: 'flex-start',
    },
map: {
    width: '100%',
    height: '50%',
},
card: {
    backgroundColor: 'white',
    borderRadius: 20,
    padding: 30,
    alignItems: 'center',
    width: '85%',
    elevation: 5, // Shadow for Android
    shadowColor: '#000', // Shadow for iOS
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.25,
    shadowRadius: 3.84,
  },
  cityText: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#333',
  },
  weatherIcon: {
    width: 120,
    height: 120,
  },
  tempText: {
    fontSize: 60,
    fontWeight: '200',
    color: '#ff8c00',
  },
  descText: {
    fontSize: 16,
    color: '#777',
    letterSpacing: 1,
    marginBottom: 20,
  },
  detailsRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '100%',
    borderTopWidth: 1,
    borderTopColor: '#eee',
    paddingTop: 15,
  },
  detail: {
    fontSize: 14,
    color: '#555',
  },
});
 
export default WeatherInLocationScreen;