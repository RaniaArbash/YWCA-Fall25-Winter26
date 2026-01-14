import { StyleSheet, View, FlatList ,Text,Alert, TouchableOpacity} from 'react-native';
import SearchBar from '../Components/SearchBar';
import { useState } from 'react';
import { db } from '../Model/Database';
const CitySearch = ({navigation}) => {
    const [searchTerm, setSearchTerm] = useState('');
    const [citiesList, setCitiesList] = useState([]);
    const [selectedCity, setselectedCity] = useState("")

    const insertCityIntoDB = async (city) => {
        if (!db) {
            console.log("No database");
            return;
        }
    const statement = await db.prepareAsync('INSERT INTO cities (city) VALUES ($value)');
    try {
        let result = await statement.executeAsync({ $value: city });
            console.log("City inserted successfully:", result);
    }
    catch (error) {
    console.error('Error inserting city:', error);
    }        
    }


    const fetchCity = async (query) => {
        if (query.length > 2) {
            try {
                const response = await fetch('http://gd.geobytes.com/AutoCompleteCity?&q='+query); 
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
                const result = await response.json();
                console.log(result)
                setCitiesList(result)
            }
            catch(err) {
                setError(err.message);
                console.log('Error in fetching cities')
            }
        } else {
            setCitiesList([])
            }
    }

    
    const showAnAlert = (c) => {
        console.log(c)
        Alert.alert('Add to favorait City???', 'Do you want to save this city in DB?', [
            {
                text: 'Yes, save to db and go to weather', onPress: () => { 
                    insertCityIntoDB(c)
                    navigation.navigate('WeatherInCity',{selectedCity: c})
                
            } },
            {
                text: 'No, just go to weather', onPress: () => { 
                        navigation.navigate('WeatherInCity',{selectedCity: c})
            } },
        ])
    }

    return (
    <View style={styles.parent}>
            <SearchBar
                term={searchTerm}
                onTermChanged={(newTerm) => {
                    setSearchTerm(newTerm)
                    fetchCity(newTerm)
                }}></SearchBar>
            
            <FlatList
                data={citiesList}
                keyExtractor={(item, i) => i}
                renderItem={({ item }) =>
                    <TouchableOpacity onPress={() => {
                        setselectedCity(item)
                        showAnAlert(item)
                    }}>
                        <Text style={styles.title}>{item}</Text>
                    </TouchableOpacity>
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
    title: {
        fontSize: 18,
        borderWidth: 1,
        borderColor: 'gray',
        padding: 2
},
});
export default CitySearch;