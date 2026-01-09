import { useRoute } from '@react-navigation/native';
import { useEffect, useState } from 'react';
import { ActivityIndicator, Image, StyleSheet, Text, View } from 'react-native';
import Weather from '../Model/Weather';

const WeatherInCity = () => {
const route = useRoute();
const { selectedCity } = route.params;

const [weather, setWeather] = useState(null);
const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchWeather = async (city) => {
    try {
    const API_KEY = 'ecf5553cc5b15522aea8026824cb8085';
    const response = await fetch(
        `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${API_KEY}&units=metric`
    );
 
    if (!response.ok) throw new Error('City not found');
 
    const result = await response.json();
    setWeather(new Weather(result));
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };
 
useEffect(() => {
    fetchWeather(selectedCity);
}, []);

if (loading) return <ActivityIndicator size='large' style={styles.center} />;

if (error)
    return (
    <View style={styles.center}>
        <Text>{error}</Text>        
    </View>
    );

return (
    <View style={styles.parent}>{weather && <WeatherCard {...weather} />}</View>
);
};
 
const WeatherCard = (weather) => {
  return (
    
    <View style={styles.card}>
    <Text style={styles.cityText}>{weather.cityName}</Text>
    {weather &&  <Image source={{ uri: weather.iconUrl }} style={styles.weatherIcon} />}

    <Text style={styles.tempText}>{weather.temp}°C</Text>
    <Text style={styles.descText}>{weather.description ? weather.description.toUpperCase() : ''}</Text>

          <View style={styles.detailsRow}>

        <Text style={styles.detail}>Feels like: {weather.feelsLike}°C</Text>
        <Text style={styles.detail}>Humidity: {weather.humidity}%</Text>
    </View>
    
    </View>
  );
};
 
const styles = StyleSheet.create({
parent: {
    flex: 1,
    backgroundColor: '#f5f5f5',
    alignItems: 'center',
    justifyContent: 'center',
  },
  center: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
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
 
export default WeatherInCity;