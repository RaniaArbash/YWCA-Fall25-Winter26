import { StyleSheet, View, FlatList ,Text,Alert, Button, TouchableOpacity} from 'react-native';
import { useEffect, useState } from 'react';
import { db } from '../Model/Database';
import { Feather } from '@expo/vector-icons';
import WeatherBarChart from '../Components/BarChart';

const FavCitiesScreen = () => {

  const API_KEY = "ecf5553cc5b15522aea8026824cb8085"; 
    const [dbCitiesList, setdbCitiesList] = useState([]);
    const [chartData, setChartData] = useState([]);
    const [showChart, setShowChart] = useState(false);
const confirmDelete = (city) => {
        Alert.alert("Confirm Delete",
            "Are you sure you want to delete this city form DB?",
            [
                { text: "Cancel", onPress: () => {  } },
                { text: "Delete", onPress: () => { handelDelete(city) } }
            ])
    }
const handelDelete = async (city) => {
    await db.runAsync(
        "DELETE FROM cities WHERE city = $city",
        { $city: city }
    );
    fetchCitiesFromDatabase();
};
    
    const fetchWeatherForCities = async () => { 
        console.log("in fetch weather for cities")
        const results = await Promise.all(
        dbCitiesList.map(async (city) => { 
        const res = await fetch(
            `https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&appid=${API_KEY}`
        );
            const json = await res.json();
            
        return {
            city,
            temp: json.main.temp
        };
    })
        );
    console.log(results)

    setChartData(results);
    setShowChart(true);

    }

    const fetchCitiesFromDatabase = async () => {
        if (!db) {
            console.log("No database");
            return;
        }
        
        try {
        var favcities = await db.getAllAsync(`SELECT * FROM cities`);
            var dblist = []
            for (const row of favcities) {
                console.log(row.city)
                dblist.push(row.city)
            }
            setdbCitiesList(dblist)
            }
    catch (error) {
    console.error('Error inserting city:', error);
    }        
    }

    useEffect(() => {
        fetchCitiesFromDatabase();
    },[])

    return (
        <View style={styles.parent}>    
            <Button title='Reload' onPress={()=>{fetchCitiesFromDatabase()}}></Button>
        <Button
        title={showChart ? "Hide Chart" : "Show Chart"}
        onPress={() => {
            showChart ? setShowChart(false) : fetchWeatherForCities();
        }}
        />
        {showChart && chartData.length > 0 && (
        <WeatherBarChart weatherData={chartData} />
        )}
            <FlatList
                data={dbCitiesList}
                keyExtractor={(item, i) => i}
                renderItem={({ item }) =>
                    <View style={styles.viewStyle}>
                        <Text style={styles.title}>{item}</Text>
                        <TouchableOpacity onPress={(item) => {
                            confirmDelete(item)
                        }}>
                            <Feather name="trash" style={styles.iconStyle}></Feather>
                        </TouchableOpacity>
                    </View>
                    
                }

    />
    </View>
);
}

const styles = StyleSheet.create({
    parent: {
        flex: 1,
        backgroundColor: 'white',
    },
     viewStyle: {
        flexDirection: 'row',
        alignItems: 'stretch',
        justifyContent:'space-between',
        borderWidth: 1,
        margin: 10,
        borderRadius: 5,
        padding: 10
    },
    title: {
        fontSize: 18,
        padding: 2
    },
    iconStyle: {
        color: 'black',
        fontSize: 35,
        alignSelf: 'center',
        marginHorizontal: 15
    },
});
export default FavCitiesScreen;
