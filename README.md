# Stock and Forex Fetcher
Android Developer NanoDegree Project capstone II

stockandforexfetcher is a simple fully functional android app which I made for the Android Developer Nanodegree program.

With the app, you can:

   * Fetch Financial data from a remote network
   * persist data from saved events
   * Calculates the Pivot point, Resistance and Support price of selected currency
   * compute Exchange rate at real time
   * Instantly search for any stock, currency, or CFds with the instrument symbol
   * Gets the graphical analysis of any stock, or currency

# How to Work with the Source

This app uses alphavantage.co, and 1forge.com API to retrieve stock data at realtime. You must provide your own API key in order to build the app. When you get it, add it to the NetworkUrlRequest and GraphFetchers class.

## Features

* Present users with a linear layout arrangement of currency realtime information .

* Allow users to easily fetch currency price or input a custom price, and calculate the pivot point of currenies

* Implemented Room Database to save trade events data.

* Allow users to either fetch rates directly, or input a custom rates to convert one currency to another

* Allow users to interact with the app via a widget: When click the widget title, the app fetches current rates instantly . 

* Used adapters and viewholders, and viewpagers to populate list views and graphs.

* Support accessibility for vision-limited users.

* Stored all the strings in string.xml. Stored all the dimens in dimens.xml.

## Libraries

* Material Design
* Achartengine
* Room Database
* ButterKnife
* Volley
* Firebase 

## Screenshots
![photo5870651584583675742](https://user-images.githubusercontent.com/32399318/56173218-b546a980-5fe4-11e9-9e36-5ac0c3e7f315.jpg)
![photo5870651584583675743](https://user-images.githubusercontent.com/32399318/56173287-f048dd00-5fe4-11e9-917d-ebb20c5c195a.jpg)
![photo5870651584583675744](https://user-images.githubusercontent.com/32399318/56173289-f048dd00-5fe4-11e9-9b92-4d8403590547.jpg)
![photo5870651584583675745](https://user-images.githubusercontent.com/32399318/56173291-f048dd00-5fe4-11e9-8b95-ffe5eb2e6d80.jpg)
![photo5870651584583675746](https://user-images.githubusercontent.com/32399318/56173292-f0e17380-5fe4-11e9-8b50-4d3e15f5cbfa.jpg)
![photo5870651584583675747](https://user-images.githubusercontent.com/32399318/56173293-f0e17380-5fe4-11e9-886b-a90e2a15505a.jpg)
![photo5870651584583675748](https://user-images.githubusercontent.com/32399318/56173295-f17a0a00-5fe4-11e9-98cc-e227c480f3e9.jpg)
![photo5870651584583675749](https://user-images.githubusercontent.com/32399318/56173296-f17a0a00-5fe4-11e9-8a4b-4c4198bb40ec.jpg)


