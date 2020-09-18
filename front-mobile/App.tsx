import {
  Play_400Regular,
  Play_700Bold, useFonts
} from '@expo-google-fonts/play';
import { AppLoading } from 'expo';
import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { StyleSheet, View } from 'react-native';
import Routes from './src/routes';


export default function App() {
  let [fontsLoaded] = useFonts({
    Play_400Regular,
    Play_700Bold,
  });

  if(!fontsLoaded) {
    return <AppLoading />
  }

  return (
    <View style={styles.container}>
      <StatusBar style="light" />
      <Routes />
    </View>    
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  }
});
