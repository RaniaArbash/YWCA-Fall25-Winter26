import { StyleSheet, View } from 'react-native';

const FlexObjectModelScreen = () => {
    return (
    <View style={styles.parent}>
            <View style={styles.redBox}></View>
            <View style={styles.greenBox}></View>
            <View style={styles.blueBox}></View>
    </View>
);
}

const styles = StyleSheet.create({
    parent: {
        height: 400,
        borderWidth: 3,
        borderColor: 'black',
        flexDirection: 'column',
        alignItems:'center',
        backgroundColor: '#0000',

    },
    redBox: {
        width: 100,
        height: 100,
        backgroundColor: 'red',
    },
    greenBox: {
        width: 100,
        height: 100,
        backgroundColor: 'green',
        alignSelf:'flex-start'
    },
    blueBox: {
        width: 100,
        height: 100,
        backgroundColor: 'blue',
        alignSelf:'flex-end'
    }
});


export default FlexObjectModelScreen;