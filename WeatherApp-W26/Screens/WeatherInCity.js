import { StyleSheet, View ,Text} from 'react-native';
import { useRoute } from '@react-navigation/native';
import { useState, useEffect } from 'react';

const WeatherInCity = () => {
    
    const route = useRoute();
    const { selectedCity } = route.params;
    const [temp, setTemp] = useState(0);
    const [feels_like, setFeelsLike] = useState(0)
    const [desc, setDesc] = useState("")
    const [icon,setIcon] = useState("")

    const fetchWeather = async (city) => {
            try {
                const response = await fetch("https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=ecf5553cc5b15522aea8026824cb8085&units=metric"); 
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
                const result = await response.json();
                setDesc(result.weather[0].description)
                setIcon(result.weather[0].icon)
                setTemp(result.main.temp)
                setFeelsLike(result.main.feels_like)

            }
            catch(err) {
                setError(err.message);
                console.log('Error in fetching cities')
            }
        }

    useEffect(() => {
        fetchWeather(selectedCity)// do any logic while the component rendered or at specific conditions. 
    } , []);
    
    return (
    <View style={styles.parent}>
            <Text style={styles.text}>{selectedCity}</Text>
            <Text style={styles.text}>{temp}</Text>
            <Text style={styles.text}>{feels_like}</Text>
            <Text style={styles.text}>{desc}</Text> 
    </View>
);
}

const styles = StyleSheet.create({
    parent: {
        flex: 1,
        backgroundColor: 'white',
    },
    text: {
        fontSize: 20
    }

});
export default WeatherInCity;