import { StyleSheet, View, FlatList ,Text, Button, TouchableOpacity} from 'react-native';
import { useEffect, useState } from 'react';
import { db } from '../Model/Database';
import { Feather } from '@expo/vector-icons';

const FavCitiesScreen = () => {
    const [dbCitiesList, setdbCitiesList] = useState([]);


 const confirmDelete = (city) => {
        Alert.alert("Confirm Delete",
            "Are you sure you want to delete this city form DB?",
            [
                { text: "Cancel", onPress: () => {  } },
                { text: "Delete", onPress: () => { handelDelete(city) } }
            ])
    }


  const handelDelete = async(city) => {
        await db.runAsync("Delete From cities WHERE city = $city" , {$city : city} )
        fetchCitiesFromDatabase()
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
            <FlatList
                data={dbCitiesList}
                keyExtractor={(item, i) => i}
                renderItem={({ item }) =>
                    <View style={styles.viewStyle}>
                        <Text style={styles.title}>{item}</Text>
                        <TouchableOpacity onPress={(item) => {
                            handelDelete(item)
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