import { useState } from 'react';
import { StyleSheet, View ,Text} from 'react-native';
import ColorBox from '../Components/ColorBox';

export default function ColorGeneratorScreen() {
    const [red,setRed] = useState(0)
    const [green,setGreen] = useState(0)
    const [blue, setBlue] = useState(0)
    
    const validate = (color) => { // 0 and 255
        return (color <= 255 && color >= 0) ? true : false;
    }
    return (
    <View style={styles.backgroud}>
        <View style={{
        width: 150,
        height: 150,
        backgroundColor: `rgb(${red},${green},${blue})`
            }}></View>
            <Text style={styles.textStyle}>{red}  - {green} -  { blue}</Text>
            <ColorBox
                colorName="Red"
                moreColor={() => {
                    validate(red) ? setRed(red + 5) : setRed(red)
                }}
                lessColor={() => {
                    validate(red)?  setRed(red - 5) : setRed(red)
                }}
            />
            <ColorBox
                colorName="Green"
                moreColor={() => {
                        validate(green)?  setGreen(green + 5) : setGreen(green)
                }}
                lessColor={() => {
                    validate(green)?  setGreen(green - 5) : setGreen(green)
                }}
            />
            <ColorBox
                colorName="Blue"
                moreColor={() => {
                    validate(blue)?  setBlue(blue + 5) : setBlue(blue)
                }}
                lessColor={() => {
                    validate(blue)?  setBlue(blue - 5) : setBlue(blue)
                }}
            />
    </View>
);
}

const styles = StyleSheet.create({
    backgroud: {
        flex: 1, 
        backgroundColor: '#0000',
        alignItems: 'center'
    }
});
